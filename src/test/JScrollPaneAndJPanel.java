/**
 * Project Name:ADSN
 * File Name:dd.java
 * Package Name:test
 * Date:2016��1��9������8:51:27
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * ClassName:dd <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016��1��9�� ����8:51:27 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class JScrollPaneAndJPanel extends JFrame {
	 public JScrollPaneAndJPanel() {
	  super("TestJScrollPane");
	  this.setLayout(null);
	  this.setBounds(200, 200, 300, 300);
	  JPanel panel = new JPanel();
	  panel.setPreferredSize(new Dimension(200,100));//��Ҫ�������룬����panel����ѡ��С��ͬʱ��֤���ߴ���JScrollPane�Ŀ��ߣ����������JScrollPane�Ż���ֹ�����
	  JButton button1  = new JButton("1");  
	  panel.add(button1);
	  JButton button2  = new JButton("2");  
	  panel.add(button2);
	  JButton button3  = new JButton("3");  
	  panel.add(button3);
	  JButton button4  = new JButton("4");  
	  panel.add(button4);
	  JButton button5  = new JButton("5");  
	  panel.add(button5);
	  JButton button6  = new JButton("6");  
	  panel.add(button6);
	  JButton button7  = new JButton("7");  
	  panel.add(button7);
	  JScrollPane scrollPane = new JScrollPane(panel);
	  scrollPane.setBounds(10, 10, 175, 70);
	  this.getContentPane().add(scrollPane);
	  this.setVisible(true);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	 }
	 public static void main(String[] args) {
	  new JScrollPaneAndJPanel();
	 }
	}