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
    public int[] valorRodada = {1,1,1};

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

    public int verificarCartasJogadas(List<Carta> cartas){

        List<int> valorJogador = new ArrayList<>();
        cartas.forEach(carta -> {
            this.baralho.cartaUsada(carta);
            valorJogador.add(this.baralho.ordem.indexOf(carta.getValor()));
        });
        int maiorValorJogado = 9;
        valorJogador.forEach(valor -> {
            maiorValorJogado = valor <= maiorValorJogado ? valor : maiorValorJogado;
        })

        if (maiorValorJogado == 9) {
            //verificar naipe
        }
        if (maiorValorJogado != 9) {
            return maiorValorJogado;
        }
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

    // public void askTruco(List<Jogador> jogadores, int rodada, int valor){

        
    // }

    public void showInfoJogadores(){
        for (Jogador jogador : this.jogadores){
            jogador.showMao();
            jogador.showPontuacao();
        }
    }

    public void showVira(List<Jogador> jogadores){

        for (Jogador jogador : jogadores) {
            
            jogador.out.println("Vira:" + this.vira.getValor() + " de " + this.vira.getNaipe());
        }
    }

    public void showManilha(List<Jogador> jogadores){

        for (Jogador jogador : jogadores) {
            
            jogador.out.println("Manilha:" + this.manilha);
        }
    }

    public void showOrdem(List<Jogador> jogadores){

        for (Jogador jogador : jogadores) {
                    
            jogador.out.println("Ordem: " + this.ordem);
        }
    }

    public void showMaoJogadores(){
        
        for (Jogador jogador : this.jogadores) {
            System.out.println("inicio");
            jogador.showMao();
            System.out.println("fim");
        }
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
