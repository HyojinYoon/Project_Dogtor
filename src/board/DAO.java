package board;
import java.sql.*;
import java.util.*; 
import board.ConnectionDAO;
import board.DTO;

public class DAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//���ǰԽ��� �Խñ� ���� (writePro���� ���) - �μ�
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
			sql+="readcount, ref,re_step,re_level, head, bs) values(Board_seq.NEXTVAL,?,?,?,sysdate,?,?,?,?,'����',2)";
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
	
	//�����Խ��� �Խñۻ���
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
	
	
	
	//���ǰԽ��� �Խñ� ���� Ȯ�� (list2.jsp���� ���) - �μ�
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
	
	//Qna myList �Խñ� ����üũ (myListQna���� ���) - �μ�
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
	
	//freeMyList �Խñ� ����üũ (freeMyList.jsp���� ���) - �μ�
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
	
    //���ǰԽ��� �Խñ� 10���� ©�� ��ȣ�ο� ��, ������ (list2.jsp���� ���) - �μ�
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
	
	//�����Խ��� �Խñ� 10���� ©�� list�� ����
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
	
	//Qna searchList ����Ʈ���� (searchLsitQna���� ���) - �μ�
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
	
	//[��˻���]free searchList ����Ʈ����
	public ArrayList<DTO> getArticlesFree(String col, String search, int start, int end, int countSearch) throws Exception {
		ArrayList<DTO> articleList = new ArrayList<DTO>();
		try {
			conn = ConnectionDAO.getConnection();
			String select = "select * from (select num,writer,subject,content,reg,readcount,ref,re_step,re_level,head, bs, rownum r from (";
			//�˻����뿡 rownum�ο�
			String select2 = "select * from (";  //��� ������ �˻�
			String where = "where r >= ? and r <= ? "; //start���� end���� �˻���� ���
			String rowOrder = "order by ref desc, re_step asc)";  //����
			String range = "Board)";  //���̺��� �˻�
			String whereSel = "where bs=3 and "+col+" like '%"+search+"%' order by ref desc, re_step asc)";   //���ϴ� �˻�
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
		
		
		//free searchList ����Ʈ���� (freeSearchList.jsp���� ���) - �μ�
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
		
		//free searchList �����˻�����Ʈ���� (freeSearchList.jsp���� ���) - �μ�
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
							 "from board where bs=3 and "+col+" like '%"+find.substring(0,1)+"%'";										// ù��° �� �˻�
					String order = "order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ";			// ���� �� ���ϴ� ������ŭ �˻������ �����Ѵ�.
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
		
	//Qna searchList �� ���� (searchLsitQna���� ���) - �μ�
    public int getArticleCountQna (String col, String search) throws Exception{
    	int x = 0;
    	try { //col�� subject, writer / search�� �Է°�
    		conn=ConnectionDAO.getConnection();
    		pstmt=conn.prepareStatement("select count(*) from Board where bs=2 and "+col+" like '%"+search+"%'");
    		//col�� writer���� subject������ �׶��׶� �޶����� ������ 'col'������ �ִ´�. 
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
    
  //[��˻���]Free searchList �� ����
    public int getArticleCountFree (String col, String search, int countSearch) throws Exception{
    	int x = 0;
    	try { //col�� subject, writer / search�� �Է°� / countSearch�� �˻�Ƚ��
    		conn=ConnectionDAO.getConnection();
    		String range = "Board)";   //range�� �˻� ��� ���� ����
    		String select = "select count(*) from (";  //�˻� ����� ����
    		String select2 = "select * from (";  //�˻����� ����� range�� �˻� 
    		String where = "where "+col+" like '%"+search+"%')";  //�˻��� �Է°��� �� �Ӽ��� ���ԵǾ� �ִ��� ���� üũ
    		//col�� writer���� subject������ �׶��׶� �޶����� ������ 'col'������ �ִ´�. 
    		String searchRange = select2+range+where; //���� ���������� ���� �˻���
    		String result = select+searchRange; //���� ���������� ���� �˻���� ����
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
    
  //Free searchList �� ���� (freeSearchList.jsp���� ���) - �μ�
  public int getArticleCountFree (String col, String search) throws Exception{
   int x = 0;
   try { //col�� subject, writer / search�� �Է°�
    conn=ConnectionDAO.getConnection();
    pstmt=conn.prepareStatement("select count(*) from Board where bs=3 and "+col+" like '%"+search+"%'");
    //col�� writer���� subject������ �׶��׶� �޶����� ������ 'col'������ �ִ´�. 
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
  
  //Free searchList �� ���� �����˻� (freeSearchList.jsp���� ���) - �μ�
  public int getArticleCountFreeSimilarity (String col, String search) throws Exception
  {
  	int x = 0;
  	String find = search.replace(" ", "");		// ��ĭ ����
  	try 
  	{ //col�� subject, writer / search�� �Է°�
  		conn=ConnectionDAO.getConnection();
  		String sql = "select count(*) from Board where bs=3 and "+col+" like '%"+find.substring(0,1)+"%'";		// ù���� �˻�
  		if(find.length() > 1)
  		{
  			for(int i=1; i<find.length(); i++)
  			{
  				if(i == find.length()-1)
  				{	sql += "and "+col+" like '%"+find.substring(i)+"%'";	}		// ������ ���� �˻�
  				else
  				{	sql += "and "+col+" like '%"+find.substring(i,i+1)+"%'";	}	// 2��°~������ �� ���� �˻�
  			}
  		}
  		pstmt=conn.prepareStatement(sql);
  		//col�� writer���� subject������ �׶��׶� �޶����� ������ 'col'������ �ִ´�. 
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
	
    //myListQna ���� ����Ʈ ���� (myListQna.jsp���� ���) - �μ�
	public List getArticlesQna(String nick, int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, r "+
					"from (select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, rownum r " +
					"from (select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs " +
					"from Board where writer =? and bs=2 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
			//rownum�� ��ɾ��, �˻��� ��� ���ڵ忡 �ٽ� ������ �ű�� ���̴�. ���� ��쿡�� myList��, 1~10���� �߶���ϴµ� ������ �Խù� ���ڰ� �龦�����ϱ⿡ ���ڸ� �ٽ� �ο��ؼ� �����Ѵ�.
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
	
	//freeMyList ���� ����Ʈ ����  ( freeMyList.jsp���� ���) - �μ�
	public List getArticlesFree(String nick, int start, int end) throws Exception {
		List articleList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, save, r "+
					"from (select num,writer,subject,content,reg, readcount,ref,re_step,re_level,head, bs, save, rownum r " +
					"from (select * " +
					"from Board where writer =? and bs=3 order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
			//rownum�� ��ɾ��, �˻��� ��� ���ڵ忡 �ٽ� ������ �ű�� ���̴�. ���� ��쿡�� myList��, 1~10���� �߶���ϴµ� ������ �Խù� ���ڰ� �龦�����ϱ⿡ ���ڸ� �ٽ� �ο��ؼ� �����Ѵ�.
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
	//[���Խ��� �ش�] ����(content) - ��ȸ�� 1����  �μ��� ���X (noticeContentjsp���� ����ϴ� �� ����)
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
	
	//sessionüũ�� ��� (content.jsp���Ͽ��� ���) -  �μ����
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
	
	//[���Խ��� �ش�] ����(content) (content, freeContent.jsp���Ͽ��� ���) - ��ȸ�� 1����(������ ������ ��ȸ�� ����X)  �μ����
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
	
	//[���Խ��� �ش�] ������(updateform) jsp���Ͽ��� ��� - ��������� �Խñ� �˻� (freeUpdateForm.jsp, updateFormQna.jsp���� ���) - �μ�
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
	
    //deletePro���� �������� ��� - ȸ���� �Խù� ������ ��й�ȣ üũ (freeDeletePro.jsp���� ���) - �μ�
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
	
	//���ǰԽ��� ȸ���� �Խù� ������ - �������ﶧ ���� ��۵� �ٰ��� ���� (deleteProQna���� ���) - �μ�
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
	
	//�����ڰ� ���ǰԽ��� �Խù� delete  (deleteProQna���� ���) - �μ�
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
	
	//�����ڰ� �����Խ��� �Խù� delete (freeDeletePro.jsp���� ���) - �μ�
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

	//���ǰԽ��� �� �������� ��� Ȯ�ν� ��й�ȣüũ - ���� �ۼ����� ��й�ȣ �Է½� �������� (passwdPro���� ���) - �μ�
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
	//�Խñ� ������, ȸ������ ���� �г��Ӱ� ��й�ȣ�� ��ġ�� ��� ���� ��� (freeUpdatePro, updateProQna���� ���) - �μ�
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
	
	//���̵� �˸´� �г��Ӱ� ������� (writeForm.jsp���� ���) - �μ�
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
	
	//id�� ���� �� status�� ����
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
	
	//������ �г��� ����ͼ� ���Ѽ����ϴ� �� (content.jsp, freecontent.jsp���� ���) - �μ�
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
		   
	//�����Խ��� �Խñ� �߰� (freeWritePro���� ���) - �μ�
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
	 
				     /*  ���� 3���� sql = "insert into picture(picturenum, num, save, save1, save2) values (picture_seq.NEXTVAL, ?, ?,?,?)";
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
		   
		   //�����Խ��� ���� ���� �޼���
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
		  //�����Խ��� ����Ʈ ���� ī��Ʈ (freeList.jsp���� ���) - �μ� 
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
			
			// �������� �����˻���� ����Ʈ ��
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
			
		//���� ����Ʈ �޾ƿ��� �޼���
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
		   // �̰��� �����Խ��� �Խñ� ����Ʈ.�޾ƿ��� �޼ҵ�.  (freeList.jsp���� ���) - �μ�
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
			
		   
			//�������� �Խñ� ���� �޼���
			   public int deleteArticle(int num, String passwd) throws Exception {
			      int x=-1;
			      try {
			            if(passwd.equals("���ݻ����ϱ�")){
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
				int x=-1; // �۹�ȣ �߸���. ������ ���� �ǹ̴� ���α׷��Ӱ� ���ϴ°�.
				try {
					conn = ConnectionDAO.getConnection();
					pstmt = conn.prepareStatement(
					"select passwd from board where num = ?");
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()){
						dbpasswd= rs.getString("passwd");
						if(dbpasswd.equals(passwd)){
							x= 1; // �۹�ȣ�� ��й��� Ȯ��.
						}else
							x= 0; // ��� Ʋ��.
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
			
			//�������� noticeSearchList ����Ʈ����
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
			
			//�������� �����˻� noticeSearchList ����Ʈ����
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