import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.sf.json.groovy.JsonGroovyBuilder;
import net.sf.json.groovy.JsonSlurper;

import org.json.simple.JSONObject;

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
import com.fourspaces.couchdb.ViewResults;

public class Reading {
 
 
 public void putIntoDB (Classifier<String, String> bayes, String database1, String database2,String host, int port, String user, String pass) throws FileNotFoundException{
  
  /*Creating a session with couch db running in 5984 port*/
  Session studentDbSession = null;
  
  if(user.equals(""))
  studentDbSession = new Session(host,port);
  else studentDbSession = new Session(host,port,user,pass);
  /*Selecting the 'student' database from list of couch database*/
  Database reading = studentDbSession.getDatabase(database1);
  Database inserting = studentDbSession.getDatabase(database2);
  
  Map<String , String> properties = new HashMap<String,String>();

  ViewResults couchViewResults = reading.getAllDocuments();
  List<Document> studentDocuments = couchViewResults.getResults();
  
  int happy=0;
  int trust=0;
  int distrust=0;
  int sad=0;
  int fear=0;
  int surprise=0;
  int anger=0;

  for(Document couchDocument: studentDocuments){
	   //JsonGroovyBuilder builder = new net.sf.json.groovy.JsonGroovyBuilder();
	   String id = couchDocument.getJSONObject().getString("id"); 
	   Document studentRow = reading.getDocument(id);
	    
	   
	   if(studentRow.containsKey("text")){
	    
		properties.put("tweet", (String) studentRow.get("text"));
		String tweet= (String) studentRow.get("text");
		String[] emotion = tweet.split("\\s");
		properties.put("emotion", bayes.classify(Arrays.asList(emotion)).getCategory());
		
		if(bayes.classify(Arrays.asList(emotion)).getCategory().equals("happy"))happy++;
		if(bayes.classify(Arrays.asList(emotion)).getCategory().equals("sad"))sad++;
		if(bayes.classify(Arrays.asList(emotion)).getCategory().equals("fear"))fear++;
		if(bayes.classify(Arrays.asList(emotion)).getCategory().equals("surprise"))surprise++;
		if(bayes.classify(Arrays.asList(emotion)).getCategory().equals("anger"))anger++;
		if(bayes.classify(Arrays.asList(emotion)).getCategory().equals("trust"))trust++;
		if(bayes.classify(Arrays.asList(emotion)).getCategory().equals("distrust"))distrust++;
		
	   }
		
	   if(studentRow.containsKey("coordinates")){
		properties.put("coordinates", (String) studentRow.get("coordinates").toString());

	    
	   }
	   

	    Document newdoc = new Document();
	 	newdoc.putAll(properties);
	 	inserting.saveDocument(newdoc);
	    
	  }
  
  System.out.println("happy "+happy);
  System.out.println("fear "+fear);
  System.out.println("sad "+sad);
  System.out.println("surprise "+surprise);
  System.out.println("trust "+trust);
  System.out.println("distrust "+distrust);
 }
}