package com.developer.logic.modulo.conversion.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.utils.StringOsmoUtils;

public class DetalleArchivoRecaudoOriginalPorConvertir {
	
	Long    daror_aror 		;
	Long    daror_id_reg 	;
	String  daror_freca 	;
	String  daror_referencia;
	String  daror_aportante ;
	String  daror_ofic 		;
	String  daror_vefe 		;
	String  daror_vche 		;
	String  daror_vtot 		;
	String  daror_cons_bsc_1;
	String  daror_tipo_reca ;
	String  daror_comp 		;
	String  daror_cons_bsc_2;
	String  daror_registro 	;
	Date    daror_fcrea 	;
	String  daror_cta_reca 	;
	
	Double  daror_vtot_double;
	
	public Long getDaror_aror() {
		return daror_aror;
	}
	public void setDaror_aror(Long daror_aror) {
		this.daror_aror = daror_aror;
	}
	public Long getDaror_id_reg() {
		return daror_id_reg;
	}
	public void setDaror_id_reg(Long daror_id_reg) {
		this.daror_id_reg = daror_id_reg;
	}
	public String getDaror_freca() {
		return daror_freca;
	}
	public void setDaror_freca(String daror_freca) {
		this.daror_freca = daror_freca;
	}
	public String getDaror_referencia() {
		return daror_referencia;
	}
	public void setDaror_referencia(String daror_referencia) {
		this.daror_referencia = daror_referencia;
	}
	public String getDaror_aportante() {
		return daror_aportante;
	}
	public void setDaror_aportante(String daror_aportante) {
		this.daror_aportante = daror_aportante;
	}
	public String getDaror_ofic() {
		return daror_ofic;
	}
	public void setDaror_ofic(String daror_ofic) {
		this.daror_ofic = daror_ofic;
	}
	public String getDaror_vefe() {
		return daror_vefe;
	}
	public void setDaror_vefe(String daror_vefe) {
		this.daror_vefe = daror_vefe;
	}
	public String getDaror_vche() {
		return daror_vche;
	}
	public void setDaror_vche(String daror_vche) {
		this.daror_vche = daror_vche;
	}
	public String getDaror_vtot() {
		return daror_vtot;
	}
	public void setDaror_vtot(String daror_vtot) {
		this.daror_vtot = daror_vtot;
		
		try {
			
			BigDecimal bigDecimal = getValorMoneda(daror_vtot);
			daror_vtot_double = getRound2Decimals(bigDecimal.doubleValue());
		} catch (Exception e) {
			daror_vtot_double = new Double(0);
		}
	}
	public Double getDaror_vtot_double() {
		return daror_vtot_double;
	}
	public void setDaror_vtot_double(Double daror_vtot_double) {
		this.daror_vtot_double = daror_vtot_double;
	}
	public String getDaror_cons_bsc_1() {
		return daror_cons_bsc_1;
	}
	public void setDaror_cons_bsc_1(String daror_cons_bsc_1) {
		this.daror_cons_bsc_1 = daror_cons_bsc_1;
	}
	public String getDaror_tipo_reca() {
		return daror_tipo_reca;
	}
	public void setDaror_tipo_reca(String daror_tipo_reca) {
		this.daror_tipo_reca = daror_tipo_reca;
	}
	public String getDaror_comp() {
		return daror_comp;
	}
	public void setDaror_comp(String daror_comp) {
		this.daror_comp = daror_comp;
	}
	public String getDaror_cons_bsc_2() {
		return daror_cons_bsc_2;
	}
	public void setDaror_cons_bsc_2(String daror_cons_bsc_2) {
		this.daror_cons_bsc_2 = daror_cons_bsc_2;
	}
	public String getDaror_registro() {
		return daror_registro;
	}
	public void setDaror_registro(String daror_registro) {
		this.daror_registro = daror_registro;
	}
	public Date getDaror_fcrea() {
		return daror_fcrea;
	}
	public void setDaror_fcrea(Date daror_fcrea) {
		this.daror_fcrea = daror_fcrea;
	}
	public String getDaror_cta_reca() {
		return daror_cta_reca;
	}
	public void setDaror_cta_reca(String daror_cta_reca) {
		this.daror_cta_reca = daror_cta_reca;
	}
	
	private BigDecimal getBigDecimal(String valor) {

		try {

			if (StringOsmoUtils.esVacio(valor)) {
				return null;
			}

			BigDecimal bigDecimal = new BigDecimal(valor.trim());
			return bigDecimal;

		} catch (Exception e) {
			SimpleLogger.error("Error", e);
			return null;
		}
	}

	private BigDecimal getValorMoneda(String valor) {

		BigDecimal resultado = getBigDecimal(valor);
		return resultado;
		
	}
	
	private double getRound2Decimals(double valor) {

		DecimalFormat twoDForm = new DecimalFormat("#.00");
	    return Double.parseDouble(twoDForm.format(valor).replace(",", "."));

		

	}
	

}
