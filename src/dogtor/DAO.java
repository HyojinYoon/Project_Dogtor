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
	
	//예약 정보 확인 및 예약폼 DAO
	//선생님 진료 가능시간 확인 메서드
	public ArrayList<DTO> select(String code, String reserve_date){ //select DAO
		
		ArrayList<DTO> list = null;
		try 
		{
			conn=ConnectionDAO.getConnection(); //1,2단계
			String sql="select * from schedule where code=? and reserve_date=? and status=0 order by reserve_time"; //sql문
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,code);
			pstmt.setString(2,reserve_date);
			
			rs = pstmt.executeQuery(); //쿼리 실행 결과 rs에 저장.
			list = new ArrayList<DTO>(); 
			while(rs.next()) 
			{ 
				DTO dto = new DTO();
				dto.setReserve_date(rs.getString("reserve_date"));
				dto.setReserve_time(rs.getString("reserve_time"));
				list.add(dto); //ArrayList add메서드.
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);//종료
		}
		
		return list;
	}
	//스케쥴 가능 유무
	public boolean selectCheck(String code, String reserve_date){ //select DAO
		
		boolean result = false;
		try 
		{
			conn=ConnectionDAO.getConnection(); //1,2단계
			String sql="select * from schedule where code=? and reserve_date=? and status=0 order by reserve_time"; //sql문
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,code);
			pstmt.setString(2,reserve_date);
			
			rs = pstmt.executeQuery(); //쿼리 실행 결과 rs에 저장.
			while(rs.next())
			{ result = true;}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);//종료
		}	
		return result;
	}
	
	
	
	
	//예약확인 메서드
	public ArrayList<DTO> select(String email, String code, String reserve_date, String reserve_time){ //select DAO
		
		ArrayList<DTO> list = null;
		try 
		{
			conn=ConnectionDAO.getConnection(); //1,2단계
			String sql="select * from reserve where email=? and code=? and reserve_date=? and reserve_time=?" ; //sql문
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,email);
			pstmt.setString(2,code);
			pstmt.setString(3,reserve_date);
			pstmt.setString(4,reserve_time);
			
			rs = pstmt.executeQuery(); //쿼리 실행 결과 rs에 저장.
			list = new ArrayList<DTO>(); 
			while(rs.next()) { 
				DTO dto = new DTO();
				dto.setName(rs.getString("name")); //해당 컬럼의 값을 꺼내고 DTO에 다시 집어 넣는다.
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setReserve_date(rs.getString("reserve_date"));
				dto.setCode(rs.getString("code"));			
				dto.setSymptom(rs.getString("symptom"));
				dto.setReserve_time(rs.getString("reserve_time"));
				dto.setP_name(rs.getString("p_name")); //?왜 오류?
				list.add(dto); //ArrayList add메서드.
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);//종료
		}
		
		return list;
	}
	
	//에약 데이터베이스 등록(해당 의사의 날짜, 시간 예약 없을 경우 예약 가능)
	public void insert(DTO dto){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2단계
		String sql="select * from reserve where code=? and reserve_date=? and reserve_time=?" ; //sql문
		pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1,dto.getCode());
		pstmt.setString(2,dto.getReserve_date());
		pstmt.setString(3,dto.getReserve_time());
		rs = pstmt.executeQuery(); //쿼리 실행 결과 rs에 저장.
		if(!rs.next()) {
			sql = "insert into reserve values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql); //쿼리 작성 //3단계
		 //DTO를 dto 객체로 이미 생성해 놓았기 때문에 그대로 사용 가능하다.
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
		pstmt.executeUpdate(); //4단계 실행
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //종료
		}
	}
	
	public void Delete(String code, String reserve_date, String reserve_time){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2단계
		String sql = "delete reserve where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //쿼리 작성 //3단계
		 //DTO를 dto 객체로 이미 생성해 놓았기 때문에 그대로 사용 가능하다.
		pstmt.setString(1, code);
		pstmt.setString(2, reserve_date);
		pstmt.setString(3, reserve_time);
	
		pstmt.executeUpdate(); //4단계 실행
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //종료
		}
	}
	
	
	//스케쥴 테이블 업데이트
	public void update(DTO dto){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2단계
		String sql = "update schedule set name=?,email=?,status=1 where reserve_date=? and reserve_time=? and code=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //쿼리 작성 //3단계
		 //DTO를 dto 객체로 이미 생성해 놓았기 때문에 그대로 사용 가능하다.
		pstmt.setString(1, dto.getName());
		pstmt.setString(2, dto.getEmail());
		pstmt.setString(3, dto.getReserve_date());
		pstmt.setString(4, dto.getReserve_time());
		pstmt.setString(5, dto.getCode());
		pstmt.executeUpdate(); //4단계 실행
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //종료
		}
	}
	
	//선생님 스케쥴 재생성 메서드
	public void cancel(String code, String reserve_date, String reserve_time){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2단계
		String sql = "update schedule set name=null,email=null,status=0 where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //쿼리 작성 //3단계
		 //DTO를 dto 객체로 이미 생성해 놓았기 때문에 그대로 사용 가능하다.
		pstmt.setString(1, code);
		pstmt.setString(2, reserve_date);
		pstmt.setString(3, reserve_time);
		pstmt.executeUpdate(); //4단계 실행
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //종료
		}
	}
	
	//외출신청 스케쥴 조정 메서드, status를 3으로, 외출 reason을 추가한다.
	public void Breaktime(String code, String reserve_date, String reserve_time, String reason){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2단계
		String sql = "update schedule set name=null,email=null,status=2,reason=? where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //쿼리 작성 //3단계
		 //DTO를 dto 객체로 이미 생성해 놓았기 때문에 그대로 사용 가능하다.
		pstmt.setString(1, reason);
		pstmt.setString(2, code);
		pstmt.setString(3, reserve_date);
		pstmt.setString(4, reserve_time);
		pstmt.executeUpdate(); //4단계 실행
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //종료
		}
	}
	//외출신청 취소 메서드 schedule을 0으로 reason을 null로 바꿔준다.
	public void BreaktimeCancle(String code, String reserve_date, String reserve_time){//insert DAO
		try{
		conn = ConnectionDAO.getConnection(); //1,2단계
		String sql = "update schedule set name=null,email=null,status=0,reason=null where code=? and reserve_date=? and reserve_time=?";
		System.out.println();
		pstmt = conn.prepareStatement(sql); //쿼리 작성 //3단계
		 //DTO를 dto 객체로 이미 생성해 놓았기 때문에 그대로 사용 가능하다.
		pstmt.setString(1, code);
		pstmt.setString(2, reserve_date);
		pstmt.setString(3, reserve_time);
		pstmt.executeUpdate(); //4단계 실행
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn); //종료
		}
	}
	
	//email을 기준으로 예약 정보 갯수를 받아온다.
	public int getReserveCount(String email) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where email=?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//code 기준으로 의사 모든 진료 갯수 받아온다.
	public int getDoctorReserveCount(String code) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where code=?");
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//이메일, 날짜 기준으로 잔료 여부를 확인한다.
	public int getReserveCheck(String reserve_date,String id) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where reserve_date=? and email=?");
		
			pstmt.setString(1, reserve_date);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	
	//날짜 기준으로 모든 의사의 진료 건수를 가져온다.
	public int getAllDoctorReserveCount(String date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where reserve_date=?");
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//code 와 날짜를 기준으로 의사 모든 진료 갯수 받아온다.
	public int getDoctorReserveCount(String code, String reserve_date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where code=? and reserve_date=?");
			pstmt.setString(1, code);
			pstmt.setString(2, reserve_date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//code를 기준으로 schedule 목록 갯수 받아온다.
	public int getDoctorScheduleCount(String code) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from schedule where code=?");
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//해당 날짜 예약 가능한지 달력 표시
	public int getDoctorScheduleCount(String code, String reserve_date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from schedule where code=? and reserve_date=?");
			pstmt.setString(1, code);
			pstmt.setString(2, reserve_date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	
	//비회원 전용 메서드 - 해당 내역으로 검색되는 예약 내역이 있으면 1 없으면 0 리턴
	public int getReserveCount(String email, String reserve_date) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from reserve where email=? and reserve_date=?");
			pstmt.setString(1, email);
			pstmt.setString(2, reserve_date);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x= rs.getInt(1);  //컬림 이름 넣어도 된다. ==> 검색된 첫번째 컬럼의 값.
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}

	//예약정보리스트를 받아오는 메서드
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
						reserveList = new ArrayList(end);  //select 로 나온 값을 다시 DTO에 집어 넣음
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setSymptom(rs.getString("symptom"));
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setP_name(rs.getString("p_name"));	
							dto.setPhone(rs.getString("phone"));
							reserveList.add(dto);  //DTO를 다시 리스트에 추가한다.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return reserveList; //리턴
	}
	//코드 기준 예약정보 리스트 받아오는 메서드(중첩셀렉트)
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
						reserveList = new ArrayList(end);  //select 로 나온 값을 다시 DTO에 집어 넣음
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setSymptom(rs.getString("symptom"));
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setP_name(rs.getString("p_name"));	
							dto.setPhone(rs.getString("phone"));
							reserveList.add(dto);  //DTO를 다시 리스트에 추가한다.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		return reserveList; //리턴
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
						reserveList = new ArrayList(end);  //select 로 나온 값을 다시 DTO에 집어 넣음
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setSymptom(rs.getString("symptom"));
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setP_name(rs.getString("p_name"));	
							dto.setPhone(rs.getString("phone"));
							reserveList.add(dto);  //DTO를 다시 리스트에 추가한다.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		return reserveList; //리턴
	}
	
	
	//코드 및 날짜를 기준 예약정보 리스트 받아오는 메서드(중첩셀렉트)
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
							reserveList = new ArrayList(end);  //select 로 나온 값을 다시 DTO에 집어 넣음
							do{ 
								DTO dto = new DTO();
								dto.setCode(rs.getString("code"));
								dto.setSymptom(rs.getString("symptom"));
								dto.setEmail(rs.getString("email"));
								dto.setReserve_date(rs.getString("reserve_date"));
								dto.setReserve_time(rs.getString("reserve_time"));
								dto.setP_name(rs.getString("p_name"));	
								dto.setPhone(rs.getString("phone"));
								reserveList.add(dto);  //DTO를 다시 리스트에 추가한다.
							}while(rs.next());
						}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}

			return reserveList; //리턴
		}
	
	//코드 기준으로 schedule 목록 가져오기(중첩셀렉트)
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
						reserveList = new ArrayList(end);  //select 로 나온 값을 다시 DTO에 집어 넣음
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setStatus(rs.getInt("status")+"");
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setReason(rs.getString("reason"));	
							dto.setName(rs.getString("name"));
							reserveList.add(dto);  //DTO를 다시 리스트에 추가한다.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return reserveList; //리턴
	}
	//코드 및 날짜를 기준으로 schedule 목록 가져오기(중첩셀렉트)	
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
						reserveList = new ArrayList(end);  //select 로 나온 값을 다시 DTO에 집어 넣음
						do{ 
							DTO dto = new DTO();
							dto.setCode(rs.getString("code"));
							dto.setStatus(rs.getInt("status")+"");
							dto.setEmail(rs.getString("email"));
							dto.setReserve_date(rs.getString("reserve_date"));
							dto.setReserve_time(rs.getString("reserve_time"));
							dto.setReason(rs.getString("reason"));	
							dto.setName(rs.getString("name"));
							reserveList.add(dto);  //DTO를 다시 리스트에 추가한다.
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return reserveList; //리턴
	}
	
}

