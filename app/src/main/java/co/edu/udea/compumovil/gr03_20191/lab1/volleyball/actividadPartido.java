package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class actividadPartido extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private Boolean running = false;
    public static TextView equipo1Text;
    public static TextView equipo2Text;
    public static int puntosdeEquipo1=0;
    public static int puntodequipo2=0;
    public static Button botonequipo1;
    public static Button botonequipo2;
    Button agregarFaltaBoton;
    public static int puntuacionMaxima=25;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_partido);

        setTitle("Set Activity");

        chronometer=findViewById(R.id.timer);
        equipo1Text = (TextView)findViewById(R.id.equipo1Text);
        equipo2Text=(TextView)findViewById(R.id.equipo2Text);

        equipo1Text.setText(listaPartidos.equipo1);
        equipo2Text.setText(listaPartidos.equipo2);

        botonequipo1=(Button)findViewById(R.id.puntosEquipo1);
        botonequipo2=(Button)findViewById(R.id.puntosEquipo2);
        agregarFaltaBoton=(Button)findViewById(R.id.agregarFaltaButton);

        if(savedInstanceState!=null) {
            int rpuntosdeEquipo1 = savedInstanceState.getInt("puntosEquipo1");
            int rpuntodequipo2 = savedInstanceState.getInt("puntosEquipo2");
            puntosdeEquipo1=rpuntosdeEquipo1;
            puntodequipo2=rpuntodequipo2;
            botonequipo1.setVisibility(View.VISIBLE);
            botonequipo2.setVisibility(View.VISIBLE);
            botonequipo1.setText(Integer.toString(puntosdeEquipo1));
            botonequipo2.setText(Integer.toString(puntodequipo2));
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            running=true;

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt("puntosEquipo1", puntosdeEquipo1);
        outState.putInt("puntosEquipo2", puntodequipo2);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            puntosdeEquipo1 = savedInstanceState.getInt("puntosEquipo1");
            puntodequipo2 = savedInstanceState.getInt("puntosEquipo2");
            botonequipo1.setVisibility(View.VISIBLE);
            botonequipo2.setVisibility(View.VISIBLE);
            botonequipo1.setText(Integer.toString(puntosdeEquipo1));
            botonequipo2.setText(Integer.toString(puntodequipo2));
        }
    }

    protected void onRestart() {
        super.onRestart();
        if(!running){

            botonequipo1.setVisibility(View.VISIBLE);
            botonequipo2.setVisibility(View.VISIBLE);

            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            running=true;

        }

    }

    public void agregarFalta(View view){

        if(running){

            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
            running=false;

        }

        Intent intent=new Intent(getApplicationContext(), Equipos.class);
        startActivity(intent);

    }

    public void sumarPuntos1(View view){

            puntosdeEquipo1++;
            botonequipo1.setText(Integer.toString(puntosdeEquipo1));
            hayGanador(Integer.parseInt(botonequipo1.getText().toString()), Integer.parseInt(botonequipo2.getText().toString()));

    }

    public void sumarPuntos2(View view){

        puntodequipo2++;
        botonequipo2.setText(Integer.toString(puntodequipo2));
        hayGanador(Integer.parseInt(botonequipo1.getText().toString()), Integer.parseInt(botonequipo2.getText().toString()));

    }

    public void hayGanador(int puntos1, int puntos2){

        if((puntos1-puntos2==1 && puntuacionMaxima-puntos1==0) || (puntos2-puntos1==1 && puntuacionMaxima-puntos2==0)){

            puntuacionMaxima++;

        }

        if(puntos1==puntuacionMaxima && puntos1>puntos2 && puntos1-puntos2>=2){

            Toast.makeText(this, "El ganador de este Set fué "+listaPartidos.equipo1, Toast.LENGTH_SHORT).show();
            if(running){

                chronometer.stop();
                pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
                running=false;

            }
            Intent intent = new Intent(getApplicationContext(),FinPartido.class);
            startActivity(intent);

        }else if(puntos2==puntuacionMaxima && puntos1<puntos2 && puntos2-puntos1>=2){

            Toast.makeText(this, "El ganador de este Set fué "+listaPartidos.equipo2, Toast.LENGTH_SHORT).show();
            if(running){

                chronometer.stop();
                pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
                running=false;

            }
            Intent intent = new Intent(getApplicationContext(),FinPartido.class);
            startActivity(intent);

        }else{

            return;

        }


    }

    public void IniciarCronometro(View view){

        botonequipo2.setVisibility(View.VISIBLE);
        botonequipo1.setVisibility(View.VISIBLE);
        agregarFaltaBoton.setVisibility(View.VISIBLE);


        if(!running){

            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            running=true;

        }

    }

    public void pausarCronometro(View view){

        if(running){

            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
            running=false;

        }

    }

    public void reiniciarCronometro(View view){

        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;
        puntodequipo2=0;
        puntosdeEquipo1=0;
        botonequipo2.setText(Integer.toString(puntodequipo2));
        botonequipo1.setText(Integer.toString(puntosdeEquipo1));
        puntuacionMaxima=25;


    }
}
