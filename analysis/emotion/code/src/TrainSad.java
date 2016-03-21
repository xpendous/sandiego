import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
public class TrainSad extends Thread{
	
	private static String deleteCharAt(String strValue, int index) {
		return strValue.substring(0, index) + strValue.substring(index + 1);

	}

	String filename="";
	public TrainSad(String f){
		filename=f;
	}
	public Classifier<String,String> ExecuteTraining(Classifier<String,String> bayes){
		ArrayList<String> WordsHappy = new ArrayList<String>();
		ArrayList<String> TweetList = new ArrayList<String>();
		ArrayList<String> Word = new ArrayList<String>();
		ArrayList<Float> Weight = new ArrayList<Float>();
		WordsHappy.add("rest in peace");
		WordsHappy.add("i'm sad|im sad");
		WordsHappy.add(":-\\(");
		WordsHappy.add(":\\(");
		WordsHappy.add("://");
		BufferedReader rd = null;
		int count2=0;
		try {
			rd = new BufferedReader(new FileReader(new File(filename)));
			//CSVReader reader = new CSVReader(new FileReader(filename));
			String req="\"\"text\"\":\"\"(.*?)\"\",\"\""; 
			//JSONObject obj = null;
			String inputLine = null;
			Gson gson = new Gson();		
			while((inputLine = rd.readLine()) != null){
			//obj = (JSONObject)parser.parse(inputLine);
			inputLine = deleteCharAt(inputLine,0);
			inputLine = deleteCharAt(inputLine, inputLine.length()-1);
			String patternString = req;
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(inputLine);
			int entrou = 0;
			String tweet="";
			
		    while(matcher.find()){
		    		entrou++;
		    		if(entrou==1){
		        	tweet = matcher.group(0).substring(11);
		        	tweet = tweet.substring(0,tweet.length()-5);
		        	tweet = tweet.toLowerCase();
		    		}
		        }
		    int entrou2=0;
		    int count=0;
		    String findT = "";
		    for(int i =0; i<WordsHappy.size();i++){
		    Pattern p = Pattern.compile(WordsHappy.get(i));
		    Matcher m = p.matcher(tweet);
		    
		    while(m.find()){
		    	findT=tweet;
		    	String[] text = tweet.split("\\s");
		    }		    
			    }
		    if(!findT.equals("")){
		    	String[] text = findT.split("\\s");
		    	bayes.learn("sad",Arrays.asList(text));
		    }
			}
			return bayes;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bayes;
	
	}
}
