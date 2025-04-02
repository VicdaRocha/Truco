import java.util.ArrayList;

public class Jogador {
    
    private String nome;
    private int pontuacao = 0;
    private ArrayList<Carta> cartas = new ArrayList<Carta>();

    Jogador(String nome){

        this.nome = nome;
    }

    public void novasCartas(Baralho baralho){

        for(int i = 0; i < 3; i++){
            this.cartas.add(baralho.cavar());
        }
    }

    public void showMao(){

        System.out.println("\nMão de :" + this.nome);
        for(Carta carta : this.cartas){
            System.out.println(carta.getValor() + " de " + carta.getNaipe());
        }
    }

    public void pontuar(int pontos){

        this.pontuacao += pontos;
    }

    public void showPontuacao(){
        System.out.println(this.nome + ": " + this.pontuacao + " pontos.\n");
    }

    public Carta jogarCarta(int index){

        Carta carta = this.cartas.get(index);
        this.cartas.remove(index);
        return carta;
    }
}
