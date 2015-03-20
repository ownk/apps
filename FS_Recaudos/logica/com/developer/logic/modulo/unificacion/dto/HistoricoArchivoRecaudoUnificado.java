package com.developer.logic.modulo.unificacion.dto;

import java.util.Date;

import com.developer.logic.modulo.general.dto.Persona;

public class HistoricoArchivoRecaudoUnificado {
	
	private Long harun_arun;
	private String harun_earun;
	private Date harun_fasig;
	private String harun_usua;
	private String harun_obser;
	
	//Informacion complementario
	Persona persona;

	public Long getHarun_arun() {
		return harun_arun;
	}

	public void setHarun_arun(Long harun_arun) {
		this.harun_arun = harun_arun;
	}

	public String getHarun_earun() {
		return harun_earun;
	}

	public void setHarun_earun(String harun_earun) {
		this.harun_earun = harun_earun;
	}

	public Date getHarun_fasig() {
		return harun_fasig;
	}

	public void setHarun_fasig(Date harun_fasig) {
		this.harun_fasig = harun_fasig;
	}

	public String getHarun_usua() {
		return harun_usua;
	}

	public void setHarun_usua(String harun_usua) {
		this.harun_usua = harun_usua;
	}

	public String getHarun_obser() {
		return harun_obser;
	}

	public void setHarun_obser(String harun_obser) {
		this.harun_obser = harun_obser;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
		

	
}
