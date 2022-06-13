
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.application.Application;
import javafx.util.Pair;
import java.util.Iterator;

import java.io.*;
import java.util.*;
import java.text.*;

public class Level
{
   public ArrayList<GameObject> objects = new ArrayList();//turn into linked list
   public HashMap<String, EnemyFactory> enemyHashMap = new HashMap<String, EnemyFactory>();
   private ArrayList<ScheduledEnemy> schedule = new ArrayList<ScheduledEnemy>();

   //create linked list and iterator that iterates over linked list
   EnemyLinkedList enemies = new EnemyLinkedList();
   EnemyIterator iter = enemies.getIterator();

   //probably should have just done the level as a singleton. 
   public static boolean bl,br,bu,bd, fdown, rdown;
   public static Level theLevel;

   private Player p;
   private String filename;
   private ScheduledEnemy nextEnemy;

   public Level()
   {
      theLevel = this;
      read();
   }

   public void read()
   {
      try
      {
         Scanner scan = new Scanner(new File("level"));
         filename = scan.next();

         scan.next();

         int numOfShips = scan.nextInt();

         //ship
         for(int i=0;i<numOfShips;i++)
         {
            String nameOfShip = scan.next();
            int size = scan.nextInt();
            String color = scan.next();
            int hp = scan.nextInt();
            int moneyIfDestroyed = scan.nextInt();
            Enemy enemy = new Enemy(500, 200, size, hp, false, moneyIfDestroyed, color, null);

            scan.next(); //weapon block

            int numweps = scan.nextInt();
            EnemyWeapon enemyWeapon = null, currentWeapon = null;

            for(int j=0;j<numweps;j++)
            {
               String namewep = scan.next();
               int xOffset = scan.nextInt();
               int yOffset = scan.nextInt();
               //should use factory?? looks like factory
               if(namewep.equals("speck")){
                  enemyWeapon = new EnemyWeaponSpeck(xOffset, yOffset, enemy, null);
               }
               else if(namewep.equals("sides")){
                  enemyWeapon = new EnemyWeaponSides(xOffset, yOffset, enemy, null);
               }
               //link them together

               //the first is the enemy, not a weapon
               if(j == 0){
                  enemy.setNextWeapon(enemyWeapon);
                  currentWeapon = enemyWeapon;
               }
               else
                  currentWeapon.setNextWeapon(enemyWeapon);
            }

            scan.next(); //ai block
            int ainums = scan.nextInt();
            for(int j=0;j<ainums;j++)
            {
               String direction = scan.next();
               int howManyFrames = scan.nextInt();
               int speedOfMove = scan.nextInt();
            }

            //create the ship type
            enemyHashMap.put(nameOfShip, new EnemyFactory(enemy));
         }

         String data = scan.next(); //data for level block

         String type = scan.next();
         ScheduledEnemy se = null;
         while(!type.equals("end"))
         {
            float timeElapsed= scan.nextFloat();
            int x = scan.nextInt();
            if (se == null){
               se = new ScheduledEnemy(timeElapsed, x, type);
               nextEnemy = se;
            }
            else{
               se.setNext(new ScheduledEnemy(timeElapsed, x, type));
               se = se.getNext();
            }

            type = scan.next();
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   //on starting the game
   public void Start()
   {
      timeElapsed = 0;
      p = Player.getInstance();//p = new Player();
      objects.add(p);
      Random rand = new Random();
   }

   //on stopping the game
   public void Stop()
   {
      objects.clear();
      GameObject.clearCollision();
   }

   public boolean end()
   {
      p = Player.getInstance();
      return p.getHp()<=0;
   }

   //main method the game loops over
   public static double timeBetweenFrames=0;
   private static double timeElapsed=0;

   public void Update(Canvas theCanvas, double time)
   {
      System.out.println("top of update");
      timeBetweenFrames = time;
      timeElapsed += timeBetweenFrames;

      //spawn enemies via linked list queue
      while (nextEnemy != null && nextEnemy.timeElapsed < timeElapsed){
         Enemy e = enemyHashMap.get(nextEnemy.type).createEnemy(nextEnemy.xPosition, -10);
         //dont do this
         //objects.add(e);
         //add enemies to linked list
         enemies.add(e);
         //add enemies to the stack
         //enemystack.push(e);
         System.out.println("---------IN ADDING WHILE LOOP -----------");
         nextEnemy = nextEnemy.getNext();
      }
      System.out.println("added enemies");
      //EnemyIterator itrEnemy
      //https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm
      //https://www.youtube.com/watch?v=VKIzUuMdmag
      /*for(Iterator iter = EnemyContainer.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      //Iterator badGuyIterator = Level.createIterator();
      /*java.util.Iterator<GameObject> it = objects.iterator();
      while(it.hasNext())
      {
         if(it.next() instanceof Enemy)
         {

         }
      }*/
      //iterate over the enemy stack
      //while(enemystack.hasNext()){ }
      //fire all the weapons
      //check collisions??

      //p = Player.getInstance();
      System.out.println("update but running thru objects");
      for(int i=0;i<objects.size();i++)
      {
         objects.get(i).run();
      }
      GameObject.RunCollisions();

      for(int i=0;i<objects.size();i++)
      {
         if(objects.get(i).checkIfNoMore())
         {
            if(objects.get(i).doesCollide())
            {
               objects.get(i).unregisterCollisions();
            }

            objects.remove(i);
            i--;
         }
      }
      System.out.println("made it thru objects, about to iterate...");
      //iterate over enemies
      //THIS IS AN ISSUE AREA
      while(iter.hasNext()) //is this the issue?
      {
         iter.current().getData().run(); //infinately running
      }
      GameObject.RunCollisions(); //can you run collisions if nothing is there?
      System.out.println("after it was supposed to run");
      while(iter.hasNext())
      {
         System.out.println("start while");
         if(iter.current().getData().checkIfNoMore())
         {
            if(iter.current().getData().doesCollide())
            {
               iter.current().getData().unregisterCollisions();
            }
            System.out.println("am i here?");
            iter.remove();
            iter.next();
            //i--;
         }
         System.out.println("end while");
      }
      System.out.println("yay you iterated!");

      GraphicsContext gc = theCanvas.getGraphicsContext2D();
      gc.setFill(Color.GRAY);
      gc.fillRect(0,0,800,700);
      for(int i=0;i<objects.size();i++)
      {
         objects.get(i).drawMe(gc);
      }
      //for enemies
      System.out.println("about to draw enemies w iterator");
      while(iter.hasNext())
      {
         iter.current().getData().drawMe(gc);
      }
      System.out.println("did emenies draw? ");
      gc.setFill(Color.WHITE);
      gc.fillText("Money "+p.getMoney(),40,40);

      //only get the rdown for the first frame it happens
      Level.rdown = false;
      System.out.println("done w/ update");
   }

   //remove the object from the containers. I don't think this is current


   //helper methods for creating the weps
   public void createSpeck(double x, double y, double speedx, double speedy, boolean playerCreated)
   {
      Speck s = new Speck(x,y,speedx, speedy,playerCreated);

      objects.add(s);
   }
   //create specks
   public void createSides(double x, double y, double speedx, double speedy, boolean playerCreated, boolean isLeft)
   {
      Sides s = new Sides(x,y,speedx, speedy,playerCreated,isLeft);
      objects.add(s);
   }


   //@Override
  /* public Iterator createIterator() {
      return null;
   }*/


   //public Iterator createIterator()
   //   return null;

      //inner class that is a container for iterator
   public class EnemyContainer implements Iterator {
      HashMap<String, EnemyFactory> enemies;
      int position;

    public EnemyContainer(HashMap<String, EnemyFactory> enemies) {
        this.enemies = enemies;
    }



    public Object next() {
        EnemyFactory enemy = enemies.get(position);
        position = position + 1;
        return enemy;
    }

    public boolean hasNext() {
        if(position >= enemies.size ()|| enemies.get(position) == null){
            return false;
        }
        else{
            return false;
        }
    }
}

   /*public interface Iterator{
      boolean hasNext();
      Object next();
   }*/

   //public static Iterator createIterator() {
      //return (Iterator) new EnemyIterator(GameObject);
   //}
}