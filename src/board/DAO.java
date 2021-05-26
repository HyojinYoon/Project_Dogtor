package board;
import java.sql.*;
import java.util.*; 
import board.ConnectionDAO;
import board.DTO;

public class DAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//문의게시판 게시글 삽입 (writePro에서 사용) - 인선
	public void insertArticleQna(DTO dto) throws Exception {
		int num=dto.getNum();
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		int number=0;
		
		String sql="";
		try {
			conn = ConnectionDAO.getConnection(); 
			pstmt = conn.prepareStatement("select max(num) from Board");
			rs = pstmt.executeQuery();
			if (rs.next()) 
				number=rs.getInt(1)+1;	
			else
				number=1; 
			if (num!=0) 
			{ 
				sql="update Board set re_step=re_step+1 where ref= ? and re_step> ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;
			}else{ 
				ref=number;
				re_step=0;
				re_level=0;
			}
 
			sql = "insert into Board(num, writer,subject,content,reg,";
			sql+="readcount, ref,re_step,re_level, head, bs) values(Board_seq.NEXTVAL,?,?,?,sysdate,?,?,?,?,'문의',2)";
				pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getReadcount());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			
			
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
	}
	
	//자유게시판 게시글삽입
	public void insertArticleFree(DTO dto) throws Exception {
		int num=dto.getNum();
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		int number=0;
		
		String sql="";
		try {
			conn = ConnectionDAO.getConnection(); 
			pstmt = conn.prepareStatement("select max(num) from Board");
			rs = pstmt.executeQuery();
			if (rs.next()) 
				number=rs.getInt(1)+1;	
			else
				number=1; 
			if (num!=0) 
			{ 
				sql="update Board set re_step=re_step+1 where ref= ? and re_step> ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;
			}else{ 
				ref=number;
				re_step=0;
				re_level=0;
			}
 
			sql = "insert into Board(num, writer,subject,content,reg,";
			sql+="readcount, ref,re_step,re_level, head, bs) values(Board_seq.NEXTVAL,?,?,?,sysdate,?,?,?,?,?,3)";
				pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getReadcount());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, dto.getHead());
			
			
			
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
	}
	
	
	
	//문의게시판 게시글 갯수 확인 (list2.jsp에서 사용) - 인선
	public int getArticleCountQna() throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from Board where bs=2");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x; 
	}
	public int getArticleCountFree() throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from Board where bs=3");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x; 
	}
	
	//Qna myList 게시글 갯수체크 (myListQna에서 사용) - 인선
	public int getArticleCountQna(String nick) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from Board where writer=? and bs=2");
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x; 
	}
	
	//freeMyList 게시글 갯수체크 (freeMyList.jsp에서 사용) - 인선
	public int getArticleCountFree(String nick) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from Board where writer=? and bs=3");
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x; 
	}
	
    //문의게시판 게시글 10개씩 짤라서 번호부여 후, 재정렬 (list2.jsp에서 사용) - 인선
	public List getArticlesQna(int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num, writer,subject,content,reg, readcount, ref,re_step,re_level, head, bs, r "+
					"from (select num, writer,subject,content,reg, readcount, ref,re_step,re_level, head, bs,  rownum r " +
					"from (select num, writer,subject,content,reg, readcount, ref,re_step,re_level, head, bs " +
					"from Board where bs=2 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							DTO article= new DTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setContent(rs.getString("content"));
							article.setReg(rs.getTimestamp("reg"));
							article.setReadcount(rs.getInt("readcount"));
							article.setRef(rs.getInt("ref"));
							article.setRe_step(rs.getInt("re_step"));
							article.setRe_level(rs.getInt("re_level"));
							article.setHead(rs.getString("head"));
							article.setBs(rs.getInt("bs"));
						
							articleList.add(article); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return articleList;
	}
	
	//자유게시판 게시글 10개씩 짤라서 list로 리턴
	public List getArticlesFree(int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num, writer,subject,content,reg, readcount, ref,re_step,re_level, head, bs, r "+
					"from (select num, writer,subject,content,reg, readcount, ref,re_step,re_level, head, bs, rownum r " +
					"from (select num, writer,subject,content,reg, readcount, ref,re_step,re_level, head, bs " +
					"from Board where bs=3 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							DTO article= new DTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setContent(rs.getString("content"));
							article.setReg(rs.getTimestamp("reg"));
							article.setReadcount(rs.getInt("readcount"));
							article.setRef(rs.getInt("ref"));
							article.setRe_step(rs.getInt("re_step"));
							article.setRe_level(rs.getInt("re_level"));
							article.setHead(rs.getString("head"));
							article.setBs(rs.getInt("bs"));
							
							articleList.add(article); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return articleList;
	}
	
	//Qna searchList 리스트나열 (searchLsitQna에서 사용) - 인선
	public List getArticles(String col, String search, int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, r "+
					"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs, rownum r " +
					"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs " +
					"from board where bs=2 and "+col+" like '%"+search+"%' order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							DTO article= new DTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setContent(rs.getString("content"));
							article.setReg(rs.getTimestamp("reg"));
							article.setReadcount(rs.getInt("readcount"));
							article.setRef(rs.getInt("ref"));
							article.setRe_step(rs.getInt("re_step"));
							article.setRe_level(rs.getInt("re_level"));
							article.setHead(rs.getString("head"));
							article.setBs(rs.getInt("bs"));
							
							
							articleList.add(article); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return articleList;
	}
	
	//[재검색용]free searchList 리스트나열
	public ArrayList<DTO> getArticlesFree(String col, String search, int start, int end, int countSearch) throws Exception {
		ArrayList<DTO> articleList = new ArrayList<DTO>();
		try {
			conn = ConnectionDAO.getConnection();
			String select = "select * from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs, rownum r from (";
			//검색내용에 rownum부여
			String select2 = "select * from (";  //모든 데이터 검색
			String where = "where r >= ? and r <= ? "; //start부터 end까지 검색결과 출력
			String rowOrder = "order by ref desc, re_step asc)";  //정렬
			String range = "Board)";  //테이블에서 검색
			String whereSel = "where bs=3 and "+col+" like '%"+search+"%' order by ref desc, re_step asc)";   //원하는 검색
			String searchRange = select2+range+whereSel;
			String result = select+searchRange+rowOrder+where;
			
			if(countSearch >1) {
				for(int i=1; i<countSearch ; i++) {
					searchRange = select2+searchRange+whereSel;
				}
				result = select2+searchRange+rowOrder+where;
				//result = select+searchRange+rowOrder+where;
			}
			pstmt = conn.prepareStatement(result);
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
			    DTO article= new DTO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setReg(rs.getTimestamp("reg"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setHead(rs.getString("head"));
				article.setBs(rs.getInt("bs"));
							
				articleList.add(article); 
			}
				
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return articleList;
		}
		
		
		//free searchList 리스트나열 (freeSearchList.jsp에서 사용) - 인선
		public List getArticlesFree(String col, String search, int start, int end) throws Exception {
			List articleList=null;
			try {
				conn = ConnectionDAO.getConnection();
				pstmt = conn.prepareStatement(
						"select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, r "+
						"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs, rownum r " +
						"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs " +
						"from board where bs=3 and "+col+" like '%"+search+"%' order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
						
				pstmt.setInt(1, start); 
				pstmt.setInt(2, end); 
			    rs = pstmt.executeQuery();
			    if (rs.next()) {
					articleList = new ArrayList(end); 
					do{ 
						DTO article= new DTO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setSubject(rs.getString("subject"));
						article.setContent(rs.getString("content"));
						article.setReg(rs.getTimestamp("reg"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setRe_step(rs.getInt("re_step"));
						article.setRe_level(rs.getInt("re_level"));
						article.setHead(rs.getString("head"));
						article.setBs(rs.getInt("bs"));
								
						articleList.add(article); 
				   }while(rs.next());
			    }
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}
			return articleList;
		}
		
		//free searchList 연관검색리스트나열 (freeSearchList.jsp에서 사용) - 인선
		public ArrayList<DTO> getArticlesFreeSimilarity(String col, String search, int start, int end) throws Exception
		{
			ArrayList<DTO> articleList=null;
			String find = search.replace(" ", "");
			try 
			{
				conn = ConnectionDAO.getConnection();
				String sql = "select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, r "+
							 "from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs, rownum r " +
							 "from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs " +
							 "from board where bs=3 and "+col+" like '%"+find.substring(0,1)+"%'";										// 첫번째 글 검색
					String order = "order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ";			// 정렬 후 원하는 갯수만큼 검색결과를 저장한다.
				if(find.length() > 1)
				{
					for(int i=1; i<find.length(); i++)
					{
						if(i == find.length()-1)
						{	sql += "and "+col+" like '%"+find.substring(i)+"%'";	}
						else
						{	sql += "and "+col+" like '%"+find.substring(i, i+1)+"%'";	}
					}
				}
				pstmt = conn.prepareStatement(sql+order);		
				pstmt.setInt(1, start); 
				pstmt.setInt(2, end); 
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					articleList = new ArrayList<DTO>(end); 
					do
					{ 
						DTO article= new DTO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setSubject(rs.getString("subject"));
						article.setContent(rs.getString("content"));
						article.setReg(rs.getTimestamp("reg"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setRe_step(rs.getInt("re_step"));
						article.setRe_level(rs.getInt("re_level"));
						article.setHead(rs.getString("head"));
						article.setBs(rs.getInt("bs"));
						articleList.add(article); 
				   }while(rs.next());
				}
			} 
			catch(Exception ex)
			{	ex.printStackTrace();	} 
			finally
			{	ConnectionDAO.close(rs, pstmt, conn);	}
			return articleList;	
		}
		
	//Qna searchList 글 갯수 (searchLsitQna에서 사용) - 인선
    public int getArticleCountQna (String col, String search) throws Exception{
    	int x = 0;
    	try { //col이 subject, writer / search는 입력값
    		conn=ConnectionDAO.getConnection();
    		pstmt=conn.prepareStatement("select count(*) from Board where bs=2 and "+col+" like '%"+search+"%'");
    		//col이 writer인지 subject인지는 그때그때 달라지기 때문에 'col'값으로 넣는다. 
    	    rs= pstmt.executeQuery();
    	    if(rs.next()) {
    	    	x= rs.getInt(1);
    	    }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		ConnectionDAO.close(rs, pstmt, conn);
    	}
    	return x;
    }
    
  //[재검색용]Free searchList 글 갯수
    public int getArticleCountFree (String col, String search, int countSearch) throws Exception{
    	int x = 0;
    	try { //col이 subject, writer / search는 입력값 / countSearch는 검색횟수
    		conn=ConnectionDAO.getConnection();
    		String range = "Board)";   //range는 검색 대상 범위 설정
    		String select = "select count(*) from (";  //검색 결과의 갯수
    		String select2 = "select * from (";  //검색범위 대상인 range를 검색 
    		String where = "where "+col+" like '%"+search+"%')";  //검색어 입력값이 각 속성에 포함되어 있는지 여부 체크
    		//col이 writer인지 subject인지는 그때그때 달라지기 때문에 'col'값으로 넣는다. 
    		String searchRange = select2+range+where; //최종 지정범위에 대한 검색문
    		String result = select+searchRange; //최종 지점범위에 대한 검색결과 갯수
    		if(countSearch >1) {
    			for(int i=1; i<countSearch; i++) {
    				searchRange = select2+searchRange+where;
    			}
    			result = select+searchRange;
    		}
    		pstmt = conn.prepareStatement(result);
    	    rs= pstmt.executeQuery();
    	    if(rs.next()) {
    	    	x= rs.getInt(1);
    	    }
    	    
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		ConnectionDAO.close(rs, pstmt, conn);
    	}
    	return x;
    }
    
  //Free searchList 글 갯수 (freeSearchList.jsp에서 사용) - 인선
  public int getArticleCountFree (String col, String search) throws Exception{
   int x = 0;
   try { //col이 subject, writer / search는 입력값
    conn=ConnectionDAO.getConnection();
    pstmt=conn.prepareStatement("select count(*) from Board where bs=3 and "+col+" like '%"+search+"%'");
    //col이 writer인지 subject인지는 그때그때 달라지기 때문에 'col'값으로 넣는다. 
    	  rs= pstmt.executeQuery();
    	  if(rs.next()) {
    	   x= rs.getInt(1);
    	  }
   }catch(Exception e) {
    e.printStackTrace();
   }finally {
    ConnectionDAO.close(rs, pstmt, conn);
   }
   return x;
  }
  
  //Free searchList 글 갯수 연관검색 (freeSearchList.jsp에서 사용) - 인선
  public int getArticleCountFreeSimilarity (String col, String search) throws Exception
  {
  	int x = 0;
  	String find = search.replace(" ", "");		// 빈칸 삭제
  	try 
  	{ //col이 subject, writer / search는 입력값
  		conn=ConnectionDAO.getConnection();
  		String sql = "select count(*) from Board where bs=3 and "+col+" like '%"+find.substring(0,1)+"%'";		// 첫글자 검색
  		if(find.length() > 1)
  		{
  			for(int i=1; i<find.length(); i++)
  			{
  				if(i == find.length()-1)
  				{	sql += "and "+col+" like '%"+find.substring(i)+"%'";	}		// 마지막 글자 검색
  				else
  				{	sql += "and "+col+" like '%"+find.substring(i,i+1)+"%'";	}	// 2번째~마지막 전 글자 검색
  			}
  		}
  		pstmt=conn.prepareStatement(sql);
  		//col이 writer인지 subject인지는 그때그때 달라지기 때문에 'col'값으로 넣는다. 
  		rs= pstmt.executeQuery();
  		if(rs.next())
  		{	x= rs.getInt(1);	}
  	}
  	catch(Exception e) 
  	{	e.printStackTrace();}
  	finally 
  	{	ConnectionDAO.close(rs, pstmt, conn);	}
  	return x;
  }
	
    //myListQna 에서 리스트 나열 (myListQna.jsp에서 사용) - 인선
	public List getArticlesQna(String nick, int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, r "+
					"from (select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, rownum r " +
					"from (select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs " +
					"from Board where writer =? and bs=2 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
			//rownum은 명령어로, 검색한 결과 레코드에 다시 순번을 매기는 것이다. 위의 경우에는 myList로, 1~10까지 잘라야하는데 기존의 게시물 숫자가 들쑥날쑥하기에 숫자를 다시 부여해서 정리한다.
			        pstmt.setString(1, nick); 
			        pstmt.setInt(2, start); 
					pstmt.setInt(3, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							DTO article= new DTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setContent(rs.getString("content"));
							article.setReg(rs.getTimestamp("reg"));
							article.setReadcount(rs.getInt("readcount"));
							article.setRef(rs.getInt("ref"));
							article.setRe_step(rs.getInt("re_step"));
							article.setRe_level(rs.getInt("re_level"));
							article.setHead(rs.getString("head"));
							article.setBs(rs.getInt("bs"));
						

							articleList.add(article); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return articleList;
	}
	
	//freeMyList 에서 리스트 나열  ( freeMyList.jsp에서 사용) - 인선
	public List getArticlesFree(String nick, int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, save, r "+
					"from (select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, save, rownum r " +
					"from (select * " +
					"from Board where writer =? and bs=3 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
			//rownum은 명령어로, 검색한 결과 레코드에 다시 순번을 매기는 것이다. 위의 경우에는 myList로, 1~10까지 잘라야하는데 기존의 게시물 숫자가 들쑥날쑥하기에 숫자를 다시 부여해서 정리한다.
				    pstmt.setString(1, nick); 
				    pstmt.setInt(2, start); 
					pstmt.setInt(3, end); 
				    rs = pstmt.executeQuery();
				    if (rs.next()) {
						articleList = new ArrayList(end); 
					do{ 
						DTO article= new DTO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setSubject(rs.getString("subject"));
						article.setContent(rs.getString("content"));
						article.setReg(rs.getTimestamp("reg"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setRe_step(rs.getInt("re_step"));
						article.setRe_level(rs.getInt("re_level"));
						article.setHead(rs.getString("head"));
						article.setBs(rs.getInt("bs"));
						article.setSave(rs.getString("save"));

						articleList.add(article); 
					}while(rs.next());
				}
	} catch(Exception ex) {
		ex.printStackTrace();
	} finally {
		ConnectionDAO.close(rs, pstmt, conn);
	}		
	return articleList;
}
	//[모든게시판 해당] 내용(content) - 조회수 1증가  인선은 사용X (noticeContentjsp에서 사용하는 것 같음)
	public DTO getArticle(int num) throws Exception {
		DTO article=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
			"update Board set readcount=readcount+1 where num = ?"); 
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
			"select * from Board where num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new DTO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setReg(rs.getTimestamp("reg"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setHead(rs.getString("head"));
				article.setBs(rs.getInt("bs"));
				article.setSave(rs.getString("save"));
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		
		return article;
	}
	
	//session체크시 사용 (content.jsp파일에서 사용) -  인선사용
	public DTO getArticleCheck(int num) throws Exception {
		DTO article=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
			"select * from Board where num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new DTO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setReg(rs.getTimestamp("reg"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setHead(rs.getString("head"));
				article.setBs(rs.getInt("bs"));
				article.setSave(rs.getString("save"));	
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}	
		return article;
	}
	
	//[모든게시판 해당] 내용(content) (content, freeContent.jsp파일에서 사용) - 조회수 1증가(본인이 읽으면 조회수 증가X)  인선사용
    public DTO getArticle(int num, String nick) throws Exception {
	    DTO article=null;
	    try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select * from Board where num =? and writer = ?");
			pstmt.setInt(1, num);
			pstmt.setString(2, nick);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pstmt = conn.prepareStatement(
				"update Board set readcount=readcount where num = ?"); 
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement(
				"select * from Board where num = ?"); 
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					article = new DTO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setContent(rs.getString("content"));
					article.setReg(rs.getTimestamp("reg"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setHead(rs.getString("head"));
					article.setBs(rs.getInt("bs"));
					article.setSave(rs.getString("save"));
				}
			}else {
				pstmt = conn.prepareStatement(
						"update Board set readcount=readcount+1 where num = ?"); 
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement("select * from Board where num = ?"); 
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					article = new DTO();
					article.setNum(rs.getInt("num"));
		         	article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setContent(rs.getString("content"));
					article.setReg(rs.getTimestamp("reg"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setHead(rs.getString("head"));
					article.setBs(rs.getInt("bs"));
					article.setSave(rs.getString("save"));
				 }	
			 }
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
			
		return article;
	}
	
	//[모든게시판 해당] 수정폼(updateform) jsp파일에서 사용 - 수정대상의 게시글 검색 (freeUpdateForm.jsp, updateFormQna.jsp에서 사용) - 인선
	public DTO updateGetArticle(int num) throws Exception {
		DTO article=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
			"select * from Board where num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new DTO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setReg(rs.getTimestamp("reg"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setHead(rs.getString("head"));
				article.setBs(rs.getInt("bs"));
				article.setSave(rs.getString("save"));
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		return article;
	}
	
    //deletePro에서 공통으로 사용 - 회원이 게시물 삭제시 비밀번호 체크 (freeDeletePro.jsp에서 사용) - 인선
	public int deleteArticleQna(int num, String passwd) throws Exception {
		String dbpasswd="";
		int x=-1;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
			"select pw from MEMBER where pw = ?");
			pstmt.setString(1, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpasswd= rs.getString("pw");
				if(dbpasswd.equals(passwd)){
					pstmt = conn.prepareStatement("delete from Board where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					x= 1; 
				}else
					x= 0; 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//문의게시판 회원이 게시물 삭제시 - 원글지울때 같이 답글들 다같이 삭제 (deleteProQna에서 사용) - 인선
    public int deleteArticleQna(int num, String passwd, int ref, int re_level, String id) throws Exception {
		String dbpasswd="";
		int x=-1;
		try {
			conn = ConnectionDAO.getConnection();
			if(re_level == 0){
				pstmt = conn.prepareStatement("select pw from MEMBER where pw = ? and id = ?");
				pstmt.setString(1, passwd);
				pstmt.setString(2,  id);
				rs = pstmt.executeQuery();
				if(rs.next()){
					dbpasswd= rs.getString("pw");
					if(dbpasswd.equals(passwd)){
						pstmt = conn.prepareStatement("delete from Board where ref=?");
						pstmt.setInt(1, ref);
						pstmt.executeUpdate();
						x= 1; 
					}else
						x= 0; 
				}
			}else {
				   pstmt = conn.prepareStatement("select pw from MEMBER where pw = ? and id = ?");
				   pstmt.setString(1, passwd);
				   pstmt.setString(2,  id);
				   rs = pstmt.executeQuery();
				   if(rs.next()){
					   dbpasswd= rs.getString("pw");
					   if(dbpasswd.equals(passwd)){
					   	pstmt = conn.prepareStatement("delete from Board where num=?");
					    pstmt.setInt(1, num);
					    pstmt.executeUpdate();
					    x= 1; 
					   }else
					    x= 0; 
				   }
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//관리자가 문의게시판 게시물 delete  (deleteProQna에서 사용) - 인선
	public int deleteArticleQna(int num, int ref, int re_level) throws Exception {
		
		int x=-1;
		try {
			conn = ConnectionDAO.getConnection();
			if(re_level == 0) {
			    pstmt = conn.prepareStatement("select * from Board where num = ?");
			    pstmt.setInt(1, num);
			    rs = pstmt.executeQuery();
			    if(rs.next()){
				    	pstmt = conn.prepareStatement("delete from Board where ref=?");
				    	pstmt.setInt(1, ref);
					    pstmt.executeUpdate();
					    x = 1;
			    }
			}else {
				pstmt = conn.prepareStatement("select * from Board where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()){
						pstmt = conn.prepareStatement("delete from Board where num=?");
						pstmt.setInt(1, num);
						pstmt.executeUpdate();
						x = 1;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		return x;
	}
	
	//관리자가 자유게시판 게시물 delete (freeDeletePro.jsp에서 사용) - 인선
		public int deleteArticleFreeArticle(int num) throws Exception {
			
			int x=-1;
			try {
				conn = ConnectionDAO.getConnection();
				pstmt = conn.prepareStatement("select * from Board where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()){
						pstmt = conn.prepareStatement("delete from Board where num=?");
						pstmt.setInt(1, num);
						pstmt.executeUpdate();
						x = 1;
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}
			return x;
		}

	//문의게시판 내 관리자의 답글 확인시 비밀번호체크 - 원글 작성자의 비밀번호 입력시 열람가능 (passwdPro에서 사용) - 인선
	public boolean passwdCheck(String id, String passwd, int ref) {
		boolean result = false;
		try {
			conn=ConnectionDAO.getConnection();
			pstmt=conn.prepareStatement("select * from MEMBER where pw = ? and id=? and nick in (select writer from board where ref=? and re_step=0)");
			pstmt.setString(1,  passwd);
			pstmt.setString(2,  id);
			pstmt.setInt(3,  ref);
			rs=pstmt.executeQuery();
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
	//게시글 수정시, 회원가입 폼의 닉네임과 비밀번호가 일치할 경우 수정 허용 (freeUpdatePro, updateProQna에서 사용) - 인선
	public boolean nickCheck(String nick, String passwd, DTO dto) {
		boolean result = false;
		String id = "";
		try {
			conn=ConnectionDAO.getConnection();
			pstmt=conn.prepareStatement("select * from MEMBER where pw = ? and nick = ?");
			pstmt.setString(1,  passwd);
			pstmt.setString(2,  nick);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pstmt=conn.prepareStatement("update Board set head=?, subject=?, content=?, save=?  where num=? ");
				pstmt.setString(1, dto.getHead());
				pstmt.setString(2, dto.getSubject());
				pstmt.setString(3, dto.getContent());
				pstmt.setString(4, dto.getSave());
				pstmt.setInt(5, dto.getNum());
				pstmt.executeUpdate();
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);
			
		}
		return result;
	}
	
	//아이디에 알맞는 닉네임값 갖고오기 (writeForm.jsp에서 사용) - 인선
	public String getNickname(String id) {
		String nick = "";
		try {
			conn = ConnectionDAO.getConnection();
		    pstmt=conn.prepareStatement("select NICK from MEMBER where id=?");
		    pstmt.setString(1, id);
		    rs= pstmt.executeQuery();
		    if(rs.next()) {
		    	nick = rs.getString("NICK");
			}
		    
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		
		return nick;
	}
	
	//id에 따라 각 status값 리턴
	public int getStatus(String id) {
		int status = 0;
		try {
			conn = ConnectionDAO.getConnection();
		    pstmt=conn.prepareStatement("select status from MEMBER where id=?");
		    pstmt.setString(1, id);
		    rs= pstmt.executeQuery();
		    if(rs.next()) {
		    	status = rs.getInt("status");
			}
		    
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		
		return status;
	}
	
	//관리자 닉네임 갖고와서 권한설정하는 용 (content.jsp, freecontent.jsp에서 사용) - 인선
	public String getAdminNick(int status, String id) {
		String adminNick = null;
		try {
			conn = ConnectionDAO.getConnection();
		    pstmt=conn.prepareStatement("select nick from MEMBER where status=? and id=? ");
		    pstmt.setInt(1, status);
		    pstmt.setString(2, id);
		    rs= pstmt.executeQuery();
		    if(rs.next()) {
		    	adminNick = rs.getString("nick");
			}
		    
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
		
		return adminNick;
	}
    
	
	public void insertArticleNotice(DTO article) throws Exception {
		  int num=article.getNum();
		  int ref=article.getRef();
		  int re_step=article.getRe_step();
		  int re_level=article.getRe_level();
		  int number=0;
		  String sql="";
		  try {
		     conn = ConnectionDAO.getConnection(); 
		     pstmt = conn.prepareStatement("select max(num) from board");
		     rs = pstmt.executeQuery();
		     if (rs.next()) 
		       number=rs.getInt(1)+1;   
		     else
		        number=1; 
		     if (num!=0) 
		     { 
		        sql="update board set re_step=re_step+1 where ref= ? and re_step> ?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, ref);
		        pstmt.setInt(2, re_step);
		        pstmt.executeUpdate();
		        re_step=re_step+1;
		        re_level=re_level+1;
		     }else{ 
		        ref=number;
		        re_step=0;
		        re_level=0;
		    }
		 
		    sql = "insert into board(num,writer,subject,content,reg,";
		    sql+="readcount,ref,re_step,re_level,head,bs) values(board_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, article.getWriter());
		    pstmt.setString(2, article.getSubject());
		    pstmt.setString(3, article.getContent());
		    pstmt.setTimestamp(4, article.getReg());
		    pstmt.setInt(5, article.getReadcount());
		    pstmt.setInt(6, ref);
		    pstmt.setInt(7, re_step);
		    pstmt.setInt(8, re_level);
		    pstmt.setString(9, article.getHead());
		    pstmt.executeUpdate();
		  } catch(Exception ex) {
		     ex.printStackTrace();
		  } finally {
		     ConnectionDAO.close(rs, pstmt, conn);
		  }
 }
		   
	//자유게시판 게시글 추가 (freeWritePro에서 사용) - 인선
	public void insertFreeArticle(DTO article) throws Exception {
			int num=article.getNum();
			      
			int ref=article.getRef();
			int re_step=article.getRe_step();
			int re_level=article.getRe_level();
			int number=0;
			String sql="";
			      
			      
			try {
			       conn = ConnectionDAO.getConnection(); 
			       pstmt = conn.prepareStatement("select max(num) from board");
			       rs = pstmt.executeQuery();
			       if (rs.next()) 
			          number=rs.getInt(1)+1;   
			      else
			          number=1; 
			      if (num!=0) 
			      { 
			          sql="update board set re_step=re_step+1 where ref= ? and re_step> ?";
			          pstmt = conn.prepareStatement(sql);
			          pstmt.setInt(1, ref);
			          pstmt.setInt(2, re_step);
			          pstmt.executeUpdate();
			          re_step=re_step+1;
			          re_level=re_level+1;
			      }else{ 
			          ref=number;
			          re_step=0;
			          re_level=0;
			      }
			 
			      sql = "insert into board(num,writer,subject,content,reg,";
			      sql+="readcount,ref,re_step,re_level,head,bs, save) values(board_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 3, ?)";
			      pstmt = conn.prepareStatement(sql);
			      pstmt.setString(1, article.getWriter());
			      pstmt.setString(2, article.getSubject());
			      pstmt.setString(3, article.getContent());
			      pstmt.setTimestamp(4, article.getReg());
			      pstmt.setInt(5, article.getReadcount());
			      pstmt.setInt(6, ref);
			      pstmt.setInt(7, re_step);
			      pstmt.setInt(8, re_level);
			      pstmt.setString(9, article.getHead());	
			      pstmt.setString(10, article.getSave());	
			      pstmt.executeUpdate();
	 
				     /*  사진 3개용 sql = "insert into picture(picturenum, num, save, save1, save2) values (picture_seq.NEXTVAL, ?, ?,?,?)";
				     pstmt = conn.prepareStatement(sql);
				     pstmt.setInt(1, number);
				     pstmt.setString(2, article.getSave());
				     pstmt.setString(3, article.getSave1());
				     pstmt.setString(4, article.getSave2());
				         
				     pstmt.executeUpdate();*/
			         
			         
			 } catch(Exception ex) {
			     ex.printStackTrace();
			 } finally {
			     ConnectionDAO.close(rs, pstmt, conn);
			 }
		}
		   
		   //자유게시판 사진 삽입 메서드
		   public void insertFreePicture(DTO article) {
			  
			   String save = article.getSave();
			   String save1 = article.getSave1();
			   String save2 = article.getSave2();
			   if(save == null) {
			    save = "";
			   }
			   if(save1 == null) {
			    save1 = "";
			   }
			   if(save2 == null) {
			    save2 = "";
			   }
			   try {
				      conn = ConnectionDAO.getConnection(); 
				      String sql = "insert into picture(picturenum, num, save, save1, save2) values (picture_seq.NEXTVAL, ?, ?,?,?)";
				      pstmt = conn.prepareStatement(sql);
				      pstmt.setInt(1, article.getNum());
				      pstmt.setString(2, save);
				      pstmt.setString(3, save1);
				      pstmt.setString(4, save2);
				         
				      pstmt.executeUpdate();
				         
			  } catch(Exception ex) {
				      ex.printStackTrace();
		      } finally {
				      ConnectionDAO.close(rs, pstmt, conn);
		      }
		   }
		   
		   public int getNoticeArticleCount() throws Exception {
		      int x=0;
		      try {
		         conn = ConnectionDAO.getConnection();
		         pstmt = conn.prepareStatement("select count(*) from board where bs=1");
		         rs = pstmt.executeQuery();
		         if (rs.next()) {
		            x= rs.getInt(1); 
		         }
		      } catch(Exception ex) {
		         ex.printStackTrace();
		      } finally {
		         ConnectionDAO.close(rs, pstmt, conn);
		      }
		      return x; 
		   }
		  //자유게시판 리스트 갯수 카운트 (freeList.jsp에서 사용) - 인선 
		   public int getFreeArticleCount() throws Exception {
			      int x=0;
			      try {
			         conn = ConnectionDAO.getConnection();
			         pstmt = conn.prepareStatement("select count(*) from board where bs=3");
			         rs = pstmt.executeQuery();
			         if (rs.next()) {
			            x= rs.getInt(1); 
			         }
			      } catch(Exception ex) {
			         ex.printStackTrace();
			      } finally {
			         ConnectionDAO.close(rs, pstmt, conn);
			      }
			      return x; 
			   }
		   
		   public int getArticleCount(String id) throws Exception {
			      int x=0;
			      try {
			         conn = ConnectionDAO.getConnection();
			         pstmt = conn.prepareStatement("select count(*) from board");
			         rs = pstmt.executeQuery();
			         if (rs.next()) {
			            x= rs.getInt(1); 
			         }
			      } catch(Exception ex) {
			         ex.printStackTrace();
			      } finally {
			         ConnectionDAO.close(rs, pstmt, conn);
			      }
			      return x; 
			   }
			public int getArticleCount(String col , String search) throws Exception {
				int x=0;
				try {
					conn = ConnectionDAO.getConnection();
					String sql = "select count(*) from board where "+col+" like '%"+search+"%'";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						x= rs.getInt(1); 
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					ConnectionDAO.close(rs, pstmt, conn);
				}
				return x; 
			}
			
			// 공지사항 연관검색결과 리스트 수
			public int getNoticeArticleSimilarityCount(String col , String search) throws Exception 
			{
				int x=0;
				String find = search.replace(" ","");
				try 
				{
					conn = ConnectionDAO.getConnection();
					String sql = "select count(*) from board where bs=1 and "+col+" like '%"+find.substring(0,1)+"%'";
					if(find.length() > 1)
					{
						for(int i=1; i<find.length(); i++)
						{
							if(i == find.length()-1)
							{	sql += "and "+col+" like '%"+find.substring(i)+"%'";	}
							else
							{	sql += "and "+col+" like '%"+find.substring(i,i+1)+"%'";	}
						}
					}
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if (rs.next()) 
					{	x= rs.getInt(1); 	}
				} 
				catch(Exception ex) 
				{	ex.printStackTrace();	} 
				finally 
				{	ConnectionDAO.close(rs, pstmt, conn);		}
				return x; 
			}
			
		//공지 리스트 받아오는 메서드
			public List getArticles(int start, int end) throws Exception {
			      List articleList=null;
			      try {
			         conn = ConnectionDAO.getConnection();
			         pstmt = conn.prepareStatement(
			               "select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs,r "+
			               "from (select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs,rownum r " +
			               "from (select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs " +
			               "from board where bs = 1 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
			               pstmt.setInt(1, start); 
			               pstmt.setInt(2, end); 

			               rs = pstmt.executeQuery();
			               if (rs.next()) {
			                  articleList = new ArrayList(end); 
			                  do{ 
			                     DTO article= new DTO();
			                     article.setNum(rs.getInt("num"));
			                     article.setWriter(rs.getString("writer"));
			                     article.setSubject(rs.getString("subject"));
			                     article.setReg(rs.getTimestamp("reg"));
			                     article.setReadcount(rs.getInt("readcount"));
			                     article.setRef(rs.getInt("ref"));
			                     article.setRe_step(rs.getInt("re_step"));
			                     article.setRe_level(rs.getInt("re_level"));
			                     article.setContent(rs.getString("content"));
			                     article.setHead(rs.getString("head")) ;
			                     article.setBs(rs.getInt("bs")) ;
			                     articleList.add(article); 
			                  }while(rs.next());
			               }
			      } catch(Exception ex) {
			         ex.printStackTrace();
			      } finally {
			         ConnectionDAO.close(rs, pstmt, conn);
			      }

			      
			      return articleList;
			   }
		   // 이것은 자유게시판 게시글 리스트.받아오는 메소드.  (freeList.jsp에서 사용) - 인선
		   public List getFreeArticles(int start, int end) throws Exception {
			      List articleList=null;
			      try {
			         conn = ConnectionDAO.getConnection();
			         pstmt = conn.prepareStatement(
			               "select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs,save, r "+
			               "from (select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs, save, rownum r " +
			               "from (select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs, save " +
			               "from board where bs = 3 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
			               pstmt.setInt(1, start); 
			               pstmt.setInt(2, end); 

			               rs = pstmt.executeQuery();
			               if (rs.next()) {
			                  articleList = new ArrayList(end); 
			                  do{ 
			                     DTO article= new DTO();
			                     article.setNum(rs.getInt("num"));
			                     article.setWriter(rs.getString("writer"));
			                     article.setSubject(rs.getString("subject"));
			                     article.setReg(rs.getTimestamp("reg"));
			                     article.setReadcount(rs.getInt("readcount"));
			                     article.setRef(rs.getInt("ref"));
			                     article.setRe_step(rs.getInt("re_step"));
			                     article.setRe_level(rs.getInt("re_level"));
			                     article.setContent(rs.getString("content"));
			                     article.setHead(rs.getString("head")) ;
			                     article.setBs(rs.getInt("bs")) ;
			                     article.setSave(rs.getString("save")) ;
			                     articleList.add(article); 
			                  }while(rs.next());
			               }
			      } catch(Exception ex) {
			         ex.printStackTrace();
			      } finally {
			         ConnectionDAO.close(rs, pstmt, conn);
			      }

			      
			      return articleList;
			   }
		   
		  

			public List getFreeArticles(String col , String search, int start, int end) throws Exception {
				List articleList=null;
				try {
					conn = ConnectionDAO.getConnection();
					pstmt = conn.prepareStatement(
							"select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs,r "+
							"from (select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs,rownum r "+
							"from (select num,writer,subject,reg,ref,re_step,re_level,content,head,readcount,bs "+
							"from board where bs=3 and "+col+" like '%"+search+"%' order by reg desc) order by reg desc ) where r >= ? and r <= ? ");
							pstmt.setInt(1, start); 
							pstmt.setInt(2, end); 

							rs = pstmt.executeQuery();
							if (rs.next()) {
								articleList = new ArrayList(end); 
								do{ 
									DTO article= new DTO();
									article.setNum(rs.getInt("num"));
									article.setWriter(rs.getString("writer"));
									article.setSubject(rs.getString("subject"));
									article.setReg(rs.getTimestamp("reg"));
									article.setReadcount(rs.getInt("readcount"));
									article.setRef(rs.getInt("ref"));
									article.setRe_step(rs.getInt("re_step"));
									article.setRe_level(rs.getInt("re_level"));
									article.setContent(rs.getString("content"));
									article.setHead(rs.getString("head"));
							        article.setBs(rs.getInt("bs"));
									articleList.add(article); 
								}while(rs.next());
							}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					ConnectionDAO.close(rs, pstmt, conn);
				}		
				return articleList;
			}
			
		   
			//공지사항 게시글 삭제 메서드
			   public int deleteArticle(int num, String passwd) throws Exception {
			      int x=-1;
			      try {
			            if(passwd.equals("지금삭제하기")){
			            	conn = ConnectionDAO.getConnection();
			            	pstmt = conn.prepareStatement("delete from board where num=? ");
			               pstmt.setInt(1, num);
			               pstmt.executeUpdate();
			               x= 1; 
			            }else
			               x= 0; 
			      } catch(Exception ex) {
			         ex.printStackTrace();
			      } finally {
			         ConnectionDAO.close(rs, pstmt, conn);
			      }
			      return x;
			   }
		   
			public int passwdCheck(int num, String passwd) throws Exception {
				String dbpasswd="";
				int x=-1; // 글번호 잘못됨. 변수의 값의 의미는 프로그래머가 정하는것.
				try {
					conn = ConnectionDAO.getConnection();
					pstmt = conn.prepareStatement(
					"select passwd from board where num = ?");
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()){
						dbpasswd= rs.getString("passwd");
						if(dbpasswd.equals(passwd)){
							x= 1; // 글번호에 비밀번가 확인.
						}else
							x= 0; // 비번 틀림.
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					ConnectionDAO.close(rs, pstmt, conn);
				}
				return x;
			}
			
			public int updateArticle(DTO article) throws Exception {
				
				int x=-1;
				try {
					conn = ConnectionDAO.getConnection();		
							pstmt = conn.prepareStatement("update board set writer=?,subject=?,content=? where num=?");
							pstmt.setString(1, article.getWriter());
							pstmt.setString(2, article.getSubject());
							pstmt.setString(3, article.getContent());
							pstmt.setInt(4, article.getNum());
							pstmt.executeUpdate();
							x= 1;	
					
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					ConnectionDAO.close(rs, pstmt, conn);
				}
				return x;
			}
			
			//공지사항 noticeSearchList 리스트나열
			public List getNoticeArticles(String col, String search, int start, int end) throws Exception {
				List articleList=null;
				try {
					conn = ConnectionDAO.getConnection();
					pstmt = conn.prepareStatement(
							"select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, r "+
							"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs, rownum r " +
							"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs " +
							"from board where bs=1 and "+col+" like '%"+search+"%' order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
							pstmt.setInt(1, start); 
							pstmt.setInt(2, end); 
							
							
							rs = pstmt.executeQuery();
							if (rs.next()) {
								articleList = new ArrayList(end); 
								do{ 
									DTO article= new DTO();
									article.setNum(rs.getInt("num"));
									article.setWriter(rs.getString("writer"));
									article.setSubject(rs.getString("subject"));
									article.setContent(rs.getString("content"));
									article.setReg(rs.getTimestamp("reg"));
									article.setReadcount(rs.getInt("readcount"));
									article.setRef(rs.getInt("ref"));
									article.setRe_step(rs.getInt("re_step"));
									article.setRe_level(rs.getInt("re_level"));
									article.setHead(rs.getString("head"));
									article.setBs(rs.getInt("bs"));
									
									articleList.add(article); 
								}while(rs.next());
							}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					ConnectionDAO.close(rs, pstmt, conn);
				}

				
				return articleList;
			}
			
			//공지사항 연관검색 noticeSearchList 리스트나열
			public ArrayList<DTO> getNoticeArticlesSimilarity(String col, String search, int start, int end) throws Exception 
			{
				ArrayList<DTO> articleList=null;
				String find = search.replace(" ","");
				try 
				{
					conn = ConnectionDAO.getConnection();
					String sql = "select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, r "+
							"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs, rownum r " +
							"from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs " +
							"from board where bs=1 and "+col+" like '%"+find.substring(0,1)+"%'";
					String order = "order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ";
					if(find.length() > 1)
					{
						for(int i=1; i<find.length(); i++)
						{
							if(i == find.length()-1)
							{	sql += "and "+col+" like '%"+find.substring(i)+"%'";	}
							else
							{	sql += "and "+col+" like '%"+find.substring(i,i+1)+"%'";	}
						}
					}
					pstmt = conn.prepareStatement(sql+order);
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					rs = pstmt.executeQuery();
					if (rs.next()) 
					{
						articleList = new ArrayList<DTO>(end); 
						do
						{ 
							DTO article= new DTO();
							article.setNum(rs.getInt("num"));
							article.setWriter(rs.getString("writer"));
							article.setSubject(rs.getString("subject"));
							article.setContent(rs.getString("content"));
							article.setReg(rs.getTimestamp("reg"));
							article.setReadcount(rs.getInt("readcount"));
							article.setRef(rs.getInt("ref"));
							article.setRe_step(rs.getInt("re_step"));
							article.setRe_level(rs.getInt("re_level"));
							article.setHead(rs.getString("head"));
							article.setBs(rs.getInt("bs"));
							articleList.add(article); 
						}while(rs.next());
					}
				}
				catch(Exception ex)
				{	ex.printStackTrace();} 
				finally 
				{	ConnectionDAO.close(rs, pstmt, conn);}
				return articleList;
			}
}