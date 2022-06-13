public interface Iterator {
    boolean hasNext();
    EnemyNode next();
    EnemyNode current();
    void remove();
}
