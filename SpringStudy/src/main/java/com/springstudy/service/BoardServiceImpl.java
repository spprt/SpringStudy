package com.springstudy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springstudy.entity.Board;
import com.springstudy.persistence.BoardDAO;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {

	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;

	@Override
	@Transactional
	public void write(Board board) throws Exception {
		boardDAO.write(board);
	}

	@Override
	public List<Board> list() throws Exception {
		return boardDAO.list();
	}

	@Override
	public int totalCnt() throws Exception {
		// to-do
		return boardDAO.totalCount();
	}

	@Override
	public Board read(Long id) throws Exception {
		return boardDAO.read(id);
	}

	@Override
	public void update(Board board) throws Exception {
		boardDAO.update(board);
	}

	@Override
	public void delete(Long id) throws Exception {
		boardDAO.delete(id);
	}

	@Override
	public List<Board> selectList(int requestPage) {
		return boardDAO.selectList(requestPage);
	}
}
