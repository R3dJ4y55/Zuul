/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room hub, corridor, departures, maintenanceTunnel, ticketing, store, storeroom, washroom;
      
        // create the rooms
        hub = new Room("in the main hub of the Station");
        corridor = new Room("in a long corridor. A sign above says Departures");
        departures = new Room("at departures. Many trains come through here.");
        maintenanceTunnel = new Room("in a tunnel that runs parrallel to the tracks. A key can be seen shining under a light, an employee must have dropped it.");
        ticketing = new Room("in a center containing stations that allow the purchasing of tickets. A one-way costs $3.50");
        store = new Room("at a convenience store.");
        storeroom = new Room("in a storeroom. Employees enter here for extra supplies. A sign above the door reads: Employees Only!");
        washroom = new Room("in a washroom. It Contains a sink with low pressure. A $0.25 coins lies on the floor");
        
        // initialise room exits
        
        //Hub
        hub.setExits("east", store); // east
        hub.setExits("south", ticketing); // south
        hub.setExits("west", corridor); // west
        
        // Corridor
        corridor.setExits("east", hub); // east
        corridor.setExits("west", departures); // west
        
        // Departures
        departures.setExits("east", corridor); // east
        departures.setExits("west", maintenanceTunnel); // west
        
        // Maintenance Tunnel
        maintenanceTunnel.setExits("east", departures); // east
        
        // Ticketing
        ticketing.setExits("north", hub); // north
        
        // Store
        store.setExits("east", washroom); // east
        store.setExits("south", storeroom); // south
        store.setExits("west", hub); // west

        // Storeroom
        storeroom.setExits("north", store); // north
        
        // Washroom
        washroom.setExits("west", store); // west

        // start game outside
        currentRoom = hub;  
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    private void printLocationInfo(){
        System.out.print(currentRoom.getExitString());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            System.out.println("You look around the room:");
            System.out.printf("\n %s \n", currentRoom.getLongDescription());
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help look");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.getExit("east");
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.getExit("south");
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.getExit("west");
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            printLocationInfo();
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            // signal that we want to quit
            return true;  
        }
    }
}
