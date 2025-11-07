
/**
 * The Player's personal info, actions, and inventory
 *
 * @author R3dJ4y55
 * @version 1
 */
public class Player
{
    private Object[] inventory = {};
    private static final int MAX_WEIGHT = 10;
    private int currentWeight = 0;
    private Room currentRoom;
    
    public String name;
    public int age;
    
    /**
     * Initializes a Player
     * 
     * @param name The player's name
     * @paran age The player's age
     */
    public Player(String name, int age, Room currentRoom){
        this.name = name;
        this.age = age;
        this.currentRoom = currentRoom;
        
        inventory = new Object[10];
    }
    
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }
    
    public void take(Object obj){
        if (obj != null){
            if (obj.isTaken() == false && (currentWeight + obj.getWeight()) <= MAX_WEIGHT)
            for (int i =0; i < inventory.length; i++){
                if(inventory[i] != null){
                    inventory[i] = obj;
                }
            }
            currentWeight += obj.getWeight();
            System.out.println("Picked up: " + obj);
        }
    }
    
    public void drop(Object obj){
        for (int i =0; i < inventory.length; i++){
            if(inventory[i].equals(obj)){
                inventory[i] = null;
            }
        }
        currentWeight -= obj.getWeight();
    }
    
    public Object toObject(String name){
        for (Object obj : currentRoom.getObjects()){
            if (obj.equals(name.trim())){
                return obj;
            }
        }
        return null;
    }
}