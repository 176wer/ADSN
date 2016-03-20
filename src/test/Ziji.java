/**
 * Project Name:ADSN
 * File Name:Ziji.java
 * Package Name:test
 * Date:2015年12月30日下午3:31:37
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

 
 
/**
 * ClassName:Ziji <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年12月30日 下午3:31:37 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Ziji extends JFrame{
	private JTabbedPane pane;
public Ziji(){
	super("JTable测试");
	setLayout(new BorderLayout());
	pane=new JTabbedPane();
	JPanel pane1=new JPanel();
	pane1.setBackground(Color.BLACK);
	pane.add(pane1);
	add(pane,BorderLayout.CENTER);
	setSize(200,300);
	setVisible(true);
	
   JPanel pane2=new JPanel();
   pane2.setBackground(Color.blue);
   pane.add(pane2);
    pane.setTabComponentAt(0, new ButtonTabComponent(pane,"dddddd"));
   
}
public static void main(String[] agrs){
	new Ziji();
}
}
  class ButtonTabComponent extends JPanel {
    private final JTabbedPane pane;
   private String name=null;
 
    private   String NodeName;
    private int is;
    public ButtonTabComponent(final JTabbedPane pane, final String name) {
         
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.name=name;

        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.pane = pane;
        setOpaque(false);
        
        String image=Ziji.class.getResource("/image/card.png").getFile(); 
        
       final Image images=Toolkit.getDefaultToolkit().getImage(image);
       JLabel la=new JLabel("fdgdfg"){
       public void paint(Graphics gs){
    	   Graphics2D g2= (Graphics2D) gs;
    	   g2.drawImage(images, 0, 0, this);
    	   g2.finalize();
       }
       };
        
        add(la);
        //make JLabel read titles from JTabbedPane
        JLabel label = new JLabel() {
            /**
			 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
			 * @since JDK 1.6
			 */
			private static final long serialVersionUID = 1L;

			public String getText() {

                return name;
            }
        };
 
        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        //tab button
        JButton button = new TabButton();
        add(button);
       
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("close this tab");
            //Make the button looks the same for all Laf's
            setUI(new BasicButtonUI());
            //Make it transparent
            setContentAreaFilled(false);
            //No need to be focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //Making nice rollover effect
            //we use the same listener for all buttons
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            //Close the proper tab by clicking the button
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            if (i != -1) {
                pane.remove(i);
            }
            
        }

        //we don't want to update UI for this button
        public void updateUI() {
        }

        //paint the cross
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover()) {
                g2.setColor(Color.MAGENTA);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}


