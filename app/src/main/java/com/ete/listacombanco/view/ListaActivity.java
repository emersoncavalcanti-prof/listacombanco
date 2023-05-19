package com.ete.listacombanco.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ete.listacombanco.R;
import com.ete.listacombanco.adapters.AdapterPessoa;
import com.ete.listacombanco.conexao.Conexao;
import com.ete.listacombanco.controller.PessoaController;
import com.ete.listacombanco.model.Pessoa;
import com.google.android.material.snackbar.Snackbar;

public class ListaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    AdapterPessoa adp;
    Conexao conn;
    ActionBar actionBar;
    PessoaController pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Lista de Pessoas");

        actionBar.setSubtitle("Lista salva em SQLITE");

        actionBar.setDisplayHomeAsUpEnabled(true);

         pc = new PessoaController(getBaseContext());

        listView = findViewById(R.id.lista);
        listView.setTextFilterEnabled (true);

        preencheLista();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();

                return true;

        }
        return true;
    }

    public void preencheLista(){

        listView.setAdapter(null);

        //qtd = listItemVendas.size();

        adp = new AdapterPessoa(this,pc.retornar());
        listView.setAdapter(adp);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        Pessoa pessoa = adp.getItem(arg2);
        pessoa.getNome();

        Snackbar snackbar = Snackbar
                .make(listView,  pessoa.getNome(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}