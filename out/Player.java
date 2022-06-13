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
import java.io.*;
import java.util.*;
import java.text.*;

class Player extends Ship
{
   private static Player instance = null;
   Weapon wepSpeckLeft = new WeaponSpeck(-5,0,this,true);
   Weapon wepSpeckRight = new WeaponSpeck(5,0,this,true);
   Weapon wepSidesLeft = new WeaponSides(-3,0,this,true);
   Weapon wepSidesRight = new WeaponSides(3,0,this,true);

   private double moveX=0;
   private double moveY=0;
   private int money=100000;
   private int speckWeaponNum = 1, sidesWeaponNum = 1, trackWeaponNum = 1;
   private ArrayList<Enemy> subscribedEnemies = new ArrayList<Enemy>();
   private long trackTime = 0, prevTime = System.currentTimeMillis();;

   //player constructor
   private Player()
   {
      super(400,500,20,20, 100, true, Projectille.Owner.PLAYER);
   }

   //method to carry over the number of a specific weapon the player has
   public void editWeaponNum(int weaponsNum, String ammoType)
   {
      if(ammoType.equals("Specks")) {
         speckWeaponNum = weaponsNum;
         System.out.println("speck changed");
      }
      else if(ammoType.equals("Sides")) {
         sidesWeaponNum = weaponsNum;
         System.out.println("side changed");
      }
      else if(ammoType.equals("Track")){
         trackWeaponNum = weaponsNum;
         System.out.println("track changed");
      }
   }

   //accessor for number of specks weapons
   public int getSpeckWeaponNum()
   {
      return speckWeaponNum;
   }
   //accessor for number of sides weapons
   public int getSidesWeaponNum()
   {
      return sidesWeaponNum;
   }
   //accessor for number of track weapons
   public int getTrackWeaponNum()
   {
      return trackWeaponNum;
   }

   //sub/unsub enemies
   public void subscribeEnemy(Enemy e){
      subscribedEnemies.add(e);
   }
   public void removeEnemy(Enemy e){
      subscribedEnemies.remove(e);
   }
   private void damageEnemies() {
      long current = System.currentTimeMillis();
      trackTime += current-prevTime;
      prevTime = current;
      if(trackTime > 1000) {
         for (int i = 0; i < subscribedEnemies.size(); i++) {
            subscribedEnemies.get(i).takeDamage(1f);
         }
         trackTime = 0;
      }
   }

   //deal with players money
   public void addMoney(int money_in)//add money when sell items (50% of item)
   {
      //see if they have the item
         //return item; take out of list of things they own
      money += money_in*0.5;
   }
   public void addShipMoney(int money_in)//add money when sell items (50% of item)
   {
      money += money_in;
      System.out.println("Ship money added : "+money_in);
   }
   public void spendMoney(int money_in)//take away money from total
   {
      if(money >= money_in)
      {
         //add item
         money = money - money_in;
      }
      else
      {
         //money = money;
         System.out.println("Can NOT buy this item");
      }
   }
   public boolean checkSpend(int money_in)//take away money from total
   {
      if(money >= money_in)
      {
         //add item
         return true;
      }
      else
      {
         //money = money;
         return false;
      }
   }
   public int getMoney()
   {
      return money;
   }
   
   public void run()
   {
      //movement code
      double lr=0;
      double ud=0;

      if(Level.bl)
      {
         lr -= 10;
      }
      if(Level.br)
      {
         lr += 10;
      }   
      if(Level.bu)
      {
         ud -= 10;
      }
      if(Level.bd)
      {
         ud += 10;
      }   
      
      moveX = moveX*.95 + lr*.05; //this is a "running average". smooths the movement... kind of.
      moveY = moveY*.95 + ud*.05;
      posX+=moveX;//*Level.timeBetweenFrames;
      posY+=moveY;//*Level.timeBetweenFrames;
      
      if(posX < 10)
      {
         posX = 10;
      }
      if(posX > 790)
      {
         posX = 790;
      }
      if(posY > 590)
      {
         posY = 590;
      }
      if(posY < 10)
      {
         posY = 10;
      }
      
      //weapon code
      if(Level.theLevel.rdown) {
        selectedWeapon = (selectedWeapon+1)%3;
      }

      //shoot the weapon
      if(Level.theLevel.fdown) {
         if(selectedWeapon == 0 && speckWeaponNum > 0)
         {
            wepSpeckLeft.run();
            wepSpeckRight.run();
         }
         else if (selectedWeapon == 1 && sidesWeaponNum > 0) {
            wepSidesLeft.run();
            wepSidesRight.run();
         }
         else if (selectedWeapon == 2 && trackWeaponNum > 0) {
            damageEnemies();
         }
         else
         {
            System.out.println("sorry mate, can't let ya shoot");
         }
      }
   }

   public int getSelectedWeapon()
   {
      return selectedWeapon;
   }
   
   int selectedWeapon = 0;
   
   //draw a simple shape for the player
   static Color c = new Color(1,.6,1,1);
   public void drawMe(GraphicsContext gc)
   {
      gc.setFill(c);
      gc.fillRect(posX - 10, posY,20,1);
      gc.fillRect(posX, posY -10,1,20);
   }

   //add singleton get instance code to get info about player from other classes
   public static Player getInstance()
   {
      if (instance == null) {
         instance = new Player();
         //set up weapons
      }
      return instance;
   }
}
