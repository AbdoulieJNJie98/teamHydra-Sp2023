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
    //Game g = new Game();


    // array list of exhibit items that will be displayed when the user is in the exhibit area
    private ArrayList<Items> itemsInExhibit;

    // default constructor
    public Exhibit() {
        itemsInExhibit = new ArrayList<>();
    }
    public void displayExhibit() {
        System.out.println("Exhibit");
        System.out.println("----------------------");
        for (int i = 0; i < itemsInExhibit.size(); i++) {
            System.out.println(itemsInExhibit.get(i).getItemName() + '\n');
        }
        System.out.println("Here are the treasures that you found during your adventure.");
    }
//    public void exhibitCommands(String input) {
//        Help();
//        Items item = null;
//        String[] fullInput = input.split(" ");
//        if (fullInput[0].equalsIgnoreCase("Description of") && fullInput.length > 1) {
//            input = g.getWord(fullInput);
//            for(int i = 0; i < itemsInExhibit.size(); i++) {
//                if (itemsInExhibit.get(i).getItemName().contains(input)) {
//                    item = itemsInExhibit.get(i);
//                    System.out.println(item.getItemDescription() + '\n');
//                }
//            }
//        }
//        else if (fullInput[0].equalsIgnoreCase("Exit Exhibit") && fullInput.length > 1) {
//            input = g.getWord(fullInput);
//            g.mainMenu();
//        }
//    }
    public void Help() {
      System.out.println("To see the description of this treasure, type 'Description of' followed by the name of the item.");
      System.out.println("To go back to the Main Menu, type 'Exit Exhibit'.");
    }

    // method used to get array list of items in the exhibit
    public ArrayList<Items> getItemsInExhibit() {
        return itemsInExhibit;
    }


}
