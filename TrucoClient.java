import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TrucoClient {
    public static void main(String[] args) throws IOException {

        final String SERVER_ADDRESS = "localhost";
        final int PORT = 12345;

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

                String linha;
                while ((linha = in.readLine()) != null) {
                    System.out.println("Servidor: " + linha);
        
                    // Se o servidor pediu algo, vamos responder:
                    if (linha.toLowerCase().contains("digite")) {
                        System.out.print("Você: ");
                        String resposta = keyboard.readLine();
                        out.println(resposta);
                    }

                    if (linha.toLowerCase().contains("[")) {
                        System.out.print("Você: ");
                        String resposta = keyboard.readLine();
                        out.println(resposta);
                    }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
