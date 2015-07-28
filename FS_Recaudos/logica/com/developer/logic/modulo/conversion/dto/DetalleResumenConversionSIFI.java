package com.developer.logic.modulo.conversion.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.utils.StringOsmoUtils;


public class DetalleResumenConversionSIFI {
	
	String daror_aror; 
	String daror_id_reg; 
	String darge_id_reg; 
	String daror_freca; 
	String daror_referencia; 
	String darge_referencia; 
	String darge_erds; 
	String erds_color; 
	String daror_aportante; 
	String daror_ofic; 
	String darge_ofic; 
	String daror_tipo_reca; 
	String daror_vefe; 
	String darge_vefe; 
	String daror_vche; 
	String darge_vche; 
	String daror_vtot; 
	String darge_vtot; 

	String daror_cons_bsc_1; 
	String daror_comp; 
	String daror_cons_bsc_2;
	
	
	
	String darge_titular_sn;
	String darge_frdp_sn;
	String darge_prca_sn;
	String darge_pnsa_sn;
	String darge_trrf_sn;
	
	String darge_vtot_format = DecimalFormat.getCurrencyInstance().format(0);
	String darge_vefe_format = DecimalFormat.getCurrencyInstance().format(0);
	String darge_vche_format = DecimalFormat.getCurrencyInstance().format(0);
	
	
	Double darge_vtot_double;
	Double darge_vefe_double;
	Double darge_vche_double;
	
	public Double getDarge_vtot_double() {
		
		if(darge_vtot_double==null){
			darge_vtot_double = new Double(0);
		}
		
		return darge_vtot_double;
	}
	
	
	public void setDarge_vtot_double(Double darge_vtot_double) {
		this.darge_vtot_double = darge_vtot_double;
	}

	
	
	public Double getDarge_vefe_double() {
		
		if(darge_vefe_double==null){
			darge_vefe_double = new Double(0);
		}
		
		return darge_vefe_double;
	}


	public void setDarge_vefe_double(Double darge_vefe_double) {
		this.darge_vefe_double = darge_vefe_double;
	}


	public Double getDarge_vche_double() {
		
		if(darge_vche_double==null){
			darge_vche_double = new Double(0);
		}
		return darge_vche_double;
	}


	public void setDarge_vche_double(Double darge_vche_double) {
		this.darge_vche_double = darge_vche_double;
	}


	public String getDarge_trrf_sn() {
		return darge_trrf_sn;
	}
	public void setDarge_trrf_sn(String darge_trrf_sn) {
		this.darge_trrf_sn = darge_trrf_sn;
	}

	public String getDaror_aror() {
		return daror_aror;
	}
	public void setDaror_aror(String daror_aror) {
		this.daror_aror = daror_aror;
	}
	public String getDaror_id_reg() {
		return daror_id_reg;
	}
	public void setDaror_id_reg(String daror_id_reg) {
		this.daror_id_reg = daror_id_reg;
	}
	public String getDarge_id_reg() {
		return darge_id_reg;
	}
	public void setDarge_id_reg(String darge_id_reg) {
		this.darge_id_reg = darge_id_reg;
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
	public String getDarge_referencia() {
		return darge_referencia;
	}
	public void setDarge_referencia(String darge_referencia) {
		this.darge_referencia = darge_referencia;
	}
	public String getDarge_erds() {
		return darge_erds;
	}
	public void setDarge_erds(String darge_erds) {
		this.darge_erds = darge_erds;
	}
	public String getErds_color() {
		return erds_color;
	}
	public void setErds_color(String erds_color) {
		this.erds_color = erds_color;
	}
	public String getDaror_aportante() {
		return daror_aportante;
	}
	public void setDaror_aportante(String daror_aportante) {
		if(daror_aportante!=null){
			this.daror_aportante = daror_aportante.trim();
		}
		
		
	}
	public String getDaror_ofic() {
		return daror_ofic;
	}
	public void setDaror_ofic(String daror_ofic) {
		this.daror_ofic = daror_ofic;
	}
	public String getDarge_ofic() {
		return darge_ofic;
	}
	public void setDarge_ofic(String darge_ofic) {
		this.darge_ofic = darge_ofic;
	}
	public String getDaror_tipo_reca() {
		return daror_tipo_reca;
	}
	public void setDaror_tipo_reca(String daror_tipo_reca) {
		this.daror_tipo_reca = daror_tipo_reca;
	}
	public String getDaror_vefe() {
		return daror_vefe;
	}
	public void setDaror_vefe(String daror_vefe) {
		this.daror_vefe = daror_vefe;
	}
	public String getDarge_vefe() {
		return darge_vefe;
	}
	public void setDarge_vefe(String darge_vefe) {
		this.darge_vefe = darge_vefe;
		
		
		try {
			
			BigDecimal bigDecimal = getValorMoneda(darge_vefe);
			darge_vefe_format = DecimalFormat.getCurrencyInstance().format(getRound2Decimals(bigDecimal.doubleValue()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			
			BigDecimal bigDecimal = getValorMoneda(darge_vefe);
			darge_vefe_double = getRound2Decimals(bigDecimal.doubleValue());
		} catch (Exception e) {
			darge_vefe_double = new Double(0);
		}
		
	}
	public String getDaror_vche() {
		return daror_vche;
	}
	public void setDaror_vche(String daror_vche) {
		this.daror_vche = daror_vche;
	}
	public String getDarge_vche() {
		return darge_vche;
	}
	public void setDarge_vche(String darge_vche) {
		this.darge_vche = darge_vche;
		
		
		try {
			
			BigDecimal bigDecimal = getValorMoneda(darge_vche);
			darge_vche_format = DecimalFormat.getCurrencyInstance().format(getRound2Decimals(bigDecimal.doubleValue()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			
			BigDecimal bigDecimal = getValorMoneda(darge_vche);
			darge_vche_double = getRound2Decimals(bigDecimal.doubleValue());
		} catch (Exception e) {
			darge_vche_double = new Double(0);
		}
		
		
	}
	public String getDaror_vtot() {
		return daror_vtot;
	}
	public void setDaror_vtot(String daror_vtot) {
		this.daror_vtot = daror_vtot;
	}
	public String getDarge_vtot() {
		return darge_vtot;
	}
	public void setDarge_vtot(String darge_vtot) {
		
		this.darge_vtot = darge_vtot;
		
		try {
			
			BigDecimal bigDecimal = getValorMoneda(darge_vtot);
			darge_vtot_format = DecimalFormat.getCurrencyInstance().format(getRound2Decimals(bigDecimal.doubleValue()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			
			BigDecimal bigDecimal = getValorMoneda(darge_vtot);
			darge_vtot_double = getRound2Decimals(bigDecimal.doubleValue());
		} catch (Exception e) {
			darge_vtot_double = new Double(0);
		}
	}
	public String getDarge_vtot_format() {
		
		if(darge_vtot_format==null){
			
			darge_vtot_format = DecimalFormat.getCurrencyInstance().format(0);
		}
		return darge_vtot_format;
	}
	public void setDarge_vtot_format(String darge_vtot_format) {
		this.darge_vtot_format = darge_vtot_format;
	}
	
	public String getDarge_vefe_format() {
		
		if(darge_vefe_format==null){
			
			darge_vefe_format = DecimalFormat.getCurrencyInstance().format(0);
		}
		return darge_vefe_format;
	}
	public void setDarge_vefe_format(String darge_vefe_format) {
		this.darge_vefe_format = darge_vefe_format;
	}
	
	public String getDarge_vche_format() {
		
		if(darge_vche_format==null){
			
			darge_vche_format = DecimalFormat.getCurrencyInstance().format(0);
		}
		return darge_vche_format;
	}
	public void setDarge_vche_format(String darge_vche_format) {
		this.darge_vche_format = darge_vche_format;
	}

	public String getDarge_titular_sn() {
		return darge_titular_sn;
	}
	public void setDarge_titular_sn(String darge_titular_sn) {
		this.darge_titular_sn = darge_titular_sn;
	}
	public String getDarge_frdp_sn() {
		return darge_frdp_sn;
	}
	public void setDarge_frdp_sn(String darge_frdp_sn) {
		this.darge_frdp_sn = darge_frdp_sn;
	}
	public String getDarge_prca_sn() {
		return darge_prca_sn;
	}
	public void setDarge_prca_sn(String darge_prca_sn) {
		this.darge_prca_sn = darge_prca_sn;
	}
	public String getDarge_pnsa_sn() {
		return darge_pnsa_sn;
	}
	public void setDarge_pnsa_sn(String darge_pnsa_sn) {
		this.darge_pnsa_sn = darge_pnsa_sn;
	}
	
	
	
	
	
	public String getDaror_cons_bsc_1() {
		return daror_cons_bsc_1;
	}
	public void setDaror_cons_bsc_1(String daror_cons_bsc_1) {
		this.daror_cons_bsc_1 = daror_cons_bsc_1;
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
