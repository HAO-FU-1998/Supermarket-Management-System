package sqlHomework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.SystemOutLogger;
public class SaleUtil {
	
	private PreparedStatement pstmt = null;	
	private ResultSet reset = null;
	private String sql = null;

	public SaleUtil() {
		// TODO Auto-generated constructor stub
	}

	public ResultSet selectSalesFoodGoods(Connection con, String[] par, String[] foodsCol) throws SQLException {
		sql = "select * from food_goods, sales_records_food sf where food_goods.gid = sf.gid";
		String sql0 = null;
		for(int i = 0; i < par.length; ++i){
			if(par[i] != "全部"){
				if(i == 5 || i == 6){
					sql0 = " and food_goods." + foodsCol[i] + "=" + par[i];
				}else if(i == par.length - 1||i== par.length - 2) {
					sql0 = " and sf.time " +(i == par.length - 1? "<":">") + "="+ "'" + par[i] + "'"; 
				}else{
					sql0 = " and food_goods." + foodsCol[i] + "=" + "'" + par[i] + "'";
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

	public ResultSet selectSalesClothesGoods(Connection con, String[] par, String[] clothesCol) throws SQLException {
		sql = "select * from clothes_goods, sales_records_clothes sc where clothes_goods.gid = sc.gid";
		String sql0 = null;
		for(int i = 0; i < par.length; ++i){
			if(par[i] != "全部"){
				if(i == 0|| i == 1||i == 2 || i == 3 || i == 4|| i == 5 ){
					sql0 =" and clothes_goods." + clothesCol[i] + "=" + "'" + par[i] + "'";
				}else if(i == par.length - 1||i== par.length - 2) {
					sql0 = " and sc.time " +(i == par.length - 1? "<":">") + "="+ "'" + par[i] + "'";
				}else{
					sql0 =" and clothes_goods." + clothesCol[i] + "=" + par[i];
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

	public void stFoodGoodFile(String start, String end, Connection con, String type) throws SQLException {
		sql = "select s.gid, sum(s.quantity), sum(total_price)  from sales_records_"+type+" s"
				+ " where s.time " + ">='" + start + "' and s.time <= '" + end +"' group by s.gid";
		pstmt = con.prepareStatement(sql);
		reset = pstmt.executeQuery();
		String sql0 = new String("select fg.brand, sum(fs.total_price) from " +type+"_goods fg, sales_records_"+type+" fs where fg.gid"
				+ " = fs.gid and fs.time >= '" + start + "' and fs.time <= '" + end + "' group by fg.brand");
		PreparedStatement pstmt0 = con.prepareStatement(sql0);
		ResultSet reset0 = pstmt0.executeQuery();
		readFoodFile(reset, reset0, start, end, type);
		System.out.println(sql);
		System.out.println(sql0);
	}

	private void readFoodFile(ResultSet reset, ResultSet reset0, String start, String end, String type) throws SQLException{
		HSSFWorkbook workbook= new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("商品编号");
		cell = row.createCell(1);
		cell.setCellValue("销售数量");
		cell = row.createCell(2);
		cell.setCellValue("销售金额");
		double total = 0;
		int i = 1;
		while(reset.next()){
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(reset.getString(1));
			cell = row.createCell(1);
			cell.setCellValue(reset.getInt(2));
			cell = row.createCell(2);
			cell.setCellValue(reset.getDouble(3));
			total += reset.getDouble(3);
			i++;
		}
		row = sheet.createRow(i);
		i++;
		cell = row.createCell(0);
		cell.setCellValue("品牌名称");
		cell = row.createCell(1);
		cell.setCellValue("销售金额");
		while(reset0.next()){
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(reset0.getString(1));
			cell = row.createCell(1);
			cell.setCellValue(reset0.getDouble(2));
			i++;
		}
		row = sheet.createRow(i);
		i++;
		cell = row.createCell(0);
		cell.setCellValue("超市单类别销售总金额");
		cell = row.createCell(1);
		cell.setCellValue(total);
		
		try {
			FileOutputStream fos = new FileOutputStream(start + "-" + end +type+ "_details.xls");
			workbook.write(fos);
			System.out.println("写入文件成功");
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("写入文件出错");
			e.printStackTrace();
		}
	}

	public void xiaoshou(String gid, String unitprice, String quantity, String totalprice, String type,
			Connection con) {
		try {
			con.setAutoCommit(false);
			updategoods(con, gid, quantity, type);
			insertsales(con, gid, unitprice, quantity, totalprice, type);
			con.commit();
		} catch (SQLException e) {
			System.out.println("事务执行异常");
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("事务回滚异常");
				e1.printStackTrace();
			} 
		}finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("事务关闭异常");
				e.printStackTrace();
			}
		}
	}

	private void insertsales(Connection con, String gid, String unitprice, String quantity, String totalprice, String type) throws SQLException{
		sql = "select count(*) from sales_records_" + type;
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("pstmt初始化出错");
			e.printStackTrace();
		}
		reset = pstmt.executeQuery();
		int count = 0;
		try {
			reset.next();
			count = reset.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		sql = "insert into sales_records_" + type + " values ('" + String.format("%03d", count + 1) +"', '" +now+ "', '"+gid+"', "+quantity+
				", " + unitprice +", "+totalprice+")";
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("pstmt初始化出错");
			e.printStackTrace();
		}
		pstmt.executeUpdate();
	}

	private void updategoods(Connection con, String gid, String quantity, String type) throws SQLException {
		sql = "update " + type+"_goods set quantity = quantity - "+quantity+" where gid = '" + gid +"'";
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("pstmt初始化出错");
			e.printStackTrace();
		}
		pstmt.executeUpdate();
		
	}

}
