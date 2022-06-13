import java.util.LinkedList;

public class EnemyLinkedList {
    //intsantiate variables (create int for items and LinkedListNode head)
    private EnemyNode head;
    private int numberOfItems;

    //create constructor for this class with no parameters
    public EnemyLinkedList()
    {
        head = null; //set head to null to avoid errors
        numberOfItems=0; //instantiate numberOfItems incase there are no items when method called
    }

    //create method that get the amount of items in the linked list and returns it
    public int getNumberOfItems()
    {
        return numberOfItems;
    }

    //create method that adds an int to the beginning of the linked list
    public void insert(Enemy theData)
    {
        EnemyNode lln = new EnemyNode(theData);
        lln.setNext(head); //connects the current head variable to entered variable
        head = lln; //sets variable to the head
        numberOfItems++; //increases the number that keeps the amount of items in the list
    }

    //creates method that returns a boolean
    public boolean delete(Enemy value)
    {
        //return false if there is no values in the linked list
        if(head == null)
            return false;
        //if statement that sees if the "int value" is at the head
        if(head.getData() == value)
        {
            head = head.getNext();//sets the head to the next value in the linked list; removes head
            numberOfItems--; //decreases the size of the list
            return true; //returns true if value was found then removed; quit function
        }
        //if there is stuff in the list and the head is not the value looking for:
        EnemyNode current = head.getNext(); //name value linked to head as current
        EnemyNode prev = head; //rename te head as prev

        //creat while loop that runs until the end of the list (null) is reached
        //or until the value looking for is reached
        while(current != null && current.getData()!= value)
        {
            prev = prev.getNext(); //renames prev to the next in list to keep running down list
            current = current.getNext(); //renames current to the next in list to keep running down list
        }
        //if the value was found in the while statement, create if statement to delete it
        if(current!= null)
        {
            prev.setNext(current.getNext());//delete the value by redirecting which one the prev points to
            numberOfItems--;//decrease the list
        }
        //if all else fails, return false; quit function; value not in list
        return false;
    }
    //delete particular value (once)
    /*public void deleteValue(Enemy delete)
    {
        if(delete(delete))
        {
        }
    }*/

    //create toSting method
    public String toString()
    {
        String toReturn = "";
        EnemyNode current = head; //set the current node to the head node

        while(current!= null) //while the current value does not equal null (reach the end of the list)
        {
            toReturn = toReturn+" " +current.getData(); //return the values in the list
            current = current.getNext(); //set current to the next value in the list
        }
        //return the entire list
        return toReturn;
    }

    //create method to insert a number at a particular index
    public void add(Enemy addInt)
    {
        //if the index they entered is less than 0 or zero, add to the beginning of list
      /*  if(index<=0)
        {
            insert(addInt);
        }
        else
        {
            //add to end of list if index wanted is larger than list of items
            if(index>numberOfItems)
            {
                EnemyNode node = head;

                for(int i=0;i<numberOfItems-1;i++)
                {
                    if (i == numberOfItems - 1) {
                            node.setNext(current.getNext());
                            System.out.println("current : " + current.getData() + " i: " + i);
                            if (current.getNext() != null)
                                current.setNext(node);
                            break;
                        }
                }
                node.setNext(new EnemyNode(addInt));
            }*/
            //if index is in the size of the list, add it to the wanted index
            //else
           // {

        System.out.println("number of items: " + numberOfItems);
        //instantiate two nodes to track through list (one in front of the other)
        EnemyNode node = new EnemyNode(addInt);
        EnemyNode current = head;
        //loop through till hit index wanted
        if(head == null)
        {
            head = node;
        }

            for(int i=0;i<=numberOfItems;i++)
                {
                    System.out.println("Whats the value of i?: " + i);
                    //if (current.getData() != null) {
                        if (i == numberOfItems - 1) {
                            node.setNext(current.getNext());
                            System.out.println("current : " + current.getData() + " i: " + i);
                            if (current != null)
                                current.setNext(node);
                            break;
                        }

                        if (current != null) {
                            current = current.getNext();
                        }
                    //}
                }
           // }
            numberOfItems++;
      //  }
    }

    //create method to delete a number at a particular index
    public void deleteAtIndex(int index)
    {
        EnemyNode node = null;
        EnemyNode current = head;
        //run through nodes
        if(index<=0)//delete if index is 0 or less
        {
            delete(head.getData());
        }
        else //delete variable at index inserted
        {
            for(int i=0;i<index;i++)
            {
                node = current;
                current = current.getNext();
            }
            node.setNext(current.getNext());
            numberOfItems--;
        }
    }

    //create method to delete particular values
    public void deleteAllEnemies(Enemy removeInts)
    {
        //instantiate variables
        EnemyNode current = head;

        for(int i=0;i<getNumberOfItems();i++) //loop through nodes
        {
            if(current.getData()==removeInts) //remove if equals int looking for
            {
                deleteAtIndex(i); //call delete at index method to delete variable
                i--;
            }
            current = current.getNext(); //update current
        }
    }

    //add method to find the last index of a particular value
    public int findIndex(Enemy findInt)
    {
        //instantiate variables (one to track head and one to update index)
        EnemyNode node = head;
        int value = -1;

        for(int i=0;i<getNumberOfItems();i++) //loop through nodes
        {
            if(node.getData()==findInt) //remove if equals int looking for
            {
                value = i;
            }
            node = node.getNext();
        }
        if(value==-1)//if value never found
        {
            return -1;
        }
        else //return the last index of where value found
            return value;
    }

    public EnemyNode getEnemy(int index)
    {
        EnemyNode node = null;
        EnemyNode current = head;
        //run through nodes
        if(index<=0)
        {
            return head;
        }
        else //delete variable at index inserted
        {
            for(int i=0;i<=index;i++)
            {
                node = current;
                current = current.getNext();
            }
            node.setNext(current.getNext());
        }
        return node;
    }

    //create iterator
    public EnemyIterator getIterator()
    {
        return new EnemyIterator(this);
    }

    //create main method
    public static void main(String[] args)
    {
        EnemyLinkedList enemies = new EnemyLinkedList();

    }

}
