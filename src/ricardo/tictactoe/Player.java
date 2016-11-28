package ricardo.tictactoe;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ricardo on 28-11-2016.
 */
public class Player {

    private ObjectInputStream inputStream;

    public static void main(String[] args) {

        Player player = new Player();
        player.start();
    }

    public void start(){
        try {
            Socket socket = new Socket(InetAddress.getByName("192.168.1.13"), 8000);
            inputStream = new ObjectInputStream(socket.getInputStream());

            String[][] board = (String[][])inputStream.readObject();
            printBoard(board);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void printBoard(String[][] board){

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
