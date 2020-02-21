package com.springstudy.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.entity.Board;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void write(Board board) throws Exception {
		getSession().saveOrUpdate(board);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> list() throws Exception {
		return getSession().createQuery("from board").list();
	}

	@Override
	public int totalCount() throws Exception {
		return list().size();
	}

	@Override
	public Board read(Long id) throws Exception {
		return (Board) getSession().get(Board.class, id);
	}

	@Override
	public void update(Board board) throws Exception {
		getSession().update(board);
	}

	@Override
	public void delete(Long id) throws Exception {
		Board board = (Board) getSession().load(Board.class, id);
		if (null != board) {
			getSession().delete(board);
		}
	}

	private static int numPerPage = 10;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Board> selectList(int requestPage) {
		Query query = getSession().createQuery("from board order by id desc");
		query.setFirstResult((requestPage-1) * numPerPage);
		query.setMaxResults(numPerPage);
		return query.list();
	}
}
