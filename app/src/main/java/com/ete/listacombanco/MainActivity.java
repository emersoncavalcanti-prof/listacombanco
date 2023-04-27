package com.ete.listacombanco;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ete.listacombanco.conexao.Conexao;

public class MainActivity extends AppCompatActivity {

    Conexao conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new Conexao(this);
        SQLiteDatabase db = conn.getReadableDatabase();
    }
}