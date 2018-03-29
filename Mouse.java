import java.util.*;
import java.net.*;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
class Mouse
{
		public static void main(String args[]) throws Exception
		{
			
			String inputLine;
			BufferedReader bin;
			ServerSocket s;
			Robot robot;
			Socket connecting;
			Runtime run=Runtime.getRuntime();	
			try
			{
				s=new ServerSocket(9099);
				robot=new Robot();
				System.out.println("Server Listening...");
				connecting=s.accept();	
				if(connecting.isConnected())
				{
					System.out.println("Client Connected");
				}
				bin = new BufferedReader(new InputStreamReader(connecting.getInputStream()));	
				while ((inputLine = bin.readLine()) != null) 
				{
					String str=inputLine.toLowerCase();
					//System.out.println(str);
					if(str.contains(","))
					{	
					float movex=Float.parseFloat(str.split(",")[0]);//extract movement in x direction
					float movey=Float.parseFloat(str.split(",")[1]);
					Point point = MouseInfo.getPointerInfo().getLocation();
					float nowx=point.x;
					float nowy=point.y;
					robot.mouseMove((int)(nowx+movex),(int)(nowy+movey));
					}
					else if(str.equals("leftclick"))
					{
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					}
					else if(str.equals("rightclick"))
					{
						robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
						robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
					}
					else{
						System.out.println("Exit");
					}
					
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
}