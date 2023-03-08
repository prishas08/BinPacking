import java.util.ArrayList;

public class Disk implements Comparable<Disk>
{
	public int specialID;
	public int size;
	public ArrayList<Integer> disk = new ArrayList<>();
	public Disk(int id, ArrayList<Integer> p)
	{
		this.specialID = id;
		this.disk = p;
	}
	public void addDisk(Integer i)
	{
		this.size+=i;
		disk.add(i);
	}
	public int getSumSize()
	{
		return size;
	}
	
	@Override
	public String toString()
	{
		String s = specialID + " ";
		for(int i = 0; i < disk.size(); i++)
		{
			s += disk.get(i) + " ";
		}
		return s;
	}
	@Override
	public int compareTo(Disk o) {
		//if the queue is null or if the none of the current disk sizes plus this
		//disk's size exceeds 1,000,000 GB, create a new space with just the new disk
		//else insert the disk into the space with the most remaining space.

		//which disk has more remaining space
		if((1000000-this.size)>(1000000-o.size))
		{
			return -1;
		}
		else if((1000000-this.size)<(1000000-o.size))
		{
			return 1;
		}
		return 0;
	}
	
}
