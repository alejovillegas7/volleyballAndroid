package co.edu.udea.compumovil.gr03_20191.lab1.volleyball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Security;
import java.security.SecurityPermission;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText password;
    life.sabujak.roundedbutton.RoundedButton buttonLogin, buttonRegister;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login or Referee Registration");

        name=(EditText)findViewById(R.id.nameText);
        password=(EditText)findViewById(R.id.passwordText);
        buttonLogin=(life.sabujak.roundedbutton.RoundedButton)findViewById(R.id.loginButton);
        buttonRegister=(life.sabujak.roundedbutton.RoundedButton)findViewById(R.id.registerButton);
        databaseReference= FirebaseDatabase.getInstance().getReference("referees");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd= null;
                pwd = password.getText().toString();

                login(name.getText().toString(), pwd);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addReferee();

            }
        });

    }

    private void addReferee(){

        String name = this.name.getText().toString();
        String password = this.password.getText().toString();

        if(TextUtils.isEmpty(name)){

            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(password)){

            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();

        }else{

            Referees referees= new Referees(name, password);
            databaseReference.child(name).child("name").setValue(name.toString());
            databaseReference.child(name).child("password").setValue(password.toString());
            Toast.makeText(this, "Referee added", Toast.LENGTH_SHORT).show();
            clearText();

        }

    }

    private void clearText(){

        name.setText("");
        password.setText("");

    }




    private void login(final String name, final String password){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(name).exists()){
                    if(!name.isEmpty()){

                        Users user = dataSnapshot.child(name).getValue(Users.class);
                        if(user.getPassword().equals(password)){

                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Intent intentEquipos = new Intent(getApplicationContext(), listaPartidos.class);
                            startActivity(intentEquipos);

                        }else{

                            Toast.makeText(MainActivity.this, "Password or username incorrect", Toast.LENGTH_SHORT).show();
                            
                        }

                    }else{

                        Toast.makeText(MainActivity.this, "User is not register yet", Toast.LENGTH_SHORT).show();
                        
                    }
                }else{

                    Toast.makeText(MainActivity.this, "User is not register yet", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
