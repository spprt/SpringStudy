package com.springstudy.service;

import java.util.List;
import java.util.Map;

import com.springstudy.entity.Board;
import com.springstudy.util.Criteria;

public interface BoardService {
	public void write(Board board) throws Exception;

	public List<Board> list() throws Exception;

	public int totalCnt() throws Exception;

	public Board read(Long id) throws Exception;

	public void update(Board board) throws Exception;

	public void delete(Long id) throws Exception;
	
	public List<Board> selectList(int requestPage);
}
