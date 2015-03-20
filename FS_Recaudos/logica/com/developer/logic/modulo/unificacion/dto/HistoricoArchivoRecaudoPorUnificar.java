package com.developer.logic.modulo.unificacion.dto;

import java.util.Date;

import com.developer.logic.modulo.general.dto.Persona;

public class HistoricoArchivoRecaudoPorUnificar {
	
	private Long harpu_arpu;
	private String harpu_earpu;
	private Date harpu_fasig;
	private String harpu_usua;
	private String harpu_obser;
	
	//Informacion complementario
	Persona persona;

	public Long getHarpu_arpu() {
		return harpu_arpu;
	}

	public void setHarpu_arpu(Long harpu_arpu) {
		this.harpu_arpu = harpu_arpu;
	}

	public String getHarpu_earpu() {
		return harpu_earpu;
	}

	public void setHarpu_earpu(String harpu_earpu) {
		this.harpu_earpu = harpu_earpu;
	}

	public Date getHarpu_fasig() {
		return harpu_fasig;
	}

	public void setHarpu_fasig(Date harpu_fasig) {
		this.harpu_fasig = harpu_fasig;
	}

	public String getHarpu_usua() {
		return harpu_usua;
	}

	public void setHarpu_usua(String harpu_usua) {
		this.harpu_usua = harpu_usua;
	}

	public String getHarpu_obser() {
		return harpu_obser;
	}

	public void setHarpu_obser(String harpu_obser) {
		this.harpu_obser = harpu_obser;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
		

	
}
