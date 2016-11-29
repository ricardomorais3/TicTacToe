package ricardo.tictactoe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ricardo on 28-11-2016.
 */
public class Server {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        try {
            serverSocket = new ServerSocket(8000);

            Socket socketP1 = serverSocket.accept();
            System.out.println(socketP1);
            PlayerHandler p1Handler = new PlayerHandler(socketP1);

            Thread thread1 = new Thread(p1Handler);
            thread1.start();

            Socket socketP2 = serverSocket.accept();
            System.out.println(socketP2);
            PlayerHandler p2Handler = new PlayerHandler(socketP2);

            Thread thread2 = new Thread(p2Handler);
            thread2.start();

            Game game = new Game(p1Handler, p2Handler);
            game.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
