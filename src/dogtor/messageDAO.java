package dogtor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dogtor.ConnectionDAO;
import dogtor.messageDTO;





public class messageDAO { // 筌롫뗄�뻻筌욑옙 筌뤴뫖以� �뵳�딅뮞占쎈뱜.
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ArrayList<messageDTO> getMessageList() {
		ArrayList<messageDTO> list = new ArrayList<messageDTO>() ;
		try {
			conn = ConnectionDAO.getConnection();  // 1/2占쎈뼊�⑨옙 筌롫뗄苑뚳옙諭� 占쎌깈�빊占�
	        pstmt = conn.prepareStatement("select * from message order by ref desc") ;
	        rs = pstmt.executeQuery() ;
	        while(rs.next()) {
	        	messageDTO dto = new messageDTO() ;
	        	dto.setNum(rs.getInt("num"));
	        	dto.setWriter(rs.getString("writer")) ;
	        	dto.setReceiver(rs.getString("receiver")) ;
	        	dto.setRe_date(rs.getTimestamp("re_date")) ;
	        	dto.setContent(rs.getString("content")) ;
	        	dto.setRef(rs.getInt("ref")) ;
	        	dto.setRe_step(rs.getInt("re_step")) ;
	        	dto.setRe_level(rs.getInt("re_level")) ;
	        	dto.setReadcheck(rs.getInt("readcheck")) ;
	        	dto.setSubject(rs.getString("subject")) ;
	        	list.add(dto) ;
	        }
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		} return list ;
	}
	
	public List getMessage(int start, int end) throws Exception {
	      List list=null;
	      try {
	         conn = ConnectionDAO.getConnection();
	         pstmt = conn.prepareStatement(
	               "select num,writer,receiver,re_date,ref,re_step,re_level,content,readcheck,subject,r "+
	               "from (select num,writer,receiver,re_date,ref,re_step,re_level,content,readcheck,subject,rownum r " +
	               "from (select num,writer,receiver,re_date,ref,re_step,re_level,content,readcheck,subject " +
	               "from message order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
	               pstmt.setInt(1, start); 
	               pstmt.setInt(2, end); 
	               rs = pstmt.executeQuery();
	               if (rs.next()) {
	                  list = new ArrayList(end); 
	                  do{ 
	                     messageDTO dto = new messageDTO();
	                     dto.setNum(rs.getInt("num"));
	                     dto.setWriter(rs.getString("writer"));
	                     dto.setReceiver(rs.getString("receiver"));
	                     dto.setRe_date(rs.getTimestamp("re_date"));
	                     dto.setRef(rs.getInt("ref"));
	                     dto.setRe_step(rs.getInt("re_step"));
	                     dto.setRe_level(rs.getInt("re_level"));
	                     dto.setContent(rs.getString("content"));
	                     dto.setReadcheck(rs.getInt("readcheck"));
	                     dto.setSubject(rs.getString("subject")) ;
	                     list.add(dto); 
	                  }while(rs.next());
	               }
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         ConnectionDAO.close(rs, pstmt, conn);
	      }
	      	return list;
	   }
	
	public ArrayList<messageDTO> getMessageList2(String receiver) { //
		ArrayList<messageDTO> list = new ArrayList<messageDTO>() ;
		try {
			conn = ConnectionDAO.getConnection();  // 1/2占쎈뼊�⑨옙 筌롫뗄苑뚳옙諭� 占쎌깈�빊占�
	        pstmt = conn.prepareStatement("select * from message where receiver = ? order by ref desc") ;
			pstmt.setString(1,receiver);
	        rs = pstmt.executeQuery() ;
	        while(rs.next()) {
	        	messageDTO dto = new messageDTO() ;
	        	dto.setNum(rs.getInt("num"));
	        	dto.setWriter(rs.getString("writer")) ;
	        	dto.setReceiver(rs.getString("receiver")) ;
	        	dto.setRe_date(rs.getTimestamp("re_date")) ;
	        	dto.setContent(rs.getString("content")) ;
	        	dto.setRef(rs.getInt("ref")) ;
	        	dto.setRe_step(rs.getInt("re_step")) ;
	        	dto.setRe_level(rs.getInt("re_level")) ;
	        	dto.setReadcheck(rs.getInt("readcheck")) ;
	        	dto.setSubject(rs.getString("subject")) ;
	        	list.add(dto) ;
	        }
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		} return list ;
	}
	
	
	public int getMessageCount() throws Exception {
      int x=0;
      try {
         conn = ConnectionDAO.getConnection();
         pstmt = conn.prepareStatement("select count(*) from message");
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
	
	public int getMessageCount(String col , String search) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			String sql = "select count(*) from message where "+col+" like '%"+search+"%'";
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
	
	// 자신의 받은 메시지 중에서 연관검색
	public int getMessageCount(String receiver, String col , String search) throws Exception {
		int x=0;
		String find = search.replace(" ", "");
		try 
		{
			conn = ConnectionDAO.getConnection();
			String sql = "select count(*) from message where receiver=? and "+col+" like '%"+find.substring(0, 1)+"%'";
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receiver);
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
	
	
	
	
	public int getMessageCount(String receiver) throws Exception {
	      int x=0;
	      try {
	         conn = ConnectionDAO.getConnection();
	         pstmt = conn.prepareStatement("select count(*) from message where receiver=?");
	         pstmt.setString(1, receiver) ;
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
	
	
	

	public int getMyMessageCount(String receiver) throws Exception {
	      int x=0;
	      try {
	         conn = ConnectionDAO.getConnection();
	         pstmt = conn.prepareStatement("select count(*) from message where receiver=? and readcheck=0");
	         pstmt.setString(1, receiver);
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
   
   
   public int readCheckCount(String receiver) throws Exception {
	      int x=0;
	      try {
	         conn = ConnectionDAO.getConnection();
	         pstmt = conn.prepareStatement("select count(*) from message where readcheck = 0 and receiver = ? ");
	         pstmt.setString(1,receiver);
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
   
   public void insertMessage(messageDTO dto) throws Exception {
	      int num=dto.getNum();
	      int ref=dto.getRef();
	      int re_step=dto.getRe_step();
	      int re_level=dto.getRe_level();
	      int readcheck=dto.getReadcheck();
	      int number=0;
	      String sql="";
	      
	      try {
	         conn = ConnectionDAO.getConnection(); 
	         pstmt = conn.prepareStatement("select max(num) from message");
	         rs = pstmt.executeQuery();
	         if (rs.next()) 
	            number=rs.getInt(1)+1;   
	         else
	            number=1; 
	         if (num!=0) 
	         { 
	            sql="update message set re_step=re_step+1 where ref= ? and re_step > ?";
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
	 
	         
	         sql = "insert into message (num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject) values (message_seq.NEXTVAL,?,?,sysdate,?,?,?,?,?,?)";
	         
	         pstmt = conn.prepareStatement(sql);   
	         
	         pstmt.setString(1, dto.getWriter());
	         pstmt.setString(2, dto.getReceiver());
	         pstmt.setString(3, dto.getContent());
	         pstmt.setInt(4, ref);
	         pstmt.setInt(5, re_step);
	         pstmt.setInt(6, re_level);
	         pstmt.setInt(7, readcheck);
	         pstmt.setString(8, dto.getSubject());

	         
	         pstmt.executeUpdate();

	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         ConnectionDAO.close(rs, pstmt, conn);
	      }
	   }
   
   public messageDTO getContent(int num) { // 筌롫뗄苑�筌욑옙 占쎄땀占쎌뒠占쎌뱽 �댆�눖沅∽옙�궔占쎈뼄.
	   messageDTO dto = new messageDTO() ;
	   try {
		   conn = ConnectionDAO.getConnection();  // 1/2占쎈뼊�⑨옙 筌롫뗄苑뚳옙諭� 占쎌깈�빊占�
		   pstmt = conn.prepareStatement("select * from message where num = ?") ;
		   pstmt.setInt(1,  num) ;
		   rs = pstmt.executeQuery() ;
		   if(rs.next()) { // next 揶쏉옙 占쎈솇占쎈뼊 占쎈뱜�뙴�뫀諭� �맱癒�諭�, rs揶쏉옙 揶쏉옙筌욑옙�⑨옙 占쎌뿳占쎈뼄.
			   dto.setNum(rs.getInt("num"));
               dto.setWriter(rs.getString("writer"));
               dto.setReceiver(rs.getString("receiver"));
               dto.setRe_date(rs.getTimestamp("re_date"));
               dto.setRef(rs.getInt("ref"));
               dto.setRe_step(rs.getInt("re_step"));
               dto.setRe_level(rs.getInt("re_level"));
               dto.setContent(rs.getString("content"));
               dto.setReadcheck(rs.getInt("readcheck"));
               dto.setSubject(rs.getString("subject")) ;
		   }
	   } catch(Exception e) {
		   e.printStackTrace() ;
	   } finally {
		   ConnectionDAO.close(rs, pstmt, conn);
	   }
	return dto;
   }

   public boolean deleteMessage(int num) {
	   boolean result = false;
	   
	   try {
		  conn=ConnectionDAO.getConnection();
	      String sql="delete from message where num=?";
	      pstmt=conn.prepareStatement(sql);
	      pstmt.setInt(1, num);
	      pstmt.executeUpdate();
		  result = true;
	   } catch(Exception e) {
	      e.printStackTrace();
	   } finally {
	      ConnectionDAO.close(rs, pstmt, conn);
	   }
	   return result;
	}

	
	public void getMessage(String id, int num) throws Exception {
		messageDTO message=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select * from message where writer=? and num=?");
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				pstmt = conn.prepareStatement("update message set readcheck=readcheck+1 where num = ?"); 
				pstmt.setInt(1, num);	
				pstmt.executeUpdate();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
	}
	
	
	//蹂대궦硫붿꽭吏��븿
	
	public int getMessageCount2(String id) throws Exception {
	      int count=0;
	      try {
	         conn = ConnectionDAO.getConnection();
	         pstmt = conn.prepareStatement("select count(*) from message where writer=?");
	         pstmt.setString(1, id) ;
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            count= rs.getInt(1); 
	         }
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         ConnectionDAO.close(rs, pstmt, conn);
	      }
	      return count; 
	   }
	
	public List getMessage(String id,int start, int end) throws Exception { //�삤踰꾨줈�뵫: 硫붿냼�뱶�쓽 �씠由꾩� 媛숈�留� 留ㅺ컻蹂��닔 媛쒖닔媛� �떎瑜대�濡� �뿉�윭x
		List messageList=null;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject,r "+
					"from (select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject,rownum r " +
					"from (select * " +
					"from message where writer = ? order by re_date desc) order by re_date desc ) where r >= ? and r <= ? ");
					pstmt.setString(1, id); 
					pstmt.setInt(2, start); 
					pstmt.setInt(3, end);
					
					rs = pstmt.executeQuery();
					if (rs.next()) {
						messageList = new ArrayList(end); 
						do{ 
							messageDTO message= new messageDTO();
							message.setNum(rs.getInt("num"));
							message.setWriter(rs.getString("writer"));
							message.setReceiver(rs.getString("receiver"));
							message.setContent(rs.getString("content"));
							message.setRe_date(rs.getTimestamp("re_date"));
							message.setRef(rs.getInt("ref"));
							message.setRe_step(rs.getInt("re_step"));
							message.setRe_level(rs.getInt("re_level"));
							message.setReadcheck(rs.getInt("readcheck"));
							message.setSubject(rs.getString("subject"));
							messageList.add(message); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}

		
		return messageList;
	}
	
	
//硫붿꽭吏� 寃��깋
	 public List getSearchMessage(String keyword, String search, int start, int end) {
		 List messageList = null;
		  
		try {
			   conn = ConnectionDAO.getConnection();
			   pstmt = conn.prepareStatement(
					   "select num, writer, receiver, re_date, content, ref, re_step, re_level, readcheck, subject, r "+
					   "from (select num, writer, receiver, re_date, content, ref, re_step, re_level, readcheck, subject, rownum r "+
					   "from (select * "+
					   "from message where "+keyword+" like '%"+search+"%' order by re_date desc ) order by re_date desc ) where r >= ? and r <= ?");
			   
			   pstmt.setInt(1, start);
			   pstmt.setInt(2, end);
			   
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()) {
				   		messageList = new ArrayList(end);
				   		do {
				   		messageDTO message = new messageDTO();
						message.setNum(rs.getInt("num"));
						message.setWriter(rs.getString("writer"));
						message.setReceiver(rs.getString("receiver"));
						message.setContent(rs.getString("content"));
						message.setRe_date(rs.getTimestamp("re_date"));
						message.setRef(rs.getInt("ref"));
						message.setRe_step(rs.getInt("re_step"));
						message.setRe_level(rs.getInt("re_level"));
						message.setReadcheck(rs.getInt("readcheck"));
						message.setSubject(rs.getString("subject"));
						message = new messageDTO();
						messageList.add(message);
				   		}while(rs.next());
					}
				
	} catch(Exception ex) {
		ex.printStackTrace();
	} finally {
		ConnectionDAO.close(rs, pstmt, conn);
	}


	return messageList;
	}

	 public int getMessageCount1(String keyword, String search) {
			int count=0;
			
			try {
				conn = ConnectionDAO.getConnection();
			    
				String sql ="select count(*) from message where "+keyword+" like '%"+search+"%'";
				pstmt = conn.prepareStatement("select count(*) from message");
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count= rs.getInt(1); 
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}
			return count; 
		}
	 public List getMessage2(String id,int start, int end) throws Exception { //�삤踰꾨줈�뵫: 硫붿냼�뱶�쓽 �씠由꾩� 媛숈�留� 留ㅺ컻蹂��닔 媛쒖닔媛� �떎瑜대�濡� �뿉�윭x
	      List messageList=null;
	      try {
	         conn = ConnectionDAO.getConnection();
	         pstmt = conn.prepareStatement(
	               "select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject,r "+
	               "from (select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject,rownum r " +
	               "from (select * " +
	               "from message where receiver = ? order by re_date desc) order by re_date desc ) where r >= ? and r <= ? ");
	               pstmt.setString(1, id); 
	               pstmt.setInt(2, start); 
	               pstmt.setInt(3, end);
	               
	               rs = pstmt.executeQuery();
	               if (rs.next()) {
	                  messageList = new ArrayList(end); 
	                  do{ 
	                     messageDTO message= new messageDTO();
	                     message.setNum(rs.getInt("num"));
	                     message.setWriter(rs.getString("writer"));
	                     message.setReceiver(rs.getString("receiver"));
	                     message.setContent(rs.getString("content"));
	                     message.setRe_date(rs.getTimestamp("re_date"));
	                     message.setRef(rs.getInt("ref"));
	                     message.setRe_step(rs.getInt("re_step"));
	                     message.setRe_level(rs.getInt("re_level"));
	                     message.setReadcheck(rs.getInt("readcheck"));
	                     message.setSubject(rs.getString("subject"));
	                     messageList.add(message); 
	                  }while(rs.next());
	               }
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         ConnectionDAO.close(rs, pstmt, conn);
	      }

	      
	      return messageList;
	   }
	 
	// 검색 SearchList 리스트나열
	public List getMessageArticles(String col, String search, int start, int end) throws Exception 
	{
		List messageList=null;
		try
		{
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement(
					"select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject, r "+
					"from (select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject,rownum r " +
					"from (select * from message where "+col+" like '%"+search+"%' order by ref desc, re_step asc) order by ref desc, re_step asc) where r >= ? and r <= ? ");
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 												
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				messageList = new ArrayList(end); 
				do
				{ 
					messageDTO message= new messageDTO();
					message.setNum(rs.getInt("num"));
					message.setWriter(rs.getString("writer"));
					message.setReceiver(rs.getString("receiver"));
					message.setRe_date(rs.getTimestamp("re_date"));
					message.setContent(rs.getString("content"));
					message.setRef(rs.getInt("ref"));
					message.setRe_step(rs.getInt("re_step"));
					message.setRe_level(rs.getInt("re_level"));
					message.setReadcheck(rs.getInt("readcheck"));
					message.setSubject(rs.getString("subject"));
					messageList.add(message); 
				}while(rs.next());
			}
		} 
		catch(Exception ex)
		{	ex.printStackTrace();		} 
		finally 
		{	ConnectionDAO.close(rs, pstmt, conn);		}		
		return messageList;
	}	 
	 
	// 연관검색 searchList 나열
		public ArrayList<messageDTO> getMessageArticles(String receiver, String col, String search, int start, int end) throws Exception {
			ArrayList<messageDTO> messageList=null;
			String find = search.replace(" ","");
			try 
			{
				conn = ConnectionDAO.getConnection();
				String sql = "select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject, r "+
						"from (select num,writer,receiver,re_date,content,ref,re_step,re_level,readcheck,subject,rownum r " +
						"from (select * from message where receiver=? and "+col+" like '%"+find.substring(0,1)+"%'";
				String order = "order by ref desc, re_step asc) order by ref desc, re_step asc) where r >= ? and r <= ? ";
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
						pstmt.setString(1, receiver);
						pstmt.setInt(2, start); 
						pstmt.setInt(3, end); 						
								
						rs = pstmt.executeQuery();
						if (rs.next())
						{
							messageList = new ArrayList<messageDTO>(end); 
								do
								{ 
									messageDTO message= new messageDTO();
									message.setNum(rs.getInt("num"));
									message.setWriter(rs.getString("writer"));
									message.setReceiver(rs.getString("receiver"));
									message.setRe_date(rs.getTimestamp("re_date"));
									message.setContent(rs.getString("content"));
									message.setRef(rs.getInt("ref"));
									message.setRe_step(rs.getInt("re_step"));
									message.setRe_level(rs.getInt("re_level"));
									message.setReadcheck(rs.getInt("readcheck"));
									message.setSubject(rs.getString("subject"));
									
									messageList.add(message); 
								}while(rs.next());
						}
			}
			catch(Exception ex) 
			{		ex.printStackTrace();	} 
			finally
			{		ConnectionDAO.close(rs, pstmt, conn);	}	
			return messageList;
			}	 
	}


