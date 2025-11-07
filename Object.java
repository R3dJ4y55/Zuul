
/**
 * Objects tha player can interact with.
 *
 * @author R3dJ4y55
 * @version 2025-11-07
 */
public class Object
{
    private String title;
    private int weight;
    private int value;
    private boolean taken;
    
    public Object(String name, int mass, int value){
        title = name;
        weight = mass;
        this.value = value;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public boolean isTaken(){
        return taken;
    }
}