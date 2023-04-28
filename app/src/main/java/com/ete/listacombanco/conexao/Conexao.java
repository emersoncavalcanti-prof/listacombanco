package com.ete.listacombanco.conexao;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ete.listacombanco.model.Pessoa;


public class Conexao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "banco.db";
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
                    "tipo char(1),"+
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

    public Cursor retornarPessoas(){

        Cursor cursor = getReadableDatabase().
                rawQuery("select * from pessoa",null);

        return cursor;
    }

    public void atualizarPessoa(Pessoa pessoa){
        try{

            Cursor cursor = getWritableDatabase().
                    rawQuery("update pessoa set" +
                            " nome = '" +pessoa.getNome()+
                            "' ,cpf = '" +pessoa.getCpf()+
                            "' ,email = '" +pessoa.getEmail()+
                            "' ,senha = '" +pessoa.getSenha()+
                            "' ,telefone = '" +pessoa.getTelefone()+
                            "' ,tipo = '" +pessoa.getTipo()+
                            "' where id = "+pessoa.getId(),null);
            cursor.moveToFirst();

        }catch (SQLException ex){
            Log.e("ERRO:",ex.getMessage());
        }
    }

    public Cursor excluirPessoa(String id){

        Cursor cursor = getWritableDatabase().
                rawQuery("delete from pessoa where id = "+id,null);
        cursor.moveToFirst();
        return cursor;
    }

    public void inserirPessoa(Pessoa pessoa){
        try{
            ContentValues valores = new ContentValues();

            valores.put("nome",pessoa.getNome());
            valores.put("cpf",pessoa.getCpf());
            valores.put("telefone",pessoa.getTelefone());
            valores.put("email",pessoa.getEmail());
            valores.put("senha",pessoa.getSenha());
            valores.put("tipo",pessoa.getTipo());

            getWritableDatabase().insert("pessoa",
                    null,valores);

        }catch (SQLException ex){
            Log.e("ERRO",ex.getMessage());
        }
    }


}
