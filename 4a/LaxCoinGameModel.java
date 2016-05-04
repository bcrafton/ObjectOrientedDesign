
public class LaxCoinGameModel extends AbstractCoinGameModel {
  /**
   * <p>Implementation Note: Class Invariants
   * {@code board} cannot be null
   * {@code board} must contain only 'O' and '-' characters
   * Moves must be made in the left direction
   * Moves cannot be made to a position occupied by a coin
   * Moves cannot be made if the game is over
   * </p>
   */

  /**
   * Constructs a LaxGameModel object with specified configuration parameters.
   * @param board the string containing the game board.
   */
  public LaxCoinGameModel(String board) {
    super(board);
  }

  @Override
  public void move(int coinIndex, int newPosition) {
    int currentPosition = getCoinPosition(coinIndex);
    if (!isOccupied(newPosition) && isLeftMove(coinIndex, newPosition)
        && !isGameOver()) {
      swap(currentPosition, newPosition);
    } else {
      throw new IllegalMoveException();
    }
  }
}
