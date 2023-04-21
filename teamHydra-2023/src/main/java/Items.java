

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

    private int itemRoomID = 0;


    private String itemType = "";

    public void setItemRoomID(int itemRoomID) {
        this.itemRoomID = itemRoomID;
    }

    public boolean isItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    private boolean itemStatus = false;

    public Items() {
    }

    public Items(int itemID, String itemName, String itemDescription, int itemRoomID, String itemType, boolean itemStatus) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemRoomID = itemRoomID;
        this.itemType = itemType;
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


    public int getItemRoomID() {
        return itemRoomID;
    }

    public void setRoomID(int itemRoomID) {
        this.itemRoomID = itemRoomID;
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

}

// method used to return the


