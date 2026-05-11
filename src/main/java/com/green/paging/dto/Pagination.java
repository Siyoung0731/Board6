package com.green.paging.dto;

import lombok.Getter;
import lombok.ToString;


// OffSet     30	ROWS FETCH NEXT     10     ROWS ONLY;
// 			limitStart	             numOfRows
// paging.jsp : 페이지 번호를 출력할 파일
// 한줄에 10개의 페이지번호를 출력
// startPage		nowpage(pageNo)		endPage			
//          1		2 3 4 5     ... 9 10  > >>
// << <    11	    12 13 14 15 ... 19 20 > >>
// << <    21	22 23 24 25 26        > >>
// 						totPageCount : 전체 페이지 수
@Getter
@ToString
public class Pagination {
	private int totCount; // 해당 메뉴의 조회된 자료수
	private int totPageCount; // 전체 페이지수 : totCount / numOfRows
	
	private int startPage;
	private int endPage;
	
	private int limitStart;
	
	private boolean existPrevPage;
	private boolean existNextPage;
	
	// 생성자
	public Pagination(int totCount, SearchDto sto) {
		if(totCount > 0) {
			this.totCount = totCount;
			calculation( sto );
		}
	}
	
	private void calculation(SearchDto sto) {
		// 전체 페이지 수 계산
		int numOfRows = sto.getNumOfRows();
		this.totPageCount = (int) Math.ceil( (double) this.totCount / (double) numOfRows);
		// 현재 페이지 : pageNo <- nowpage
		int pageNo = sto.getPageNo();
		if(pageNo > this.totPageCount ) {
			pageNo = this.totPageCount;
			sto.setPageNo(pageNo);
		}
		
		// 페이지 번호의 시작 계산 
		int pageSize = sto.getPageSize(); // 한줄에 출력할 페이지 번호 수
		startPage = ((pageNo - 1) / pageSize) * pageSize + 1;
		endPage = startPage + pageSize - 1;
		
		//limitStart : 데이터베이스에서 가져올 시작위치
		limitStart = sto.getOffset();
		// == limitStart = ( pageNo-1 ) * numOfRows;
		
		// 이전 페이지 이동 버튼 필요하다
		existPrevPage = startPage > 1;
		// 다음 페이지 이동 버튼 필요하다
		existNextPage = endPage < totPageCount;
	}
}




















