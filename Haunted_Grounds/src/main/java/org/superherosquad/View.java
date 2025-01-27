/***********************************Cobi && Cody - ReAnn (Created initial class)************************************/
package org.superherosquad;

import java.io.Serializable;

public class View implements Serializable {
	private static final long serialVersionUID = 673824491807579304L;

	public void print(String s)
	{
		System.out.println(s);
	}

	public void userInput() {System.out.print("\n> ");} // ReAnn

	public void invalid()
	{
		print("\n\u001B[31mThat is an invalid command.\u001B[0m");
		print("Try again!");
	}
	
	public void room(String s)
	{
		print("You are currently in the \033[1m" + "\033[93m" + s + "\033[0m" + ".");
	}

	public void encounterMonster(Monster monster) { //Cody
		print(monster.getName() + "is in the room!");
	}

	// ReAnn
	public void mainMenu() {print("" +
							    "\033[1m\033[93m  - Main Menu -\033[0m\n" +
			                    "\033[1m    New Game\n" +
			                    "New Hard Mode Game\n" +
								"    Load Game\n" +
								"    Menu Help\n" +
								"      Exit\033[0m\n");}

	// ReAnn
	public void pauseMenu() {print(
			"\033[1m\033[93m  - Main Menu -\033[0m\n" +
					"\033[1m    New Game\n" +
					"New Hard Mode Game\n" +
					"    Load Game\n" +
					"    Save Game\n" +
					"    Menu Help\n" +
					"      Exit\033[0m\n\n" +
					"Input Options: \"newgame\" | \"newhard\" | \"load\" | \"save\" | \"menuhelp\" | \"exit\"");}
	// End of Work


	public void shopPrint(Item i) {
    	print(i.getName() + ": $" + i.getPrice());
    }
    
    public void endGame() {
    	print("And so, the campus spirit has been vanquished. You have saved your friend from the perilous paws of the campus spirit, vanquished many foes, solved many challenging puzzles, and even met a ghost teacher on the way. You and your friend return to where you came. The end.");
    }

	public void helpMenu() { //ReAnn & Cobi
		print("""
				 =======================================================================================================================================================================================
				            
				 GAME COMMANDS ->

				 \u001B[31mMENU:\u001B[0m
				 "menu" : When in the game, the user can use this command to display the main menu.
				 "menuhelp" : When in the main menu, the user can use this command to see information about the load and save features.
				 "newgame" : When in the main menu, this command allows the user to create a new game and save file.
				 "newhard" : When in the main menu, this command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.
				 "load" : When in the main menu, use this command to load a save.
				 "save" : When in the main menu, use this command to save all progress.
				 "continue" : When in the main menu, this command returns the user back into the current game(only displayed if the user is already in a game).
				 "exit" : When in the main menu, the user can use this command to leave the game.

				 \u001B[31mPLAYER/ROOM:\u001B[0m
				 "north" / "south" / "east" / "west" : Command used to move the player to the room in the specified direction if said room exists.
				 "exitroom" : When in a room, the user can use this command to return to the previous room.
				 "help" : When in the game, the user can use this command to display a list of commands followed by a brief description for each one.
				 "inspectroom" : When in a room, the user can use this command to see a list of all contents in the current room.
				 "lights" : When in a room with lights, the user can use this command to turn on the lights.
				 "inspect " + item : Users can use this command to read a description of an item located in the room or inventory.
				 "inventory" : Command used to view all items currently in the player's inventory. Also, displays current items equipped.
				 "startpuzzle" : When in a room with a puzzle, the user can use this command to start attempting to solve the puzzle.
				 "talk" : When in a rooom with an NPC, the user can use this command to initiate conversation with the NPC. 
				            
				 \u001B[31mCOMBAT:\u001B[0m
				 "attack" : When in combat, the user can use this command to attack the current enemy.
				 "defend" : When in combat, the user can use this command to defend against the current enemy's next attack.
				 "item" : When in combat, the user can use this command to use a consumable item or switch weapons.
				 "run" : When in combat, the user can use this command to run away from the current enemy.
				            
				 \u001B[31mPUZZLE/RIDDLE:\u001B[0m
				 "answer " + answer : When solving a puzzle, the user can use this command followed by user input to try and solve the puzzle.
				 "hint" : When solving a puzzle, the user can use this command to receive a hint.
				            
				 \u001B[31mITEMS:\u001B[0m
				 "pickup " + item name : Users can use this command to read a description of an item located in the room or inventory.
				 "drop " + item name : Users can use this command to drop an item followed by the name of the item.
				 "equip " + item name : Users can use this command to unequip an item followed by the name of the item. The item returns to the character's inventory.
				 "use " + item name : Users can use this command to use an item followed by the name of the item.
				 "drink " + item name : Users can use this command to drink an item followed by the name of the item.
				            
				 \u001B[31mNPCS/SHOP:\u001B[0m
				 "shop" : User can use this command for the purchase of consumables.
				 "riddle" : User can use this command to initiate a riddle with the NPC.
				 "leave" : User can use this command to leave the shop or end the conversion with the NPC.
				 "buy " + item name + quantity: When in the shop, the user can use this command to purchase consumables.
				 "list" : When in the shop, the user can use this command to list all items available for purchase in the shop.
				            
				 =======================================================================================================================================================================================                                                                          
				 """);
	}


	public void mainMenuHelp() {
		print("""
    =======================================================================================================================================================================================\n
				 MAIN MENU COMMANDS ->
				 
				\u001B[31m"newgame"\u001B[0m : Use this command to create a new game and save file.
				\u001B[31m"newhard"\u001B[0m : This command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.          			  
				\u001B[31m"load "\u001B[0m + save name : Use this command to load a save by inputting the previously determined save name.      	              
				\u001B[31m"menuhelp"\u001B[0m : Use this command to see a list of commands that can be used in the menu.
				\u001B[31m"exit"\u001B[0m : Use this command to leave the game.
				\n=======================================================================================================================================================================================
				 """);
	}

	public void pauseMenuHelp() {
		print("""
    =======================================================================================================================================================================================\n
				 PAUSE MENU COMMANDS ->
				 
				\u001B[31m"newgame"\u001B[0m : Use this command to create a new game and save file.
				\u001B[31m"newhard"\u001B[0m : This command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.          			  
				\u001B[31m"load "\u001B[0m + save name : Use this command to load a save by inputting the previously determined save name.      	              
				\u001B[31m"menuhelp"\u001B[0m : Use this command to see a list of commands that can be used in the menu.
				"continue" : Use this command to return to the current game.
				\u001B[31m"exit"\u001B[0m : Use this command to leave the game.
				\n=======================================================================================================================================================================================
				 """);
	}
}
