import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;
import java.io.File;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    public int[] health = {100, 100};

    public GameOver()
    {
        try{
            File f = new File("userdata.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            Socket clientSocket = new Socket(MyWorld.server_address, 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            
            String displayMsg = "";
            if (Acceptor.xy.health[0] <= 10){
                Acceptor.xy.health[0] = 0;
                displayMsg = "Player 2 Wins";
                if(Acceptor.xy.id==1)
                    outToServer.writeBytes(br.readLine()+"#1"+ '\n');
                else
                    outToServer.writeBytes(br.readLine()+"#0"+ '\n');
                
            }
            if(Acceptor.xy.health[1] <= 10){
                Acceptor.xy.health[1] = 0;
                displayMsg = "Player 1 Wins";
                if(Acceptor.xy.id==0)
                    outToServer.writeBytes(br.readLine()+"#1"+ '\n');
                else
                    outToServer.writeBytes(br.readLine()+"#0"+ '\n');
            }
            setImage(new GreenfootImage(displayMsg, 48, Color.WHITE, Color.BLACK));
        }catch(IOException e){
            System.out.println(e);
        }

        Sender send = new Sender();
        
        //MyWorld.t.stop();
        health[0] = Acceptor.xy.health[0];
        health[1] = Acceptor.xy.health[1];
        //Acceptor.xy = new XY(-1,-1,health);
        
        

    }
    
    /*public void act(){
        if("space".equals(Greenfoot.getKey())) 
        {
            MyWorld.sound.stop();
            Acceptor.xy = new XY(-1,-1,Acceptor.xy.health);
            try{
                Greenfoot.setWorld(new Introduction());    
            }catch(Exception e){
                //do_ntg
            }
            
        }
    }*/
}
