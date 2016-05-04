import java.io.IOException;

public interface Controller {

  /**
   * checks if game is over
   * @return if the game is over
   */
  public boolean gameOver();

  /**
   * runs a game
   * @throws IOException
   */
  public void run() throws IOException;

  /**
   * steps through a game
   * @throws IOException
   */
  public void step() throws IOException;

  /**
   * makes a move
   * @throws IOException
   */
  public void move() throws IOException;

  /**
   * resets the checkers
   */
  public void reset();
}
