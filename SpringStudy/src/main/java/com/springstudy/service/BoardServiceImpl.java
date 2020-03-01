package com.springstudy.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springstudy.entity.Board;
import com.springstudy.entity.BoardFile;
import com.springstudy.entity.DocFile;
import com.springstudy.persistence.BoardDAO;
import com.springstudy.util.FileUtils;
import com.springstudy.util.SearchCriteria;

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
	public Board read(Long id) throws Exception {
		return boardDAO.read(id);
	}

	@Override
	public void update(Board board) throws Exception {
		boardDAO.update(board);
	}

	@Override
	public void delete(Long id) throws Exception {
		Board board = read(id);
		Collection<? extends DocFile> fileList = board.getFiles();
		for (DocFile f : fileList) {
			fileUtil.fileDelete(f.getStoredName());
		}
		boardDAO.delete(id);
	}

	@Override
	public List<Board> selectList(SearchCriteria cri) {
		return boardDAO.selectList(cri);
	}

	@Override
	public List<Board> selectList(SearchCriteria cri, Long empid) {
		return boardDAO.selectList(cri, empid);
	}
	
	@Override
	public int selectCount(SearchCriteria cri) {
		return boardDAO.selectCount(cri);
	}

	@Override
	public int selectCount(SearchCriteria cri, Long empid) {
		return boardDAO.selectCount(cri, empid);
	}

	@Override
	public BoardFile readFile(Long id) throws Exception {
		return boardDAO.readFile(id);
	}

	@Override
	public void fileDown(Long id, HttpServletResponse response) throws Exception {
		BoardFile file = readFile(id);
		String storedName = file.getStoredName();
		String fileName = file.getFileName();

		fileUtil.fileDown(response, storedName, fileName);
	}
}
