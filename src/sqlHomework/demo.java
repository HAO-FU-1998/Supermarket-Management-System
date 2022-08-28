package sqlHomework;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://127.0.0.1/testone?user=root&password=Fu161018&"
				+ "useSSL=true";
		String name = "com.mysql.jdbc.Driver";
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet reset = null;
		try {
			Class.forName(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql = "select * from class;";
		pstmt = con.prepareStatement(sql);
		reset = pstmt.executeQuery();
		while(reset.next()){
			System.out.println(reset.getString(1)+reset.getString(2));
		}
	}

}
