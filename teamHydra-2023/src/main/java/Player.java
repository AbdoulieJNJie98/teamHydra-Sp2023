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

    private String playerName;

    private int healthPoints;

    private int attackStat;

    private int defenseStat;

    private ArrayList<Items> playerInventory = new ArrayList<>();




    public Player() {
        map = new Map();
        currentRoom = map.hashMapRooms.get(1);
    }

    public Player(String playerName, int healthPoints, int attackStat, int defenseStat){
        this.playerName = playerName;
        this.healthPoints=100;
        this.attackStat=10;
        this.defenseStat=10;
    }


    // getter used to access room methods
    public Rooms getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Items> getplayerInventory() {
        return playerInventory;
    }

    public void move(String playerInput) {
        if (playerInput.equalsIgnoreCase("north") || playerInput.equalsIgnoreCase("n")) {
            if (currentRoom.getNorthRoomID() != 0) {
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
            if (currentRoom.getSouthRoomID() != 0) {
                currentRoom.setRoomVisited(true);
                previousRoom = currentRoom;
                currentRoom = map.hashMapRooms.get(currentRoom.getSouthRoomID());
                System.out.println("You have arrived to " + currentRoom.getRoomName());

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


    public void checkIfRoomHasBeenVisited() {
        if (currentRoom.isRoomVisited()) {
            System.out.println("This room looks familiar");
        }
    }
//    public void solvePuzzle(String playerInput) {
//        puzzles.checkPlayersAnswer(playerInput);
//    }

    //method used to add items to player's inventory
    public void pickUpItem(String playerInput) {
        // Items variable used to put an item in the player's inventory
        Items item = currentRoom.removeItemFromRoomInventory(playerInput);
        playerInventory.add(item);
    }

    // method used to drop item from player's inventory, and leave them in the room the player is currently in
    public void dropItem(String playerInput) {
        // Items variable used to put an item that is in the player's inventory, into the room's inventory
        Items item = null;
        // for loop used to find and assign the dropped item to the items variable, so that it may be added to the current room's inventory
        for (Items value : playerInventory) {
            if (value.getItemName().contains(playerInput) && !(value.getItemType().equalsIgnoreCase("Equitable"))){
                item = value;
                currentRoom.getRoomInventory().add(item);
            } else {
                System.out.println("Looks like that item isn't in your inventory currently or cannot be dropped");
            }
        }

    }

    //method used to inspect item and will return the item's description
    public void inspectItem(String playerInput) {
        // Items variable used to search for an item that is in the player's inventory, and display its description
        Items item = null;
        // for loop used to find and assign the dropped item to the items variable, so that it may be added to the current room's inventory
        for (Items value : playerInventory) {
            if (value.getItemName().contains(playerInput)) {
                item = value;
                System.out.println(item.getItemName() + ": "+ item.getItemDescription());
            }
        }
    }

    // method used to archive items
    public void archive(String playerInput){
        // Items variable used to put an item that is in the player's inventory, into the exhibit
        Items item = null;
        // for loop used to find and assign the item the player is trying to archive,to the items variable
        // so that it can be added to the exhibit's array list of items
        for (Items value : playerInventory) {
            if (value.getItemName().contains(playerInput) && value.getItemType().equalsIgnoreCase("Key")) {
                item = value;
                exhibit.getItemsInExhibit().add(item);
            }
            else {
                System.out.println("The item you attempted to archive is not the right type");
            }
        }
    }


    // method used to view player's inventory

    public void getCurrentInventory(String playerInput) {
        if  (playerInventory.isEmpty()) {
            System.out.println("You currently don't have anything in your inventory");
        } else
            System.out.println(playerInventory);
    }

    // method used to call room method that will display the current room's description, items, puzzles, and monster
    public void exploreRoom() {
        currentRoom.exploreRoom(currentRoom);
    }

}
