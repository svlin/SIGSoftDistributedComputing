/**
 * Created by Stephen S. and Sophia L. on 10/11/14.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DCApp implements ActionListener {
    JTextField runsBox = new JTextField(40);
    JTextArea log = new JTextArea(10, 10);
    JButton button = new JButton("Click Me!");
    JFrame frame = new JFrame("Calculating Pi");

    static DCApp app;
    String str;
    int val_str;

    public static void main(String[] args) {
        app = new DCApp();
        app.init();
    }
    
    public void init() {
        DCApp gui = new DCApp();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button.addActionListener(gui);

        runsBox.addKeyListener(new KeyAdapter() {
            public void keyTyped (KeyEvent e) {
                char c = e.getKeyChar();

                if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))
                {
                    e.consume();
                }
            }
        });
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(button, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.add(new JLabel("Runs: "));
        panel.add(runsBox);

        log.setEditable(false);
        
        frame.getContentPane().add(log, BorderLayout.CENTER);
        frame.getContentPane().add(runsBox, BorderLayout.SOUTH);
        
        frame.setSize(600, 600);
    }

    public void actionPerformed(ActionEvent evt) {
        str = app.runsBox.getText();

        if (str.length() != 0)
        {
            val_str = Integer.parseInt(str);
        }

        try {
            if (!(str.equals("") || val_str == 0)) {
                System.out.println(str);
                MonteCarloSim sim = new MonteCarloSim(Integer.parseInt(str.trim()));
                sim.runAll();
                long hits = sim.getHits();
                System.out.println(hits);

                Connection connection = new Connection("http://tensile-tenure-727.appspot.com/totals");
                connection.post(hits, Integer.parseInt(str.trim()));
            } else {
                JOptionPane.showMessageDialog(null, "Please only insert a positive integer for the number of runs.");
                System.out.println("User did not insert a valid value for the number of runs.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception Occurred.");
        }
    }
}