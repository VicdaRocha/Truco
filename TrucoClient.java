import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TrucoClient {
    public static void main(String[] args) throws IOException {

        final String SERVER_ADDRESS = "localhost";
        final int PORT = 12345;

        try (
            Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
             ) {

                String linha;
                while ((linha = in.readLine()) != null) {
                    // System.out.println("Servidor: " + linha);
                    System.out.println(linha);
        
                    if (linha.toLowerCase().contains("digite")) {
                        System.out.print("Você: ");
                        String resposta = keyboard.readLine();
                        out.println(resposta);
                    }

                    if (linha.toLowerCase().contains("[0")) {
                        System.out.print("Você: ");
                        String resposta = keyboard.readLine();
                        out.println(resposta);
                    }

                    if (linha.toLowerCase().contains("ordem")) {
                        System.out.println("Iniciando Jogo.");
                    }

                    if (linha.toLowerCase().contains("trucar")) {
                        System.out.print("Você: ");
                        String resposta = keyboard.readLine();
                        out.println(resposta);
                    }

                    if (linha.toLowerCase().contains("Escolha o índice da carta a ser jogada.")) {
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
