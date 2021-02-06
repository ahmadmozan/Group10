import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Floor extends Thread {
	
	static String[] data= new String[4];
	
	private boolean empty = true;
	public Object contents = null;
	public static Thread floor;
	
	public static void main(String[] args) {
		System.out.println("creating threads...");
		floor=new Thread();
		floor.start();
		getData();
		//getArray();
		String[] op = getArray();
	    for(String str : op)
	        System.out.println(str);
	}
	public static void getData() {
		BufferedReader reader;
		try {
			System.out.println("Getting data...");
			reader=new BufferedReader(new FileReader("C:\\Users\\akkas\\Desktop\\SYSC 3303\\InputFile.txt"));
			String line = reader.readLine();
			while(line !=null) {
				//System.out.println(line);
				for (int i=0;i<4;i++) {
				data[i]=line;
				line=reader.readLine();
				}
				//System.out.println(Arrays.toString(data));
				
				
			}
			reader.close();
		}catch (IOException e) {
			System.out.println("Error: File cannot be found");
			e.printStackTrace();
		}
	}
	
	
	
	public static String[] getArray() {
		String[] output=new String[4];
		output[0]=data[0];
		output[1]=data[1];
		output[2]=data[2];
		output[3]=data[3];
		return output;
		

	
		
	}
		
		
}
