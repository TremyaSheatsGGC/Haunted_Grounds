/**************** ReAnn (created initial class) ********************/
package org.superherosquad;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller { //Cobi && Cobi
    private Scanner input = new Scanner(System.in);
    private View view = new View();
    private Combat combat = new Combat();
    private Saver saver = new Saver();

    /*
     * Main gameplay loop.
     * The reason this returns an integer is to change the game mode.
     */

    //Cody and Cobi
    public int gamePlay(ArrayList<Room> rooms, ArrayList<Item> items, ArrayList<Puzzle> puzzles, ArrayList<Monster> monsters, ArrayList<NPC> npcs, Player p, Shop shop, int mode, int prevMode, int saveMode, boolean hard) {
        String playerInput;
        String[] tokens;
        switch (mode) {
            /*
             * First switch case checks the game mode.
             * The way the game interprets commands is based on this switch statement.
             */

            case 5, 6: //Initial menu
                if (mode == 5 && prevMode != 5) { //Welcoming messages for the main menu.
                    view.print("\n=======================================================================================================================================================================================\n");
                    view.print("\033[1m--- HAUNTED GROUNDS ---\033[0m\n");
                    view.print("Welcome to the haunting adventure that takes place in GGC's now deserted campus.\n" +
                                "Your main mission is to find your missing friend Jack.  \n" +
                                "As you explore the dilapidated grounds, the looming presence of the campus spirit sends shivers down your spine. \n" +
                                "With each step, you never know what spine-chilling encounter awaits you.\n" +
                                "Though no student has attended this campus in ages, you must tread with caution because the campus spirit is still strong. \n" +
                                "\033[1mBeware, you're in for a scare...\033[0m\n");
                    view.mainMenu();
                    view.print("Input Options: \"newgame\" | \"newhard\" | \"load\" | \"menuhelp\" | \"exit\"");
                    view.userInput();
                }

                if (mode == 6 && prevMode != 6) { //Welcoming messages for the pause menu.
                    view.print("");
                    view.pauseMenu();
                    view.userInput();
                }

                playerInput = input.nextLine().toLowerCase(); //Parse user input
                tokens = playerInput.split(" ");

                switch (tokens[0]) { //Direct user input to the proper methods.
                    case "menuhelp": //Prints out the help message for the menu that the user is on.
                        if (mode == 5) {
                            view.mainMenuHelp();
                            view.userInput();;
                        }

                        if (mode == 6) {
                            view.pauseMenuHelp();
                            view.userInput();
                        }
                        return mode;

                    case "exit": //Exit the program.
                        view.print("Thanks for playing!");
                        System.exit(0);
                        return mode;

                    case "continue": //Return the user from the pause menu to their currently-active game.
                        if (mode == 6) {
                            return saveMode;
                        } else {
                            view.invalid();
                        }
                        return mode;

                    case "newgame":  //Create a new game by effectively resetting to the start state.
                        view.print("\nStarting a New Game...\n");
                        return 90;

                    case "newhard": //Create a new hard mode game.
                        view.print("Starting a New Hard-mode Game!");
                        return 80;

                    case "load": //Load a game from a previous save file
                        if(mode == 5) {
                        	return 75;
                        }
                        if(mode == 6) {
                        	return 76;
                        }

                    case "save": //Save a game from a previous save file
                        saver.saveGame(rooms, items, puzzles, monsters, npcs, p, shop, mode, prevMode, saveMode, hard);
                        return mode;

                    default: //If none of the previous cases were reached, a message telling the user that their command is invalid will be printed.
                        view.invalid();
                        return mode;
                }

                //Cody
            case 0: //Navigating between rooms
                view.print("");
                view.room(p.getCurrentRoom().getName()); //Tells the player what room they are in.
                view.print(p.getCurrentRoom().getDescription());
                view.print("Type \"help\" for the list of game commands.");
                view.userInput(); // places user input.
                playerInput = input.nextLine().toLowerCase(); //Interpret player input.
                tokens = playerInput.split(" ");

                switch (tokens[0]) { //This is the first word of the input.

                    case "north", "n" -> { //Attempt to move to the room to the north.
                        p.moveRooms("n", rooms);
                        return mode;
                    }
                    case "south", "s" -> { //Attempt to move to the room to the south.
                        p.moveRooms("s", rooms);
                        return mode;
                    }
                    case "east", "e" -> { //Attempt to move to the room to the east.
                        p.moveRooms("e", rooms);
                        return mode;
                    }
                    case "west", "w" -> { //Attempt to move to the room to the west.
                        p.moveRooms("w", rooms);
                        return mode;
                    }

                    case "exitroom" -> { //Move to the room that the player was previously in.
                        p.exitRoom();
                        return mode;
                    }

                    case "inspectroom" -> { //Inspect the room. This will start combat if there is a monster, tell the user that the room is dark if it is, or list the room's description, items, and puzzle.
                        int n = p.getCurrentRoom().inspect(p, mode, false);
                        if(n == 10) {
                        	if(p.finalBossCheck(monsters, puzzles)) {
                        		return 1;
                        	}
                        	else {
                        		n = p.getCurrentRoom().inspect(p, mode, true);
                        	}
                        }
                        return n % 10; 
                    }

                    case "lights" -> { //Turns the lights on in a dark room, or lets the user know the lights are already on.
                        p.getCurrentRoom().lightsOn();
                        return mode;
                    }

                    case "inventory" -> { //Shows the user all items in their inventory.
                        view.print(p.showInventory());
                        return mode;
                    }
                        
                    case "startpuzzle" -> { //Initiates the puzzle if there is a puzzle in the room.
                    	mode = p.getCurrentRoom().activatePuzzle(mode);
                    	return mode;
                    }
                    
                    case "talk" -> { //Initiate conversation with the NPC in the room if there is one.
                    	mode = p.getCurrentRoom().talk(mode);
                    	return mode;
                    }

                    case "help" -> { //Prints out the help menu.
                        String yellow = "\u001B[33m"; // ANSI escape code for yellow color
                        String reset = "\u001B[0m"; // ANSI escape code to reset color
                        System.out.println(yellow);
                        view.helpMenu();
                        System.out.println(reset);
                        return mode;
                    }
                    case "pickup" -> {
                        if (tokens.length == 2) p.addItemToInventory(tokens[1]);
                        else if (tokens.length == 3) p.addItemToInventory(tokens[1] + " " + tokens[2]);
                        else if (tokens.length == 4) p.addItemToInventory(tokens[1] + " " + tokens[2] + " " + tokens[3]);
                        return mode;
                    }
                    case "use", "u" -> {
                        if (tokens.length == 2) {
                            if (p.getItem(tokens[1]) != null) {
                                p.useConsumableItem(p.getItem(tokens[1])); //add item effect to player's health
                            }
                        } else if (tokens.length == 3){
                            if (p.getItem(tokens[1] + " " + tokens[2]) != null) {
                                p.useConsumableItem(p.getItem(tokens[1] + " " + tokens[2])); //add item effect to player's health
                            }
                        } else if (tokens.length == 4){
                            if (p.getItem(tokens[1] + " " + tokens[2] + " " + tokens[3]) != null) {
                                p.useConsumableItem(p.getItem(tokens[1] + " " + tokens[2] + " " + tokens[3])); //add item effect to player's health
                            }
                        }
                        return mode;
                    }
                    case "drop" -> {
                        if (tokens.length == 2) p.dropItem(tokens[1]);
                        else if (tokens.length == 3) p.dropItem(tokens[1] + " " + tokens[2]);
                        else if (tokens.length == 4) p.dropItem(tokens[1] + " " + tokens[2] + " " + tokens[3]);
                        return mode;
                    }
                    case "equip" -> {
                        if (tokens.length == 2) p.equipItem(tokens[1]);
                        else if (tokens.length == 3) p.equipItem(tokens[1] + " " + tokens[2]);
                        else if (tokens.length == 4) p.equipItem(tokens[1] + " " + tokens[2] + " " + tokens[3]);
                        return mode;
                    }case "unequip" -> {
                        if (tokens.length == 2) p.unEquipItem(tokens[1]);
                        else if (tokens.length == 3) p.unEquipItem(tokens[1] + " " + tokens[2]);
                        else if (tokens.length == 4) p.unEquipItem(tokens[1] + " " + tokens[2] + " " + tokens[3]);
                        return mode;
                    }case "equipped" -> {
                        p.showEquipped();
                        return mode;
                    }
                    case "inspect" -> {
                    	if (tokens.length == 1) {
                    		view.print("You must enter the name of an item to use the inspect command.");
                    	} if (tokens.length == 2) p.inspectInventoryItem(p.getItem(tokens[1]));
                          else if (tokens.length == 3) p.inspectInventoryItem(p.getItem(tokens[1] + " " + tokens[2]));
                          else if (tokens.length == 4) p.inspectInventoryItem(p.getItem(tokens[1] + " " + tokens[2] + " " + tokens[3]));
                          return mode;
                    }
                    
                    case "menu" -> { //Pauses the game.
                    	return 6;
                    }

                    default -> {
                        view.invalid();
                        return mode;
                    }
                }

            case 1: //Combat - Cody
                mode = combat.combatLoop(p, input, prevMode);
                return mode;


            case 2: { //Puzzle - Cobi
                Puzzle active = null;
                for(Puzzle pz: puzzles) { //Find the active puzzle.
                    if(pz.getActive()) {
                        active = pz;
                        break;
                    }
                }
                
                view.print(active.getQuestion()); //Print the question at the start of each loop where puzzle mode is active.
                if(prevMode == 3) {
                	if(p.getCurrentRoom().getNpcId() == 0) {
                		//TODO: Mark "you really die" in red.
                        view.print("Also, you're not like us. If you die... \u001B[31m you really die \u001B[0m.");

                	}
                }

                playerInput = input.nextLine().toLowerCase(); //Interpret player input.

                switch(playerInput) {
                    case "hint" -> { //Print the hint associated with the puzzle.
                        view.print(active.getHint());
                        return mode;
                    }
                    
                    case "menu" -> { //Pauses the game.
                    	return 26;
                    }

                    default -> { //All other commands are treated as guesses to the solution of the puzzle.
                        if(playerInput.equalsIgnoreCase(active.getSolution())) {
                            mode = active.onSolve(p.getCurrentRoom(), rooms, items);
                            return mode;
                        }
                        else {
                            active.incorrect();
                            return mode;
                        }
                    }
                }
            }
            
            case 3: { //Talking to NPCs - Cobi
            	NPC active = p.getCurrentRoom().getNPC(); //The active NPC is the one in the room with the player.
            	playerInput = input.nextLine().toLowerCase(); //Interpret player input.
            	
            	switch(playerInput) {
            		case "riddle" -> { //Activate the NPC's riddle if it isn't already solved.
            			mode = active.activatePuzzle(mode);
            			return mode;
            		}
            		
            		case "shop" -> { //Attempt to enter the shop.
            			mode = active.enterShop(shop);
            			return mode;
            		}
            		
            		case "leave" -> { //Exit conversation with the NPC.
            			mode = active.leave();
            			return mode;
            		}
            		
            		case "menu" -> { //Pauses the game.
            			return 36;
            		}
            		
            		default -> {
            			view.invalid();
            			return mode;
            		}
            	}
            }
            
            case 4: { //Shop - Cobi
                playerInput = input.nextLine().toLowerCase(); //Interpret player input.
                tokens = playerInput.split(" ");
            	
            	switch(tokens[0]) {
	            	case "leave" -> { //Exits the shop and returns to talk mode.
	            		mode = shop.leave();
	            		return mode;
	            	}
	            	
	            	case "buy" -> { //Attempts to purchase the given quantity of the given item.
	            		try {
		            		try {
		            			shop.purchase(p, tokens[1], Integer.parseInt(tokens[2]));
			            		return mode;
		            		} catch(NumberFormatException nfe) { //This is reached if the given quantity is not an integer.
		            			view.print("You must send an integer for the quantity in this command.");
		            			return mode;
		            		}
	            		} catch (IndexOutOfBoundsException ioob) {
	            			view.print("You must send a quantity to use this command.");
	            		}
	            	}
	            	
	            	case "list" -> { //Lists all items in the shop.
	            		shop.list();
	            		return mode;
	            	}
	            	
	            	case "menu" -> { //Pauses the game.
	            		return 46;
	            	}
            	}
            }
        }
        return mode;
    }
}