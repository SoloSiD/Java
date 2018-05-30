package kr.or.ddit.jdbc.stmt.service.impl;

import java.util.List;

import kr.or.ddit.jdbc.stmt.dao.StmtDao;
import kr.or.ddit.jdbc.stmt.dao.impl.StmtDaoImpl;
import kr.or.ddit.jdbc.stmt.service.StmtService;
import kr.or.ddit.jdbc.stmt.vo.BoardVO;

public class StmtServiceImpl implements StmtService {

	//Singleton
	private StmtDao dao = StmtDaoImpl.getInstance();
	
	private StmtServiceImpl() {

	}
	
	private static StmtServiceImpl instance = new StmtServiceImpl();
	
	public static StmtServiceImpl getInstance() {
		return instance;
	}
		
	@Override
	public void createTable() {
		dao.createTable();
	}

	@Override
	public void dropTable() {
		dao.dropTable();
	}

	@Override
	public void insert(String boardWriter, String boardTitle, String boardContent) {
		dao.insert(boardWriter, boardTitle, boardContent);

	}

	@Override
	public void update(String boardTitle, String boardContent, String boardNo) {
		dao.update(boardTitle, boardContent, boardNo);

	}

	@Override
	public void delete(String boardNo) {
		dao.delete(boardNo);

	}

	@Override
	public List<BoardVO> selectList(String boardNo, String boardTitle, String boardWriter, String boardContent) {
		return dao.selectList(boardNo, boardTitle, boardWriter, boardContent);
	}

	@Override
	public BoardVO select(String boardNo) {
		return dao.select(boardNo);
	}

}
