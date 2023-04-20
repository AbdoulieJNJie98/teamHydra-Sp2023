/*
The Map class reads in the designated text file and generates a map based on the data stored in that said file.
The data is then stored into a hashmap that is made up of room objects, and uses each respective room's ID number as the key
to access the data that is associated with each key.

@Author Abdoulie and Jason
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Map  implements Serializable {
    // String used to represent file name
    public String roomsFilePath = "rooms.txt";
    public String itemsFilePath = "Items.txt";
    public String puzzlesFilePath = "Puzzles.txt";

    public String monsterFilePath = "monster.txt";

    // hash map used to store and retrieve the elements read from file
    public HashMap<Integer, Rooms> hashMapRooms = new HashMap<Integer, Rooms>();
    public HashMap<Integer, Items> hashMapItems = new HashMap<Integer, Items>();
    public HashMap<Integer, Puzzles> hashMapPuzzles = new HashMap<Integer, Puzzles>();

    public HashMap <Integer, Monster> hashMapMonsters = new HashMap<>();

    // array list of items, rooms, and puzzles used throughout the game
    public ArrayList<Items> arrayListOfItems = new ArrayList<>();
    public ArrayList<Puzzles> arrayListOfPuzzles = new ArrayList<>();
    public ArrayList<Rooms> arrayListOfRooms = new ArrayList<>();

    public  ArrayList<Monster>arrayListOfMonsters = new ArrayList<>();
    public Map() {
        readFiles();
    } // method used to read the room files


    // method used to read file
    public void readFiles() {
        // try catch statement that uses a buffered reader to read the text file, split data in the text file based on the commas found within it,
        // and stores the information in a hash map, or prints a IO Exception to indicate an issue with reading in the desired file.
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(roomsFilePath)); // buffer reader used to read the file
            while ((line = bufferedReader.readLine()) != null) { // while loop that reads until the end of the file

                String[] parts = line.split("~", 11); // String array used to each element in the given file individually
                if (parts.length >= 11) {
                    // temp room object used to hold information collected from current iteration of loop
                    Rooms r = new Rooms(Integer.parseInt(parts[0]), Boolean.parseBoolean(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7], Integer.parseInt(parts[8]), Integer.parseInt(parts[9]), Integer.parseInt(parts[10]));
                    hashMapRooms.put(r.getRoomID(), r); // used to add the values stored in the temp room to the hash map
                    arrayListOfRooms.add(r);
                }

            }
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // try catch statement that uses a buffered reader to read the text file, split data in the text file based on the commas found within it,
        // and stores the information in a hash map, or prints a IO Exception to indicate an issue with reading in the desired file.
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(itemsFilePath)); // buffer reader used to read the file
            while ((line = bufferedReader.readLine()) != null) { // while loop that reads until the end of the file

                String[] parts = line.split("~", 5); // String array used to each element in the given file individually
                if (parts.length >= 5) {
                    // temp item object used to hold information collected from current iteration of loop
                    Items i = new Items(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                    hashMapItems.put(i.getItemRoomID(), i); // used to add the values stored in the temp items to the hash map
                    arrayListOfItems.add(i);

                }

            }
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // try catch statement that uses a buffered reader to read the text file, split data in the text file based on the commas found within it,
        // and stores the information in a hash map, or prints a IO Exception to indicate an issue with reading in the desired file.
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(puzzlesFilePath)); // buffer reader used to read the file
            while ((line = bufferedReader.readLine()) != null) { // while loop that reads until the end of the file

                String[] parts = line.split("~", 10); // String array used to each element in the given file individually
                if (parts.length >= 10) {
                    // temp room object used to hold information collected from current iteration of loop
                    Puzzles p = new Puzzles(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),parts[2], parts[3], parts[4],parts[5], parts[6],parts[7]);
                    hashMapPuzzles.put(p.getPuzzleID(), p); // used to add the values stored in the temp puzzle to the hash map
                }

            }
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // try catch statement that uses a buffered reader to read the text file, split data in the text file based on the commas found within it,
        // and stores the information in a hash map, or prints a IO Exception to indicate an issue with reading in the desired file.
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(monsterFilePath)); // buffer reader used to read the file
            while ((line = bufferedReader.readLine()) != null) { // while loop that reads until the end of the file

                String[] parts = line.split("~", 6); // String array used to each element in the given file individually
                if (parts.length >= 6) {
                    // temp room object used to hold information collected from current iteration of loop
                    Monster m = new Monster(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), (parts[4]), parts[5]);
                    hashMapMonsters.put(m.getMonsterID(), m); // used to add the values stored in the temp room to the hash map
                    arrayListOfMonsters.add(m);
                }

            }
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
