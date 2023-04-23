

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
        setItemName(itemName.toLowerCase());
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

    // method used to return the name of the room where the treasure is, based on the treasure map the player is inspecting
    public String getWhereTheTreasureIs(String itemName, Map map, Player player){
        // item variable used to reference the itemRoomID and match it with the room's itemID
        Items item = null;
        // String used to hold the room name of where the treasure can be found
        String location = "";
        // for loop used to search the player's inventory and set the item variable
        for(int i = 0; i<player.getPlayerInventory().size(); i++){
            // if statements used to determine which value the item will have
            if(player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase(itemName)){
                item = player.getPlayerInventory().get(i);
                // for loop used to search the array list of rooms and set the location variable equal to the desired room description
                for(int j = 0; j < map.arrayListOfRooms.size(); j++ ){
                    // if statement used to determine the which room name will be displayed
                    if(item.itemRoomID == map.arrayListOfRooms.get(j).getItemID()){
                        location = map.arrayListOfRooms.get(j).getRoomDescription();
                    }

                }

            }
            else{
                location = "Error occurred";
            }
        }
        return location;
    }

}


