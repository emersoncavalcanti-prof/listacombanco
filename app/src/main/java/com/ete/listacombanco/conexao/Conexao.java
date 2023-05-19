package com.ete.listacombanco.conexao;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ete.listacombanco.model.Pessoa;
import com.ete.listacombanco.model.Usuario;

import java.util.ArrayList;


public class Conexao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meubanco.db";
    private static final int DATABASE_VERSION = 1;

    public Conexao (Context ctx) {
        super (ctx, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            db.execSQL("CREATE TABLE pessoa ( "+
                    "id INTEGER PRIMARY KEY autoincrement, " +
                    "nome VARCHAR(80), " +
                    "cpf VARCHAR(11), " +
                    "telefone VARCHAR(15)," +
                    "senha varchar(32),"+
                    "email VARCHAR(100));");

            db.execSQL("CREATE TABLE usuario ( "+
                    "id INTEGER PRIMARY KEY autoincrement, " +
                    "usuario VARCHAR(100), " +
                    "senha varchar(32));");




        }catch (SQLException ex){
            Log.e("AULA",ex.getMessage());
        }

    }




}
