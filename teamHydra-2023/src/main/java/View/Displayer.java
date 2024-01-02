package View;/*
 The Displayer class will be used to display out puts to the console that steam from the driver class game. So, any output the user would see
 due to an action caused by interacting with the game class directly, will be displayed from this class. However, this class will not be used
 to display every output to the console, since some outputs will reference information that is local to specific classes.
 */
import Model.Player;

import java.io.Serializable;

// Authors: @Abdoulie, Jason, Amara

public class Displayer implements Serializable {
    String menuInput = "";
    Player player;

    public Displayer() {
    }

    // method main menu used to display when the player starts the game
    public void mainMenuBeforeInput() {
        System.out.println("Treasures of the Deep!");
        System.out.println("Main menu");
        System.out.println("----------");
        System.out.println("(Please enter one of the following commands)");
        System.out.println("New Game");
        System.out.println("Load Save");
        System.out.println("Exit Game");

    }

    public void mainMenuAfterInput() {
        System.out.println("Treasures of the Deep!");
        System.out.println("Main menu");
        System.out.println("----------");
        System.out.println("(Please enter one of the following commands)");
        System.out.println("Start Game");
        System.out.println("Exhibit");
        System.out.println("Exit Game");
    }
    // made by Jason and Amar
    public void welcomeTextPart1(){
            System.out.println("You are an avid adventurer and accomplished treasure hunter and historian.\n " +
                    "You know how to pilot a submarine and you even own one equipped with a claw for grabbing\n" +
                    " treasure and deterring wildlife and, though it seems like overkill, torpedo tubes should you want that extra firepower.\n " +
                    "You now fund an expedition into the depths of the ocean to uncover many items of monetary and historical significance.\n" +
                    " You are the one and only Captain… Actually, who are you?\n");
        }
    // made by Jason and Amar
    public void welcomeTextPart2(Player player){

        System.out.println("Right, Captain " + player.getName() + "\n" +
                " Anyway, you seek many treasures of both ancient and modern significance and will encounter dangers" +
                " and conundrums along the way.\n " +
                "Are you, Captain " + player.getName() +  ", up for this daunting challenge?");
    }

    public void displayCommands() {
        System.out.println("List of commands:\n" +
                 "help| : The help command prints a list of all the commands available in the game, and what each command does\n" +
                "\n" +
                "|north or n| |south or s| |west or w| |east or e|: Theses commands are what are needed to move to different rooms in the game\n" +
                "\n" +
                "|pickup| : The pickup command allows you to add items to your inventory by entering the command followed by the name of the item you wish to add in your inventory. The item must be present in the room you are currently in to be added successfully to your inventory\n" +
                "\n" +
                "|drop| : The drop command allows you to remove items from your inventory by entering the command followed by the item you wish to remove from your inventory.\n" +
                "\n" +
                "|equip| : The equip command allows you to equip an item as long as it is in your inventory; once equipped, the item will remain in your inventory.\n" +
                "\n" +
                "|use| : The use command allows you to use an item as long as it is in your inventory; once used, the item will be removed from your inventory.\n" +
                "\n" +
                "|inventory| : The inventory command will display all the items currently in your inventory.\n" +
                "\n" +
                "|explore| : The explore command will display the current room's description, any items, monsters and puzzles in the room.\n" +
                "\n" +
                "|sonar| : The sonar command will display the room names and monsters of the rooms that are connected to the current room.\n" +
                "\n" +
                "|inspect| The inspect command, followed by the name of an item in your inventory, prints the item's description.\n" +
                "\n" +
                "|archive| : The archive command will send the designated item to the exhibit, removing it from your inventory.\n" +
                "\n" +
                "|start puzzle| : The start puzzle command followed by the name of the puzzle, will allow you to interact with the designated puzzle in the current room.\n" +
                "\n" +
                "|fight| : The fight command followed by the name of the monster you wish to fight will initiate a combat loop between you and the monster.\n" +
                "\n" +
                "|status| : The status command will display your name, current HP, current attack power, and current defense level.\n" +
                "\n" +
                "|exit game| :The exit game command will allow you to shut down the game. You will then be prompt to either save your game, or just exit the game.\n" +
                "\n" +
                "|map| : The map command will display the zone numbers and zone names found throughout the game.\n");
    }

    public void exitGameFirstPrompt(){
        System.out.println("Would you like to save your game before you exit? \n" +
                "(Please type yes to save, or no to exit without saving)");
    }


    public void exitGamePromptForNoPart1(){
        System.out.println("Are you sure you wouldn't like to save your progress?\n" +
                "(Please enter no if you would like to save your progress, \n " +
                "or yes to exit the game without saving)");
    }
    public void exitGamePromptForNoPart2(){
        System.out.println("Thanks for playing!");
    }

    public void invalidInputDuringGame(){
        System.out.println("The command you entered is invalid, please try again\n" +
                "{Remember, you can type help to get a list of  all the commands!}");
    }

    public void invalidInputForMenu(){
        System.out.println("I'm sorry, the command you entered was invalid, please try again");
    }


    public void puzzleMenuOptions(){
        System.out.println("While in the puzzle state, you may do the following: \n" +
                "Enter the 'get puzzle hint' command to display the current puzzle's hint.\n" +
                "Enter the 'solve puzzle' command to attempt to resolve the puzzle\n" +
                "Enter the 'exit puzzle' command to leave the current puzzle\n");
    }

    public void gameOverMessage(){
        System.out.println("You were lost to the depths of the sea, so long captain...");
    }

    public void displayMap(){
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                    Treasures of the Deep Map                                                            ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("__________________     __________________   ____________________                                                       ^" );
        System.out.println("| Man-of-war zone|-----| The Turtle zone|---|Enormous Clam Zone |                                     | <  West   -  North  - East >    |");
        System.out.println("|                |     |                |   |                   |                                                      |");
        System.out.println("|                |     |                |   |                   |                                                    South");
        System.out.println("|_______|________|     |________|_______|   |________|__________|                                                    -----");
        System.out.println("        |                       |                    |");
        System.out.println("________|___________   _________|_________   ________|_______________");
        System.out.println("|The Six-gill Zone|     |  Octopus Zone  |   |The Cookie-Cutter Zone|");
        System.out.println("|_________________|     |________________|   |_________|_____________|");
        System.out.println("                                                       |");
        System.out.println("                                             __________|______________" );
        System.out.println("                                             |     Oar weed Zone     |");
        System.out.println("                                             |___________|___________|");
        System.out.println("                                                         |");
        System.out.println("                        __________________  _____________|___________  ________________________" );
        System.out.println("                       |Macra Terror Zone|--|     The Spongy Zone   |--|     Sperm Whale Zone   |");
        System.out.println("                        |______|________|   |_______________________|  |___________|___________|");
        System.out.println("                               |                                                   |");
        System.out.println("                        _______|__________                               __________|_____________" );
        System.out.println("                        | Gulper Eel Zone|                               |   Hatchet Fish Zone   |");
        System.out.println("                        |________________|                               |_________|_____________|");
        System.out.println("                                                                                   |");
        System.out.println("                                                   __________________    __________|_____________    ________________________" );
        System.out.println("                                                   |Glass Sponge Zone|---|The Brittle Star Colony|---|     Narwhal Zone       |");
        System.out.println("                                                   |________|________|   |_______________________|   |_______________________|");
        System.out.println("                                                            |");
        System.out.println("                                                ____________|____________" );
        System.out.println("                                                |    Sea Spider Zone     |");
        System.out.println("                                                |___________|___________|");
        System.out.println("                                                            |");
        System.out.println("                       ____________________   ______________|_________   ________________________" );
        System.out.println("                       |Rat-Tail Fish Zone|---|     Angler Fish Zone  |--|   Colossal Squid Zone |");
        System.out.println("                       |__________________|   |____________|__________|  |_______________________|");
        System.out.println("                                                           |");
        System.out.println("                                               ____________|____________" );
        System.out.println("                                               |  Macra Terror Zone II |");
        System.out.println("                                               |_______________________|\n");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
    }
}


