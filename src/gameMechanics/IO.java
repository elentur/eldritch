package gameMechanics;

import java.io.BufferedReader;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
	 
	
	 public class IO { 
	 	public static Socket socket=null; 
	 	
	 	public static Map<String,String> readText(String source) { 
	 		 		Map<String,String> target = new HashMap<String,String>(); 
	 		 		Path path = Paths.get(source);
	 		 			BufferedReader br;
						try {
							br = Files.newBufferedReader(path,StandardCharsets.UTF_8);
						
	 		 			
	 		 			String line; 

	 		 			while ((line=br.readLine()) != null) {
	 		 				String[] keyValue =line.split(":",2);
	 		 				keyValue[1]=keyValue[1].replaceAll("#", System.getProperty("line.separator"));
	 		 				target.put(keyValue[0],keyValue[1]);
	 		 			} 
	 		 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
	 				return target; 
	 		 	} 

	 	 
//	 	public static PlayingField readFile() throws FileNotFoundException, IOException, ClassNotFoundException { 
//	 		if(socket==null){ 
//	 			ObjectInputStream in = new ObjectInputStream(new FileInputStream("Game1.obj")); 
//	 			PlayingField readedPlayingField = (PlayingField)in.readObject(); 
//	 			in.close(); 
//	 			return readedPlayingField; 
//	 		}else{ 
//	 			ObjectInputStream  in = new ObjectInputStream(socket.getInputStream()); 
//	 			PlayingField readedPlayingField = (PlayingField)in.readObject(); 
//	 			return readedPlayingField; 
//	 		} 
//	 		 
//	 	} 
	 	 
//	 	public static void writeFile(PlayingField playingField) { 
//	 		try { 
//	 			if(socket==null){ 
//	 				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Game1.obj")); 
//	 				out.writeObject(playingField); 
//	 				out.close(); 
//	 			}else{ 
//	 				OutputStream out =socket.getOutputStream(); 
//	 				ObjectOutputStream  writer = new ObjectOutputStream(out); 
//	 				writer.writeObject(playingField); 
//	 			} 
//	 		} catch (IOException e) { 
//	 			System.out.println("Fehler beim schreiben.");		} 
//	 		 
//	 	} 
	 	 
//	 	public static void writeTurnMap(PlayingField playingField) { 
//	 		try { 
//	 			FileWriter writer = new FileWriter("Game1.map", true); 
//	 			writer.write("###################\r\n"); 
//	 			writer.write(playingField.print()); 
//	 			writer.close(); 
//	 		} catch (IOException e) { 
//	 			System.out.println("Fehler beim schreiben.");		} 
//			 
//		} 

}
