import java.util.*;
import java.net.*;
import java.io.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
class VLCControl
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
					System.out.println(str);
					switch(str)
					{ 
						case "shutdown":
						run.exec("c:\\Windows\\System32\\shutdown -s -t 0");
						break;
						case "restart":
						run.exec("c:\\Windows\\System32\\shutdown -r -t 0");
						break;
						case "next":
						robot.keyPress(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_N);
						break;
						case "previous":
						robot.keyPress(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_P);		        	
						break;	
						case "play":
						robot.keyPress(KeyEvent.VK_SPACE);
						robot.keyRelease(KeyEvent.VK_SPACE);
						break;
						case "pause":
						robot.keyPress(KeyEvent.VK_SPACE);
						robot.keyRelease(KeyEvent.VK_SPACE);
						break;
						case "up":
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_UP);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_UP);
						break;
						case "down":
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_DOWN);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_DOWN);
						break;
						case "disconnect":
						connecting.close();
						s.close();
						default:
						break;
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
}