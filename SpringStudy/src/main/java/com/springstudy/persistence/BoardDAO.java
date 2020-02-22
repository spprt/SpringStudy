package com.springstudy.persistence;

import java.util.List;

import com.springstudy.entity.Board;
import com.springstudy.util.Criteria;

public interface BoardDAO {

	public void write(Board board) throws Exception;

	public List<Board> list() throws Exception;

	public int totalCount() throws Exception;

	public Board read(Long id) throws Exception;

	public void update(Board board) throws Exception;

	public void delete(Long id) throws Exception;

	public List<Board> selectList(Criteria cri);
}
