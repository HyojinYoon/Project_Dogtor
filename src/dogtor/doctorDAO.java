package dogtor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dogtor.ConnectionDAO;



public class doctorDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void insertDoctor(doctorDTO dto) {
		try {
			conn = ConnectionDAO.getConnection(); // 1,2단계
			pstmt = conn.prepareStatement("insert into doctor values(?,?,?,?,?,?)");  // 3단계
	
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getCode());
			
			
			pstmt.executeUpdate();// 4단계
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
	}
	
	public ArrayList<doctorDTO> selectDoctor()
	{
		ArrayList<doctorDTO> list = null;
		try 
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from doctor";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<doctorDTO>();
			while(rs.next()) 
			{
				doctorDTO dto = new doctorDTO();
				dto.setPw(rs.getString("pw"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setPhone(rs.getString("phone"));
				dto.setCode(rs.getString("code"));
				list.add(dto);
			}
		}
		catch(Exception e) 
		{	e.printStackTrace();	}
		finally 
		{		ConnectionDAO.close(rs, pstmt, conn);	}
		return list;
	}
	
	
	public boolean loginCheck(String id, String pw) {
		boolean result = false; //기본값은 false로 설정, true일때는 무엇인지 나오게 설정

		try {
			
			conn=ConnectionDAO.getConnection(); //1,2단계 메서드 호출
			pstmt = conn.prepareStatement("select * from doctor where id=? and pw=?");
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			}finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}
			return result;
	}
	
	


	public doctorDTO getDoctor(String id, String name) {
		doctorDTO dto = null;
		try {
			conn=ConnectionDAO.getConnection();
		
			pstmt = conn.prepareStatement("select * from doctor where id=? and name=?");
			pstmt.setString(1, id);
			pstmt.setString(2, name);
		
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
			
				dto = new doctorDTO();
			
				dto.setPw(rs.getString("pw"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setPhone(rs.getString("phone"));
				dto.setCode(rs.getString("code"));
			
			
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return dto;
	}
	
	public doctorDTO getDoctor(String id) {
		doctorDTO dto = null;
		try {
			conn=ConnectionDAO.getConnection();
		
			pstmt = conn.prepareStatement("select * from doctor where id=?");
			pstmt.setString(1, id);
			
		
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
			
				dto = new doctorDTO();
			
				dto.setPw(rs.getString("pw"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getTimestamp("birth"));
				dto.setPhone(rs.getString("phone"));
				dto.setCode(rs.getString("code"));
			
			
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return dto;
	}
	
	public String searchId(String name, String phone)	
	{
		String id = null;
		String p1 = phone.substring(0, 3);
		String p2 = phone.substring(3,7);
		String p3 = phone.substring(7);
		String sql = "select id from doctor where name =? and phone =?";
		try
		{
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, p1+"-"+p2+"-"+p3);
			rs = pstmt.executeQuery();
			if(rs.next())
			{		id= rs.getString(("id"));	}
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
		return id;
	}
	
	
	public String searchPw(String id, String name, String phone)	
	{
		String pw = null;
		String p1 = phone.substring(0,3);
		String p2 = phone.substring(3,7);
		String p3 = phone.substring(7);
		String sql = "select pw from doctor where id =? and name =? and phone =?";
		try
		{
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, p1+"-"+p2+"-"+p3);
			rs = pstmt.executeQuery();
			if(rs.next())
			{	pw= rs.getString(("pw"));	}
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
		return pw;
		}
	
	public void updateDoctor(doctorDTO dto)
	{
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "update doctor set pw=?,birth=?,phone=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getBirth());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getId());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); }
	}

	public boolean deleteDoctor(String id, String pw) {
		boolean result = false;
	
		try {
			conn=ConnectionDAO.getConnection();
			String sql = "select * from doctor where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
	
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				sql = "delete from doctor where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				result = true;
			}
			}catch(Exception e) {
			
			}finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}
		return result;
	}

	public boolean codeCheck(String code)
	{
		boolean result = true;				
		try{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from doctor where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = false;
				}			
		}catch(Exception e){
			e.printStackTrace();	
			}
		finally{
			ConnectionDAO.close(rs, pstmt, conn);
			}
		return result;
	}


	public boolean idCheck(String id)
	{
		boolean result = true;			
		try{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from doctor where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				result = false;
				}			
		}catch(Exception e){	
			e.printStackTrace();	
			}finally{
				ConnectionDAO.close(rs, pstmt, conn); 	
				}
		return result;
	}


	public boolean idpw_Check(String id, String pw) { 
		boolean result = false;
		try{	
			conn = ConnectionDAO.getConnection();
			String sql = "select * from doctor where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				result = true;	
				}		
		}catch(Exception e){
			e.printStackTrace();
			}finally{
				ConnectionDAO.close(rs, pstmt, conn);
				}
		return result;
	}



}


	



















