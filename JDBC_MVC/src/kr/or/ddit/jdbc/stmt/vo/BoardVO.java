package kr.or.ddit.jdbc.stmt.vo;

public class BoardVO {
	private String boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardDate;
	private String boardContent;
	
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	
	@Override
	public String toString() {

		if (boardTitle.length() >= 12) {
			if (boardContent.length() >= 6) {
				boardContent = boardContent.substring(0, 6);
			}
			return boardNo + "\t" + boardTitle + "\t\t" + boardWriter + "\t" + "\t" + boardDate + "\t" + boardContent+"...";
		} else if(boardTitle.length() > 5 && boardTitle.length() < 12) {
			if (boardContent.length() >= 6) {
				boardContent = boardContent.substring(0, 6);
			}
			return boardNo + "\t" + boardTitle + "\t\t\t" + boardWriter + "\t" + "\t" + boardDate + "\t" + boardContent+"...";			
		} else {
			if (boardContent.length() >= 6) {
				boardContent = boardContent.substring(0, 6);
			}
			return boardNo + "\t" + boardTitle + "\t\t\t\t" + boardWriter + "\t" + "\t" + boardDate + "\t"
					+ boardContent;
		}
	}

	public String toString(boolean selectOne) {
		if (boardTitle.length() > 4) {
			return boardNo + "\t" + boardTitle + "\t\t" + boardWriter + "\t" + "\t" + boardDate + "\n\n" + boardContent;
		} else {
			return boardNo + "\t" + boardTitle + "\t\t\t\t" + boardWriter + "\t" + "\t" + boardDate + "\n\n" + boardContent;
		}

	}
}
