package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConnectionDAO {//1, 2단계와 close하는 것을 여기에 분리할 것이다.

	public static Connection getConnection() {
		Connection conn = null;
		try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");  

	          String dbHost = "jdbc:oracle:thin:@masternull.iptime.org:1521:orcl";
	          String user ="team02";
	          String pass ="team";
	          conn = DriverManager.getConnection(dbHost, user, pass);
		}catch(Exception e){
			  e.printStackTrace();
		}
		return conn; //3단계에서 conn사용해야하니 리턴타입으로 줌
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
	
		if(rs != null) {try {rs.close();}catch(SQLException s) {s.printStackTrace();}}
		if(pstmt != null) {try {pstmt.close();}catch(SQLException s) {s.printStackTrace();}}
		if(conn != null) {try {conn.close();}catch(SQLException s) {s.printStackTrace();}}
	}
}
