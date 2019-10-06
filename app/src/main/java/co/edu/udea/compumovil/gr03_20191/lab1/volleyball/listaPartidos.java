package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class listaPartidos extends AppCompatActivity {

    public static String equipo1;
    public static String equipo2;

    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_partidos);
        setTitle("Partidos");

        databaseReference = FirebaseDatabase.getInstance().getReference("partidos");
        listView=(ListView)findViewById(R.id.listaPartidos);
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String value = dataSnapshot.getValue(Partidos.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor preferences1=getSharedPreferences("string1",MODE_PRIVATE).edit();
                SharedPreferences.Editor preferences2=getSharedPreferences("string2",MODE_PRIVATE).edit();
                SharedPreferences preferences1retorna;
                SharedPreferences preferences2retorna;
                StringBuilder str=new StringBuilder();
                StringBuilder str2= new StringBuilder();
                for(int j=0;j<Equipos.faltas1.length;j++){

                    str.append(0).append(",");

                }
                for(int j=0;j<Equipos.faltas2.length;j++){

                    str2.append(0).append(",");

                }

                preferences1.putString("string",str.toString());
                preferences1.apply();
                preferences2.putString("string",str2.toString());
                preferences2.apply();
                String cadenaSeleccionada = (listView.getItemAtPosition(i).toString());
                String[] equipos=cadenaSeleccionada.split(" vs ");
                equipo1 = equipos[0];
                equipo2 = equipos[1];
                Intent intent = new Intent(listaPartidos.this, actividadPartido.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
