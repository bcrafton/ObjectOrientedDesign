
public abstract class AbstractCoinGameModel implements CoinGameModel{
  /**
   * <p>Implementation Note: Class Invariants
   * {@code board} cannot be null
   * {@code board} must contain only 'O' and '-' characters
   * Moves must be made in the left direction
   * Moves cannot be made to a position occupied by a coin
   * Moves cannot be made if the game is over
   * </p>
   */

  private String board;

  /**
   * Constructor for the the abstract model for the coin game
   *     with the specified configuration parameters.
   * @param board the string containing the game board.
   * @throws NullPointerException if the board is Null
   * @throws IllegalArgumentException if board contains an invalid value
   *     (not O or -)
   */
  public AbstractCoinGameModel(String board) {
    this.board = board;
    final int boardSize = this.boardSize();
    if (board == null) {
      throw new NullPointerException("Board cannot be null");
    }
    for (int counter = 0; counter < boardSize; counter++) {
      if (board.charAt(counter) != 'O'
          && this.board.charAt(counter) != '-') {
        throw new IllegalArgumentException("The board contains "
                                           + "an invalid character:"
                                           + " position = " + counter);
      }
    }
  }

  /**
   * Returns a deep copy of the game {@code board}.
   * @return a copy of the game {@code board}
   */
  @Override
  public String toString() {
    return new String(board);
  }

  @Override
  public int coinCount() {
    final int stringLength = this.boardSize();
    int coinCount = 0;
    for (int indexCounter = 0; indexCounter < stringLength; indexCounter++) {
      if (board.charAt(indexCounter) == 'O') {
        coinCount++;
      }
    }
    return coinCount;
  }

  @Override
  public int boardSize() {
    return this.board.length();
  }

  @Override
  public int getCoinPosition(int coinIndex) {
    if (coinIndex >= coinCount()) {
      throw new IllegalArgumentException();
    }

    final int stringLength = this.boardSize();
    int coinCount = 0;

    for (int indexCounter = 0; indexCounter < stringLength; indexCounter++) {
      if (this.board.charAt(indexCounter) == 'O') {
        if (coinIndex == coinCount) {
          return indexCounter;
        } else {
          coinCount++;
        }
      }
    }
    return -1;
  }

  /**
   * Checks whether {@code newPosition} is occupied.
   * @param coinPosition the position that is being checked
   * @return true or false, whether or not the {@code coinPosition} is occupied
   */
  protected boolean isOccupied(int coinPosition) {
    return (board.charAt(coinPosition) == 'O');
  }

  /**
   * Swaps coin character in {@code currentPosition} with character in
   * {@code newPosition}.
   * @param currentPosition the current position of the coin to be moved
   * @param newPosition the new position the coin is being moved to
   */
  protected void swap(int currentPosition, int newPosition) {
    char[] temp = board.toCharArray();
    temp[currentPosition] = '-';
    temp[newPosition] = 'O';
    board = String.valueOf(temp);
  }

  @Override
  public boolean isGameOver() {
    final int coinCount = this.coinCount();
    for (int indexCounter = 0; indexCounter < coinCount; indexCounter++) {
      if (board.charAt(indexCounter) != 'O') {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks to see if the move being made is to the left.
   * @param coinIndex the coin number from the left
   * @param newPosition the position the coin is being moved to
   * @return whether the coin is moving to the left or not.
   */
  protected boolean isLeftMove(int coinIndex, int newPosition) {
    return newPosition < getCoinPosition(coinIndex);
  }
}
