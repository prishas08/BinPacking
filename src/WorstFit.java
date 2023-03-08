import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WorstFit {
	
	static Queue<Disk> pq = new PriorityQueue<>();
	static ArrayList<Integer> pqArrayList = new ArrayList<>();
	static int totalSize;
	
	public static void main(String[] args)
	{
		Scanner input=null;
		try {
			input = new Scanner(new File("input20.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(input.hasNext())
		{
			int in = input.nextInt();
			pqArrayList.add(in);
			totalSize+=in;
		}
		for(int i = 0; i<pqArrayList.size(); i++)
		{
			if(pq==null)
			{
				//create new disk
				ArrayList<Integer> newPQ = new ArrayList<>();
				newPQ.add(pqArrayList.get(i));
				pq.offer(new Disk (i, newPQ));	
			}			
			else
			{
				ArrayList<Disk> temp = new ArrayList<>();
				Queue<Disk> tempPQ = new PriorityQueue<>();
				while(pq!=null)
				{
					tempPQ.offer(pq.poll());
				}
				while(tempPQ!=null)
				{
					temp.add(tempPQ.poll());
				}
				int id = 0;
				int remainingSpace = 0;
				//traverse and compare remaining sizes and find optimal one
				for(int x = 0; x <= temp.size(); x++)
				{
					for(int y = 0; y <= temp.size(); y++)
					{
						int compare = temp.get(x).compareTo(temp.get(y));
						if(compare>0)
						{
							if(1000000 - (temp.get(x).getSumSize())> remainingSpace)
							{
								id = temp.get(x).specialID;

							}
						}
						else if (compare<0)
						{
							if(1000000 - (temp.get(y).getSumSize())> remainingSpace)
							{
								id = temp.get(y).specialID;

							}
						}
						else
						{
							if(1000000 - (temp.get(x).getSumSize())> remainingSpace)
							{
								id = temp.get(x).specialID;

							}
						}
					}
				}
				while(tempPQ!=null)
				{
					pq.add(tempPQ.poll());
				}
				//if none of the disks have enough space
				if(remainingSpace<0)
				{
					ArrayList<Integer> newPQ = new ArrayList<>();
					newPQ.add(pqArrayList.get(i));
					pq.offer(new Disk (i, newPQ));	
				}
				else
				{
					//use most remaining space ID to add disk to worst fit ID
					for(int n = 0; n < temp.size(); n++)
					{
						if(temp.get(n).specialID==id)
						{
							temp.get(n).addDisk(pqArrayList.get(i));
						}
					}
					
				}
				
			}
			System.out.println("Total Size = " + totalSize/1000 + " GB");
			System.out.println("Disks Required = " + pq.size());
			while(!pq.isEmpty())
			{
				System.out.println(pq.poll() + " ");
			}
		}
		
		
	}
	
	
	
	

}

