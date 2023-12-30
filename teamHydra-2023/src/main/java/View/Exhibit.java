package View;

import Model.Items;

import java.io.Serializable;
import java.util.ArrayList;

/*
    The exhibit class is used to hold the array list of items the player has archived, as well as display the exhibit menu
    and allow the player to inspect the items found within the exhibit, which will display the description of the item when the
    player inputs the "inspect [item name]" command.
 */

//  Authors: @Abdoulie,  Barbra
public class Exhibit implements Serializable {

    // array list of exhibit items that will be displayed when the user is in the exhibit area
    private ArrayList<Items> itemsInExhibit;
    private static final long serialVersionUID = 1L;

    // default constructor
    public Exhibit() {
        itemsInExhibit = new ArrayList<>();
    }
    public void displayExhibit(ArrayList <Items> itemsInExhibit) {
        System.out.println("Exhibit");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Here are the treasures that you've found during your adventure.");
        for (int i = 0; i < itemsInExhibit.size(); i++) {
            System.out.println(itemsInExhibit.get(i).getItemName() + '\n');
        }
    }

    public void displayExhibitHelp() {
        System.out.println("To see the description of a treasure, type 'Inspect' followed by the name of the treasure.");
        System.out.println("To go back to the Main Menu, type 'Exit Exhibit'.");
    }

//    public void setItemsInExhibit(ArrayList <Items> itemsInExhibit) {
//        this.itemsInExhibit = itemsInExhibit;
//    }


    // method used to get array list of items in the exhibit
    public ArrayList<Items> getItemsInExhibit(ArrayList<Items> itemsInExhibit) {
        return itemsInExhibit;
    }


}
