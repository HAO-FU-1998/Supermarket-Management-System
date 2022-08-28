package sqlHomework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectUtil {

	private PreparedStatement pstmt = null;	
	private ResultSet reset = null;
	private String sql = null;
	
	public SelectUtil() {
		// TODO Auto-generated constructor stub
	}

	public ResultSet selectFoodGoods(Connection con, String[] par, String[] foodsCol) throws SQLException {
		sql = "select * from food_goods";
		String sql0 = null;
		boolean label = true;
		for(int i = 0; i < par.length; ++i){
			if(par[i] != "全部"){
				if(label){
					sql0 = " where ";
					label = false;
				}else{
					sql0 = " and ";
				}
				if(i == 5 || i == 6){
					sql0 = sql0 +"food_goods." + foodsCol[i] + "=" + par[i];
				}else{
					sql0 = sql0 +"food_goods." + foodsCol[i] + "=" + "'" + par[i] + "'";
				}
				sql += sql0;
				sql0 = null;
			}
		}
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		reset = pstmt.executeQuery();
		return reset;
	}

	public ResultSet selectClothesGoods(Connection con, String[] par, String[] clothesCol) throws SQLException {
		sql = "select * from clothes_goods";
		String sql0 = null;
		boolean label = true;
		for(int i = 0; i < par.length; ++i){
			if(par[i] != "全部"){
				if(label){
					sql0 = " where ";
					label = false;
				}else{
					sql0 = " and ";
				}
				if(i == 0|| i == 1||i == 2 || i == 3 || i == 4|| i == 5 ){
					sql0 = sql0 + "clothes_goods." + clothesCol[i] + "=" + "'" + par[i] + "'";
				}else{
					sql0 = sql0 + "clothes_goods." + clothesCol[i] + "=" + par[i];
				}
				sql += sql0;
				sql0 = null;
			}
		}
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		reset = pstmt.executeQuery();
		return reset;
	}

	public ResultSet init(String fields, String type, Connection con) throws SQLException {
		sql = "select " + fields +" from " + type + "_goods";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		reset = pstmt.executeQuery();
		return reset;
	}

}
