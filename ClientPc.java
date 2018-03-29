import java.io.*;
import java.util.*;
import java.net.*;
class ClientPc
{
		public static void main(String args[])
		{
			
			String userInput;
			try
			{
				Socket s=new Socket("192.168.1.107",9099);
				System.out.println("Client is Connected");
				PrintWriter out=new PrintWriter(s.getOutputStream(),true);
				//BufferedReader in =new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				
				BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));
				while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
			//System.out.println("echo: " + in.readLine());
				}
			}
			
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
}