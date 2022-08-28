package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import sqlHomework.SuperMarket;
import java.awt.Font;
import javax.swing.UIManager;

public class ShangPinXiaJia {

	public JTabbedPane total = new JTabbedPane();
	public JPanel shangpinxiajia = new JPanel();
	public JPanel sub = new JPanel();
	public JPanel sub1 = new JPanel();
	private JTextField reason;
	private SuperMarket server;
	private String goodtype = "clothes";
	
	public ShangPinXiaJia(final SuperMarket server){
		this.server = server;
		sub.setLayout(new GridLayout(3,1, 0, 0));
//		JPanel shangpinxiajia = new JPanel();
		shangpinxiajia.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));
	    //FlowLayout layout = (FlowLayout) shangpinxiajia.getLayout();
		sub.add(shangpinxiajia);
		shangpinxiajia.setBorder(new TitledBorder(null, "��Ʒ�¼�"));
		
	    
	    class MyActionListener1 implements ActionListener{

	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		if("ʳ��".equals(((JRadioButton)e.getSource()).getText())){
	    			goodtype = "food";
	    		}else{
	    			goodtype = "clothes";
	    		}
	    	}
	    }
		JRadioButton clothesType = new JRadioButton("�·�", true);
		clothesType.setFont(new Font("����", Font.BOLD, 16));
		clothesType.setPreferredSize(new Dimension(100, 30));
		shangpinxiajia.add(clothesType);
		clothesType.addActionListener(new MyActionListener1());
		
		JRadioButton foodType = new JRadioButton("ʳ��");
		foodType.setFont(new Font("����", Font.BOLD, 16));
		foodType.setPreferredSize(new Dimension(100, 30));
		shangpinxiajia.add(foodType);
		foodType.addActionListener(new MyActionListener1());
		
		ButtonGroup bugp = new ButtonGroup();
		bugp.add(foodType);
		bugp.add(clothesType);
		
		final JComboBox<String> gid = new JComboBox<String>();
		gid.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		shangpinxiajia.add(gid);
		List<String> listgid = FunctionUI.getGidSetFood();
		Iterator<String> it = listgid.iterator();
		while(it.hasNext()){
			gid.addItem(it.next());
		}
		listgid = FunctionUI.getGidSetClothes();
		it = listgid.iterator();
		while(it.hasNext()){
			gid.addItem(it.next());
		}
		
		JLabel reasonLabel = new JLabel("�¼�ԭ��");
		reasonLabel.setFont(new Font("����", Font.BOLD, 16));
		reasonLabel.setPreferredSize(new Dimension(100, 30));
		shangpinxiajia.add(reasonLabel);
		
		reason = new JTextField();
		reason.setFont(new Font("������", Font.PLAIN, 16));
		reason.setColumns(30);
		reason.setPreferredSize(new Dimension(100, 30));
		shangpinxiajia.add(reason);
		
		
		class MyActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] par = ("clothes".equals(goodtype)? new String[10]:new String[7]);
				par[0] = gid.getSelectedItem().toString();
				for(int i = 1; i < par.length; i++){
					par[i] = "ȫ��";
				}
				String[][] result = null;
				try {
					result = ("clothes").equals(goodtype)?server.selectClothesGoods(par):server.selectFoodGoods(par);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(result.length == 0){
					JOptionPane.showMessageDialog(null, "��Ʒ������", "�¼�ʧ��", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(result[0][result[0].length - 2].equals("-1")){
					JOptionPane.showMessageDialog(null, "��Ʒ���¼ܣ��벻Ҫ�ظ��¼�", "�¼�ʧ��", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					server.removeGoods(goodtype, reason.getText(), gid.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "��Ʒ�¼ܳɹ�", "��ʾ", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		JButton removeButton = new JButton("\u786E\u8BA4");
		removeButton.setFont(new Font("����", Font.BOLD, 16));
		shangpinxiajia.add(removeButton);
		removeButton.addActionListener(new MyActionListener());
		
		
		
		
		
		
		JPanel foodshangjia = new JPanel();
		foodshangjia.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));
	    //FlowLayout layout = (FlowLayout) shangpinxiajia.getLayout();
		sub.add(foodshangjia);
		foodshangjia.setBorder(new TitledBorder(null, "ʳ����Ʒ�ϼ�"));
		
		JLabel foodshanglabel1 = new JLabel("��Ʒ��ţ�");
		foodshanglabel1.setFont(new Font("����", Font.BOLD, 16));
		final JTextField foodshangtext1 = new JTextField(10);
		foodshangtext1.setFont(new Font("������", Font.PLAIN, 16));
		JLabel foodshanglabel2 = new JLabel("��Ʒ���ƣ�");
		foodshanglabel2.setFont(new Font("����", Font.BOLD, 16));
		final JTextField foodshangtext2 = new JTextField(10);
		foodshangtext2.setFont(new Font("������", Font.PLAIN, 16));
		JLabel foodshanglabel3 = new JLabel("��ƷƷ�ƣ�");
		foodshanglabel3.setFont(new Font("����", Font.BOLD, 16));
		final JTextField foodshangtext3 = new JTextField(10);
		foodshangtext3.setFont(new Font("������", Font.PLAIN, 16));
		JLabel foodshanglabel4 = new JLabel("��Ʒ��ֹ���ڣ�");
		foodshanglabel4.setFont(new Font("����", Font.BOLD, 16));
		final JTextField foodshangtext4 = new JTextField(10);
		foodshangtext4.setFont(new Font("������", Font.PLAIN, 16));
		JLabel foodshanglabel5 = new JLabel("��Ʒԭ���أ�");
		foodshanglabel5.setFont(new Font("����", Font.BOLD, 16));
		final JTextField foodshangtext5 = new JTextField(10);
		foodshangtext5.setFont(new Font("������", Font.PLAIN, 16));
		JLabel foodshanglabel6 = new JLabel("��Ʒ�۸�");
		foodshanglabel6.setFont(new Font("����", Font.BOLD, 16));
		final JTextField foodshangtext6 = new JTextField(10);
		foodshangtext6.setFont(new Font("������", Font.PLAIN, 16));
		JLabel foodshanglabel7 = new JLabel("��Ʒ��棺");
		foodshanglabel7.setFont(new Font("����", Font.BOLD, 16));
		final JTextField foodshangtext7 = new JTextField(10);
		foodshangtext7.setFont(new Font("������", Font.PLAIN, 16));
		JButton foodshangbu = new JButton("\u786E\u8BA4");
		foodshangbu.setFont(new Font("����", Font.BOLD, 16));
		foodshangjia.add(foodshanglabel1);
		foodshangjia.add(foodshangtext1);
		foodshangjia.add(foodshanglabel2);
		foodshangjia.add(foodshangtext2);
		foodshangjia.add(foodshanglabel3);
		foodshangjia.add(foodshangtext3);
		foodshangjia.add(foodshanglabel4);
		foodshangjia.add(foodshangtext4);
		foodshangjia.add(foodshanglabel5);
		foodshangjia.add(foodshangtext5);
		foodshangjia.add(foodshanglabel6);
		foodshangjia.add(foodshangtext6);
		foodshangjia.add(foodshanglabel7);
		foodshangjia.add(foodshangtext7);
		foodshangjia.add(foodshangbu);
		foodshangbu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					server.shangfood(foodshangtext1.getText(),foodshangtext2.getText(),foodshangtext3.getText(),foodshangtext4.getText(),foodshangtext5.getText(),
							foodshangtext6.getText(),foodshangtext7.getText());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "��Ʒ�ϼ�ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "��Ʒ�ϼܳɹ���", "�ɹ�",JOptionPane.WARNING_MESSAGE);
			}
		});
		
		
		
		
		
		
		
		JPanel clothesshangjia = new JPanel();
		clothesshangjia.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));
	    //FlowLayout layout = (FlowLayout) shangpinxiajia.getLayout();
		sub.add(clothesshangjia);
		clothesshangjia.setBorder(new TitledBorder(null, "\u8863\u670D\u5546\u54C1\u4E0A\u67B6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel clothesshanglabel1 = new JLabel("��Ʒ��ţ�");
		clothesshanglabel1.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext1 = new JTextField(10);
		clothesshangtext1.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel2 = new JLabel("��Ʒ���ƣ�");
		clothesshanglabel2.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext2 = new JTextField(10);
		clothesshangtext2.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel3 = new JLabel("��ƷƷ�ƣ�");
		clothesshanglabel3.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext3 = new JTextField(10);
		clothesshangtext3.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel4 = new JLabel("��Ʒ��ɫ��");
		clothesshanglabel4.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext4 = new JTextField(10);
		clothesshangtext4.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel5 = new JLabel("��Ʒ��С��");
		clothesshanglabel5.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext5 = new JTextField(10);
		clothesshangtext5.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel6 = new JLabel("��Ʒ�ʺ��Ա�");
		clothesshanglabel6.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext6 = new JTextField(10);
		clothesshangtext6.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel7 = new JLabel("��Ʒ�ʺϿ�ʼ���䣺");
		clothesshanglabel7.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext7 = new JTextField(10);
		clothesshangtext7.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel8 = new JLabel("��Ʒ�ʺϽ������䣺");
		clothesshanglabel8.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext8 = new JTextField(10);
		clothesshangtext8.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel9 = new JLabel("��Ʒ�۸�");
		clothesshanglabel9.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext9 = new JTextField(10);
		clothesshangtext9.setFont(new Font("������", Font.PLAIN, 16));
		JLabel clothesshanglabel10 = new JLabel("��Ʒ��棺");
		clothesshanglabel10.setFont(new Font("����", Font.BOLD, 16));
		final JTextField clothesshangtext10 = new JTextField(10);
		clothesshangtext10.setFont(new Font("������", Font.PLAIN, 16));
		JButton clothesshangbu = new JButton("\u786E\u8BA4");
		clothesshangbu.setFont(new Font("����", Font.BOLD, 16));
		clothesshangjia.add(clothesshanglabel1);
		clothesshangjia.add(clothesshangtext1);
		clothesshangjia.add(clothesshanglabel2);
		clothesshangjia.add(clothesshangtext2);
		clothesshangjia.add(clothesshanglabel3);
		clothesshangjia.add(clothesshangtext3);
		clothesshangjia.add(clothesshanglabel4);
		clothesshangjia.add(clothesshangtext4);
		clothesshangjia.add(clothesshanglabel5);
		clothesshangjia.add(clothesshangtext5);
		clothesshangjia.add(clothesshanglabel6);
		clothesshangjia.add(clothesshangtext6);
		clothesshangjia.add(clothesshanglabel7);
		clothesshangjia.add(clothesshangtext7);
		clothesshangjia.add(clothesshanglabel8);
		clothesshangjia.add(clothesshangtext8);
		clothesshangjia.add(clothesshanglabel9);
		clothesshangjia.add(clothesshangtext9);
		clothesshangjia.add(clothesshanglabel10);
		clothesshangjia.add(clothesshangtext10);
		clothesshangjia.add(clothesshangbu);
		clothesshangbu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					server.shangclothes(clothesshangtext1.getText(),clothesshangtext2.getText(),clothesshangtext3.getText(),
							clothesshangtext4.getText(),clothesshangtext5.getText(),
							clothesshangtext6.getText(),clothesshangtext7.getText(),clothesshangtext8.getText(),
							clothesshangtext9.getText(), clothesshangtext10.getText());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "��Ʒ�ϼ�ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "��Ʒ�ϼܳɹ���", "�ɹ�",JOptionPane.WARNING_MESSAGE);
			}
		});
		
		
		sub1.setLayout(new BorderLayout(0,0));
		JPanel chakanbuttonpanel = new JPanel();
		sub1.add(chakanbuttonpanel,BorderLayout.NORTH);
		JButton chakanbutton = new JButton("\u67E5\u8BE2");
		chakanbutton.setFont(new Font("����", Font.BOLD, 16));
		chakanbuttonpanel.add(chakanbutton);
		
		
		final String[] head = {"��Ʒ����","�¼ܱ��", "��Ʒԭʼ���", "�¼�ʱ��", "�¼�ԭ��"};
		String[][] result = null;
		DefaultTableModel dtm = new DefaultTableModel(result, head);
		final JTable table = new JTable(dtm);
		table.setFont(new Font("������", Font.PLAIN, 14));
		JScrollPane jspanel = new JScrollPane(table);
		jspanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		chakanbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String[][] result1 = null;
				try {
					result1 = server.chakanremoved();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultTableModel dtm1 = (DefaultTableModel) table.getModel();
				dtm1.setDataVector(result1, head);
				dtm1.fireTableStructureChanged();
			}
			
		});
		sub1.add(jspanel);
		//�ո��껬������table  
		
		
		total.add(sub,"��Ʒ�������"); 
		total.add(sub1, "�¼���Ʒ��Ϣ���");
	}
	public JTabbedPane getJpanel() {
		return this.total;
	}
}
