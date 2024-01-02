# Treasures of the deep
This project is a text-based video game that contains one player, a map of 20 rooms, six monsters, four puzzles, 19 item types, and a combat system. 
It was designed with the waterfall methodology originally, and serverd as mock project with one group being the clients and the other being the developers.

## Table of Summary
- [User Manual](#user-manual)
- [Files](#files)

## User-Manual
This game reads in multiple text files that contain the information needed to generate the map, items, monsters, and puzzles used throughout the game.
This is a list of all the commands in the game, and what they do:

### Commands used in the start up menu
- |New Game| : This command is used to initialize a new game, which will consist of generating the map, monsters, puzzles, items, and player for the game.
- |Load Save| : This command is used to load save files stored in the user's local saves file, the user must enter the name of a save file correctly in order for it to load properly.
- |Exit Game| : This command allows the user to close the game from the main menu

### Commands used after a save file has been loaded
- |Start Game| : This command allows the user to start their saved run of the game, which will start them in the room they saved in.
- |Exhibit| : This command will send the user to the exhibit state, which is a menu of all the items the user may of archived during their playthrough.
- |Exit Game| : This command allows the user to close the game from the main menu

### Commands used in the exhibit state
- |Inspect| : The inspect command, followed by the name of item in the exhibit, will display the item description of item entered by the user.
- |Exit Exhibit| : This commands allows the user to exit the exhibit state, and returns them to the menu generated after a save file has been loaded 


### Commands used in the explore state
- |help| : The help command prints a list of all the commands available in the game, and what each command does.
- |north or n| |south or s| |west or w| |east or e|: Theses commands  are what are needed to move to different rooms in the game
- |pickup| : The pickup command allows you to add items to your inventory by entering the command followed by the name of the item you wish to add in your inventory. The item must be present in the room you are currently in
  to be added successfully to your inventory
- |drop| : The drop command allows you to remove items from your inventory by entering the command followed by the item you wish to remove from your inventory.
- |equip| : The equip command allows you to equip an item as long as it is in your inventory; once equipped, the item will remain in your inventory.
- |use| : The use command allows you to use an item as long as it is in your inventory; once used, the item will be removed from your inventory.
- |inventory| : The inventory command will display all the items currently in your inventory.
- |explore| : The explore command will display the current room's description, any items, monsters and puzzles in the room.
- |sonar| : The sonar command will display the room names and monsters of the rooms that are connected to the current room.
- |inspect| The inpspect command, followed by the name of an item in your inventory, prints the item's description.
- |archive| : The archive command will send the designated item to the exhibit, removing it from your inventory.
- |start puzzle| : The start puzzle command will allow you to interact with the designated puzzle in the current room.
- |fight| : The fight command followed by the name of the monster you wish to fight will initiate a combat loop between you and the monster.
- |status| : The status command will display your name, current HP, current attack power, and current defense level.

- |exit game| :The exit game command will allow you to shut down the game. You will then be prompt to either save your game, or just exit the game.
- |map| : The map command will display the zone numbers and zone names found throughout the game.

### Commands used in the puzzle state
- |solve puzzle| : The solve puzzle command will allow you to initiate the process of resolving the current puzzle you are interacting with.
- |get puzzle hint| : The get puzzle hint will display the hint associated with the current puzzle you are interacting with.
- |exit puzzle|: The exit puzzle command allows the user to exit the puzzle state, returning them to the explore state.

### Commands used in the combat state
- |attack| : The attack commnd allows the user to attack the monster they would be in combat with.
- |use| : The use command, followed by an item name, will allow the user to use usable items, as long as it is in their inventory
- |check| : The check command will display the monster's name, current HP, current attack power, and current defense level.
- |status| : The status command will display your name, current HP, current attack power, and current defense level.
- |inventory| : The inventory command will display all the items currently in your inventory.
- |flee| : The flee command allows the user to exit the combat state with a monster, it then puts the player back in the explore state, sending them to the pervious room they were in before the fight was initated 

             
## Files

### rooms.txt
 - This text file contains the information that makes up each room that make up the map.
 - This is an example of how the information is stored, and defined:

   ```
    roomID~roomVistedStatus~roomName~northRoomID~southRoomID~westRoomID~eastRoomID~roomInventory~puzzlesInRoom~monstersInRoom~southRoomLockedStatus
   
   EX: 1~False~Man-of-war zone~0~5~2~0~As you enter this part of the Ocean you notice the presence of Man-of-war Jellyfish, as far as you can see, their tentacles are spread like the hair of an old witch, long and tangled. This area is well lit but you also see bioluminescent phytoplankton, sparkling at intervals like stars.~0~0~0~false
   ```
### Items.txt
 - This text file contains the information that makes up each item type.
 - This is an example of how the information is stored, and defined:
   
   ```
   itemID~itemName~itemDescription~itemType~itemRoomID~itemStatus
   
   EX: 1~Hull Upgrade 2 ~This final upgrade to sub allows it to travel to the furthest depths of the ocean. + 10 defense.~Equitable~11~false

   ```
### monster.txt
 - This text file contains the information that makes up each monster type.
 - This is an example of how the information is stored, and defined:

   ```
    monsterID~healthPoints~attackStat~defenseStat~monsterName~monsterDescription
   
   EX: 1~5~12~8~Gigantic Clam ~ The clam has a thick, heavy shell with fluted, or curved edges. The soft tissue, or mantle, inside its shell, displays a pattern and mixture of colors such as yellow, green, iridescent blue, and purple. This large freak of nature is 20 feet wide and sits on the ocean floor, waiting for something to haplessly wander too close to its gaping shell. Due to the issue of mobility, it shouldn’t pose too great a threat to a sub, but discretion is the better part of valor.

   ```
# Puzzle.txt
 - This text file contains the information that makes up each puzzle type.
 - This is an example of how the information is stored, and defined:

   ```
   puzzleID~puzzleName~puzzleDescription~puzzleHint~puzzleSolvedStatus~descriptionIfPuzzleIsSolved~descriptionIfPuzzleIsNotSolved
   
   EX: 1~A Giant Rock blocks your path. If only you had a claw strong enough to break through the rock.~You need a stronger claw to get passed this rock.~false~You have successfully broken through the rock and can continue your journey!.~Giant Rock ~Your submarine doesn't have enough power to get through the rock.

   ```


