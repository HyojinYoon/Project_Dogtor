package memberDTO;

import java.sql.Timestamp;

import check.ValueCheck;

public class PetDTO
{
	private int num;				// 전체 펫 number
	private String idEmail;			// 이메일 id
	private String siteEmail;		// 이메일 site
	private String id;				// id
	private String p_name;			// 펫 이름
	private String p_birth;			// 펫 생일
	private String p_gender;		// 펫 성별
	private String p_kind;			// 펫 종류
	private String p_etc;			// 펫 기타사항
	private int myp_num;			// 사용자가 저장한 펫 수
	private int status;				// 회원상태
	
	// 전체 펫 number 저장 및 호출
	public int getNum()
	{	return num;	}
	public void setNum(int num)
	{	this.num = num;	}
	
	//idEmail 저장 및 호출
	public String getIdEmail()
	{	return idEmail;	}
	public void setIdEmail(String idEmail)
	{	this.idEmail = idEmail;	}
	
	//siteEmail 저장 및 호출
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
	
	// id DB용
	public void setIdDB(String id)
	{	
		this.id = id;	
		String[] email = this.id.split("@");
		this.idEmail = email[0];
		this.siteEmail = email[1];
	}
	public String getIdDB()
	{	return id;	}
	
	// 펫 이름 저장 및 호출
	public String getP_name()
	{	return p_name;	}
	public void setP_name(String p_name)
	{	
		if(p_name == null)
		{	p_name = "";	}
		this.p_name = p_name;	
	}
	
	// 펫 생년월일 저장 및 호출
	public String getP_birth()
	{	return p_birth;	}
	// 회원가입 때 받은 값을 DB에 저장할 때 사용
	public void setP_birth(String p_birth)
	{	this.p_birth = p_birth;	}
	// DB에서 받은 값을 DTO에 저장할 때 사용
	public void setP_birth(Timestamp p_birth)
	{	
		ValueCheck vc = new ValueCheck();
		this.p_birth = vc.timeToStr(p_birth);
	}
	
	// 펫 성별 저장 및 호출
	public String getP_gender()
	{	return p_gender;	}
	public void setP_gender(String p_gender)
	{	this.p_gender = p_gender;	}
	
	// 펫 품종 저장 및 호출
	public String getP_kind()
	{	return p_kind;	}
	public void setP_kind(String p_kind)
	{	this.p_kind = p_kind;	}
	
	// 펫 기타사항 저장 및 호출
	public String getP_etc()
	{	return p_etc;	}
	public void setP_etc(String p_etc)
	{	
		if(p_etc == null)
		{	p_etc ="";	}
		this.p_etc = p_etc;	
	}
	
	// 사용자가 저장한 펫 수 저장 및 호출
	public int getMyp_num()
	{	return myp_num	;	}
	public void setMyp_num(int myp_num)
	{	this.myp_num = myp_num;	}
	
	// 회원상태 저장 및 호출
	public int getStatus()
	{	return status;	}
	public void setStatus(int status)
	{	this.status = status;	}
}


