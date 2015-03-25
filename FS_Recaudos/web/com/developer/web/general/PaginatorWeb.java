package com.developer.web.general;

import java.util.ArrayList;
import java.util.List;

public class PaginatorWeb {
	
	double totalPages;
	double pageSize;
	double pageNumber;
	List<PagePaginatorWeb> pages;
	
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public double getPageSize() {
		return pageSize;
	}
	public void setPageSize(double pageSize) {
		this.pageSize = pageSize;
	}
	public double getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(double pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public List<PagePaginatorWeb> generatePages() {
		
		pages = new ArrayList<PagePaginatorWeb>();
		Boolean isActive = false;
		for (double i = 1; i <= totalPages; i++) {
			
			
			
			if(pageNumber==i){
				isActive = true;
			}
			pages.add(new PagePaginatorWeb(i, isActive));
			
		}
		return pages;
	}
	
	
	

}
