package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SignIn{
	private JTextField user;
	private JPasswordField password;
	private JFrame jf;
	/**
	 * Create the frame.
	 */
	public SignIn() {
		jf = new JFrame("Supermarket");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.getContentPane().setLayout(new GridLayout(3,1));
		
		
		ImageIcon icon=new ImageIcon("123.jpg");  
        //Image im=new Image(icon);  
        //将图片放入label中  
        JLabel label=new JLabel(icon);  
        jf.setBounds(650, 300,613,490);
        //设置label的大小  
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel j=(JPanel)jf.getContentPane();  
        j.setOpaque(false); 
        
		
		
		
		
		JPanel biaoti = new JPanel();
		biaoti.setOpaque(false);
		jf.getContentPane().add(biaoti);
		
		JLabel lblNewLabel_1 = new JLabel("SUPERMARKET");
		lblNewLabel_1.setForeground(new Color(51, 102, 51));
		lblNewLabel_1.setFont(new Font("华文行楷", Font.BOLD, 60));
		biaoti.add(lblNewLabel_1);
		

		
		JPanel denglujiemian = new JPanel();
		jf.getContentPane().add(denglujiemian, BorderLayout.CENTER);
		denglujiemian.setLayout(new GridLayout(2, 1, 0, 0));
		denglujiemian.setOpaque(false);
		
		JPanel userPanel = new JPanel();
		userPanel.setSize(new Dimension(600, 0));
		denglujiemian.add(userPanel);
		userPanel.setOpaque(false);
		
		JLabel userLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		userLabel.setForeground(Color.CYAN);
		userLabel.setFont(new Font("宋体", Font.BOLD, 16));
		userPanel.add(userLabel);
		
		user = new JTextField();
		user.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		userPanel.add(user);
		user.setColumns(15);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		denglujiemian.add(passwordPanel);
		
		JLabel passwordLabel = new JLabel("\u5BC6\u7801\uFF1A  ");
		passwordLabel.setForeground(Color.CYAN);
		passwordLabel.setFont(new Font("宋体", Font.BOLD, 16));
		passwordPanel.add(passwordLabel);
		
		password = new JPasswordField();
		password.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordPanel.add(password);
		password.setColumns(15);
		
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		buttonPanel.setOpaque(false);
		jf.getContentPane().add(buttonPanel);
		
		JButton signin = new JButton("\u786E\u8BA4");
		signin.setFont(new Font("华文宋体", Font.BOLD, 30));
		signin.setBackground(Color.WHITE);
		signin.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		signin.setForeground(Color.BLACK);
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Initalization init = new Initalization(user.getText(), password.getText());
				init.firstInit();
				jf.dispose();
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("        ");
		buttonPanel.add(lblNewLabel);
		buttonPanel.add(signin);
		
		
//		BackgroundPanel bgp = new BackgroundPanel();
		
		
		jf.setVisible(true);
	}

}
