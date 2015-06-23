package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class ProyectoNoSIFIActivo {
	
	Long    pnsa_proy 		;
	Long    pnsa_plan_sifi 	;
	String  pnsa_descri 	;
	Date    pnsa_fcrea 		;
	public Long getPnsa_proy() {
		return pnsa_proy;
	}
	public void setPnsa_proy(Long pnsa_proy) {
		this.pnsa_proy = pnsa_proy;
	}
	public Long getPnsa_plan_sifi() {
		return pnsa_plan_sifi;
	}
	public void setPnsa_plan_sifi(Long pnsa_plan_sifi) {
		this.pnsa_plan_sifi = pnsa_plan_sifi;
	}
	public String getPnsa_descri() {
		return pnsa_descri;
	}
	public void setPnsa_descri(String pnsa_descri) {
		this.pnsa_descri = pnsa_descri;
	}
	public Date getPnsa_fcrea() {
		return pnsa_fcrea;
	}
	public void setPnsa_fcrea(Date pnsa_fcrea) {
		this.pnsa_fcrea = pnsa_fcrea;
	}

	
	

}
