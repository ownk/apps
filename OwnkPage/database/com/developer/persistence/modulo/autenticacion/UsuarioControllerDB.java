package com.developer.persistence.modulo.autenticacion;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Modulo;
import com.developer.logic.modulo.autenticacion.dto.Servicio;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.autenticacion.dto.UsuarioURL;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.autenticacion.mapper.dao.UsuarioDao;

public class UsuarioControllerDB {
	
	
	private static UsuarioControllerDB instance;
	
	public static UsuarioControllerDB getInstance() {
		if (instance == null) {
			instance = new UsuarioControllerDB();
		}
		
		return instance;
	}
	
	
		
	public Usuario isUsuarioValido(String login, String claveEncriptada){
		
		Usuario usuario = new Usuario();
		usuario.setUsua_usua(login);
		usuario.setUsua_clave(claveEncriptada);
		
		Usuario usuarioAutenticado = null;
		
		SqlSession session = DBManager.openSession();

		try {

			UsuarioDao dao = session.getMapper(UsuarioDao.class);
			usuarioAutenticado= dao.isUsuarioValido(usuario);
			
			if(usuarioAutenticado!=null){
				usuarioAutenticado.setRoles(getRolesPorUsuario(usuarioAutenticado));
				
			}else{
				SimpleLogger.error("Error autenticando usuario, usuario invalido");
			}
			
		}catch (Exception e) {
			SimpleLogger.error("Error autenticando usuario", e);
			
		} finally {
			session.close();
		}
		
		return usuarioAutenticado;
	}
	
	
	
	public Servicio getServicioUsuarioPorURL(Usuario usuario, String url){
		
		
		Servicio servicio = null;
		
		SqlSession session = DBManager.openSession();

		try {
			UsuarioDao dao = session.getMapper(UsuarioDao.class);
			servicio = dao.getServicioUsuarioPorURL(new UsuarioURL(usuario.getUsua_usua(), url));

			
			
		}catch (Exception e) {
			SimpleLogger.error("Error consultando servicio asociado a la:"+url+" para el usuario: "+usuario.getUsua_usua(), e);
			
		} finally {
			session.close();
		}
		
		return servicio;
		
	}
	
	
	public List<Servicio> getMenuHorizontalPorUsuario(Usuario usuario){
		
		List<Servicio> serviciosPorUsuario = null;
		
		SqlSession session = DBManager.openSession();
		
		try {

			UsuarioDao dao = session.getMapper(UsuarioDao.class);
			serviciosPorUsuario = dao.getMenuHorizontalPorUsuario(usuario);

			 
			
		}catch (Exception e) {
			SimpleLogger.error("Error obteniendo servicios por usuario por usuario: "+usuario.getUsua_usua(), e);
			
		} finally {
			session.close();
		}
		
		return serviciosPorUsuario;
		
	}
	
	
	public List<Servicio> getServiciosTipoListadoPorUsuario(Usuario usuario){
		
		List<Servicio> serviciosPorUsuario = null;
		
		SqlSession session = DBManager.openSession();
		
		try {

			UsuarioDao dao = session.getMapper(UsuarioDao.class);
			serviciosPorUsuario = dao.getServiciosTipoListadoPorUsuario(usuario);

			 
			
		}catch (Exception e) {
			SimpleLogger.error("Error obteniendo servicios tipo listado por usuario: "+usuario.getUsua_usua(), e);
			
		} finally {
			session.close();
		}
		
		return serviciosPorUsuario;
		
	}
	
	public List<Modulo> getModulosPorUsuario(Usuario usuario){
		
		List<Modulo> modulosPorUsuario = null;
		
		SqlSession session = DBManager.openSession();
		
		try {

			UsuarioDao dao = session.getMapper(UsuarioDao.class);
			modulosPorUsuario = dao.getModulosPorUsuario(usuario);

			 
			
		}catch (Exception e) {
			SimpleLogger.error("Error obteniendo modulos por usuario por usuario: "+usuario.getUsua_usua(), e);
			
		} finally {
			session.close();
		}
		
		return modulosPorUsuario;
		
	}
	
	public Usuario getUsuario(String usua_usua){
		SqlSession session = DBManager.openSession();
		Usuario usuario = null;
		
		try {

			UsuarioDao dao = session.getMapper(UsuarioDao.class);
			usuario= dao.getUsuario(usua_usua);
			
			//Se consultan los roles por usuario
			usuario.setRoles(getRolesPorUsuario(usuario));
			
		}catch (Exception e) {
			SimpleLogger.error("Error consultando usuario: "+usua_usua, e);
			
		} finally {
			session.close();
		}
		
		return usuario;
		
	}
	
	
	private List<String> getRolesPorUsuario(Usuario usuario){
		SqlSession session = DBManager.openSession();
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
	
	
	public Usuario crearUsuarioTransaccional(	SqlSession session, 
			Usuario usuario, 
			Usuario usuarioCreador, 
			StringBuffer mensajeError){


		try{
		UsuarioDao dao= session.getMapper(UsuarioDao.class);
		
		//Se crea la preusuario
		dao.crearUsuario(usuario);
		return usuario;
		
		}catch (Exception e) {
			SimpleLogger.error("Error crearUsuarioTransaccional", e);
			mensajeError.append("Error al crear el registro de proyecto curricular");
			return null;
		}
		
		
	}
	
	public Boolean asignarRolPorUsuarioTransaccional(SqlSession session, String usua_usua, String rol_rol, StringBuffer mensajeError){
	
		try{
			UsuarioDao dao= session.getMapper(UsuarioDao.class);
		
			//Se crea la preusuario
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("usua_usua", usua_usua);
			hashMap.put("rol_rol", rol_rol);
			dao.asignarRolPorUsuario(hashMap);
			
			return true;
		
		}catch (Exception e) {
			SimpleLogger.error("Error crearUsuarioTransaccional", e);
			mensajeError.append("Error al crear el registro de proyecto curricular");
			return false;
		}
			
	}
	
	
}
