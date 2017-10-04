package background;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import background.Interval.Position;
import taiko.*;

public class Game 
{
	private FXMLDocumentController controller;

	private String song;
	private long startTime;
	private List<Interval> intervalsL=new ArrayList<>();
	private List<Interval> intervalsR=new ArrayList<>();
	private List<Long> spawnsL=new ArrayList<>();
	private List<Long> spawnsR=new ArrayList<>();
	private List<Guide> guides=new ArrayList<>();
	private Map<Long,Guide> guidesMap=new HashMap<>();
	private int fails;
	private boolean end;

	//INITIALIZATION
	public Game(FXMLDocumentController c,String song) 
	{
		controller=c;
		this.song=song;
		try(BufferedReader bf=new BufferedReader(new FileReader(song+".txt"))) 
		{
			String s;
			while((s=bf.readLine())!=null)
				new Interval(this,s);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	//GAME
	public void start()
	{
		end=false;
		fails=0;
		boolean endL=false,endR=false;
		try 
		{
			Thread.sleep(3000);
			File soundfile=new File(song+".wav");
			Clip clip = AudioSystem.getClip();
    		AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundfile);
		    clip.open(inputStream);
		    clip.start();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		setStartTime(System.currentTimeMillis());
		int currentL=0,currentR=0;
		try 
		{
			while(!end)
			{
				long time=System.currentTimeMillis()-getStartTime();
				int i;
				if((!endL)&&time>spawnsL.get(currentL))
				{
					Guide g=new Guide(this,controller,Position.left);
					guides.add(g);
					guidesMap.put(spawnsL.get(currentL),g);
					currentL++;
					if(currentL==spawnsL.size())
						endL=true;
				}
				if((!endR)&&time>spawnsR.get(currentR))
				{
					Guide g=new Guide(this,controller,Position.right);
					guides.add(g);
					guidesMap.put(spawnsR.get(currentR),g);
					currentR++;
					if(currentR==spawnsR.size())
						endR=true;
				}
				for(i=guides.size()-1;i>=0;i--)
				{
					if(!(guides.get(i).isVisible()))
						break;
					guides.get(i).move();
				}
				end=checkFails();
				if(endL&&endR)
					end=true;
				Thread.sleep(27);
			}
			System.exit(0);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	//GAME FUNCTIONS
	public void incrementFails() 
	{
		fails++;
		System.out.println(fails);
	}
	private boolean checkFails()
	{
		if(fails>=5)
			return true;
		return false;
	}
	public void binarySearchLeft(long time) 
	{
		int l=0;
		int r=intervalsL.size()-1;
		binarySearchR(intervalsL,time,l,r);
	}
	public void binarySearchRight(long time) 
	{
		int l=0;
		int r=intervalsR.size()-1;
		binarySearchR(intervalsR,time,l,r);
	}
	private void binarySearchR(List<Interval> intervals,long time,int l,int r) 
	{
		if(l>r)
			return;
		int m=(l+r)/2;
		int flag=intervals.get(m).contains(time);
		if(flag==0)
		{
			Guide g=guidesMap.get(intervals.get(m).getLimitL()-950);
			g.setChecked(true);
		}
		else if(flag>0)
			binarySearchR(intervals,time,m+1,r);
		else
			binarySearchR(intervals,time,l,m-1);	
		return;
	}
	public void addIntervalL(Interval interval) 
	{
		intervalsL.add(interval);
	}
	public void addIntervalR(Interval interval) 
	{
		intervalsR.add(interval);
	}
	public void addSpawnL(Long time)
	{
		spawnsL.add(time);
	}
	public void addSpawnR(Long time)
	{
		spawnsR.add(time);
	}
	
	//GETTERS AND SETTERS
	public long getStartTime() 
	{
		return startTime;
	}
	public void setStartTime(long startTime) 
	{
		this.startTime = startTime;
	}
	public FXMLDocumentController getController()
	{
		return controller;
	}
}
