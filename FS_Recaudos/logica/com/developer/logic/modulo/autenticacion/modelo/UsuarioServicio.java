package com.developer.logic.modulo.autenticacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Modulo;
import com.developer.logic.modulo.autenticacion.dto.Servicio;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.autenticacion.controllerdb.UsuarioControllerDB;
import com.developer.persistence.modulo.autenticacion.mapper.dao.UsuarioDao;

public class UsuarioServicio {
	
	UsuarioControllerDB controllerDB;
	
	public UsuarioServicio() {
		controllerDB = new UsuarioControllerDB();
	}
	
	public Usuario crearUsuarioTransaccional(SqlSession session, Usuario usuario, Usuario usuarioCreador, StringBuffer mensajeError){
		Usuario nuevoUsuario= null;
		
		try {
			
			String claveValida = generarClaveValida(usuario.getUsua_usua(), usuario.getUsua_clave());
			
			usuario.setUsua_clave(claveValida);
			nuevoUsuario = controllerDB.crearUsuarioTransaccional(session, usuario, usuarioCreador, mensajeError);
			
			return nuevoUsuario;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			mensajeError.append("Error creando usuario. No se ha podido finalizar correctamente.");
			
			return null;
			
			
		} 	
		
	}
	
	
	public Boolean asignarRolPorUsuarioTransaccional(SqlSession session, String usua_usua, String rol_rol, StringBuffer mensajeError){
		
		
		Boolean respuesta = controllerDB.asignarRolPorUsuarioTransaccional(session, usua_usua, rol_rol, mensajeError);
		return respuesta;
	}
	
	
	public Usuario isUsuarioValido(String login, String clave){
		
		String claveEncriptada = generarClaveValida(login, clave);
		Usuario usuarioAutenticado = controllerDB.isUsuarioValido(login, claveEncriptada);
		
		return usuarioAutenticado;
	}
	
	
	
	public Servicio getServicioUsuarioPorURL(Usuario usuario, String url){
		
		
		Servicio servicio = null;
		
		servicio= controllerDB.getServicioUsuarioPorURL(usuario, url);
		
		return servicio;
		
	}
	
	
	public List<Servicio> getMenuHorizontalPorUsuario(Usuario usuario){
		
		List<Servicio> servicios = controllerDB.getMenuHorizontalPorUsuario(usuario);
		
		return servicios;
	}
	
	
	public List<Servicio> getServiciosTipoListadoPorUsuario(Usuario usuario){
		
		List<Servicio> servicios = controllerDB.getServiciosTipoListadoPorUsuario(usuario);
		
		return servicios;
	}
	
	public List<Modulo> getModulosPorUsuario(Usuario usuario){
		
		List<Modulo> modulos = controllerDB.getModulosPorUsuario(usuario);
		return modulos;
		
	}
	
	public Usuario getUsuario(String usua_usua){
		Usuario usuario = controllerDB.getUsuario(usua_usua);
		
		return usuario;
	}
	
	
	public List<String> getRolesPorUsuario(Usuario usuario){
		SqlSession session = DBManagerFSRecaudos.openSession();
		List<String> roles = null;
		
		try {

			UsuarioDao dao = session.getMapper(UsuarioDao.class);
			roles= dao.getRolesPorUsuario(usuario);
			
		}catch (Exception e) {
			SimpleLogger.error("Error getRolesPorUsuario : "+usuario.getUsua_usua(), e);
			
		} finally {
			session.close();
		}
		
		return roles;
		
	}
	
	public String  generarClaveValida(String login, String password){
		//String claveEncriptada = com.developer.core.utils.StringUtils.MD5(password);
		
		return password;
		
	}
	
	
	
}
