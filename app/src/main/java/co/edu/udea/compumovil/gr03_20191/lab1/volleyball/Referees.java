package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

public class Referees {

    String name;
    String password;

    public Referees(String name, String password){

        name=name;
        password=password;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

}
