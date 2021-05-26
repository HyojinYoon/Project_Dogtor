package board;

import java.sql.Timestamp;

public class datDTO { //ÀÚÀ¯°Ô½ÃÆÇ ´ñ±Û DTO

	private int num;             //´ñ±Û°íÀ¯¹øÈ£
	private String writer;       //´ñ±ÛÀÛ¼ºÀÚ
	private Timestamp reg_date;  //´ñ±ÛÀÛ¼º½Ã°£
	private int superNum;        //¿ø±Û numÀúÀå¿ëµµ
	private String contentDat;   //´ñ±Û³»¿ë
	
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
