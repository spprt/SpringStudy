package com.springstudy.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springstudy.entity.Board;
import com.springstudy.entity.BoardFile;
import com.springstudy.util.SearchCriteria;

public interface BoardService {
	public void write(Board board) throws Exception;

	public void write(Board board, MultipartHttpServletRequest mpReq) throws Exception;

	public Board read(Long id) throws Exception;

	public void update(Board board) throws Exception;

	public void delete(Long id) throws Exception;

	public List<Board> selectList(SearchCriteria cri);

	public List<Board> selectList(SearchCriteria cri, Long empid);
	
	public int selectCount(SearchCriteria cri);

	public int selectCount(SearchCriteria cri, Long empid);

	public BoardFile readFile(Long id) throws Exception;

	public void fileDown(Long id, HttpServletResponse response) throws Exception;
}
