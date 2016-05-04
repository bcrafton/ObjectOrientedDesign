import org.junit.Test;

import static org.junit.Assert.*;

public class CoinGameModelAdaptorTest {

  @Test
  public void test() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("----O", "Brian", "Dave");
    game.getCurrentPlayer().move(0, 1);
    int[] array = new int[1];
    array[0] = 1;
    assertArrayEquals(game.getCoinPositions(), array);
  }

  @Test
  public void gameNotOver() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("-O--O", "Brian", "Dave");
    assertNull(game.getWinner());
  }

  @Test
  public void testBoardSize() throws Exception {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("-O--O", "Brian", "Dave");
    assertEquals(game.boardSize(), 5);
  }

  @Test
  public void testCoinCount() throws Exception {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("-O--O", "Brian", "Dave");
    assertEquals(game.coinCount(), 2);
  }

  @Test
  public void testGetCoinPositions() throws Exception {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("-O--O", "Brian", "Dave");
    game.getCurrentPlayer().move(1, 3);
    game.getCurrentPlayer().move(0, 0);
    int[] array = new int[2];
    array[0] = 0;
    array[1] = 3;
    assertArrayEquals(game.getCoinPositions(), array);
  }

  @Test
  public void testGetPlayOrder() throws Exception {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("-O--O", "Brian", "Dave");
    game.getCurrentPlayer().move(1, 3);
    game.getCurrentPlayer().move(0, 0);

    CoinGamePlayer player = game.getCurrentPlayer();
    assertEquals(game.getCurrentPlayer().getName(), "Brian");

    assertEquals(game.addPlayerAfter(player, "Rick").getName(), "Rick");

    CoinGamePlayer[] players = game.getPlayOrder();
    assertEquals(players[0].getName(), "Brian");
    assertEquals(players[1].getName(), "Rick");
    assertEquals(players[2].getName(), "Dave");
  }

  @Test
  public void testGetWinner() throws Exception {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("-O--O", "Brian", "Dave");
    game.getCurrentPlayer().move(1, 3);
    game.getCurrentPlayer().move(0, 0);
    game.getCurrentPlayer().move(1, 1);
    assertEquals(game.getWinner().getName(), "Brian");
  }

  @Test
  public void testGetCurrentPlayer() throws Exception {
    NewCoinGameModel
        game =
        CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave", "Rick", "Dan", "Matt");
    assertEquals(game.getCurrentPlayer().getName(), "Brian");
    game.getCurrentPlayer().move(0, 8);
    assertEquals(game.getCurrentPlayer().getName(), "Dave");
    game.getCurrentPlayer().move(0, 7);
    assertEquals(game.getCurrentPlayer().getName(), "Rick");
    game.getCurrentPlayer().move(0, 6);
    assertEquals(game.getCurrentPlayer().getName(), "Dan");
    game.getCurrentPlayer().move(0, 5);
    assertEquals(game.getCurrentPlayer().getName(), "Matt");
    game.getCurrentPlayer().move(0, 4);
    assertEquals(game.getCurrentPlayer().getName(), "Brian");
    game.getCurrentPlayer().move(0, 3);
    assertEquals(game.getCurrentPlayer().getName(), "Dave");
    game.getCurrentPlayer().move(0, 2);
    assertEquals(game.getCurrentPlayer().getName(), "Rick");
    game.getCurrentPlayer().move(0, 1);
    assertEquals(game.getCurrentPlayer().getName(), "Dan");
  }

  @Test
  public void testAddPlayerAfter() throws Exception {
    CoinGamePlayer players[];

    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian");
    //make a move
    game.getCurrentPlayer().move(0, 8);
    // add a new player after current player
    game.addPlayerAfter(game.getCurrentPlayer(), "Dave");
    // get the play order
    players = game.getPlayOrder();
    // make sure the current player is still the first player,
    // since move was made before addition of new player
    assertEquals(game.getCurrentPlayer().getName(), "Brian");
    // make a move, turn belongs to second player
    game.getCurrentPlayer().move(0, 7);
    // add a player after the first player
    game.addPlayerAfter(players[0], "Rick");
    // make sure the turn still belongs to the first addition
    assertEquals(game.getCurrentPlayer().getName(), "Dave");
    // check the order
    players = game.getPlayOrder();
    assertEquals(players[0].getName(), "Brian");
    assertEquals(players[1].getName(), "Rick");
    assertEquals(players[2].getName(), "Dave");

    // make a move
    game.getCurrentPlayer().move(0, 6);
    assertEquals(game.getCurrentPlayer().getName(), "Brian");
    game.getCurrentPlayer().move(0, 5);
    assertEquals(game.getCurrentPlayer().getName(), "Rick");
    game.getCurrentPlayer().move(0, 4);
    assertEquals(game.getCurrentPlayer().getName(), "Dave");
    game.getCurrentPlayer().move(0, 3);
    assertEquals(game.getCurrentPlayer().getName(), "Brian");
    game.getCurrentPlayer().move(0, 2);
    assertEquals(game.getCurrentPlayer().getName(), "Rick");
    game.getCurrentPlayer().move(0, 1);
    assertEquals(game.getCurrentPlayer().getName(), "Dave");
  }

  @Test
  public void addPlayerToBack() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave", "Rick");
    CoinGamePlayer players[] = game.getPlayOrder();
    game.addPlayerAfter(players[players.length - 1], "Matt");

    assertEquals(game.getCurrentPlayer().getName(), "Brian");
    game.getCurrentPlayer().move(0, 8);
    assertEquals(game.getCurrentPlayer().getName(), "Dave");
    game.getCurrentPlayer().move(0, 7);
    assertEquals(game.getCurrentPlayer().getName(), "Rick");
    game.getCurrentPlayer().move(0, 6);
    assertEquals(game.getCurrentPlayer().getName(), "Matt");
    game.getCurrentPlayer().move(0, 5);

    assertEquals(game.getCurrentPlayer().getName(), "Brian");
    game.getCurrentPlayer().move(0, 4);
    assertEquals(game.getCurrentPlayer().getName(), "Dave");
    game.getCurrentPlayer().move(0, 3);
    assertEquals(game.getCurrentPlayer().getName(), "Rick");
    game.getCurrentPlayer().move(0, 2);
    assertEquals(game.getCurrentPlayer().getName(), "Matt");
  }

  @Test
  public void testPlayerCount() {
    NewCoinGameModel
        game =
        CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave", "Rick", "Dan", "Matt");
    CoinGamePlayer[] players = game.getPlayOrder();
    assertEquals(players.length, 5);
  }

  // shud be multiple cases, look at pset05 for all of them
  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void moveSamePlace() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave");
    game.getCurrentPlayer().move(0, 9);
  }

  // had to add to strict game code to account for moving to index that does not exist.
  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void moveOutOfBounds() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave");
    game.getCurrentPlayer().move(0, 10);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void moveToLeft() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave");
    game.getCurrentPlayer().move(0, 1);
    game.getCurrentPlayer().move(0, 4);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void moveNegativeLocation() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave");
    game.getCurrentPlayer().move(0, -1);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void moveNegativeCoin() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave");
    game.getCurrentPlayer().move(-1, 1);
  }

  @Test
  public void sameNameWorks() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave");
    game.addPlayerAfter(game.getCurrentPlayer(), "Brian");
    game.addPlayerAfter(game.getCurrentPlayer(), "Brian");
    game.addPlayerAfter(game.getCurrentPlayer(), "Brian");
    game.addPlayerAfter(game.getCurrentPlayer(), "Brian");

    CoinGamePlayer[] players = game.getPlayOrder();
    assertEquals(players[0].getName(), "Brian");
    assertEquals(players[1].getName(), "Brian");
    assertEquals(players[2].getName(), "Brian");
    assertEquals(players[3].getName(), "Brian");
    assertEquals(players[4].getName(), "Brian");
    assertEquals(players[5].getName(), "Dave");
  }

  @Test
  public void testEquals() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Dave");
    CoinGamePlayer player = game.getCurrentPlayer();
    player.equals(player);
  }

  @Test
  public void testNotEquals() {
    NewCoinGameModel game = CoinGameModelAdaptor.fromString("---------O", "Brian", "Brian");
    CoinGamePlayer[] players = game.getPlayOrder();
    players[0].equals(players[1]);
  }

}