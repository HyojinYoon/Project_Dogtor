package dogtor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDAO { // 1,2�ܰ�
	public static Connection getConnection() {
		Connection conn = null;
		try {
			//1�ܰ�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2�ܰ�
			String dbHost = "jdbc:oracle:thin:@masternull.iptime.org:1521:orcl";
			String user = "team02";
			String pass = "team";
			conn = DriverManager.getConnection(dbHost, user, pass);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
		if(rs != null) {try {rs.close();}catch(SQLException s) {}} // DB���� �߻��ϴ� exception
		if(pstmt != null) {try {pstmt.close();}catch(SQLException s) {}}
		if(conn != null) {try {conn.close();}catch(SQLException s) {}}
	}
}
