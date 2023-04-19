/*
 The Displayer class will be used to display out puts to the console that steam from the driver class game. So, any output the user would see
 due to an action caused by interacting with the game class directly, will be displayed from this class. However, this class will not be used
 to display every output to the console, since some outputs will reference information that is local to specific classes.
 */
import java.io.Serializable;

public class Displayer implements Serializable {
    //    private Player player = new Player();
    String menuInput = "";

    public Displayer() {
    }

    // method main menu used to display when the player starts the game
    public void mainMenu() {
        System.out.println("Treasures of the Deep");
        System.out.println("----------------------");
        System.out.println("Main menu");
        System.out.println("----------");
        System.out.println("(Please enter one of the following commands)");
        System.out.println("new game");
        System.out.println("load save");
        System.out.println("exhibit");

        // if statement that is used to determine what to show based on the user's input
        if (menuInput.equalsIgnoreCase("New Game")) {

            System.out.println("Please enter any of the following keys to traverse the world:\n" +
                    " |north or n|\n |south or s| \n |west or w|\n |east or e| \n" +
                    "{Type in the command help to get a list of the commands used for the game!}\n" +
                    "{Type in the command  'exit' or press the x key to exit the game}");

            System.out.println("Welcome to the Sea!");

        }
        else if (menuInput.equals("load save")) {
        }
    }

    public void displayCommands() {
        System.out.println("List of commands:\n" +
                "|north or n| |south or s| |west or w| |east or e| \n" +
                "|The commands above are what are needed to move to different rooms in the game|\n" +
                "|-----------------------------------------------------|\n" +
                "|add| |drop| |inventory| |explore| |inspect|\n" +
                "|-----------------------------------------------------|\n" +
                "|The add command allows you to add items to your inventory|\n" +
                "|The drop command allows you to drop items from your inventory|\n" +
                "|The inventory command will display all the items in your current inventory\n|" +
                "|The explore command will display the room description and any items in the room\n|" +
                "|The inspect command will display the designated item's description, as long as it is in your inventory|" +
                "|-----------------------------------------------------|\n" +
                "|exit or x|\n" +
                "|The command above is used to exit the game|" +
                "|-----------------------------------------------------|");
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

    public void invalidInputForMainMenu(){
        System.out.println("The command you entered is invalid, please try again\n" +
                "{Remember, you can type help to get a list of the commands!}");
    }

    public void ifRoomHasBeenVisited(){
        System.out.println("This room seems familiar");
    }
    public void ifPlayerInventoryIsEmpty(){
        System.out.println("There is nothing currently in your inventory");
    }




}

