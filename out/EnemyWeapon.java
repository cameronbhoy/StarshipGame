import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class EnemyWeapon extends Enemy {
    protected Weapon wp; //Reuse Weapon code, will use for behavior
    protected Enemy owner;
    protected String weaponName;

    public EnemyWeapon(double  x, double y,  Enemy owner_in, EnemyWeapon nextEnemyWeapon){
        super(x, y, 0, 0, false, 0,"NULL", nextEnemyWeapon);
        owner = owner_in;
    }

    String getWeaponName(){
        return weaponName;
    }

    @Override //run this weapon and the next weapon, if there is one
    public void run() {
        wp.run();
        if(next != null)
            next.run();
    }

    @Override
    public void drawMe(GraphicsContext gc)
    {
        if(next != null)
            next.drawMe(gc);
    }


}
