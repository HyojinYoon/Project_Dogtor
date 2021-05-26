package board;
import java.sql.*;
import java.util.*; 
import board.ConnectionDAO;
import board.datDTO;

public class datDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	

	//댓글 DB에 삽입 (datWritePro에서 사용) - 인선
	public void insertArticleDat(datDTO dto) throws Exception {
		
		String sql="";
		try {
			conn = ConnectionDAO.getConnection(); 
			sql = "insert into DatBoard values(Board_seq.NEXTVAL,?,?,?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContentDat());
			pstmt.setInt(3, dto.getSuperNum());
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionDAO.close(rs, pstmt, conn);
		}
	}
	
	//원글에 대한 댓글 갯수 확인 (freeContent에서 사용) - 인선
	public int getArticleCountDat(int superNum) throws Exception {
		int x=0;
		try {
			conn = ConnectionDAO.getConnection();
			pstmt = conn.prepareStatement("select count(*) from DatBoard where superNum=?");
			pstmt.setInt(1, superNum);
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
		
	
	// 원글에 해당하는 댓글 리스트.받아오는 메서드. (freeContent.jsp에서 사용) - 인선
	public List getDatArticles(int superNum) throws Exception {
		   List articleList=null;
		   try {
		      conn = ConnectionDAO.getConnection();
		      pstmt = conn.prepareStatement("select * from datboard where superNum= ? order by reg_date desc");
		      pstmt.setInt(1, superNum);
              rs = pstmt.executeQuery();
		      if (rs.next()) {
		            articleList = new ArrayList(); 
		       do{ 
		            datDTO article= new datDTO();
		            article.setNum(rs.getInt("num"));
		            article.setWriter(rs.getString("writer"));
		            article.setReg_date(rs.getTimestamp("reg_date"));
		            article.setSuperNum(rs.getInt("superNum"));
		            article.setContentDat(rs.getString("contentDat"));
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
	   
	 //댓글 수정폼(updateform) jsp파일에서 사용 - 수정대상의 게시글 검색
		public datDTO updateGetArticle(int num) throws Exception {
			datDTO article= new datDTO();
			try {
				conn = ConnectionDAO.getConnection();
				pstmt = conn.prepareStatement(
				"select * from DatBoard where num = ?"); 
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setContentDat(rs.getString("contentDat"));
					article.setSuperNum(rs.getInt("superNum"));
					article.setReg_date(rs.getTimestamp("reg_date"));
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}

			return article;
		}
		
		//게시글 수정시, 회원가입 폼의 닉네임과 비밀번호가 일치할 경우 수정 허용 (datUpdatePro에서 사용) - 인선
		public boolean nickCheck(String nick, String passwd, datDTO dto) {
			boolean result = false;
			String id = "";
			try {
				conn=ConnectionDAO.getConnection();
				pstmt=conn.prepareStatement("select * from MEMBER where pw = ? and nick = ?");
				pstmt.setString(1,  passwd);
				pstmt.setString(2,  nick);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					pstmt=conn.prepareStatement("update DatBoard set contentDat=? where num=? ");
					pstmt.setString(1, dto.getContentDat());
					pstmt.setInt(2, dto.getNum());
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
		
		//관리자가 댓글 delete (datdeletePro에서 사용) - 인선
		public int deleteArticle(int num) throws Exception {
			
			int x=-1;
			try {
				conn = ConnectionDAO.getConnection();
				pstmt = conn.prepareStatement(
				"select * from DatBoard where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()){
						pstmt = conn.prepareStatement("delete from DatBoard where num=?");
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
		
		//회원이 댓글 delete - 비밀번호 체크
		public int deleteArticle(int num, String passwd) throws Exception {
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
						pstmt = conn.prepareStatement("delete from DatBoard where num=?");
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
		
		//원글에 대한 댓글 갯수
		public int getDatCount(int num) {
			int datCount = 0;
			try {
				conn = ConnectionDAO.getConnection();
				pstmt = conn.prepareStatement(
				"select count(*) from datboard where superNum = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()){
					datCount = rs.getInt(1);
				} 
			}catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				ConnectionDAO.close(rs, pstmt, conn);
			}
			return datCount;
			}
}
