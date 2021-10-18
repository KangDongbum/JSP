package com.core;

public class Pagination2 {
	private int startpage; 
	private int endpage;
	
	public Pagination2(int page, int total) {
		/**
		 * 페이지 구간 번호
		 * (구간별 페이지 개수)
		 */
		page = (page <=0)?1:page; // 페이지 기본값
		int pageLinks =5; //구간별 페이지
		int num=(int)Math.floor((page -1) / pageLinks);
		
		startpage = (page * pageLinks) +1;

		endpage = (startpage +pageLinks)-1;
		
		/**
		 * endpage <= 마지막 페이지
		 * 1) 마지막 페이지(1페이지당 레코드 갯수)
		 * 2) 구간별 종료 번호가 마지막페이지 보다 큰지 체크 -> 크면 = 마지막 페이지
		 */
		int limit= 15;
		int endpageNo=(int)Math.ceil(total/(double)limit);
		
	}
	
	public String getPageHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class='pagination'>");
		for(int i=startpage; i<=endpage; i++) {
			sb.append("<li>");
			sb.append(i);
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}
}
