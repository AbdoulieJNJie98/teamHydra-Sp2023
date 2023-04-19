import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/*
The Game class is a class that starts the game itself, and accepts the player's input with the use of a
switch statement that is inside a while loop that verifies the player's input so that they may navigate through the map of the game
author @Abdoulie
 */
public class Game implements Serializable {
    private Map map = new Map();
    private Scanner input = new Scanner(System.in);

    private Displayer display = new Displayer();
    private static Game game = new Game();

    private Exhibit exhibit = new Exhibit();

    private Monster  monster = new Monster();

    private Player player = null;



    public static void main(String[] args) {
        game.mainMenu(); // call to the start game method that will initialize a run of the game
    }

    public void mainMenu(){
        display.mainMenu();
        String menuOptions = input.nextLine();
        // boolean used to dictate if the user will create new game, load a save game file, or enter the exhibit
        boolean validMenuOptionEntered = false;

        // while loop that contains a decision statements used to determine if the user will create a new game that
        while (!validMenuOptionEntered){
            if(menuOptions.equalsIgnoreCase("New Game")){
                game.createNewGame();
                validMenuOptionEntered = true;
            } else if (menuOptions.equalsIgnoreCase("Load Game")) {
                game.loadSaveFile(map, player, exhibit.getItemsInExhibit());
                validMenuOptionEntered = true;
//            }else if (menuOptions.equalsIgnoreCase("Exhibit")) {
//                game.viewExhibit();
//                validMenuOptionEntered = true;
            }

        }

    }
    private void startGame() {
        String playerInput = input.nextLine();
        String[] playerInputParts = playerInput.split(" ");


        Boolean playing = true; // boolean used to dictate when the game will continue or end.

        while (playing) { // while loop that continues the game as long the boolean variable "playing" is true

            if (playerInputParts[0].equalsIgnoreCase("help")) {
                display.displayCommands();
            } else if (playerInputParts[0].equalsIgnoreCase("north") || playerInputParts[0].equalsIgnoreCase("n")
                    || playerInputParts[0].equalsIgnoreCase("south") || playerInputParts[0].equalsIgnoreCase("s")
                    || playerInputParts[0].equalsIgnoreCase("east") || playerInputParts[0].equalsIgnoreCase("e")
                    || playerInputParts[0].equalsIgnoreCase("west") || playerInputParts[0].equalsIgnoreCase("w")) {
                playerInput = game.makeCommand(playerInputParts);
                player.move(playerInput);
                player.checkIfRoomHasBeenVisited();
            } else if (playerInputParts[0].equalsIgnoreCase("quit")) {
                quitGame(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("pickup ") && playerInputParts.length > 1) {
                playerInput = game.makeCommand(playerInputParts);
                player.pickUpItem(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("drop ") && playerInputParts.length > 1) {
                playerInput = game.makeCommand(playerInputParts);
                player.dropItem(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("inspect ") && playerInputParts.length > 1) {
                playerInput = game.makeCommand(playerInputParts);
                player.inspectItem(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("explore")) {
                player.exploreRoom();
            } else if (playerInputParts[0].equalsIgnoreCase("inventory")) {
                player.getCurrentInventory(playerInput);
            }
            else if (playerInputParts[0].equalsIgnoreCase("archive ") && playerInputParts.length > 1) {
                playerInput = game.makeCommand(playerInputParts);
                player.archive(playerInput);

            } else if ((playerInputParts[0].equalsIgnoreCase("Solve Puzzle"))) {
                //player.solvePuzzle(playerInput);
            }
            else if ((playerInputParts[0].equalsIgnoreCase("Fight ") && playerInputParts.length > 1)) {
                playerInput = game.makeCommand(playerInputParts);
                //player.solvePuzzle(playerInput);
            }
            else if ((playerInputParts[0].equalsIgnoreCase("Save Game"))){
                game.saveGame(map, player, exhibit.getItemsInExhibit());
            }
        }
    }

    // method used to being array list of string back together to form one command
    public String makeCommand(String [] playerInputParts){
        // string used to send the full command back to the method the player activates
        // based on their input
        String command = " ";
        // for loop used to add each part of the string array list and forms a command
        for(int i = 0; i < playerInputParts.length; i++){
            command+= playerInputParts[i] + " ";
        }
        return command;
    }

    public void quitGame(String playerInput) {
        display.exitGameFirstPrompt();
        playerInput = input.nextLine().toLowerCase();
        if (playerInput.equalsIgnoreCase("yes")) {
            String saveFileName = "";
            display.exitGamePromptForYes();
            saveFileName = input.nextLine().toLowerCase();
            display.exitGamePromptForYesPartTwo(saveFileName);
            System.out.println("Thanks for playing!");
            System.exit(0);
        } else if (playerInput.equalsIgnoreCase("no")) {
            display.exitGamePromptForNoPart1();
            playerInput = input.nextLine().toLowerCase();
            // if statement used to send the user back to save game process if they enter no
            if(playerInput.equalsIgnoreCase("no")){
                String saveFileName = "";
                display.exitGamePromptForYes();
                saveFileName = input.nextLine().toLowerCase();
                display.exitGamePromptForYesPartTwo(saveFileName);
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            else if(playerInput.equalsIgnoreCase("yes")) {
                display.exitGamePromptForNoPart2();
                System.exit(0);
            }
        }
    }

    public void createNewGame(){
        Map gameMap = map;
        gameMap.readFiles();
        Player player = new Player();
        Exhibit exhibit = new Exhibit();
        saveGame(gameMap, player, exhibit.getItemsInExhibit());
    }
    public void saveGame(Map gameMap, Player player, ArrayList<Items> itemsInExhibit){
        System.out.println("Please enter the name of your save");
        String saveGameFile = input.nextLine();
    }

    public void loadSaveFile(Map gameMap, Player player, ArrayList <Items>itemsInExhibit){

    }

    public void gameOver(){

    }
    // this method will be used to randomize the puzzleID number found in each room that has a 0 value for its puzzleID,
    // if the puzzleID is not zero, the method will skip randomizing the puzzle's ID number
    public void randomizePuzzleRoomID(){

    }

}


