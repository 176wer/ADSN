package gui;

import control.MyLogger;
import control.NodeLEList;
import control.NodeLife;
import control.SerialConnectionException;
import core.DrawCurve;
import core.Surface;
import gui.toolbar.QueryNode;
import gui.toolbar.ViewExper;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class MainFrame extends Thread {

    private JFrame frame;
    private SerialParameters parameters;
    private DrawCurve drawCurve;
    private Surface NodeTopo;
    private SerialConnection SerCon;
    private JTabbedPane tabbedPane;
    private TablePanel tablePanel;
    private DynamicTree treePanel;
    private JButton gatherButton;
    private JButton CloseButton;
    private ConfDialog conf;
    private JTable table;
    private long startTime = System.nanoTime();
    private JLabel timelabel;
    private JLabel recorderLabel;
    private JLabel nodeCount;// ��¼�ڵ���
    private JTextArea logArea;
    private boolean isLife = false;// �жϽڵ��Ƿ�ʼ��������
    // �����ڵ�������
    private HashMap<String, NodeLife> lifeMap = new HashMap<String, NodeLife>();
    // �����ڵ��������������յ���������Ϊ1�����͵���������Ϊ2
    private HashMap<String, Integer> energy = new HashMap<String, Integer>();
    private JMenu menu_2;
    private JMenuItem mntmNewMenuItem_2;
    private JCheckBox chckbxNewCheckBox;
    private JCheckBox chckbxNewCheckBox_1;
    private boolean paintAll = true;// true,���������˽����ػ棬false��ѡ�еĵ�����ػ�
	private JMenu add;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                 
                  
                    
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame window = new MainFrame();
                    window.frame.setVisible(true);
                    window.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the application.
     */
    public MainFrame() {
        parameters = new SerialParameters();

        initialize();
        SerCon = new SerialConnection(this);

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 1408, 877);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, 1304, 41);
        frame.getContentPane().add(toolBar);

        gatherButton = new JButton("�ɼ�����");
        gatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Cursor previousCursor = frame.getCursor();
                if (conf == null) {
                    JOptionPane.showMessageDialog(frame, "���Ƚ��в�������", "��ʾ", JOptionPane.ERROR_MESSAGE,
                            new ImageIcon(MainFrame.class.getResource("/image/error.png")));

                    return;
                }
                gatherButton.setEnabled(false);
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                conf.setParameters();
                try {
                    SerCon.openConnection();
                    isLife = true;
                    System.out.println("�������");
                } catch (SerialConnectionException e2) {
                    AlertDialog ad = new AlertDialog(frame, "Error Opening Port!", "Error opening port,",
                            e2.getMessage() + ".", "Select new settings, try again.");
                    gatherButton.setEnabled(true);
                    frame.setCursor(previousCursor);
                    return;
                }
                portOpened();
                frame.setCursor(previousCursor);
            }
        });
        gatherButton.setIcon(new ImageIcon(MainFrame.class.getResource("/image/card.png")));
        toolBar.add(gatherButton);

        JButton SetparamButton = new JButton("��������");
        SetparamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conf = new ConfDialog(frame, parameters, SerCon);
                conf.setSize(500, 300);
                conf.setVisible(true);

            }
        });
        SetparamButton.setIcon(new ImageIcon(MainFrame.class.getResource("/image/gear.png")));
        toolBar.add(SetparamButton);

        JButton LocatButton = new JButton("λ������");
        LocatButton.setIcon(new ImageIcon(MainFrame.class.getResource("/image/internet.png")));
        toolBar.add(LocatButton);

        JButton NetButton = new JButton("��������");
        NetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HistryTopo window = new HistryTopo();


            }
        });
        NetButton.setIcon(new ImageIcon(MainFrame.class.getResource("/image/node.png")));
        toolBar.add(NetButton);

        JButton MonitorButton = new JButton("���");
        MonitorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Monitor dialog = new Monitor();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }

            }
        });
        MonitorButton.setIcon(new ImageIcon(MainFrame.class.getResource("/image/monitor.png")));
        toolBar.add(MonitorButton);

        CloseButton = new JButton("�رմ���");
        CloseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                portClosed();
                isLife = false;
            }
        });
        MainGUI m=new MainGUI();
        CloseButton.setIcon(new ImageIcon(MainFrame.class.getResource("/image/serial.png")));
        toolBar.add(CloseButton);

        JButton button = new JButton("ˢ��");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FreshDialog dialog = new FreshDialog(SerCon);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);

                } catch (Exception en) {
                    en.printStackTrace();
                }
            }
        });
        button.setIcon(new ImageIcon(MainFrame.class.getResource("/image/refresh.png")));
        toolBar.add(button);

        JButton btnNewButton = new JButton("�ڵ�״̬");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ItemLabelDemo1 demo = new ItemLabelDemo1("Item Label Demo 1", lifeMap, energy);
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
            }
        });
        toolBar.add(btnNewButton);

        JButton queryNode = new JButton("�ڵ��ѯ");
        queryNode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                QueryNode dialog = new QueryNode();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        queryNode.setIcon(new ImageIcon(MainFrame.class.getResource("/image/search.png")));
        toolBar.add(queryNode);

        ParameterPanel panel = new ParameterPanel();
        panel.setBorder(new TitledBorder(null, "������ʾ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 571, 173, 207);
        frame.getContentPane().add(panel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 72, 173, 483);
        frame.getContentPane().add(scrollPane);

        JScrollPane scrolTopo = new JScrollPane();
        NodeTopo = new Surface();
        scrolTopo.setViewportView(NodeTopo);

        treePanel = new DynamicTree(panel, NodeTopo);
        treePanel.tree.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "���νڵ�",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setViewportView(treePanel);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(193, 51, 873, 504);
        frame.getContentPane().add(tabbedPane);


        tabbedPane.addTab("�ڵ�����", null, scrolTopo, null);
        treePanel.setTable(tabbedPane);
        // �������������
         drawCurve= new DrawCurve();
        tabbedPane.addTab("��������", null, drawCurve, null);
        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_1.setBounds(193, 565, 873, 207);
        frame.getContentPane().add(tabbedPane_1);

        tablePanel = new TablePanel();
        tabbedPane_1.addTab("��׼����", null, tablePanel, null);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"16����ԭʼ����"}));

        JScrollPane scrollpanel = new JScrollPane(table);
        tabbedPane_1.addTab("ԭʼ����", null, scrollpanel, null);

        JLabel lblNewLabel = new JLabel("����ʱ��");
        lblNewLabel.setBounds(20, 788, 72, 15);
        frame.getContentPane().add(lblNewLabel);

        timelabel = new JLabel("");
        timelabel.setBounds(93, 788, 72, 15);
        frame.getContentPane().add(timelabel);

        JLabel label_1 = new JLabel("��¼��");
        label_1.setBounds(175, 788, 48, 15);
        frame.getContentPane().add(label_1);

        recorderLabel = new JLabel("");
        recorderLabel.setBounds(237, 788, 54, 15);
        frame.getContentPane().add(recorderLabel);

        JLabel lblNewLabel_1 = new JLabel("�ڵ���");
        lblNewLabel_1.setBounds(304, 788, 48, 15);
        frame.getContentPane().add(lblNewLabel_1);

        nodeCount = new JLabel("");
        nodeCount.setBounds(363, 788, 54, 15);
        frame.getContentPane().add(nodeCount);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1
                .setViewportBorder(new TitledBorder(null, "��־", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane_1.setBounds(1076, 72, 228, 483);
        frame.getContentPane().add(scrollPane_1);

        logArea = new JTextArea();
        scrollPane_1.setViewportView(logArea);

        chckbxNewCheckBox = new JCheckBox("New check box");
        chckbxNewCheckBox.setBounds(428, 487, 103, 23);
        frame.getContentPane().add(chckbxNewCheckBox);

        chckbxNewCheckBox_1 = new JCheckBox("transform");
        chckbxNewCheckBox_1.setBounds(1201, 799, 103, 23);
        frame.getContentPane().add(chckbxNewCheckBox_1);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = new JMenu("\u6587\u4EF6");
        menuBar.add(menu);

        JMenu menu_1 = new JMenu("\u8BBE\u7F6E");
        menuBar.add(menu_1);

        JMenuItem mntmNewMenuItem = new JMenuItem("������־");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyLogger Mylog = new MyLogger();
                Mylog.start();
            }
        });
        menu_1.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("��������");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CameraDialog camera = new CameraDialog(drawCurve, NodeTopo);
                camera.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                camera.setVisible(true);
            }
        });
        menu_1.add(mntmNewMenuItem_1);

        menu_2 = new JMenu("�鿴");
        
        
        menuBar.add(menu_2);

        mntmNewMenuItem_2 = new JMenuItem("ʵ��");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewExper dialog = new ViewExper();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        
        
        menu_2.add(mntmNewMenuItem_2); 
        
        JMenu menu_3=new JMenu("����");
        menuBar.add(menu_3);
        JMenuItem item3=new JMenuItem("���ݿ���");
        menu_3.add(item3);
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_MASK));
        item3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			  MainGUI m=new MainGUI();
				
			}
        	
        });
        
        JMenuItem item_4=new JMenuItem("�������");
        menu_3.add(item_4);
        item_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK)); 
        item_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
        	
        });
        
        
    }

    public void portClosed() {
        SerCon.closeConnection();
        gatherButton.setEnabled(true);
        CloseButton.setEnabled(false);

    }

    public void portOpened() {
        gatherButton.setEnabled(false);
        CloseButton.setEnabled(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    /*
     * ���ز���reference for �������
     */
    public DrawCurve getDrawCurve() {
        return  drawCurve;
    }

    /*
     * ����reference for SerialParameters
     */
    public SerialParameters getSerialParameters() {
        return parameters;
    }

    /*
     * ����reference for DynamicTree
     */
    public DynamicTree getDynamicTree() {
        return treePanel;
    }

    /*
     * ����reference for Topo
     */
    public Surface getTopo() {
        return NodeTopo;
    }

    /*
     * ����reference for tablepane
     */
    public TablePanel getTablePane() {
        return tablePanel;
    }

    public JTable getJTable() {
        return table;
    }

    public JLabel getNodeLabel() {
        return nodeCount;
    }

    public JTextArea getLogArea() {
        return logArea;
    }

    /*
     * �����������ʱ��
     */
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                long endTime = (System.nanoTime() - startTime) / 1000000000;
                String rtime = Long.toString(endTime);
                timelabel.setText(rtime);
                recorderLabel.setText(String.valueOf(SerCon.getRecorder()));
                if (isLife) {
                    new NodeLEList(energy, lifeMap);
                }

            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return the lifeMap
     */
    public HashMap<String, NodeLife> getLifeMap() {
        return lifeMap;
    }

    /**
     * lifeMap.
     *
     * @param lifeMap the lifeMap to set
     */
    public void setLifeMap(HashMap<String, NodeLife> lifeMap) {
        this.lifeMap = lifeMap;
    }

    /**
     * @return the energy
     */
    public HashMap<String, Integer> getEnergy() {
        return energy;
    }

    /**
     * energy.
     *
     * @param energy the energy to set
     */
    public void setEnergy(HashMap<String, Integer> energy) {
        this.energy = energy;
    }

    public SerialConnection getConnecton() {
        return SerCon;
    }
}
