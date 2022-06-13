
public class EnemyNode
{
    //instantiate variables
    private Enemy data;
    private EnemyNode next;

    //create constructor with no constructor
    public EnemyNode()
    {
        //turns the data from whatever it is (null) to zero
        setData(null);
        //set the next variable in the list to null so program doesnt have error
        next = null;
    }

    //create constructor with integer parameter (data)
    public EnemyNode(Enemy data)
    {
        //set the data to the data entered in the parameter
        setData(data);
        next = null; //always set node variables to null. It will make your life easier in C++.
    }

    //create method that will return the data that was most recently entered in
    public Enemy getData()
    {
        return data;
    }

    //create method that returns what is in the node connected to the one called on
    public EnemyNode getNext()
    {
        return next;
    }

    //create method that sets the data at a certain point to the data in the parantheses
    public void setData(Enemy data)
    {
        this.data = data;
    }

    //create method that connects a node to the following node
    public void setNext(EnemyNode next)
    {
        this.next = next;
    }

    //create iterator function
   /* public Iterator getIterator()
    {
        //return new EnemyIterator(this);
    }*/
}