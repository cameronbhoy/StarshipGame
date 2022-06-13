public abstract class Ship extends GameObject
{
   protected double hp;
   protected boolean itDidCollide = false;
   
   //demonstrating how to use "inner" definitions (if you used public inner classes)
   protected Projectille.Owner o;

   
   public Ship(double  x, double y, double w, double h, double hp_in, boolean doesCollide_in, Projectille.Owner o_in)
   {
      super(x,y,w,h,doesCollide_in);
      hp = hp_in;
      o = o_in;
   }
   
   public double getHp()
   {
      return hp;
   }
   
   //overiding the onTriggerCollide method. The idea is that I have the ships do soething if they hit a projectile. Damages the ship and removes the projectile.
   public void onTriggerCollide(GameObject other)
   {
      if(other instanceof Projectille)
      {
         Projectille p = (Projectille) other;
         if(p.getOwner() != o)
         {
            takeDamage(p.getDamage());
            p.markToRemove();
         }
      }
   }

   public void takeDamage(float damage){
      hp -= damage;
      if(hp <= 0) {
         itDidCollide = true;
         markToRemove();
      }
   }
}