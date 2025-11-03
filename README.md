#Zuul
---
## Part 1    -   Zuul Bad

Q1: The program makes a text-based game.  
It accepts the commands: 
* go
* quit
* help  

The go command moves the plyer in the specified direction  
The quit command quits the game.  
The help command displays a help menu with important information and explains the scenario  
There are five rooms in the scenario  
[Here is a map of the five rooms](./map)  

Q2:  
The *Game* class creates and initialises all the others, as well as excecutes the commands returned by the parser and starts the game.  
The *Parser* class takes the user's input and transforms it into an object of the Command class by comparing it to the list of command words stored in the CommandWords class, if no match is found, the parse will return a command object marked as unknown.  
The *Command* class holds the two strings that make up the issued command.  
The *CommandWords* class stores the list of known command words as an array of type String[]. It has methods that allow it to compare input with the array.  
The *Room* class represents the rooms, it specifies a room's description and exits.  
---