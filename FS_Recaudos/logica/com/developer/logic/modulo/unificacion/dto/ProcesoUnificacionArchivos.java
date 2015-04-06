package com.developer.logic.modulo.unificacion.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProcesoUnificacionArchivos {
	
	//Estados
	
	public static String INICIADO = "INICIADO";
	public static String UNIFICANDO_ARCHIVOS = "UNIFICANDO_ARCHIVOS";
	public static String FINALIZADO = "FINALIZADO";
	public static String ANULADO = "ANULADO";


	//Atributos atomicos
	private	Long    prun_prun 	 ;
	private	String  prun_usua 	 ;
	private	String	prun_eprun   ;
	private	Date	prun_fcrea   ;
	private	String	prun_observ  ;
	private	Date	prun_fini    ;
	private	Date	prun_ffin    ;
	private Long 	prun_archivos;
	
	
	
	//Atributos calculados
	List<ArchivoZIPProcesoUnificacion> archivosAZPU;
	List<ArchivoRecaudoUnificado> archivosARUN;
	List<HistoricoProcesoUnificacionArchivos> historicoProcesoUnificacionArchivos;
	List<TransformacionArchivoRecaudo> archivosTRAR;
	
	public ProcesoUnificacionArchivos() {
		archivosAZPU = new ArrayList<ArchivoZIPProcesoUnificacion>();
		archivosARUN = new ArrayList<ArchivoRecaudoUnificado>();
		archivosTRAR = new ArrayList<TransformacionArchivoRecaudo>();
	}
	
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
	public List<ArchivoZIPProcesoUnificacion> getArchivosAZPU() {
		return archivosAZPU;
	}
	public void setArchivosAZPU(List<ArchivoZIPProcesoUnificacion> archivosAZPU) {
		this.archivosAZPU = archivosAZPU;
	}
	public Long getPrun_archivos() {
		return prun_archivos;
	}
	public void setPrun_archivos(Long prun_archivos) {
		this.prun_archivos = prun_archivos;
	}
	

	public Date getPrun_fini() {
		return prun_fini;
	}

	public void setPrun_fini(Date prun_fini) {
		this.prun_fini = prun_fini;
	}

	public Date getPrun_ffin() {
		return prun_ffin;
	}

	public void setPrun_ffin(Date prun_ffin) {
		this.prun_ffin = prun_ffin;
	}

	public List<HistoricoProcesoUnificacionArchivos> getHistoricoProcesoUnificacionArchivos() {
		return historicoProcesoUnificacionArchivos;
	}

	public void setHistoricoProcesoUnificacionArchivos(
			List<HistoricoProcesoUnificacionArchivos> historicoProcesoUnificacionArchivos) {
		this.historicoProcesoUnificacionArchivos = historicoProcesoUnificacionArchivos;
	}

	public List<ArchivoRecaudoUnificado> getArchivosARUN() {
		return archivosARUN;
	}

	public void setArchivosARUN(List<ArchivoRecaudoUnificado> archivosARUN) {
		this.archivosARUN = archivosARUN;
	}

	public List<TransformacionArchivoRecaudo> getArchivosTRAR() {
		return archivosTRAR;
	}

	public void setArchivosTRAR(List<TransformacionArchivoRecaudo> archivosTRAR) {
		this.archivosTRAR = archivosTRAR;
	}


	


}
