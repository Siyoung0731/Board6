package com.green.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.config.MvcConfig;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDto;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {

    private final MvcConfig mvcConfig;

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired 
	private BoardPagingMapper boardPagingMapper;

    BoardPagingController(MvcConfig mvcConfig) {
        this.mvcConfig = mvcConfig;
    }
	// List
	///BoardPaging/List?menu_id=MENU01&nowpage=1
	@RequestMapping("/List")
	public ModelAndView list(BoardDto bto, int nowpage,
			String searchType, String keyword) {
		
		// 전체 메뉴 목록 : menus.jsp 용
		List<MenuDTO> mList = menuMapper.getMenuList();
			
		// 게시판 목록 조회(페이징해서)
		// 해당 메뉴의 자료 갯수 : 조회된
		int totCount = boardPagingMapper.count(bto, searchType, keyword); // menu_id
		System.out.println("totcount: "+totCount);
		
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
		int offset = sto.getOffset();
		int numOfRows = sto.getNumOfRows();
		
		String menu_id = bto.getMenu_id();
		
		// 페이지 조회
		List<BoardDto> list = boardPagingMapper.getBoardPagingList(
					menu_id, searchType, keyword, offset, numOfRows);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("mList", mList);
		
		mv.addObject("nowpage", nowpage);
		mv.addObject("menu_id", menu_id); // 현재 메뉴 정보
		
		mv.addObject("bList", list);
		mv.addObject("sto", sto);
		
		mv.addObject("searchType", searchType);
		mv.addObject("keyword", keyword);
		
		return mv;
	}
	// View
	// /BoardPaging/View?idx=190&menu_id=MENU01&nowpage=1
	@RequestMapping("/View")
	public ModelAndView view(BoardDto bto, int nowpage) {
		
		// 메뉴 목록 조회
		List<MenuDTO> mList = menuMapper.getMenuList();
 		
		// IDX 에 해당하는 게시글 조회수 증가
		boardPagingMapper.incHit(bto);
		
		// idx 로 조회한 게시글 한 개를 조회
		BoardDto board = boardPagingMapper.getBoard(bto);
		
		String menu_id = bto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/view");
		mv.addObject("mList", mList);
		
		mv.addObject("menu_id", menu_id);
		mv.addObject("nowpage", nowpage);
		
		mv.addObject("board", board);
		return mv;
	}
	// WriteForm
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(BoardDto bto, int nowpage) {
		
		List<MenuDTO> mList = menuMapper.getMenuList();
		
		String menu_id = bto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/write");
		mv.addObject("mList", mList);
		mv.addObject("menu_id", menu_id);
		mv.addObject("nowpage", nowpage);
		return mv;
	}
	// Write
	// DB 저장 : menu_id=MENU01, title=제목, writer=admin, content=내용
	// 돌아가기 위해 필요한 변수 : menu_id, nowpage
	@RequestMapping("/Write")
	public ModelAndView write(BoardDto bto, int nowpage) {
		
		boardPagingMapper.insertBoard(bto);
				
		// 새 글 저장 - DB 저장
		ModelAndView mv = new ModelAndView();
		String menu_id = bto.getMenu_id(); // 넘어 오는 menu_id
		
		String fmt = "redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
		String loc = String.format(fmt, menu_id, nowpage);
		mv.setViewName(loc);
		return mv;
	}
	// Delete
	///BoardPaging/WriteForm?menu_id=MENU01&nowpage=1
	@RequestMapping("/Delete")
	public ModelAndView Delete(BoardDto bto, int nowpage) {
		
		boardPagingMapper.deleteBoard(bto);
		
		int idx = bto.getIdx();
		String menu_id = bto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("idx", idx);
		mv.addObject("menu_id", menu_id);
		mv.addObject("nowpage", nowpage);
		
		mv.setViewName("redirect:List");
		return mv;
	}
	// UpdateForm
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(BoardDto bto, int nowpage) {
		
		List<MenuDTO> mList = menuMapper.getMenuList();
		
		BoardDto board = boardPagingMapper.getBoard(bto);
		
		int idx = bto.getIdx();
		String menu_id = bto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/update");
		mv.addObject("idx", idx);
		mv.addObject("menu_id", menu_id);
		mv.addObject("nowpage", nowpage);
		mv.addObject("board", board);
		mv.addObject("mList", mList);
		return mv;
	}
	// Update
	@RequestMapping("/Update")
	public ModelAndView update(BoardDto bto, int nowpage) {
		
		boardPagingMapper.updateBoard(bto);
		
		int idx = bto.getIdx();
		String menu_id = bto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("idx", idx);
		mv.addObject("menu_id", menu_id);
		mv.addObject("nowpage", nowpage);
		mv.setViewName("redirect:/BoardPaging/View");
		return mv;
	}
}
