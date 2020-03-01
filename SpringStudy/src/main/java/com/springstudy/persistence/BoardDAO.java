package com.springstudy.persistence;

import java.util.List;

import com.springstudy.entity.Board;
import com.springstudy.entity.BoardFile;
import com.springstudy.entity.DocFile;
import com.springstudy.util.SearchCriteria;

public interface BoardDAO {

	public void write(Board board) throws Exception;

	public Board read(Long id) throws Exception;

	public void update(Board board) throws Exception;

	public void delete(Long id) throws Exception;

	public List<Board> selectList(SearchCriteria cri);

	public List<Board> selectList(SearchCriteria cri, Long empid);

	public int selectCount(SearchCriteria cri);

	public int selectCount(SearchCriteria cri, Long empid);

	public void insertFile(DocFile file, Board board) throws Exception;

	public BoardFile readFile(Long id) throws Exception;
}
