import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
    
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    private ArrayList<Carta> cartas_usadas = new ArrayList<Carta>();
    private String[] naipes = {"Paus", "Copas", "Espada", "Ouro"};
    private String[] valores = {"4","5","6","7","Q","J","K","A","2","3"};

    Baralho(){

        for(int naipe = 0; naipe < naipes.length; naipe++){
            for (int valor = 0; valor < valores.length; valor++){
                this.cartas.add(new Carta(this.valores[valor],this.naipes[naipe]));
            }
        }
    }

    public void showBaralho(){
        System.out.println("\nBaralho:");
        for (Carta carta : this.cartas){
            System.out.println(carta.getValor() + " de " + carta.getNaipe());
        }
    }
     
    public void embaralhar(){
        Collections.shuffle(this.cartas);
    }

    public Carta cavar(){
        Carta carta = this.cartas.get(0);
        this.cartas.remove(0);
        return carta;
    }

    public void cartaUsada(Carta carta){
        this.cartas_usadas.add(carta);
    }
}
