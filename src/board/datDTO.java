package board;

import java.sql.Timestamp;

public class datDTO { //�����Խ��� ��� DTO

	private int num;             //��۰�����ȣ
	private String writer;       //����ۼ���
	private Timestamp reg_date;  //����ۼ��ð�
	private int superNum;        //���� num����뵵
	private String contentDat;   //��۳���
	
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
