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
        String [] menuOptionsPart = menuOptions.split("");
        // boolean used to dictate if the user will create new game, load a save game file, or enter the exhibit
        boolean validMenuOptionEntered = false;

        // while loop that contains a decision statements used to determine if the user will create a new game that
        while (!validMenuOptionEntered){
            if(menuOptionsPart[0].equalsIgnoreCase("New") && menuOptionsPart[1].equalsIgnoreCase("Game")){
                game.createNewGame();
                validMenuOptionEntered = true;
            } else if (menuOptionsPart[0].equalsIgnoreCase("Load") && menuOptionsPart[1].equalsIgnoreCase("Game")) {
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

        // boolean used to determine if the player is currently fighting a monster
        // this variable will be used to differentiate between when the player uses the
        // use command in and outside of combat
        Boolean combatState = false;


        Boolean playing = true; // boolean used to dictate when the game will continue or end.

        while (playing) { // while loop that continues the game as long the boolean variable "playing" is true

            if (playerInputParts[0].equalsIgnoreCase("help")) {
                display.displayCommands();
            } else if (playerInputParts[0].equalsIgnoreCase("north") || playerInputParts[0].equalsIgnoreCase("n")
                    || playerInputParts[0].equalsIgnoreCase("south") || playerInputParts[0].equalsIgnoreCase("s")
                    || playerInputParts[0].equalsIgnoreCase("east") || playerInputParts[0].equalsIgnoreCase("e")
                    || playerInputParts[0].equalsIgnoreCase("west") || playerInputParts[0].equalsIgnoreCase("w")) {
                playerInput = game.getWord(playerInputParts);
                player.move(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("quit")) {
                quitGame(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("pickup ") && playerInputParts.length > 1) {
                playerInput = game.getWord(playerInputParts);
                player.pickUpItem(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("drop ") && playerInputParts.length > 1) {
                playerInput = game.getWord(playerInputParts);
                player.dropItem(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("inspect ") && playerInputParts.length > 1) {
                playerInput = game.getWord(playerInputParts);
                player.inspectItem(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("inspect") && playerInputParts[1].equalsIgnoreCase("area")) {
                player.inspectArea();
            } else if (playerInputParts[0].equalsIgnoreCase("inventory")) {
                player.getCurrentInventory();
            }
            else if (playerInputParts[0].equalsIgnoreCase("archive ") && playerInputParts.length > 1) {
                playerInput = game.getWord(playerInputParts);
                player.archive(playerInput);
            } else if (playerInputParts[0].equalsIgnoreCase("sonar")) {
                player.sonar();
            }
            else if ((playerInputParts[0].equalsIgnoreCase("Start"))&& playerInputParts[1].equalsIgnoreCase("Puzzle")) {
                game.startPuzzle(player, playerInput, playerInputParts);
            }
            else if ((playerInputParts[0].equalsIgnoreCase("Equip "))&& playerInputParts.length > 1) {
                playerInput = game.getWord(playerInputParts);
                player.equippedItem(playerInput);
            }
            else if ((playerInputParts[0].equalsIgnoreCase("Fight ") && playerInputParts.length > 1)) {
                playerInput = game.getWord(playerInputParts);
                //game.startCombat(playerInput);
            }
            else if ((playerInputParts[0].equalsIgnoreCase("status"))){
                player.getStatusForPlayer(player);
            }
            else if ((playerInputParts[0].equalsIgnoreCase("Save"))&& playerInputParts[1].equalsIgnoreCase("Game")){
                game.saveGame(map, player, exhibit.getItemsInExhibit());
            }
        }
    }

    // method used to being array list of string back together to form one command
    public String getWord(String [] playerInputParts){
        // string used to send the full word back to the method the player activates
        // based on their input
        String word = " ";
        // for loop used to add each part of the string array list and forms a word
        for(int i = 0; i < playerInputParts.length; i++){
            word+= playerInputParts[i] + " ";
        }
        return word;
    }

    public void quitGame(String playerInput) {
        display.exitGameFirstPrompt();
        playerInput = input.nextLine();
        if (playerInput.equalsIgnoreCase("yes")) {
            String saveFileName = "";
            display.exitGamePromptForYes();
            saveFileName = input.nextLine();
            display.exitGamePromptForYesPartTwo(saveFileName);
            System.out.println("Thanks for playing!");
            System.exit(0);
        } else if (playerInput.equalsIgnoreCase("no")) {
            display.exitGamePromptForNoPart1();
            playerInput = input.nextLine();
            // if statement used to send the user back to save game process if they enter no
            if(playerInput.equalsIgnoreCase("no")){
                String saveFileName = "";
                display.exitGamePromptForYes();
                saveFileName = input.nextLine();
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
        Player player = new Player();
        setPlayerName(player);
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
    // method used to activate puzzle state and will allow the player to access the current room's puzzle aspect
    public void startPuzzle(Player player, String playerInput,String [] playerInputParts){
        // Puzzles variable used to make sure the puzzle name the player entered is valid
        Puzzles puzzle = new Puzzles();
        // for loop used to determine if the puzzleName is in the current room, and if it is,
        // it will set puzzle equal to the puzzle in the room
        for(int i = 0; i < player.getCurrentRoom().getPuzzlesInRoom().size(); i++){
            if(player.getCurrentRoom().getPuzzlesInRoom().get(i).getPuzzleName().equalsIgnoreCase(playerInput)){
                puzzle = player.getCurrentRoom().getPuzzlesInRoom().get(i);

            }
            else{
                System.out.println("The puzzle you attempted to start was not found in this room");
            }
        }
        // the following statement will display the options available when a player successfully starts a puzzle\
        display.puzzleMenuOptions();
        System.out.println(puzzle.getPuzzleDescription());
        playerInput = input.nextLine();

        // if else statement used to handel the puzzle menu options
        if(playerInputParts[0].equalsIgnoreCase("get") && playerInputParts[1].equalsIgnoreCase("puzzle")
                && playerInputParts[2].equalsIgnoreCase("hint")){
            System.out.println(puzzle.getPuzzleHint());
        }
        else if(playerInputParts[0].equalsIgnoreCase("solve") && playerInputParts[1].equalsIgnoreCase("puzzle")){
            puzzle.resolvePuzzle(player);
        }
    }

    // method used to set the player name when a new game is created
    public void  setPlayerName(Player player ){
        System.out.println("Please enter your name captain: ");
        String playerName = input.nextLine();
        player.setName(playerName);
    }

}
