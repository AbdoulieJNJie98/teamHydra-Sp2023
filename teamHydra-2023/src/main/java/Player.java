/*
The player class is used to allow the player to traverse the map,
pickup, drop, equipped and inspect items, as well as explore rooms
@Abdoulie J NJie
 */

import java.io.Serializable;
import java.util.ArrayList;



public class Player extends ActorForMonsterAndPlayer implements Serializable {
    private Rooms currentRoom;
    private Rooms previousRoom;
    private Map map;
    private Exhibit exhibit;

    private Player player;

    private String playerName;

    private int healthPoints = 100;

    private int attackStat = 10;

    private int defenseStat = 10;


    private ArrayList<Items> playerInventory = new ArrayList<>();




    public Player() {
        map = new Map();
        player = new Player(getName(), getHealthPoints(), getAttackStat(), getDefenseStat());
        currentRoom = map.hashMapRooms.get(1);
    }

    public Player(String playerName, int healthPoints, int attackStat, int defenseStat){
        this.playerName = playerName;
        this.healthPoints = healthPoints;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
    }


    // getter used to access room methods
    public Rooms getCurrentRoom() {
        return currentRoom;
    }


    // method used to adjust the current room the player is moving too
    public void move(String playerInput) {
        if (playerInput.equalsIgnoreCase("north") || playerInput.equalsIgnoreCase("n")) {
            if (currentRoom.getNorthRoomID() != 0 ) {
                currentRoom.setRoomVisited(true);
                previousRoom = currentRoom;
                currentRoom = map.hashMapRooms.get(currentRoom.getNorthRoomID());
                System.out.println("You have arrived to " + getCurrentRoom());
            } else {
                System.out.println("There doesn't seem to be a zone that way; " +
                        "according to your radar, these are the zones connected to this one: \n");
                currentRoom.getCurrentRoomConnections(currentRoom);
            }
        } else if (playerInput.equalsIgnoreCase("south") || playerInput.equalsIgnoreCase("s")) {
            if (currentRoom.getSouthRoomID() != 0 && !(currentRoom.getIsSouthRoomLocked())) {
                currentRoom.setRoomVisited(true);
                previousRoom = currentRoom;
                currentRoom = map.hashMapRooms.get(currentRoom.getSouthRoomID());
                System.out.println("You have arrived to " + currentRoom.getRoomName());
            }
            else if (currentRoom.getSouthRoomID() != 0 && (currentRoom.getIsSouthRoomLocked())){
                // puzzle variable used to access the current room's puzzle elements
                Puzzles puzzles = currentRoom.getPuzzlesInRoom().get(0);
                // display statement used to explain why the room south of the current player's position is not accessible
                System.out.println(puzzles.getDescriptionIfPuzzleIsNotSolved() + "\n");

            } else {
                System.out.println("There doesn't seem to be a zone that way; " +
                        "according to your radar, these are the zones connected to this one: \n");
                currentRoom.getCurrentRoomConnections(currentRoom);
            }

        } else if (playerInput.equalsIgnoreCase("east") || playerInput.equalsIgnoreCase("e")) {
            if (currentRoom.getEastRoomID() != 0) {
                currentRoom.setRoomVisited(true);
                previousRoom = currentRoom;
                currentRoom = map.hashMapRooms.get(currentRoom.getEastRoomID());
                System.out.println("You have arrived to " + getCurrentRoom());
            } else {
                System.out.println("There doesn't seem to be a zone that way; " +
                        "according to your radar, these are the zones connected to this one: \n");
                currentRoom.getCurrentRoomConnections(currentRoom);
            }
        } else if (playerInput.equalsIgnoreCase("west") || playerInput.equalsIgnoreCase("w")) {
            if (currentRoom.getWestRoomID() != 0) {
                currentRoom.setRoomVisited(true);
                previousRoom = currentRoom;
                currentRoom = map.hashMapRooms.get(currentRoom.getWestRoomID());
                System.out.println("You have arrived to " + getCurrentRoom());
            } else {
                System.out.println("There doesn't seem to be a zone that way; " +
                        "according to your radar, these are the zones connected to this one: \n");
                currentRoom.getCurrentRoomConnections(currentRoom);
            }
        }
    }

    public ArrayList<Items> getPlayerInventory() {
        return playerInventory;
    }

    //method used to add items to player's inventory
    public void pickUpItem(String itemName) {
        // Items variable used to put an item in the player's inventory
        Items item = currentRoom.removeItemFromRoomInventory(itemName);
        playerInventory.add(item);
    }

    // method used to drop item from player's inventory, and leave them in the room the player is currently in
    public void dropItem(String itemName) {
        // Items variable used to put an item that is in the player's inventory, into the room's inventory
        Items item = null;
        // for loop used to find and assign the dropped item to the items variable, so that it may be added to the current room's inventory
        for (Items value : playerInventory) {
            if (value.getItemName().equalsIgnoreCase(itemName) && !(value.getItemType().equalsIgnoreCase("Equitable"))){
                item = value;
                currentRoom.getRoomInventory().add(item);
            } else {
                System.out.println("Looks like that item isn't in your inventory currently or cannot be dropped");
            }
        }

    }
    // method used to equipped items
    public void equippedItem(String itemName){
        // Items variable used to search for an item that is in the player's inventory, and has an equitable item type
        Items item = null;
        // for loop used to search through the player's inventory
        for (Items value : playerInventory) {
            if (value.getItemName().equalsIgnoreCase(itemName) && value.getItemType().equalsIgnoreCase("Equitable")) {
                item = value;
                item.setItemStatus(true);
            }
            else{
                System.out.println("The item you attempted to equipped is not the correct type");
            }

        }

    }

    //method used to inspect item and will return the item's description
    public void inspectItem(String itemName) {
        // Items variable used to search for an item that is in the player's inventory, and display its description
        Items item = null;
        // for loop used to find and assign the dropped item to the items variable, so that it may be added to the current room's inventory
        for (Items value : playerInventory) {
            if (value.getItemName().equalsIgnoreCase(itemName)) {
                item = value;
                System.out.println(item.getItemName() + ": "+ item.getItemDescription());
            }
            else if(itemName.equalsIgnoreCase("Treasure map 1")){
                item = value;
                System.out.println(item.getItemDescription() + " " + item.getWhereTheTreasureIs(itemName, map, player) );
            }
        }
    }

    // method used to archive items
    public void archive(String itemName){
        // Items variable used to put an item that is in the player's inventory, into the exhibit
        Items item = null;
        // for loop used to find and assign the item the player is trying to archive,to the items variable
        // so that it can be added to the exhibit's array list of items
        for (Items value : playerInventory) {
            if (value.getItemName().equalsIgnoreCase(itemName) && value.getItemType().equalsIgnoreCase("Treasure")) {
                item = value;
                exhibit.getItemsInExhibit().add(item);
            }
            else {
                System.out.println("The item you attempted to archive is not the right type");
            }
        }
    }


    // method used to view player's inventory

    public void getCurrentInventory() {
        if  (playerInventory.isEmpty()) {
            System.out.println("You currently don't have anything in your inventory");
        } else
            System.out.println(playerInventory);
    }

    // method used to call room method that will display the current room's description, items, puzzles, and monster
    public void inspectArea() {
        currentRoom.displayInspectedArea(currentRoom);
    }

    // method used to call room method that will display the rooms that are connected
    // to the current room, and show their descriptions, items, puzzles, and monster found within them
    public void sonar() {
        currentRoom.showSonarResult(currentRoom);
    }


}
