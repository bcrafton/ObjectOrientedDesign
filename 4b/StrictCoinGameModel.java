import java.util.ArrayList;

/** You don't need to implement this class or any concrete subclasses
 * for pset04.
 */
public final class StrictCoinGameModel implements CoinGameModel {
  // (Exercise 2) Declare the fields needed to support the methods in
  // the interface you’ve designed:
  private ArrayList<Integer> players;
  private Integer nextPlayer = 0;
  private Integer turnCount = 0;

  // (Exercise 3) Describe, as precisely as you can, your
  // representation’s class invariants:

  /**
   * Class Invariants
   * {@code board} cannot be null
   * {@code board} must contain only 'O' and '-' characters
   * Moves must be made in the left direction
   * Moves cannot be made to a position occupied by a coin
   * Moves cannot be made past another coin
   * Moves cannot be made if the game is over
   *
   * there must be atleast 1 player
   * </p>
   */


  // (Exercise 4) Describe your constructor API here by filling in
  // whatever arguments you need and writing good Javadoc. (You may
  // declare any combination of constructors and static factory
  // methods that you like, but you need not get fancy.)
  /**
   * Constructs a StrictGameModel object with a board string.
   *
   * @param [board] the game board
   * @throws NullPointerException if the board is Null
   * @throws IllegalArgumentException if board contains an invalid value
   *     (not O or -)
   */
  protected StrictCoinGameModel(String board) {
    // You don't need to implement this constructor.
    throw new UnsupportedOperationException("no need to implement this");
  }
  /**
   * Constructs a StrictGameModel object with a board string and a initial
   *     number of players
   *
   * @param [board] the game board.
   * @param [numberOfPlayers] the starting number of players.
   * @throws NullPointerException if the board is Null
   * @throws IllegalArgumentException if board contains an invalid value
   *     (not O or -)
   * @throws IllegalArgumentException if [numberOfPlayers] is negative
   */
  protected StrictCoinGameModel(String board, int numberOfPlayers) {
    // You don't need to implement this constructor.
    throw new UnsupportedOperationException("no need to implement this");
  }

  // You don't need to implement any methods or constructors. However,
  // if you want to make sure your code compiles, you could have your
  // IDE generate stubs for all the missing methods. This would also
  // allow you to make sure that your tests in StrictCoinGameModelTest
  // actually type check and compile against this class (though you
  // don’t need to make them pass, because you don’t need to implement
  // StrictCoinGameModel’s methods).

  @Override
  public int boardSize() {
    return 0;
  }

  @Override
  public int coinCount() {
    return 0;
  }

  @Override
  public int getCoinPosition(int coinIndex) {
    return 0;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public void move(int coinIndex, int newPosition) {

  }

  @Override
  public int whosTurn() {
    return 0;
  }

  @Override
  public int getWinner() {
    return 0;
  }

  @Override
  public void addPlayer() {

  }

  @Override
  public int playerCount() {
    return 0;
  }

  @Override
  public int turnCount() {
    return 0;
  }
}
