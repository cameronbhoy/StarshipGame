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

public class ShopPane extends VBox
{
   int intAmt= 1; //so player starts with a weapon, counts number of weapons player has purchased

   Button plus = new Button("+");;
   Button minus = new Button("-");
   Label amt = new Label("" + intAmt); //amt label displays
   Label title = new Label("");
   Label costLabel = new Label("");
   private static Label moneyLabel = new Label("");
   Player p = Player.getInstance();

   String weaponName;
   int cost;
   int costTrack = p.getMoney();

   public ShopPane()
   {
      moneyLabel.setText("Current Funds: "+ p.getMoney());
      //money = Player.getInstance().getMoney();

      HBox row = new HBox();

      getChildren().add(moneyLabel);

      //getChildren().add(row);

      setAlignment(Pos.TOP_CENTER);
   }
   //setup the shop

   public ShopPane(String name, int cost_in)
   {
      moneyLabel.setText("Current Funds: " + p.getMoney());
      weaponName = name;
      title.setText(name);
      cost = cost_in;
      costLabel.setText(""+cost);
      amt.setText(""+ intAmt);

      costTrack = cost;
      HBox row = new HBox();
      
      getChildren().add(title);
      getChildren().add(costLabel);
      
      row.getChildren().add(minus);
      row.getChildren().add(amt);
      row.getChildren().add(plus);
      
      getChildren().add(row);
      
      minus.setOnAction(new ButtonListener());  
      plus.setOnAction(new ButtonListener());  
      
      setAlignment(Pos.TOP_CENTER);
   }
   
   //handler for the buttons
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)      
      {
         if(e.getSource() == plus)
         {
            if(Player.getInstance().checkSpend(cost))
            {
               intAmt++;
               p.editWeaponNum(intAmt, weaponName); //edit the amount of weapons, sorted by the name of the weapon (specks vs sides)
               Player.getInstance().spendMoney(cost);//spend money
               amt.setText(""+intAmt);

              // moneyLabel.setText("Current Funds: "+p.getMoney());
            }
         }
         if(e.getSource() == minus)
         {
            if(intAmt > 0)//can not go below zero
            {
               intAmt--;
               p.editWeaponNum(intAmt, weaponName); //edit the amount of weapons, sorted by the name of the weapon (specks vs sides)
               Player.getInstance().addMoney(cost);//add money to account
            }
            else
               intAmt = 0;
            amt.setText(""+intAmt);
           // moneyLabel.setText("Current Funds: "+p.getMoney());
         }
         moneyLabel.setText("Current Funds: "+p.getMoney());
      }
   }

   public void updateMoney(int money_in)
   {
      moneyLabel.setText("Current Funds: "+p.getMoney());
   }
}