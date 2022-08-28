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
		shangpinxiajia.setBorder(new TitledBorder(null, "商品下架"));
		
	    
	    class MyActionListener1 implements ActionListener{

	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		if("食物".equals(((JRadioButton)e.getSource()).getText())){
	    			goodtype = "food";
	    		}else{
	    			goodtype = "clothes";
	    		}
	    	}
	    }
		JRadioButton clothesType = new JRadioButton("衣服", true);
		clothesType.setFont(new Font("黑体", Font.BOLD, 16));
		clothesType.setPreferredSize(new Dimension(100, 30));
		shangpinxiajia.add(clothesType);
		clothesType.addActionListener(new MyActionListener1());
		
		JRadioButton foodType = new JRadioButton("食物");
		foodType.setFont(new Font("黑体", Font.BOLD, 16));
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
		
		JLabel reasonLabel = new JLabel("下架原因：");
		reasonLabel.setFont(new Font("黑体", Font.BOLD, 16));
		reasonLabel.setPreferredSize(new Dimension(100, 30));
		shangpinxiajia.add(reasonLabel);
		
		reason = new JTextField();
		reason.setFont(new Font("新宋体", Font.PLAIN, 16));
		reason.setColumns(30);
		reason.setPreferredSize(new Dimension(100, 30));
		shangpinxiajia.add(reason);
		
		
		class MyActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] par = ("clothes".equals(goodtype)? new String[10]:new String[7]);
				par[0] = gid.getSelectedItem().toString();
				for(int i = 1; i < par.length; i++){
					par[i] = "全部";
				}
				String[][] result = null;
				try {
					result = ("clothes").equals(goodtype)?server.selectClothesGoods(par):server.selectFoodGoods(par);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(result.length == 0){
					JOptionPane.showMessageDialog(null, "商品不存在", "下架失败", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(result[0][result[0].length - 2].equals("-1")){
					JOptionPane.showMessageDialog(null, "商品已下架，请不要重复下架", "下架失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					server.removeGoods(goodtype, reason.getText(), gid.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "商品下架成功", "提示", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		JButton removeButton = new JButton("\u786E\u8BA4");
		removeButton.setFont(new Font("黑体", Font.BOLD, 16));
		shangpinxiajia.add(removeButton);
		removeButton.addActionListener(new MyActionListener());
		
		
		
		
		
		
		JPanel foodshangjia = new JPanel();
		foodshangjia.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));
	    //FlowLayout layout = (FlowLayout) shangpinxiajia.getLayout();
		sub.add(foodshangjia);
		foodshangjia.setBorder(new TitledBorder(null, "食物商品上架"));
		
		JLabel foodshanglabel1 = new JLabel("商品编号：");
		foodshanglabel1.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField foodshangtext1 = new JTextField(10);
		foodshangtext1.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel foodshanglabel2 = new JLabel("商品名称：");
		foodshanglabel2.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField foodshangtext2 = new JTextField(10);
		foodshangtext2.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel foodshanglabel3 = new JLabel("商品品牌：");
		foodshanglabel3.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField foodshangtext3 = new JTextField(10);
		foodshangtext3.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel foodshanglabel4 = new JLabel("商品截止日期：");
		foodshanglabel4.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField foodshangtext4 = new JTextField(10);
		foodshangtext4.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel foodshanglabel5 = new JLabel("商品原产地：");
		foodshanglabel5.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField foodshangtext5 = new JTextField(10);
		foodshangtext5.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel foodshanglabel6 = new JLabel("商品价格：");
		foodshanglabel6.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField foodshangtext6 = new JTextField(10);
		foodshangtext6.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel foodshanglabel7 = new JLabel("商品库存：");
		foodshanglabel7.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField foodshangtext7 = new JTextField(10);
		foodshangtext7.setFont(new Font("新宋体", Font.PLAIN, 16));
		JButton foodshangbu = new JButton("\u786E\u8BA4");
		foodshangbu.setFont(new Font("黑体", Font.BOLD, 16));
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
					JOptionPane.showMessageDialog(null, "商品上架失败！", "错误", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "商品上架成功！", "成功",JOptionPane.WARNING_MESSAGE);
			}
		});
		
		
		
		
		
		
		
		JPanel clothesshangjia = new JPanel();
		clothesshangjia.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));
	    //FlowLayout layout = (FlowLayout) shangpinxiajia.getLayout();
		sub.add(clothesshangjia);
		clothesshangjia.setBorder(new TitledBorder(null, "\u8863\u670D\u5546\u54C1\u4E0A\u67B6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel clothesshanglabel1 = new JLabel("商品编号：");
		clothesshanglabel1.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext1 = new JTextField(10);
		clothesshangtext1.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel2 = new JLabel("商品名称：");
		clothesshanglabel2.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext2 = new JTextField(10);
		clothesshangtext2.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel3 = new JLabel("商品品牌：");
		clothesshanglabel3.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext3 = new JTextField(10);
		clothesshangtext3.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel4 = new JLabel("商品颜色：");
		clothesshanglabel4.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext4 = new JTextField(10);
		clothesshangtext4.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel5 = new JLabel("商品大小：");
		clothesshanglabel5.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext5 = new JTextField(10);
		clothesshangtext5.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel6 = new JLabel("商品适合性别：");
		clothesshanglabel6.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext6 = new JTextField(10);
		clothesshangtext6.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel7 = new JLabel("商品适合开始年龄：");
		clothesshanglabel7.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext7 = new JTextField(10);
		clothesshangtext7.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel8 = new JLabel("商品适合结束年龄：");
		clothesshanglabel8.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext8 = new JTextField(10);
		clothesshangtext8.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel9 = new JLabel("商品价格：");
		clothesshanglabel9.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext9 = new JTextField(10);
		clothesshangtext9.setFont(new Font("新宋体", Font.PLAIN, 16));
		JLabel clothesshanglabel10 = new JLabel("商品库存：");
		clothesshanglabel10.setFont(new Font("黑体", Font.BOLD, 16));
		final JTextField clothesshangtext10 = new JTextField(10);
		clothesshangtext10.setFont(new Font("新宋体", Font.PLAIN, 16));
		JButton clothesshangbu = new JButton("\u786E\u8BA4");
		clothesshangbu.setFont(new Font("黑体", Font.BOLD, 16));
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
					JOptionPane.showMessageDialog(null, "商品上架失败！", "错误", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "商品上架成功！", "成功",JOptionPane.WARNING_MESSAGE);
			}
		});
		
		
		sub1.setLayout(new BorderLayout(0,0));
		JPanel chakanbuttonpanel = new JPanel();
		sub1.add(chakanbuttonpanel,BorderLayout.NORTH);
		JButton chakanbutton = new JButton("\u67E5\u8BE2");
		chakanbutton.setFont(new Font("黑体", Font.BOLD, 16));
		chakanbuttonpanel.add(chakanbutton);
		
		
		final String[] head = {"商品类型","下架编号", "商品原始编号", "下架时间", "下架原因"};
		String[][] result = null;
		DefaultTableModel dtm = new DefaultTableModel(result, head);
		final JTable table = new JTable(dtm);
		table.setFont(new Font("新宋体", Font.PLAIN, 14));
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
		//刚搞完滑动面板和table  
		
		
		total.add(sub,"商品操作面板"); 
		total.add(sub1, "下架商品信息面板");
	}
	public JTabbedPane getJpanel() {
		return this.total;
	}
}
