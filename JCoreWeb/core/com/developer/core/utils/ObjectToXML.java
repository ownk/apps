package com.developer.core.utils;

import com.developer.core.config.GeneralConstants;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class ObjectToXML {
	
	XmlFriendlyReplacer replacer;  
	com.thoughtworks.xstream.XStream xstream;
	
	
	public ObjectToXML(){
		replacer = new XmlFriendlyReplacer("$", "_");  
		xstream = new XStream(new DomDriver(GeneralConstants.ENCONDING, replacer)){
			@Override
	        protected MapperWrapper wrapMapper(MapperWrapper next) {
	            return new MyClassAliasingMapper(next);
	        }
	    };
	    
		
	}
	
	
		
	public String getXML ( Object object){
		
		return xstream.toXML(object);
		
	}
	
	
	
	private class MyClassAliasingMapper extends MapperWrapper {

	    public MyClassAliasingMapper(Mapper wrapped) {
	        super(wrapped);
	    }

	    @SuppressWarnings("rawtypes")
		@Override
		public String serializedClass(Class type) {
			try {
				if(type == null){
					return null;
				}
				
				String nombreClase = type.getSimpleName();
				
				if(nombreClase.contains("$")){
					nombreClase = nombreClase.substring(0, nombreClase.indexOf("$"));
					
				}
				
				return nombreClase;
			}
			catch (Throwable e) {
				SimpleLogger.debug("Error al obtener el nombre de la clase" +  type);
			}
			return null;
		}

	}
	
	

}
