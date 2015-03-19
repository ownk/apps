package com.developer.logic.modulo.unificacion.dto;

import java.util.Date;

import com.developer.logic.modulo.general.dto.Persona;

public class HistoricoProcesoUnificacionArchivos {
	
	private Long hprun_prun;
	private String hprun_eprun;
	private Date hprun_fasig;
	private String hprun_usua;
	private String hprun_obser;
	
	//Informacion complementario
	Persona persona;

	public Long getHprun_prun() {
		return hprun_prun;
	}

	public void setHprun_prun(Long hprun_prun) {
		this.hprun_prun = hprun_prun;
	}

	public String getHprun_eprun() {
		return hprun_eprun;
	}

	public void setHprun_eprun(String hprun_eprun) {
		this.hprun_eprun = hprun_eprun;
	}

	public Date getHprun_fasig() {
		return hprun_fasig;
	}

	public void setHprun_fasig(Date hprun_fasig) {
		this.hprun_fasig = hprun_fasig;
	}

	public String getHprun_usua() {
		return hprun_usua;
	}

	public void setHprun_usua(String hprun_usua) {
		this.hprun_usua = hprun_usua;
	}

	public String getHprun_obser() {
		return hprun_obser;
	}

	public void setHprun_obser(String hprun_obser) {
		this.hprun_obser = hprun_obser;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
		

	
}
