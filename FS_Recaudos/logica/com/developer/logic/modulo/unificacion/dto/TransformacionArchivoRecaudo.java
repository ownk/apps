package com.developer.logic.modulo.unificacion.dto;

import java.io.File;
import java.util.Date;

public class TransformacionArchivoRecaudo {
	
	Long trar_trar; 			
	String trar_tpar;				
	String trar_usua; 			
	Long trar_prun; 			
	String  trar_url_file_ini; 	
	String trar_url_file_fin; 	
	Long trar_bytes_file_ini; 	
	Long trar_bytes_file_fin; 	
	Long trar_reg_file_ini; 	
	Long trar_reg_file_fin; 	
	String trar_observ; 			
	Date trar_fcrea;
	
	//Atributos calculados
	String nombreOrigen =null;
	String nombreDestino =null;
	
	public TransformacionArchivoRecaudo() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getTrar_trar() {
		return trar_trar;
	}
	public void setTrar_trar(Long trar_trar) {
		this.trar_trar = trar_trar;
	}
	public String getTrar_tpar() {
		return trar_tpar;
	}
	public void setTrar_tpar(String trar_tpar) {
		this.trar_tpar = trar_tpar;
	}
	public String getTrar_usua() {
		return trar_usua;
	}
	public void setTrar_usua(String trar_usua) {
		this.trar_usua = trar_usua;
	}
	public Long getTrar_prun() {
		return trar_prun;
	}
	public void setTrar_prun(Long trar_prun) {
		this.trar_prun = trar_prun;
	}
	public String getTrar_url_file_ini() {
		return trar_url_file_ini;
	}
	public void setTrar_url_file_ini(String trar_url_file_ini) {
		
		File file = new File(trar_url_file_ini);
		if(file.exists()){
			nombreOrigen = file.getName();
			
		}
		
		
		this.trar_url_file_ini = trar_url_file_ini;
	}
	public String getTrar_url_file_fin() {
		return trar_url_file_fin;
	}
	public void setTrar_url_file_fin(String trar_url_file_fin) {
		
		File file = new File(trar_url_file_fin);
		if(file.exists()){
			nombreDestino = file.getName();
			
		}
		
		this.trar_url_file_fin = trar_url_file_fin;
	}
	public Long getTrar_bytes_file_ini() {
		return trar_bytes_file_ini;
	}
	public void setTrar_bytes_file_ini(Long trar_bytes_file_ini) {
		this.trar_bytes_file_ini = trar_bytes_file_ini;
	}
	public Long getTrar_bytes_file_fin() {
		return trar_bytes_file_fin;
	}
	public void setTrar_bytes_file_fin(Long trar_bytes_file_fin) {
		this.trar_bytes_file_fin = trar_bytes_file_fin;
	}
	public Long getTrar_reg_file_ini() {
		return trar_reg_file_ini;
	}
	public void setTrar_reg_file_ini(Long trar_reg_file_ini) {
		this.trar_reg_file_ini = trar_reg_file_ini;
	}
	public Long getTrar_reg_file_fin() {
		return trar_reg_file_fin;
	}
	public void setTrar_reg_file_fin(Long trar_reg_file_fin) {
		this.trar_reg_file_fin = trar_reg_file_fin;
	}
	public String getTrar_observ() {
		return trar_observ;
	}
	public void setTrar_observ(String trar_observ) {
		this.trar_observ = trar_observ;
	}
	public Date getTrar_fcrea() {
		return trar_fcrea;
	}
	public void setTrar_fcrea(Date trar_fcrea) {
		this.trar_fcrea = trar_fcrea;
	}
	
	
	

}
