import java.util.ArrayList;
import java.util.Iterator;


public class CoinGameModelAdaptor implements NewCoinGameModel {

  CoinGameModel adaptee;
  ArrayList<NewCoinGamePlayer> coinGamePlayers;

  /**
   * the constructor for the CoinGameModelAdaptor class
   *
   * @param adaptee a reference to a CoinGameModel object
   */
  public CoinGameModelAdaptor(CoinGameModel adaptee) {
    this.adaptee = adaptee;
    coinGamePlayers = new ArrayList<NewCoinGamePlayer>();
  }

  /**
   * static factory method to create CoinGameModelAdaptors
   *
   * @param initialBoard the initial game board
   * @param players      a variable length argument containing the player names
   * @return a new CoinGameModelAdaptor
   */
  public static CoinGameModelAdaptor fromString(String initialBoard, String... players) {
    // create a new coin game with the board.
    CoinGameModel coinGame = new StrictCoinGameModel(initialBoard, players.length);
    // create a new adapter with the coin game.
    CoinGameModelAdaptor adapter = new CoinGameModelAdaptor(coinGame);
    // keep the players names and add all the new players.
    for (int playerCounter = 0; playerCounter < players.length; playerCounter++) {
      NewCoinGamePlayer
          player =
          new NewCoinGamePlayer(players[playerCounter], playerCounter, adapter.adaptee);
      adapter.coinGamePlayers.add(player);
    }
    return adapter;
  }

  @Override
  public int boardSize() {
    return adaptee.boardSize();
  }

  @Override
  public int coinCount() {
    return adaptee.coinCount();
  }

  @Override
  public int[] getCoinPositions() {
    int[] coinPosition = new int[coinCount()];
    for (int coinCounter = 0; coinCounter < coinCount(); coinCounter++) {
      coinPosition[coinCounter] = adaptee.getCoinPosition(coinCounter);
    }
    return coinPosition;
  }

  @Override
  public CoinGamePlayer[] getPlayOrder() {
    return coinGamePlayers.toArray(new NewCoinGamePlayer[coinGamePlayers.size()]);
  }

  @Override
  public CoinGamePlayer getWinner() {
    Integer winner;
    try {
      winner = adaptee.getWinner();
    } catch (IllegalStateException e) {
      return null;
    }
    Iterator<NewCoinGamePlayer> itr = coinGamePlayers.listIterator();
    NewCoinGamePlayer current = null;

    while (itr.hasNext()) {
      current = itr.next();
      if (current.getPosition().intValue()
          == winner) {
        return current;
      }
    }
    return current;
  }

  @Override
  public CoinGamePlayer getCurrentPlayer() {
    Iterator<NewCoinGamePlayer> itr = coinGamePlayers.listIterator();
    NewCoinGamePlayer current;

    while (itr.hasNext()) {
      current = itr.next();
      if (current.getPosition() == adaptee.whosTurn()) {
        return current;
      }
    }
    return null;
  }

  @Override
  public CoinGamePlayer addPlayerAfter(CoinGamePlayer predecessor, String name) {
    if (name == null || predecessor == null) {
      throw new NullPointerException();
    } else {
      boolean predecessorFound = false;
      final int playerCount = coinGamePlayers.size();
      NewCoinGamePlayer player = null;
      for (int playerCounter = 0; playerCounter < playerCount; playerCounter++) {
        if (coinGamePlayers.get(playerCounter).equals(predecessor)) {
          player = new NewCoinGamePlayer(name, playerCount, adaptee);
          coinGamePlayers.add(playerCounter + 1, player);

          adaptee.addPlayer(playerCounter + 1);

          predecessorFound = true;
        }
      }
      if (!predecessorFound) {
        throw new IllegalArgumentException();
      } else {
        return player;
      }
    }

  }

  private static class NewCoinGamePlayer implements CoinGamePlayer {

    private String name;
    private Integer playerID;
    private CoinGameModel game;

    /**
     * constructs a NewCoinGamePlayer
     *
     * @param name     the name of the player
     * @param playerID the position of the player in the order of the game
     * @param game     a reference to the game object
     */
    private NewCoinGamePlayer(String name, Integer playerID, CoinGameModel game) {
      this.name = name;
      this.playerID = playerID;
      this.game = game;
    }

    @Override
    public String getName() {
      return new String(name);
    }

    /**
     * return ths position of the player in the order of the game
     *
     * @return ths position of the player in the order of the game
     */
    public Integer getPosition() {
      return new Integer(playerID);
    }

    @Override
    public void move(int coinIndex, int newPosition) {
      if (isTurn()) {
        try {
          this.game.move(coinIndex, newPosition);
        } catch (Exception e) {
          throw new CoinGameModel.IllegalMoveException();
        }
      } else {
        throw new CoinGameModel.IllegalMoveException();
      }
    }

    @Override
    public boolean isTurn() {
      return (playerID == this.game.whosTurn());
    }

    @Override
    public boolean equals(Object o) {
      NewCoinGamePlayer player = (NewCoinGamePlayer) o;
      return player == this;
    }

    @Override
    public int hashCode() {
      return this.name.hashCode();
    }
  }
}

