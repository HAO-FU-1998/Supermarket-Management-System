package GUI;

import java.sql.SQLException;

import sqlHomework.SuperMarket;

//�������ڳ�ʼ��
public class Initalization {

	private String user = null;
	private String password = null;
	public Initalization(String user, String password) {
		this.user = user;
		this.password = password.toString();
	}

	public void firstInit() {
		//��ʼ����������
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
		
		
		//���������棬�رյ�¼����
		FunctionUI funUI = new FunctionUI(server);
		
	}

}
