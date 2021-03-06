package com.developer.logic.modulo.conversion.dto;

public class TipoTransformacionArchivoRecaudo {
	
	public static final Long TPTR_PLAN_GENERICO_TAM_REF_MAX = new Long(1);
	public static final Long TPTR_PLAN_GENERICO_TPAR_VOL_NO = new Long(2);
	public static final Long TPTR_PLAN_GENERICO_NOSIFI_INACT = new Long(3);
	public static final Long TPTR_PLAN_COMP_RF_FOND = new Long(4);
	public static final Long TPTR_PLAN_DPFD_ACT = new Long(5);
	public static final Long TPTR_VCHE_DPFD_ACT = new Long(6);
	public static final Long TPTR_VEFE_DPFD_ACT = new Long(7);
	public static final Long TPTR_VTOT_DPFD_ACT = new Long(8);
	public static final Long TPTR_PLAN_GENERICO_EPSG = new Long(9);
	public static final Long TPTR_PLAN_PROY_NOSIFI_ACT = new Long(10);
	public static final Long TPTR_PLAN_GENERICO_PLAN_CA = new Long(11);
	public static final Long TPTR_PLAN_GENERICO_REF_NE = new Long(12);
	public static final Long TPTR_PLAN_GENERICO_PLAN_PCA_RCHE = new Long(13);
	public static final Long TPTR_PLAN_PROY_CAN = new Long(14);
	public static final Long TPTR_OFIC_SIFI_EXISTE = new Long(15);
	
		
	
	Long    tptr_tptr 	; 
	String  tptr_descri ;
	String  tptr_color 	;
	
	
	public Long getTptr_tptr() {
		return tptr_tptr;
	}
	public void setTptr_tptr(Long tptr_tptr) {
		this.tptr_tptr = tptr_tptr;
	}
	public String getTptr_descri() {
		return tptr_descri;
	}
	public void setTptr_descri(String tptr_descri) {
		this.tptr_descri = tptr_descri;
	}
	public String getTptr_color() {
		return tptr_color;
	}
	public void setTptr_color(String tptr_color) {
		this.tptr_color = tptr_color;
	}   
	
	

}
