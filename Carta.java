public class Carta {

    private String valor;
    private String naipe;

    Carta(String valor, String naipe){
        this.valor = valor;
        this.naipe = naipe;
    }

    protected String getValor(){
        return this.valor;
    }

    protected String getNaipe(){
        return this.naipe;
    }
}
