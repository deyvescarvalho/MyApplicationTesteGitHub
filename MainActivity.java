package com.example.deyves.myapplicationtestegithub;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deyves.myapplicationtestegithub.classes.Usuario;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase referencia = FirebaseDatabase.getInstance();
    private DatabaseReference referenciaUsuario = referencia.getReference("usuarios");
    private Usuario usuario;

    private Button botaoCadastrar;
    private TextInputEditText nome;
    private TextInputEditText email;
    private TextInputEditText idade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nomeUsuario);
        email = findViewById(R.id.emailUsuario);
        idade = findViewById(R.id.idadeUsuario);







    }



    public void cadastrarUsuario(View view){

        if (usuario == null){

            usuario = new Usuario();
        }




        try{
            usuario.setIdade(Integer.parseInt(idade.getText().toString()));
            usuario.setNome(nome.getText().toString());
            usuario.setEmail(email.getText().toString());
            referenciaUsuario.child(UUID.randomUUID().toString()).setValue(usuario);

            idade.setText("");
            nome.setText("");
            email.setText("");
            nome.requestFocus();

            Toast.makeText(this, "Usuario cadastrado com sucesso", Toast.LENGTH_LONG).show();
            usuario = null;
        }catch (Error e){
            e.getMessage();
        }





    }
}
