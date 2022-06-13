import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyWeaponSpeck extends EnemyWeapon {

    public EnemyWeaponSpeck(double  x, double y,  Enemy owner_in, EnemyWeapon nextEnemyWeapon){
        super(x, y, owner_in, nextEnemyWeapon);
        wp = new WeaponSpeck((int)x, (int)y, owner_in, false);
        weaponName = "speck";
    }

    @Override
    public void drawMe(GraphicsContext gc)
    {
        gc.setFill(Color.PURPLE);
        gc.fillOval(owner.getXpos()+posX, owner.getYpos()+posY, 5,3);

        super.drawMe(gc);
    }
}
