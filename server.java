import java.io.*;
import java.net.*;
import java.util.*;

class server {
	public static void main(String[] args) {
		

		try {
			ggmaple gg=new ggmaple();
			gg.start();
				
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
class ggmaple extends Thread{
	ServerSocket ss;
	String data;
	BufferedReader br=null;
	Socket cs=null;
	String a;
	Date day = new Date();


	public void run(){
		try{
			ss = new ServerSocket(8888);
			
		cs = ss.accept();
			while(true) {

				 br= new BufferedReader(new InputStreamReader(cs.getInputStream()));

						System.out.println(cs.getLocalAddress());
						while((a=br.readLine())!=null){
							System.out.println(a);
							System.out.println(day.toString());
						}
			}
				
		}catch(Exception ex){
			System.out.println(ex.toString());
		}finally{
			try{
				br.close();
			}catch(Exception ex){
				System.out.println(ex.toString());
			}


		}
	}




}