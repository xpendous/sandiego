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
public class TrainAll{
	
	ArrayList<String> THappy = new ArrayList<String>();
	ArrayList<String> TAnger = new ArrayList<String>();
	ArrayList<String> TCourage = new ArrayList<String>();
	ArrayList<String> TDistrust = new ArrayList<String>();
	ArrayList<String> TFear = new ArrayList<String>();
	ArrayList<String> TSad = new ArrayList<String>();
	ArrayList<String> TSurprise = new ArrayList<String>();
	ArrayList<String> TTrust = new ArrayList<String>();
	
	ArrayList<String> WordsHappy = new ArrayList<String>();
	ArrayList<String> WordsAnger = new ArrayList<String>();
	ArrayList<String> WordsCourage = new ArrayList<String>();
	ArrayList<String> WordsDistrust = new ArrayList<String>();
	ArrayList<String> WordsFear = new ArrayList<String>();
	ArrayList<String> WordsSad = new ArrayList<String>();
	ArrayList<String> WordsSurprise = new ArrayList<String>();
	ArrayList<String> WordsTrust = new ArrayList<String>();
	ArrayList<String> TweetList = new ArrayList<String>();
	ArrayList<Integer> times = new ArrayList<Integer>();
	String filename="";
	int nanger=0; 
    int ncourage=0;
    int ndistrust=0;
    int nfear=0;
    int nhappy=0;
    int nsad=0;
    int nsurprise=0;
    int ntrust=0;
	
	public static Classifier<String, String> bayes = new BayesClassifier<String, String>();
	private static String deleteCharAt(String strValue, int index) {
		return strValue.substring(0, index) + strValue.substring(index + 1);

	}
	
	public TrainAll(String f) {
		// TODO Auto-generated constructor stub
		filename=f;
	}
	
/////////////////////// HAPPY ///////////////////////////////////	
	
	public void tHappy(String t){
		String findT = "";
	    for(int i =0; i<WordsHappy.size();i++){
		    Pattern p = Pattern.compile(WordsHappy.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	String[] text = findT.split("\\s");
	    	THappy.add(findT);
	    	nhappy++;
	    }
	}
	
	public void ttHappy(int men){
		men=THappy.size();
		for (int i=0;i<men;i++){
			String[] text = THappy.get(i).split("\\s");
			bayes.learn("happy",Arrays.asList(text));
		}
	}
	
	
	public void addHappy(){
		//WordsHappy.add("happy b'day|bday|birthday");
		WordsHappy.add("i(')*(m)* happy");
		WordsHappy.add("=d");
		WordsHappy.add(":d");
		WordsHappy.add(";d");
		WordsHappy.add("this|that is nice|cool|amazing");
	}
	
/////////////////////////// ANGER ////////////////////////////////////	
	
	
	public void tAnger(String t){
		String findT = "";
	    for(int i =0; i<WordsAnger.size();i++){
		    Pattern p = Pattern.compile(WordsAnger.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	TAnger.add(findT);
	    	nanger++;
	    }
	}
	
	public void ttAnger(int men){
		men=TAnger.size();
		for (int i=0;i<men;i++){
			String[] text = TAnger.get(i).split("\\s");
			bayes.learn("anger",Arrays.asList(text));
		}
	}
	
	public void addAnger(){
		WordsAnger.add("i hate this");
		WordsAnger.add("what the fuck|fuck!");
		WordsAnger.add("wtf");
		WordsAnger.add("i'm|im angry");
		WordsAnger.add("this/that is shit|stupid|irresponsible|idiot");
		WordsAnger.add("fu(ck|k) every(thing|one)*");
		WordsAnger.add("fu(ck|k) (you|u)");
		WordsAnger.add("fu(c|k)*(n|in|ing)*(')* he(l)+");
		WordsAnger.add("fuck|fuk o(f)+");
		WordsAnger.add("mother fu(ck|k)e*r*");
	}
	
///////////////////////// COURAGE //////////////////////////////////	
	
	public void tCourage(String t){
		String findT = "";
	    for(int i =0; i<WordsCourage.size();i++){
		    Pattern p = Pattern.compile(WordsCourage.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	TCourage.add(findT);
	    	ncourage++;
	    }
	}
	
	public void ttCourage(int men){
		men=TCourage.size();
		for (int i=0;i<men;i++){
			String[] text = TCourage.get(i).split("\\s");
			bayes.learn("courage",Arrays.asList(text));
		}
	}
	
	
	public void addCourage(){
		WordsCourage.add("i can(!)*$");
		WordsCourage.add("no[A-Za-z]+( will)* stop(s)* m(e)+");
		WordsCourage.add("i have n(o)+ fear");
		WordsCourage.add("is heartened");
		WordsCourage.add("no(t)* afraid");
	}

	
////////////////////////// DISTRUST //////////////////////////////////	
	
	public void tDistrust(String t){
		String findT = "";
	    for(int i =0; i<WordsDistrust.size();i++){
		    Pattern p = Pattern.compile(WordsDistrust.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	TDistrust.add(findT);
	    	ndistrust++;
	    }
	}
	
	public void ttDistrust(int men){
		men=TDistrust.size();
		for (int i=0;i<men;i++){
			String[] text = TDistrust.get(i).split("\\s");
			bayes.learn("distrust",Arrays.asList(text));
		}
	}
	
	
	public void addDistrust(){
		WordsDistrust.add("(i do(n'*t| not)|don'*t|never) (believe|trust)");
		WordsDistrust.add("no way to (believe|trust)");
		WordsDistrust.add("i('ve| have) no confidence|faith");
		WordsDistrust.add("be distrustful of+");
		WordsDistrust.add("nver|nvr|never trust");
		WordsDistrust.add("this|that is (hard|not easy|dangerous|impossible)");
	}

/////////////////////////////////// FEAR ///////////////////////////////////
	
	public void tFear(String t){
		String findT = "";
	    for(int i =0; i<WordsFear.size();i++){
		    Pattern p = Pattern.compile(WordsFear.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	TFear.add(findT);
	    	nfear++;
	    }
	}
	
	public void ttFear(int men){
		men=TFear.size();
		for (int i=0;i<men;i++){
			String[] text = TFear.get(i).split("\\s");
			bayes.learn("fear",Arrays.asList(text));
		}
	}
	
	public void addFear(){
		WordsFear.add("i've|ive fear|panic");
		WordsFear.add("i have fear");
		WordsFear.add("i(')*(m)* afraid|scared|frightened|dreading*");
		WordsFear.add("i'*m* in terror");
		WordsFear.add("this|that is (hard|not easy|dangerous|impossible|not possible)");
	}
	
/////////////////////////// SAD ////////////////////////////
	
	public void tSad(String t){
		String findT = "";
	    for(int i =0; i<WordsSad.size();i++){
		    Pattern p = Pattern.compile(WordsSad.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	TSad.add(findT);
	    	nsad++;
	    }
	}
	
	public void ttSad(int men){
		men=TSad.size();
		for (int i=0;i<men;i++){
			String[] text = TSad.get(i).split("\\s");
			bayes.learn("sad",Arrays.asList(text));
		}
	}
	
	public void addSad(){
		WordsSad.add("rest in peace");
		WordsSad.add("i'm sad|im sad");
		WordsSad.add(":-\\(");
		WordsSad.add(":\\\\");
		WordsSad.add("this|that is sad|bad");
	}

	
//////////////////// SUPRISE /////////////////////////////
	
	public void tSurprise(String t){
		String findT = "";
	    for(int i =0; i<WordsSurprise.size();i++){
		    Pattern p = Pattern.compile(WordsSurprise.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	TSurprise.add(findT);
	    	nsurprise++;
	    }
	}
	
	public void ttSurprise(int men){
		men=TSurprise.size();
		for (int i=0;i<men;i++){
			String[] text = TSurprise.get(i).split("\\s");
			bayes.learn("surprise",Arrays.asList(text));
		}
	}
	
	public void addSurprise(){
		//WordsSurprise.add("im|i'm surprised");
		WordsSurprise.add("omg");
		WordsSurprise.add("oh my go(d|sh)");
		WordsSurprise.add("i (can'*t|cannot) believe");
		WordsSurprise.add("=o");
		WordsSurprise.add(":o");
		WordsSurprise.add(":-o");
		WordsSurprise.add("this|that is unbelievable|unbelivable|surreal");
		
	}

	
	
/////////////////////////// TRUST /////////////////////////////
	
	public void tTrust(String t){
		String findT = "";
	    for(int i =0; i<WordsTrust.size();i++){
		    Pattern p = Pattern.compile(WordsTrust.get(i));
		    Matcher m = p.matcher(t);
		    while(m.find()){
		    	findT=t;
		    				}		    
		    								}
	    if(!findT.equals("")){
	    	TTrust.add(findT);
	    	ntrust++;
	    }
	}
	
	public void ttTrust(int men){
		men=TTrust.size();
		for (int i=0;i<men;i++){
			String[] text = TTrust.get(i).split("\\s");
			bayes.learn("trust",Arrays.asList(text));
		}
	}
	
	public void addTrust(){
		
		WordsTrust.add("i know you|u can");
		WordsTrust.add("i admire");
		WordsTrust.add("i believe (in|on)");
		WordsTrust.add("i(')*(m)* sure");
		WordsTrust.add("i think (he(')*(s)*|she(')*(s)*|we(')*(re)*|they(')*(re)*)( is| are)* right");
		WordsTrust.add("this|that is possible(^?)");
	}
	
	public Classifier<String,String> ExecuteTraining(){
		bayes.setMemoryCapacity(1000000000);
		addAnger();
		addCourage();
		addDistrust();
		addFear();
		addSad();
		addSurprise();
		addTrust();
		addHappy();
		BufferedReader rd = null;
		int count2=0;
		int ct=0;
		try {
			rd = new BufferedReader(new FileReader(new File(filename)));
			String req="\"\"text\"\":\"\"(.*?)\"\",\"\""; 
			String inputLine = null;
			int countg=0;
			while((inputLine = rd.readLine()) != null){
			countg++;
			if(countg%100000==0)System.out.println("100k");
			inputLine = deleteCharAt(inputLine,0);
			inputLine = deleteCharAt(inputLine, inputLine.length()-1);
			Pattern pattern = Pattern.compile(req);
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
		    
		   if(!tweet.equals(""))
		   {
			   int exist=0;
			   for(int j=0;j<TweetList.size();j++){
				   if(tweet.equals(TweetList.get(j))){
					   exist=1;
					   ct++;
					   //System.out.println(ct);
				   }
			   }
			   if(exist==0){
			   
			   TweetList.add(tweet);
			   tAnger(tweet);
			   tCourage(tweet);
			   tDistrust(tweet);
			   tFear(tweet);
			   tHappy(tweet);
			   tSad(tweet);
			   tSurprise(tweet);
			   tTrust(tweet);
			   }
		   }
		    
													}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		times.add(nhappy);
		times.add(nanger);
		times.add(ndistrust);
		times.add(nfear);
		times.add(nsad);
		times.add(nsurprise);
		times.add(ntrust);
		//times.add(ncourage);
		int menor=99999999;
		for(int i=0;i<times.size();i++){
			if(times.get(i)<menor )menor=times.get(i);
		}
		System.out.println("menor "+menor);
		ttAnger(menor);
		//ttCourage(menor);
		ttDistrust(menor);
	    ttFear(menor);
		ttHappy(menor);
		ttSad(menor);
		ttSurprise(menor);
		ttTrust(menor);
		
		
		System.out.println("happy "+nhappy+"\n");
		System.out.println("anger "+nanger+"\n");
		System.out.println("courage "+ncourage+"\n");
		System.out.println("distrust "+ndistrust+"\n");
		System.out.println("fear "+nfear+"\n");
		System.out.println("sad "+nsad+"\n");
		System.out.println("surprise "+nsurprise+"\n");
		System.out.println("trust "+ntrust+"\n");
		return bayes;
	
	}
}

