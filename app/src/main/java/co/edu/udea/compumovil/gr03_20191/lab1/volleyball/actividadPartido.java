package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class actividadPartido extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private Boolean running;
    TextView equipo1Text;
    TextView equipo2Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_partido);

        chronometer=findViewById(R.id.timer);
        equipo1Text = (TextView)findViewById(R.id.equipo1Text);
        equipo2Text=(TextView)findViewById(R.id.equipo2Text);

        equipo1Text.setText(listaPartidos.equipo1);
        equipo2Text.setText(listaPartidos.equipo2);

    }

    public void IniciarCronometro(View view){

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


    }
}
