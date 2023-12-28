# Treasures of the deep
This project is a text-based video game that contains one player, a map of 20 rooms, six monsters, four puzzles, 19 item types, and a combat system

## Table of Summary
- [User Manuale](#user-manuale)
- [Files](#files)

## User-Manuale
This game reads in multiple text files that contain the information needed to generate the map, items, monsters, and puzzles used throughout the game.
This is a list of all the commands needed to play the game, and what theyd do:
- |help| : The help command prints a list of all the commands avaible in the game, and what each command does
 
- |north or n| |south or s| |west or w| |east or e|: Theses commands  are what are needed to move to different rooms in the game
 
- |pickup| : The pickup command allows you to add items to your inventory by entering the command followed by the name of the item you wish to add in your inventory. The item must be present in the room you are currently in
  to be added successfully to your inventory
  
- |drop| : The drop command allows you to remove items from your inventory by entering the command followed by the item you wish to remove from your inventory.
  
- |equip| : The use command allows you to use an item as long as it is in your inventory; once used, the item will be removed from your inventory.

- |use|
  
- |inventory|
  
- |explore area|
  
- |sonar|
  
- |quit|
  
- |archive|
  
- |start puzzle|
  
- |get puzzle hint|
  
- |fight|
  
- |status|
  
- |inspect monster|
  
- |solve puzzle|
  
- |exit game|
  
- |map|
             
                "|The inventory command will display all the items currently in your inventory\n|" +
                "|The explore area command will display the current room's description, any items, monsters and puzzles in the room|\n" +
                "|The sonar command will display the room names and monsters of the rooms that are connected to the current room|\n" +
                "|The inspect command will display the designated item's description, as long as it is in your inventory|\n" +
                "|The archive command will send the designated item to the exhibit, removing it from your inventory|\n" +
                "|The start puzzle command will allow you to interact with the designated puzzle in the current room|\n" +
                "|The get puzzle hint will display the hint associated with the current puzzle you are interacting with|\n" +
                "|The fight command followed by the name of the monster you wish to fight \n" +
                " will initiate a combat loop between you and the monster|\n" +
                "|The status command will display your name, current HP, current attack power, and current defense level|\n"+
                "|The inspect monster command will display the monster's name, current HP, current attack power, and current defense level|\n"+
                "|The solve puzzle command will allow you to initiate the process of resolving the current puzzle you are interacting with\n"+
                "|The exit game command will allow you to shut down the game. You will then be prompt to either save your game, or just exit the game.|\n" +
                "|The map command will display the zone numbers and zone names found throughout the game|\n" +
                "|--------------------------------------------------------------------------------------------------------------------|\n" );


## Files

# rooms.txt
 - This text file contains the information that makes up each room that make up the map.
 - This is an example of how the information is stored, and defined:

   ```
    roomID~roomVistedStatus~roomName~northRoomID~southRoomID~westRoomID~eastRoomID~roomInventory~puzzlesInRoom~monstersInRoom~southRoomLockedStatus
   
   1~False~Man-of-war zone~0~5~2~0~As you enter this part of the Ocean you notice the presence of Man-of-war Jellyfish, as far as you can see, their tentacles are spread like the hair of an old witch, long and tangled. This area is well lit but you also see bioluminescent phytoplankton, sparkling at intervals like stars.~0~0~0~false
   ```
# Items.txt
 - This text file contains the information that makes up each item type.
 - This is an example of how the information is stored, and defined:
   
   ```
    roomID~roomVistedStatus~roomName~northRoomID~southRoomID~westRoomID~eastRoomID~roomInventory~puzzlesInRoom~monstersInRoom~southRoomLockedStatus
   
   1~False~Man-of-war zone~0~5~2~0~As you enter this part of the Ocean you notice the presence of Man-of-war Jellyfish, as far as you can see, their tentacles are spread like the hair of an old witch, long and tangled. This area is well lit but you also see bioluminescent phytoplankton, sparkling at intervals like stars.~0~0~0~false
   ```
# monster.txt
 - This text file contains the information that makes up each monster type.
 - This is an example of how the information is stored, and defined:

   ```
    roomID~roomVistedStatus~roomName~northRoomID~southRoomID~westRoomID~eastRoomID~roomInventory~puzzlesInRoom~monstersInRoom~southRoomLockedStatus
   
   1~False~Man-of-war zone~0~5~2~0~As you enter this part of the Ocean you notice the presence of Man-of-war Jellyfish, as far as you can see, their tentacles are spread like the hair of an old witch, long and tangled. This area is well lit but you also see bioluminescent phytoplankton, sparkling at intervals like stars.~0~0~0~false
   ```
# Puzzle.txt
 - This text file contains the information that makes up each puzzle type.
 - This is an example of how the information is stored, and defined:

   ```
    roomID~roomVistedStatus~roomName~northRoomID~southRoomID~westRoomID~eastRoomID~roomInventory~puzzlesInRoom~monstersInRoom~southRoomLockedStatus
   
   1~False~Man-of-war zone~0~5~2~0~As you enter this part of the Ocean you notice the presence of Man-of-war Jellyfish, as far as you can see, their tentacles are spread like the hair of an old witch, long and tangled. This area is well lit but you also see bioluminescent phytoplankton, sparkling at intervals like stars.~0~0~0~false
   ```


