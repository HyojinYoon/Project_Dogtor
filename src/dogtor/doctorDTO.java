package dogtor;

import java.sql.Timestamp;
import dogtor.ValueCheck;

public class doctorDTO {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String phone;
	private String code;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	// Timestamp타입을 String으로 변환
	public void setBirth(Timestamp birth)
	{	
		ValueCheck vc = new ValueCheck();
		this.birth = vc.timeToStr(birth);
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
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
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
