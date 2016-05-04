import static org.junit.Assert.*;

import org.junit.Test;

public abstract class AbstractCoinGameModelTest {

  protected abstract AbstractCoinGameModel fromBoard(String board);

  @Test
  public void testToString() throws Exception {
    AbstractCoinGameModel game = fromBoard("OO-O-");
    assertEquals(game.toString(), "OO-O-");
  }

  @Test
  public void testCoinCount() throws Exception {
    AbstractCoinGameModel game = fromBoard("OO-O-");
    assertEquals(game.coinCount(), 3);
  }

  @Test
  public void testBoardSize() throws Exception {
    AbstractCoinGameModel game = fromBoard("OO-O-");
    assertEquals(game.boardSize(), 5);
  }

  @Test
  public void testGetCoinPosition() throws Exception {
    AbstractCoinGameModel game = fromBoard("OO-O-");
    assertEquals(game.getCoinPosition(0), 0);
    assertEquals(game.getCoinPosition(1), 1);
    assertEquals(game.getCoinPosition(2), 3);
  }

  @Test
  public void testIsOccupied() throws Exception {
    AbstractCoinGameModel game = fromBoard("OO-O-");
    assertTrue(game.isOccupied(0));
    assertFalse(game.isOccupied(2));
  }

  @Test
  public void testSwap() throws Exception {
    AbstractCoinGameModel game = fromBoard("OO-O-");
    game.swap(3,2);
    assertEquals(game.toString(), "OOO--");
  }

  @Test
  public void testIsGameOver() throws Exception {
    assertTrue(fromBoard("OO---").isGameOver());
    assertFalse(fromBoard("O-O--").isGameOver());

  }

  @Test
  public void testValidMove() {
    AbstractCoinGameModel game = fromBoard("-O--O");
    game.move(0, 0);
    assertEquals(game.toString(), "O---O");
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testOccupiedPosition() {
    AbstractCoinGameModel game = fromBoard("-O--O");
    game.move(1, 1);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testSamePosition() {
    AbstractCoinGameModel game = fromBoard("-O--O");
    game.move(1, 4);
  }


  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testRightMove() {
    AbstractCoinGameModel game = fromBoard("-O--O");
    game.move(0, 2);
  }

  @Test
  public void gameIsOver() {
    assertTrue(fromBoard("OO---").isGameOver());
  }

  @Test
  public void gameNotOver() {
    assertFalse(fromBoard("O-O--").isGameOver());
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void moveWhenGameIsOver() {
    AbstractCoinGameModel game = fromBoard("OO---");
    game.move(0, 0);
  }

  @Test
  public void allCoins() {
    AbstractCoinGameModel game = fromBoard("OOOOO");
    assertTrue(game.isGameOver());
  }

  @Test
  public void noCoins() {
    AbstractCoinGameModel game = fromBoard("-----");
    assertTrue(game.isGameOver());
  }

  @Test
  public void emptyBoard() {
    AbstractCoinGameModel game = fromBoard("");
    assertTrue(game.isGameOver());
  }

}