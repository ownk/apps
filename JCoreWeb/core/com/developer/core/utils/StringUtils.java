package com.developer.core.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class StringUtils {

	public static final String segureCode = "MEDKSIWEODMLSLLJRJIYEBQPWORJUNMXNZKAJEUFHGHJSKLALSKASDJHOJOWIYEBKJKLASDJAWQOEUBDUEWGFTTRUEOPIUQOWEHWSNMKCDSJKC";

	public static List<String> subString(List datos, int ini) {

		List<String> ret = new ArrayList<String>();

		if (datos != null && !datos.isEmpty()) {

			for (Object dat : datos) {

				if (dat != null) {
					String strdat = dat.toString();

					try {
						ret.add(strdat.substring(ini));
					} catch (Exception e) {
					}
				}
			}
		}

		return ret;
	}

	public static List<String> subString(List datos, int ini, int fin) {

		List<String> ret = new ArrayList<String>();

		if (datos != null && !datos.isEmpty()) {

			for (Object dat : datos) {

				if (dat != null) {
					String strdat = dat.toString();

					try {
						ret.add(strdat.substring(ini, fin));
					} catch (Exception e) {
					}
				}
			}
		}

		return ret;
	}

	public static List<String> filtrarLista(List datos, String txt) {

		List<String> ret = new ArrayList<String>();

		if (datos != null && !datos.isEmpty()) {

			for (Object dat : datos) {

				if (dat != null) {
					String strdat = dat.toString();

					if (strdat.indexOf(txt) >= 0) {
						ret.add(strdat);
					}
				}
			}
		}

		return ret;
	}

	public static String aplanar(List datos, String separador) {

		StringBuffer ret = new StringBuffer("");

		if (datos != null && !datos.isEmpty()) {

			for (int i = 0; i < datos.size(); i++) {
				Object reg = datos.get(i);

				if (reg != null && reg.toString().trim().length() > 0) {
					if (ret.length() > 0) {
						ret.append(separador);
					}
					ret.append(reg.toString().trim());
				}
			}

		}

		return ret.toString();
	}

	public static Boolean esVerdad(String string) {

		if (esVacio(string)) {
			return false;
		}

		string = string.trim().toLowerCase();

		return (string.equals("si") || string.equals("yes") || string.equals("true") || string.equals("1") || string.equals("y") || string.equals("s") || string.equals("t"));
	}

	/**
	 * valida que un String solo tenga ciertos caracteres (todos o alguno), no
	 * sea vacio
	 * 
	 * @param texto
	 *            Texto a validar
	 * @param caracteres
	 *            Caracteres validos por ej: SN valida que tena S o N o los dos
	 *            depende de lengthMax
	 * @param lengthMax
	 *            longitud m�xima del texto
	 * @return
	 */
	public static Boolean contieneSolo(String texto, String caracteres, Integer lengthMax) {
		if (StringUtils.esVacio(texto) || texto.length() > lengthMax || !org.apache.commons.lang.StringUtils.containsOnly(texto, caracteres)) {
			return false;
		}

		return true;
	}

	/**
	 * determina si un string es vacio (el espacio es un caracter vacio)
	 * 
	 * @param string
	 * @return true si todos son vacios
	 */
	public static Boolean esVacio(String... string) {

		if (string == null || string.length == 0) {
			return true;
		}

		Boolean ret = true;
		for (String str : string) {
			ret = ret && (str == null || str.trim().length() == 0);
		}

		return ret;
	}

	/**
	 * determina si un string es vacio (el espacio es un caracter vacio)
	 * 
	 * @param string
	 * @return true, si todos no son vacios
	 */
	public static Boolean esNoVacio(String... string) {

		if (string == null || string.length == 0) {
			return false;
		}

		Boolean ret = true;
		for (String str : string) {
			ret = ret && (str != null && str.trim().length() > 0);
		}

		return ret;
	}

	public static String espacios(int cantidad) {
		return repetir(cantidad, ' ');
	}

	public static String repetir(int cantidad, char str) {
		StringBuffer r = new StringBuffer("");
		for (int i = 0; i < cantidad; i++) {
			r.append(str);
		}
		return r.toString();
	}

	public static String derecha(Object txt, int cantidad) {

		return derecha(txt, cantidad, ' ');
	}

	public static String derecha(Object obj, int cantidad, char caracter) {

		String txt = (obj == null) ? "" : obj.toString();

		return txt.trim() + repetir(cantidad - txt.trim().length(), caracter);
	}

	public static String izquierda(Object obj, int cantidad) {

		return izquierda(obj, cantidad, ' ');
	}

	public static String izquierda(Object obj, int cantidad, char caracter) {

		String txt = (obj == null) ? "" : obj.toString();

		return repetir(cantidad - txt.trim().length(), caracter) + txt.trim();
	}

	// ---------------------------------------------------

	public static String traducir(String cadena, Map transformaciones) {
		String respuesta = cadena;
		Set conjTranformaciones = transformaciones.entrySet();
		Iterator it = conjTranformaciones.iterator();
		while (it.hasNext()) {
			Map.Entry clave = (Map.Entry) it.next();
			String val1 = (String) clave.getKey();
			String val2 = (String) clave.getValue();
			respuesta = respuesta.replaceAll(val1, val2);
		}
		return respuesta;
	}

	// ---------------------------------------------------

	public static String escapeSQL(String sql) {

		if (sql == null) {
			return "";
		}
		String retsql = sql.replace("'", "''");
		retsql = retsql.replace("%", "");

		return retsql;
	}

	// ---------------------------------------------------

	public static String escapeXML(String str) {

		if (str == null) {
			return "";
		}

		String retsql = str.trim();
		retsql = retsql.replace("&", "&amp;");
		retsql = retsql.replace("<", "&lt;");
		retsql = retsql.replace(">", "&gt;");
		retsql = retsql.replace("\"", "&quot;");
		retsql = retsql.replace("%", "");

		return retsql;
	}

	// ---------------------------------------------------

	public static String convertirAColExcel(Integer num) {

		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String p2 = abc.charAt((num - 1) % 26) + "";
		String p1 = num <= 26 ? "" : (abc.charAt((num - 1) / 26 - 1) + "");

		return p1 + p2;
	}

	// ---------------------------------------------------

	public static String toFileName(String name) {
		if (name == null || name.trim().length() == 0) {
			return "empty";
		}

		return name.replace(' ', '_').replace('/', '_').replace('\\', '_').replace('$', '_').replace('&', '_');
	}

	// ---------------------------------------------------

	public static String getStringCorchetes(String str) {
		if (str == null)
			return "";

		str = str.substring(str.indexOf("[") + 1, str.length());

		int cc = str.indexOf("]");
		if (cc >= 0) {
			str = str.substring(0, cc);
		}

		return str;

	}

	// ---------------------------------------------------

	private static String CRIPTOALPHA = "ABCDEFGHIJKLMNOPQRSTUBWXYZabcdefghijklmnopqrstubwxyz0123456789.:";

	private static String getString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];

			int a = (int) b + 128;

			int p1 = a / CRIPTOALPHA.length();
			int p2 = a % CRIPTOALPHA.length();

			sb.append(CRIPTOALPHA.charAt(p1));
			sb.append(CRIPTOALPHA.charAt(p2));
		}
		return sb.toString();
	}

	public static String MD5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes());
			return getString(bytes);
		} catch (Exception e) {
			SimpleLogger.error("Error al realizar MD5", e);
			return null;
		}
	}

	public static String SHA(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] bytes = md.digest(source.getBytes());
			return getString(bytes);
		} catch (Exception e) {
			SimpleLogger.error("Error al realizar SHA", e);
			return null;
		}
	}

	public static String toString(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	public static InputStream toInputStream(String str) {
		byte[] bytes = str.getBytes();
		return new ByteArrayInputStream(bytes);
	}

	public static String randomString(Integer size) {

		if (size == null) {
			size = 32;
		}

		String alfa = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";

		StringBuffer salida = new StringBuffer("");

		for (int i = 0; i < size; i++) {
			int p = (int) (Math.random() * alfa.length());
			salida.append(alfa.charAt(p));
		}

		return salida.toString();

	}

	@SuppressWarnings("deprecation")
	public static String getStringDate() {
		String dia;
		Integer anno, mes, ndia;

		Date date = new Date();

		anno = date.getYear() + 1900;
		mes = date.getMonth() + 1;
		ndia = date.getDate();

		switch (date.getDay()) {
		case 0:
			dia = "Domingo";
			break;
		case 1:
			dia = "Lunes";
			break;
		case 2:
			dia = "Martes";
			break;
		case 3:
			dia = "Miércoles";
			break;
		case 4:
			dia = "Jueves";
			break;
		case 5:
			dia = "Viernes";
			break;
		case 6:
			dia = "Sábado";
			break;
		default:
			dia = "??";
			break;
		}

		return (dia + ", " + (ndia < 10 ? "0" : "") + ndia + "/" + (mes < 10 ? "0" : "") + mes + "/" + anno);
	}

	@SuppressWarnings("deprecation")
	public static String getStringTime() {
		Integer hora, minutos;

		Date date = new Date();

		hora = date.getHours();
		minutos = date.getMinutes();

		return getStringDate() + " - " + (hora < 10 ? "0" : "") + hora + ":" + (minutos < 10 ? "0" : "") + minutos;
	}
	
	/**
	 * Recibe el patr�n (string) para el formato y retorna la cadena con la fecha/hora formateada
	 * @param pattern patron est�ndar para fecha/hora 
	 * @return String con fecha actual formateada
	 */
	public static String getStringTime(String pattern) {
		String resultado = "";
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		
		SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateTimeInstance(DateFormat.LONG, 
				DateFormat.LONG, new Locale("es", "ES"));
		
		try {
			formatter.applyPattern(pattern);
			resultado = formatter.format(date);
		} catch (Exception e) {
			formatter.applyPattern("EEEE, dd/MM/yyyy - HH:mm");
			resultado = formatter.format(date);
		}
		
		return resultado;
	}

	public static String trim(String str) {
		if (str == null) {
			return "";
		} else {
			return str.trim();
		}
	}

	public static String[] trim(String str[]) {

		if (str == null) {
			return null;
		}

		String ret[] = new String[str.length];
		for (int i = 0; i < str.length; i++) {
			ret[i] = trim(str[i]);
		}

		return ret;
	}

	public static List<String> trim(List<String> str) {

		ArrayList<String> ret = new ArrayList<String>();
		if (str != null) {
			for (String string : str) {
				ret.add(trim(string));
			}
		}

		return ret;
	}

	public static String simpleCripto(String data) {

		if (data == null) {
			return null;
		}

		String res = "";

		for (int i = 0; i < data.length(); i++) {
			res = res + izquierda("" + ((int) data.charAt(i)), 3, '0');
		}

		return res;
	}

	public static Date toDate(String strdate) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(strdate);
		} catch (Exception e) {
		}

		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(strdate);
		} catch (Exception e) {
		}

		return null;
	}

	public static String toString(Object valor) {

		if (valor == null) {
			return null;
		}

		if (valor instanceof Date) {

			try {
				return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((Date) valor).replace(" 00:00:00", "").trim();
			} catch (Exception e) {
			}
		}

		return valor.toString();
	}

	public static String toStringFormat(Object valor) {

		if (valor == null) {
			return null;
		}

		if (valor instanceof Date) {

			try {
				return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((Date) valor).replace(" 00:00:00", "").trim();
			} catch (Exception e) {
			}
		}

		if (valor instanceof BigDecimal) {
			NumberFormat formatter = new DecimalFormat("##,##0.00################");
			return formatter.format(valor);
		}

		return valor.toString();
	}

	public static Integer calcularDigitoVerificacion(String nit1) {

		Integer dv1;

		Integer vpri[] = new Integer[16];
		int x = 0;
		int y = 0;
		int z = nit1.length();
		vpri[1] = 3;
		vpri[2] = 7;
		vpri[3] = 13;
		vpri[4] = 17;
		vpri[5] = 19;
		vpri[6] = 23;
		vpri[7] = 29;
		vpri[8] = 37;
		vpri[9] = 41;
		vpri[10] = 43;
		vpri[11] = 47;
		vpri[12] = 53;
		vpri[13] = 59;
		vpri[14] = 67;
		vpri[15] = 71;

		for (int i = 0; i < z; i++) {
			y = Integer.parseInt(nit1.substring(i, i + 1));
			x += (y * vpri[z - i]);
		}
		y = x % 11;

		if (y > 1) {
			dv1 = 11 - y;
		} else {
			dv1 = y;
		}

		return dv1;

	}

	public static Map<String, Object> MapToUpperCase(Map<String, Object> mapa) {
		if (mapa == null) {
			return null;
		}

		Map<String, Object> map_ret = new HashMap<String, Object>();

		Set<String> keys = mapa.keySet();

		if (keys == null) {
			return mapa;
		}

		for (String key : keys) {
			map_ret.put(key.toUpperCase(), mapa.get(key));
		}

		return map_ret;
	}

	public static List<Map<String, Object>> ListMapToUpperCase(List<Map<String, Object>> listmapa) {
		if (listmapa == null) {
			return null;
		}

		List<Map<String, Object>> listmapa_ret = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> map : listmapa) {
			listmapa_ret.add(MapToUpperCase(map));
		}

		return listmapa_ret;
	}

	// ---------------------------------------------------

	public static Boolean validaExpRegular(String string, String patron) {

		if (esVacio(string) || esVacio(patron)) {
			return false;
		}

		boolean result = string.matches(patron);
		return result;
	}

	/** [\\d]+ = Número, equivale a [0-9] */
	public static Boolean validaNumero(String string) {
		boolean result = validaExpRegular(string, "^[\\d]+$");
		return result;
	}

	/** [\\w]+ = Palabra, equivale a [a-zA-Z_0-9-]. No incluye espacios! */
	public static Boolean validaPalabra(String string) {
		boolean result = validaExpRegular(string, "^[\\w\\-]+$");
		return result;
	}

	/** [\\w]+ = Palabra, equivale a [a-zA-Z_0-9-]. No incluye espacios! */
	public static Boolean validaPalabraEspeciales(String string) {
		boolean result = validaExpRegular(string, "^[\\w\\-\\.\\' ]+$");
		return result;
	}

	/**
	 * Valida el correo-e con el patrón
	 * ^[\\w\\-]([\\-\\_\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4}$.
	 */
	public static Boolean validaCorreoE(String string) {
		boolean result = validaExpRegular(string, "^[\\w\\-]([\\-\\_\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4}$");
		return result;
	}

	/** Validación OR de la cadena para todos los patrones enviados en la lista */
	public static Boolean validaListadoPatrones(String string, List<String> lista) {
		int longitud = lista.size();
		int tmp = 0;
		String patronConcat = "";

		if (longitud > 0) {
			tmp = longitud - 1;
		} else {
			return false;
		}

		for (int i = 0; i < longitud; i++) {
			patronConcat = patronConcat + lista.get(i);
			if (i < tmp) {
				patronConcat = patronConcat + "|";
			}
		}

		boolean result = validaExpRegular(string, patronConcat);
		return result;
	}

	/***
	 * Funcion que reemplaza caracteres especiales tales como
	 * 
	 * 
	 */
	public static String quitarCaracteresEspeciales(String texto, String diccionario, Map<Character, Character> mapaTraductor) {

		if (texto == null) {
			return null;
		}

		if (mapaTraductor != null) {
			Set<Character> mapakey = mapaTraductor.keySet();

			// Se remplazan todos los caracteres especificados en el mapa
			for (Character key : mapakey) {
				texto = texto.replace(key, mapaTraductor.get(key));
			}
		}

		// Luego de reemplazar se valida con el diccionario
		StringBuffer textoReemplazado = new StringBuffer();
		for (int i = 0; i < texto.length(); i++) {
			if (diccionario.contains(texto.charAt(i) + "")) {
				textoReemplazado.append(texto.charAt(i));
			}
		}

		return textoReemplazado.toString();

	}

	/***
	 * Reemplaza caracteres especiales para nombres. Para ello utila un
	 * diccionario básico Diccionario básico:
	 * abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890' ' 
	 * 
	 * @param
	 * @return
	 */

	public static String reemplazarCaracteresEspecialesParaNombre(String nombre) {

		String diccionario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ";

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

		return quitarCaracteresEspeciales(nombre, diccionario, mapaTraductor);

	}

}