package com.developer.logic.modulo.conversion.dto;

public class TipoValidacionArchivoRecaudo {
	
	
	public static final Long TPVL_TAM_REF_MENOR 	= 	new Long(1);
	public static final Long TPVL_PLAN_CAN 			=	new Long(2);
	public static final Long TPVL_PLAN_PCA 			= 	new Long(3);
	public static final Long TPVL_TIPO_RECA_RCHE 	=	new Long(4);
	public static final Long TPVL_APOR_NO_TITULAR 	= 	new Long(5);
	public static final Long TPVL_APOR_VACIO 		= 	new Long(6);
	public static final Long TPVL_RNDB_EXCLUIDO 	= 	new Long(7);
	public static final Long TPVL_TAM_REF_MAYOR		= 	new Long(8);
	public static final Long TPVL_REF_VOLANTE 		= 	new Long(9);
	public static final Long TPVL_PROY_CAN 			= 	new Long(10);
	public static final Long TPVL_TPAR_MANEJA_VOLANTE_SI = new Long(11);
	public static final Long TPVL_TPAR_MANEJA_VOLANTE_NO = new Long(12);
	public static final Long TPVL_PLAN_NOSIFI_ACT 	= 	new Long(13);
	public static final Long TPVL_PROY_RECA_NOSIFI_ACT = 	new Long(14);
	public static final Long TPVL_PLAN_NOSIFI_INACT = new Long(15);
	public static final Long TPER_PLAN_FRDP_ACT 	= new Long(16);
	public static final Long TPER_PLAN_DPFD_ACT 	= new Long(17);
	public static final Long TPER_EPSG_ACT 			= new Long(18);
		
	
	Long    tpvl_tpvl 	; 
	String  tpvl_descri ;
	String  tpvl_color 	;
	
	
	public Long getTpvl_tpvl() {
		return tpvl_tpvl;
	}
	public void setTpvl_tpvl(Long tpvl_tpvl) {
		this.tpvl_tpvl = tpvl_tpvl;
	}
	public String getTpvl_descri() {
		return tpvl_descri;
	}
	public void setTpvl_descri(String tpvl_descri) {
		this.tpvl_descri = tpvl_descri;
	}
	public String getTpvl_color() {
		return tpvl_color;
	}
	public void setTpvl_color(String tpvl_color) {
		this.tpvl_color = tpvl_color;
	}   
	
	

}
