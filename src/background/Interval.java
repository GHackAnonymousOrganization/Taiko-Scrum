package background;
public class Interval
{
	private Game game;
	public enum Position
	{
		left,right;
	}
	private long limitL;
	private long limitR;
	
	//INITIALIZATION
	public Interval(Game game,String init) 
	{
		this.game=game;
		Position position;
		long center;
		if(init.substring(0,1).equals("\t"))
		{
			position=Position.right;
			init=init.substring(1);
		}
		else
			position=Position.left;
		center=stringConversion(init);
		limitL=center-100;
		limitR=center+100;
		if(position==Position.left)
		{
			this.game.addIntervalL(this);
			this.game.addSpawnL(limitL-950);
		}
		else
		{
			this.game.addIntervalR(this);
			this.game.addSpawnR(limitL-950);
		}
	}
	
	//FUNCTIONS
	public static long stringConversion(String init) 
	{
		long time=0;
		String values[]=init.split(":");
		time=Long.parseLong(values[0])*60*1000+Long.parseLong(values[1])*1000+Long.parseLong(values[2]);
		return time;
	}
	public static String timeConversion(long time)
	{
		StringBuffer bs=new StringBuffer();
		String s=Long.toString(time/(60*1000));
		s=s.length() < 2 ? "0" + s : s;
		bs.append(s+":");
		time=time%(60*1000);
		s=Long.toString(time/1000);
		s=s.length() < 2 ? "0" + s : s;
		bs.append(s+":");
		time=time%(1000);
		s=Long.toString(time);
		s=s.length() < 3 ? "0" + s : s;
		s=s.length() < 2 ? "0" + s : s;
		bs.append(s);
		return bs.toString();
	}
	public int contains(long time)
	{
		if(time<limitL)
			return -1;
		if(time>limitR)
			return +1;
		return 0;
	}
	//GETTERS AND SETTERS
	public long getLimitL() 
	{
		return limitL;
	}
	public long getLimitR() 
	{
		return limitR;
	}
}
