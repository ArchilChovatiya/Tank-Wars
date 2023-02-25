import java.io.*;
import java.net.*;

public class Sender  
{
    private static DatagramSocket socket;
    private static InetAddress address;
    private static XY oldXY = new XY();
    static
    {
        try{
           if (socket==null)
               socket = new DatagramSocket();
           address = InetAddress.getByName(MyWorld.server_address);
        }catch(Exception e){e.printStackTrace();}
    }

    public static void sendData(Object o){
        

        try{
         
             //System.out.println("inside");
         ByteArrayOutputStream bStream = new ByteArrayOutputStream();
         ObjectOutput oo = new ObjectOutputStream(bStream);
         oo.writeObject(o);
         oo.close();

         byte[] buf = bStream.toByteArray();
         DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 5000);
         socket.send(packet);
        
        }catch(Exception e){e.printStackTrace();}

    }
}
