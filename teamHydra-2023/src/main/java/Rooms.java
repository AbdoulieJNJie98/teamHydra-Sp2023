import java.io.Serializable;
import java.util.ArrayList;


//@Abdoulie J NJie

public class Rooms implements Serializable {

    private int roomID = 0;
    private boolean roomVisited = false;
    private String roomName = "";
    private int northRoomID = 0;
    private int southRoomID = 0;
    private int westRoomID = 0;
    private int eastRoomID = 0;
    private String roomDescription = "";
    private ArrayList<Items> roomInventory;
    private ArrayList<Puzzles> puzzlesInRoom;

    private  ArrayList <Monster> monstersInRoom;

    private  Boolean isSouthRoomLocked;
    private int itemID = 0;
    private int puzzleID = 0;
    private  int monsterID = 0;

    // boolean used to signify the default item, puzzles, and monsters have been loaded in the game
    boolean itemsLoaded = false;
    boolean puzzlesLoaded = false;
    boolean monstersLoaded = false;

    // This variable's purpose is to be used as link to access the data that was pulled from the readFiles method in the map clas
    private Map gameMap = new Map();

    public Rooms() {
    }

    public Rooms(int roomID, boolean roomVisitedStatus, String roomName,
                 int northRoomID, int southRoomID, int eastRoomID, int westRoomID, String roomDescription, int itemID,
                 int puzzleID, int monsterID, Boolean isSouthRoomLocked) {
        this.roomID = roomID;
        this.roomVisited = roomVisitedStatus;
        this.roomName = roomName;
        this.northRoomID = northRoomID;
        this.southRoomID = southRoomID;
        this.eastRoomID = eastRoomID;
        this.westRoomID = westRoomID;
        this.roomDescription = roomDescription;
        this.itemID = itemID;
        this.puzzleID = puzzleID;
        this.monsterID = monsterID;
        this.isSouthRoomLocked = isSouthRoomLocked;
        roomInventory = new ArrayList<>();
        puzzlesInRoom = new ArrayList<>();
        monstersInRoom = new ArrayList<>();

    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public boolean isRoomVisited() {

        return roomVisited;
    }

    public void setRoomVisited(boolean roomVisitedStatus) {
        this.roomVisited = roomVisitedStatus;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomDescription = roomName;
    }

    public int getNorthRoomID() {
        return northRoomID;
    }

    public void setNorthRoomID(int northRoomID) {
        this.northRoomID = northRoomID;
    }

    public int getSouthRoomID() {
        return southRoomID;
    }

    public void setSouthRoomID(int southRoomID) {
        this.southRoomID = southRoomID;
    }

    public int getWestRoomID() {
        return westRoomID;
    }

    public void setWestRoomID(int westRoomID) {
        this.westRoomID = westRoomID;
    }

    public int getEastRoomID() {
        return eastRoomID;
    }

    public void setEastRoomID(int eastRoomID) {
        this.eastRoomID = eastRoomID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public ArrayList<Items> getRoomInventory() {

        return setRoomInventory(roomInventory);
    }

    public ArrayList<Puzzles> getPuzzlesInRoom() {
        return setPuzzlesInRoom(puzzlesInRoom);
    }

    public ArrayList<Monster> getMonstersInRoom() {
        return setMonstersInRoom(monstersInRoom);
    }


    public Boolean getIsSouthRoomLocked() {
        return isSouthRoomLocked;
    }

    public void setIsSouthRoomLocked(Boolean southRoomLocked) {
        isSouthRoomLocked = southRoomLocked;
    }

    public ArrayList<Items> setRoomInventory(ArrayList<Items> roomInventory) {
        // if statement to determine if the current room has any items, if it doesn't
        // the for loop will run, if the current room does have any item, it will just return the room's inventory
        if((!itemsLoaded)) {
            // for loop used to check the game maps array list of item's ID and match it with the room's ID
            for (int i = 0; i < gameMap.getArrayListOfItems().size(); i++) {
                if (gameMap.getArrayListOfItems().get(i).getItemRoomID() == roomID) {
                    roomInventory.add(gameMap.getArrayListOfItems().get(i));
                    itemsLoaded = true;
                }
            }
        }
        return this.roomInventory = roomInventory;
    }

    public ArrayList<Puzzles>  setPuzzlesInRoom(ArrayList<Puzzles> puzzlesInRoom) {
        // if statement to determine if the current room has any puzzles, if it doesn't
        // the for loop will run, if the current room does have any puzzles,
        // it will just return the puzzles found in the current room
        if (puzzlesInRoom.isEmpty() && (!puzzlesLoaded)) {
            // for loop used to check the game maps array list of puzzle IDs and match it with the room's ID
            for (int i = 0; i < gameMap.getArrayListOfPuzzles().size(); i++) {
                if (gameMap.getArrayListOfPuzzles().get(i).getPuzzleID() == puzzleID) {
                    puzzlesInRoom.add(gameMap.getArrayListOfPuzzles().get(i));
                    puzzlesLoaded = true;
                }
            }
        }
          return this.puzzlesInRoom = puzzlesInRoom;
    }

    public ArrayList<Monster> setMonstersInRoom(ArrayList<Monster> monstersInRoom) {

        // if statement to determine if the current room has any monsters, if it doesn't
        // the for loop will run, if the current room does have any monsters,
        // it will just return the monsters in the current room
        if (monstersInRoom.isEmpty() && (!monstersLoaded)) {
            // for loop used to check the game maps array list of monster IDs and match it with the room's ID
            for (int i = 0; i < gameMap.getArrayListOfMonsters().size(); i++) {
                if (gameMap.getArrayListOfMonsters().get(i).getMonsterID() == monsterID) {
                    monstersInRoom.add(gameMap.getArrayListOfMonsters().get(i));
                    monstersLoaded = true;
                }
            }
        }
        return this.monstersInRoom = monstersInRoom;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }


    // method used to display the names of the rooms accessible from the current room the player is in
    public void getCurrentRoomConnections(Rooms currentRoom) {
        // room variables used to access the room names of rooms connected to the current room
        Rooms northRoom;
        Rooms southRoom;
        Rooms eastRoom;
        Rooms westRoom;
        // if statements that are used to determine if there is a room in a given location based on the current room
        if (currentRoom.northRoomID != 0) {
                /* for loop that will search through an array list of rooms and match the desired location's
                roomID with the matching roomID found within an array list of rooms. It then will assign the information of
                the matching roomID numbers to a room variable with a name that matches the desired direction.
                 */
            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.northRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    northRoom = gameMap.getArrayListOfRooms().get(i);
                    // Will display the name of the room north of the current room
                    System.out.println("North of here: " + northRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("North of here: There is nothing of interest that way  \n");
        }
        if (currentRoom.southRoomID != 0) {

            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.southRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    southRoom = gameMap.getArrayListOfRooms().get(i);
                    System.out.println("South of here: " + southRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("South of here: There is nothing of interest that way  \n");
        }
        if (currentRoom.eastRoomID != 0) {

            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.eastRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    eastRoom = gameMap.getArrayListOfRooms().get(i);
                    System.out.println("East of here: " + eastRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("East of here: There is nothing of interest that way  \n");
        }
        if (currentRoom.westRoomID != 0) {

            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.westRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    westRoom = gameMap.getArrayListOfRooms().get(i);
                    System.out.println("West of here: " + westRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("West of here: There is nothing of interest that way  \n");
        }
    }

    // method used to display the information of rooms connected to the current room
    public void showSonarResult(Rooms currentRoom){
        // room variables used to access the room names of rooms connected to the current room
        Rooms northRoom;
        Rooms southRoom;
        Rooms eastRoom;
        Rooms westRoom;
        // if statements that are used to determine if there is a room in a given location based on the current room
        if (currentRoom.northRoomID != 0) {
                /* for loop that will search through an array list of rooms and match the desired location's
                roomID with the matching roomID found within an array list of rooms. It then will assign the information of
                the matching roomID numbers to a room variable with a name that matches the desired direction.
                 */
            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.northRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    northRoom = gameMap.getArrayListOfRooms().get(i);

                    // Will display the name of the room north of the current room
                    System.out.println("North of here: " + northRoom.getRoomName());

                    // display the message to mark the start of items found within the north room
                    System.out.print("\nItems found in this area: ");

                    // for loop that will be used search the north room's item array list to display the names of the items found
                    // within the room
                    for(int j = 0; j < northRoom.getRoomInventory().size(); j++ ){
                        System.out.println(northRoom.getRoomInventory().get(j).getItemName());
                    }
                    // display the message to mark the start of monsters found within the north room
                    System.out.print("\nMonsters found in this area:");

                    // for loop that will be used search the north room's monster array list to display the names of monsters found
                    // within the room
                    for(int k = 0; k < northRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(northRoom.getMonstersInRoom().get(k).getName());
                    }
                }
            }
        } else {
            System.out.println("North of here: There is nothing of interest that way");
        }
        if (currentRoom.southRoomID != 0) {

            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.southRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    southRoom = gameMap.getArrayListOfRooms().get(i);

                    System.out.println("\nSouth of here: " + southRoom.getRoomName());

                    System.out.print("\nItems found in this area: ");

                    for(int j = 0; j < southRoom.getRoomInventory().size(); j++ ){
                        System.out.println(southRoom.getRoomInventory().get(j).getItemName());
                    }

                    System.out.print("\nMonsters found in this area: ");

                    for(int k = 0; k < southRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(southRoom.getMonstersInRoom().get(k).getName());
                    }
                }

            }
        } else {
            System.out.println("\nSouth of here: There is nothing of interest that way");
        }
        if (currentRoom.eastRoomID != 0) {

            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.eastRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    eastRoom = gameMap.getArrayListOfRooms().get(i);

                    System.out.println("\nEast of here: " + eastRoom.getRoomName());
                    System.out.print("\nItems found in this area: ");

                    for(int j = 0; j < eastRoom.getRoomInventory().size(); j++ ){
                        System.out.println(eastRoom.getRoomInventory().get(j).getItemName());
                    }

                    System.out.print("\nMonsters found in this area: ");

                    for(int k = 0; k < eastRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(eastRoom.getMonstersInRoom().get(k).getName());
                    }
                }
            }
        } else {
            System.out.println("\nEast of here: There is nothing of interest that way");
        }
        if (currentRoom.westRoomID != 0) {

            for (int i = 0; i < gameMap.getArrayListOfRooms().size(); i++) {
                if (currentRoom.westRoomID == gameMap.getArrayListOfRooms().get(i).getRoomID()) {
                    westRoom = gameMap.getArrayListOfRooms().get(i);

                    System.out.println("\nWest of here: " + westRoom.getRoomName());
                    System.out.print("\nItems found in this area: ");

                    for(int j = 0; j < westRoom.getRoomInventory().size(); j++ ){
                        System.out.println(westRoom.getRoomInventory().get(j).getItemName());
                    }

                    System.out.print("\nMonsters found in this area: ");

                    for(int k = 0; k < westRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(westRoom.getMonstersInRoom().get(k).getName());
                    }
                }
            }
        } else {
            System.out.println("\nWest of here: There is nothing of interest that way");
        }
    }


    // method used to remove item from the room's array list and send it to the player
    public Items removeItemFromRoomInventory(String itemName, Rooms currentRoom) {
        Items item = null;
        // for loop used to search the array of list items found within the room
        for (int i = 0; i < currentRoom.getRoomInventory().size(); i++) {
            // if statement used to check if the player's input matches the name of an item in
            // the room's inventory
            if (currentRoom.getRoomInventory().get(i).getItemName().equalsIgnoreCase(itemName)) {
                item = currentRoom.getRoomInventory().get(i);
                currentRoom.getRoomInventory().remove(i);
            }

        }

        return item;
    }

    // method used to add items to current room inventory
    public void addItemToCurrentRoom(Items item) {
        roomInventory.add(item);
    }
    // method used to display the contents of a room when a player inputs the "inspect area" command
    public void displayInspectedArea(Rooms currentRoom){
        // items variable used to hold the name of the current i value in the loop
        Items item = null;
        // puzzle variable used to hold the name of the current i value in the loop
        Puzzles puzzle = null;
        // monster variable used to hold the name of the current i value in the loop
        Monster monster = null;

        System.out.println(currentRoom.getRoomDescription());

        // for loops that will be used to display the names of items, puzzles, and monsters found within the room
        // if statement used to determine what to display
        if(currentRoom.getRoomInventory().isEmpty()) {
            System.out.print("There are no items in this room");
        }

        else if (!currentRoom.getRoomInventory().isEmpty()) {
            System.out.print("Items: ");
            for (int i = 0; i < currentRoom.getRoomInventory().size(); i++) {
                item = currentRoom.getRoomInventory().get(i);
                System.out.print(item.getItemName() + " ");
            }

        }
        if(currentRoom.getPuzzlesInRoom().isEmpty()){
            System.out.println("\nThere are no puzzles in this room");
        }
        else {
            for (int i = 0; i < currentRoom.getPuzzlesInRoom().size(); i++) {
                puzzle = currentRoom.getPuzzlesInRoom().get(i);
                // if statement to break from the loop
                if(currentRoom.getPuzzlesInRoom().contains(puzzle)){
                    break;
                }
            }
            System.out.println("\nPuzzles: " + puzzle.getPuzzleName() + " ");
        }
        if(currentRoom.getMonstersInRoom().isEmpty()){
            System.out.println("There are no monsters in this room");
        }
        else {
            for (int i = 0; i < currentRoom.getMonstersInRoom().size(); i++) {
                monster = currentRoom.getMonstersInRoom().get(i);
                // if statement to break from the loop
                if(currentRoom.getMonstersInRoom().contains(monster)){
                    break;
                }
            }
            System.out.println("Monsters: " + monster.getName() + " ");
        }
    }


    @Override
    public String toString() {
        return  roomName;
    }


}

