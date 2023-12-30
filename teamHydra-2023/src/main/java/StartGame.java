import Controller.Game;

/*
 Class used to server as the main to call an instance of the game class
 */
public class StartGame {
    public static void main(String[] args) {
        Game game = new Game();
        game.firstMainMenu(); // call to the start game method that will initialize a run of the game
    }
}
