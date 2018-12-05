package code;



import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.CPM.Task;

public class Dashboard implements ActionListener {
	private JFrame f;
	private JLabel lid, ldes, ldur, lpre, lpid,proname,maname,a,b;
	private JTextField tinput[][];
	private JButton calculate, restart;
	private JPanel p;
	private int noAct;

	// Edit
	int duration;
	private JButton schdule, cpath;
	public static ArrayList<String> criticalName = new ArrayList<String>();
	public static ArrayList<String> noncriticalName = new ArrayList<String>();
	public static ArrayList<Integer> criES = new ArrayList<Integer>();
	public static ArrayList<Integer> criEF = new ArrayList<Integer>();
	public static ArrayList<Integer> nonES = new ArrayList<Integer>();
	public static ArrayList<Integer> nonLF = new ArrayList<Integer>();
	public static ArrayList<Integer> cridur = new ArrayList<Integer>();
	public static ArrayList<Integer> nondur = new ArrayList<Integer>();

	public Dashboard(int noAct,String project,String manager) {
		a=new JLabel(project);
		b=new JLabel(manager);
		this.noAct = noAct;
		tinput = new JTextField[noAct][4];
		proname=new JLabel("Project name:");
		maname=new JLabel("Project manager:");
		f = new JFrame("Critical Path");
		lid = new JLabel("Activity");
		ldes = new JLabel("Description");
		ldur = new JLabel("Duration");
		lpre = new JLabel("Predecessor");
		for (int i = 0; i < noAct; i++) {
			for (int j = 0; j < 4; j++)
				tinput[i][j] = new JTextField();
		}
		calculate = new JButton("Calculate");
		calculate.addActionListener(this);

		restart = new JButton("Restart");
		restart.addActionListener(this);

		schdule = new JButton("Result");
		schdule.addActionListener(this);
	
	

		cpath = new JButton("Critical Path");
		cpath.addActionListener(this); 
	}
	public void setPanel() {
		JPanel p1 = new JPanel(new GridLayout((noAct + 1), 4, 10, 5));
		p1.add(lid);
		p1.add(ldes);
		p1.add(ldur);
		p1.add(lpre);
		p1.setBackground(Color.white);
		JPanel p4=new JPanel(new GridLayout(2,2));
		p4.add(proname);
		p4.add(a);
		p4.add(maname);
		p4.add(b);
		p4.setBackground(Color.white);

		for (int i = 0; i < noAct; i++) {
			for (int j = 0; j < 4; j++)
				p1.add(tinput[i][j]);
		}
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(restart);
		p2.add(calculate);
		p2.setBackground(Color.white);
		JPanel p10=new JPanel();
		p10.add(schdule);
		JPanel p3=new JPanel(new BorderLayout());
		p3.add(p2,BorderLayout.CENTER);
		p3.add(p10,BorderLayout.EAST);
		p3.setBackground(Color.white);
		
		p = new JPanel(new BorderLayout());
		p.add(p1, BorderLayout.CENTER);
		p.add(p4,BorderLayout.NORTH);
		p.add(p3, BorderLayout.SOUTH);
		p.setBackground(Color.white);
		
	}
	public void setFrame()
	{
		f.setLocation(100, 100);
		if(noAct<10)
			f.setSize(700,500);
		else
		f.setSize(1250,1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource() == restart) {
			{
				Main f1 = new Main();
				f1.setFrame();
				f.hide();
				
			}
		} else {
			
			// f.hide();
			HashSet<Task> allTasks = new HashSet<Task>();
			ArrayList<String> remain = new ArrayList<String>();
			Task end = new Task("End", 0);
			allTasks.add(end);
		
			ArrayList<String> name = new ArrayList<String>();
			ArrayList<Integer> dur = new ArrayList<Integer>();
			
			
			ArrayList<ArrayList<String>> successor = new ArrayList<ArrayList<String>>();
			
			ArrayList<String> t = new ArrayList<String>();

			String[] sus = new String[noAct];
		
				
			HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
			for (int i = 0; i < noAct; i++) {
				name.add(tinput[i][0].getText());
				String tname = tinput[i][0].getText();
				remain.add(tinput[i][0].getText());
				dur.add(Integer.parseInt(tinput[i][2].getText()));
				String s = tinput[i][3].getText();
				
			
				
				StringTokenizer st = new StringTokenizer(s,",");

				while (st.hasMoreTokens()) {
					String key = st.nextToken();
					if (!key.equals("-")) {
						if (hm.containsKey(key)) {
							hm.get(key).add(tname);
						} else {
							ArrayList<String> temp = new ArrayList<String>();
							temp.add(tname);
							hm.put(key, temp);
						}
					}
				}

			}

			System.out.println("name" + name);
			System.out.println(hm);
			for (int j = 0; j < name.size(); j++) {
				if (hm.containsKey(name.get(j)))
					successor.add(hm.get(name.get(j)));
				else {
					ArrayList<String> tt = new ArrayList<String>();
					successor.add(tt);
				}

			}
			Task ts[] = new Task[noAct];
			for (int i = noAct - 1; i >= 0; i--) {
	
				if (successor.get(i).size() == 0) {
					ts[i] = new Task(name.get(i), dur.get(i), end);
					allTasks.add(ts[i]);
				} else {
					ArrayList<Integer> k = new ArrayList<Integer>();
					ArrayList<String> ss = successor.get(i);

					int found;
					System.out.println("SS" + ss);
					System.out.println("remain" + remain);
					for (String temp : ss) {
						found = remain.indexOf(temp);
						if (found >= 0) {
							k.add(found);
						
						}
					}

					System.out.println("K" + k.size());
					Task tsub[] = new Task[k.size()];
					int ii = 0;
					for (int ind1 : k) {
						tsub[ii] = ts[ind1];
						ii++;
					}
					ts[i] = new Task(name.get(i), dur.get(i), tsub);
					allTasks.add(ts[i]);
				}
			}

			for (int k = 0; k < successor.size(); k++) {
				ArrayList<String> ss = successor.get(k);

				int found;
				for (String temp : ss) {
					found = remain.indexOf(ss);
					if (found >= 0) {
						// k.add(found);
						remain.set(found, "[");
					}
				}
			}
			System.out.println(remain);
			int count = 0;
			for (int ind = 0; ind < remain.size(); ind++) {
				if (!remain.get(ind).equals("[")) {
					count++;
				}

			}
			System.out.println(count);
			Task tsub[] = new Task[count];
			int ii = 0;
			for (int ind = 0; ind < remain.size(); ind++) {
				if (!remain.get(ind).equals("[")) {
					tsub[ii] = ts[ind];
					ii++;
				}

			}
			Task start = new Task("Start", 0, tsub);
			allTasks.add(start);

			Task[] result = CPM.criticalPath(allTasks);
		
			if (arg.getSource() == schdule) {
		
		
				for (Task rr : result) {
					if (rr.earlyStart == rr.latestStart) {
						if (rr.name.equals("Start") || rr.name.equals("End")) {
							System.out.println("No need");
						} else {
							criticalName.add(rr.name);
							criES.add(rr.earlyStart);
							criEF.add(rr.earlyFinish);
							cridur.add(rr.cost);
						}
					} else {
						noncriticalName.add(rr.name);
						nonES.add(rr.earlyStart);
						nonLF.add(rr.latestFinish);
						nondur.add(rr.cost);
					}
				}

				System.out.println("Size of criticalName:"
						+ criticalName.size());
				System.out.println("Size of NoncriticalName:"
						+ noncriticalName.size());
				System.out.println("Size of nonES " + nonES.size());

				System.out.println("Critical are");
				for (int i = 0; i < criticalName.size(); i++) {
					System.out.print(criticalName.get(i) + " ");
				}
				System.out.println("Critical ES are");
				for (int i = 0; i < criES.size(); i++) {
					System.out.print(criES.get(i) + " ");
				}
				System.out.println("Critical EF are");
				for (int i = 0; i < criEF.size(); i++) {
					System.out.print(criEF.get(i) + " ");
				}
				System.out.println("Noncritical name are");
				for (int i = 0; i < noncriticalName.size(); i++) {
					System.out.print(noncriticalName.get(i) + " ");
				}
				System.out.println("NonCritical ES are");
				for (int i = 0; i < nonES.size(); i++) {
					System.out.print(nonES.get(i) + " ");
				}
				System.out.println("NonCritical LF are");
				for (int i = 0; i < nonLF.size(); i++) {
					System.out.print(nonLF.get(i) + " ");
				}
				// Edit

				duration = CPM.maxCost(allTasks);
				System.out.println("Duration is " + duration);

				CartesianFrame frame = new CartesianFrame();
		
				frame.showUI();
				frame.dispose();

			} else if (arg.getSource() == calculate) {
				Output.setFrame();
				CPM.print(result);
				// Edit
				

				
			}
		}
	}
}
