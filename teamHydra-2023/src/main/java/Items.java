

/*
 Items class will use a hash map that reads in a text file of items that will be associated
 with their respective rooms, it will also hard code what it is read from the file into the game
 so that the player may interact with each item

 @Abdoulie J NJie
 */


import java.io.Serializable;

public class Items  implements Serializable {
    private int itemID = 0;

    private String itemName = "";
    private String itemDescription = "";

    private String itemType = "";

    private int itemRoomID = 0;


    private boolean itemStatus;

    public Items() {
    }

    public Items(int itemID, String itemName, String itemDescription, String itemType,int itemRoomID, boolean itemStatus) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
        this.itemRoomID = itemRoomID;
        this.itemStatus = itemStatus;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {

        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemRoomID() {
        return itemRoomID;
    }

    public void setItemRoomID(int itemRoomID) {
        this.itemRoomID = itemRoomID;
    }

    public boolean isItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    // method used to change the player's states based on the item they're attempting to equipped
    // @Jason
    public void equipItem(String playerInput, Player player){
        Items item= null;
        if(playerInput.equalsIgnoreCase("Hull Upgrade 1 ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (!player.getPlayerInventory().get(i).isItemStatus()) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null){
                player.setDefenseStat(player.getDefenseStat()+ 10);
                item.setItemStatus(true);
            }
        }
        if(playerInput.equalsIgnoreCase("Hull Upgrade 2 ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (!player.getPlayerInventory().get(i).isItemStatus()) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null){
                player.setDefenseStat(player.getDefenseStat()+ 10);
                item.setItemStatus(true);
            }
        }
        if(playerInput.equalsIgnoreCase("Drill Upgrade ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (!player.getPlayerInventory().get(i).isItemStatus()) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null){
                player.setAttackStat(player.getAttackStat()+ 10);
                item.setItemStatus(true);
            }
        }
        if(playerInput.equalsIgnoreCase("Claw Upgrade ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (!player.getPlayerInventory().get(i).isItemStatus()) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null){
                player.setAttackStat(player.getAttackStat()+ 10);
                item.setItemStatus(true);
            }
        }
        if(playerInput.equalsIgnoreCase("Super Torpedo Upgrade ")) {
            for (int i = 0; i < player.getPlayerInventory().size(); i++) {
                if (!player.getPlayerInventory().get(i).isItemStatus()) {
                    item = player.getPlayerInventory().get(i);
                }
            }
            if (item != null){
                item.setItemStatus(true);
            }
        }
    }

    // method used when the player is attempting to pick up a treasure type item
    public void pickupTreasure(Player player, Items item){
    if (item != null && item.getItemName().equalsIgnoreCase("Belitung Shipwreck ")) {
        // item instance used to help with the search of the player's inventory
        Items treasureMap = null;
        // for loop used to search player's inventory for a specific treasure map
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {
            if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Treasure Map 1 ")) {
                treasureMap = player.getPlayerInventory().get(i);
            }
        }
        if (player.getPlayerInventory().contains(treasureMap)) {
            player.getPlayerInventory().add(item);
            System.out.println("You were able to obtain to the treasure with the help of the treasure map");
        } else {
            System.out.println("Your sonar is picking up some treasure, but you can't seem to find it");
        }

    } else if (item != null && item.getItemName().equalsIgnoreCase("The Titanic ")) {
        // item instance used to help with the search of the player's inventory
        Items treasureMap = null;
        // for loop used to search player's inventory for a specific treasure map
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {
            if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Treasure Map 2 ")) {
                treasureMap = player.getPlayerInventory().get(i);
            }
        }
        if (player.getPlayerInventory().contains(treasureMap)) {
            player.getPlayerInventory().add(item);
            System.out.println("You were able to obtain to the treasure with the help of the treasure map");
        } else {
            System.out.println("Your sonar is picking up some treasure, but you can't seem to find it");
        }
    } else if (item != null && item.getItemName().equalsIgnoreCase("Ancient Caesarean coins ")) {
        // item instance used to help with the search of the player's inventory
        Items treasureMap = null;
        // for loop used to search player's inventory for a specific treasure map
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {
            if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Treasure Map 3 ")) {
                treasureMap = player.getPlayerInventory().get(i);
            }
        }
        if (player.getPlayerInventory().contains(treasureMap)) {
            player.getPlayerInventory().add(item);
            System.out.println("You were able to obtain to the treasure with the help of the treasure map");
        } else {
            System.out.println("Your sonar is picking up some treasure, but you can't seem to find it");
        }
    }
        else if (item != null && item.getItemName().equalsIgnoreCase("Wreck of the Nuestra Señora de Atocha ")) {
        // item instance used to help with the search of the player's inventory
        Items treasureMap = null;
        // for loop used to search player's inventory for a specific treasure map
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {
            if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Treasure Map 4 ")) {
                treasureMap = player.getPlayerInventory().get(i);
            }
        }
        if (player.getPlayerInventory().contains(treasureMap)) {
            player.getPlayerInventory().add(item);
            System.out.println("You were able to obtain to the treasure with the help of the treasure map");
        } else {
            System.out.println("Your sonar is picking up some treasure, but you can't seem to find it");
        }
    }
        else if (item != null && item.getItemName().equalsIgnoreCase("Heracleion ")) {
        // item instance used to help with the search of the player's inventory
        Items treasureMap = null;
        // for loop used to search player's inventory for a specific treasure map
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {
            if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Treasure Map 5 ")) {
                treasureMap = player.getPlayerInventory().get(i);
            }
        }
        if (player.getPlayerInventory().contains(treasureMap)) {
            player.getPlayerInventory().add(item);
            System.out.println("You were able to obtain to the treasure with the help of the treasure map");
        } else {
            System.out.println("Your sonar is picking up some treasure, but you can't seem to find it");
        }
        }
    }

    @Override
    public String toString() {
        return itemName;
    }
}


