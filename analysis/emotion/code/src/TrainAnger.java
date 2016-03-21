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
public class TrainAnger{
	String filename="";
	private static String deleteCharAt(String strValue, int index) {
		return strValue.substring(0, index) + strValue.substring(index + 1);

	}

	public TrainAnger(String f) {
		// TODO Auto-generated constructor stub
		filename=f;
	}
	
	public Classifier<String,String> ExecuteTraining(Classifier<String,String> bayes){
		ArrayList<String> WordsAnger = new ArrayList<String>();
		ArrayList<String> TweetList = new ArrayList<String>();
		ArrayList<String> Word = new ArrayList<String>();
		ArrayList<Float> Weight = new ArrayList<Float>();
		WordsAnger.add("i hate");
		WordsAnger.add("fu(ck|k) every(thing|one)*");
		WordsAnger.add("fu(ck|k) (you|u)");
		WordsAnger.add("fu(c|k)*(n|in|ing)*(')* he(l)+");
		WordsAnger.add("fuck|fuk o(f)+");
		WordsAnger.add("mother fu(ck|k)e*r*");
		BufferedReader rd = null;
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
		    Pattern p2 = Pattern.compile("[^\\s]+");
		    Matcher m2 = p2.matcher(tweet);
		    for(int i =0; i<WordsAnger.size();i++){
		    Pattern p = Pattern.compile(WordsAnger.get(i));
		    Matcher m = p.matcher(tweet);
		    
		    while(m.find()){
		    //look if matches the Happy Statements
		    entrou2++;
		    }		    
		    if(entrou2 ==1){
		    	entrou2++;
			    while(m2.find()){
			    	count++;
			    }
			    }
		    
		    }
		    
		    Pattern p3 = Pattern.compile("[^\\s]+");
		    Matcher m3 = p2.matcher(tweet);
		    
		    if(count!=0){
		    	String[] text = tweet.split("\\s");
		    	bayes.learn("anger",Arrays.asList(text));
		    	int exist=0;
		    	for(int i=0;i<TweetList.size();i++){
		    		if(TweetList.get(i).equals(tweet))exist=1;
		    	}
		    	if(exist==0){
		    		TweetList.add(tweet);
		    		while(m3.find()){
			    		//System.out.println(m3.group());
		    			int exist2=0;
		    			for(int i=0;i<Word.size();i++)
		    				if(Word.get(i).equals(m3.group())){
		    					exist2=1;
		    					Weight.set(i,Weight.get(i)+(float)1/count);
		    				}
		    			if(exist2==0){
		    				Word.add(m3.group());
		    				//System.out.println((float)1/count);
		    				Weight.add((float)1/count);
		    			}
			    	}
		    	}
		    }	    
			//DataObject obj = gson.fromJson(inputLine, this.class);
			//System.out.println(inputLine);
			//System.out.println(obj);
			}
			FileWriter arq = new FileWriter("/home/lucas/Documents/WordsAnger.txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			for(int i=0;i<Weight.size();i++){
		    	//System.out.println(Word.size());
				gravarArq.printf("%s %f\n",Word.get(i),Weight.get(i));
				
		    }
			arq.close();
			return bayes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
}
