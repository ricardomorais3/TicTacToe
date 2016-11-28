package ricardo.tictactoe;

/**
 * Created by codecadet on 28/11/16.
 */
public class Game {

    private PlayerHandler p1Handler;
    private PlayerHandler p2Handler;

    public Game(PlayerHandler p1Handler, PlayerHandler p2Handler) {
        this.p1Handler = p1Handler;
        this.p2Handler = p2Handler;
    }

    public void start() {
        String[][] board = {{" ", " ", " "},{" ", " ", " "},{" ", " ", " "}};
        p1Handler.sendBoard(board);
        p2Handler.sendBoard(board);
    }
}
