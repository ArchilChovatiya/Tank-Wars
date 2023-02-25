

import java.io.*;
import java.net.*;

class TCPClient {
  public static void main(String argv[]) throws Exception {
      String sentence;
      String modifiedSentence;
      try (Socket cs = new Socket("192.168.29.73", 6789)) {
		DataOutputStream outToServer = new DataOutputStream(cs.getOutputStream());
		  sentence = "abcdefg#1";
		  outToServer.writeBytes(sentence);
		  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(cs.getInputStream()));
		  modifiedSentence = inFromServer.readLine();
		  System.out.println("FROM SERVER: " + modifiedSentence);
	}
      
  }
}




