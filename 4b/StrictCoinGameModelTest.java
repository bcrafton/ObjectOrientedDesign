import static org.junit.Assert.*;

import org.junit.Test;

public class StrictCoinGameModelTest{

  @Test
  public void getWinner() {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("-0---");
    strictGame.addPlayer();
    strictGame.addPlayer();
    strictGame.move(0,0);
    assertEquals(0, strictGame.getWinner());
  }

  @Test
  public void turnCount() {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("----O");
    strictGame.addPlayer();
    strictGame.move(0, 4);
    strictGame.move(0, 3);
    assertEquals(strictGame.turnCount(), 2);
  }

  @Test
  public void addPlayer() {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("----O", 3);
    strictGame.addPlayer();
  }

  @Test
  public void numberOfPlayers() {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("----O", 3);
    assertEquals(strictGame.playerCount(), 3);
  }

  @Test
  public void nextPlayer(){
    StrictCoinGameModel strictGame = new StrictCoinGameModel("----O");
    strictGame.addPlayer();
    strictGame.addPlayer();
    assertEquals(strictGame.whosTurn(), 0);
    strictGame.move(0, 4);
    assertEquals(strictGame.whosTurn(), 1);
    strictGame.move(0, 3);
    assertEquals(strictGame.whosTurn(), 0);
  }

  @Test(expected = IllegalStateException.class)
  public void noWinner()
  {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("----O");
    strictGame.getWinner();
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void moveNoPlayers()
  {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("----O");
    strictGame.move(0,0);
  }

  @Test
  public void moveSuccessful()
  {
    StrictCoinGameModel strictGame = new StrictCoinGameModel("----O");
    strictGame.addPlayer();
    strictGame.move(0,0);
  }





}