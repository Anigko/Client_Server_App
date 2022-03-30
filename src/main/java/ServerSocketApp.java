import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerSocketApp {

    public static void main(String[] args) throws IOException {
        int port = 8089;

        while (true) {

            try (ServerSocket serverSocket = new ServerSocket(port); // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
                 Socket clientSocket = serverSocket.accept(); // ждем подключения
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {

                out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                final String name = in.readLine();

                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            }
        }
    }
}