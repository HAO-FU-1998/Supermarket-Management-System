package sqlHomework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemovedUtil {

	private PreparedStatement pstmt = null;	
	private ResultSet reset = null;
	private String sql = null;
	public RemovedUtil() {
	}

	public void removeGoods(String goodtype, String reason, Connection con, String gid) throws SQLException {
		sql = "update " + goodtype + "_goods" + " set quantity = -1 where gid = " + "'" + gid +"'";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		pstmt = con.prepareStatement("select count(*) from " + goodtype + "_rgoods");
		reset = pstmt.executeQuery();
		reset.next();
		int count = reset.getInt(1);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(now);
		sql = "insert into " + goodtype +"_rgoods values('" + String.format("%03d", count + 1) + "', '" + gid + "', '" + time +"', '"
				+ reason + "')";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
	}

	public ResultSet chakan(String type,Connection con) throws SQLException {
		sql = "select * from "+ type+"_rgoods";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		reset = pstmt.executeQuery();
		return reset;
	}

	public ResultSet caigou(String type, Connection con) throws SQLException {
		sql = "select * from purchase_records_"+ type;
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		reset = pstmt.executeQuery();
		return reset;
	}

}
