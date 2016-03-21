import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;

public class intoCouchDB {

 /*These are the keys of student document in couch db*/

 public static final String STUDENT_KEY_NAME ="name";
 
 public static final String STUDENT_KEY_MARKS ="marks";
 
 public static final String STUDENT_KEY_ROLL="roll";
 
 
 public void intoDB(String tweetemotioncoordinates) throws FileNotFoundException{
  
  /*Creating a session with couch db running in 5984 port*/
  Session studentDbSession = new Session("localhost",5984);
  
  /*Selecting the 'student' database from list of couch database*/
  Database studentCouchDb = studentDbSession.getDatabase("emotiontweets");
  
  /*Creating a new Document*/
  
  /*Map for list of properties for the new document*/
  Map<String , String> properties = new HashMap<String,String>();
  Scanner sc = new Scanner(new File(tweetemotioncoordinates));
  int count = 0;
  while(sc.hasNext()){
		if(count%3==0){
			properties.put("text", sc.nextLine());
		}
		if(count%3==1){
			properties.put("emotion", sc.nextLine());
		}
		if(count%3==2){
			properties.put("coordinates", sc.nextLine());
			Document newdoc = new Document();
		 	newdoc.putAll(properties);
		 	studentCouchDb.saveDocument(newdoc); 
		}
		count++;
	}
  
 // properties.put("text", "saan");
  
  
  //properties.put(STUDENT_KEY_MARKS, "67");
  
  //properties.put(STUDENT_KEY_ROLL, "12");
  
  
  /*Adding all the properties to the new document*/
 // newdoc.putAll(properties);
  
  /*Saving the new document in the 'student' database */
  //studentCouchDb.saveDocument(newdoc);  
  
 }
}