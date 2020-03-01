package com.springstudy.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.entity.Board;
import com.springstudy.entity.BoardFile;
import com.springstudy.entity.DocFile;
import com.springstudy.util.SearchCriteria;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> selectList(SearchCriteria cri) {
		String sql = getListQuery(cri);
		Query query = getSession().createQuery(sql);
		appendSearchCondition(cri, query);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> selectList(SearchCriteria cri, Long empid) {
		Query query = getSession().createQuery("from board where writerId = :empid order by id desc");
		query.setParameter("empid", empid);
		query.setFirstResult((cri.getPage() - 1) * cri.getPerPageNum());
		query.setMaxResults(cri.getPerPageNum());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int selectCount(SearchCriteria cri) {
		String sql = getListQuery(cri);
		Query query = getSession().createQuery(sql);
		appendSearchCondition(cri, query, true);
		List<Board> list = query.list();

		return list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int selectCount(SearchCriteria cri, Long empid) {
		Query query = getSession().createQuery("from board where writerId = :empid order by id desc");
		query.setParameter("empid", empid);
		query.setFirstResult((cri.getPage() - 1) * cri.getPerPageNum());
		query.setMaxResults(cri.getPerPageNum());
		List<Board> list = query.list();

		return list.size();
	}

	public void insertFile(DocFile file, Board board) throws Exception {
		DocFile f = new BoardFile(board);
		f.setDocid(file.getDocid());
		f.setFileName(file.getFileName());
		f.setFileSize(file.getFileSize());
		f.setStoredName(file.getStoredName());

		getSession().save(f);
	}

	@Override
	public BoardFile readFile(Long id) throws Exception {
		return (BoardFile) getSession().get(BoardFile.class, id);
	}

	private String getListQuery(SearchCriteria cri) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from board ");
		if (null != cri.getKeyword() && !cri.getKeyword().isEmpty()) {
			sql.append(" where ");
			sql.append(cri.getSearchType());
			sql.append(" like '%' || :keyword || '%' ");
		}
		sql.append("order by id desc");
		return sql.toString();
	}

	private void appendSearchCondition(SearchCriteria cri, Query query) {
		appendSearchCondition(cri, query, false);
	}

	private void appendSearchCondition(SearchCriteria cri, Query query, boolean isCount) {
		if (null != cri.getKeyword() && !cri.getKeyword().isEmpty()) {
			query.setParameter("keyword", cri.getKeyword());
		}
		if (!isCount) {
			query.setFirstResult((cri.getPage() - 1) * cri.getPerPageNum());
			query.setMaxResults(cri.getPerPageNum());
		}
	}
}
