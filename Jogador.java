import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Jogador {

    private String nome;
    private int pontuacao = 0;
    public ArrayList<Carta> cartas = new ArrayList<Carta>();
    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;

    Jogador(Socket socket) throws IOException {

        // this.nome = nome;
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void novasCartas(Baralho baralho) {

        for (int i = 0; i < 3; i++) {
            this.cartas.add(baralho.cavar());
        }
    }

    public void getMao() {
        this.out.println("Sua mão:");
        this.cartas.forEach(carta -> this.out.println(carta));
    }

    // public void showMao() {

    // this.out.println("Mão de " + this.nome + ":");
    // for (Carta carta : this.cartas) {
    // this.out.println(this.cartas.indexOf(carta) + " - " + carta.getValor() + " de
    // " + carta.getNaipe());
    // }
    // }
    public void showMao() {
        
        this.out.println("Suas cartas:");
        for (Carta carta : this.cartas) {
            this.out.println(this.cartas.indexOf(carta) + " - " + carta.getValor() + " de " + carta.getNaipe());
        }

        // this.out.println("Sua mão, " + this.nome + ":");
        // for (int i = 0; i < this.cartas.size(); i++) {
        //     Carta carta = this.cartas.get(i);
        //     this.out.println(i + " - " + carta.getValor() + " de " + carta.getNaipe());
        // }
    }

    public void pontuar(int pontos) {

        this.pontuacao += pontos;
    }

    public void showPontuacao() {
        this.out.println(this.nome + ": " + this.pontuacao + " pontos.");
    }

    public Carta jogarCarta(int index) {

        Carta carta = this.cartas.get(index);
        this.cartas.remove(index);

        carta.showCarta();

        return carta;
    }
}
