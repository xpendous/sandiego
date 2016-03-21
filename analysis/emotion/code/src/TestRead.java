import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Session;
import com.fourspaces.couchdb.ViewResults;


public class TestRead {
	public void TweetEmotionCoordinates(String tweetemotion, String coordin, String tweetemotioncoordinates) throws IOException{
		/*Retieving all document as result to a List*/
		String inputLine=null;
		String inputLine2=null;
		int count=0;
		String tweet=null;
		String emotion=null;
		String number=null;
		String coordinates=null;
		/*while((inputLine = rd.readLine()) != null && (inputLine2 = rd2.readLine()) != null){
			System.out.println(inputLine);
			System.out.println(inputLine2);
			if(count%3==1)tweet=inputLine;
			if(count%3==2)emotion=inputLine;
		}*/
		Scanner sc = new Scanner(new File(coordin));
		Scanner sc2 = new Scanner(new File(tweetemotion));
		FileWriter arq = new FileWriter(tweetemotioncoordinates);
		PrintWriter gravarArq = new PrintWriter(arq);
		while(sc2.hasNext()){
			if(count%3==0)number=sc2.nextLine();
			if(count%3==1)tweet=sc2.nextLine();
			if(count%3==2){
				emotion=sc2.nextLine();
				gravarArq.printf("%s\n",tweet);
				gravarArq.printf("%s\n",emotion);
				gravarArq.printf("%s\n",sc.nextLine());
			}
			count++;
		}
		//String linha = sc.nextLine();
		
		
	}
}
