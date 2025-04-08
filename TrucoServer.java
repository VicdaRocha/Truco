import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TrucoServer {

    public static void main(String[] args) {

        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Aguardando Jogadores...");

            List<Jogador> jogadores = new ArrayList<>();
            int i = 0;
            String modo;
            while (i <= 4) {

                Socket playerSocket = serverSocket.accept();
                System.out.println("Jogador Conectado.");

                Jogador jogador = new Jogador(playerSocket);
                jogador.out.println("Digite seu apelido");
                String nome = jogador.in.readLine();
                jogador.setNome(nome);
                jogador.out.println("Bem vindo, " + jogador.getNome());
                jogadores.add(jogador);

                if (i == 0) {
                    jogador.out.println("Selecione o modo de jogo:");
                    jogador.out.println("[0] Simples;[1] Duplas.");
                    modo = jogador.in.readLine();
                    switch (modo) {
                        case "0":
                            i += 2;
                            jogador.out.println("Jogo Simples");
                            break;
                        case "1":
                            jogador.out.println("Jogo em Duplas");
                            break;
                        default:
                            jogador.socket.close();
                            jogadores.remove(jogadores.indexOf(jogador));
                            continue;
                    }
                }
                i++;
            }

            Truco jogo = new Truco(jogadores);
            jogo.darAsCartas();
            jogo.vira();
            jogo.showVira();
            jogo.showManilha();
            jogo.showOrdem();
            jogo.showInfoJogadores();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
