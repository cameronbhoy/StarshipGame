import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyWeaponSides extends EnemyWeapon {

    public EnemyWeaponSides(double  x, double y,  Enemy owner_in, EnemyWeapon nextEnemyWeapon){
        super(x, y, owner_in, nextEnemyWeapon);
        wp = new WeaponSides((int)x, (int)y, owner_in, false);
        weaponName = "sides";
    }

    @Override
    public void drawMe(GraphicsContext gc)
    {
        gc.setFill(Color.ALICEBLUE);
        gc.fillOval(owner.getXpos()+posX, owner.getYpos()+posY, 3,5);

        super.drawMe(gc);
    }
}