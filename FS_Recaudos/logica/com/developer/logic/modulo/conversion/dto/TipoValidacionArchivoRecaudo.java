package com.developer.logic.modulo.conversion.dto;

public class TipoValidacionArchivoRecaudo {
	
	
	public static final Long TPVL_TAM_REF_MENOR 	= 	new Long(1);
	public static final Long TPVL_ESTADO_PLAN_CAN 	=	new Long(2);
	public static final Long TPVL_ESTADO_PLAN_PCA 	= 	new Long(3);
	public static final Long TPVL_TIPO_RECA_RCHE 	=	new Long(4);
	public static final Long TPVL_APOR_NO_TITULAR 	= 	new Long(5);
	public static final Long TPVL_APOR_VACIO 		= 	new Long(6);
	public static final Long TPVL_RNDB_EXCLUIDO 	= 	new Long(7);
	public static final Long TPVL_TAM_REF_MAYOR		= 	new Long(8);
	public static final Long TPVL_REF_VOLANTE 		= 	new Long(9);
	public static final Long TPVL_PROY_CAN 			= 	new Long(10);
	public static final Long TPVL_TPAR_MANEJA_VOLANTE_SI = new Long(11);
	public static final Long TPVL_TPAR_MANEJA_VOLANTE_NO = new Long(12);

		
	
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
