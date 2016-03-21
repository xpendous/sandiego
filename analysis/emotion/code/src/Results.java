import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Results {
	private static String deleteCharAt(String strValue, int index) {
		return strValue.substring(0, index) + strValue.substring(index + 1);

	}
	
	public void ExecuteResults(Classifier<String,String> bayes) throws IOException{
		String filename = "/home/lucas/Desktop/Twitter.csv";
		BufferedReader rd = null;
		rd = new BufferedReader(new FileReader(new File(filename)));
		String req="\"\"text\"\":\"\"(.*?)\"\",\"\""; 
		//JSONObject obj = null;
		String inputLine = null;
		FileWriter arq = new FileWriter("/home/lucas/Documents/Results.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		while((inputLine = rd.readLine()) != null){
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
		        	String[] unknownText1 = tweet.split("\\s");
		    		gravarArq.printf("%s -> %s\n",tweet,bayes.classify(Arrays.asList(unknownText1)).getCategory());
		    		}
		        }
			
			 
		
		}
	}
}
