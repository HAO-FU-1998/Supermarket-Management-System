package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import sqlHomework.SuperMarket;

public class XiaoShouTongJi {
	public JPanel xiaoshoutongji = new JPanel();
	private JTextField foodStartDate;
	private JTextField foodEndDate;
	private JTextField clothesStartDate;
	private JTextField clothesEndDate;
	
	
	public List<JComboBox<String>> foodcbList = new ArrayList<JComboBox<String>>();
	public List<JComboBox<String>> clothescbList = new ArrayList<JComboBox<String>>();
	
	public String[] foodCol = {"商品编号", "商品名称", "商品品牌", "截止日期", "原产地", "商品价格", "库存量","销售编号","销售时间","货物编号", "销售数量","销售单价","销售总价","备注"};
	public String[] clothesCol = {"商品编号", "商品名称", "商品品牌", "颜色", "尺寸", "性别", "起始年龄", "截止年龄", "商品价格", "库存量", "销售编号","销售时间","货物编号", "销售数量","销售单价","销售总价","备注"};
	
	private SuperMarket server = null;
	

	public XiaoShouTongJi(final SuperMarket server){
		this.server = server;
		xiaoshoutongji.setSize(new Dimension(0, 100));
		xiaoshoutongji.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel foodPart = new JPanel();
		xiaoshoutongji.add(foodPart);
		foodPart.setLayout(new BorderLayout(0, 0));
		
		JPanel food_button_jpanel = new JPanel();
		foodPart.add(food_button_jpanel, BorderLayout.WEST);
		
		JButton foodConButton = new JButton("");
		food_button_jpanel.add(foodConButton);
		Icon foodima = new ImageIcon("food.png");
		foodConButton.setIcon(foodima);
		foodConButton.setBorderPainted(false);
		foodConButton.setBorder(null); 
		
		
		JPanel foodSelection = new JPanel();
		foodPart.add(foodSelection, BorderLayout.CENTER);
		foodSelection.setLayout(new BorderLayout(0, 0));
		
		JPanel foodComboJpanel = new JPanel();
		((FlowLayout) foodComboJpanel.getLayout()).setAlignment(FlowLayout.LEFT);
		foodSelection.add(foodComboJpanel, BorderLayout.NORTH);
		
		//食物的combo下拉列表
		JComboBox<String> foodGid = new JComboBox<String>();
		foodGid.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(foodGid);
		List<String> gidSet = FunctionUI.getGidSetFood();
		Iterator<String> it = gidSet.iterator();
		while(it.hasNext()){
			foodGid.addItem(it.next());
		}
		foodGid.setEditable(false);
		this.foodcbList.add(foodGid);
		
		JComboBox<String> name = new JComboBox<String>();
		name.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(name);
		List<String> nameSet = FunctionUI.getNameSetFood();
		it = nameSet.iterator();
		while(it.hasNext()){
			name.addItem(it.next());
		}
		name.setEditable(false);
		this.foodcbList.add(name);
		
		JComboBox<String> brand = new JComboBox<String>();
		brand.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(brand);
		List<String> brandSet = FunctionUI.getBrandSetFood();
		it = brandSet.iterator();
		while(it.hasNext()){
			brand.addItem(it.next());
		}
		brand.setEditable(false);
		this.foodcbList.add(brand);
		
		JComboBox<String> deadline = new JComboBox<String>();
		deadline.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(deadline);
		List<String> deadlineSet = FunctionUI.getDeadlineSet();
		it = deadlineSet.iterator();
		while(it.hasNext()){
			deadline.addItem(it.next());
		}
		deadline.setEditable(false);
		this.foodcbList.add(deadline);
		
		
		JComboBox<String> origin = new JComboBox<String>();
		origin.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(origin);
		List<String> originSet = FunctionUI.getOriginSet();
		it = originSet.iterator();
		while(it.hasNext()){
			origin.addItem(it.next());
		}
		origin.setEditable(false);
		this.foodcbList.add(origin);
		
		
		JComboBox<String> price = new JComboBox<String>();
		price.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(price);
		List<String> priceSet = FunctionUI.getPriceSetFood();
		it = priceSet.iterator();
		while(it.hasNext()){
			price.addItem(it.next());
		}
		price.setEditable(false);
		this.foodcbList.add(price);
		
		
		JComboBox<String> quantity = new JComboBox<String>();
		quantity.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(quantity);
		List<String> quantitySet = FunctionUI.getQuantitySetFood();
		it = quantitySet.iterator();
		while(it.hasNext()){
			quantity.addItem(it.next());
		}
		quantity.setEditable(false);
		this.foodcbList.add(quantity);
		
		JLabel label = new JLabel("开始时间：");
		label.setFont(new Font("黑体", Font.BOLD, 16));
		foodComboJpanel.add(label);
		final JTextField select_food_startdate = new JTextField("2018-05-20");
		select_food_startdate.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(select_food_startdate);
		JLabel label_1 = new JLabel("截止时间：");
		label_1.setFont(new Font("黑体", Font.BOLD, 16));
		foodComboJpanel.add(label_1);
		final JTextField select_food_enddate = new JTextField("2018-05-20");
		select_food_enddate.setFont(new Font("新宋体", Font.PLAIN, 16));
		foodComboJpanel.add(select_food_enddate);
		
		
		String[] par = new String[foodcbList.size() + 2];
		for(int i = 0; i < foodcbList.size() + 2; ++i){
			par[i] = "全部";
		}
		String[][] result = null;
		try {
			result = server.selectSalesFoodGoods(par);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DefaultTableModel dtm=new DefaultTableModel(result,foodCol);
		final JTable table = new JTable(dtm);
		table.setFont(new Font("新宋体", Font.PLAIN, 14));
		JScrollPane foodScrollPanel = new JScrollPane(table);
		foodScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		foodScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		foodSelection.add(foodScrollPanel, BorderLayout.CENTER);
		
		
		class SelectFoodListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] par = new String[foodcbList.size() + 2];
				for(int i = 0; i < foodcbList.size(); ++i){
					par[i] = (String) foodcbList.get(i).getSelectedItem().toString();
				}
				par[foodcbList.size()] = select_food_startdate.getText();
				par[foodcbList.size() + 1] = select_food_enddate.getText();
				String[][] result = null;
				try {
					result = server.selectSalesFoodGoods(par);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				DefaultTableModel dtm2=(DefaultTableModel)table.getModel();
				dtm2.setDataVector(result,foodCol);
				dtm2.fireTableStructureChanged();
			}
		}
		
		
		JButton ack1 = new JButton("\u786E\u8BA4");
		ack1.setFont(new Font("黑体", Font.BOLD, 16));
		ack1.addActionListener(new SelectFoodListener());
		foodComboJpanel.add(ack1);
		
		
		
		JPanel foodFileJpanel = new JPanel();
		foodSelection.add(foodFileJpanel, BorderLayout.SOUTH);
		
		foodStartDate = new JTextField();
		foodStartDate.setText("2018-05-20");
		foodFileJpanel.add(foodStartDate);
		foodStartDate.setColumns(12);
		foodStartDate.setHorizontalAlignment(JTextField.CENTER);
		foodStartDate.setPreferredSize(new Dimension(15, 30));
		foodStartDate.setFont(new Font("新宋体", Font.PLAIN, 20));
		
		JLabel food_ = new JLabel("-");
		foodFileJpanel.add(food_);
		food_.setFont(new Font("宋体",Font.PLAIN, 25));
		
		foodEndDate = new JTextField();
		foodEndDate.setText("2018-05-20");
		foodFileJpanel.add(foodEndDate);
		foodEndDate.setColumns(12);
		foodEndDate.setHorizontalAlignment(JTextField.CENTER);
		foodEndDate.setPreferredSize(new Dimension(15, 30));
		foodEndDate.setFont(new Font("新宋体", Font.PLAIN, 20));
		
		JButton foodMakeFile = new JButton("生成统计表");
		foodMakeFile.setFont(new Font("黑体", Font.BOLD, 16));
		foodFileJpanel.add(foodMakeFile);
		foodMakeFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try {
					server.makeFile(foodStartDate.getText(), foodEndDate.getText(), "food");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		JPanel clothesPart = new JPanel();
		xiaoshoutongji.add(clothesPart);
		clothesPart.setLayout(new BorderLayout(0, 0));
		
		JPanel clothes_button_jpanel = new JPanel();
		clothesPart.add(clothes_button_jpanel, BorderLayout.WEST);
		
		JButton clothesConButton = new JButton("");
		clothes_button_jpanel.add(clothesConButton);
		Icon clothesima = new ImageIcon("clothes.png");
		clothesConButton.setIcon(clothesima);
		clothesConButton.setBorderPainted(false);
		clothesConButton.setBorder(null);
		
		JPanel clothesSelection = new JPanel();
		clothesPart.add(clothesSelection, BorderLayout.CENTER);
		clothesSelection.setLayout(new BorderLayout(0, 0));
		
		JPanel clothesComboJpanel = new JPanel();
		((FlowLayout) clothesComboJpanel.getLayout()).setAlignment(FlowLayout.LEFT);
		clothesSelection.add(clothesComboJpanel, BorderLayout.NORTH);
		
		JComboBox<String> clothesGid = new JComboBox<String>();
		clothesGid.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(clothesGid);
		gidSet = FunctionUI.getGidSetClothes();
		it = gidSet.iterator();
		while(it.hasNext()){
			clothesGid.addItem(it.next());
		}
		clothesGid.setEditable(false);
		this.clothescbList.add(clothesGid);
		
		JComboBox<String> name1 = new JComboBox<String>();
		name1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(name1);
		nameSet = FunctionUI.getNameSetClothes();
		it = nameSet.iterator();
		while(it.hasNext()){
			name1.addItem(it.next());
		}
		name1.setEditable(false);
		this.clothescbList.add(name1);
		
		
		JComboBox<String> brand1 = new JComboBox<String>();
		brand1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(brand1);
		brandSet = FunctionUI.getBrandSetClothes();
		it = brandSet.iterator();
		while(it.hasNext()){
			brand1.addItem(it.next());
		}
		brand1.setEditable(false);
		this.clothescbList.add(brand1);
		
		
		JComboBox<String> colour1 = new JComboBox<String>();
		colour1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(colour1);
		List<String> colourSet = FunctionUI.getClourSet();
		it = colourSet.iterator();
		while(it.hasNext()){
			colour1.addItem(it.next());
		}
		colour1.setEditable(false);
		this.clothescbList.add(colour1);
		
		JComboBox<String> size1 = new JComboBox<String>();
		size1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(size1);
		List<String> sizeSet = FunctionUI.getSizeSet();
		it = sizeSet.iterator();
		while(it.hasNext()){
			size1.addItem(it.next());
		}
		size1.setEditable(false);
		this.clothescbList.add(size1);
		
		JComboBox<String> gender1 = new JComboBox<String>();
		gender1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(gender1);
		List<String> genderSet = FunctionUI.getGerderSet();
		it = genderSet.iterator();
		while(it.hasNext()){
			gender1.addItem(it.next());
		}
		gender1.setEditable(false);
		this.clothescbList.add(gender1);
		
		JComboBox<String> age1 = new JComboBox<String>();
		age1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(age1);
		List<String> age1Set = FunctionUI.getAge1Set();
		it = age1Set.iterator();
		while(it.hasNext()){
			age1.addItem(it.next());
		}
		age1.setEditable(false);
		this.clothescbList.add(age1);
		
		
		JComboBox<String> age2 = new JComboBox<String>();
		age2.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(age2);
		List<String> age2Set = FunctionUI.getAge2Set();
		it = age2Set.iterator();
		while(it.hasNext()){
			age2.addItem(it.next());
		}
		age2.setEditable(false);
		this.clothescbList.add(age2);
		
		
		JComboBox<String> price1 = new JComboBox<String>();
		price1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(price1);
		priceSet = FunctionUI.getPriceSetClothes();
		it = priceSet.iterator();
		while(it.hasNext()){
			price1.addItem(it.next());
		}
		price1.setEditable(false);
		this.clothescbList.add(price1);
		
		
		JComboBox<String> quantity1 = new JComboBox<String>();
		quantity1.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(quantity1);
		quantitySet = FunctionUI.getQuantitySetClothes();
		it = quantitySet.iterator();
		while(it.hasNext()){
			quantity1.addItem(it.next());
		}
		quantity1.setEditable(false);
		this.clothescbList.add(quantity1);
		
		JLabel label_2 = new JLabel("开始时间：");
		label_2.setFont(new Font("黑体", Font.BOLD, 16));
		clothesComboJpanel.add(label_2);
		final JTextField select_clothes_startdate = new JTextField("2018-05-20");
		select_clothes_startdate.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(select_clothes_startdate);
		JLabel label_3 = new JLabel("截止时间：");
		label_3.setFont(new Font("黑体", Font.BOLD, 16));
		clothesComboJpanel.add(label_3);
		final JTextField select_clothes_enddate = new JTextField("2018-05-20");
		select_clothes_enddate.setFont(new Font("新宋体", Font.PLAIN, 16));
		clothesComboJpanel.add(select_clothes_enddate);
		
		
		String[] par1 = new String[clothescbList.size() + 2];
		for(int i = 0; i < clothescbList.size() + 2; ++i){
			par1[i] = "全部";
		}
		String[][] result1 = null;
		try {
			result1 = server.selectSalesClothesGoods(par1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DefaultTableModel cdtm=new DefaultTableModel(result1,clothesCol);
		final JTable table1 = new JTable(cdtm);
		table1.setFont(new Font("新宋体", Font.PLAIN, 14));
		JScrollPane clothesScrollPanel = new JScrollPane(table1);
		clothesScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		clothesScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		clothesSelection.add(clothesScrollPanel, BorderLayout.CENTER);
		
		
		class SelectClothesListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] par = new String[clothescbList.size() + 2];
				for(int i = 0; i < clothescbList.size(); ++i){
					par[i] = (String) clothescbList.get(i).getSelectedItem().toString();
				}
				par[clothescbList.size()] = select_clothes_startdate.getText();
				par[clothescbList.size() + 1] = select_clothes_enddate.getText();
				String[][] result = null;
				try {
					result = server.selectSalesClothesGoods(par);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				DefaultTableModel cdtm=(DefaultTableModel)table1.getModel();
				cdtm.setDataVector(result,clothesCol);
				cdtm.fireTableStructureChanged();
			}
		}
		
		JButton ack2 = new JButton("\u786E\u8BA4");
		ack2.setFont(new Font("黑体", Font.BOLD, 16));
		clothesComboJpanel.add(ack2);
		ack2.addActionListener(new SelectClothesListener());
		
		
		JPanel clothesFilePanel = new JPanel();
		clothesSelection.add(clothesFilePanel, BorderLayout.SOUTH);
		
		clothesStartDate = new JTextField();
		clothesStartDate.setText("2018-05-20");
		clothesStartDate.setColumns(12);
		clothesStartDate.setHorizontalAlignment(JTextField.CENTER);
		clothesStartDate.setPreferredSize(new Dimension(15, 35));
		clothesStartDate.setFont(new Font("新宋体", Font.PLAIN, 20));
		clothesFilePanel.add(clothesStartDate);
		
		JLabel clothes_ = new JLabel("-");
		clothesFilePanel.add(clothes_);
		clothes_.setFont(new Font("宋体",Font.PLAIN, 25));
		
		clothesEndDate = new JTextField();
		clothesEndDate.setText("2018-05-20");
		clothesEndDate.setColumns(12);
		clothesEndDate.setHorizontalAlignment(JTextField.CENTER);
		clothesEndDate.setPreferredSize(new Dimension(15, 30));
		clothesEndDate.setFont(new Font("新宋体", Font.PLAIN, 20));
		clothesFilePanel.add(clothesEndDate);
		
		JButton clohtesMakeFile = new JButton("生成统计表");
		clohtesMakeFile.setFont(new Font("黑体", Font.BOLD, 16));
		clothesFilePanel.add(clohtesMakeFile);
		
		clohtesMakeFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try {
					server.makeFile(clothesStartDate.getText(), clothesEndDate.getText(), "clothes");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
		
	
	
	
	
	
	
	
	
	
	public JPanel getJPanel(){
		return this.xiaoshoutongji;
	}
}
