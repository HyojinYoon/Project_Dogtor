package board;
import java.sql.Timestamp;

public class DTO{

	private int num;         //게시글 고유번호(시퀀스처리)
    private String writer;   //작성자
    private String subject;  //제목
    private String content;  //내용
    private Timestamp reg;   //작성시간
    private int readcount;   //조회수
    private int ref;         //게시글 그룹
    private int re_step;	 //게시글 그룹내 작성순서
    private int re_level;    //원글에 답글 그룹
    private String notice;   
    private String head;     //머릿말
    private int bs;          //보드내 status
    private String save;     //사진첨부
    private String save1;     //사진첨부
    private String save2;     //사진첨부
    private String[] save3;     //사진첨부
    private int pictureNum; //사진테이블의 프라이머리키

	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getBs() {
		return bs;
	}
	public void setBs(int bs) {
		this.bs = bs;
	}
	private String id;
	
	
	public String getId() {
		return id;
	}
	public void setId (String id) {
		this.id = id;
	}
	public String getSave() {
		return save;
	}
	public void setSave (String save) {
		this.save = save;
	}
	public String getSave1() {
		return save1;
	}
	public void setSave1(String save1) {
		this.save1 = save1;
	}
	public String getSave2() {
		return save2;
	}
	public void setSave2(String save2) {
		this.save2= save2;
	}
	public String[] getSave3() {
		return save3;
	}
	public void setSave2 (String[] save3) {
		this.save3 = save3;
	}
	public int getPictureNum() {
		return pictureNum;
	}
	public void setPictureNum(int pictureNum) {
		this.pictureNum = pictureNum;	
	}
    
}