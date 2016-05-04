import static org.junit.Assert.*;

import org.junit.Test;

public class LaxCoinGameModelTest extends AbstractCoinGameModelTest{

  protected AbstractCoinGameModel fromBoard(String board) {
    return new LaxCoinGameModel(board);
  }

  @Test
  public void movePathBlocked() {
    LaxCoinGameModel laxGame = new LaxCoinGameModel("-O-O-");
    laxGame.move(1, 0);
    assertEquals("OO---", laxGame.toString());
  }
}
