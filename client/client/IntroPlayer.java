import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.net.*;
import java.io.*;
/**
 * Write a description of class Ele here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroPlayer extends Actor
{

    
    public IntroPlayer(){
        GreenfootImage image = getImage();
        image.scale(image.getWidth() - 100, image.getHeight() - 30);
        setImage(image);

    }
}
    

