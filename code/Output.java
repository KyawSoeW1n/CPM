package code;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Output implements ActionListener{
	public static JFrame f;
	public static JTextArea area;
	
	public static void setFrame()
	{	
		area=new JTextArea();
		area.setEditable(false);
		JScrollPane sp=new  JScrollPane(area);
	
		f=new JFrame("Critical Path Method");
		f.setSize(850, 500);
		f.add(sp);
		f.setVisible(true);
	}
	public static void setText(String text){
		
		area.append("\n"+text);
		area.setFont(new Font("Time News Roman",Font.BOLD,16));
		

	}
	public static void setText1(String text)
	{	
		area.append("\n"+text);
		area.setFont(new Font("Time News Roman",Font.BOLD,20));
	}
	public void actionPerformed(ActionEvent arg) {
		int d=JOptionPane.showConfirmDialog(null,"Do you want to exit." );
		 if(d==JOptionPane.YES_OPTION)
			 f.hide();
		 else if(d==JOptionPane.NO_OPTION)
		 {
			 Main f1=new Main();
			 f1.setFrame();
		 }
	 }
	public static void setTextA(Object[] stringArray) 
	{	
		area.append(null);
		area.setFont(new Font("Time News Roman",Font.BOLD,25));
		area.disable();
		
	}
	
	public static void main(String args[])
	{
		Output.setFrame();
		Output.setText("GGWP");
	}
	
}
