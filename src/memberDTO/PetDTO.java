package memberDTO;

import java.sql.Timestamp;

import check.ValueCheck;

public class PetDTO
{
	private int num;				// ��ü �� number
	private String idEmail;			// �̸��� id
	private String siteEmail;		// �̸��� site
	private String id;				// id
	private String p_name;			// �� �̸�
	private String p_birth;			// �� ����
	private String p_gender;		// �� ����
	private String p_kind;			// �� ����
	private String p_etc;			// �� ��Ÿ����
	private int myp_num;			// ����ڰ� ������ �� ��
	private int status;				// ȸ������
	
	// ��ü �� number ���� �� ȣ��
	public int getNum()
	{	return num;	}
	public void setNum(int num)
	{	this.num = num;	}
	
	//idEmail ���� �� ȣ��
	public String getIdEmail()
	{	return idEmail;	}
	public void setIdEmail(String idEmail)
	{	this.idEmail = idEmail;	}
	
	//siteEmail ���� �� ȣ��
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
	
	// id DB��
	public void setIdDB(String id)
	{	
		this.id = id;	
		String[] email = this.id.split("@");
		this.idEmail = email[0];
		this.siteEmail = email[1];
	}
	public String getIdDB()
	{	return id;	}
	
	// �� �̸� ���� �� ȣ��
	public String getP_name()
	{	return p_name;	}
	public void setP_name(String p_name)
	{	
		if(p_name == null)
		{	p_name = "";	}
		this.p_name = p_name;	
	}
	
	// �� ������� ���� �� ȣ��
	public String getP_birth()
	{	return p_birth;	}
	// ȸ������ �� ���� ���� DB�� ������ �� ���
	public void setP_birth(String p_birth)
	{	this.p_birth = p_birth;	}
	// DB���� ���� ���� DTO�� ������ �� ���
	public void setP_birth(Timestamp p_birth)
	{	
		ValueCheck vc = new ValueCheck();
		this.p_birth = vc.timeToStr(p_birth);
	}
	
	// �� ���� ���� �� ȣ��
	public String getP_gender()
	{	return p_gender;	}
	public void setP_gender(String p_gender)
	{	this.p_gender = p_gender;	}
	
	// �� ǰ�� ���� �� ȣ��
	public String getP_kind()
	{	return p_kind;	}
	public void setP_kind(String p_kind)
	{	this.p_kind = p_kind;	}
	
	// �� ��Ÿ���� ���� �� ȣ��
	public String getP_etc()
	{	return p_etc;	}
	public void setP_etc(String p_etc)
	{	
		if(p_etc == null)
		{	p_etc ="";	}
		this.p_etc = p_etc;	
	}
	
	// ����ڰ� ������ �� �� ���� �� ȣ��
	public int getMyp_num()
	{	return myp_num	;	}
	public void setMyp_num(int myp_num)
	{	this.myp_num = myp_num;	}
	
	// ȸ������ ���� �� ȣ��
	public int getStatus()
	{	return status;	}
	public void setStatus(int status)
	{	this.status = status;	}
}


