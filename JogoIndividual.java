public class JogoIndividual {

    private Baralho baralho = new Baralho();
    private Jogador jogador1;
    private Jogador jogador2;
    private Carta manilha;

    JogoIndividual(Jogador jogador1, Jogador jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.baralho.embaralhar();
    }

    public void darAsCartas(){
        this.baralho.embaralhar();
        this.jogador1.novasCartas(this.baralho);
        this.jogador2.novasCartas(this.baralho);
    }

    public vira(){
        
    }
}
