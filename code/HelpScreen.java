package code;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpScreen implements ActionListener {
    private JFrame f;
    private JLabel lmenu1, lmenu2, lmenu3, lmenu;
    private JLabel linput1, linput2, linput3, linput, main1, main2, main3, main4;
    private JLabel lcal1, lcal2, lcal3, lcal4, lcal;
    private JLabel pic11, pic22;
    private JButton but;

    public HelpScreen() {
        f = new JFrame("help");

        ImageIcon img2 = new ImageIcon("Image/start.png");
        pic11 = new JLabel(img2);

        ImageIcon img3 = new ImageIcon("Image/lolz.jpg");
        pic22 = new JLabel(img3);

        linput1 = new JLabel("You should ender the number of activities of the project.It only access integer!!!");

        linput2 = new JLabel("If you want to calculate, click 'Continue'.");

        lcal1 = new JLabel("If you want to input again, click 'Restart'.");


        lcal2 = new JLabel("If you want to find which activities are critical, click 'Calculate'.");


        lcal3 = new JLabel("If you want to find the critical path, click 'Critical Path'.");


        lcal4 = new JLabel("If you want to obtain the schedule of the project, click 'Schedule'.");


        but = new JButton("Main menu");
        but.addActionListener(this);


    }

    public void setFrame() {
        JPanel pbut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pbut.add(but);


        JPanel pic1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pic1.add(pic11);

        JPanel ppic3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ppic3.add(pic22);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 1));
        p1.add(linput1);
        p1.add(linput2);

        JPanel p2 = new JPanel();
        p2.add(pic1, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.EAST);

        JPanel p3 = new JPanel();
        p3.add(pic22);

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(4, 1));
        p4.add(lcal1);
        p4.add(lcal2);
        p4.add(lcal3);
        p4.add(lcal4);
        JPanel p5 = new JPanel();
        p5.add(p3, BorderLayout.CENTER);
        p5.add(p4, BorderLayout.EAST);

        JPanel p6 = new JPanel();
        p1.setLayout(new GridLayout(3, 1));
        p6.add(p2);
        p6.add(p5);
        p6.add(pbut);
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p6);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        MainScreen f1 = new MainScreen();
        f1.setFrame();
        f.hide();
    }

    public static void main(String args[]) {
        HelpScreen f = new HelpScreen();
        f.setFrame();
    }
}
