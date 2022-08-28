package GUI;

import java.sql.SQLException;

import sqlHomework.SuperMarket;

//此类用于初始化
public class Initalization {

	private String user = null;
	private String password = null;
	public Initalization(String user, String password) {
		this.user = user;
		this.password = password.toString();
	}

	public void firstInit() {
		//初始化服务器类
		SuperMarket server = null;
		try {
			server = new SuperMarket(this.user, this.password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//开启主界面，关闭登录界面
		FunctionUI funUI = new FunctionUI(server);
		
	}

}
