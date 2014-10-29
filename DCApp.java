/**
 * Created by sophialin on 10/11/14.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class DCApp implements ActionListener
{
    JTextField runsBox;
    static DCApp app;

    public static void main(String[] args)
    {
        app = new DCApp();
        app.init();
    }

    public void init()
    {
        DCApp gui = new DCApp();

        JFrame frame = new JFrame("Simple GUI");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Click Me!");
        button.addActionListener(gui);
        runsBox = new JTextField(12);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(button, BorderLayout.NORTH);
        JPanel p1 = new JPanel();
        frame.getContentPane().add(p1);
        p1.add(new JLabel("Runs:"));
        p1.add(runsBox);

        JTextArea log = new JTextArea();
        frame.getContentPane().add(log, BorderLayout.CENTER);
        frame.getContentPane().add(runsBox, BorderLayout.SOUTH);

        frame.setSize(600,600);
    }

    public void actionPerformed (ActionEvent evt)
    {
        /*String title = "Greetings";  // Shown in title bar of dialog box.
        String message = "Hello.";
        JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.INFORMATION_MESSAGE);*/
        System.out.println(app.runsBox.getText());
        MonteCarloSim sim = new MonteCarloSim(Integer.parseInt(app.runsBox.getText().trim()));
        sim.runAll();
        System.out.println(sim.getHits());
    }

}