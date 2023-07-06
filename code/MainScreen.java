package code;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainScreen implements ActionListener {
    private JFrame f;
    private JLabel l1, la, lpname, lmname;
    private JTextField jactivity, jpname, jmname;
    private JButton oki, about, help;
    private JPanel p;

    public MainScreen() {


        f = new JFrame("Critical Path Method");

        l1 = new JLabel("Project Scheduling Management Using Critical Path Method");
        l1.setFont(new Font("Time News Roman", Font.BOLD, 20));
        l1.setForeground(Color.BLUE);

        la = new JLabel("Enter the number of activites");
        la.setFont(new Font("Time News Roman", Font.BOLD, 18));

        lpname = new JLabel("Enter project name");
        lpname.setFont(new Font("Time News Roman", Font.BOLD, 18));

        lmname = new JLabel("Enter project manager's name");
        lmname.setFont(new Font("Time News Roman", Font.BOLD, 18));


        jactivity = new JTextField();
        jpname = new JTextField();
        jmname = new JTextField();

        help = new JButton("Help");
        help.addActionListener(this);

        oki = new JButton("Continue");
        oki.addActionListener(this);

        about = new JButton("About");
        about.addActionListener(this);

    }

    public void setFrame() {

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p2.add(l1);
        p2.setBackground(Color.white);

        JPanel p3 = new JPanel(new BorderLayout());

        p3.add(p2, BorderLayout.CENTER);

        JPanel p4 = new JPanel(new GridLayout(3, 2, 10, 20));
        p4.setBackground(Color.white);
        p4.add(la);
        p4.add(jactivity);
        p4.add(lpname);
        p4.add(jpname);
        p4.add(lmname);
        p4.add(jmname);

        JPanel p5 = new JPanel();
        p5.add(oki);

        JPanel p7 = new JPanel(new GridLayout(1, 2, 5, 5));
        p7.add(about);
        p7.add(help);

        JPanel p8 = new JPanel();
        p8.setLayout(new FlowLayout());
        p8.setBackground(Color.white);
        p8.add(p7);
        p8.add(p5);

        JPanel p6 = new JPanel(new BorderLayout());
        p6.setBackground(Color.white);
        p6.add(p4, BorderLayout.NORTH);
        p6.add(p8, BorderLayout.CENTER);


        p = new JPanel();
        p.setBackground(Color.white);
        p.add(p3);
        p.add(p6);


        f.setSize(900, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent arg) {
        if (arg.getSource() == oki) {
            int activities = 0;
            String str = jactivity.getText();
            String proname = jpname.getText();
            String maname = jmname.getText();
            try {
                activities = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "plz type only integer in no of activites" + "\n");
            }

            activities = Integer.parseInt(jactivity.getText());
            DashboardScreen f2 = new DashboardScreen(activities, proname, maname);
            f2.setPanel();
            f2.setFrame();
            f.dispose();
        } else if (arg.getSource() == about) {
            AboutPage a = new AboutPage();
            a.setFrame();
            f.dispose();
        } else if (arg.getSource() == help) {
            HelpScreen h = new HelpScreen();
            h.setFrame();
            f.dispose();
        }

    }

    public static void main(String args[]) {
        MainScreen f = new MainScreen();
        f.setFrame();
    }


}

