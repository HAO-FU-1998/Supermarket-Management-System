package GUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
//控制卡片布局管理器的事件监听器类，他加在控制面板cardControl上的button上
public class CardListener implements ActionListener{

	private JPanel card = null;
	private JButton shouye = null;
	private JButton kucunchaxun = null;
	private JButton monixiaoshou = null;
	private JButton xiaoshoutongji = null;
	private JButton shangpincaigou = null;
	private JButton shangpinxiajia = null;
	public CardListener(JPanel card, JButton shouye, JButton kucunchaxun, JButton monixiaoshou, JButton xiaoshoutongji, JButton shangpincaigou, JButton shangpinxiajia) {
		this.card = card;
		this.shouye = shouye;
		this.kucunchaxun = kucunchaxun;
		this.monixiaoshou = monixiaoshou;
		this.xiaoshoutongji = xiaoshoutongji;
		this.shangpincaigou = shangpincaigou;
		this.shangpinxiajia = shangpinxiajia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout)card.getLayout();
		if((JButton)e.getSource() == shouye){
			layout.show(card, "shouye");
		}
		if((JButton)e.getSource() == kucunchaxun){
			layout.show(card, "kucunchaxun");
		}
		if((JButton)e.getSource() == monixiaoshou){
			layout.show(card, "monixiaoshou");
		}
		if((JButton)e.getSource() == xiaoshoutongji){
			layout.show(card, "xiaoshoutongji");
		}
		if((JButton)e.getSource() == shangpincaigou){
			layout.show(card, "shangpincaigou");
		}
		if((JButton)e.getSource() == shangpinxiajia){
			layout.show(card, "shangpinxiajia");
		}
	}

}
