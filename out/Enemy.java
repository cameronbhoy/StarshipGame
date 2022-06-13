import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;

public class Enemy extends Ship {
    protected EnemyWeapon next; //is the starting "node"
    protected String color;
    protected int moneyIfDestroyed;
    protected Color drawColor;
    protected int hasItAlreadyCollided = 0;
    protected boolean isDead = false;

    public Enemy(double  x, double y, double size, double hp_in, boolean doesCollide_in, int moneyIfDestroyed_in, String color_in, EnemyWeapon enemyWeapon){
        super(x, y, size, size, hp_in, doesCollide_in, Projectille.Owner.ENEMY);
        next = enemyWeapon;
        color = color_in;
        moneyIfDestroyed = moneyIfDestroyed_in;
        drawColor = getDrawColor();
    }

    //allows linking
    public void setNextWeapon(EnemyWeapon weapon){
        next = weapon;
    }

    @Override
    public void run() {
        posY += 1;
        System.out.println("in run");
        if(next != null)
            next.run();

        Player p = Player.getInstance();
        double distance = Math.sqrt(Math.pow(posX-p.getXpos(),2) + Math.pow( posY-p.getYpos(),2));

        if (distance < 100){
            p.subscribeEnemy(this);
        }else{
            p.removeEnemy(this);
        }
    }

    @Override
    public void drawMe(GraphicsContext gc)
    {
        gc.setFill(drawColor);
        gc.fillRect(posX, posY,width,height);

        if (next != null){
            next.drawMe(gc);
        }
    }

    public Color getDrawColor(){
        switch(color){
            case "red":
                return Color.RED;
            case "green":
                return Color.DARKGREEN;
        }
        return null;
    }
    @Override
    public void takeDamage(float damage){
        hp -= damage;
        if(hp <= 0 && !isDead) {
            isDead = true;
            itDidCollide = true;
            Player.getInstance().addShipMoney(moneyIfDestroyed);
            Player.getInstance().removeEnemy(this);
            markToRemove();
        }
    }



    //accessors
    public double getSize(){
        return width; //width and height are the same for all enemies
    }

    public double getHp(){
        return hp;
    }

   // public int getMoneyIfDestroyed() {return moneyIfDestroyed}

    public EnemyWeapon getNextWeapon(){
        return next;
    }

    public String getColor(){
        return color;
    }

    public int getMoneyIfDestroyed() {
        return moneyIfDestroyed;
    }

    /*public static void addDestructionMoney()
    {
        Player.getInstance().addShipMoney(moneyIfDestroyed);
    }*/
}
