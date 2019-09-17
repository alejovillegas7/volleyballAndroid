package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

public class Jugador {

    public static String numero="";
    public static int faltas=0;

    public Jugador(String numero, int faltas){

        numero=numero;
        faltas=faltas;

    }

    public String getNumero(){
        return numero;
    }

    public void setNumero(String numero){
        this.numero=numero;
    }

    public int getFaltas(){
        return faltas;
    }

    public void setFaltas(int faltas){
        this.faltas=faltas;
    }

}
