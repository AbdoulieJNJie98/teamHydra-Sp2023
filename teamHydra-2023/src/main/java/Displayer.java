/*
 The Displayer class will be used to display out puts to the console that steam from the driver class game. So, any output the user would see
 due to an action caused by interacting with the game class directly, will be displayed from this class. However, this class will not be used
 to display every output to the console, since some outputs will reference information that is local to specific classes.
 */
import java.io.Serializable;

// Authors: @Abdoulie, Jason, Amara

public class Displayer implements Serializable {
    //private Player player = new Player();
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
        System.out.println("Quit Game");

    }

    public void mainMenuAfterInput() {
        System.out.println("Treasures of the Deep!");
        System.out.println("Main menu");
        System.out.println("----------");
        System.out.println("(Please enter one of the following commands)");
        System.out.println("Start Game");
        System.out.println("Exhibit");
        System.out.println("Quit Game");
    }
    // made by Jason and Amar
    public void welcomeTextPart1(){
            System.out.println("You are an avid adventurer and accomplished treasure hunter and historian.\n " +
                    "You know how to pilot a submarine and you even own one equipped with a claw for grabbing" +
                    " treasure and deterring wildlife and, though it seems like overkill, torpedo tubes should you want that extra firepower.\n " +
                    "You now fund an expedition into the depths of the ocean to uncover many items of monetary and historical significance.\n" +
                    " You are the one and only Captain…actually, who are you?\n");
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
                "|north or n| |south or s| |west or w| |east or e| \n" +
                "|The commands above are what are needed to move to different zones in the game|\n" +
                "|-----------------------------------------------------|\n" +
                "|pickup| |drop| |equip| |use| |inventory| |explore area| |sonar| |quit| |archive|\n" +
                "|start puzzle| |get puzzle hint| |fight| |status| |inspect monster| |solve puzzle| |exit game| |map| \n"+
                "|-------------------------------------------------------------------------------------|\n" +
                "|The pickup command allows you to add items to your inventory by entering the command\n" +
                "followed by the name of the item you wish to add in your inventory. The item must be present in the\n" +
                "room you are currently in to be added successfully to your inventory|\n" +
                "|The drop command allows you to remove items from your inventory by entering the command\n" +
                "followed by the item you wish to remove from your inventory.|\n" +
                "|The use command allows you to use an item as long as it is in your inventory; once used, the item will be removed\n" +
                "from your inventory|\n"+
                "|The inventory command will display all the items currently in your inventory\n|" +
                "|The explore area command will display the current room's description, any items, monsters and puzzles in the room|\n" +
                "|The sonar command will display the room names and monsters of the rooms that are connected to the current room|\n" +
                "|The inspect command will display the designated item's description, as long as it is in your inventory|\n" +
                "|The archive command will send the designated item to the exhibit, removing it from your inventory|\n" +
                "|The start puzzle command will allow you to interact with the designated puzzle in the current room|\n" +
                "|The get puzzle hint will display the hint associated with the current puzzle you are interacting with|\n" +
                "|The fight command followed by the name of the monster you wish to fight \n" +
                " will initiate a combat loop between you and the monster|\n" +
                "|The status command will display your name, current HP, current attack power, and current defense level|\n"+
                "|The inspect monster command will display the monster's name, current HP, current attack power, and current defense level|\n"+
                "|The solve puzzle command will allow you to initiate the process of resolving the current puzzle you are interacting with\n"+
                "|The exit game command will allow you to shut down the game. You will then be prompt to either save your game, or just exit the game.|\n" +
                "|The map command will display the zone numbers and zone names found throughout the game|\n" +
                "|--------------------------------------------------------------------------------------------------------------------|\n" );
    }

    public void exitGameFirstPrompt(){
        System.out.println("Would you like to save your game before you quit? \n" +
                "(Please type yes to save, or no to quit without saving)");
    }

    public void exitGamePromptForYes(){
        System.out.println("Please enter the name of a save file\n");
    }
    public void exitGamePromptForYesPartTwo(String saveFileName){
        System.out.println("File: " + saveFileName + "has been saved");
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
                "{Remember, you can type help to get a list of the commands!}");
    }

    public void invalidInputForMenu(){
        System.out.println("I'm sorry, the command you entered was invalid, please try again");
    }


    public void puzzleMenuOptions(){
        System.out.println("While in the puzzle state, you may do the following: \n" +
                "Enter the 'get puzzle hint' command to display the current puzzle's hint.\n" +
                "Enter the 'solve puzzle' command to attempt to resolve the puzzle\n");
    }

    public void gameOverMessage(){
        System.out.println("You were lost to the depths of the sea, so long captain...");
    }
}

