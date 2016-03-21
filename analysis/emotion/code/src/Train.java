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

import com.google.gson.Gson;

public class Train {
	
	public static Classifier<String, String> bayes = new BayesClassifier<String, String>();
	public static void main(String args[]) throws IOException{
		String traindata = "/home/lucas/Desktop/100000T.csv";
		traindata = args[0];
		String database1 = args[1];
		String database2 = args[2];
		String host = args[3];
		int port = Integer.parseInt(args[4]);
		String user = "";
		String pass = "";
		if(args.length>5){
		user = args[5];
		pass = args[6];
		}
		Reading rd = new Reading();
		TrainAll tall = new TrainAll(traindata);
		
		//res.RemoveSpaces(destination,destinationws);
		//results.ExecuteResult(tall.ExecuteTraining(),destinationws,tweetemotion);
		//tr.TweetEmotionCoordinates(tweetemotion, coordinates, tweetemotioncoordinates);
		//icd.intoDB(tweetemotioncoordinates);
		
		rd.putIntoDB(tall.ExecuteTraining(),database1,database2,host,port,user,pass);
}
}
