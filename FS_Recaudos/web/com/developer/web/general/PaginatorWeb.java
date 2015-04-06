package com.developer.web.general;

import java.util.ArrayList;
import java.util.List;

public class PaginatorWeb {
	
	public static Long MAX_SIZE = new Long(100);
	
	
	long totalPages;
	long pageSize;
	long pageNumber;
	long totalSize;
	long sizeIni;
	long sizeFin;
	
	List<PagePaginatorWeb> pages;
	
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public long getPageNumber() {
		
		
		return pageNumber;
	}
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
	public long getSizeIni() {
		return sizeIni;
	}
	public void setSizeIni(long sizeIni) {
		this.sizeIni = sizeIni;
	}
	public long getSizeFin() {
		return sizeFin;
	}
	public void setSizeFin(long sizeFin) {
		this.sizeFin = sizeFin;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		
		this.totalSize = Math.round(totalSize);
	}
	
	
	public List<PagePaginatorWeb> generatePages() {
		
		pages = new ArrayList<PagePaginatorWeb>();
		Boolean isActive;
		for (long i = 1; i <= totalPages; i++) {
			
			
			
			if(pageNumber==i){
				isActive = new Boolean(true);
			}else{
				isActive = new Boolean(false);
			}
			pages.add(new PagePaginatorWeb(i, isActive));
			
		}
		return pages;
	}
	
	
	

}
