package code;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import code.Output;
import code.CPM.Task;
public class CPM {
    public static int maxCost;
    public static String format = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n";

    public static void main(String[] args) {

    	
        HashSet<Task> allTasks = new HashSet<Task>();
        Task end = new Task("End", 0);
        Task F = new Task("F", 2, end);
        Task A = new Task("A", 3, end);
        Task X = new Task("X", 4, F, A);
        Task Q = new Task("Q", 2, A, X);
        Task start = new Task("Start", 0, Q);
        allTasks.add(end);
        allTasks.add(F);
        allTasks.add(A);
        allTasks.add(X);
        allTasks.add(Q);
        allTasks.add(start);
        Task[] result = criticalPath(allTasks);
        print(result);
    }

    public static class Task {
        // the actual cost of the task
        public int cost;
        // the cost of the task along the critical path
        public int criticalCost;
        // a name for the task for printing
        public String name;
        // the earliest start
        public int earlyStart;
        // the earliest finish
        public int earlyFinish;
        // the latest start
        public int latestStart;
        // the latest finish
        public int latestFinish;
        // the tasks on which this task is dependant
        public HashSet<Task> dependencies = new HashSet<Task>();
        
        public Task(String name, int cost, Task... dependencies) {
            this.name = name;
            this.cost = cost;
         //  output.setText(name+cost+dependencies.length);
            for (Task t : dependencies) {
                this.dependencies.add(t);
            }
            this.earlyFinish = -1;
            
           
            //for(Task t:dependencies)
            	//output.setText(t.name+",");
            //output.setText();
        }

        public void setLatest() {
            latestStart = maxCost - criticalCost;
            latestFinish = latestStart + cost;
        }

        public String[] toStringArray() {
            String criticalCond = earlyStart == latestStart ? "Yes" : "No";
            String[] toString = { name, earlyStart + "", earlyFinish + "", latestStart + "", latestFinish + "",
                    latestStart - earlyStart + "", criticalCond };
            return toString;
        
        }
        
        private  String testCritical()
        {
        	String temp=null;
        	//System.out.println("\n  Critical path:");
        	
        		if(earlyStart==latestStart)
               	{
               			if(name!="Start"|| name!="End")
               			{
               				temp =name;
               				return temp;
               			}
               	}
        	
        	return null;
    	}
        

        public boolean isDependent(Task t) 
        {
            // is t a direct dependency?
            if (dependencies.contains(t)) {
                return true;
            }
            // is t an indirect dependency
            for (Task dep : dependencies) {
                if (dep.isDependent(t)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Task[] criticalPath(Set<Task> tasks) {
    	
    	
        HashSet<Task> cc = new HashSet<Task>();
        HashSet<Task> noncc= new HashSet<Task>();
         
        HashSet<Task> completed = new HashSet<Task>();
        
        HashSet<Task> remaining = new HashSet<Task>(tasks);
    
        while (!remaining.isEmpty()) {
            boolean progress = false;

            // find a new task to calculate
            for (Iterator<Task> it = remaining.iterator(); it.hasNext();) {
                Task task = it.next();
                if (completed.containsAll(task.dependencies)) {
                    int critical = 0;
                    for (Task t : task.dependencies) {
                        if (t.criticalCost > critical) {
                            critical = t.criticalCost;
                        }
                    }
                    task.criticalCost = critical + task.cost;
     
                    completed.add(task);
                    it.remove();
   
                    progress = true;
                }
            }

            if (!progress)
                throw new RuntimeException("Cyclic dependency, algorithm stopped!");
        }


        int max=maxCost(tasks);
    
        HashSet<Task> initialNodes = initials(tasks);
        calculateEarly(initialNodes);

        Task[] ret = completed.toArray(new Task[0]);

        Arrays.sort(ret, new Comparator<Task>() {

            public int compare(Task o1, Task o2) {
                return o1.name.compareTo(o2.name);
            }
        });
              
        
               return ret;
          
    }

    public static void calculateEarly(HashSet<Task> initials) {
        for (Task initial : initials) {
            initial.earlyStart = 0;
            initial.earlyFinish = initial.cost;
            setEarly(initial);
        }
    }

    public static void setEarly(Task initial) {
        int completionTime = initial.earlyFinish;
        for (Task t : initial.dependencies) {
            if (completionTime >= t.earlyStart) {
                t.earlyStart = completionTime;
                t.earlyFinish = completionTime + t.cost;
            }
            setEarly(t);
        }
    }

    public static HashSet<Task> initials(Set<Task> tasks) {
        HashSet<Task> remaining = new HashSet<Task>(tasks);
        for (Task t : tasks) {
            for (Task td : t.dependencies) {
                remaining.remove(td);
            }
        }

        return remaining;
    }

    public static int maxCost(Set<Task> tasks) {
        int max = -1;
        for (Task t : tasks) {
            if (t.criticalCost > max)
                max = t.criticalCost;
        }
        maxCost = max;
   
        for (Task t : tasks) {
            t.setLatest();
        }
        return maxCost;
    }

    public static void print(Task[] tasks)
    {
    
    	
    	 Output.setText("Duration: " + maxCost);

    	Output.setText("Task"+"\tES"+"\tEF"+"\tLS"+"\tLF"+"\tSlack"+"\tCritical\n");
        for (Task t : tasks)
        {
        	String arr[]= t.toStringArray();
        	String temp="";
        	for(int i=0;i<arr.length;i++)
          	{	
        		if(arr[i]=="Start" || arr[i]=="End")
        		{
        			break;
        		}
        		temp+=arr[i]+"\t";
        		
        	}
        
        	
        	Output.setText(temp);
        }        	
        
    }

    public static String printCritical(Task[] tasks)
    {
     	String temp="";
    	String end="";
    	String c;
    	ArrayList<String> tempc=new ArrayList<String>(); 
    	
    	
    	for(Task t:tasks)
    	{
    		c=t.testCritical();
    		if(c!=null)
    		{
    			tempc.add(c);
    		}
    	}
    	int count=tempc.size();
    	String[] critical=new String[count];
    	
    	for(int i=0;i<count;i++)
    	{
    		critical[i]=tempc.get(i);
    	}

    	
    	for(int i=0;i<count;i++)
    	{
    		if(critical[i].equals("Start") || critical[i].equals("End"))
    			{temp+="";}
    		else
    		{
    			temp+=critical[i]+"-";
    		}
    	}
    	return temp;
    	
     }
}



