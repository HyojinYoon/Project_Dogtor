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
	
	
	// idEmail ���� �� ȣ��
	public String getIdEmail()
	{	return idEmail;	}
	public void setIdEmail(String idEmail)
	{	this.idEmail = idEmail; 	}
	
	// SiteEmail ���� �� ȣ��
	public String getSiteEmail()
	{	return siteEmail;	}
	public void setSiteEmail(String siteEmail)
	{	this.siteEmail = siteEmail;	}
	
	// id ���� �� ȣ��
	public String getId()
	{	return id;	}
	public void setId(String id)
	{	this.id = id;	}
	// ȸ������ pro��
	public void setId(String idEmail, String siteEmail)
	{	this.id = idEmail+"@"+siteEmail;	}
	
	// pw ���� �� ȣ��
	public String getPw()
	{	return pw;	}
	public void setPw(String pw)
	{	this.pw = pw;	}
	
	// �̸� ���� �� ȣ��
	public String getName()
	{	return name;	}
	public void setName(String name) 
	{	this.name = name;	}
	
	// ������� ���� �� ȣ��
	public String getBirth()
	{	return birth;	}
	public void setBirth(String birth) 
	{	this.birth = birth;	}
	// TimestampŸ���� String���� ��ȯ
	public void setBirth(Timestamp birth)
	{	
		ValueCheck vc = new ValueCheck();
		this.birth = vc.timeToStr(birth);
	}
	
	// �г��� ���� �� ȣ��
	public String getNick() 
	{	return nick;	}
	public void setNick(String nick)
	{	this.nick = nick;	}
	
	// �޴���ȭ ���� �� ȣ��
	public String getPhone()
	{	return phone;	}
	// ȸ������/ȸ���������� '-'��ȣ�� ������ �״�� DB�� ����, ������ '-'��ȣ �ٿ��� ����
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
	
	// ȸ������ ���� �� ȣ��
	public int getStatus()
	{	return status;	}
	public void setStatus(int status)
	{	this.status = status;	}
	
	// ���Գ�¥ ���� �� ȣ��
	public Timestamp getReg()
	{	return reg;	}
	public void setReg(Timestamp reg) 
	{	this.reg = reg;	}
	
	// sql��
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
