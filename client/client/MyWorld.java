import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    static GreenfootSound sound;
    public static MyWorld mw;
    Player1 player1;
    Player2 player2;
    Counter counter;
    Counter2 counter2;
    public static WorldState current;
    private WorldState prev;    
    private WorldState onGoing;
    private WorldState gameOver;
    private WorldState waiting;
    public static String server_address;
    Thread t;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        MyWorld.mw = this;
        player1 = new Player1();
        player2 = new Player2();
        counter = new Counter(player1);
        counter2 = new Counter2(player2);
        onGoing = new OngoingGameWorldState(this);
        gameOver = new GameOverGameWorldState(this);
        waiting = new WaitingGameWorldState(this);
        sound = new GreenfootSound("Marimba Boy.wav");
        current = waiting;
        prepare();
        try{
            t = new Acceptor();
            t.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Thread not running");
        }
    }
    
    public void act(){
        BackMusic();
        prepare();
    }
    
    public WorldState getState() {
        return current;
    }
    public void setState(WorldState state) {
        this.current = state;
    }
    
    public WorldState getOngoingState() {
        return this.onGoing;
    }

    public WorldState getGameOverState() {
        return this.gameOver;
    }
    
    public WorldState getWaitingState() {
        return this.waiting;
    }

    public Counter getCounter()
    {
        return counter;
    }
    public Counter2 getCounter2()
    {
        return counter2;
    }
    
    public void doOngoingGame() {
       this.current.doOngoingGame();
   }
   public void doPause() {
       this.current.doPause(); 
   }
   public void doGameOver() {
       this.current.doGameOver();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        if (prev != current)
        {
            if (current instanceof WaitingGameWorldState)
                prepareWaiting();
            if (current instanceof OngoingGameWorldState)
                prepare2player();
            if (current instanceof GameOverGameWorldState)
                prepareGameOver();
        }
        prev = current;
    }
    
    private void prepareWaiting()
    {
        Waiting waiting = new Waiting();
        addObject(player1,100,300);
        this.addObject(waiting, 400, 300);
    }
    
    private void prepareGameOver()
    {
        GameOver gameover = new GameOver();
        this.addObject(gameover, this.getWidth()/2, this.getHeight()/2);
    }
    private void prepare2player()
    {
        removeObjects(getObjects(null));
        addObject(counter, 200, 40);
        addObject(counter2, 500, 40);
        addObject(player1,100,300);
        addObject(player2,700,300);
    }
    public void BackMusic(){
        sound.playLoop();
    }
}
