//import java.util.HashMap;
//import java.util.Iterator;

/*public interface Iterator {
    //public Iterator createIterator();
    boolean hasNext();
    EnemyNode next();

}*/

public class EnemyIterator implements Iterator {
    //public Iterator createIterator();
    EnemyLinkedList enemies;
    int position = 0;

    //constructor
    public EnemyIterator(EnemyLinkedList enemies) {
        this.enemies = enemies;
    }

    //function gets next enemy node in linked list
    public EnemyNode next() {
        this.position += 1;
        EnemyNode enemy = enemies.getEnemy(position);
        return enemy;
    }
    //sees if there is another node in linked list
    public boolean hasNext() {
        if (position >= enemies.getNumberOfItems() || enemies.getEnemy(position) == null) {
            return false;
        } else {
            return true;
        }
    }
    //returns the node that it is currently on
    public EnemyNode current(){
        EnemyNode enemy = enemies.getEnemy(position);
        return enemy;
    }

    //remove enemynode in linked list
    public void remove()
    {
        enemies.deleteAtIndex(position);
    }

}

/*public class EnemyIterator implements Iterator {
    HashMap<String, EnemyFactory> enemies;
    int position;

    /*public EnemyIterator(HashMap<String, EnemyFactory> enemies) {
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
}*/
