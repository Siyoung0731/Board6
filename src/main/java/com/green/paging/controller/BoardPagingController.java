package com.green.paging.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.config.MvcConfig;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.PagingResponse;
import com.green.paging.dto.SearchDto;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired 
	private BoardPagingMapper boardPagingMapper;
	
    private final MvcConfig mvcConfig;

    BoardPagingController(MvcConfig mvcConfig) {
        this.mvcConfig = mvcConfig;
    }
	///BoardPaging/List?menu_id=MENU01&nowpage=1
	@RequestMapping("/List")
	public ModelAndView list(BoardDto bto, int nowpage) {
		
		// 메뉴 목록 : menus.jsp 용
		List<MenuDTO> mList = menuMapper.getMenuList();
		
		
		// 게시판 목록 조회(페이징해서)
		// 해당 메뉴의 자료 갯수 : 
		int totCount = boardPagingMapper.count(bto); // menu_id
		System.out.println("totcount: "+totCount);
		
		PagingResponse<BoardDto> res = null;
		if( totCount < 1) { // 현재 Menu_id 로 조회한 자료가 없다면 
			res = new PagingResponse<>(
					Collections.emptyList(), null);
			//Collections.emptyList() : 자료가 없는 빈 리스트를 채운다.
		}
		
		// 페이징을 위한 초기설정
		SearchDto sto = new SearchDto();
		sto.setPageNo(nowpage); // 현재 페이지 정보
		sto.setNumOfRows(10); // 한 페이지에 출력될 자료수
		sto.setPageSize(10); // paging.jsp 한줄에 출력될 페이지 번호 수(= 처음, 이전 -> 다음, 마지막)
		
		// Pagination 설정
		Pagination pagination = new Pagination(totCount, sto);
		sto.setPagination(pagination);
		
		// 검색 조건 추가
		// 추가된 검색 조건
		String title = bto.getTitle();
		String writer = bto.getWriter();
		String content = bto.getContent();
		
		int offset = sto.getOffset();
		int numOfRows = sto.getNumOfRows();
		
		String menu_id = bto.getMenu_id();
		
		List<BoardDto> list = boardPagingMapper.getBoardPagingList(
					menu_id, title, writer, content, offset, numOfRows);
		res = new PagingResponse<>(list, pagination);
		
		System.out.println(res);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("mList", mList);
		mv.addObject("nowpage", nowpage);
		mv.addObject("menu_id", menu_id); // 현재 메뉴 정보
		
		mv.addObject("bList", list);
		mv.addObject("sto", sto);
		
		return mv;
	}
	
	///BoardPaging/WriteForm?menu_id=MENU01&nowpage=1
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(BoardDto bto, int nowpage) {
		
		
		ModelAndView mv = new ModelAndView();
		return mv;
	}
}
