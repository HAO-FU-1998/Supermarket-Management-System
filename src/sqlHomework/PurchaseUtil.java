package sqlHomework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseUtil {

	String sql = null;
	PreparedStatement pstmt = null;
	ResultSet reset = null;
	public PurchaseUtil() {
		// TODO Auto-generated constructor stub
	}

	public void dayPurchaseFood(String[][] array, Connection con, String type) throws SQLException {
		for(int i = 0; i < array.length; ++i){
			sql = "select count(*) from purchase_records_" + type;
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			reset = pstmt.executeQuery();
			reset.next();
			int count = reset.getInt(1);
			sql = "select g.price from "+ type +"_goods g where g.gid = '" + array[i][0] +"'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			reset = pstmt.executeQuery();
			reset.next();
			double price = reset.getDouble(1);
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = sdf.format(now);
			sql = "insert into purchase_records_"+type+" values('" + String.format("%03d", count + 1)+"', '" + nowDate+"', '" +
					array[i][0] + "', " + array[i][1] + ", " + String.valueOf(price) +", " + String.valueOf(price * Integer.valueOf(array[i][1])+")");
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			sql = "update "+type+"_goods set quantity = quantity + " + array[i][1] + " where gid = "+ "'" + array[i][0]+"'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}
	}

}
