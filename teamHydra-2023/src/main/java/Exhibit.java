import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/*
    The exhibit class is used to hold the array list of items the player has archived, as well as display the exhibit menu
    and allow the player to inspect the items found within the exhibit, which will display the description of the item when the
    player inputs the "inspect [item name]" command.
 */

//(Barbara)
public class Exhibit implements Serializable {


    // array list of exhibit items that will be displayed when the user is in the exhibit area
    private ArrayList<Items> itemsInExhibit;

    // default constructor
    public Exhibit() {
        System.out.println("Exhibit");
        System.out.println("----------------------");
        for (int i = 0; i < itemsInExhibit.size(); i++) {
            System.out.println(itemsInExhibit.get(i).getItemName());
        }
        System.out.println("Here are the treasure items that have been archived in the museum.");


    }
    public void Help() {
        System.out.println("If you want to display the treasure items that have been archived in the museum, type 'Exhibit'.");
        System.out.println("If you want to display the description of an item, type the name of the item and 'Description'.");
        Scanner input = new Scanner(System.in);
        String help = input.nextLine();
        String d = "Description";
        int searchIndex = help.indexOf(d);
        if (help.contains(d) && itemsInExhibit.size() > 0) {
            String result = help.substring(0, searchIndex);
            for (int i = 0; i < itemsInExhibit.size(); i++) {
                if (result.equals(itemsInExhibit.get(i).getItemName())) {
                    System.out.println("The description of " + itemsInExhibit.get(i).getItemName() + " is: " + itemsInExhibit.get(i).getItemDescription());
                }
            }
        }
    }

    // method used to get array list of items in the exhibit
    public ArrayList<Items> getItemsInExhibit() {
        return itemsInExhibit;
    }
}