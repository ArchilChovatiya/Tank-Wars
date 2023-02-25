import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.Date;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import javax.swing.*;
/**
 * Write a description of class Introduction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Introduction extends World
{
    GreenfootSound sound = new GreenfootSound("Lounge Game2.wav");
    /**
     * Constructor for objects of class Introduction.
     * 
     */
    public Introduction() throws java.io.IOException
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800,600, 1);
        File f = new File("userdata.txt");
        if(!(f.exists() && !f.isDirectory())) { 
            System.out.print("File doesn't exist.");  
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
                Date now = new Date();      
                Long longTime = new Long(now.getTime());
                Writer wr = new FileWriter(f);
                wr.write(new Integer(longTime.intValue()).toString());
                wr.close();
            }
        }
            BufferedReader br = new BufferedReader(new FileReader(f));
            if(MyWorld.server_address==null)
                MyWorld.server_address = JOptionPane.showInputDialog("Enter ip address of the server."); 
            try{
               Socket clientSocket = new Socket(MyWorld.server_address, 6789);

                // Get input and output streams for the socket
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            
                // Send a message to the server
                outToServer.writeBytes(br.readLine()+ '\n');
                // Receive a response from the server
                String modifiedSentence = inFromServer.readLine();
            
                // Print the response
                System.out.println("FROM SERVER: " + modifiedSentence);
                // Close the socket
                clientSocket.close(); 
                String[] splited = modifiedSentence.split("#");
                addObject(new Label(splited[0],Color.BLUE), 150, 500);
                addObject(new Label(splited[1],Color.GREEN), 400, 500);
                addObject(new Label(splited[2],Color.RED), 650, 500);
            }catch(Exception e){
                
            }
            

                
    }
    
    public void act(){
        IntroMusic();
        start();
        prepare();
        
    }
    
    public void start(){
        if("space".equals(Greenfoot.getKey())) 
        {
            sound.stop();
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
    public void prepare(){
        
        IntroPlayer intro1 = new IntroPlayer();
        addObject(intro1,100,330);
        IntroPlayer2 intro2 = new IntroPlayer2();
        addObject(intro2,700,240);
        if(Greenfoot.getRandomNumber(200)<1){
            Bullets bullet = new Bullets();
            addObject(bullet,100,330);
        }
        if(Greenfoot.getRandomNumber(200)<1){
            Bullet2 bullet2 = new Bullet2();
            addObject(bullet2,700,240);
        }
        //removeObjects(getObjects(Bullets.class));
        //removeObjects(getObjects(Bullet2.class));
   
    }
    public void IntroMusic(){
        sound.playLoop();
    }
    
    
}
