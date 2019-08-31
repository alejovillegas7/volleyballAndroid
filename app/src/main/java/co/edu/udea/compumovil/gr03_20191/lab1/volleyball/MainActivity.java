package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbReference = database.getReference("name");
        dbReference.setValue("Andres");
        dbReference.setValue("Alejo");

        /*DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();//referencia a nuestra base de datos
        Map<String,String> valoresEjemplo = new HashMap<>();

        valoresEjemplo.put("name", "Alejo");

        dbReference.push().setValue(valoresEjemplo, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null){

                    Log.i("info","Save sucessful");

                }else{
                    Log.i("info", "save failed");
                }
            }
        });*/
    }
}
