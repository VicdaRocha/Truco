import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TrucoServer {

    public static void main(String[] args) {

        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Aguardando o primeiro jogador...");

            List<Jogador> jogadores = new ArrayList<>();
            String modo = "";
            int maxJogadores = 0;

            Socket primeiroSocket = serverSocket.accept();
            System.out.println("Primeiro jogador conectado.");

            Jogador primeiroJogador = new Jogador(primeiroSocket);
            primeiroJogador.out.println("Digite seu apelido:");
            String nome = primeiroJogador.in.readLine();
            primeiroJogador.setNome(nome);
            primeiroJogador.out.println("Bem-vindo, " + nome);

            while (true) {
                primeiroJogador.out.println("Selecione o modo de jogo:");
                primeiroJogador.out.println("[0] Simples (1x1);[1] Duplas (2x2)");
                modo = primeiroJogador.in.readLine();

                if (modo.equals("0")) {
                    maxJogadores = 2;
                    primeiroJogador.out.println("Modo Simples selecionado.");
                    break;
                } else if (modo.equals("1")) {
                    maxJogadores = 4;
                    primeiroJogador.out.println("Modo Duplas selecionado.");
                    break;
                } else {
                    primeiroJogador.out.println("Modo inválido. Tente novamente.");
                }
            }

            jogadores.add(primeiroJogador);

            while (jogadores.size() < maxJogadores) {
                System.out.println("Aguardando mais jogadores...");
                Socket playerSocket = serverSocket.accept();
                System.out.println("Jogador conectado.");

                Jogador jogador = new Jogador(playerSocket);
                jogador.out.println("Digite seu apelido:");
                nome = jogador.in.readLine();
                jogador.setNome(nome);
                jogador.out.println("Bem-vindo, " + jogador.getNome());

                jogadores.add(jogador);
            }

            System.out.println("Todos os jogadores conectados!");
            jogadores.forEach(
                    jogador -> System.out.println("Jogador " + jogadores.indexOf(jogador) + ": " + jogador.getNome()));

            Truco jogo = new Truco(jogadores);
            jogo.darAsCartas();
            jogo.vira();
            jogo.showVira(jogadores);
            jogo.showManilha(jogadores);
            jogo.showOrdem(jogadores);

            // if (modo == "0") {

            //     Jogador[] t1 = {jogadores.get(0)};
            //     Jogador[] t2 = {jogadores.get(1)};
            // }
            // if (modo == "1") {

            //     Jogador[] t1 = {jogadores.get(0), jogadores.get(2)};
            //     Jogador[] t2 = {jogadores.get(1), jogadores.get(3)};
            // }

            // RODADA
            // jogo.askTruco(jogadores, 0, 1);
            // for (Jogador jogador : jogadores) {
            
            //     jogador.out.println("Trucar?");
            //     String resposta = jogador.in.readLine();
            //     boolean trucou = resposta.equals("s") ? true : false;
            //     if (trucou) {
            //         int jogadorTruco = jogadores.indexOf(jogador);
            //         System.out.println(jogador.getNome() + "trucou");
            //         break;
            //     }
            // }

            for (int rodada = 0; rodada <= 2; rodada++){

                jogadores.forEach(jogador -> {
                    jogador.showMao();
                });

                List<Carta> cartasJogadas = new ArrayList<Carta>();
                jogadores.forEach(jogador -> {
                    jogador.out.println("Escolha o índice da carta a ser jogada.");
                    String selecao = jogador.in.readLine();
                    switch(selecao){
                        case "0":
                            Carta cartaJogada = jogador.jogarCarta(0);
                            break;
                        case "1":
                            Carta cartaJogada = jogador.jogarCarta(1);
                            break;
                        case "2":
                            Carta cartaJogada = jogador.jogarCarta(2);
                            break;
                    }
                    cartasJogadas.add(cartaJogada);
                });

                int indiceVencedor = jogo.verificarCartasJogadas(cartasJogadas);
                Jogador vencedor = jogadores.get(indiceVencedor)
                vencedor.pontuar(1);

                jogadores.forEach(jogador -> jogador.showPontuacao());
            }


            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
