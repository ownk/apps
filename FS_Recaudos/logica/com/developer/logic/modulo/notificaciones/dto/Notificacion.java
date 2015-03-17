package com.developer.logic.modulo.notificaciones.dto;


/**
 * Una notificación es un mensaje enviado a un usuario que se puede
 * enviar al correo del usuario, es visible desde la aplicación,
 * tiene estados 
 * 
 * @author oskar
 *
 */
public class Notificacion {

	public static final String ESTADO_NUEVA = "N";
	public static final String ESTADO_LEIDA = "L";
	public static final String ESTADO_ELIMINADA = "E";
	public static final String ESTADO_PENDIENTE_LECTURA = "P";

	private Long noti_noti; 
	private String noti_usua_emisor; 
	private String noti_titu;
	private String noti_cont; 
	private String noti_fcrea;
	private String noti_esta = ESTADO_NUEVA;
	private String noti_usua_receptor;
	

	public Long getNoti_noti() {
		return noti_noti;
	}

	public void setNoti_noti(Long noti_noti) {
		this.noti_noti = noti_noti;
	}

	public String getNoti_titu() {
		return noti_titu;
	}

	public void setNoti_titu(String noti_titu) {
		this.noti_titu = noti_titu;
	}

	public String getNoti_cont() {
		return noti_cont;
	}

	public void setNoti_cont(String noti_cont) {
		this.noti_cont = noti_cont;
	}

	public String getNoti_fcrea() {
		return noti_fcrea;
	}

	public void setNoti_fcrea(String noti_fcrea) {
		this.noti_fcrea = noti_fcrea;
	}

	public String getNoti_esta() {
		return noti_esta;
	}

	public void setNoti_esta(String noti_esta) {
		this.noti_esta = noti_esta;
	}

	


	public String getNoti_usua_emisor() {
		return noti_usua_emisor;
	}

	public void setNoti_usua_emisor(String noti_usua_emisor) {
		this.noti_usua_emisor = noti_usua_emisor;
	}

	public String getNoti_usua_receptor() {
		return noti_usua_receptor;
	}

	public void setNoti_usua_receptor(String noti_usua_receptor) {
		this.noti_usua_receptor = noti_usua_receptor;
	}

	@Override
	public String toString() {
		return "Notificacion [noti_noti=" + noti_noti + ", noti_usua_emisor="
				+ noti_usua_emisor + ", noti_titu=" + noti_titu
				+ ", noti_cont=" + noti_cont + ", noti_fcrea=" + noti_fcrea
				+ ", noti_esta=" + noti_esta + ", noti_usua_receptor="
				+ noti_usua_receptor + "]";
	}

	
}
