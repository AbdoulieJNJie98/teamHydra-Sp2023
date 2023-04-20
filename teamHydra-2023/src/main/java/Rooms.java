import java.io.Serializable;
import java.util.ArrayList;

//MODE

/*
Room class holds all the information needed to define a rooms object.
For instance, values such as the roomID number, room visited status, and the other room ID number based on their directions
are set and retrieved here
@Abdoulie J NJie
 */
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

    private Map gameMap = new Map();


    public Items getItem() {
        return item;
    }


    private int itemID = 0;
    private int puzzleID = 0;
    private  int monsterID = 0;
    private Items item;
    private Puzzles puzzle;

    public Rooms() {
    }

    public Rooms(int roomID, boolean roomVisitedStatus, String roomName,
                 int northRoomID, int southRoomID, int eastRoomID, int westRoomID, String roomDescription, int itemID, int puzzleID, int monsterID) {
        this.roomID = roomID;
        this.roomVisited = roomVisitedStatus;
        this.roomName = roomName;
        this.northRoomID = northRoomID;
        this.southRoomID = southRoomID;
        this.westRoomID = westRoomID;
        this.eastRoomID = eastRoomID;
        this.roomDescription = roomDescription;
        this.itemID = itemID;
        this.puzzleID = puzzleID;
        this.monsterID = monsterID;
        roomInventory = new ArrayList<>();
        puzzlesInRoom = new ArrayList<>();
        monstersInRoom = new ArrayList<>();
        setDefaultItemInRoom();
        setDefaultPuzzleInRoom();
        setMonstersInRoom();
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
    public void setItem(Items item) {this.item = item;}

    public Puzzles getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzles puzzle) {
        this.puzzle = puzzle;
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
        return roomInventory;
    }

    public ArrayList<Puzzles> getPuzzlesInRoom() {
        return puzzlesInRoom;
    }

    public ArrayList<Monster> getMonstersInRoom() {
        return monstersInRoom;
    }




    // method used to initialize which item will be in each room by default based on the room's itemID
    public void setDefaultItemInRoom() {
        // for loop used to match the itemID found within the room text file, with the itemID found within the item text file
        for (int i = 0; i < gameMap.arrayListOfItems.size(); i++) {
            // if statement use to match the itemIDs with one another
            if (itemID == gameMap.arrayListOfItems.get(i).getItemRoomID()) {
                // new Items variable used to set the default item value in the room
                Items item = gameMap.arrayListOfItems.get(i);
                roomInventory.add(item);
            }
        }
    }

    // method used to initialize which puzzle will be in each room by default based on the room's puzzleID
    public void setDefaultPuzzleInRoom() {
        // for loop used to match the puzzleID found within the room text file, with the puzzleID found within the puzzle text file
        for (int i = 0; i < gameMap.arrayListOfPuzzles.size(); i++) {
            // if statement use to match the itemIDs with one another
            if (puzzleID == gameMap.arrayListOfPuzzles.get(i).getPuzzleRoomID()) {
                // new Puzzles variable used to set the default item value in the room
                Puzzles puzzle = gameMap.arrayListOfPuzzles.get(i);
                puzzlesInRoom.add(puzzle);
            }
        }
    }

    // method used to initialize which monsters will be in each room by default based on the room's monsterID
    public void setMonstersInRoom() {
        // for loop used to match the monsterID found within the monster  text file, with the monsterID found within the monster text file
        for (int i = 0; i < gameMap.arrayListOfMonsters.size(); i++) {
            // if statement use to match the itemIDs with one another
            if (monsterID == gameMap.arrayListOfMonsters.get(i).getMonsterID()) {
                // new Puzzles variable used to set the default item value in the room
                Monster monster = gameMap.arrayListOfMonsters.get(i);
                monstersInRoom.add(monster);
            }
        }
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
            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.northRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    northRoom = gameMap.arrayListOfRooms.get(i);
                    // Will display the name of the room north of the current room
                    System.out.println("North of here: " + northRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
        }
        if (currentRoom.southRoomID != 0) {

            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.southRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    southRoom = gameMap.arrayListOfRooms.get(i);
                    System.out.println("South of here: " + southRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
        }
        if (currentRoom.eastRoomID != 0) {

            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.eastRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    eastRoom = gameMap.arrayListOfRooms.get(i);
                    System.out.println("East of here: " + eastRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
        }
        if (currentRoom.westRoomID != 0) {

            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.westRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    westRoom = gameMap.arrayListOfRooms.get(i);
                    System.out.println("West of here: " + westRoom.getRoomName() + "\n");
                }
            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
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
            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.northRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    northRoom = gameMap.arrayListOfRooms.get(i);

                    // Will display the name of the room north of the current room
                    System.out.println("North of here: " + northRoom.getRoomName() + "\n");

                    // display the message to mark the start of items found within the north room
                    System.out.println("Items found in this area: ");

                    // for loop that will be used search the north room's item array list to display the names of the items found
                    // within the room
                    for(int j = 0; j < northRoom.getRoomInventory().size(); j++ ){
                        System.out.println(northRoom.getRoomInventory().get(j).getItemName() + " ");
                    }
                    // display the message to mark the start of monsters found within the north room
                    System.out.println("\nMonsters found in this area: ");

                    // for loop that will be used search the north room's monster array list to display the names of monsters found
                    // within the room
                    for(int k = 0; k < northRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(northRoom.getMonstersInRoom().get(k).getName() + " ");
                    }
                }
            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
        }
        if (currentRoom.southRoomID != 0) {

            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.southRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    southRoom = gameMap.arrayListOfRooms.get(i);

                    System.out.println("South of here: " + southRoom.getRoomName() + "\n");

                    System.out.println("Items found in this area: ");

                    for(int j = 0; j < southRoom.getRoomInventory().size(); j++ ){
                        System.out.println(southRoom.getRoomInventory().get(j).getItemName() + " ");
                    }

                    System.out.println("\nMonsters found in this area: ");

                    for(int k = 0; k < southRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(southRoom.getMonstersInRoom().get(k).getName() + " ");
                    }
                }

            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
        }
        if (currentRoom.eastRoomID != 0) {

            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.eastRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    eastRoom = gameMap.arrayListOfRooms.get(i);

                    System.out.println("East of here: " + eastRoom.getRoomName() + "\n");
                    System.out.println("Items found in this area: ");

                    for(int j = 0; j < eastRoom.getRoomInventory().size(); j++ ){
                        System.out.println(eastRoom.getRoomInventory().get(j).getItemName() + " ");
                    }

                    System.out.println("\nMonsters found in this area: ");

                    for(int k = 0; k < eastRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(eastRoom.getMonstersInRoom().get(k).getName() + " ");
                    }
                }
            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
        }
        if (currentRoom.westRoomID != 0) {

            for (int i = 0; i < gameMap.arrayListOfRooms.size(); i++) {
                if (currentRoom.westRoomID == gameMap.arrayListOfRooms.get(i).getRoomID()) {
                    westRoom = gameMap.arrayListOfRooms.get(i);

                    System.out.println("West of here: " + westRoom.getRoomName() + "\n");
                    System.out.println("Items found in this area: ");

                    for(int j = 0; j < westRoom.getRoomInventory().size(); j++ ){
                        System.out.println(westRoom.getRoomInventory().get(j).getItemName() + " ");
                    }

                    System.out.println("\nMonsters found in this area: ");

                    for(int k = 0; k < westRoom.getMonstersInRoom().size(); k++ ){
                        System.out.println(westRoom.getMonstersInRoom().get(k).getName() + " ");
                    }
                }
            }
        } else {
            System.out.println("There is nothing of interest that way  \n");
        }
    }


    // method used to remove item from the room's array list and send it to the player
    public Items removeItemFromRoomInventory(String playerInput) {
        Items item = null;
        // for loop used to search the array of list items found within the room
        for (int i = 0; i < roomInventory.size(); i++) {
            // if statement used to check if the player's input matches the name of an item in
            // the room's inventory
            if (roomInventory.get(i).getItemName().contains(playerInput)) {
                item = roomInventory.get(i);
                roomInventory.remove(i);
            }

        }

        return item;
    }

    // method used to display the contents of a room when a player inputs the "inspect area" command
    public void displayInspectedArea(Rooms currentRoom){
        // for loops that will be used to display the names of items, puzzles, and monsters found within the room
        for(int i = 0; i < currentRoom.getRoomInventory().size(); i++ ){
            System.out.println("Items: " + currentRoom.getRoomInventory().get(i).getItemName() + " " );
        }
//        for(int i = 0; i < currentRoom.getPuzzlesInRoom().size(); i++ ){
//            System.out.println("\nPuzzles: " + currentRoom.getPuzzlesInRoom().get(i).getPuzzleName() + " " );
//        }
        for(int i = 0; i < currentRoom.getRoomInventory().size(); i++ ){
            System.out.println("\nMonsters: " + currentRoom.getMonstersInRoom().get(i).getName() + " " );
        }
        for(int i = 0; i < currentRoom.getRoomInventory().size(); i++ ){
            System.out.println("Items: " + currentRoom.getRoomInventory().get(i).getItemName() + " " );
        }
    }


    @Override
    public String toString() {
        return  roomName;
    }
}