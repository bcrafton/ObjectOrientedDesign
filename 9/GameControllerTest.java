import org.junit.Test;

import java.io.InputStream;

public class GameControllerTest {

  NetworkClientTester.GameRunner gameRunner = (InputStream input, Appendable output) -> {
    Model model = new Board();
    BoardView view = new BoardView(output);
    Controller controller = new GameController(model, view, output, input);
    controller.reset();
    while (!controller.gameOver()) {
      controller.step();
    }
  };

  @Test
  public void test1() throws Exception {
    NetworkClientTester.assertGameMatches(gameRunner, 1, 1, 1);
  }

  @Test
  public void test2() throws Exception {
    NetworkClientTester.assertGameMatches(gameRunner, 'a', 'b', 'c');
  }

  @Test
  public void test3() throws Exception {
    NetworkClientTester.assertGameMatches(gameRunner, "Brian", "Crafton", "Kevin", "Wilson");
  }

  @Test
  public void test4() throws Exception {
    NetworkClientTester
        .assertGameMatches(gameRunner, 2, 1, 1, 3, 1, 3, 2, 1, 1, 2, 2, 3, 3, 2, 3, 1, 3, 1, 1, 3,
                           2, 1, 1, 1, 2, 2, 1, 3, 1, 3, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 3, 2,
                           2, 1, 3, 2, 1, 1, 1, 1, 1, 3, 3, 1, 2, 3, 3, 2, 2, 3, 2, 2, 3, 1, 2, 1,
                           3, 2, 3, 1, 2, 2, 3, 2, 2, 2, 3, 3, 2, 3, 3, 1, 2, 3, 3, 2, 3, 1, 2, 3,
                           2, 2, 3, 1, 3, 1, 2, 2, 1, 3, 2, 3, 1, 2, 3, 3, 1, 2, 3, 3, 3, 1, 3, 2,
                           1, 1, 1, 2, 1, 3, 2, 2, 1, 2, 1, 3, 3, 3, 3, 3, 1, 1, 1, 1, 2, 2, 3, 1,
                           1, 3, 2, 1, 1, 1, 1, 2, 2, 1, 3, 2, 3, 2, 3, 1, 3, 1, 2, 3, 2, 1, 2, 1,
                           3, 1, 3, 1, 3, 1, 3, 3, 2, 1, 1, 3, 3, 3, 1, 3, 1, 1, 3, 2, 2, 1, 3, 1,
                           3, 3, 1, 1, 3, 3, 3, 1, 1, 1, 3, 1, 2, 3, 1, 2, 1, 3, 2, 3, 2, 3, 1, 2,
                           2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 3, 2, 3, 2, 3, 1, 1, 3, 2, 2, 3, 1, 3, 3,
                           2, 3, 1, 3, 3, 3, 3, 3, 3, 1, 2, 1, 1, 2, 2, 1, 1, 3, 1, 1, 1, 1, 3, 2,
                           3, 1, 3, 1, 2, 1, 3, 2, 3, 1, 3, 3, 1, 3, 1, 1, 3, 2, 1, 1, 3, 1, 2, 2,
                           3, 2, 1, 1, 2, 2, 1, 1, 3, 2, 1, 1, 3, 1, 1, 1);
  }

}