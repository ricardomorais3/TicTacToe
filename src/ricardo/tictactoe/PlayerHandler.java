package ricardo.tictactoe;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ricardo on 28-11-2016.
 */
public class PlayerHandler implements Runnable {

    private ObjectOutputStream outputStream;

    public PlayerHandler(Socket socket) {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }

    public void sendBoard(String[][] board) {
        try {
            outputStream.reset();
            outputStream.writeObject(board);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
