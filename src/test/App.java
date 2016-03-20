package test;

/**
 * Created by zgs on 2016/3/19.
 */
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by San on 2016/3/19.
 * name: App
 * description: ≤‚ ‘√Ê∞Â«–ªª
 * remark:
 * question:
 */
public class App extends JFrame implements ActionListener {

    CardLayout layout = new CardLayout(0, 0);
    JPanel panel = null;

    public App() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setResizable(false);
        getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("1");
        btnNewButton.setBounds(25, 26, 91, 21);
        btnNewButton.addActionListener(this);
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("2");
        btnNewButton_1.addActionListener(this);
        btnNewButton_1.setBounds(209, 26, 91, 21);
        getContentPane().add(btnNewButton_1);

        panel = new JPanel();
        panel.setBounds(25, 71, 317, 169);
        getContentPane().add(panel);
        panel.setLayout(layout);

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, "p1");

        JLabel lblNewLabel = new JLabel("panel1");
        panel_1.add(lblNewLabel);

        JPanel panel_2 = new JPanel();
        panel.add(panel_2, "p2");

        lblNewLabel = new JLabel("panel2");
        panel_2.add(lblNewLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

    public void actionPerformed(ActionEvent e) {
        if ("1".equals(e.getActionCommand())) {
            layout.show(panel, "p1");
        }

        if ("2".equals(e.getActionCommand())) {
            layout.show(panel, "p2");
        }
    }
}
