import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 Authors: @Abdoulie, Jason, Barbra
 */
public class Game implements Serializable {
    private Map map = new Map();
    private Scanner input = new Scanner(System.in);

    private Displayer display = new Displayer();
    private static Game game = new Game();

    private Exhibit exhibit = new Exhibit();


    private Player player = new Player();

    // booleans used to signify which game state the player is currently in
    private boolean exploreState;
    private boolean combatState;
    private boolean puzzleState;
    private boolean exhibitState;
    private boolean mainMenuBeforeInputState;
    private boolean quitGameState;
    private boolean gameOverState;
    private boolean mainMenuAfterInputState;


    // default constructor
    public Game() {

    }


    public static void main(String[] args) {
        game.firstMainMenu(); // call to the start game method that will initialize a run of the game
    }

    public void firstMainMenu() {
        display.mainMenuBeforeInput();
        // statement used to activate current game state
        mainMenuBeforeInputState = true;
        String menuOptions = input.nextLine();
        String[] menuOptionsPart = menuOptions.split(" ");
        // if statement used    to catch invalid inputs
        // while loop that contains decision statements used to determine if the user will create a new game, or load a game
        while ((mainMenuBeforeInputState)) {
            try{
            if (menuOptionsPart[0].equalsIgnoreCase("New") && menuOptionsPart[1].equalsIgnoreCase("Game")) {
                mainMenuBeforeInputState = false;
                mainMenuAfterInputState = true;
                game.createNewGame();
            } else if (menuOptionsPart[0].equalsIgnoreCase("Load") && menuOptionsPart[1].equalsIgnoreCase("Save")) {
                mainMenuBeforeInputState = false;
                mainMenuAfterInputState = true;
                game.loadSaveFile();
            } else if (menuOptionsPart[0].equalsIgnoreCase("Quit") && menuOptionsPart[1].equalsIgnoreCase("Game")) {
                display.exitGamePromptForNoPart2();
                System.exit(0);
            } else {
                display.invalidInputForMenu();
                menuOptions = input.nextLine();
                menuOptionsPart = menuOptions.split(" ");

            }

        }catch (ArrayIndexOutOfBoundsException AOB) {
                display.invalidInputForMenu();
                menuOptions = input.nextLine();
                menuOptionsPart = menuOptions.split(" ");
            }
        }


    }

    public void secondMainMenu() {
        // boolean used to dictate if the user enters a valid input
        display.mainMenuAfterInput();
        String menuOptions = input.nextLine();
        String[] menuOptionsPart = menuOptions.split(" ");
        // while loop that contains decision statements used to determine if the user will create a new game, or load a game
        while ((mainMenuAfterInputState)) {
            try{
            if (menuOptionsPart[0].equalsIgnoreCase("Start") && menuOptionsPart[1].equalsIgnoreCase("Game")) {
                mainMenuAfterInputState = false;
                exploreState = true;
                game.startGame(player, map, exhibit.getItemsInExhibit());

            } else if (menuOptionsPart[0].equalsIgnoreCase("Exhibit")) {
                mainMenuAfterInputState = false;
                exhibitState = true;
                game.startExhibit(exhibit.getItemsInExhibit());
            } else if (menuOptionsPart[0].equalsIgnoreCase("Quit") && menuOptionsPart[1].equalsIgnoreCase("Game")) {
                display.exitGamePromptForNoPart2();
                System.exit(0);
            } else {
                display.invalidInputForMenu();
                menuOptions = input.nextLine();
                menuOptionsPart = menuOptions.split(" ");
            }
            }catch (ArrayIndexOutOfBoundsException AOB) {
                display.invalidInputForMenu();
                menuOptions = input.nextLine();
                menuOptionsPart = menuOptions.split(" ");
            }
        }

    }

    private void startGame(Player player, Map gameMap, ArrayList<Items> itemInExhibit) {

        System.out.println("\nWelcome to the depths of the sea!\n" +
                "To start explore, please enter the direction you would like to go!");
        System.out.println("Your currently location: " + player.getCurrentRoom().getRoomName());

        while ((exploreState)) { // while loop that continues the game as long the boolean variable "playing" is true
            String playerInput = input.nextLine();
            String[] playerInputParts = playerInput.split(" ");
            try {

                if (playerInputParts[0].equalsIgnoreCase("help")) {
                    display.displayCommands();
                } else if (playerInputParts[0].equalsIgnoreCase("north") || playerInputParts[0].equalsIgnoreCase("n")
                        || playerInputParts[0].equalsIgnoreCase("south") || playerInputParts[0].equalsIgnoreCase("s")
                        || playerInputParts[0].equalsIgnoreCase("east") || playerInputParts[0].equalsIgnoreCase("e")
                        || playerInputParts[0].equalsIgnoreCase("west") || playerInputParts[0].equalsIgnoreCase("w")) {
                    player.move(playerInput);
                } else if (playerInputParts[0].equalsIgnoreCase("Exit") && playerInputParts[1].equalsIgnoreCase("Game")) {
                    exploreState = false;
                    quitGameState = true;
                    quitGame(playerInput, map, player, exhibit.getItemsInExhibit());
                } else if (playerInputParts[0].equalsIgnoreCase("pickup") && playerInputParts.length > 1) {
                    playerInput = getItemName(playerInputParts);
                    player.pickUpItem(playerInput, player.getCurrentRoom(), player);
                } else if (playerInputParts[0].equalsIgnoreCase("drop") && playerInputParts.length > 1) {
                    playerInput = getItemName(playerInputParts);
                    player.dropItem(playerInput, player, player.getCurrentRoom());
                } else if (playerInputParts[0].equalsIgnoreCase("inspect") && playerInputParts.length > 1) {
                    playerInput = getItemName(playerInputParts);
                    player.inspectItem(playerInput, player);
                } else if (playerInputParts[0].equalsIgnoreCase("explore") && playerInputParts[1].equalsIgnoreCase("area")) {
                    player.exploreArea();
                } else if (playerInputParts[0].equalsIgnoreCase("inventory")) {
                    player.getCurrentInventory();
                } else if (playerInputParts[0].equalsIgnoreCase("archive") && playerInputParts.length > 1) {
                    playerInput = getItemName(playerInputParts);
                    player.archive(playerInput, exhibit.getItemsInExhibit(), player);
                } else if (playerInputParts[0].equalsIgnoreCase("sonar")) {
                    player.sonar();
                } else if (playerInputParts[0].equalsIgnoreCase("use") && playerInputParts.length > 1) {
                    playerInput = getItemName(playerInputParts);
                    game.useItemOutsideOfCombat(playerInput, player);
                } else if ((playerInputParts[0].equalsIgnoreCase("Start")) && playerInputParts[1].equalsIgnoreCase("Puzzle") && playerInputParts.length > 2) {
                    playerInput = getPuzzleName(playerInputParts);
                    exploreState = false;
                    puzzleState = true;
                    game.startPuzzle(player, playerInput);
                } else if ((playerInputParts[0].equalsIgnoreCase("Equip")) && playerInputParts.length > 1) {
                    playerInput = getItemName(playerInputParts);
                    player.equippedItem(playerInput, player);
                } else if ((playerInputParts[0].equalsIgnoreCase("Fight") && playerInputParts.length > 1)) {
                    playerInput = getItemName(playerInputParts);
                    exploreState = false;
                    combatState = true;
                    combat(playerInput, player, map, exhibit.getItemsInExhibit());
                } else if ((playerInputParts[0].equalsIgnoreCase("status"))) {
                    player.getStatusForPlayer(player);
                } else if ((playerInputParts[0].equalsIgnoreCase("Save")) && playerInputParts[1].equalsIgnoreCase("Game")) {
                    game.saveGame(gameMap, player, exhibit.getItemsInExhibit());
                } else if ((playerInputParts[0].equalsIgnoreCase("map"))) {
                    display.displayMap();
                    System.out.println("Your currently location: " + player.getCurrentRoom().getRoomName());
                } else {
                    display.invalidInputDuringGame();
                }
            } catch (ArrayIndexOutOfBoundsException AOB) {
                display.invalidInputDuringGame();
            }
        }
    }

    // method used to being array list of string back together to form one command
    public String getItemName(String[] playerInputParts) {
        // string used to send the full word back to the method the player activates
        // based on their input
        String itemName = "";
        // for loop used to add each part of the string array list and forms a word
        for (int i = 1; i < playerInputParts.length; i++) {
            itemName += playerInputParts[i] + " ";
        }
        return itemName;
    }

    public String getPuzzleName(String[] playerInputParts) {
        // string used to send the full word back to the method the player activates
        // based on their input
        String puzzleName = "";
        // for loop used to add each part of the string array list and forms a word
        for (int i = 2; i < playerInputParts.length; i++) {
            puzzleName += playerInputParts[i] + " ";
        }
        return puzzleName;
    }

    public void quitGame(String playerInput, Map gameMap, Player player, ArrayList<Items> exhibit) {
        while (quitGameState) {
            display.exitGameFirstPrompt();
            playerInput = input.nextLine();
            if (playerInput.equalsIgnoreCase("yes")) {
                //display.exitGamePromptForYes();
                saveGame(gameMap, player, exhibit);
                System.out.println("Thanks for playing!");
                quitGameState = false;
                System.exit(0);
            } else if (playerInput.equalsIgnoreCase("no")) {
                display.exitGamePromptForNoPart1();
                playerInput = input.nextLine();
                // if statement used to send the user back to save game process if they enter no
                if (playerInput.equalsIgnoreCase("no")) {
                    // display.exitGamePromptForYes();
                    saveGame(gameMap, player, exhibit);
                    System.out.println("Thanks for playing!");
                    quitGameState = false;
                    System.exit(0);
                } else if (playerInput.equalsIgnoreCase("yes")) {
                    display.exitGamePromptForNoPart2();
                    quitGameState = false;
                    System.exit(0);
                }
            } else {
                display.invalidInputForMenu();


            }
        }
    }

    // abdoulie
    public void createNewGame() {
        Map gameMap = map;
        gameMap.readRoomFile();
        gameMap.readItemFile();
        gameMap.readPuzzleFile();
        gameMap.readMonsterFile();
        Exhibit exhibit = new Exhibit();
        Player player = new Player();
        display.welcomeTextPart1();
        setPlayerName(player);
        display.welcomeTextPart2(player);
        exploreState = true;
        startGame(player, gameMap, exhibit.getItemsInExhibit());

    }

    public void saveGame(Map gameMap, Player player, ArrayList<Items> itemsInExhibit) {
        System.out.println("Please enter the name of your save file");
        String saveGameFile = input.nextLine();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveGameFile + ".dat"));
            out.writeObject(gameMap);
            out.writeObject(player);
            out.writeObject(itemsInExhibit);
            out.close();
            System.out.println("Game saved to " + saveGameFile);
        } catch (IOException e) {
            System.out.println("An error occured when trying to save " + e.getMessage());
        }
    }

    public ArrayList<Object> loadSaveFile() {
        ArrayList<Object> result = new ArrayList<>();
        while (true) {
            System.out.println("Please enter save file name");
            String saveFileName = input.nextLine();
            try {
                ObjectInputStream load = new ObjectInputStream(new FileInputStream(saveFileName + ".dat"));
                while (true) {
                    Object obj = load.readObject();
                    result.add(obj);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("An error occurred: " + e.getMessage());
                firstMainMenu();
            } catch (EOFException e) {
                System.out.println(saveFileName + " load complete");
                secondMainMenu();
                return result;
            } catch (IOException e) {
                System.out.println("An error occurred when trying to load save " + e.getMessage());
                firstMainMenu();
            }
        }
    }





    // method used to handles what happens when the player loses all their HP
    public void gameOver(){
        display.gameOverMessage();
        System.exit(0);

    }
    // method used to activate puzzle state and will allow the player to access the current room's puzzle aspect
    public void startPuzzle(Player player, String puzzleName) {
        // Puzzles variable used to make sure the puzzle name the player entered is valid
        Puzzles puzzle = new Puzzles();

        // for loop used to determine if the puzzleName is in the current room, and if it is,
        // it will set puzzle equal to the puzzle in the room
        for (int i = 0; i < player.getCurrentRoom().getPuzzlesInRoom().size(); i++) {
            if (player.getCurrentRoom().getPuzzlesInRoom().get(i).getPuzzleName().equalsIgnoreCase(puzzleName)) {
                puzzle = player.getCurrentRoom().getPuzzlesInRoom().get(i);

            } else {
                System.out.println("The puzzle you attempted to start was not found in this room");
                puzzleState = false;
                exploreState = true;
                startGame(player, map, exhibit.getItemsInExhibit());
            }
        }
        // the following statement will display the options available when a player successfully starts a puzzle\
        display.puzzleMenuOptions();
        System.out.println(puzzle.getPuzzleDescription());
        String playerInput = input.nextLine();
        String[] playerInputParts = playerInput.split(" ");
        // while loop used to keep player in the puzzle state
        while (puzzleState) {
            try {
                // if else statement used to handel the puzzle menu options
                if (playerInputParts[0].equalsIgnoreCase("Get") && playerInputParts[1].equalsIgnoreCase("Puzzle")
                        && playerInputParts[2].equalsIgnoreCase("Hint")) {
                    System.out.println(puzzle.getPuzzleHint());
                    System.out.println("You have returned to the puzzle state. (Input any of the puzzle state commands to interact with the puzzle)");
                    playerInput = input.nextLine();
                    playerInputParts = playerInput.split(" ");
                } else if (playerInputParts[0].equalsIgnoreCase("Solve") && playerInputParts[1].equalsIgnoreCase("Puzzle")) {
                    puzzle.resolvePuzzle(player);
                    System.out.println("You have returned to the puzzle state. (Input any of the puzzle state commands to interact with the puzzle)");
                    playerInput = input.nextLine();
                    playerInputParts = playerInput.split(" ");
                } else if (playerInputParts[0].equalsIgnoreCase("Exit") && playerInputParts[1].equalsIgnoreCase("Puzzle")) {
                    System.out.println("Exiting puzzle...");
                    puzzleState = false;
                    exploreState = true;
                    startGame(player, map, exhibit.getItemsInExhibit());
                } else {
                    display.invalidInputForMenu();
                    playerInput = input.nextLine();
                    playerInputParts = playerInput.split(" ");
                }
            } catch (ArrayIndexOutOfBoundsException AOB) {
                display.invalidInputForMenu();
                playerInput = input.nextLine();
                playerInputParts = playerInput.split(" ");
            }
        }
    }

    // method used to set the player name when a new game is created
    public void  setPlayerName(Player player ){
        System.out.println("(Please enter your name captain)");
        String playerName = input.nextLine();
        player.setName(playerName);
    }

    public void combat(String monsterName, Player player, Map map, ArrayList<Items> exhibit ){
        if(player.getCurrentRoom().getMonstersInRoom().get(0).getName().equalsIgnoreCase(monsterName)) {

            while (combatState) {
                Monster currentMonster = player.getCurrentRoom().getMonstersInRoom().get(0);
                System.out.println("You have entered combat with " + currentMonster.getName());

                System.out.println("What will you do? Your options are Attack, Use, Flee, Check, Status, Inventory ");
                String playerInput = input.nextLine();
                String[] playerInputParts = playerInput.split(" ");
                if (playerInput.equalsIgnoreCase("attack")) {
                    int damage = player.getAttackStat() - currentMonster.getDefenseStat();
                    if (damage > 0) {
                        currentMonster.setHealthPoints(currentMonster.getHealthPoints() - damage);
                        System.out.println("You dealt " + damage + " damage to " + currentMonster.getName());
                        System.out.println("The " + currentMonster.getName() + " now has " + currentMonster.getHealthPoints() + " health points remaining");
                    } else {
                        System.out.println("Your attack did no damage to the " + currentMonster.getName());
                    }
                } else if (playerInput.equalsIgnoreCase("Use Torpedo")) {
                    playerInput = getItemName(playerInputParts);
                    game.useItemInCombat(playerInput, currentMonster, player);

                } else if (playerInput.equalsIgnoreCase("Use Super Torpedo")) {
                    playerInput = getItemName(playerInputParts);
                    game.useItemInCombat(playerInput, currentMonster, player);
                } else if (playerInput.equalsIgnoreCase("flee")) {
                    System.out.println("You have successfully fled!");
                    player.setCurrentRoom(player.getPreviousRoom());
                    combatState = false;
                    exploreState = true;
                    startGame(player, map, exhibit);

                } else if (playerInput.equalsIgnoreCase("Use repair kit")) {
                    playerInput = getItemName(playerInputParts);
                    game.useItemInCombat(playerInput, null, player) ;
                } else if (playerInput.equalsIgnoreCase("check")) {
                    player.getCurrentRoom().getMonstersInRoom().get(0).getStatusForMonster(player.getCurrentRoom().getMonstersInRoom().get(0));
                } else if (playerInput.equalsIgnoreCase("status")) {
                    player.getStatusForPlayer(player);
                } else if (playerInput.equalsIgnoreCase("inventory")) {
                    player.getCurrentInventory();}
                else {
                    System.out.println("Invalid Command");
                }

                // Monster attacks after player's turn
                if (currentMonster.getHealthPoints() > 0) {
                    int monsterDamage = currentMonster.getAttackStat() - player.getDefenseStat();
                    if (monsterDamage > 0 && (!playerInput.equalsIgnoreCase("check")) && (!playerInput.equalsIgnoreCase("status")) &&
                            (!playerInput.equalsIgnoreCase("inventory")) && (!playerInput.equalsIgnoreCase("flee"))) {
                        player.setHealthPoints(player.getHealthPoints() - monsterDamage);
                        System.out.println(currentMonster.getName() + " dealt " + monsterDamage + " damage to you");
                    } else {
                        System.out.println(currentMonster.getName() + "'s attack did no damage to you!");
                    }
                }

                if (currentMonster.getHealthPoints() <= 0) {
                    currentMonster.setMonsterID(-1);
                    player.getCurrentRoom().getMonstersInRoom().remove(0);
                    combatState = false;
                    exploreState = true;
                    startGame(player, map, exhibit);
                }
                if (player.getHealthPoints() <= 0) {
                    System.out.println("Your HP as reached 0 ");
                    gameOverState = true;
                    combatState = false;
                    gameOver();

                }
            }
        }
        else {
            System.out.println("The monster you attempted to fight is not in this room");
            combatState = false;
            exploreState = true;
            startGame(player,map, exhibit);
        }
    }
    public void useItemInCombat(String itemName, Monster currentMonster, Player player) {
        // item variable used to hold the item the user is attempting to use
        Items item = null;
        // for loop used to determine if the player has the item they're attempting to use in their inventory
        if(itemName.equalsIgnoreCase("Torpedo ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase(itemName) &&
                        player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null) {
                player.getPlayerInventory().remove(item);
                currentMonster.setHealthPoints(currentMonster.getHealthPoints()-30);
                System.out.println("You fire a torpedo at the enemy");
                    int damage = player.getAttackStat() - currentMonster.getDefenseStat();
                    if (damage > 0) {
                        currentMonster.setHealthPoints(currentMonster.getHealthPoints() - damage);
                        System.out.println("You dealt " + damage + " damage to " + currentMonster.getName());
                        System.out.println("The " + currentMonster.getName() + " now has " + currentMonster.getHealthPoints() + " health points remaining");
                    }
            }

            if (item == null){
                System.out.println("You have no torpedoes!");
            }
        }
        if(itemName.equalsIgnoreCase("Super Torpedo ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase(itemName) &&
                        player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    item = player.getPlayerInventory().get(i);
                    int damage = player.getAttackStat() - currentMonster.getDefenseStat();
                    if (damage > 0) {
                        currentMonster.setHealthPoints(currentMonster.getHealthPoints() - damage);
                        System.out.println("You dealt " + damage + " damage to " + currentMonster.getName());
                        System.out.println("The " + currentMonster.getName() + " now has " + currentMonster.getHealthPoints() + " health points remaining");
                    }

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
        if(itemName.equalsIgnoreCase("Repair Kit ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase(itemName) &&
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
                if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase(itemName) &&
                        !player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                    System.out.println("This item cannot be used");
                }
                if (!player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase(itemName)){
                    System.out.println("Invalid Item");
                }
            }


        }
    }
    public void useItemOutsideOfCombat(String itemName, Player player) {
        // item variable used to hold the item the user is attempting to use
           Items item = null;
//        Items usableItem = null;
//        System.out.println("What item would you like to use?");
//        // for loop that will display all the usable items the player current has
//        for(int i = 0; i < player.getPlayerInventory().size(); i ++){
//            if(player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")){
//                usableItem = player.getPlayerInventory().get(i);
//                System.out.println(usableItem.getItemName());
//            }
//        }
//        playerInput = input.nextLine();
        // for loop used to determine if the player has the item they're attempting to use in their inventory
        if (itemName.equalsIgnoreCase("Repair Kit ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase(itemName) &&
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
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {
            if (player.getPlayerInventory().get(i).getItemName().contains(itemName) &&
                    !player.getPlayerInventory().get(i).getItemType().equalsIgnoreCase("Usable")) {
                System.out.println("This item cannot be used");
            }
            if (!player.getPlayerInventory().get(i).getItemName().contains(itemName)){
                System.out.println("Invalid Item");
            }
        }
    }
    // (Barbara)
        public void startExhibit(ArrayList<Items> itemsInExhibit) {
            exhibit.displayExhibitHelp();
            exhibit.displayExhibit(itemsInExhibit);
            Items item = null;
            String exhibitMenuInput = input.nextLine();
            String[] fullInput = exhibitMenuInput.split(" ");
            // while loop that keeps players in the exhibit unless
            while (exhibitState)
                try {
                    if (fullInput[0].equalsIgnoreCase("Inspect") && fullInput.length > 1) {
                        exhibitMenuInput = getItemName(fullInput);
                        for (int i = 0; i < itemsInExhibit.size(); i++) {
                            if (itemsInExhibit.get(i).getItemName().contains(exhibitMenuInput)) {
                                item = itemsInExhibit.get(i);
                                System.out.println(item.getItemDescription() + '\n');
                            }
                        }
                    } else if (fullInput[0].equalsIgnoreCase("Exit") && fullInput[1].equalsIgnoreCase("Exhibit")) {
                        System.out.println("Returning to main menu...");
                        exhibitState = false;
                        mainMenuAfterInputState = true;
                        secondMainMenu();
                    } else {
                        display.invalidInputForMenu();
                        exhibitMenuInput = input.nextLine();
                        fullInput = exhibitMenuInput.split(" ");
                    }
                } catch (ArrayIndexOutOfBoundsException AOB) {
                    display.invalidInputForMenu();
                    exhibitMenuInput = input.nextLine();
                    fullInput = exhibitMenuInput.split(" ");
                }
        }

}


