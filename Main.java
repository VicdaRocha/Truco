public class Main {
    public static void main(String[] args) {

        Baralho baralho = new Baralho();
        Jogador jogador1 = new Jogador("Victor");
        Jogador jogador2 = new Jogador("John");

        baralho.showBaralho();
        baralho.embaralhar();
        baralho.showBaralho();
        
        jogador1.novasCartas(baralho);
        jogador1.showMao();
        jogador1.showPontuacao();
        
        jogador2.novasCartas(baralho);
        jogador2.showMao();
        jogador2.showPontuacao();
    }
}
