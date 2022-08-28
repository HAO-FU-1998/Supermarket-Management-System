package GUI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import sqlHomework.SuperMarket;
import java.awt.Font;

public class KuCunChaXun{
	
	public JPanel kucunchaxun = new JPanel();
	public String[] foodCol = {"商品编号", "商品名称", "商品品牌", "截止日期", "原产地", "商品价格", "库存量", "备注"};
	public String[] clothesCol = {"商品编号", "商品名称", "商品品牌", "颜色", "尺寸", "性别", "起始年龄", "截止年龄", "商品价格", "库存量", "备注"};
	public List<JComboBox<String>> foodcbList = new ArrayList<JComboBox<String>>();
	
	
	public List<JComboBox<String>> clothescbList = new ArrayList<JComboBox<String>>();
	private SuperMarket server = null;

	public KuCunChaXun(final SuperMarket server) {
		this.server = server;
		kucunchaxun.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel food = new JPanel();
		kucunchaxun.add(food);
		food.setLayout(new BorderLayout(0, 0));
		
		JPanel food_button = new JPanel();
		food.add(food_button, BorderLayout.WEST);
		
		JButton btnImage = new JButton("");
		food_button.add(btnImage);
		Icon foodima = new ImageIcon("food.png");
		btnImage.setIcon(foodima);
		btnImage.setBorderPainted(false);
		btnImage.setBorder(null); 
		
		JPanel selection = new JPanel();
		food.add(selection);
		selection.setLayout(new BorderLayout(0, 0));
		
		JPanel jcomboBox = new JPanel();//jcombobox 是装下拉列表的面板
		FlowLayout fl_jcomboBox = (FlowLayout) jcomboBox.getLayout();
		fl_jcomboBox.setAlignment(FlowLayout.LEFT);
		selection.add(jcomboBox, BorderLayout.NORTH);
		
		JComboBox<String> gid = new JComboBox<String>();
		gid.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox.add(gid);
		List<String> gidSet = FunctionUI.getGidSetFood();
		Iterator<String> it = gidSet.iterator();
		while(it.hasNext()){
			gid.addItem(it.next());
		}
		gid.setEditable(false);
		this.foodcbList.add(gid);
		
		
		JComboBox<String> name = new JComboBox<String>();
		name.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox.add(name);
		List<String> nameSet = FunctionUI.getNameSetFood();
		it = nameSet.iterator();
		while(it.hasNext()){
			name.addItem(it.next());
		}
		name.setEditable(false);
		this.foodcbList.add(name);
		
		
		JComboBox<String> brand = new JComboBox<String>();
		brand.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox.add(brand);
		List<String> brandSet = FunctionUI.getBrandSetFood();
		it = brandSet.iterator();
		while(it.hasNext()){
			brand.addItem(it.next());
		}
		brand.setEditable(false);
		this.foodcbList.add(brand);
		
		JComboBox<String> deadline = new JComboBox<String>();
		deadline.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox.add(deadline);
		List<String> deadlineSet = FunctionUI.getDeadlineSet();
		it = deadlineSet.iterator();
		while(it.hasNext()){
			deadline.addItem(it.next());
		}
		deadline.setEditable(false);
		this.foodcbList.add(deadline);
		
		
		JComboBox<String> origin = new JComboBox<String>();
		origin.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox.add(origin);
		List<String> originSet = FunctionUI.getOriginSet();
		it = originSet.iterator();
		while(it.hasNext()){
			origin.addItem(it.next());
		}
		origin.setEditable(false);
		this.foodcbList.add(origin);
		
		
		JComboBox<String> price = new JComboBox<String>();
		price.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox.add(price);
		List<String> priceSet = FunctionUI.getPriceSetFood();
		it = priceSet.iterator();
		while(it.hasNext()){
			price.addItem(it.next());
		}
		price.setEditable(false);
		this.foodcbList.add(price);
		
		
		JComboBox<String> quantity = new JComboBox<String>();
		quantity.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox.add(quantity);
		List<String> quantitySet = FunctionUI.getQuantitySetFood();
		it = quantitySet.iterator();
		while(it.hasNext()){
			quantity.addItem(it.next());
		}
		quantity.setEditable(false);
		this.foodcbList.add(quantity);
		
		
		String[] par = new String[foodcbList.size()];
		for(int i = 0; i < foodcbList.size(); ++i){
			par[i] = "全部";
		}
		String[][] result = null;
		try {
			result = server.selectFoodGoods(par);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DefaultTableModel dtm=new DefaultTableModel(result,foodCol);
		final JTable table = new JTable(dtm);
		table.setFont(new Font("新宋体", Font.PLAIN, 14));
		JScrollPane showresult = new JScrollPane(table);
		showresult.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		showresult.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		selection.add(showresult, BorderLayout.CENTER);
		
		
		class SelectFoodListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] par = new String[foodcbList.size()];
				for(int i = 0; i < foodcbList.size(); ++i){
					par[i] = (String) foodcbList.get(i).getSelectedItem().toString();
				}
				String[][] result = null;
				try {
					result = server.selectFoodGoods(par);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				DefaultTableModel dtm2=(DefaultTableModel)table.getModel();
				dtm2.setDataVector(result,foodCol);
				dtm2.fireTableStructureChanged();
			}
		}
		
		
		JButton ack1 = new JButton("确认选择");
		ack1.setFont(new Font("黑体", Font.BOLD, 16));
		ack1.addActionListener(new SelectFoodListener());
		jcomboBox.add(ack1);
		
		
		
		
		
		
		
		
		
		
		
		
		//clothes部分
		JPanel clothes = new JPanel();
		kucunchaxun.add(clothes);
		clothes.setLayout(new BorderLayout(0, 0));
		
		JPanel clothes_button = new JPanel();
		clothes_button.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		clothes.add(clothes_button, BorderLayout.WEST);
		
		JButton btnNewButton = new JButton("");
		clothes_button.add(btnNewButton);
		Icon clothesima = new ImageIcon("clothes.png");
		btnNewButton.setIcon(clothesima);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null); 
		
		JPanel selecction_clothes = new JPanel();
		clothes.add(selecction_clothes, BorderLayout.CENTER);
		selecction_clothes.setLayout(new BorderLayout(0, 0));
		
		JPanel jcomboBox_clothes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jcomboBox_clothes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		selecction_clothes.add(jcomboBox_clothes, BorderLayout.NORTH);
		
		JComboBox<String> gid1 = new JComboBox<String>();
		gid1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(gid1);
		gidSet = FunctionUI.getGidSetClothes();
		it = gidSet.iterator();
		while(it.hasNext()){
			gid1.addItem(it.next());
		}
		gid1.setEditable(false);
		this.clothescbList.add(gid1);
		
		
		JComboBox<String> name1 = new JComboBox<String>();
		name1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(name1);
		nameSet = FunctionUI.getNameSetClothes();
		it = nameSet.iterator();
		while(it.hasNext()){
			name1.addItem(it.next());
		}
		name1.setEditable(false);
		this.clothescbList.add(name1);
		
		
		JComboBox<String> brand1 = new JComboBox<String>();
		brand1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(brand1);
		brandSet = FunctionUI.getBrandSetClothes();
		it = brandSet.iterator();
		while(it.hasNext()){
			brand1.addItem(it.next());
		}
		brand1.setEditable(false);
		this.clothescbList.add(brand1);
		
		
		JComboBox<String> colour1 = new JComboBox<String>();
		colour1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(colour1);
		List<String> colourSet = FunctionUI.getClourSet();
		it = colourSet.iterator();
		while(it.hasNext()){
			colour1.addItem(it.next());
		}
		colour1.setEditable(false);
		this.clothescbList.add(colour1);
		
		JComboBox<String> size1 = new JComboBox<String>();
		size1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(size1);
		List<String> sizeSet = FunctionUI.getSizeSet();
		it = sizeSet.iterator();
		while(it.hasNext()){
			size1.addItem(it.next());
		}
		size1.setEditable(false);
		this.clothescbList.add(size1);
		
		JComboBox<String> gender1 = new JComboBox<String>();
		gender1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(gender1);
		List<String> genderSet = FunctionUI.getGerderSet();
		it = genderSet.iterator();
		while(it.hasNext()){
			gender1.addItem(it.next());
		}
		gender1.setEditable(false);
		this.clothescbList.add(gender1);
		
		JComboBox<String> age1 = new JComboBox<String>();
		age1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(age1);
		List<String> age1Set = FunctionUI.getAge1Set();
		it = age1Set.iterator();
		while(it.hasNext()){
			age1.addItem(it.next());
		}
		age1.setEditable(false);
		this.clothescbList.add(age1);
		
		
		JComboBox<String> age2 = new JComboBox<String>();
		age2.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(age2);
		List<String> age2Set = FunctionUI.getAge2Set();
		it = age2Set.iterator();
		while(it.hasNext()){
			age2.addItem(it.next());
		}
		age2.setEditable(false);
		this.clothescbList.add(age2);
		
		
		JComboBox<String> price1 = new JComboBox<String>();
		price1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(price1);
		priceSet = FunctionUI.getPriceSetClothes();
		it = priceSet.iterator();
		while(it.hasNext()){
			price1.addItem(it.next());
		}
		price1.setEditable(false);
		this.clothescbList.add(price1);
		
		
		JComboBox<String> quantity1 = new JComboBox<String>();
		quantity1.setFont(new Font("新宋体", Font.PLAIN, 16));
		jcomboBox_clothes.add(quantity1);
		quantitySet = FunctionUI.getQuantitySetClothes();
		it = quantitySet.iterator();
		while(it.hasNext()){
			quantity1.addItem(it.next());
		}
		quantity1.setEditable(false);
		this.clothescbList.add(quantity1);
		
		
		
		String[] par1 = new String[clothescbList.size()];
		for(int i = 0; i < clothescbList.size(); ++i){
			par1[i] = "全部";
		}
		String[][] result1 = null;
		try {
			result1 = server.selectClothesGoods(par1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DefaultTableModel cdtm=new DefaultTableModel(result1,clothesCol);
		final JTable table1 = new JTable(cdtm);
		table1.setFont(new Font("新宋体", Font.PLAIN, 14));
		JScrollPane showresult1 = new JScrollPane(table1);
		showresult1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		showresult1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		selecction_clothes.add(showresult1, BorderLayout.CENTER);
		
		
		
		class SelectClothesListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] par = new String[clothescbList.size()];
				for(int i = 0; i < clothescbList.size(); ++i){
					par[i] = (String) clothescbList.get(i).getSelectedItem().toString();
				}
				String[][] result = null;
				try {
					result = server.selectClothesGoods(par);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				DefaultTableModel cdtm=(DefaultTableModel)table1.getModel();
				cdtm.setDataVector(result,clothesCol);
				cdtm.fireTableStructureChanged();
			}
		}
		
		JButton ack2 = new JButton("确认选择");
		ack2.setFont(new Font("黑体", Font.BOLD, 16));
		jcomboBox_clothes.add(ack2);
		ack2.addActionListener(new SelectClothesListener());
	}
	
	
	
	
	public JPanel getJPanel(){
		return this.kucunchaxun;
	}

}
