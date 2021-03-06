package board;

import java.sql.Timestamp;

public class datDTO { //자유게시판 댓글 DTO

	private int num;             //댓글고유번호
	private String writer;       //댓글작성자
	private Timestamp reg_date;  //댓글작성시간
	private int superNum;        //원글 num저장용도
	private String contentDat;   //댓글내용
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter (String writer) {
		this.writer = writer;
	}
	
	
	public Timestamp getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	public int getSuperNum() {
		return superNum;
	}
	public void setSuperNum(int ref) {
		this.superNum = ref;
	}
	
	public String getContentDat() {
		return contentDat;
	}
	public void setContentDat(String contentDat) {
		this.contentDat = contentDat;
	}
	
}
