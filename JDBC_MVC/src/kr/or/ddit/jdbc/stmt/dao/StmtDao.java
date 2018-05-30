package kr.or.ddit.jdbc.stmt.dao;

import java.util.List;

import kr.or.ddit.jdbc.stmt.vo.BoardVO;

public interface StmtDao {
	public void createTable();
	
	public void dropTable();
	
	public void insert(String boardWriter, String boardTitle, String boardContent);
	
	public void update(String boardTitle, String boardContent, String boardNo);
	
	public void delete(String boardNo);
	
	public List<BoardVO> selectList(String boardNo, String boardTitle, String boardWriter, String boardContent);
	
	public BoardVO select(String boardNo);
}
