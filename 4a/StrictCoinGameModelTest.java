import static org.junit.Assert.*;

import org.junit.Test;

public class StrictCoinGameModelTest extends AbstractCoinGameModelTest{

  protected AbstractCoinGameModel fromBoard(String board) {
    return new StrictCoinGameModel(board);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void movePathBlocked() {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("-O-O-");
    strictGame.move(1, 0);
  }
}