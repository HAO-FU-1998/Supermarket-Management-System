package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import sqlHomework.SuperMarket;
import java.awt.FlowLayout;
import java.awt.Component;

public class FunctionUI extends JFrame {

	private JPanel contentPane;
	private JPanel card = new JPanel(new CardLayout());
	public JButton shouye_button = new JButton("          \u9996\u9875          ");
	public JButton kucunchaxun_button = new JButton("          \u5E93\u5B58          ");
	public JButton shujutongji_button = new JButton("          \u7EDF\u8BA1          ");
	public JButton monixiaoshou_button = new JButton("          \u9500\u552E          ");
	public JButton shangpincaigou_button = new JButton("          \u91C7\u8D2D          ");
	public JButton shangpinxiajia_button = new JButton("          \u4E0B\u67B6          ");
	private CardListener cardListener = new CardListener(card, shouye_button, kucunchaxun_button, monixiaoshou_button, shujutongji_button, shangpincaigou_button, shangpinxiajia_button);
	private SuperMarket server = null;
	
	
	
	public static List<String> gidSet = new ArrayList<String>();
	public static List<String> gidSet1 = new ArrayList<String>();
	public static List<String> nameSet = new ArrayList<String>();
	public static List<String> nameSet1 = new ArrayList<String>();
	public static List<String> brandSet = new ArrayList<String>();
	public static List<String> brandSet1 = new ArrayList<String>();
	public static List<String> deadlineSet = new ArrayList<String>();
	public static List<String> originSet = new ArrayList<String>();
	public static List<String> priceSet = new ArrayList<String>();
	public static List<String> priceSet1 = new ArrayList<String>();
	public static List<String> quantitySet = new ArrayList<String>();
	public static List<String> quantitySet1 = new ArrayList<String>();
	public static List<String> colourSet = new ArrayList<String>();
	public static List<String> sizeSet = new ArrayList<String>();
	public static List<String> genderSet = new ArrayList<String>();
	public static List<String> age1 = new ArrayList<String>();
	public static List<String> age2 = new ArrayList<String>();
	private JTextField foodunitprice;
	private JTextField foodquantity;
	private JTextField foodtotalprice;
	private JTextField foodquantitykucun;
	private JTextField clothesquantitykucun;
	private JTextField clothesunitprice;
	private JTextField clothesquantity;
	private JTextField clothestotalprice;
	/**
	 * Create the frame.
	 */
	public FunctionUI(final SuperMarket server) {
		super("Supermarket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 45, 1920, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		//JButton btnNewButton = new JButton("          \u9996\u9875          ");
		//btnNewButton.setFont(new Font("����", Font.BOLD, 18));
		//toolBar.add(btnNewButton);
		kucunchaxun_button.setFont(new Font("����", Font.BOLD, 16));
		toolBar.add(kucunchaxun_button);
		kucunchaxun_button.addActionListener(cardListener);
		shujutongji_button.setFont(new Font("����", Font.BOLD, 16));
		toolBar.add(shujutongji_button);
		shujutongji_button.addActionListener(cardListener);
		monixiaoshou_button.setFont(new Font("����", Font.BOLD, 16));
		toolBar.add(monixiaoshou_button);
		monixiaoshou_button.addActionListener(cardListener);
		shangpincaigou_button.setFont(new Font("����", Font.BOLD, 16));
		toolBar.add(shangpincaigou_button);
		shangpincaigou_button.addActionListener(cardListener);
		shangpinxiajia_button.setFont(new Font("����", Font.BOLD, 16));
		toolBar.add(shangpinxiajia_button);
		shangpinxiajia_button.addActionListener(cardListener);
		contentPane.add(card, BorderLayout.CENTER);
		this.server = server;
		
		//�����б��ʼ��
		initSet();
		
		
		JPanel kucunchaxun = new KuCunChaXun(server).getJPanel();
		card.add(kucunchaxun, "kucunchaxun");
		
		//ģ�����ۿ�ʼ
		JPanel monixiaoshou = new JPanel();
		card.add(monixiaoshou, "monixiaoshou");
		monixiaoshou.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel sale_food = new JPanel();
		monixiaoshou.add(sale_food);
		sale_food.setLayout(new BorderLayout(0, 0));
		
		JPanel image_food_sale = new JPanel();
		sale_food.add(image_food_sale, BorderLayout.WEST);
		
		JButton image_food_button = new JButton("");
		image_food_sale.add(image_food_button);
		Icon foodima = new ImageIcon("food.png");
		image_food_button.setIcon(foodima);
		image_food_button.setBorderPainted(false);
		image_food_button.setBorder(null); 
		
		JPanel selection_sale_food = new JPanel();
		selection_sale_food.setBorder(new TitledBorder(null, "ʳ������"));
		sale_food.add(selection_sale_food, BorderLayout.CENTER);
		selection_sale_food.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel foodpanel1 = new JPanel();
		selection_sale_food.add(foodpanel1);
		
		JLabel foodlabel1 = new JLabel("��Ʒ��ţ�");
		foodlabel1.setFont(new Font("����", Font.PLAIN, 18));
		foodpanel1.add(foodlabel1);
		
		final JComboBox<String> foodgid = new JComboBox<String>();
		foodgid.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		foodpanel1.add(foodgid);
		List<String> listgid = FunctionUI.getGidSetFood();
		String[] list = listgid.toArray(new String[listgid.size()]);
		for(int i = 1; i < list.length - 1; ++i){
			foodgid.addItem(list[i]);
		}
		foodgid.setEditable(false);
		
		JPanel foodpanel2 = new JPanel();
		selection_sale_food.add(foodpanel2);
		
		JLabel foodlabel2 = new JLabel("��Ʒ��棺");
		foodlabel2.setFont(new Font("����", Font.PLAIN, 18));
		foodpanel2.add(foodlabel2);
		
		foodquantitykucun = new JTextField();
		foodquantitykucun.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		foodpanel2.add(foodquantitykucun);
		foodquantitykucun.setColumns(10);
		foodquantitykucun.setEditable(false);
		
		JPanel foodpanel3 = new JPanel();
		selection_sale_food.add(foodpanel3);
		
		JLabel foodlabel3 = new JLabel("��Ʒ���ۣ�");
		foodlabel3.setFont(new Font("����", Font.PLAIN, 18));
		foodpanel3.add(foodlabel3);
		
		foodunitprice = new JTextField();
		foodunitprice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		foodpanel3.add(foodunitprice);
		foodunitprice.setColumns(10);
		foodunitprice.setEditable(false);
		
		foodgid.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String[] par =new String[7];
				par[0] = foodgid.getSelectedItem().toString();
				for(int i = 1; i < par.length; i++){
					par[i] = "ȫ��";
				}
				String[][] result = null;
				try {
					result = server.selectFoodGoods(par);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(result.length == 0){
					JOptionPane.showMessageDialog(null, "��Ʒ������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(result[0][result[0].length - 2].equals("-1")){
					JOptionPane.showMessageDialog(null, "��Ʒ���¼�", "����ʧ��", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					foodunitprice.setText(result[0][result[0].length - 3]);
					foodquantitykucun.setText(result[0][result[0].length - 2]);
				}
				
			}
		});
		
		JPanel foodpanel4 = new JPanel();
		selection_sale_food.add(foodpanel4);
		
		JLabel foodlabel4 = new JLabel("����������");
		foodlabel4.setFont(new Font("����", Font.PLAIN, 18));
		foodpanel4.add(foodlabel4);
		
		foodquantity = new JTextField();
		foodquantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		foodpanel4.add(foodquantity);
		foodquantity.setColumns(10);
		
		JPanel foodpanel5 = new JPanel();
		selection_sale_food.add(foodpanel5);
		
		JLabel foodlabel5 = new JLabel("�����ܼۣ�");
		foodlabel5.setFont(new Font("����", Font.PLAIN, 18));
		foodpanel5.add(foodlabel5);
		
		foodtotalprice = new JTextField();
		foodtotalprice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		foodpanel5.add(foodtotalprice);
		foodtotalprice.setColumns(10);
		foodtotalprice.setEditable(false);
		
		
		foodquantity.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Integer.valueOf(foodquantity.getText()) > Integer.valueOf(foodquantitykucun.getText())){
					JOptionPane.showMessageDialog(null, "��Ʒ��治��", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				}else{
					foodtotalprice.setText(String.valueOf(Double.valueOf(foodunitprice.getText())*Integer.valueOf(foodquantity.getText())));
				}
			}
		});
		
		JPanel foodpanel6 = new JPanel();
		selection_sale_food.add(foodpanel6);
		
		JButton foodButton = new JButton("ȷ������");
		foodButton.setFont(new Font("����", Font.BOLD, 18));
		foodpanel6.add(foodButton);
		foodButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Integer.valueOf(foodquantity.getText()) > Integer.valueOf(foodquantitykucun.getText())){
					JOptionPane.showMessageDialog(null, "��Ʒ��治��", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				}else{
					foodtotalprice.setText(String.valueOf(Double.valueOf(foodunitprice.getText())*Integer.valueOf(foodquantity.getText())));
					boolean flag = false;
					try {
						flag = server.xiaoshuo(foodgid.getSelectedItem().toString(), foodunitprice.getText(), foodquantity.getText(), foodtotalprice.getText(), "food");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "���۳ɹ�", "����", JOptionPane.OK_CANCEL_OPTION);
					if(flag){
						String number = JOptionPane.showInputDialog("��治��5������������Ҫ�����ɹ�������", 10);
						String[][] array = new String[1][2];
						array[0][0] = foodgid.getSelectedItem().toString();
						array[0][1] = number;
						try {
							server.caigoukucunFoodBuy(array, "food");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "�ɹ��ɹ�", "�ɹ�", JOptionPane.OK_CANCEL_OPTION);
					}
				}
			}
		});
		
		
		
		
		
		
		JPanel sale_clothes = new JPanel();
		monixiaoshou.add(sale_clothes);
		sale_clothes.setLayout(new BorderLayout(0, 0));
		
		JPanel image_clothes_sale = new JPanel();
		sale_clothes.add(image_clothes_sale, BorderLayout.WEST);
		
		JButton image_clothes_button = new JButton("");
		image_clothes_sale.add(image_clothes_button);
		Icon clothesima = new ImageIcon("clothes.png");
		image_clothes_button.setIcon(clothesima);
		image_clothes_button.setBorderPainted(false);
		image_clothes_button.setBorder(null);
		
		JPanel selection_sale_clothes = new JPanel();
		selection_sale_clothes.setBorder(new TitledBorder(null,  "�·�����"));
		sale_clothes.add(selection_sale_clothes, BorderLayout.CENTER);
		selection_sale_clothes.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel clothespanel1 = new JPanel();
		selection_sale_clothes.add(clothespanel1);
		
		JLabel clotheslabel1 = new JLabel("��Ʒ��ţ�");
		clotheslabel1.setFont(new Font("����", Font.PLAIN, 18));
		clothespanel1.add(clotheslabel1);
		
		final JComboBox<String> clothesgid = new JComboBox<String>();
		clothesgid.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		clothespanel1.add(clothesgid);
		List<String> listclothesgid = FunctionUI.getGidSetClothes();
		String[] clotheslist = listclothesgid.toArray(new String[listclothesgid.size()]);
		for(int i = 1; i < clotheslist.length - 1; ++i){
			clothesgid.addItem(clotheslist[i]);
		}
		clothesgid.setEditable(false);
		
		JPanel clothespanel2 = new JPanel();
		selection_sale_clothes.add(clothespanel2);
		
		JLabel clotheslabel2 = new JLabel("��Ʒ��棺");
		clotheslabel2.setFont(new Font("����", Font.PLAIN, 18));
		clothespanel2.add(clotheslabel2);
		
		clothesquantitykucun = new JTextField();
		clothesquantitykucun.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		clothespanel2.add(clothesquantitykucun);
		clothesquantitykucun.setColumns(10);
		clothesquantitykucun.setEditable(false);
		
		JPanel clothespanel3 = new JPanel();
		selection_sale_clothes.add(clothespanel3);
		
		JLabel clotheslabel3 = new JLabel("��Ʒ���ۣ�");
		clotheslabel3.setFont(new Font("����", Font.PLAIN, 18));
		clothespanel3.add(clotheslabel3);
		
		clothesunitprice = new JTextField();
		clothesunitprice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		clothespanel3.add(clothesunitprice);
		clothesunitprice.setColumns(10);
		clothesunitprice.setEditable(false);
		
		
		
		clothesgid.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String[] par =new String[10];
				par[0] = clothesgid.getSelectedItem().toString();
				for(int i = 1; i < par.length; i++){
					par[i] = "ȫ��";
				}
				String[][] result = null;
				try {
					result = server.selectClothesGoods(par);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(result.length == 0){
					JOptionPane.showMessageDialog(null, "��Ʒ������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(result[0][result[0].length - 2].equals("-1")){
					JOptionPane.showMessageDialog(null, "��Ʒ���¼�", "����ʧ��", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					clothesunitprice.setText(result[0][result[0].length - 3]);
					clothesquantitykucun.setText(result[0][result[0].length - 2]);
				}
				
			}
		});
		
		JPanel clothespanel4 = new JPanel();
		selection_sale_clothes.add(clothespanel4);
		
		JLabel clotheslabel4 = new JLabel("����������");
		clotheslabel4.setFont(new Font("����", Font.PLAIN, 18));
		clothespanel4.add(clotheslabel4);
		
		clothesquantity = new JTextField();
		clothesquantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		clothespanel4.add(clothesquantity);
		clothesquantity.setColumns(10);
		
		JPanel clothespanel5 = new JPanel();
		selection_sale_clothes.add(clothespanel5);
		
		JLabel clotheslabel5 = new JLabel("�����ܼۣ�");
		clotheslabel5.setFont(new Font("����", Font.PLAIN, 18));
		clothespanel5.add(clotheslabel5);
		
		clothestotalprice = new JTextField();
		clothestotalprice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		clothespanel5.add(clothestotalprice);
		clothestotalprice.setColumns(10);
		clothestotalprice.setEditable(false);
		
		
		clothesquantity.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Integer.valueOf(clothesquantity.getText()) > Integer.valueOf(clothesquantitykucun.getText())){
					JOptionPane.showMessageDialog(null, "��Ʒ��治��", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				}else{
					clothestotalprice.setText(String.valueOf(Double.valueOf(clothesunitprice.getText())*Integer.valueOf(clothesquantity.getText())));
				}
			}
		});
		
		
		JPanel clothespanel6 = new JPanel();
		selection_sale_clothes.add(clothespanel6);
		
		JButton clothesButton = new JButton("ȷ������");
		clothesButton.setFont(new Font("����", Font.BOLD, 18));
		clothespanel6.add(clothesButton);
		
		JPanel xiaoshoutongji = new XiaoShouTongJi(server).getJPanel();
		card.add(xiaoshoutongji, "xiaoshoutongji");
		
		clothesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Integer.valueOf(clothesquantity.getText()) > Integer.valueOf(clothesquantitykucun.getText())){
					JOptionPane.showMessageDialog(null, "��Ʒ��治��", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				}else{
					clothestotalprice.setText(String.valueOf(Double.valueOf(clothesunitprice.getText())*Integer.valueOf(clothesquantity.getText())));
					boolean flag = false;
					try {
						flag = server.xiaoshuo(clothesgid.getSelectedItem().toString(), clothesunitprice.getText(), clothesquantity.getText(), clothestotalprice.getText(), "clothes");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "���۳ɹ�", "����", JOptionPane.OK_CANCEL_OPTION);
					if(flag){
						String number = JOptionPane.showInputDialog("��治��5������������Ҫ�����ɹ�������", 10);
						String[][] array = new String[1][2];
						array[0][0] = clothesgid.getSelectedItem().toString();
						array[0][1] = number;
						try {
							server.caigoukucunFoodBuy(array, "clothes");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "�ɹ��ɹ�", "�ɹ�", JOptionPane.OK_CANCEL_OPTION);
					}
				}
			}
		});
		//ģ�����۽���
		
		
		
		//��Ʒ�ɹ���ʼ
		
		
		
		JPanel shangpincaigou1 = new JPanel();
		
		
		JTabbedPane shangpincaigou = new JTabbedPane();
		shangpincaigou.add("��Ʒ�ɹ����", shangpincaigou1);
		
		card.add(shangpincaigou, "shangpincaigou");
		shangpincaigou1.setLayout(new BorderLayout(0, 0));
		
		JPanel northButtonPanel = new JPanel();
		shangpincaigou1.add(northButtonPanel, BorderLayout.NORTH);
		
		
		JPanel caigoubiao = new JPanel();
		shangpincaigou.add("�ɹ���Ϣ���", caigoubiao);
		caigoubiao.setLayout(new BorderLayout(0,0));
		JPanel caigoubiaobuttonpa = new JPanel();
		FlowLayout flowLayout = (FlowLayout) caigoubiaobuttonpa.getLayout();
		flowLayout.setHgap(10);
		caigoubiao.add(caigoubiaobuttonpa,BorderLayout.NORTH);
		JButton caigoubiaobutton = new JButton("\u67E5\u8BE2");
		caigoubiaobutton.setFont(new Font("������", Font.BOLD, 16));
		caigoubiaobuttonpa.add(caigoubiaobutton);
		
		final String[] head = {"��Ʒ����","�ɹ����", "�ɹ�ʱ��",  "��Ʒ���","�ɹ�����"};
		String[][] result = null;
		DefaultTableModel caigoudtm = new DefaultTableModel(result, head);
		final JTable table = new JTable(caigoudtm);
		JScrollPane jspanel = new JScrollPane(table);
		jspanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		caigoubiaobutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String[][] result1 = null;
				try {
					result1 = server.caigoubiao();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultTableModel caigoudtm1 = (DefaultTableModel) table.getModel();
				caigoudtm1.setDataVector(result1, head);
				caigoudtm1.fireTableStructureChanged();
			}
			
		});
		caigoubiao.add(jspanel);
		
		
		
		JPanel displayPanel = new JPanel();
		shangpincaigou1.add(displayPanel, BorderLayout.CENTER);
		displayPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		final String[] tablehead = {"��Ʒ���", "���ʣ��", "��������"};
		DefaultTableModel dtm=new DefaultTableModel(null,tablehead);
		final JTable foodtable = new JTable(dtm);
		JScrollPane foodDisplayPanel = new JScrollPane(foodtable);
		foodDisplayPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		foodDisplayPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		foodDisplayPanel.setBorder(new TitledBorder(null, "ʳ��"));
		displayPanel.add(foodDisplayPanel);
		
		DefaultTableModel dtmclothes=new DefaultTableModel(null,tablehead);
		final JTable clothestable = new JTable(dtmclothes);
		JScrollPane clothesDisplayPanel = new JScrollPane(clothestable);
		clothesDisplayPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		clothesDisplayPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		clothesDisplayPanel.setBorder(new TitledBorder(null, "�·�"));
		displayPanel.add(clothesDisplayPanel);
		
		JButton chakankucun = new JButton("�鿴���");
		chakankucun.setFont(new Font("������", Font.BOLD, 16));
		chakankucun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] result = null;
				try {
					result = server.caigoukucunFood();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultTableModel dtm1food=(DefaultTableModel)foodtable.getModel();
				dtm1food.setDataVector(result,tablehead);
				dtm1food.fireTableStructureChanged();
				
				result = null;
				try {
					result = server.caigoukucunClothes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultTableModel dtm1clothes=(DefaultTableModel)clothestable.getModel();
				dtm1clothes.setDataVector(result,tablehead);
				dtm1clothes.fireTableStructureChanged();
			}
		});
		northButtonPanel.add(chakankucun);
		
		JButton querencaigou = new JButton("ȷ�ϲɹ�");
		querencaigou.setFont(new Font("������", Font.BOLD, 16));
		northButtonPanel.add(querencaigou);
		querencaigou.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				List<String[]> par = new ArrayList<String[]>();
				String[] par0 = new String[2];
				DefaultTableModel addModel = (DefaultTableModel) foodtable.getModel();
				for(int i = 0; i < addModel.getRowCount(); ++i){
					if(addModel.getValueAt(i, 2) .equals("0")){
						continue;
					}
					par0[0] = (String) addModel.getValueAt(i, 0);
					par0[1] = (String) addModel.getValueAt(i, 2);
					par.add(par0);
					par0 = new String[2];
				}
				try {
					server.caigoukucunFoodBuy(par.toArray(new String[par.size()][]), "food");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				par = new ArrayList<String[]>();
				DefaultTableModel addMode2 = (DefaultTableModel) clothestable.getModel();
				for(int i = 0; i < addMode2.getRowCount(); ++i){
					if(addMode2.getValueAt(i, 2) .equals("0")){
						continue;
					}
					par0[0] = (String) addMode2.getValueAt(i, 0);
					par0[1] = (String) addMode2.getValueAt(i, 2);
					par.add(par0);
					par0 = new String[2];
				}
				try {
					server.caigoukucunFoodBuy(par.toArray(new String[par.size()][]), "clothes");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "�ɹ��ɹ�", "�ɹ�", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		//��Ʒ�ɹ�����
		
		
		
		
		JTabbedPane shangpinxiajia = new ShangPinXiaJia(server).getJpanel();
		card.add(shangpinxiajia, "shangpinxiajia");
		
		
		this.setVisible(true);
	}

	private void initSet() {
		// TODO Auto-generated method stub
		gidSet.add("��Ʒ���");
		String[] temp = new String[5];
		try {
			temp = server.init("gid","food", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			gidSet.add(temp[i - 1]);
		}
		gidSet.add("ȫ��");
		
		
		gidSet1.add("��Ʒ���");
		try {
			temp = server.init("gid","clothes", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			gidSet1.add(temp[i - 1]);
		}
		gidSet1.add("ȫ��");
		
		nameSet.add("��Ʒ����");
		try {
			temp = server.init("name","food", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			nameSet.add(temp[i - 1]);
		}
		nameSet.add("ȫ��");
		
		nameSet1.add("��Ʒ����");
		try {
			temp = server.init("name","clothes", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			nameSet1.add(temp[i - 1]);
		}
		nameSet1.add("ȫ��");
		
		brandSet.add("��ƷƷ��");
		try {
			temp = server.init("brand","food", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			brandSet.add(temp[i - 1]);
		}
		brandSet.add("ȫ��");
		
		
		brandSet1.add("��ƷƷ��");
		try {
			temp = server.init("brand","clothes", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			brandSet1.add(temp[i - 1]);
		}
		brandSet1.add("ȫ��");
		
		deadlineSet.add("��ֹ����");
		try {
			temp = server.init("deadline","food", "Date");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			deadlineSet.add(temp[i - 1]);
		}
		deadlineSet.add("ȫ��");
		
		
		originSet.add("ԭ����");
		try {
			temp = server.init("origin","food", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			originSet.add(temp[i - 1]);
		}
		originSet.add("ȫ��");
		
		priceSet.add("��Ʒ�۸�");
		try {
			temp = server.init("price","food", "Double");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			priceSet.add(temp[i - 1]);
		}
		priceSet.add("ȫ��");
		
		priceSet1.add("��Ʒ�۸�");
		try {
			temp = server.init("price","clothes", "Double");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			priceSet1.add(temp[i - 1]);
		}
		priceSet1.add("ȫ��");
		
		quantitySet.add("�����");
		try {
			temp = server.init("quantity","food", "Int");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			quantitySet.add(temp[i - 1]);
		}
		quantitySet.add("ȫ��");
		
		
		quantitySet1.add("�����");
		try {
			temp = server.init("quantity","clothes", "Int");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			quantitySet1.add(temp[i - 1]);
		}
		quantitySet1.add("ȫ��");
		
		colourSet.add("��ɫ");
		try {
			temp = server.init("colour","clothes", "String");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; i <= temp.length; ++i){
			colourSet.add(temp[i - 1]);
		}
		colourSet.add("ȫ��");
		
		
		sizeSet.add("�ߴ�");
		sizeSet.add("S");
		sizeSet.add("M");
		sizeSet.add("L");
		sizeSet.add("XL");
		sizeSet.add("XXL");
		sizeSet.add("XXXL");
		sizeSet.add("ȫ��");
		
		genderSet.add("�Ա�");
		genderSet.add("��");
		genderSet.add("Ů");
		genderSet.add("ȫ��");
		
		age1.add("��ʼ����");
		for(int i = 0; i < 10; ++i){
			age1.add(String.valueOf(i*10));
		}
		age1.add("ȫ��");
		
		age2.add("��ֹ����");
		for(int i = 0; i < 10; ++i){
			age2.add(String.valueOf(i*10));
		}
		age2.add("ȫ��");
	}

	public static List<String> getGidSetFood() {
		// TODO Auto-generated method stub
		return gidSet;
	}
	public static List<String> getGidSetClothes() {
		// TODO Auto-generated method stub
		return gidSet1;
	}

	public static List<String> getNameSetFood() {
		// TODO Auto-generated method stub
		return nameSet;
	}
	
	
	public static List<String> getNameSetClothes() {
		// TODO Auto-generated method stub
		return nameSet1;
	}

	public static List<String> getBrandSetFood() {
		// TODO Auto-generated method stub
		return brandSet;
	}
	
	
	public static List<String> getBrandSetClothes() {
		// TODO Auto-generated method stub
		return brandSet1;
	}

	public static List<String> getDeadlineSet() {
		// TODO Auto-generated method stub
		return deadlineSet;
	}

	public static List<String> getOriginSet() {
		// TODO Auto-generated method stub
		return originSet;
	}

	public static List<String> getPriceSetFood() {
		// TODO Auto-generated method stub
		return priceSet;
	}
	
	public static List<String> getPriceSetClothes() {
		// TODO Auto-generated method stub
		return priceSet1;
	}

	public static List<String> getQuantitySetFood() {
		// TODO Auto-generated method stub
		return quantitySet;
	}
	
	public static List<String> getQuantitySetClothes() {
		// TODO Auto-generated method stub
		return quantitySet1;
	}

	public static List<String> getClourSet() {
		// TODO Auto-generated method stub
		return colourSet;
	}

	public static List<String> getSizeSet() {
		// TODO Auto-generated method stub
		return sizeSet;
	}

	public static List<String> getGerderSet() {
		// TODO Auto-generated method stub
		return genderSet;
	}

	public static List<String> getAge1Set() {
		// TODO Auto-generated method stub
		return age1;
	}

	public static List<String> getAge2Set() {
		// TODO Auto-generated method stub
		return age2;
	}
	
	
	
}
