package kr.or.ddit.jdbc.stmt.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.jdbc.stmt.dao.StmtDao;
import kr.or.ddit.jdbc.stmt.vo.BoardVO;

public class StmtDaoImpl implements StmtDao {

	private StmtDaoImpl() {
		
	}
	
	private static StmtDaoImpl instance = new StmtDaoImpl();
	
	public static StmtDaoImpl getInstance() {
		return instance;
	}
	
	private final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private final String DB_USER = "pc10";
	private final String DB_PASSWORD = "java";

	public void createTable() {
		Connection conn = null;
		Statement stmt = null;
		// ResultSet rs = null;

		String query = " CREATE TABLE JDBC_TEST " 
				+ " ( " + " BOARD_NO NUMBER NOT NULL, "
				+ " BOARD_TITLE VARCHAR2(100) NOT NULL, " 
				+ " BOARD_WRITER VARCHAR2(20) NOT NULL, " 
				+ " BOARD_DATE DATE, "
				+ " BOARD_CONTENT VARCHAR2(2000), " 
				+ " CONSTRAINT PK_JDBC_TEST PRIMARY KEY (BOARD_NO) " 
				+ " ) ";
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB 연결
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// 3. Statement 생성
			stmt = conn.createStatement();

			// 4. Query의 실행 - create문은 결과값을 받지 않으므로 5단계는 생략.
			stmt.execute(query);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6. 연결을 닫아준다.(역순으로 닫기)
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void dropTable() {
		Connection conn = null;
		Statement stmt = null;

		String query = "DROP TABLE JDBC_TEST";

		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DB 연결
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 4. Query 실행 - 5단계 생략.
			stmt.execute(query);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void insert(String boardWriter, String boardTitle, String boardContent) {
		Connection conn = null;
		Statement stmt = null;
		
		String query = " INSERT INTO JDBC_TEST "
					+	" ( "
					+	" BOARD_NO, "
					+	" BOARD_WRITER, "
					+	" BOARD_TITLE, "
					+	" BOARD_DATE, "
					+	" BOARD_CONTENT "
					+	" ) "
					+	" VALUES "
					+	" ( "
					+	" SEQ_JDBC_TEST.NEXTVAL, "
					+	" '"+boardWriter+"', "
					+	" '"+boardTitle+"', "
					+	" SYSDATE, "
					+	" '"+boardContent+"' "
					+	" ) ";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);		
			//execute()
			//거의 안씀
			//executeUpdate()
			//반환값이 영향을 받은 컬럼의 갯수.
			//executeQuery()
			//Select문을 쓸때 사용.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	public void update(String boardNumber, String boardTitle,  String boardContent) {
		Connection conn = null;
		Statement stmt = null;
		
		String query =" UPDATE JDBC_TEST "
					+ " SET "
					+ " BOARD_TITLE = " + " '"+boardTitle+"', "
					+ " BOARD_CONTENT = " + " '" +boardContent+"', "
					+ " BOARD_DATE = SYSDATE " 
					+ " WHERE " 
					+ " BOARD_NO = " + boardNumber;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void delete(String boardNumber) {
		Connection conn = null;
		Statement stmt = null;
		
		String query = " DELETE FROM JDBC_TEST "
				+	" WHERE BOARD_NO = "+boardNumber;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public List<BoardVO> selectList(String boardNo, String boardTitle, String boardWriter, String boardContent) { 
		List<BoardVO> resultList = new ArrayList<BoardVO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = " SELECT * FROM JDBC_TEST WHERE 1=1";
		
		if(boardNo != null && !boardNo.equals("")) {
			query += " AND BOARD_NO = " + boardNo;
		}
		
		if(boardTitle != null && !boardTitle.equals("")) {
			query += " AND BOARD_TITLE LIKE '%" + boardTitle +"%' ";
		}
		
		if(boardWriter != null && !boardWriter.equals("")) {
			query += " AND BOARD_WRITER LIKE '%" + boardWriter +"%' ";
		}
		
		if(boardContent != null && !boardContent.equals("")) {
			query += " AND BOARD_CONTENT LIKE '%" + boardContent +"%' ";
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String resultBoardNo = rs.getString("BOARD_NO");
				String resultBoardTitle = rs.getString("BOARD_TITLE");
				String resultBoardWriter = rs.getString("BOARD_WRITER");
				String resultBoardDate = rs.getString("BOARD_DATE");
				String resultBoardContent = rs.getString("BOARD_CONTENT");
				
				//rs.getString(1);
				
				BoardVO vo = new BoardVO();
				vo.setBoardNo(resultBoardNo);
				vo.setBoardTitle(resultBoardTitle);
				vo.setBoardWriter(resultBoardWriter);
				vo.setBoardDate(resultBoardDate);
				vo.setBoardContent(resultBoardContent);
				
				resultList.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		}		
		
		return resultList;
	}
	
	public BoardVO select(String boardNo) {
		BoardVO vo = new BoardVO();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = " SELECT * FROM JDBC_TEST WHERE BOARD_NO = "+boardNo;
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String resultBoardNo = rs.getString("BOARD_NO");
				String resultBoardTitle = rs.getString("BOARD_TITLE");
				String resultBoardWriter = rs.getString("BOARD_WRITER");
				String resultBoardDate = rs.getString("BOARD_DATE");
				String resultBoardContent = rs.getString("BOARD_CONTENT");
				
				vo.setBoardNo(resultBoardNo);
				vo.setBoardTitle(resultBoardTitle);
				vo.setBoardWriter(resultBoardWriter);
				vo.setBoardDate(resultBoardDate);
				vo.setBoardContent(resultBoardContent);							
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}			
		}
		return vo;
	}
}
