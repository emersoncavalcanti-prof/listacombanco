package com.ete.listacombanco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ete.listacombanco.R;
import com.ete.listacombanco.conexao.Conexao;
import com.ete.listacombanco.model.Usuario;

public class MainActivity extends AppCompatActivity {

    Conexao conn;
    EditText editTextUsuario,editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);



        conn = new Conexao(this);
        SQLiteDatabase db = conn.getReadableDatabase();
    }

    public void cadastrar(View v){

        if(editTextUsuario.getText().toString().equals("")){
            Toast.makeText(this, "Preencha o usuário", Toast.LENGTH_SHORT).show();
        }else if(editTextSenha.getText().toString().equals("")){
            Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
        }else{
            Usuario usuario = new Usuario();
            usuario.setUsuario(editTextUsuario.getText().toString());
            usuario.setSenha(editTextSenha.getText().toString());
            conn.inserirUsuario(usuario);

            editTextUsuario.setText("");
            editTextSenha.setText("");
            Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }

    }

}