package com.example.deyves.myapplicationtestegithub.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.deyves.myapplicationtestegithub.R;
import com.example.deyves.myapplicationtestegithub.helper.ConfiguracaoFireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import butterknife.BindView;
import butterknife.OnClick;


public class CadastroActivity extends AppCompatActivity {

    private Button botaoAcessar;
    private EditText campoEmail, campoSenha;
    private Switch opcaoSwitch;


    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if (!email.isEmpty()) {

                    if (!senha.isEmpty()) {
                        autenticacao = ConfiguracaoFireBase.getFirebaseAuth();

                        if (!opcaoSwitch.isChecked()) {

                            autenticacao.signInWithEmailAndPassword(email, senha)
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            Toast.makeText(
                                                    CadastroActivity.this,
                                                    "Autenticaçao realizada com sucesso",
                                                    Toast.LENGTH_LONG
                                            ).show();

                                            startActivity( new Intent( getApplicationContext(), AnuncioActivity.class ) );

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(
                                            CadastroActivity.this,
                                            "Erro ao autenticar",
                                            Toast.LENGTH_LONG
                                    ).show();

                                }
                            });

//Segunda forma de autenticar
//                            autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                    if (task.isSuccessful()) {
//
//                                        Toast.makeText(
//                                                MainActivity.this,
//                                                "Autenticaçao realizada com sucesso",
//                                                Toast.LENGTH_LONG
//                                        ).show();
//                                    } else {
//                                        Toast.makeText(MainActivity.this,
//                                                "Falha ao tentar realizar o acesso",
//                                                Toast.LENGTH_LONG
//                                        ).show();
//                                    }
//                                }
//                            });

                        } else {

                            autenticacao.createUserWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                                    } else {
                                        String errorExcecao;
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            errorExcecao = "Digite uma senha mais forte";
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            errorExcecao = "Digite um Email valido";
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            errorExcecao = "Esta conta ja foi cadastrada";
                                        } catch (Exception e) {
                                            errorExcecao = "Erro ao cadastradar usuario " + e.getMessage();
                                            e.printStackTrace();
                                        }

                                        Toast.makeText(CadastroActivity.this, errorExcecao, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }

                    } else {
                        Toast.makeText(CadastroActivity.this, "Informe a senha", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(CadastroActivity.this, "Digite o Email", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void inicializarComponentes() {

        botaoAcessar = findViewById(R.id.btnCadastrar);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        opcaoSwitch = findViewById(R.id.switch1);


    }


}
