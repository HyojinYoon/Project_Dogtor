package dogtor;
import dogtor.ConnectionDAO;
import dogtor.DTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DAO 
{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//���� ���� Ȯ�� �� ������ DAO
	//������ ���� ���ɽð� Ȯ�� �޼���
	public ArrayList<DTO> select(String code, String reserve_date){ //select DAO
		
		ArrayList<DTO> list = null;
		try 
		{
			conn=ConnectionDAO.getConnection(); //1,2�ܰ�
			String sql="select * from schedule where code=? and reserve_date=? and status=0 order by reserve_time"; //sql��
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,code);
			pstmt.setString(2,reserve_date);
			
			rs = pstmt.executeQuery(); //���� ���� ��� rs�� ����.
			list = new ArrayList<DTO>(); 
			while(rs.next()) 
			{ 
				DTO dto = new DTO();
				dto.setReserve_date(rs.getString("reserve_date"));
				dto.setReserve_time(rs.getString("reserve_time"));
				list.add(dto); //ArrayList add�޼���.
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);//����
		}
		
		return list;
	}
	//������ ���� ����
	public boolean selectCheck(String code, String reserve_date){ //select DAO
		
		boolean result = false;
		try 
		{
			conn=ConnectionDAO.getConnection(); //1,2�ܰ�
			String sql="select * from schedule where code=? and reserve_date=? and status=0 order by reserve_time"; //sql��
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,code);
			pstmt.setString(2,reserve_date);
			
			rs = pstmt.executeQuery(); //���� ���� ��� rs�� ����.
			while(rs.next())
			{ result = true;}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);//����
		}	
		return result;
	}
	
	
	
	
	//����Ȯ�� �޼���
	public ArrayList<DTO> select(String email, String code, String reserve_date, String reserve_time){ //select DAO
		
		ArrayList<DTO> list = null;
		try 
		{
			conn=ConnectionDAO.getConnection(); //1,2�ܰ�
			String sql="select * from reserve where email=? and code=? and reserve_date=? and reserve_time=?" ; //sql��
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,email);
			pstmt.setString(2,code);
			pstmt.setString(3,reserve_date);
			pstmt.setString(4,reserve_time);
			
			rs = pstmt.executeQuery(); //���� ���� ��� rs�� ����.
			list = new ArrayList<DTO>(); 
			while(rs.next()) { 
				DTO dto = new DTO();
				dto.setName(rs.getString("name")); //�ش� �÷��� ���� ������ DTO�� �ٽ� ���� �ִ´�.
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setReserve_date(rs.getString("reserve_date"));
				dto.setCode(rs.getString("code"));			
				dto.setSymptom(rs.getString("symptom"));
				dto.setReserve_time(rs.getString("reserve_time"));
				dto.setP_name(rs.getString("p_name")); //?�� ����?
				list.add(dto); //ArrayList add�޼���.
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);//����
		}
		
		return list;
	}
	
	//���� �����ͺ��̽� ���(�ش� �ǻ��� ��¥, �ð� ���� ���� ��� ���� ����)
	public void insert(DTO dto){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2�ܰ�
		String sql="select * from reserve where code=? and reserve_date=? and reserve_time=?" ; //sql��
		pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1,dto.getCode());
		pstmt.setString(2,dto.getReserve_date());
		pstmt.setString(3,dto.getReserve_time());
		rs = pstmt.executeQuery(); //���� ���� ��� rs�� ����.
		if(!rs.next()) {
			sql = "insert into reserve values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql); //���� �ۼ� //3�ܰ�
		 //DTO�� dto ��ü�� �̹� ������ ���ұ� ������ �״�� ��� �����ϴ�.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getReserve_date());
			pstmt.setString(5, dto.getReserve_time());	
			pstmt.setString(6, dto.getCode());
			pstmt.setString(7, dto.getP_name());
			pstmt.setString(8, dto.getSymptom());
			int member = Integer.parseInt(dto.getMember());
		pstmt.setInt(9, member);		
		pstmt.executeUpdate(); //4�ܰ� ����
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //����
		}
	}
	
	public void Delete(String code, String reserve_date, String reserve_time){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2�ܰ�
		String sql = "delete reserve where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //���� �ۼ� //3�ܰ�
		 //DTO�� dto ��ü�� �̹� ������ ���ұ� ������ �״�� ��� �����ϴ�.
		pstmt.setString(1, code);
		pstmt.setString(2, reserve_date);
		pstmt.setString(3, reserve_time);
	
		pstmt.executeUpdate(); //4�ܰ� ����
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //����
		}
	}
	
	
	//������ ���̺� ������Ʈ
	public void update(DTO dto){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2�ܰ�
		String sql = "update schedule set name=?,email=?,status=1 where reserve_date=? and reserve_time=? and code=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //���� �ۼ� //3�ܰ�
		 //DTO�� dto ��ü�� �̹� ������ ���ұ� ������ �״�� ��� �����ϴ�.
		pstmt.setString(1, dto.getName());
		pstmt.setString(2, dto.getEmail());
		pstmt.setString(3, dto.getReserve_date());
		pstmt.setString(4, dto.getReserve_time());
		pstmt.setString(5, dto.getCode());
		pstmt.executeUpdate(); //4�ܰ� ����
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //����
		}
	}
	
	//������ ������ ����� �޼���
	public void cancel(String code, String reserve_date, String reserve_time){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2�ܰ�
		String sql = "update schedule set name=null,email=null,status=0 where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //���� �ۼ� //3�ܰ�
		 //DTO�� dto ��ü�� �̹� ������ ���ұ� ������ �״�� ��� �����ϴ�.
		pstmt.setString(1, code);
		pstmt.setString(2, reserve_date);
		pstmt.setString(3, reserve_time);
		pstmt.executeUpdate(); //4�ܰ� ����
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //����
		}
	}
	
	//�����û ������ ���� �޼���, status�� 3����, ���� reason�� �߰��Ѵ�.
	public void Breaktime(String code, String reserve_date, String reserve_time, String reason){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2�ܰ�
		String sql = "update schedule set name=null,email=null,status=2,reason=? where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //���� �ۼ� //3�ܰ�
		 //DTO�� dto ��ü�� �̹� ������ ���ұ� ������ �״�� ��� �����ϴ�.
		pstmt.setString(1, reason);
		pstmt.setString(2, code);
		pstmt.setString(3, reserve_date);
		pstmt.setString(4, reserve_time);
		pstmt.executeUpdate(); //4�ܰ� ����
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //����
		}
	}
	//�����û ��� �޼��� schedule�� 0���� reason�� null�� �ٲ��ش�.
	public void BreaktimeCancle(String code, String reserve_date, String reserve_time){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2�ܰ�
		String sql = "update schedule set name=null,email=null,status=0,reason=null where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //���� �ۼ� //3�ܰ�
		 //DTO�� dto ��ü�� �̹� ������ ���ұ� ������ �״�� ��� �����ϴ�.
		pstmt.setString(1, code);
		pstmt.setString(2, reserve_date);
		pstmt.setString(3, reserve_time);
		pstmt.executeUpdate(); //4�ܰ� ����
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //����
		}
	}
	
	//email�� �������� ���� ���� ������ �޾ƿ´�.
	public int getReserveCount(String email) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where email=?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//code �������� �ǻ� ��� ���� ���� �޾ƿ´�.
	public int getDoctorReserveCount(String code) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where code=?");
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//�̸���, ��¥ �������� �ܷ� ���θ� Ȯ���Ѵ�.
	public int getReserveCheck(String reserve_date,String id) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where reserve_date=? and email=?");
		
			pstmt.setString(1, reserve_date);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	
	//��¥ �������� ��� �ǻ��� ���� �Ǽ��� �����´�.
	public int getAllDoctorReserveCount(String date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where reserve_date=?");
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//code �� ��¥�� �������� �ǻ� ��� ���� ���� �޾ƿ´�.
	public int getDoctorReserveCount(String code, String reserve_date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where code=? and reserve_date=?");
			pstmt.setString(1, code);
			pstmt.setString(2, reserve_date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//code�� �������� schedule ��� ���� �޾ƿ´�.
	public int getDoctorScheduleCount(String code) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from schedule where code=?");
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//�ش� ��¥ ���� �������� �޷� ǥ��
	public int getDoctorScheduleCount(String code, String reserve_date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from schedule where code=? and reserve_date=?");
			pstmt.setString(1, code);
			pstmt.setString(2, reserve_date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	
	//��ȸ�� ���� �޼��� - �ش� �������� �˻��Ǵ� ���� ������ ������ 1 ������ 0 ����
	public int getReserveCount(String email, String reserve_date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where email=? and reserve_date=?");
			pstmt.setString(1, email);
			pstmt.setString(2, reserve_date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //�ø� �̸� �־ �ȴ�. ==> �˻��� ù��° �÷��� ��.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}

	//������������Ʈ�� �޾ƿ��� �޼���
	public List getReserves(int start, int end, String email) throws Exception {
		List reserveList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select email,reserve_date,reserve_time,code,symptom,p_name,phone,rownum from reserve where rownum>=? and rownum<=? and email=?");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					pstmt.setString(3, email); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						reserveList = new ArrayList(end);  //select �� ���� ���� �ٽ� DTO�� ���� ����
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setSymptom(rs.getString("symptom"));
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setP_name(rs.getString("p_name"));	
							dto.setPhone(rs.getString("phone"));
							reserveList.add(dto);  //DTO�� �ٽ� ����Ʈ�� �߰��Ѵ�.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return reserveList; //����
	}
	//�ڵ� ���� �������� ����Ʈ �޾ƿ��� �޼���(��ø����Ʈ)
	public List getDoctorReserves(int start, int end, String code) throws Exception {
		List reserveList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select email,reserve_date,reserve_time,code,symptom,p_name,phone,rownum from reserve where rownum>=? and rownum<=? and code=?");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					pstmt.setString(3, code); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						reserveList = new ArrayList(end);  //select �� ���� ���� �ٽ� DTO�� ���� ����
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setSymptom(rs.getString("symptom"));
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setP_name(rs.getString("p_name"));	
							dto.setPhone(rs.getString("phone"));
							reserveList.add(dto);  //DTO�� �ٽ� ����Ʈ�� �߰��Ѵ�.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		return reserveList; //����
	}
	public List getAllDoctorReserves(int start, int end, String date) throws Exception {
		List reserveList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select email,reserve_date,reserve_time,code,symptom,p_name,phone,rownum from reserve where rownum>=? and rownum<=? and reserve_date=?");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					pstmt.setString(3, date); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						reserveList = new ArrayList(end);  //select �� ���� ���� �ٽ� DTO�� ���� ����
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setSymptom(rs.getString("symptom"));
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setP_name(rs.getString("p_name"));	
							dto.setPhone(rs.getString("phone"));
							reserveList.add(dto);  //DTO�� �ٽ� ����Ʈ�� �߰��Ѵ�.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		return reserveList; //����
	}
	
	
	//�ڵ� �� ��¥�� ���� �������� ����Ʈ �޾ƿ��� �޼���(��ø����Ʈ)
		public List getDoctorReserves(int start, int end, String code,String reserve_date) throws Exception {
			List reserveList=null;
			try {
				conn = ConnectionDAO.getConnection();
				pstmt = conn.prepareStatement(
						"select email,reserve_date,reserve_time,code,symptom,p_name,phone,rownum from reserve where rownum>=? and rownum<=? and code=? and reserve_date=?");
						pstmt.setInt(1, start); 
						pstmt.setInt(2, end); 
						pstmt.setString(3, code); 
						pstmt.setString(4, reserve_date); 

						rs = pstmt.executeQuery();
						if (rs.next()) {
							reserveList = new ArrayList(end);  //select �� ���� ���� �ٽ� DTO�� ���� ����
							do{ 
								DTO dto = new DTO();
								dto.setCode(rs.getString("code"));
								dto.setSymptom(rs.getString("symptom"));
								dto.setEmail(rs.getString("email"));
								dto.setReserve_date(rs.getString("reserve_date"));
								dto.setReserve_time(rs.getString("reserve_time"));
								dto.setP_name(rs.getString("p_name"));	
								dto.setPhone(rs.getString("phone"));
								reserveList.add(dto);  //DTO�� �ٽ� ����Ʈ�� �߰��Ѵ�.
							}while(rs.next());
						}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}

			return reserveList; //����
		}
	
	//�ڵ� �������� schedule ��� ��������(��ø����Ʈ)
	public List getDoctorSchedules(int start, int end, String code) throws Exception {
		List reserveList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select name,email,reserve_date,reserve_time,code,reason,status,rownum from (select * from (select * from schedule order by reserve_date) order by reserve_time) where rownum>=? and rownum<=? and code=?");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					pstmt.setString(3, code); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						reserveList = new ArrayList(end);  //select �� ���� ���� �ٽ� DTO�� ���� ����
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setStatus(rs.getInt("status")+"");
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setReason(rs.getString("reason"));	
							dto.setName(rs.getString("name"));
							reserveList.add(dto);  //DTO�� �ٽ� ����Ʈ�� �߰��Ѵ�.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return reserveList; //����
	}
	//�ڵ� �� ��¥�� �������� schedule ��� ��������(��ø����Ʈ)	
	public List getDoctorSchedules(int start, int end, String code, String reserve_date) throws Exception {
		List reserveList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select name,email,reserve_date,reserve_time,code,reason,status,rownum from (select * from (select * from schedule order by reserve_date) order by reserve_time) where rownum>=? and rownum<=? and code=? and reserve_date=?");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					pstmt.setString(3, code); 
					pstmt.setString(4, reserve_date); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						reserveList = new ArrayList(end);  //select �� ���� ���� �ٽ� DTO�� ���� ����
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setStatus(rs.getInt("status")+"");
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setReason(rs.getString("reason"));	
							dto.setName(rs.getString("name"));
							reserveList.add(dto);  //DTO�� �ٽ� ����Ʈ�� �߰��Ѵ�.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return reserveList; //����
	}
	
}

