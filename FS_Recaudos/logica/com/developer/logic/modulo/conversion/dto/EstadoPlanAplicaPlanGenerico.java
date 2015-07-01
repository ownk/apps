package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class EstadoPlanAplicaPlanGenerico {

	String epsg_tpar;
	String epsg_esta;
	Date epsg_fcrea;

	public String getEpsg_tpar() {
		return epsg_tpar;
	}

	public void setEpsg_tpar(String epsg_tpar) {
		this.epsg_tpar = epsg_tpar;
	}

	public String getEpsg_esta() {
		return epsg_esta;
	}

	public void setEpsg_esta(String epsg_esta) {
		this.epsg_esta = epsg_esta;
	}

	public Date getEpsg_fcrea() {
		return epsg_fcrea;
	}

	public void setEpsg_fcrea(Date epsg_fcrea) {
		this.epsg_fcrea = epsg_fcrea;
	}

}
