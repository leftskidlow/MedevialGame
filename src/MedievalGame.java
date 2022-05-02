import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class MedievalGame {

    private Player player;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        MedievalGame game = new MedievalGame();
        game.player = game.start(console);
        game.addDelay(200);
        System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
        System.out.println(game.player);
        game.addDelay(1000);
        System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
        game.save();
        game.addDelay(1000);
        System.out.println("We just saved your game...");
        System.out.println("Now we are going to try to load your character to make sure the save worked...");
        game.addDelay(1000);
        System.out.println("Deleting character...");
        String charName = game.player.getName();
        game.player = null;
        game.addDelay(1000);
        game.player = game.load(charName, console);
        System.out.println("Loading character...");
        game.addDelay(1000);
        System.out.println("Now let's print out your character again to make sure everything loaded:\n");
        game.addDelay(500);
        System.out.println(game.player);
    }

    private Player start(Scanner console) {
        Art.homeScreen();
        System.out.println("Welcome to your latest adventure!");
        System.out.println("Tell me traveler, have you been here before?");
        System.out.print("   Enter 'y' to load a game, 'n' to create a new game: ");
        String answer = console.next().toLowerCase();
        Player player;
        while (true) {
            addDelay(200);
            if (answer.equals("y")) {
                System.out.print("\nAhh... I knew I remembered you, what was your name again? Let me see if I can find your backpack: ");
                player = load(console.next(), console);
                break;
            } else if (answer.equals("n")) {
                System.out.print("\nWell then, don't be shy, go ahead an tell me your name: ");
                String possibleName = console.next();
                while (true) {
                    System.out.println("Welcome " + possibleName + ", am I pronouncing that correctly? (Enter 'y' to confirm, 'n' to enter a new name");
                    String nameResponse = console.next().toLowerCase();
                    if (Objects.equals(nameResponse, "y")) break;
                    System.out.println("So sorry, can you spell it for me again?");
                    possibleName = console.next();
                }
                player = new Player(possibleName);
                break;
            } else {
                System.out.print("Sorry adventurer, I only speak the common tongue, please enter 'y' to load a game or 'n' to start a new game: ");
            }
        }
        return player;
    }

    private void save() {
        FileOutputStream userSaveFile = null;
        try {
            userSaveFile = new FileOutputStream(player.getName() + ".svr");
            ObjectOutputStream userSaver = new ObjectOutputStream(userSaveFile);
            userSaver.writeObject(player);
        } catch (IOException e) {
            System.out.println("There was an error saving your game, your file might not be available the next time you go to load a game.");
        }
    }

    private void addDelay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Player load(String playerName, Scanner console){
        Player loadedPlayer;
        FileInputStream userSaveFile = null;
        try {
            userSaveFile = new FileInputStream(playerName + ".svr");
            ObjectInputStream userLoader = new ObjectInputStream(userSaveFile);
            loadedPlayer = (Player) userLoader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There was a problem loading your character, we've created a new player with the name you entered.");
            System.out.println("If you're sure the spelling is correct, your character file may no longer exist, please reload the game if you'd like to try again.");
            loadedPlayer = new Player(playerName);
        }
        return loadedPlayer;
    }

}