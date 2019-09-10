package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

public class Users {

    private String name;
    private String password;

    public Users(String name, String password){

        this.password=password;
        this.name=name;

    }

    public Users (){



    }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}


}
