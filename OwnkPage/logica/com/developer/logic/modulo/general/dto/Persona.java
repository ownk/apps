package com.developer.logic.modulo.general.dto;

import java.util.List;

public class Persona {
	
	Long pern_pern;
	String 	pern_nomb;
	String 	pern_papell;
	String 	pern_sapell;
	Long pern_tpdoc;
	String 	pern_doc;
		
	
	List<Long> estudiantes;
	
	public Long getPern_pern() {
		return pern_pern;
	}
	public void setPern_pern(Long pern_pern) {
		this.pern_pern = pern_pern;
	}
	public String getPern_nomb() {
		return pern_nomb;
	}
	public void setPern_nomb(String pern_nomb) {
		this.pern_nomb = pern_nomb;
	}
	public String getPern_papell() {
		return pern_papell;
	}
	public void setPern_papell(String pern_papell) {
		this.pern_papell = pern_papell;
	}
	public String getPern_sapell() {
		return pern_sapell;
	}
	public void setPern_sapell(String pern_sapell) {
		this.pern_sapell = pern_sapell;
	}
	public Long getPern_tpdoc() {
		return pern_tpdoc;
	}
	public void setPern_tpdoc(Long pern_tpdoc) {
		this.pern_tpdoc = pern_tpdoc;
	}
	public String getPern_doc() {
		return pern_doc;
	}
	public void setPern_doc(String pern_doc) {
		this.pern_doc = pern_doc;
	}
	
	public void setEstudiantes(List<Long> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}
}
