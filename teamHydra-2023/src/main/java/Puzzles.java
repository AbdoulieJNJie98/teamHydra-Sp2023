import java.io.Serializable;

// Author @ Abdoulie and Amara
public class Puzzles implements Serializable {
    private int puzzleID;
    private String puzzleDescription;
    private String puzzleHint;
    private boolean puzzleSolvedStatus;
    private String descriptionIfPuzzleIsSolved;
    private String descriptionIfPuzzleIsNotSolved;
    private String puzzleName;

    public Puzzles(){};

    public Puzzles(int id, String description, String puzzleHint, boolean puzzleSolvedStatus, String solvedDesc, String puzzleName, String unsolvedDesc) {
        this.puzzleID = id;
        this.puzzleDescription = description;
        this.puzzleHint = puzzleHint;
        this.puzzleSolvedStatus = puzzleSolvedStatus;
        this.descriptionIfPuzzleIsSolved = solvedDesc;
        this.descriptionIfPuzzleIsNotSolved = unsolvedDesc;
        this.puzzleName = puzzleName;
    }

    // getters and setters
    public String getPuzzleSolution() {
        return puzzleHint;
    }

    public void setPuzzleSolution(String puzzleSolution) {
        this.puzzleHint = puzzleSolution;
    }

    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public boolean isPuzzleSolvedStatus() {
        return puzzleSolvedStatus;
    }

    public void setPuzzleSolvedStatus(boolean puzzleSolvedStatus) {
        this.puzzleSolvedStatus = puzzleSolvedStatus;
    }

    public String getDescriptionIfPuzzleIsSolved() {
        return descriptionIfPuzzleIsSolved;
    }

    public void setDescriptionIfPuzzleIsSolved(String descriptionIfPuzzleIsSolved) {
        this.descriptionIfPuzzleIsSolved = descriptionIfPuzzleIsSolved;
    }

    public String getDescriptionIfPuzzleIsNotSolved() {
        return descriptionIfPuzzleIsNotSolved;
    }

    public void setDescriptionIfPuzzleIsNotSolved(String descriptionIfPuzzleIsNotSolved) {
        this.descriptionIfPuzzleIsNotSolved = descriptionIfPuzzleIsNotSolved;
    }

    public String getPuzzleDescription(){return puzzleDescription;}

    public void setPuzzleDescription(String puzzleDescription){
        this.puzzleDescription = puzzleDescription;
    }
    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    public String getPuzzleHint() {
        return puzzleHint;
    }

    public void setPuzzleHint(String puzzleHint) {
        this.puzzleHint = puzzleHint;
    }

    // method used to remove the puzzle in the current puzzle room the player is in
    // and allows the player to continue moving south passed the puzzle room.
    public void resolvePuzzle(Player player) {
        // item variable used to determine if the player has a specific item in their inventory
        Items item = null;
        // puzzle variable used to access the puzzle methods in the current room
        Puzzles puzzles = player.getCurrentRoom().getPuzzlesInRoom().get(0);
        // for loop used to search through the player's current inventory
        for (int i = 0; i < player.getPlayerInventory().size(); i++) {

            if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Drill Upgrade ")) {
                item = player.getPlayerInventory().get(i);
                // if statement that is used to see if the current room's puzzle ID is equal to the corresponding puzzle's ID number
                // as well as if the player has the item equipped to determine if the room south of the current room will be locked or not
                if (item.isItemStatus() && player.getCurrentRoom().getPuzzleID() == 4) {
                    player.getCurrentRoom().setIsSouthRoomLocked(false);
                    System.out.println(puzzles.getDescriptionIfPuzzleIsSolved());
                    // statement used to help with removing the puzzle that has been solved from the game
                    player.getCurrentRoom().setPuzzleID(-1);
                    removePuzzleFromRoom(player.getCurrentRoom(), puzzles);

                }
            } else if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Claw Upgrade ")) {
                item = player.getPlayerInventory().get(i);
                if (item.isItemStatus() && player.getCurrentRoom().getPuzzleID() == 1) {
                    player.getCurrentRoom().setIsSouthRoomLocked(false);
                    System.out.println(puzzles.getDescriptionIfPuzzleIsSolved());
                    player.getCurrentRoom().setPuzzleID(-1);
                    removePuzzleFromRoom(player.getCurrentRoom(), puzzles);
                }
            } else if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Super Torpedo Upgrade ")) {
                item = player.getPlayerInventory().get(i);
                if (item.isItemStatus() && player.getCurrentRoom().getPuzzleID() == 2) {
                    player.getCurrentRoom().setIsSouthRoomLocked(false);
                    System.out.println(puzzles.getDescriptionIfPuzzleIsSolved());
                    player.getCurrentRoom().setPuzzleID(-1);
                    removePuzzleFromRoom(player.getCurrentRoom(), puzzles);
                }
            } else if (player.getPlayerInventory().get(i).getItemName().equalsIgnoreCase("Antikythera mechanism ")) {
                item = player.getPlayerInventory().get(i);
                if (player.getCurrentRoom().getPuzzleID() == 3) {
                    System.out.println(puzzles.getDescriptionIfPuzzleIsSolved());
                    player.getCurrentRoom().setPuzzleID(-1);
                    removePuzzleFromRoom(player.getCurrentRoom(), puzzles);
                    // switches the item type to treasure so that the Antikythera mechanism can be archived
                    item.setItemType("Treasure");
                }
            }
        }
        if(player.getPlayerInventory().size() == 0){
            System.out.println(puzzles.getDescriptionIfPuzzleIsNotSolved());
        }
    }

    //  method used to remove the puzzle from the current room once the puzzle has been solved
    public void removePuzzleFromRoom(Rooms currentRoom, Puzzles puzzle){
        // if statement used to check if the currentRoom's puzzleID is equal to -1
        if(currentRoom.getPuzzleID() == -1){
            currentRoom.getPuzzlesInRoom().remove(puzzle);
        }
    }

}

