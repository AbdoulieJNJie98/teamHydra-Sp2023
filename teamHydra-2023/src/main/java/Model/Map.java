package Model;/*
@Author Abdoulie and Jason
 */


import Controller.Rooms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Map  implements Serializable {
     //String used to represent file name
    public String roomsFilePath = "rooms.txt";
    public String itemsFilePath = "Items.txt";
    public String puzzlesFilePath = "Puzzle.txt";
    public String monsterFilePath = "monster.txt";

    // hash map used to store and retrieve the elements read from file
    public HashMap<Integer, Rooms> hashMapRooms = new HashMap<Integer, Rooms>();
    public HashMap<Integer, Items> hashMapItems = new HashMap<Integer, Items>();
    public HashMap<Integer, Puzzles> hashMapPuzzles = new HashMap<Integer, Puzzles>();
    public HashMap <Integer, Monster> hashMapMonsters = new HashMap<Integer, Monster>();




    // array list of items, rooms, and puzzles used throughout the game
    public ArrayList<Items> arrayListOfItems = new ArrayList<>();
    public ArrayList<Puzzles> arrayListOfPuzzles = new ArrayList<>();
    public ArrayList<Rooms> arrayListOfRooms = new ArrayList<>();
    public  ArrayList<Monster>arrayListOfMonsters = new ArrayList<>();
    public Map() {
    }

    // method used to read the room text file and return an array list of rooms
    public ArrayList<Rooms> readRoomFile(){

        // try catch statement that uses a buffered reader to read the text file, split data in the text file based on the commas found within it,
        // and stores the information in a hash map, or prints a IO Exception to indicate an issue with reading in the desired file.
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(roomsFilePath)); // buffer reader used to read the file
            while ((line = bufferedReader.readLine()) != null) { // while loop that reads until the end of the file

                String[] parts = line.split("~", 12); // String array used to each element in the given file individually
                if (parts.length >= 12) {
                    // temp room object used to hold information collected from current iteration of loop
                    Rooms r = new Rooms(Integer.parseInt(parts[0]), Boolean.parseBoolean(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7], Integer.parseInt(parts[8]), Integer.parseInt(parts[9]),
                            Integer.parseInt(parts[10]), Boolean.parseBoolean(parts[11]));
                    hashMapRooms.put(r.getRoomID(), r); // used to add the values stored in the temp room to the hash map
                    arrayListOfRooms.add(r);

                }

            }
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayListOfRooms;
    }

    // method used to read the item text file and return an array list of items
    public ArrayList<Items> readItemFile() {
        // try catch statement that uses a buffered reader to read the text file, split data in the text file based on the commas found within it,
        // and stores the information in a hash map, or prints a IO Exception to indicate an issue with reading in the desired file.
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(itemsFilePath)); // buffer reader used to read the file
            while ((line = bufferedReader.readLine()) != null) { // while loop that reads until the end of the file

                String[] parts = line.split("~", 6); // String array used to each element in the given file individually
                if (parts.length >= 6) {
                    parts[4] = randomIntegerGenerator(parts[4]);
                    // temp item object used to hold information collected from current iteration of loop
                    Items i = new Items(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5]));
                    hashMapItems.put(i.getItemID(), i); // used to add the values stored in the temp items to the hash map
                    arrayListOfItems.add(i);

                }

            }
            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayListOfItems;
    }

    // method used to read the puzzle file and return an array list of puzzles
    public ArrayList<Puzzles> readPuzzleFile(){
        // try catch statement that uses a buffered reader to read the text file, split data in the text file based on the commas found within it,
        // and stores the information in a hash map, or prints a IO Exception to indicate an issue with reading in the desired file.
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(puzzlesFilePath)); // buffer reader used to read the file
            while ((line = bufferedReader.readLine()) != null) { // while loop that reads until the end of the file

                String[] parts = line.split("~", 7); // String array used to each element in the given file individually
                if (parts.length >= 7) {
                    // temp room object used to hold information collected from current iteration of loop
                    Puzzles p = new Puzzles(Integer.parseInt(parts[0]),parts[1], parts[2], Boolean.parseBoolean(parts[3]), parts[4], parts[5], parts[6]);
                    hashMapPuzzles.put(p.getPuzzleID(), p); // used to add the values stored in the temp puzzle to the hash map
                    arrayListOfPuzzles.add(p);
                }

            }

            bufferedReader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayListOfPuzzles;
    }

    // method used to read the text file of monsters and return an array list of monster
    public ArrayList<Monster> readMonsterFile(){

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
        return arrayListOfMonsters;

    }

    public ArrayList<Items> getArrayListOfItems() {

        return setArrayListOfItems(this.arrayListOfItems);
    }

    public ArrayList<Items> setArrayListOfItems(ArrayList<Items> tempArrayListOfItems) {
        // if statement to make sure the read room file has already been read
        if(tempArrayListOfItems.isEmpty()){
            tempArrayListOfItems = readItemFile();
        }

        return tempArrayListOfItems;
    }

    public ArrayList<Puzzles> getArrayListOfPuzzles() {
        return setArrayListOfPuzzles(this.arrayListOfPuzzles);
    }

    public ArrayList<Puzzles> setArrayListOfPuzzles(ArrayList<Puzzles> tempArrayListOfPuzzles) {
        // if statement to make sure the read room file has already been read
        if(tempArrayListOfPuzzles.isEmpty()){
            tempArrayListOfPuzzles = readPuzzleFile();
        }
        return tempArrayListOfPuzzles;
    }

    public ArrayList<Rooms> getArrayListOfRooms() {

        return setArrayListOfRooms(this.arrayListOfRooms);
    }

    public ArrayList<Rooms> setArrayListOfRooms(ArrayList<Rooms> tempArrayListOfRooms) {
        // if statement to make sure the read room file has already been read
        if(tempArrayListOfRooms.isEmpty()){
            tempArrayListOfRooms = readRoomFile();
        }
        return tempArrayListOfRooms;
    }

    public ArrayList<Monster> getArrayListOfMonsters() {
        return setArrayListOfMonsters(this.arrayListOfMonsters);
    }

    public ArrayList<Monster> setArrayListOfMonsters(ArrayList<Monster> tempArrayListOfMonsters) {
        // if statement to make sure the read room file has already been read
        if(tempArrayListOfMonsters.isEmpty()){
            tempArrayListOfMonsters = readMonsterFile();
        }
        return tempArrayListOfMonsters;
    }

    // method used to generate a randomized integer, convert it to a string, and set the passed string equal to the newly created string
    public String randomIntegerGenerator(String itemRoomID){
        // int used to convert the itemRoomID into a string
        int convertItemRoomID = Integer.parseInt(itemRoomID);

        // ints used to create the min and max range of the randomized int
        int min = 1;
        int max = 20;

        // if statement used to determine what happens if convertItemRoomID = 0
        if(convertItemRoomID == 0){
            // the following statement sets the convertItemRoomID to a randomized int value between 1 and 20
            convertItemRoomID = (int) (Math.random() * (max - min)) + min;
            // set the itemRoomID equal to the string value of the convertItemRoomID
            itemRoomID = Integer.toString(convertItemRoomID);
        }
        return itemRoomID;
    }

}

