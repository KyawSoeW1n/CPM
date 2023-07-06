package code;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Output implements ActionListener {
    public static JFrame f;
    public static JTextArea area;

    public static void setFrame() {
        area = new JTextArea();
        area.setEditable(false);
        JScrollPane sp = new JScrollPane(area);

        f = new JFrame("Critical Path Method");
        f.setSize(850, 500);
        f.add(sp);
        f.setVisible(true);
    }

    public static void setText(String text) {

        area.append("\n" + text);
        area.setFont(new Font("Time News Roman", Font.BOLD, 16));


    }

    public void actionPerformed(ActionEvent arg) {
        int d = JOptionPane.showConfirmDialog(null, "Do you want to exit.");
        if (d == JOptionPane.YES_OPTION)
            f.dispose();
        else if (d == JOptionPane.NO_OPTION) {
            MainScreen f1 = new MainScreen();
            f1.setFrame();
        }
    }

    public static void setTextA(Object[] stringArray) {
        area.append(null);
        area.setFont(new Font("Time News Roman", Font.BOLD, 25));
        area.disable();

    }

    public static void main(String args[]) {
        Output.setFrame();
        Output.setText("");
    }

}
