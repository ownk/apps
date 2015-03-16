package com.developer.persistence.modulo.autenticacion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.autenticacion.dto.Modulo;
import com.developer.logic.modulo.autenticacion.dto.Servicio;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.autenticacion.dto.UsuarioURL;

public interface UsuarioDao {

	public Usuario isUsuarioValido(Usuario usuario);
	
	public List<Servicio> getMenuHorizontalPorUsuario(Usuario usuario);
	
	public Servicio getServicioUsuarioPorURL(UsuarioURL usuarioURL);
	
	public List<Modulo> getModulosPorUsuario(Usuario usuario);

	public Usuario getUsuario(String usua_usua);
	
	public List<String> getRolesPorUsuario(Usuario usuario);
}
