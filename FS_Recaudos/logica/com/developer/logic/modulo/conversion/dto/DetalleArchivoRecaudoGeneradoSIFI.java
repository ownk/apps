package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class DetalleArchivoRecaudoGeneradoSIFI {
	
	
	public static String ERDS_NO_ENCONTRADO = "NO_ENCONTRADO";
	public static String ERDS_VACIO = "VACIO";
	public static String ERDS_VOLANTE = "VOLANTE";
	
	
	Long    darge_arge 		  ;
	Long    darge_daror_id_reg;
	Long    darge_id_reg      ;
	String  darge_freca 	  ;
	String  darge_referencia  ;
	String  darge_aportante   ;
	String  darge_erds	  ;
	String  darge_ofic 		  ;
	String  darge_vefe 		  ;
	String  darge_vche 		  ;
	String  darge_vtot 		  ;
	String  darge_cons_bsc_1  ;
	String  darge_tipo_reca   ;
	String  darge_comp 		  ;
	String  darge_cons_bsc_2  ;
	Date    darge_fcrea 	  ;
	String darge_titular_sn;
	String darge_frdp_sn;
	String darge_prca_sn;
	String darge_pnsa_sn;
	String darge_trrf_sn;
	
	
	
	
	public String getDarge_trar_rf_sn() {
		return darge_trrf_sn;
	}
	public void setDarge_trar_rf_sn(String darge_trrf_sn) {
		this.darge_trrf_sn = darge_trrf_sn;
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
	public String getDarge_erds() {
		return darge_erds;
	}
	public void setDarge_erds(String darge_erds) {
		this.darge_erds = darge_erds;
	}
	public Long getDarge_arge() {
		return darge_arge;
	}
	public void setDarge_arge(Long darge_arge) {
		this.darge_arge = darge_arge;
	}
	public Long getDarge_daror_id_reg() {
		return darge_daror_id_reg;
	}
	public void setDarge_daror_id_reg(Long darge_daror_id_reg) {
		this.darge_daror_id_reg = darge_daror_id_reg;
	}
	public Long getDarge_id_reg() {
		return darge_id_reg;
	}
	public void setDarge_id_reg(Long darge_id_reg) {
		this.darge_id_reg = darge_id_reg;
	}
	public String getDarge_freca() {
		return darge_freca;
	}
	public void setDarge_freca(String darge_freca) {
		this.darge_freca = darge_freca;
	}
	public String getDarge_referencia() {
		return darge_referencia;
	}
	public void setDarge_referencia(String darge_referencia) {
		this.darge_referencia = darge_referencia;
	}
	public String getDarge_aportante() {
		return darge_aportante;
	}
	public void setDarge_aportante(String darge_aportante) {
		this.darge_aportante = darge_aportante;
	}
	public String getDarge_ofic() {
		return darge_ofic;
	}
	public void setDarge_ofic(String darge_ofic) {
		this.darge_ofic = darge_ofic;
	}
	public String getDarge_vefe() {
		return darge_vefe;
	}
	public void setDarge_vefe(String darge_vefe) {
		this.darge_vefe = darge_vefe;
	}
	public String getDarge_vche() {
		return darge_vche;
	}
	public void setDarge_vche(String darge_vche) {
		this.darge_vche = darge_vche;
	}
	public String getDarge_vtot() {
		return darge_vtot;
	}
	public void setDarge_vtot(String darge_vtot) {
		this.darge_vtot = darge_vtot;
	}
	public String getDarge_cons_bsc_1() {
		return darge_cons_bsc_1;
	}
	public void setDarge_cons_bsc_1(String darge_cons_bsc_1) {
		this.darge_cons_bsc_1 = darge_cons_bsc_1;
	}
	public String getDarge_tipo_reca() {
		return darge_tipo_reca;
	}
	public void setDarge_tipo_reca(String darge_tipo_reca) {
		this.darge_tipo_reca = darge_tipo_reca;
	}
	public String getDarge_comp() {
		return darge_comp;
	}
	public void setDarge_comp(String darge_comp) {
		this.darge_comp = darge_comp;
	}
	public String getDarge_cons_bsc_2() {
		return darge_cons_bsc_2;
	}
	public void setDarge_cons_bsc_2(String darge_cons_bsc_2) {
		this.darge_cons_bsc_2 = darge_cons_bsc_2;
	}
	public Date getDarge_fcrea() {
		return darge_fcrea;
	}
	public void setDarge_fcrea(Date darge_fcrea) {
		this.darge_fcrea = darge_fcrea;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "darge_arge:"+darge_arge 		
				+"darge_daror_id_reg:"+darge_daror_id_reg
				+"darge_id_reg:"+darge_id_reg      
				+"darge_freca:"+darge_freca 	  
				+"darge_referencia:"+darge_referencia  
				+"darge_aportante:"+darge_aportante   
				+"darge_ofic:"+darge_ofic 		
				+"darge_vefe:"+darge_vefe 		
				+"darge_vche:"+darge_vche 		
				+"darge_vtot:"+darge_vtot 		
				+"darge_cons_bsc_1:"+darge_cons_bsc_1  
				+"darge_tipo_reca:"+darge_tipo_reca   
				+"darge_comp:"+darge_comp 		
				+"darge_cons_bsc_2:"+darge_cons_bsc_2  
				+"darge_fcrea_"+darge_fcrea;
	}
	

}
