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
            }else if (menuOptions.equalsIgnoreCase("Exhibit")) {
                exhibit.displayExhibit();
                exhibit.exhibitCommands(menuOptions);
                validMenuOptionEntered = true;
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

    public void combat(){

        while (!player.getCurrentRoom().getMonstersInRoom().isEmpty()) {
            Monster currentMonster = player.getCurrentRoom().getMonstersInRoom().get(0);
            System.out.println("You have entered combat with " + currentMonster.getName());

            System.out.println("What will you do? Your options are Attack, Flee, Repair, Check, and Status ");
            String playerInput = input.nextLine();
            if (playerInput.equalsIgnoreCase("attack")){
                int damage = player.getAttackStat() - currentMonster.getDefenseStat();
                if (damage > 0) {
                    currentMonster.setHealthPoints(currentMonster.getHealthPoints() - damage);
                    System.out.println("You dealt " + damage + " damage to " + currentMonster.getName());
                    System.out.println("The " + currentMonster.getName() + " now has " + currentMonster.getHealthPoints() + " health points remaining");
                } else {
                    System.out.println("Your attack did no damage to the " + currentMonster.getName());
                }
            }
            else if (playerInput.equalsIgnoreCase("Torpedo")) {
                game.useItemInCombat(playerInput, currentMonster);

            }
            else if (playerInput.equalsIgnoreCase("Super Torpedo")){
                game.useItemInCombat(playerInput, currentMonster);
            }

            else if (playerInput.equalsIgnoreCase("flee")) {
                System.out.println("You have successfully fled!");
                player.getCurrentRoom().setRoomID(player.getPreviousRoom().getRoomID());
              //  player.setCurrentRoom(player.getPreviousRoom());
            }
            else if (playerInput.equalsIgnoreCase("repair")) {
                game.useItemInCombat(playerInput, null);
            }
            else if (playerInput.equalsIgnoreCase("check")) {
                System.out.println(currentMonster.getName() + "'s HP: " + currentMonster.getHealthPoints());
                System.out.println(currentMonster.getName() + "'s Attack Stat: " + currentMonster.getAttackStat());
                System.out.println(currentMonster.getName() + "'s Defense Stat: " + currentMonster.getDefenseStat());
            }
            else if (playerInput.equalsIgnoreCase("status")) {
                System.out.println("Your HP: " + player.getHealthPoints());
                System.out.println("Your Attack Stat: " + player.getAttackStat());
                System.out.println("Your Defense Stat: " + player.getDefenseStat());
            }
            else {
                System.out.println("Invalid Command");
            }

            // Monster attacks after player's turn
            if (currentMonster.getHealthPoints() > 0) {
                int monsterDamage = currentMonster.getAttackStat() - player.getDefenseStat();
                if (monsterDamage > 0) {
                    player.setHealthPoints(player.getHealthPoints() - monsterDamage);
                    System.out.println(currentMonster.getName() + " dealt " + monsterDamage + " damage to you");
                } else {
                    System.out.println(currentMonster.getName() + "'s attack did no damage to you!");
                }
            }

            if (currentMonster.getHealthPoints()<= 0) {
                currentMonster.setMonsterID(-1);
                player.getCurrentRoom().getMonstersInRoom().remove(0);
          //      player.getCurrentRoom().setMonstersInRoom(-1);
                break;
            }
            if (player.getHealthPoints()<=0) {
                System.out.println("Your HP as reached 0 ");
                System.exit(0);

            }
        }

    }
    public void useItemInCombat(String playerInput, Monster currentMonster) {
        // item variable used to hold the item the user is attempting to use
        Items item = null;
        // for loop used to determine if the player has the item they're attempting to use in their inventory
        if(playerInput.equalsIgnoreCase("Torpedo")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null) {
                player.getPlayerInventory().remove(item);
                currentMonster.setHealthPoints(currentMonster.getHealthPoints()-30);
                System.out.println("You fire a torpedo at the enemy");
            }

            if (item == null){
                System.out.println("You have no torpedoes!");
            }
        }
        if(playerInput.equalsIgnoreCase("Super Torpedo")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null) {
                player.getPlayerInventory().remove(item);
                currentMonster.setHealthPoints(currentMonster.getHealthPoints()-60);
                System.out.println("You fire a super torpedo at the enemy");
            }

            if (item == null){
                System.out.println("You have no super torpedoes!");
            }
        }
        if(playerInput.equalsIgnoreCase("Repair")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item == null){
                System.out.println("You have no repair kits!");
            }
            if (item != null) {
                if (player.getHealthPoints() < player.maximumHP) {
                    player.getPlayerInventory().remove(item);
                    player.setHealthPoints(player.getHealthPoints() + (player.maximumHP - player.getHealthPoints()));
                    System.out.println("You used a repair kit");
                }
                else{
                    System.out.println("You are already max HP!");
                    item = null;
                }
            }
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().contains(playerInput) &&
                        !player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    System.out.println("This item cannot be used");
                }
                if (!player.getPlayerInventory().get(i).getItemName().contains(playerInput)){
                    System.out.println("Invalid Item");
                }
            }


        }


    }
    public void useItemOutsideOfCombat(String playerInput) {
        // item variable used to hold the item the user is attempting to use
        Items item = null;
        System.out.println("What item would you like to use?");
        playerInput = input.nextLine();
        // for loop used to determine if the player has the item they're attempting to use in their inventory
        if (playerInput.equalsIgnoreCase("Repair Kit")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item == null) {
                System.out.println("You have no repair kits!");
            }
            if (item != null) {
                if (player.getHealthPoints() < player.maximumHP) {
                    player.getPlayerInventory().remove(item);
                    player.setHealthPoints(player.getHealthPoints() + (player.maximumHP - player.getHealthPoints()));
                    System.out.println("You used a repair kit");
                } else {
                    System.out.println("You are already max HP!");
                    item = null;
                }
            }

        }
        if (playerInput.equalsIgnoreCase("Antikythera mechanism")) {
            Puzzles Antikythera = player.getCurrentRoom().getPuzzlesInRoom().get(0);
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item == null) {
                System.out.println("You do not have the Antikythera mechanism in your inventory");
            }
            if (item != null) {
                if (player.getCurrentRoom().getRoomID() != Antikythera.getPuzzleID()) {
                    Antikythera.getPuzzleDescription();
                } else {
                    item.setItemType("Treasure");
                    Antikythera.setPuzzleSolvedStatus(true);
                    item = null;
                }
            }

        }
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {
            if (player.getPlayerInventory().get(i).getItemName().contains(playerInput) &&
                    !player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                System.out.println("This item cannot be used");
            }
            if (!player.getPlayerInventory().get(i).getItemName().contains(playerInput)){
                System.out.println("Invalid Item");
            }
        }
    }
}


