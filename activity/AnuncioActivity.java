package com.example.deyves.myapplicationtestegithub.activity;

import com.example.deyves.myapplicationtestegithub.R;
import com.example.deyves.myapplicationtestegithub.helper.ConfiguracaoFireBase;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AnuncioActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);

        autenticacao = ConfiguracaoFireBase.getFirebaseAuth();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        if (autenticacao.getCurrentUser() != null){
            menu.setGroupVisible(R.id.group_logado, true);
        }else{
            menu.setGroupVisible(R.id.group_deslogado, true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_cadastrar:
                startActivity( new Intent( getApplicationContext(), CadastroActivity.class ) );
                break;

            case R.id.menu_sair :
                deslogarUsuario();
                invalidateOptionsMenu();
//                startActivity( new Intent( getApplicationContext(), CadastroActivity.class ) );
                break;

            case R.id.menu_anuncios:
                startActivity( new Intent( AnuncioActivity.this, MeusAnuncioActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario(){

        autenticacao.signOut();

        Toast.makeText(AnuncioActivity.this, "Usuario deslogado", Toast.LENGTH_LONG).show();

    }
}
