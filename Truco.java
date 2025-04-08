import java.util.ArrayList;
import java.util.List;

public class Truco {

    private Baralho baralho = new Baralho();
    public Jogador jogador1;
    public Jogador jogador2;
    private Carta vira;
    private String manilha;
    private List<String> ordem = new ArrayList<>();
    public List<Jogador> jogadores = new ArrayList<>();

    // Truco(Jogador jogador1, Jogador jogador2){
    //     this.jogador1 = jogador1;
    //     this.jogador2 = jogador2;
    //     this.baralho.embaralhar();
    // }
    Truco(List<Jogador> jogadores){
        this.jogadores = jogadores;
        this.baralho.embaralhar();
    }

    public void darAsCartas(){
        this.baralho.embaralhar();

        for (Jogador jogador : this.jogadores){
            jogador.novasCartas(this.baralho);
        }
        // this.jogador1.novasCartas(this.baralho);
        // this.jogador2.novasCartas(this.baralho);
    }

    public void vira(){

        Carta vira = this.baralho.cavar();
        this.vira = vira;

        this.makeManilha(vira);
    }

    private void makeManilha(Carta vira){

        String[] valores = this.baralho.getValores();
        int cont_valores = valores.length;
        for (int i = 0; i < cont_valores; i++){

            if (vira.getValor() == valores[i]){

                boolean ultimoValor = i == cont_valores - 1;
                i = ultimoValor ? 0 : i + 1;
                this.manilha = valores[i];
                this.makeOrdem(i);
                break;
            }
        }

    }

    private void makeOrdem(int indexManilha){

        this.ordem.clear();
        String[] valores = this.baralho.getValores();
        int cont_valores = valores.length;

        for (int i = indexManilha; i < cont_valores; i++){

            if (this.ordem.size() < cont_valores){
                this.ordem.add(valores[i]);
            }
            if (this.ordem.size() == cont_valores){
                break;
            }

            i = i == cont_valores - 1 ? -1 : i;
        }
    }

    public void showVira(){
        System.out.println("Vira:\n" + this.vira.getValor() + " de " + this.vira.getNaipe() + "\n");
    }


    public void showInfoJogadores(){
        for (Jogador jogador : this.jogadores){
            jogador.showMao();
            jogador.showPontuacao();
        }
    }

    public void showInfoPartida(){
        this.showVira();
        this.showManilha();
        this.showOrdem();
    }

    public void showManilha(){
        System.out.println("Manilha:\n" + this.manilha + "\n");
    }

    public void showOrdem(){
        System.out.println("Ordem: " + this.ordem);
    }

    public void rodada(Carta cartaJogador1, Carta cartaJogador2){

        String valorCartaJogador1 = cartaJogador1.getValor();
        String valorCartaJogador2 = cartaJogador2.getValor();
        String naipeCartaJogador1 = cartaJogador1.getNaipe();
        String naipeCartaJogador2 = cartaJogador2.getNaipe();
        String[] naipes = this.baralho.getNaipes();

        if (valorCartaJogador1 == valorCartaJogador2 && naipeCartaJogador1 == naipeCartaJogador2){
            // Empate
            System.out.println("Empate");
        }
        if (valorCartaJogador1 == valorCartaJogador2 && naipeCartaJogador1 != naipeCartaJogador2){
            // Verifica naipe
            int indexNaipeCartaJogador1 = 0;
            int indexNaipeCartaJogador2 = 0;
            for (int i = 0; i < naipes.length; i++){
                indexNaipeCartaJogador1 = naipeCartaJogador1 == naipes[i] ? i : indexNaipeCartaJogador1;
                indexNaipeCartaJogador2 = naipeCartaJogador2 == naipes[i] ? i : indexNaipeCartaJogador2;
            }
            
            if (indexNaipeCartaJogador1 < indexNaipeCartaJogador2){
                System.out.println("Vencedor: " + this.jogador1.getNome());
                this.jogador1.pontuar(1);
            }
            if (indexNaipeCartaJogador1 > indexNaipeCartaJogador2){
                System.out.println("Vencedor: " + this.jogador2.getNome());
                this.jogador2.pontuar(1);    
            }
        }
        if (valorCartaJogador1 != valorCartaJogador2 && this.ordem.indexOf(valorCartaJogador1) < this.ordem.indexOf(valorCartaJogador2)){
            System.out.println("Vencedor: " + this.jogador1.getNome());
            this.jogador1.pontuar(1);
        }
        if (valorCartaJogador1 != valorCartaJogador2 && this.ordem.indexOf(valorCartaJogador1) > this.ordem.indexOf(valorCartaJogador2)){
            System.out.println("Vencedor: " + this.jogador2.getNome());
            this.jogador2.pontuar(1);            
        }
        
    }
}
