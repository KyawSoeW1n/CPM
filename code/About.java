package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About implements ActionListener 
{
	private JFrame f;
	private JLabel welcome,title,about1,about2,about3;
	private JButton back;
	
	public About()
	{
		f=new JFrame("About");
		
		back=new JButton("Main Menu");
		back.addActionListener(this);
		
		title=new JLabel("Scheduling Construction Project Using Critical Path Method");
		title.setFont(new Font("Time News Roman",Font.BOLD,25));
		title.setForeground(Color.BLUE);
		

		
		about1=new JLabel("To plan the project complete in time and  minimize project duration." );				
		about1.setFont(new Font("Time News Roman",Font.BOLD,20));		
		
		about2=new JLabel("The system obtains the minimum completion time for a project along with the possible start and finish times for the project activities.");				
		about2.setFont(new Font("Time News Roman",Font.BOLD,20));		
		
		about3=new JLabel("The user can save time, and money.");				
		about3.setFont(new Font("Time News Roman",Font.BOLD,20));		
	
		
	}
	public void setFrame()
	{
		JPanel p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(title);
		p1.setBackground(Color.white);
	
		JPanel p3=new JPanel(new BorderLayout());
		p3.add(p1,BorderLayout.NORTH);
		p3.setBackground(Color.white);
		
		JPanel p5=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p5.setBackground(Color.white);
		p5.add(about1);
		
		JPanel p8=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p8.setBackground(Color.white);
		p8.add(about2);
		
		JPanel p9=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p9.setBackground(Color.white);
		p9.add(about3);
			
		JPanel pabout=new JPanel(new BorderLayout());
		pabout.setBackground(Color.white);
		pabout.add(p5,BorderLayout.NORTH);
		pabout.add(p8,BorderLayout.CENTER);
		pabout.add(p9,BorderLayout.SOUTH);

		JPanel pbut=new JPanel(new FlowLayout(FlowLayout.CENTER));
		pbut.setBackground(Color.white);
		pbut.add(back);

		
		
		JPanel p6=new JPanel(new BorderLayout());
	
	
		p6.add(pabout,BorderLayout.SOUTH);
		
		JPanel p7=new JPanel(new BorderLayout());
		p7.add(p3,BorderLayout.NORTH);
		p7.add(p6,BorderLayout.CENTER);
	
		
		JPanel p=new JPanel(new BorderLayout());
		p.add(p7,BorderLayout.NORTH);
		p.add(pbut,BorderLayout.CENTER);
		
		f.setSize(1400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent arg0)
	{
		Main f1=new Main();
		f1.setFrame();
		f.hide();
	}
	public static void main(String args[])
	{
		About f=new About();
		f.setFrame();
	}
	
}
