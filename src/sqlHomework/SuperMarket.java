package sqlHomework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuperMarket {

	private static final Object[] Sting = null;
	private String name  = null;
	private Connection con = null;
	private SelectUtil stUtil = null;
	private SaleUtil saUtil = null;
	private PurchaseUtil pcUtil = null;
	private RemovedUtil rmUtil = null;
	private String user = null;
	private String password = null;
	private String[] foodsCol = {"gid", "name", "brand", "deadline", "origin", "price", "quantity"};
	private String[] clothesCol = {"gid", "name", "brand", "colour", "size",
				"people_of_gender", "people_of_age1", "people_of_age2", "price", "quantity",};
	public SuperMarket(String user, String password) throws ClassNotFoundException, SQLException{
		//连接数据库
		this.name = "com.mysql.jdbc.Driver";
		Class.forName(name);
		String url = new String("jdbc:mysql://127.0.0.1/supermarket?"
				+ "characterEncoding=utf8&useSSL=true");
		this.user = user;
		this.password = password;
		this.con = DriverManager.getConnection(url, this.user, this.password);
		this.stUtil = new SelectUtil();
		this.saUtil = new SaleUtil();
		this.rmUtil = new RemovedUtil();
		this.pcUtil = new PurchaseUtil();
	}
	
	public String[][] selectFoodGoods(String[] par) throws SQLException{
		ResultSet reset = stUtil.selectFoodGoods(con, par, foodsCol);
		List<String[]> list = new ArrayList<String[]>();
		String[] result = new String[foodsCol.length + 1];
		while(reset.next()){//获得查询结果，是一个多行的list
			for(int i = 1; i <= foodsCol.length; ++i){
				if(i == 4){
					result[i - 1] = reset.getDate(i).toString();
				}else if(i == 6){
					result[i - 1] = String.valueOf(reset.getDouble(i));
				}else if(i == 7){
					result[i - 1] = String.valueOf(reset.getInt(i));
					if(reset.getInt(i) == -1){
						result[foodsCol.length] = "商品已下架";
					}
				}else{
					result[i - 1] = reset.getString(i);
				}
			}
			list.add(result);
			result = new String[foodsCol.length + 1];
		}
		return list.toArray(new String[list.size()][]);
	}
	
	public String[][] selectClothesGoods(String[] par) throws SQLException{
		ResultSet reset = stUtil.selectClothesGoods(con, par, clothesCol);
		List<String[]> list = new ArrayList<String[]>();
		String[] result = new String[clothesCol.length + 1];
		while(reset.next()){//获得查询结果，是一个多行的list
			for(int i = 1; i <= clothesCol.length; ++i){
				if( i == 7|| i== 8){
					result[i -1] = String.valueOf(reset.getInt(i));
				}else if(i == 10){
					result[i - 1] = String.valueOf(reset.getInt(i));
					if(reset.getInt(i) == -1){
						result[clothesCol.length] = new String("商品已下架");
					}
				}else if(i == 9){
					result[i - 1] = String.valueOf(reset.getDouble(i));
				}else{
					result[i - 1] = reset.getString(i);
				}
			}
			list.add(result);
			result = new String[clothesCol.length + 1];
		}
		return list.toArray(new String[list.size()][]);
	}

	public String[][] selectSalesFoodGoods(String[] par) throws SQLException {
		ResultSet reset = saUtil.selectSalesFoodGoods(con, par, foodsCol);
		List<String[]> list = new ArrayList<String[]>();
		String[] result = new String[foodsCol.length + 1 + 6];
		while(reset.next()){//获得查询结果，是一个多行的list
			for(int i = 1; i <= foodsCol.length + 6; ++i){
				if(i == 4 || i == foodsCol.length + 2){
					result[i - 1] = reset.getDate(i).toString();
				}else if(i == 6 || i ==foodsCol.length + 5 || i == foodsCol.length + 6){
					result[i - 1] = String.valueOf(reset.getDouble(i));
				}else if(i == 7 || i ==foodsCol.length + 4){
					result[i - 1] = String.valueOf(reset.getInt(i));
					if(reset.getInt(i) == -1 && i == 7){
						result[foodsCol.length + 6] = "商品已下架";
					}
				}else{
					result[i - 1] = reset.getString(i);
				}
			}
			list.add(result);
			result = new String[foodsCol.length + 1 + 6];
		}
		return list.toArray(new String[list.size()][]);
	}

	public String[][] selectSalesClothesGoods(String[] par) throws SQLException {
		ResultSet reset = saUtil.selectSalesClothesGoods(con, par, clothesCol);
		List<String[]> list = new ArrayList<String[]>();
		String[] result = new String[clothesCol.length + 1 + 6];
		while(reset.next()){//获得查询结果，是一个多行的list
			for(int i = 1; i <= clothesCol.length + 6; ++i){
				if(i == clothesCol.length + 2){
					result[i - 1] = reset.getDate(i).toString();
				}else if(i == 9 || i ==clothesCol.length + 5 || i == clothesCol.length + 6){
					result[i - 1] = String.valueOf(reset.getDouble(i));
				}else if(i == 7 || i == 8 || i == 10|| i ==clothesCol.length + 4){
					result[i - 1] = String.valueOf(reset.getInt(i));
					if(reset.getInt(i) == -1 && i == 10){
						result[clothesCol.length + 6] = "商品已下架";
					}
				}else{
					result[i - 1] = reset.getString(i);
				}
			}
			list.add(result);
			result = new String[clothesCol.length + 1 + 6];
		}
		return list.toArray(new String[list.size()][]);
	}

	public void makeFile(String start, String end, String type) throws SQLException {
		saUtil.stFoodGoodFile(start, end, con, type);
	}

	public void removeGoods(String goodtype, String reason, String gid) throws SQLException {
		rmUtil.removeGoods(goodtype, reason, con, gid);
	}

	public String[][] caigoukucunFood() throws SQLException {
		String[] par = new String[7];
		for(int i = 0; i < par.length; ++i){
			par[i] = "全部";
		}
		String[] result0 = new String[3];
		List<String[]> result = new ArrayList<String[]>();
		String[][] temp = this.selectFoodGoods(par);
		for(int i = 0; i < temp.length; ++i){
			if(temp[i][foodsCol.length - 1].equals("-1")){
				continue;
			}
			result0[0] = temp[i][0];
			result0[1] = temp[i][foodsCol.length - 1];
			result0[2] = "0";
			result.add(result0);
			result0 = new String[3];
		}
		return  result.toArray(new String[result.size()][]);
	}

	public void caigoukucunFoodBuy(String[][] array, String type) throws SQLException {
		pcUtil.dayPurchaseFood(array, con, type);
	}

	public String[][] caigoukucunClothes() throws SQLException {
		String[] par = new String[10];
		for(int i = 0; i < par.length; ++i){
			par[i] = "全部";
		}
		String[] result0 = new String[3];
		List<String[]> result = new ArrayList<String[]>();
		String[][] temp = this.selectClothesGoods(par);
		for(int i = 0; i < temp.length; ++i){
			if(temp[i][clothesCol.length - 1].equals("-1")){
				continue;
			}
			result0[0] = temp[i][0];
			result0[1] = temp[i][clothesCol.length - 1];
			result0[2] = "0";
			result.add(result0);
			result0 = new String[3];
		}
		return  result.toArray(new String[result.size()][]);
	}

	public boolean xiaoshuo(String gid, String unitprice, String quantity, String totalprice, String type) throws SQLException {
		saUtil.xiaoshou(gid, unitprice, quantity, totalprice, type, con);
		String sql = "select quantity from " + type + "_goods where gid = '"+gid+"'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet reset = pstmt.executeQuery();
		reset.next();
		return (reset.getInt(1) < 5?true:false);
	}

	public void shangfood(String text, String text2, String text3, String text4, String text5, String text6,
			String text7) throws SQLException {
		String sql = "insert into food_goods values ('" + text+"', '" + text2+"', '"+text3+"', '"+text4+"', '"+text5+"', "+
			text6 + ","+text7+")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
	}

	public void shangclothes(String text, String text2, String text3, String text4, String text5, String text6,
			String text7, String text8, String text9, String text10) throws SQLException {
		String sql = "insert into clothes_goods values ('" + text+"', '" + text2+"', '"+text3+"', '"+text4+"', '"+text5+"', '"+
				text6 + "', "+text7+", " +text8+ ","+text9+","+text10+ ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		
	}

	public String[][] chakanremoved() throws SQLException {
		ResultSet reset = rmUtil.chakan("food", con);
		List<String[]> list = new ArrayList<String[]>();
		String[] result = new String[5];
		while(reset.next()){
			result[0] = "食物";
			result[1] = reset.getString(1);
			result[2] = reset.getString(2);
			result[3] = reset.getDate(3).toString();
			result[4] = reset.getString(4);
			list.add(result);
			result = new String[5];
		}
		reset = rmUtil.chakan("clothes", con);
		while(reset.next()){
			result[0] = "衣服";
			result[1] = reset.getString(1);
			result[2] = reset.getString(2);
			result[3] = reset.getDate(3).toString();
			result[4] = reset.getString(4);
			list.add(result);
			result = new String[5];
		}
		return list.toArray(new String[list.size()][]);
	}

	public String[] init(String field, String type, String typef) throws SQLException {
		ResultSet reset = stUtil.init(field, type, con);
		List<String> list = new ArrayList<String>();
		String result = new String();
		while(reset.next()){
			if(typef.equals("String")){
				result = reset.getString(1);
			}else if(typef.equals("Int")){
				result = String.valueOf(reset.getInt(1));
			}else if(typef.equals("Double")){
				result = String.valueOf(reset.getDouble(1));
			}else{
				result = reset.getDate(1).toString();
			}
			list.add(result);
			result = new String();
		}
		return list.toArray(new String[list.size()]);
	}

	public String[][] caigoubiao() throws SQLException {
		ResultSet reset = rmUtil.caigou("food", con);
		List<String[]> list = new ArrayList<String[]>();
		String[] result = new String[5];
		while(reset.next()){
			result[0] = "食物";
			result[1] = reset.getString(1);
			result[2] = reset.getDate(2).toString();
			result[3] = reset.getString(3);
			result[4] = String.valueOf(reset.getInt(4));
			list.add(result);
			result = new String[5];
		}
		reset = rmUtil.caigou("clothes", con);
		while(reset.next()){
			result[0] = "衣服";
			result[1] = reset.getString(1);
			result[2] = reset.getDate(2).toString();
			result[3] = reset.getString(3);
			result[4] = String.valueOf(reset.getInt(4));
			list.add(result);
			result = new String[5];
		}
		return list.toArray(new String[list.size()][]);
	}
}
