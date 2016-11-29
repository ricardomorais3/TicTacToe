package ricardo.tictactoe;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by ricardo on 28-11-2016.
 */
public class Player {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private boolean firstToPlay;
    private Scanner scanner;
    private String symbol;

    public static void main(String[] args) {

        Player player = new Player();
        player.start();
    }

    public void start() {

        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 8000);
            inputStream = new ObjectInputStream(socket.getInputStream());

            // The Game sends this flag before sending the board
            firstToPlay = (boolean) inputStream.readObject();

            if (firstToPlay) {
                symbol = "X";
            } else {
                symbol = "O";
            }

            scanner = new Scanner(System.in);

            int col;
            int row;

            outputStream = new ObjectOutputStream(socket.getOutputStream());

            String[][] board;

            int counter = 0;

            while (counter < 5) {

                board = (String[][]) inputStream.readObject();
                printBoard(board);

                row = scanner.nextInt();
                col = scanner.nextInt();

                board[row][col] = symbol;

                if(checkVictory(board)){
                    System.out.println("You WIN!!!");
                }

                outputStream.reset();
                outputStream.writeObject(board);
                outputStream.flush();

                counter++;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean checkVictory(String[][] board) {
        if ((board[0][0].equals(symbol) && board[0][1].equals(symbol) && board[0][2].equals(symbol)) ||
                (board[1][0].equals(symbol) && board[1][1].equals(symbol) && board[1][2].equals(symbol)) ||
                (board[2][0].equals(symbol) && board[2][1].equals(symbol) && board[2][2].equals(symbol)) ||
                (board[0][0].equals(symbol) && board[1][0].equals(symbol) && board[2][0].equals(symbol)) ||
                (board[0][1].equals(symbol) && board[1][1].equals(symbol) && board[2][1].equals(symbol)) ||
                (board[0][2].equals(symbol) && board[1][2].equals(symbol) && board[2][2].equals(symbol)) ||
                (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)) ||
                (board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol))){
            return true;
        }
        return false;
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
