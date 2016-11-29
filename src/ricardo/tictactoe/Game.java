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

        PlayerHandler firstPlayer;
        PlayerHandler secondPlayer;

        if (Math.random() < 0.5) {
            firstPlayer = p1Handler;
            secondPlayer = p2Handler;
        } else {
            firstPlayer = p2Handler;
            secondPlayer = p1Handler;
        }

        String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

        firstPlayer.sendObject(true);
        secondPlayer.sendObject(false);

        int counter = 0;

        while (!firstPlayer.isReady() || !secondPlayer.isReady()) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (counter < 5) {

            firstPlayer.sendObject(board);
            board = (String[][]) firstPlayer.getObject();
            secondPlayer.sendObject(board);
            board = (String[][]) secondPlayer.getObject();

            counter++;
        }
    }
}
