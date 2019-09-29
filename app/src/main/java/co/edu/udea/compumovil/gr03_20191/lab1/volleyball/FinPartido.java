package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinPartido extends AppCompatActivity {

    TextView equipo1;
    TextView equipo2;
    TextView puntosEquipo1;
    TextView puntosEquipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_partido);
        setTitle("Set is Over");
        equipo1=(TextView) findViewById(R.id.textViewEquipo1);
        equipo2=(TextView) findViewById(R.id.textViewEquipo2);
        puntosEquipo1=(TextView)findViewById(R.id.textViewPuntuacion1);
        puntosEquipo2=(TextView)findViewById(R.id.textViewPuntuacion2);
        puntosEquipo1.setText(Integer.toString(actividadPartido.puntosdeEquipo1));
        puntosEquipo2.setText(Integer.toString(actividadPartido.puntodequipo2));
        equipo1.setText(listaPartidos.equipo1);
        equipo2.setText(listaPartidos.equipo2);
    }

    public void jugarOtroSet(View view){
        Intent intent=new Intent(getApplicationContext(), actividadPartido.class);
        startActivity(intent);
        actividadPartido.puntosdeEquipo1=0;
        actividadPartido.puntodequipo2=0;
        actividadPartido.puntuacionMaxima=25;
    }

    public void volverLista(View view){

        Intent intent = new Intent(getApplicationContext(), listaPartidos.class);
        startActivity(intent);
        actividadPartido.puntosdeEquipo1=0;
        actividadPartido.puntodequipo2=0;
        actividadPartido.puntuacionMaxima=25;
    }
}
