/**
 * Created by Stephen, Andrew, and Sophia on 10/11/14.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DCApp implements ActionListener {
    JTextField runsBox;
    JTextArea log;
    static DCApp app;
    String str;
    int val_str;
    //boolean letter;
    
    public static void main(String[] args) {
        app = new DCApp();
        app.init();
    }
    
    public void init() {
        DCApp gui = new DCApp();
        
        JFrame frame = new JFrame("Calculating Pi");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Click Me!");
        button.addActionListener(gui);
        runsBox = new JTextField(40);
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(button, BorderLayout.NORTH);
        
        JPanel p1 = new JPanel();
        frame.getContentPane().add(p1);
        p1.add(new JLabel("Runs: "));
        p1.add(runsBox);
        
        log = new JTextArea(10, 10);
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
            //letter = str.matches(".*[a-zA-Z]+.*");
        }
        
        try {
            if (!(str.equals("") || val_str <= 0)) {
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