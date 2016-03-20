/**
 * Project Name:ADSN
 * File Name:df.java
 * Package Name:test
 * Date:2015年12月24日下午8:27:29
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;

import jfreechart.HoistryNode;

import javax.swing.*;
import java.awt.*;

/**
 * ClassName:df <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年12月24日 下午8:27:29 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class df extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			df dialog = new df();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public df() {
		setLayout(new BorderLayout());
		//HoistryNode h=new HoistryNode();
		//add(h, BorderLayout.CENTER);
		setSize(200, 200);

	}
}

