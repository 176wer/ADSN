/**
 * Project Name:ADSN
 * File Name:san.java
 * Package Name:test
 * Date:2015年12月30日下午4:33:26
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;

/**
 * ClassName:san <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年12月30日 下午4:33:26 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class san {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					san window = new san();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public san() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setMargin(new Insets(1,3,3,3));
		 		btnNewButton.setBounds(45, 47, 71, 66);
		frame.getContentPane().add(btnNewButton);
		
		 String image=Ziji.class.getResource("/image/card.png").getFile(); 
	        
	       final Image images=Toolkit.getDefaultToolkit().getImage(image);
		 JLabel  la=new JLabel(){
		       public void paint(Graphics gs){
		    	   Graphics2D g2= (Graphics2D) gs;
		    	   g2.drawImage(images, 0, 0, this);
		    	   g2.finalize();
		       }
		       };
		    la.setBounds(2, 2  ,100, 100); 
		    frame.getContentPane().add(la);
 		System.out.println(image);
        ImageIcon icon=new ImageIcon(image);
	}

}

