package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
    int[] faltas1=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int[] faltas2=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    SharedPreferences.Editor preferences1;
    SharedPreferences.Editor preferences2;
    SharedPreferences preferences1retorna;
    SharedPreferences preferences2retorna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("JUGADORES");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);
        listView1=(ListView)findViewById(R.id.jugadoresEquipo1);
        listView2=(ListView)findViewById(R.id.jugadoresEquipo2);
        llenarListas();
        //preferences1.edit().putString("string","");
        //preferences2.edit().putString("string","");
        /*arrayAdapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, Jugador1);
        arrayAdapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, Jugador2);
        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);*/
        preferences1retorna=getSharedPreferences("string1", MODE_PRIVATE);
        preferences2retorna=getSharedPreferences("string2",MODE_PRIVATE);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                preferences1=getSharedPreferences("string1",MODE_PRIVATE).edit();


                if(faltas1[i+1]==0){

                    faltas1[i+1]++;

                }else if(faltas1[i+1]==1){

                    String jugadorExpulsado=listView1.getItemAtPosition(i).toString();
                    faltas1[i+1]++;
                    Toast.makeText(Equipos.this, "el jugador "+jugadorExpulsado+" debe de ser expulsado", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(Equipos.this, "EL JUGADOR YA FUE EXPULSADO", Toast.LENGTH_SHORT).show();

                }

                StringBuilder str = new StringBuilder();
                for(int j=0;j<faltas1.length;j++){

                    str.append(faltas1[j]).append(",");

                }
                preferences1.putString("string",str.toString());
                preferences1.apply();

            }

        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                preferences2=getSharedPreferences("string2",MODE_PRIVATE).edit();

                if(faltas2[i+1]==0){

                    faltas2[i+1]++;

                }else if(faltas2[i+1]==1){

                    String jugadorExpulsado2=listView2.getItemAtPosition(i).toString();
                    faltas2[i+1]++;
                    Toast.makeText(Equipos.this, "el jugador "+jugadorExpulsado2+" debe de ser expulsado", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Equipos.this, "EL JUGADOR YA FUE EXPULSADO", Toast.LENGTH_SHORT).show();
                }
                StringBuilder str2 = new StringBuilder();
                for(int j=0;j<faltas2.length;j++){

                    str2.append(faltas2[j]).append(",");

                }
                preferences2.putString("string",str2.toString());
                preferences2.apply();


            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        String savedString1=preferences1retorna.getString("string","");
        StringTokenizer st1 = new StringTokenizer(savedString1, ",");
        for (int j = 0; j < faltas1.length; j++) {
            faltas1[j] = Integer.parseInt(st1.nextToken());
        }
        String savedString2=preferences2retorna.getString("string","");
        StringTokenizer st2 = new StringTokenizer(savedString2, ",");
        for (int j = 0; j < faltas1.length; j++) {
            faltas2[j] = Integer.parseInt(st2.nextToken());
        }
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
