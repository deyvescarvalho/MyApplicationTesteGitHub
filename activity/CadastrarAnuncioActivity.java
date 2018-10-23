package com.example.deyves.myapplicationtestegithub.activity;

import android.support.v7.app.AppCompatActivity;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.deyves.myapplicationtestegithub.R;
import com.santalu.widget.MaskEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarAnuncioActivity extends AppCompatActivity {

    @BindView(R.id.editProdutoId)
    EditText campoProduto;

    @BindView(R.id.editDescriaoId)
    EditText campoDescricao;

    @BindView(R.id.editValorId)
    CurrencyEditText campoValor;

    @BindView(R.id.editTelefoneId)
    MaskEditText campoTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        ButterKnife.bind(this);




    }

    @OnClick(R.id.btnCadastrarAnuncioId)
    public void cadastrarAnuncio(View view){
        Toast.makeText(CadastrarAnuncioActivity.this, campoTelefone.getRawText(), Toast.LENGTH_LONG).show();
    }
}
