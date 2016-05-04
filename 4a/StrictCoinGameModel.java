
public class StrictCoinGameModel extends AbstractCoinGameModel{
  /**
   * <p>Implementation Note: Class Invariants
   * {@code board} cannot be null
   * {@code board} must contain only 'O' and '-' characters
   * Moves must be made in the left direction
   * Moves cannot be made to a position occupied by a coin
   * Moves cannot be made past another coin
   * Moves cannot be made if the game is over
   * </p>
   */

  /**
   * Constructs a StrictCoinGameModel object with specified configuration
   *     parameters.
   * @param board the string containing the game board.
   */
  public StrictCoinGameModel(String board) {
    super(board);
  }

  @Override
  public void move(int coinIndex, int newPosition) {
    int currentPosition = getCoinPosition(coinIndex);
    if (!isOccupied(newPosition) && this.isPathClear(coinIndex, newPosition)
        && isLeftMove(coinIndex, newPosition) && !isGameOver()) {
      swap(currentPosition, newPosition);
    } else {
      throw new IllegalMoveException();
    }
  }

  /**
   * Checks if the path is clear between {@code coinIndex} and
   *     {@code newPosition}.
   * @param coinIndex which coin to move
   * @param newPosition where to move it to
   * @return true of false if it is clear or not
   */
  private boolean isPathClear(int coinIndex, int newPosition) {
    int coinPosition = this.getCoinPosition(coinIndex);

    for (int indexCounter = coinPosition - 1; indexCounter > newPosition;
         indexCounter--) {
      if (isOccupied(indexCounter)) {
        return false;
      }
    }
    return true;
  }
}
