public class ScheduledEnemy {
    public float timeElapsed;
    public int xPosition;
    public String type;
    private ScheduledEnemy next;

    public ScheduledEnemy(float timeElapsed, int xPosition, String type){
        this.timeElapsed = timeElapsed;
        this.xPosition = xPosition;
        this.type = type;
        next = null;
    }
    public void setNext(ScheduledEnemy se){
        next = se;
    }
    public ScheduledEnemy getNext(){
        return next;
    }
}
