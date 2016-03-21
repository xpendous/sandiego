import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class RemovingEmptySpaces {
	public void RemoveSpaces(String destination, String destinationws) throws IOException{
	String filename = destination;
	BufferedReader rd = null;
	rd = new BufferedReader(new FileReader(new File(destination)));
	String inputLine = null;
	FileWriter arq = new FileWriter(destinationws);
	PrintWriter gravarArq = new PrintWriter(arq);
	String tweet="";
	while((inputLine = rd.readLine()) != null){
			inputLine = inputLine.toLowerCase();
			//System.out.println(inputLine);
			if (!inputLine.equals("")) // don't write out blank lines
		    {
				inputLine=inputLine+"\n";
		        gravarArq.write(inputLine, 0, inputLine.length());
		    }
											}
			
}
}
