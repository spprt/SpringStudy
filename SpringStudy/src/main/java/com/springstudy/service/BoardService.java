package com.springstudy.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springstudy.entity.Board;
import com.springstudy.entity.BoardFile;
import com.springstudy.util.Criteria;

public interface BoardService {
	public void write(Board board) throws Exception;

	public void write(Board board, MultipartHttpServletRequest mpReq) throws Exception;

	public List<Board> list() throws Exception;

	public int totalCnt() throws Exception;

	public int totalCnt(Long empid) throws Exception;

	public Board read(Long id) throws Exception;

	public void update(Board board) throws Exception;

	public void delete(Long id) throws Exception;

	public List<Board> selectList(Criteria cri);

	public List<Board> selectList(Criteria cri, Long empid);

	public BoardFile readFile(Long id) throws Exception;

	public void fileDown(Long id, HttpServletResponse response) throws Exception;
}
