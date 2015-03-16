package com.developer.mybatis.utils;

import org.apache.ibatis.session.RowBounds;

import com.developer.web.general.Constantes;

public class DataBaseUtils {
	
	public static RowBounds getRowBounds(Integer pagina){
		int offset = (pagina - 1) / Constantes.RESULTADOS_PAGINA + 1;
		return new RowBounds(offset, Constantes.RESULTADOS_PAGINA);
		
	}

}
