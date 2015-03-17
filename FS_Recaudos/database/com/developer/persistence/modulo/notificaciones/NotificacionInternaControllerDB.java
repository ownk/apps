package com.developer.persistence.modulo.notificaciones;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.notificaciones.dto.Notificacion;
import com.developer.mybatis.DBManager;
import com.developer.mybatis.utils.DataBaseUtils;
import com.developer.persistence.modulo.notificaciones.mapper.dao.NotificacionInternaDao;

/**
 * Controlador de las notificaciones,
 * hereda de Observable para que existan Observadores que notifiquen
 * al usuario una vez generada la notificacion
 * @author oskar
 *
 */
public class NotificacionInternaControllerDB{
	

	private static NotificacionInternaControllerDB instance;

	private NotificacionInternaControllerDB() {

	}

	public static NotificacionInternaControllerDB getInstance() {
		if (instance == null) {
			instance = new NotificacionInternaControllerDB();
		}
		return instance;
	}

	public Boolean crearNotificacion(Notificacion notificacion) {

		SqlSession session = DBManager.openSession();
		
		try {
			
			NotificacionInternaDao notificacionDao = session.getMapper(NotificacionInternaDao.class);
			notificacionDao.guardarNotificacion(notificacion);
			
			session.commit();
			return true;
		} catch (Exception e) {
			SimpleLogger.error("Error creando la notitificacion interna", e);
			session.rollback();
			return false;

		} finally {
			session.close();
		}

	}
		
	/**
	 * Consultar las notificaciones de un usuario por estado
	 * 
	 * @param usua_usua
	 * @param estado - llave del estado(opcional), si no se pasa estado se consultan todos
	 * los estados menos Eliminados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Notificacion>  consultarNotificacionesPorUsuarioPaginada(String usua_usua, String noti_esta, Integer pagina) {

		SqlSession session = DBManager.openSession();

		try {
			
			HashMap<String , Object> hashMap = new HashMap<String, Object>();
			hashMap.put("usua_usua", usua_usua);
			hashMap.put("noti_esta", noti_esta);
			
			RowBounds rowBounds = DataBaseUtils.getRowBounds(pagina);
			System.out.println(rowBounds.getOffset()  + " " + rowBounds.getLimit());
			
			return session.selectList("com.developer.mybatis.mapper.NotificacionDao.consultarNotificacionesPorUsuario", hashMap, rowBounds);
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando notificaciones por usuario", e);

			return null;

		} finally {
			session.close();
		}

	}
	
	/**
	 * Consultar las notificaciones de un usuario por estado
	 * 
	 * @param usua_usua
	 * @param estado - llave del estado(opcional), si no se pasa estado se consultan todos
	 * los estados menos Eliminados
	 * @return
	 */
	public List<Notificacion>  consultarNotificacionesPorUsuario(String usua_usua, String...noti_estados ) {

		SqlSession session = DBManager.openSession();

		try {

			NotificacionInternaDao dao = session.getMapper(NotificacionInternaDao.class);
			
			HashMap<String , Object> hashMap = new HashMap<String, Object>();
			hashMap.put("usua_usua", usua_usua);
			
			//Si es estado es null, se consulta todos los estados
			if(noti_estados!=null){
				String estados="";
				for (String string : noti_estados) {
					estados = estados+"'"+string+"',";
				}
				estados = estados.substring(0, estados.lastIndexOf(','));
				
				hashMap.put("noti_estados", estados);
			}
			
			return dao.consultarNotificacionesPorUsuario(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando notificaciones por usuario", e);
			return null;

		} finally {
			session.close();
		}

	}
	
	/**
	 * Cambia el estado de la notificación
	 * @param usua_usua
	 * @param noti_noti
	 * @param estado
	 * @return
	 */
	public Boolean cambiarEstadoNotificacion(Long noti_noti, String estado) {

		SqlSession session = DBManager.openSession();

		try {
			

			NotificacionInternaDao notificacionDao = session.getMapper(NotificacionInternaDao.class);
			 
			Notificacion notificacion = new Notificacion();
			notificacion.setNoti_noti(noti_noti);
			notificacion.setNoti_esta(estado);
			notificacionDao.cambiarEstadoNotificacion(notificacion);
			
			session.commit();
			
			return true;
		} catch (Exception e) {
			SimpleLogger.error("Error cambiando estado de la notificacion interna: "+noti_noti, e);
			session.rollback();
			return false;

		} finally {
			session.close();
		}

	}
	
	public static void main(String[] args) {
		String estados = "('a','b',";
		estados = estados.substring(0, estados.lastIndexOf(','));
		System.out.println(estados);
	}
	
	
}
