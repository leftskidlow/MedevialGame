package working;

public class MedievalGame {

  /* Instance Variables */

  /* Main Method */
  public static void main(String[] args) {

  } // End of main

  /* Instance Methods */
  private Player start() {
    // Add start functionality here

    return new Player("Test");
  } // End of start

  private void save() {
    // Add save functionality here

  } // End of save

  private Player load() {
    // Add load functionality here

    return new Player("Test");
  } // End of load

  // Adds a delay to the console so it seems like the computer is "thinking"
  // or "responding" like a human, not instantly like a computer.
  private void addDelay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}