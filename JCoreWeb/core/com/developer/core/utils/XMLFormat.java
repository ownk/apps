package com.developer.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XMLFormat {

	public static String format(String xml) {

		String h = "";

		// elimina comentarios

		int i = 0;
		int j = 0;

		while ((i = xml.indexOf("<!--")) >= 0) {
			j = xml.indexOf("-->", i);
			String t1 = xml.substring(0, i);
			String t2 = xml.substring(j + 3);

			xml = t1 + t2;
		}

		// elimina header

		i = 0;

		while ((i = xml.indexOf("<?")) >= 0) {
			j = xml.indexOf(">", i);
			String t1 = xml.substring(0, i);
			h += xml.substring(i, j + 1) + "\n";
			String t2 = xml.substring(j + 1);

			xml = t1 + t2;
		}

		i = 0;

		while ((i = xml.indexOf("<!")) >= 0) {
			j = xml.indexOf(">", i);
			String t1 = xml.substring(0, i);
			h += xml.substring(i, j + 1) + "\n";
			String t2 = xml.substring(j + 1);

			xml = t1 + t2;
		}

		xml = h + format(xml.replace('\n', ' ').replace('\r', ' ').replace('\t', ' '), 0).trim();

		while (xml.indexOf("  ") >= 0) {
			xml = xml.replace("  ", " ");
		}

		return xml;
	}

	private static String format(String xml, Integer ident) {

		String tab = espacios(ident);

		if (xml == null) {
			return "";
		}

		xml = xml.trim();

		if (xml.length() == 0) {
			return "";
		}

		if (xml.charAt(0) != '<') {
			return xml;
		}

		// ---------------------------------------------

		int nivel = 0;

		boolean escapado = false;
		List<String> bloques = new ArrayList<String>();

		int iniciobloque = 0;
		for (int i = 0; i < xml.length(); i++) {
			String c = xml.charAt(i) + "";
			String n = (i + 1 < xml.length()) ? (xml.charAt(i + 1) + "") : "";

			if (escapado) {

				if ("'\"".indexOf(c) >= 0) {
					escapado = false;
				}

			} else {
				if ("<".equals(c) && "/".endsWith(n)) {
					nivel--;
					i = xml.indexOf(">", i);

					if (nivel == 0) {
						bloques.add(xml.substring(iniciobloque, i + 1));
						iniciobloque = i;
					}

				} else

				if ("/".equals(c) && ">".endsWith(n)) {
					nivel--;
					i++;

					if (nivel == 0) {
						bloques.add(xml.substring(iniciobloque, i + 1));
						iniciobloque = i;
					}

				} else

				if ("<".equals(c)) {

					if (nivel == 0) {
						iniciobloque = i;
					}

					nivel++;
				} else

				if ("'\"".indexOf(c) >= 0) {
					escapado = true;
				}
			}
		}

		if (bloques.size() > 1) {
			String ret = "";
			for (String d : bloques) {
				ret += format(d, ident);
			}
			return ret;
		}

		// ---------------------------------------------

		int pos_cerrado1 = xml.indexOf("/>");
		int pos_cerrado2 = xml.indexOf(">");
		int pos_cerrado3 = xml.lastIndexOf("<");

		if (pos_cerrado1 >= 0 && pos_cerrado1 < pos_cerrado2) {
			String d = xml.substring(0, pos_cerrado1 + 2);
			return tab + d + format(xml.substring(d.length()), ident);
		} else {

			String d1 = xml.substring(0, pos_cerrado2 + 1);
			String d2 = xml.substring(d1.length(), pos_cerrado3);
			String d3 = xml.substring(pos_cerrado3);
			String d4 = format(d2, ident + 1);

			return tab + d1 + d4 + ((d4.length() > 0 && d4.charAt(d4.length() - 1) == '>') ? tab : "") + d3;

		}

	}

	private static String espacios(int n) {

		StringBuffer ret = new StringBuffer("\n");
		for (int i = 0; i < n; i++) {
			ret.append("\t");
		}

		return ret.toString();
	}
	
	public static String eliminarCaracteresEspeciales(String xml){
	
		String diccionario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 _<>.![]{}*+-";

		Map<Character, Character> mapaTraductor = new HashMap<Character, Character>();
		mapaTraductor.put('ñ', 'n');
		mapaTraductor.put('Ñ', 'N');
		mapaTraductor.put('á', 'a');
		mapaTraductor.put('é', 'e');
		mapaTraductor.put('í', 'i');
		mapaTraductor.put('ó', 'o');
		mapaTraductor.put('ú', 'u');
		mapaTraductor.put('Á', 'A');
		mapaTraductor.put('É', 'E');
		mapaTraductor.put('Í', 'I');
		mapaTraductor.put('Ó', 'O');
		mapaTraductor.put('Ú', 'U');
		
		if (xml == null) {
			return null;
		}

		if (mapaTraductor != null) {
			Set<Character> mapakey = mapaTraductor.keySet();

			// Se remplazan todos los caracteres especificados en el mapa
			for (Character key : mapakey) {
				xml = xml.replace(key, mapaTraductor.get(key));
			}
		}

		// Luego de reemplazar se valida con el diccionario
		StringBuffer textoReemplazado = new StringBuffer();
		for (int i = 0; i < xml.length(); i++) {
			
			
			if((xml.charAt(i)+"").equals("/") && i>0){
				if((xml.charAt(i-1)+"").equals("<")){
					textoReemplazado.append(xml.charAt(i));
					
				}
			}
		
			if (diccionario.contains(xml.charAt(i) + "")) {
				textoReemplazado.append(xml.charAt(i));
			}
			
			
		}
		
		return textoReemplazado.toString();
	}

}
