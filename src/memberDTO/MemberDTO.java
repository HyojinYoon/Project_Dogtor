package memberDTO;

import java.sql.Timestamp;
import java.util.ArrayList;

import check.ValueCheck;

public class MemberDTO {
	private String idEmail;
	private String siteEmail;
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String nick;
	private String phone;
	private int status;
	private Timestamp reg;
	private String col;
	private String searchMember;
	private String sql;
	private static ArrayList<MemberDTO> searchList;
	
	
	// idEmail 저장 및 호출
	public String getIdEmail()
	{	return idEmail;	}
	public void setIdEmail(String idEmail)
	{	this.idEmail = idEmail; 	}
	
	// SiteEmail 저장 및 호출
	public String getSiteEmail()
	{	return siteEmail;	}
	public void setSiteEmail(String siteEmail)
	{	this.siteEmail = siteEmail;	}
	
	// id 저장 및 호출
	public String getId()
	{	return id;	}
	public void setId(String id)
	{	this.id = id;	}
	// 회원가입 pro용
	public void setId(String idEmail, String siteEmail)
	{	this.id = idEmail+"@"+siteEmail;	}
	
	// pw 저장 및 호출
	public String getPw()
	{	return pw;	}
	public void setPw(String pw)
	{	this.pw = pw;	}
	
	// 이름 저장 및 호출
	public String getName()
	{	return name;	}
	public void setName(String name) 
	{	this.name = name;	}
	
	// 생년월일 저장 및 호출
	public String getBirth()
	{	return birth;	}
	public void setBirth(String birth) 
	{	this.birth = birth;	}
	// Timestamp타입을 String으로 변환
	public void setBirth(Timestamp birth)
	{	
		ValueCheck vc = new ValueCheck();
		this.birth = vc.timeToStr(birth);
	}
	
	// 닉네임 저장 및 호출
	public String getNick() 
	{	return nick;	}
	public void setNick(String nick)
	{	this.nick = nick;	}
	
	// 휴대전화 저장 및 호출
	public String getPhone()
	{	return phone;	}
	// 회원가입/회원수정에서 '-'기호가 있으면 그대로 DB에 저장, 없으면 '-'기호 붙여서 저장
	public void setPhone(String phone)
	{	
		ValueCheck vc = new ValueCheck();
		if(vc.charSpecial(phone,45))
		{	this.phone = phone;	}
		else
		{
			String ph1 = phone.substring(0, 3);
			String ph2 = phone.substring(3, 7);
			String ph3 = phone.substring(7);
			this.phone = ph1+"-"+ph2+"-"+ph3;
		}
	}
	
	// 회원상태 저장 및 호출
	public int getStatus()
	{	return status;	}
	public void setStatus(int status)
	{	this.status = status;	}
	
	// 가입날짜 저장 및 호출
	public Timestamp getReg()
	{	return reg;	}
	public void setReg(Timestamp reg) 
	{	this.reg = reg;	}
	
	// sql문
	public String getSql()
	{	return sql;	}
	public void setSql(String sql)
	{	this.sql = sql;	}
	
	public String getCol() 
	{	return col;}
	public void setCol(String col)
	{	this.col = col;}
	
	public String getSearchMember()
	{	return searchMember;	}
	public void setSearchMember(String searchMember) 
	{	this.searchMember = searchMember;}
	
	public ArrayList<MemberDTO> getSearchList()
	{	return searchList;	}
	public void setSearchList(ArrayList<MemberDTO> searchList)
	{	this.searchList = searchList;	}
}
