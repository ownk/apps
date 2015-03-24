package com.developer.logic.modulo.unificacion.dto;

import java.util.Date;


public class ProcesoUnificacionArchivos {
	
	//Estados
	
	public static String INICIADO = "INICIADO";
	public static String UNIFICANDO_ARCHIVOS = "UNIFICANDO_ARCHIVOS";
	public static String FINALIZADO = "FINALIZADO";


	
	private	Long    prun_prun 	 ;
	private	String  prun_usua 	 ;
	private	String	prun_eprun   ;
	private	Date	prun_fcrea   ;
	private	String	prun_observ  ;
	
	
	public Long getPrun_prun() {
		return prun_prun;
	}
	public void setPrun_prun(Long prun_prun) {
		this.prun_prun = prun_prun;
	}
	public String getPrun_usua() {
		return prun_usua;
	}
	public void setPrun_usua(String prun_usua) {
		this.prun_usua = prun_usua;
	}
	public String getPrun_eprun() {
		return prun_eprun;
	}
	public void setPrun_eprun(String prun_eprun) {
		this.prun_eprun = prun_eprun;
	}
	public Date getPrun_fcrea() {
		return prun_fcrea;
	}
	public void setPrun_fcrea(Date prun_fcrea) {
		this.prun_fcrea = prun_fcrea;
	}
	public String getPrun_observ() {
		return prun_observ;
	}
	public void setPrun_observ(String prun_observ) {
		this.prun_observ = prun_observ;
	}





}
