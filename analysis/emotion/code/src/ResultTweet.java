import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class ResultTweet {
	private static String deleteCharAt(String strValue, int index) {
		return strValue.substring(0, index) + strValue.substring(index + 1);

	}

	
	public void ExecuteResult(Classifier<String,String> bayes, String destinationws, String tweetemotion) throws IOException{
		String filename = destinationws;
		BufferedReader rd = null;
		rd = new BufferedReader(new FileReader(new File(destinationws)));
		String inputLine = null;
		FileWriter arq = new FileWriter(tweetemotion);
		PrintWriter gravarArq = new PrintWriter(arq);
		//FileWriter arq2 = new FileWriter("/home/lucas/EmotionTweet.txt");
		PrintWriter gravarArq2 = new PrintWriter(arq);
		String tweet="";
		int count=0;
		while((inputLine = rd.readLine()) != null){
				inputLine = inputLine.toLowerCase();
				//System.out.println(inputLine);
				if(inputLine.charAt(0)=='|'){
					count++;
					String[] unknownText1 = tweet.split("\\s");
					gravarArq.printf("%d\n",count);
		    		gravarArq.printf("%s\n",tweet);
		    		gravarArq2.printf("%s\n",bayes.classify(Arrays.asList(unknownText1)).getCategory());
					//System.out.println(tweet);
		    		tweet="";
				}
				else tweet=tweet+inputLine;
												}
				
	}

}
