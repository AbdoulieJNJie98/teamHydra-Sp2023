/*

@Abdoulie J NJie, Benjamin, Jason
 */

import java.io.Serializable;
import java.util.ArrayList;



public class Player implements Serializable {
    private Rooms currentRoom;
    private Rooms previousRoom;
    private Map map;
    private Exhibit exhibit;

    private Player player;

    private String playerName;

    public   int maximumHP = 100;
    private int healthPoints = 100;

    private int attackStat = 10;

    private int defenseStat = 10;
    private ArrayList<Items> playerInventory = new ArrayList<>();




    public Player() {
        map = new Map();
        map.readRoomFile();
        map.readItemFile();
        map.readPuzzleFile();
        map.readMonsterFile();
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
    public void setCurrentRoom(Rooms currentRoom) {
        this.currentRoom = currentRoom;
    }
    public void setPreviousRoom(Rooms previousRoom) {
        this.previousRoom = previousRoom;
    }
    public Rooms getCurrentRoom() {
        return currentRoom;
    }
    public Rooms getPreviousRoom() {
        return previousRoom;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    public int getDefenseStat() {
        return defenseStat;
    }

    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    public String getName() {return playerName;}

    public void setName(String name) {this.playerName = name;}

    // method used to display the name, attack power, and defense level of either the player
    public void getStatusForPlayer(Player player){
        System.out.println("Status:\n" +
                "Name: " + player.getName() + "\n" +
                "HP: " + player.getHealthPoints() + " "+
                "ATK: " + player.getAttackStat() + " " +
                "DEF: " + player.getDefenseStat());
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
    public void pickUpItem(String itemName, Rooms currentRoom, Player player) {
        // Items variable used to put an item in the player's inventory
        Items item = currentRoom.removeItemFromRoomInventory(itemName, currentRoom);
        if (item != null && (!item.getItemType().equalsIgnoreCase("Treasure"))) {
            playerInventory.add(item);
            System.out.println(item.getItemName() + "has been added to your inventory");
        } else if (item != null && item.getItemType().equalsIgnoreCase("Treasure")) {
            item.pickupTreasure(player, item);
        } else {
            System.out.println("That item is currently not in this room");
        }
    }



        // method used to drop item from player's inventory, and leave them in the room the player is currently in
    public void dropItem(String itemName, Player player, Rooms currentRoom) {
        // Items variable used to put an item that is in the player's inventory, into the room's inventory
        Items item = null;
        // for loop used to find and assign the dropped item to the items variable, so that it may be added to the current room's inventory
        for (Items value : player.getPlayerInventory()) {
            if (value.getItemName().equalsIgnoreCase(itemName) && !(value.getItemType().equalsIgnoreCase("Equitable"))) {
                item = value;
                currentRoom.addItemToCurrentRoom(item);
            }
        }
            if(item != null){
                player.getPlayerInventory().remove(item);
                System.out.println(item.getItemName() + "has been removed from your inventory");
            }
            else{
                System.out.println("The item you attempted to drop is either not in your inventory, or cannot be dropped");
            }
        }

    // method used to equipped items
    //Abdoulie
    public void equippedItem(String itemName, Player player){
        // Items variable used to search for an item that is in the player's inventory, and has an equitable item type
        Items item = null;
        // for loop used to search through the player's inventory
        for (Items value : player.getPlayerInventory()) {
            if (value.getItemName().equalsIgnoreCase(itemName) &&
                    value.getItemType().equalsIgnoreCase("Equitable")) {
                item = value;
                item.equipItem(itemName, player);

            }
        }
        if(item != null && item.isItemStatus()){
            System.out.println(item.getItemName() + "has been equipped");
        }
        else{
            System.out.println("This item is either not in your inventory, not the correct type, or has already been equipped!");
        }

    }

    //method used to inspect item and will return the item's description
    public void inspectItem(String itemName, Player player) {
        // Items variable used to search for an item that is in the player's inventory, and display its description
        Items item = null;
        // for loop used to find and assign the dropped item to the items variable, so that it may be added to the current room's inventory
        for (Items value : player.getPlayerInventory()) {
            if (value.getItemName().equalsIgnoreCase(itemName)) {
                item = value;
            }
        }
        if(item != null){
            System.out.println(item.getItemName() + ": "+ item.getItemDescription());
        }
        else {
            System.out.println("There is no item to inspect, please try again");
        }
    }

    // method used to archive items
    public void archive(String itemName, ArrayList<Items> itemForExhibit, Player player){
        // Items variable used to put an item that is in the player's inventory, into the exhibit
        Items item = null;
        // for loop used to find and assign the item the player is trying to archive,to the items variable
        // so that it can be added to the exhibit's array list of items
        for (Items value : player.getPlayerInventory()) {
            if (value.getItemName().equalsIgnoreCase(itemName) && value.getItemType().equalsIgnoreCase("Treasure")) {
                item = value;
            }
        }
        if(item != null){
            itemForExhibit.add(item);
            player.getPlayerInventory().remove(item);
            System.out.println(item.getItemName() + "has been sent to the exhibit");

        }
        else {
            System.out.println("This item cannot be archived");
        }
    }


    // method used to view player's inventory
    // Abdoulie
    public void getCurrentInventory() {
        if  (playerInventory.isEmpty()) {
            System.out.println("You currently don't have anything in your inventory");
        } else
            System.out.println(playerInventory);
    }

    // method used to call room method that will display the current room's description, items, puzzles, and monster
    public void exploreArea() {
        currentRoom.displayInspectedArea(currentRoom);
    }

    // method used to call room method that will display the rooms that are connected
    // to the current room, and show their descriptions, items, puzzles, and monster found within them
    public void sonar() {
        currentRoom.showSonarResult(currentRoom);
    }


    // method used to chang


}
