package memberDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import memberDTO.MemberDTO;
import memberDTO.PetDTO;

public class MemberDAO
{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private static ArrayList<String> countListSQL = new ArrayList<String>();							// count ���� sql��
	private static ArrayList<String> sqlList = new ArrayList<String>();								// ���ù����� sql��
	MemberDTO searchDTO = new MemberDTO();
	
	// ȸ�� ��ü ��ȸ
	public ArrayList<MemberDTO> selectMember()
	{
		ArrayList<MemberDTO> list = null;
		try 
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from member order by status, reg asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<MemberDTO>();
			while(rs.next()) 
			{
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getTimestamp("birth"));
				dto.setNick(rs.getString("nick"));
				dto.setPhone(rs.getString("phone"));
				dto.setStatus(rs.getInt("status"));
				dto.setReg(rs.getTimestamp("reg"));
				list.add(dto);
			}
		}
		catch(Exception e) 
		{	e.printStackTrace();	}
		finally 
		{		ConnectionDAO.close(rs, pstmt, conn);	}
		return list;
	}
	
	// ȸ�� �� ��ü ��ȸ
	public ArrayList<PetDTO> selectPet()
	{
		ArrayList<PetDTO> list = null;
		try 
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from pet order by id asc, num asc, myp_num asc, status asc,";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<PetDTO>();
			while(rs.next()) 
			{
				PetDTO dto = new PetDTO();
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setP_name(rs.getString("p_name"));
				dto.setP_birth(rs.getTimestamp("p_birth"));
				dto.setP_gender(rs.getString("p_gender"));
				dto.setP_kind(rs.getString("p_kind"));
				dto.setP_etc(rs.getString("p_etc"));
				dto.setMyp_num(rs.getInt("myp_num"));
				dto.setStatus(rs.getInt("startus"));
				list.add(dto);
			}
		}
		catch(Exception e) 
		{	e.printStackTrace();	}
		finally 
		{		ConnectionDAO.close(rs, pstmt, conn);	}
		return list;
	}

	//�α��� üũ
	public boolean loginCheck(MemberDTO dto)
	{
		boolean result = false;
		try 
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from member where id=? and pw=? and status!=50";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{	result = true;	}
		}
		catch(Exception e) 
		{	e.printStackTrace();	}
		finally 
		{	ConnectionDAO.close(rs, pstmt, conn);	}
		return result;	
	}

	//ȸ������(join) : �� ���� �߰�
	public void insert(MemberDTO mem)
	{
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "insert into member values(?,?,?,?,?,?,1,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getIdEmail()+"@"+mem.getSiteEmail());
			pstmt.setString(2, mem.getPw());
			pstmt.setString(3, mem.getName());
			pstmt.setString(4, mem.getBirth());
			pstmt.setString(5, mem.getNick());
			pstmt.setString(6, mem.getPhone());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
	}
	
	// ȸ������(join) : ������ �߰�
	public void insert(ArrayList<PetDTO> petList)
	{
		try
		{	
			for(int i=0; i<petList.size(); i++)
			{	
				PetDTO p_dto = new PetDTO();
				p_dto = petList.get(i);
				conn = ConnectionDAO.getConnection();
				String sql = "insert into pet values(pet_seq.nextval,?,?,?,?,?,?,?,1)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p_dto.getId());
				pstmt.setString(2, p_dto.getP_name());
				pstmt.setString(3, p_dto.getP_birth());
				pstmt.setString(4, p_dto.getP_gender());
				pstmt.setString(5, p_dto.getP_kind());
				pstmt.setString(6, p_dto.getP_etc());
				pstmt.setInt(7, i+1);
				pstmt.executeUpdate();
			}
		
		}	
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
	}
	
	// ������ ã��
	public MemberDTO getMember(String id)
	{
		MemberDTO dto = null;
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				dto = new MemberDTO();
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getTimestamp("birth"));
				dto.setNick(rs.getString("nick"));
				dto.setPhone(rs.getString("phone"));
				dto.setStatus(rs.getInt("status"));
				dto.setReg(rs.getTimestamp("reg"));
			}
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
		return dto;
	}
	
	// ���� ������ ã��
	public ArrayList<PetDTO> getPet(String id)
	{
		ArrayList<PetDTO> myPetList = new ArrayList<PetDTO>();
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from pet where id=? order by myp_num asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				PetDTO p_dto = new PetDTO();
				p_dto.setP_name(rs.getString("p_name"));
				p_dto.setP_birth(rs.getTimestamp("p_birth"));
				p_dto.setP_gender(rs.getString("p_gender"));
				p_dto.setP_kind(rs.getString("p_kind"));
				p_dto.setP_etc(rs.getString("p_etc"));
				p_dto.setMyp_num(rs.getInt("myp_num"));
				p_dto.setStatus(rs.getInt("status"));
				p_dto.setNum(rs.getInt("num"));
				myPetList.add(p_dto);
			}
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
		return myPetList;
	}
	
	// ������ ����
	public void updateMember(MemberDTO m_dto)
	{
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "update member set pw=?,name=?,birth=?,phone=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_dto.getPw());
			pstmt.setString(2, m_dto.getName());
			pstmt.setString(3, m_dto.getBirth());
			pstmt.setString(4, m_dto.getPhone());
			pstmt.setString(5, m_dto.getId());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); }
	}
	
	// �ֿϵ��� ���� ����
	public void updatePet(ArrayList<PetDTO> petList)
	{
		try
		{	
			conn = ConnectionDAO.getConnection();
			for(int i=0; i<petList.size(); i++)
			{
				PetDTO p_dto = new PetDTO();
				p_dto = petList.get(i);
				String sql = "";
				if(p_dto.getNum() == 0)
				{
					sql = "insert into pet(num,id,p_name,p_birth,p_gender,p_kind,p_etc,myp_num,status) values(pet_seq.nextval,?,?,?,?,?,?,?,1)"; 
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, p_dto.getId());
					pstmt.setString(2, p_dto.getP_name());
					pstmt.setString(3, p_dto.getP_birth());
					pstmt.setString(4, p_dto.getP_gender());
					pstmt.setString(5, p_dto.getP_kind());
					pstmt.setString(6, p_dto.getP_etc());
					pstmt.setInt(7, i+1);
					pstmt.executeUpdate();
				}
				else
				{	
					sql = "update pet set p_name=?,p_birth=?,p_gender=?,p_kind=?,p_etc=?,myp_num=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, p_dto.getP_name());
					pstmt.setString(2, p_dto.getP_birth());
					pstmt.setString(3, p_dto.getP_gender());
					pstmt.setString(4, p_dto.getP_kind());
					pstmt.setString(5, p_dto.getP_etc());
					pstmt.setInt(6, i+1);
					pstmt.setInt(7, p_dto.getNum());
					pstmt.executeUpdate();
				}
			}
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); }
	}
	
	// �ֿϵ��� ���� ���� : DB�� ����� ������ ���û����ϴ� �޼���
	public void updatePetDel(String id, ArrayList<PetDTO> petList)
	{
		try
		{
			int count = 0;													// ������ ���� �ִ��� Ȯ���ϴ� ī��Ʈ( 0: ����, 1 : �ִ�)
			conn = ConnectionDAO.getConnection();
			String sql = "select * from pet where id=?";					// �ش� id�� pet table������ �˻��Ѵ�.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				PetDTO p_num = new PetDTO();								
				p_num.setNum(rs.getInt("num"));								// DB���� �ش� id�� �˻��� �ݷ������� num(������ȣ)�� ���� �� ȣ��
				int petNumDB = p_num.getNum();
				for(int i=0; i<petList.size(); i++)							// for������ ������ �ݷ����� ���� ���̺��� ���� ������ �ݺ��Ѵ�.
				{
					PetDTO p_dto = new PetDTO();							
					p_dto = petList.get(i);
					int petNumUser = p_dto.getNum();						// DB�� �˻��� �ݷ������� ������ȣ�� ȸ���� ������ �ݷ������� ������ȣ�� ���Ѵ�.
					if((petNumUser != 0) && (petNumUser == petNumDB))
					{	count++;	}										// ���� ���, DB�� �˻��� �ݷ������� ������ȣ�� ȸ���� ������ �ݷ������� ������ȣ�� ������ count�� 1�߰��Ѵ�.
				}
				if(count == 0)												// count�� 0�� �ݷ������� ������ �����Ѵ�.
				{	
					sql = "delete from pet where num=? and id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, petNumDB);
					pstmt.setString(2, id);
					pstmt.executeUpdate();
				}
				count = 0;
			}
		}
		catch(Exception e)
		{	e.printStackTrace();  }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn);  }
	}
	
	// �ֿϵ��� ���� ���� : DB�� ����� ������ ��ü�����ϴ� �޼���
	public void updatePetDel(String id)
	{
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "delete from pet where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{	e.printStackTrace();  }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn);  }
	}
	
	//ȸ�� Ż�� : status(ȸ�� ����) 50���� ����
	public boolean deleteMember(String id, String pw) 
	{ 
		boolean result = false;
		try 
		{	
			conn = ConnectionDAO.getConnection();
			String sql = "select * from member where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				sql = "update member set status=50 where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeQuery();
				result = true;
			}
		}
		catch(Exception e) 
		{}
		finally 
		{ConnectionDAO.close(rs, pstmt, conn);}
		return result;
	}
	
	//�� Ż�� : status(ȸ�� ����) 50���� ����
	public boolean deletePet(String id) 
	{ 
		boolean result = false;
		try 
		{	
			conn = ConnectionDAO.getConnection();
			String sql = "select * from pet where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				sql = "update pet set status=50 where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeQuery();
				result = true;
			}		
		}
		catch(Exception e) 
		{}
		finally 
		{ConnectionDAO.close(rs, pstmt, conn);}
		return result;
	}

	// nick �ߺ�Ȯ�� üũ
	public boolean nickCheck(String nick)
	{
		boolean result = true;				// nick ��� ����
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select * from member where nick=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if(rs.next())
			{	result = false;	}			// nick ��� �Ұ���
		}
		catch(Exception e)
		{	e.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
		return result;
	}
	
	// email �ߺ�Ȯ�� üũ
		public boolean emailCheck(String email)
		{
			boolean result = true;				// email ��� ����
			try
			{
				conn = ConnectionDAO.getConnection();
				String sql = "select * from member where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				if(rs.next())
				{	result = false;	}			// nick ��� �Ұ���
			}
			catch(Exception e)
			{	e.printStackTrace();	}
			finally
			{	ConnectionDAO.close(rs, pstmt, conn); 	}
			return result;
		}
	
	// ���̵�. ��й�ȣ Ȯ��
	public boolean idpw_Check(String id, String pw) 
	{ // ��ġ=true, ����ġ=false
		boolean result = false;
		try 
		{	
			conn = ConnectionDAO.getConnection();
			String sql = "select * from member where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) 
			{	result = true;	}		
		}
		catch(Exception e) 
		{	e.printStackTrace(); 	}
		finally 
		{ConnectionDAO.close(rs, pstmt, conn);}
		return result;
	}
	
	// ID ã��
	public String searchId(String name, String phone)	
	{
		String id = null;
		String p1 = phone.substring(0, 3);
		String p2 = phone.substring(3,7);
		String p3 = phone.substring(7);
		String sql = "select id from member where name =? and phone =?";
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
		
	//PW ã��
	public String searchPw(String id, String name, String phone)	
	{
		String pw = null;
		String p1 = phone.substring(0,3);
		String p2 = phone.substring(3,7);
		String p3 = phone.substring(7);
		String sql = "select pw from member where id =? and name =? and phone =?";
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
	
	// ������ ȸ�� Ż��(status 50����)
	public void adminDeleteMember(String id)
	{
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "update member set status=50 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{	e.printStackTrace(); }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); 	}
	}
	
	// �����ڷ� ��� ����(status 100����)
	public void adminChangeStatus(String id)
	{
		try
		{
			conn = ConnectionDAO.getConnection();
			String sql = "update member set status=100 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{	e.printStackTrace(); }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); }
	}
	
	// �긮��Ʈ�� ���ȣ�� �ٿ� ���ϴ� ����Ʈ�� ��� �޼���(1~10��, 11~20��)
	public ArrayList<PetDTO> getPetList(int start, int end) throws Exception
	{
		ArrayList<PetDTO> petCurrentList = new ArrayList<PetDTO>();
		try
		{
			conn = ConnectionDAO.getConnection();
			// start���� end���� pet������ �˻�
			String sql = "select * from "
					+ "(select num, id, p_name, p_birth, p_gender, p_kind, p_etc, myp_num, status, rownum r from "
					+ "(select * from pet order by id asc, myp_num desc, status asc) order by id asc, myp_num desc, status asc)"
					+ " where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				PetDTO p_dto = new PetDTO();				// petDTO ����
				p_dto.setNum(rs.getInt("num"));
				p_dto.setId(rs.getString("id"));
				p_dto.setP_name(rs.getString("p_name"));
				p_dto.setP_birth(rs.getTimestamp("p_birth"));
				p_dto.setP_gender(rs.getString("p_gender"));
				p_dto.setP_kind(rs.getString("p_kind"));
				p_dto.setP_etc(rs.getString("P_etc"));
				p_dto.setMyp_num(rs.getInt("myp_num"));
				p_dto.setStatus(rs.getInt("Status"));
				petCurrentList.add(p_dto);
			}
		}
		catch(Exception e)
		{	e.printStackTrace(); }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); }
		return petCurrentList;
	}
	
	// �˻��� �긮��Ʈ�� ���ȣ�� �ٿ� ���ϴ� ����Ʈ�� ��� �޼���(1~10��, 11~20��)
	public ArrayList<PetDTO> getPetList(String col, String searchPet, int start, int end) throws Exception
	{
		ArrayList<PetDTO> petCurrentList = new ArrayList<PetDTO>();
		try
		{
			conn = ConnectionDAO.getConnection();
			// start���� end���� pet������ �˻�
			String sql = "select * from "
					+ "(select num, id, p_name, p_birth, p_gender, p_kind, p_etc, myp_num, status, rownum r from "
					+ "(select * from pet where "+col+" like '%"+searchPet+"%' order by id asc, myp_num desc, status asc) "
					+ "order by id asc, myp_num desc, status asc) where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				PetDTO p_dto = new PetDTO();				// petDTO ����
				p_dto.setNum(rs.getInt("num"));
				p_dto.setId(rs.getString("id"));
				p_dto.setP_name(rs.getString("p_name"));
				p_dto.setP_birth(rs.getTimestamp("p_birth"));
				p_dto.setP_gender(rs.getString("p_gender"));
				p_dto.setP_kind(rs.getString("p_kind"));
				p_dto.setP_etc(rs.getString("P_etc"));
				p_dto.setMyp_num(rs.getInt("myp_num"));
				p_dto.setStatus(rs.getInt("Status"));
				petCurrentList.add(p_dto);
			}
		}
		catch(Exception e)
		{	e.printStackTrace(); }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); }
		return petCurrentList;
	}
	
	// ��ü �� table�� ���ڵ� �� ����
	public int getPetCount() throws Exception
	{
		int count=0;
		try
		{
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from pet");		// pet ���̺��� ��ü ���ڵ� �� �˻�
			rs = pstmt.executeQuery();
			if(rs.next())
			{	count = rs.getInt(1);	}
		}
		catch(Exception e)
		{	e.printStackTrace(); }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn);  }
		return count;
	}
	
	// �˻��� �� table�� ���ڵ� �� ����
		public int getPetCount(String col, String searchPet) throws Exception
		{
			int count=0;
			try
			{
				conn = ConnectionDAO.getConnection();
				String sql = "select count(*) from pet where "+col+" like '%"+searchPet+"%'";
				pstmt = conn.prepareStatement(sql);		// pet ���̺��� ��ü ���ڵ� �� �˻�
				rs = pstmt.executeQuery();
				if(rs.next())
				{	count = rs.getInt(1);	}
			}
			catch(Exception e)
			{	e.printStackTrace(); }
			finally
			{	ConnectionDAO.close(rs, pstmt, conn);  }
			return count;
		}
		
	// ������ ȸ����� �� ����
	public int getMemberCount() throws Exception 
	{
		int count = 0;
		try
		{
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from member"); // count : ����(��ɾ�)
			rs = pstmt.executeQuery();
			if (rs.next())
			{	count= rs.getInt(1);  }
		}
			catch(Exception e)
			{	e.printStackTrace();} 
			finally 
			{	ConnectionDAO.close(rs, pstmt, conn);}
			return count; 
		}
		
	// searchMemberForm - ������ ȸ����� �� ����
	public int getMemberCount(String col, String searchMember) throws Exception 
	{
		int count = 0;	// �˻��� ����
		try 
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select count(*) from member where "+col+" like '%"+searchMember+"%'";	// count : ����(��ɾ�)
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			if (rs.next())
			{	count = rs.getInt(1); }// �÷� �̸� �ܿ� ���ڷ� ���� �� - �˻��� ù��° �÷� = count
		}
		catch(Exception e)
		{		e.printStackTrace();	} 
		finally 
		{		ConnectionDAO.close(rs, pstmt, conn);	}
			return count; 
	}
	
	// �󼼰˻�
	public int getMemberCount(String col, String searchMember, int countSearch) throws Exception
	{
		int count = 0;
		try
		{
			conn = ConnectionDAO.getConnection();
			String range= "member)";		// ��ü����
			String select = "select count(*) from (";			// �˻��� ����� ���� ����
			String select2 = "select * from (";
		//	String where = "where "+col+" like '%"+searchMember+"%')";	// ���� ���� ���� where�� 
			String search = "";
			ArrayList<MemberDTO> searchList = new ArrayList<MemberDTO>();
			
		//	String result = "";
			
		//	if(countSearch > 1)							// �ι� �̻��� �˻�(���ù���)
		//	{	
		//		search = select2+countListSQL.get(countListSQL.size()-1)+where;
		//		result = select+search;
		//	}
		//	else if(countSearch == 1)
		//	{
		//		search = select2+range+where;		// ù��° ����
		//		result = select+search;				// ù��° �˻�
		//	}
		
			if(countSearch >= searchList.size())
			{
				for(int i=0; i<countSearch; i++)			// col�� searchMember �˻� �׸� ����
				{	
					searchDTO.setCol(col);
					searchDTO.setSearchMember(searchMember);
					if(countSearch > 1)
					{	searchList = searchDTO.getSearchList();	}
					searchList.add(searchDTO);
					searchDTO.setSearchList(searchList);		
				}
			
				for(int i=0; i<countSearch; i++)
				{
					searchList = searchDTO.getSearchList();
					//System.out.println(searchList.get(i));
					MemberDTO getDTO = searchList.get(i);
					col = getDTO.getCol();
					searchMember = getDTO.getSearchMember();
					range = select2+range+"where "+col+" like '%"+searchMember+"%')";	
				}
			}
			else
			{
				searchList = searchDTO.getSearchList();
				for(int i=countSearch; i <searchList.size(); i++)
				{	searchList.remove(i);	}
				searchDTO.setSearchList(searchList);
			}
			search = select+range;
			pstmt = conn.prepareStatement(search);
			rs = pstmt.executeQuery();
			if(rs.next())
			{	count = rs.getInt(1);	}
		//	if(count > 0)
		//	{	countListSQL.add(search);	}
		}
		catch(Exception e)
		{	e.printStackTrace(); }
		finally
		{	ConnectionDAO.close(rs, pstmt, conn); }
		return count;
	}
		
	// ������ ȸ�� ��� 10���� ��ȸ
	public ArrayList<MemberDTO> getMembers(int start, int end) throws Exception 
	{ //1~10�� ��ü �� ��ȸ
		ArrayList<MemberDTO> memberList=new ArrayList<MemberDTO>();
		try 
		{
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement( //��øselect�� - start���� end���� member������ �˻�
					"select * from "
					+ "(select id,pw,name,birth,nick,phone,status,reg,rownum r from "
					+ "(select * from member order by status asc, reg desc) "
					+ "order by status asc, reg desc ) where r >= ? and r <= ? ");
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 

			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getTimestamp("birth"));
				dto.setNick(rs.getString("nick"));
				dto.setPhone(rs.getString("phone"));
				dto.setStatus(rs.getInt("status"));
				dto.setReg(rs.getTimestamp("reg"));
				memberList.add(dto);
			}
		} 
		catch(Exception ex)
		{	ex.printStackTrace();}
		finally 
		{	ConnectionDAO.close(rs, pstmt, conn);}
			return memberList;
	}
		
	// searchMemberForm - ������ ȸ�� ��� 10���� ��ȸ
	public ArrayList<MemberDTO> getMembers(String col, String searchMember, int start, int end) throws Exception 
	{ 
		ArrayList<MemberDTO> memberList=new ArrayList<MemberDTO>();
		try 
		{
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement( //��øselect��
					"select * from (select id,pw,name,birth,nick,phone,status,reg,rownum r from "
					+ "(select * from member where "+col+" like '%"+searchMember+"%' order by status asc, reg desc) "
					+ "order by status asc, reg desc ) where r >= ? and r <= ? ");
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 

			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getTimestamp("birth"));
				dto.setNick(rs.getString("nick"));
				dto.setPhone(rs.getString("phone"));
				dto.setStatus(rs.getInt("status"));
				dto.setReg(rs.getTimestamp("reg"));
				memberList.add(dto);
			}
		} 
		catch(Exception ex) 
		{	ex.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn);	}
		return memberList;
	}
	
	// searchMemberForm - ������ ȸ�� ��� 10���� ��ȸ(���ù���)
	public ArrayList<MemberDTO> getMembers(String col, String searchMember, int countSearch, int start, int end) throws Exception 
	{ 
		ArrayList<MemberDTO> memberList=new ArrayList<MemberDTO>();
		try 
		{
			conn = ConnectionDAO.getConnection();
			String select = "select * from (select id,pw,name,birth,nick,phone,status,reg,rownum r from (";			// rownum �ο��Ͽ� �˻�
			String select2 = "select * from (";						// ��� ������ �˻�
			String where = "where r >= ? and r <= ?";				// start���� end���� �˻���� ���
			String rowOrder = "order by status asc, reg desc)";		// ����
			String range = "member)";			// ��ü�˻�
			String whereSel = "where "+col+" like '%"+searchMember+"%' order by status asc, reg desc)";	//���ϴ� �˻�
			String search = "";				
			String result = ""; 		// 1��° sql��
			ArrayList<MemberDTO> searchList = new ArrayList<MemberDTO>();
					
		//	if(countSearch > 1)
		//	{
		//		search = select2+sqlList.get(sqlList.size()-1)+whereSel;		// n��° ����
		//		result = select+search+rowOrder+where;
		//	}
		//	else if(countSearch == 1)
		//	{
		//		search = select2+range+whereSel;				// 1��° ����
		//		result = select+search+rowOrder+where;							// 1��° �˻�
		//	}
			
		//	for(int i=0; i<countSearch; i++)			// col�� searchMember �˻� �׸� ����
		//	{	
		//		dto.setCol(col);
		//		dto.setSearchMember(searchMember);
		//		searchList = dto.getSearchList();
		//		searchList.add(dto);
		//		dto.setSearchList(searchList);		
		//	}
			
			for(int i=0; i<countSearch; i++)
			{
				searchList = searchDTO.getSearchList();	
				MemberDTO getDTO = searchList.get(i);
				col = getDTO.getCol();
				searchMember = getDTO.getSearchMember();
				range = select2+range+"where "+col+" like '%"+searchMember+"%')";	
			}
			search = select+range+rowOrder+where;
			
			
			pstmt = conn.prepareStatement(search);
			
		//	pstmt = conn.prepareStatement( //��øselect��
		//			"select * from "
		//			+ "(select id,pw,name,birth,nick,phone,status,reg,rownum r from "
		//			+ "(select * from (member) where "+col+" like '%"+searchMember+"%' order by status asc, reg desc) "
		//					+ "order by status asc, reg desc)"
		//					+ "where r >= ? and r <= ? ");
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 

			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getTimestamp("birth"));
				dto.setNick(rs.getString("nick"));
				dto.setPhone(rs.getString("phone"));
				dto.setStatus(rs.getInt("status"));
				dto.setReg(rs.getTimestamp("reg"));
				memberList.add(dto);
			}
	//		if(memberList.size() > 0)
	//		{	sqlList.add(search);	}
			
		} 
		catch(Exception ex) 
		{	ex.printStackTrace();	}
		finally
		{	ConnectionDAO.close(rs, pstmt, conn);	}
		return memberList;
	}
}