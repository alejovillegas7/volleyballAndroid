package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Equipos extends AppCompatActivity {

    TextView title1, title2;
    ListView listView1, listView2;
    ArrayList<String> players1=new ArrayList<>();
    ArrayList<String> players2= new ArrayList<>();
    ArrayAdapter<String>arrayAdapter1;
    ArrayAdapter<String>arrayAdapter2;
    private int numeroFaltas1=0;
    private int numeroFaltas2=0;
    private long lastItemSelected1=0;
    private long lastItemSelected2=0;
    //private boolean isItemClicked=false;
    Jugador[] Jugador1 = new Jugador[31];
    Jugador[] Jugador2 = new Jugador[31];//{"#1","#2","#3","#4","#5","#6","#7","#8","#9","#10","#11","#12","#13","#14","#15","#16","#17","#18","#19","#20","#21","#22","#23","#24","#25","#26","#27","#28","#29","#30",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("JUGADORES");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);
        listView1=(ListView)findViewById(R.id.jugadoresEquipo1);
        listView2=(ListView)findViewById(R.id.jugadoresEquipo2);
        llenarListas();
        /*arrayAdapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, Jugador1);
        arrayAdapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, Jugador2);
        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);*/

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                boolean isItemClicked=false;

                if(!isItemClicked){

                    isItemClicked = true;

                    Toast.makeText(Equipos.this, "1", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(Equipos.this, "2", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    public void llenarListas(){

        String []items1=new String[31];
        String []items2=new String[31];
        items1[0]="PLAYERS OF "+listaPartidos.equipo1;
        items2[0]="PLAYERS OF "+listaPartidos.equipo2;

        for(int i=1;i<=30;i++){

            Jugador1[i-1]=new Jugador("#"+i,0);
            Jugador2[i-1]=new Jugador("#"+i,0);

            Jugador1[i-1].setNumero("#"+i);
            Jugador2[i-1].setNumero("#"+i);

            items1[i]=Jugador1[i-1].getNumero();
            items2[i]=Jugador2[i-1].getNumero();


        }

        arrayAdapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, items1);
        arrayAdapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, items2);
        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);

    }

    /*private void resetIsItemClicked() {
        isItemClicked = false;
    }*/
}
