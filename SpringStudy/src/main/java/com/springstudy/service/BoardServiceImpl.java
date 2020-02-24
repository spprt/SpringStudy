package com.springstudy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springstudy.entity.Board;
import com.springstudy.entity.DocFile;
import com.springstudy.persistence.BoardDAO;
import com.springstudy.util.Criteria;
import com.springstudy.util.FileUtils;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {

	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;

	@Resource(name = "fileUtil")
	private FileUtils fileUtil;

	@Override
	@Transactional
	public void write(Board board) throws Exception {
		boardDAO.write(board);
	}

	@Override
	public void write(Board board, MultipartHttpServletRequest mpReq) throws Exception {
		write(board);
		List<? extends DocFile> list = fileUtil.parseInsertFileInfo(board, mpReq);
		int size = list.size();

		for (int i = 0; i < size; i++) {
			boardDAO.insertFile(list.get(i), board);
		}
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
	public List<Board> selectList(Criteria cri) {
		return boardDAO.selectList(cri);
	}
}
