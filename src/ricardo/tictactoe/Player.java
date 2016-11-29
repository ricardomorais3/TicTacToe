package ricardo.tictactoe;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ricardo on 28-11-2016.
 */
public class Player {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public static void main(String[] args) {

        Player player = new Player();
        player.start();
    }

    public void start() {


        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 8000);
            inputStream = new ObjectInputStream(socket.getInputStream());

            String[][] board = (String[][]) inputStream.readObject();
            printBoard(board);

            board[1][1] = "X";
            int count = 0;
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (count<5) {
                outputStream.reset();
                outputStream.writeObject(board);
                outputStream.flush();
                board = (String[][]) inputStream.readObject();
                printBoard(board);
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
