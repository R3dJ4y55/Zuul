import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Object[] objects = {};

    /**
     * Create a room described "description". Initially, it has no exits. 
     * "description" is something like "a kitchen" or "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        objects = new Object[5];
    }
    public void addObject(Object obj){
        boolean added = false;
        for (int i =0; i < objects.length && !added; i++){
            if(objects[i] != null){
                objects[i] = obj;
                System.out.println("Added!");
                added = true;
            }
        }
    }
    public void rmObject(Object obj){
        for (int i =0; i < objects.length; i++){
            if(objects[i].equals(obj)){
                objects[i] = null;
            }
        }
    }
    public Object[] getObjects(){
        return objects;
    }
    public Room getExit(String direction){
        return exits.get(direction);
    }
    /** * Return a description of the room's exits,
     * for example, "Exits: north west."
     * @return A description of the available exits */
    public String getExitString(){
        String exitStr = "";
        Set<String> keys = exits.keySet();
        for (String exit : keys){
            exitStr = exitStr + " " + exit;
        }
            return exitStr;
    }
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param dir The direction of the neighboring room (north, east, south, west, up, down)
     * @param neighbor The neighboring room
     */
    public void setExits(String dir, Room neighbor) 
    {
        exits.put(dir, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return a more detailed room description
     */
    public String getLongDescription(){
        return "You are " + description + ".\n" + getExitString();
    }
}
