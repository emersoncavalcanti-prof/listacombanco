package com.ete.listacombanco.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ete.listacombanco.R;
import com.ete.listacombanco.conexao.Conexao;
import com.ete.listacombanco.controller.PessoaController;
import com.ete.listacombanco.model.Pessoa;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    EditText etNome,etCpf,etTelefone,etEmail,etSenha;
    Button btCadastrar;
    ActionBar actionBar;
    PessoaController pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.editTextNome);
        etCpf = findViewById(R.id.editTextCPF);
        etTelefone = findViewById(R.id.editTextTelefone);
        etEmail = findViewById(R.id.editTextEmail);
        etSenha = findViewById(R.id.editTextSenha);
        btCadastrar = findViewById(R.id.button);


        actionBar = getSupportActionBar();
        actionBar.setSubtitle("Cadastro de pessoa");

        pc =  new PessoaController(getBaseContext());


    }

    public boolean onCreateOptionsMenu(Menu menu) {



        MenuItem m1 = menu.add(1,1,1,"Pesquisar");
        m1.setIcon(R.drawable.list_light);
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {


            case 1:

                Intent i = new Intent(getBaseContext(), ListaActivity.class);
                startActivity(i);

                return true;



        }
        return true;
    }



    public void cadastrar(View v){

        if(etNome.getText().toString().equals("")){
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
        }else if(etSenha.getText().toString().equals("")){
            Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
        }else{
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(etNome.getText().toString());
            pessoa.setCpf(etCpf.getText().toString());
            pessoa.setTelefone(etTelefone.getText().toString());
            pessoa.setEmail(etEmail.getText().toString());
            pessoa.setSenha(etSenha.getText().toString());
            pc.inserir(pessoa);

            limparCampos();
            Snackbar snackbar = Snackbar
                    .make(btCadastrar, "Cadastrado com sucesso!", Snackbar.LENGTH_LONG);
            snackbar.show();

        }

    }

    public void limparCampos(){
        etNome.setText("");
        etCpf.setText("");
        etTelefone.setText("");
        etEmail.setText("");
        etSenha.setText("");
    }

}