package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

public class Partidos {

    private String equipo1;
    private String equipo2;
    private int id;

    public Partidos(String equipo1, String equipo2){

        this.equipo1=equipo1;
        this.equipo2=equipo2;

    }

    public String getEquipo1(){return equipo1;}

    public String getEquipo2(){return equipo2;}

    public void setEquipo1(){this.equipo1=equipo1;}

    public void setEquipo2(){this.equipo2=equipo2;}

    /*public Partidos(String equipo1, String equipo2){

        this.equipo1=equipo1;
        this.equipo2=equipo2;

    }*/

    public Partidos(){

    }

    public String toString(){

        return " "+equipo1+" vs "+equipo2;

    }


}
