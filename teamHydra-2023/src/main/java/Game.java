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
            else if (player.getCurrentRoom().getMonsterID()>0){
                game.combat();
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
                    player.setCurrentRoom(player.getPreviousRoom());
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
                    player.getCurrentRoom().setMonsterID(-1);
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
            for (int i = 0; i < player.getplayerInventory().size(); i++) {
                if (player.getplayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getplayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getplayerInventory().get(i);
                }
            }
            if (item != null) {
                player.getplayerInventory().remove(item);
                currentMonster.setHealthPoints(currentMonster.getHealthPoints()-30);
                System.out.println("You fire a torpedo at the enemy");
            }

            if (item == null){
                System.out.println("You have no torpedoes!");
            }
        }
        if(playerInput.equalsIgnoreCase("Super Torpedo")) {
            for (int i = 0; i < player.getplayerInventory().size(); i++) {
                if (player.getplayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getplayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getplayerInventory().get(i);
                }
            }
            if (item != null) {
                player.getplayerInventory().remove(item);
                currentMonster.setHealthPoints(currentMonster.getHealthPoints()-60);
                System.out.println("You fire a super torpedo at the enemy");
            }

            if (item == null){
                System.out.println("You have no super torpedoes!");
            }
        }
        if(playerInput.equalsIgnoreCase("Repair")) {
            for (int i = 0; i < player.getplayerInventory().size(); i++) {
                if (player.getplayerInventory().get(i).getItemName().contains(playerInput) &&
                        player.getplayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getplayerInventory().get(i);
                }
            }
            if (item == null){
                System.out.println("You have no repair kits!");
            }
            if (item != null) {
                if (player.getHealthPoints() < player.maximumHP) {
                    player.getplayerInventory().remove(item);
                    player.setHealthPoints(player.getHealthPoints() + (player.maximumHP - player.getHealthPoints()));
                    System.out.println("You used a repair kit");
                }
                else{
                    System.out.println("You are already max HP!");
                    item = null;
                }
            }


        }


    }
    }






