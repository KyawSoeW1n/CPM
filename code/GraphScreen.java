package code;

import java.awt.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class CartesianFrame extends JFrame {
    JFrame jf = new JFrame();
    public CartesianPanel panel;

    public void showUI() {


        panel = new CartesianPanel();
        jf.add(panel);
        //jf.add(p1,BorderLayout.SOUTH);

        jf.setTitle("Graph");
        jf.setSize(1200, 700);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}


class CartesianPanel extends JPanel {

    // x-axis cod constants
    public static final int X_AXIS_FIRST_X_COORD = 50;
    public static final int X_AXIS_SECOND_X_COORD = 1250;
    public static final int X_AXIS_Y_COORD = 500;

    // y-axis cod constants
    public static final int Y_AXIS_FIRST_Y_COORD = 50;
    public static final int Y_AXIS_SECOND_Y_COORD = 500;
    public static final int Y_AXIS_X_COORD = 50;


    public static final int FIRST_LENGHT = 10;
    public static final int SECOND_LENGHT = 5;

    // size of start coordinate lenght
    public static final int ORIGIN_COORDINATE_LENGHT = 20;

    // distance of coordinate strings from axis
    public static final int AXIS_STRING_DISTANCE = 20;

    //Edit
    public static final int yConstant = 100;

    //Edit


    public void paintComponent(Graphics g) {
        final Color color = Color.black;
        final Color cc = Color.red;

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // x-axis
        g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        // y-axis
        g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD, Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);


        // x-axis arrow
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD - SECOND_LENGHT, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
                X_AXIS_Y_COORD + SECOND_LENGHT, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);

        g2.drawString("Duration in days", X_AXIS_FIRST_X_COORD + 600, X_AXIS_Y_COORD + 60);

        // y-axis arrow
        g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
                Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);

        g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
                Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);

        g2.drawString("Activities", Y_AXIS_X_COORD - 20, Y_AXIS_FIRST_Y_COORD - 10);

        g2.drawString("X", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
                X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        g2.drawString("Y", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
                Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);
        g2.drawString("(0, 0)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE,
                Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);


        int xCoordNumbers = CPM.maxCost + 1;
        int yCoordNumbers = 10;
        int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)
                / xCoordNumbers;
        int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)
                / yCoordNumbers;


        if (CPM.maxCost <= 100) {

            for (int i = 2; i < xCoordNumbers && (i * xLength) < X_AXIS_SECOND_X_COORD; i += 2) {
                g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
                        X_AXIS_Y_COORD - SECOND_LENGHT,
                        X_AXIS_FIRST_X_COORD + (i * xLength),
                        X_AXIS_Y_COORD + SECOND_LENGHT);
                g2.drawString(Integer.toString(i),
                        X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
                        X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);

            }
        } else {
            for (int i = 5; i < xCoordNumbers && (i * xLength) < X_AXIS_SECOND_X_COORD; i += 5) {
                g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
                        X_AXIS_Y_COORD - SECOND_LENGHT,
                        X_AXIS_FIRST_X_COORD + (i * xLength),
                        X_AXIS_Y_COORD + SECOND_LENGHT);
                g2.drawString(Integer.toString(i),
                        X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
                        X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
            }
        }

        //Edit
        int increase = 0;

        for (int j = 0; j < DashboardScreen.criticalName.size(); j++) {

            if (j == 0) {
                g2.drawString(DashboardScreen.criticalName.get(j) + " - " + DashboardScreen.cridur.get(j),
                        X_AXIS_FIRST_X_COORD + 20, yConstant);
                //g2.drawString("Critical Path are  shown in red color   \t"+alert.criticalName.get(j)+" , ",50,600);
                g2.drawString("Critical Path are  shown in red color and non critical path are shown in black color  \t", 50, 600);

                g2.setColor(cc);

                g2.drawLine(X_AXIS_FIRST_X_COORD, yConstant,
                        X_AXIS_FIRST_X_COORD + (DashboardScreen.criEF.get(j) * xLength), yConstant);


            } else {
                increase += 20;
                g2.setColor(color);
                g2.drawString(DashboardScreen.criticalName.get(j) + " - " + DashboardScreen.cridur.get(j),
                        X_AXIS_FIRST_X_COORD + (DashboardScreen.criES.get(j) * xLength) + 20, yConstant + increase);
                //g2.drawString(alert.criticalName.get(j)+" , ",160+increase,600);
                g2.setColor(cc);
                g2.drawLine(X_AXIS_FIRST_X_COORD + (DashboardScreen.criES.get(j) * xLength), yConstant + increase,
                        X_AXIS_FIRST_X_COORD + (DashboardScreen.criEF.get(j) * xLength), yConstant + increase);


            }

        }
        for (int j = 0; j < DashboardScreen.noncriticalName.size(); j++) {
            increase += 20;
            g2.setColor(color);
            g2.drawString(DashboardScreen.noncriticalName.get(j) + " - " + DashboardScreen.nondur.get(j),
                    X_AXIS_FIRST_X_COORD + (DashboardScreen.nonES.get(j) * xLength) + 20, yConstant + increase);
            g2.drawLine(X_AXIS_FIRST_X_COORD + (DashboardScreen.nonES.get(j) * xLength), yConstant + increase,
                    X_AXIS_FIRST_X_COORD + (DashboardScreen.nonLF.get(j) * xLength), yConstant + increase);

        }


    }
}