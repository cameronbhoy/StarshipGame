public class Weapon
{
/*

   in my player...
   Weapon wepSpeckLeft = new WeaponSpeck(-5,0,this,true);
   Weapon wepSpeckRight = new WeaponSpeck(5,0,this,true);
   
   Weapon wepSidesLeft = new WeaponSides(-3,0,this,true);
   Weapon wepSidesRight = new WeaponSides(3,0,this,true);
   
*/

   protected int currentCoolDown;
   protected int maxCoolDown;
   
   WeaponBehavior weps;

   private int xoffset;
   private int yoffset;
   
   Ship owner;

   public Weapon(int xoffset_in, int yoffset_in, Ship owner_in, int maxCoolDown_in)
   {
      xoffset = xoffset_in;
      yoffset = yoffset_in;
      owner = owner_in;
      maxCoolDown = maxCoolDown_in;
   }

   public Ship getOwner()
   {
      return owner;
   }

   public void run()
   {
      currentCoolDown--;
      if(currentCoolDown <= 0)
      {
         currentCoolDown = maxCoolDown;
         weps.fire(xoffset+owner.getXpos(),yoffset+owner.getYpos());
      }
   }
}