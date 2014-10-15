package com.company;

/**
 * Created by sophialin on 10/11/14.
 */

import javax.swing.*;
import java.awt.event.*;

public class DCApp implements ActionListener
{
    public static void main(String[] args)
    {
        init();
    }

    public static void init()
    {
        DCApp gui = new DCApp();

        JFrame frame = new JFrame("Simple GUI");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Click Me!");
        button.addActionListener(gui);

        frame.getContentPane().add(button);
        frame.pack();
    }

    public void actionPerformed (ActionEvent evt)
    {
        String title = "Greetings";  // Shown in title bar of dialog box.
        String message = "Hello.";
        JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }

}