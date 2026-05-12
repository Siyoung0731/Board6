package com.green.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;

@Mapper
public interface BoardPagingMapper {
	int count(BoardDto bto, String searchType, String keyword);

	List<BoardDto> getBoardPagingList(String menu_id, String searchType, String keyword, int offset, int numOfRows);

	BoardDto getBoard(BoardDto bto);

	void deleteBoard(BoardDto bto);

	void updateBoard(BoardDto bto);	
}
