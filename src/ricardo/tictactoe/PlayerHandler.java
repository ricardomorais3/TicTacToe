package ricardo.tictactoe;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ricardo on 28-11-2016.
 */
public class PlayerHandler implements Runnable {

    private Socket socket;
    private ObjectOutputStream outputStream;

    public PlayerHandler(Socket socket) {
        this.socket = socket;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int count = 0;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (count < 5) {
                String[][] board = (String[][]) inputStream.readObject();
                printBoard(board);

                outputStream.reset();
                outputStream.writeObject(board);
                outputStream.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        count++;
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

    public void printBoard(String[][] board) {

        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
