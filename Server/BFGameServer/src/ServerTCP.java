import java.net.*;
import java.io.*;

public class ServerTCP extends Thread {
  public ServerTCP()  throws IOException {
    // Create a server socket and bind it to a port
	JDBCInterface sqlServer = new JDBCInterface();

    ServerSocket welcomeSocket = new ServerSocket(6789);

    while (true) {
      // Wait for a client to connect
      Socket connectionSocket = welcomeSocket.accept();

      // Get input and output streams for the socket
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

      // Read a message from the client
      String clientSentence = inFromClient.readLine();
      // Modify the message and send it back to the client
      String[] splited = clientSentence.split("#");
      System.out.println(clientSentence);
      if(splited.length==1) {
          DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
          String capitalizedSentence = sqlServer.getWinPlayCount(clientSentence) + '\n';
          outToClient.writeBytes(capitalizedSentence);
      }else {
    	  sqlServer.updatePlayer(splited[0],Integer.parseInt(splited[1]));
      }
      // Close the socket
      connectionSocket.close();
    }
  }
}
