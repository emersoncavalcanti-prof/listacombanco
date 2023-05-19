package com.ete.listacombanco.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ete.listacombanco.conexao.Conexao;
import com.ete.listacombanco.model.Pessoa;

import java.util.ArrayList;

public class PessoaController {

    private SQLiteDatabase db;
    private Conexao conexao;

    public PessoaController(Context context){
        conexao = new Conexao(context);
    }

    public ArrayList<Pessoa> retornar() {

        Cursor c = null;
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {
            c = conexao.getReadableDatabase().
                    rawQuery("select * from pessoa order by id desc", null);

            while (c.moveToNext()) {
                Pessoa p = new Pessoa();
                p.setNome(c.getString(c.getColumnIndexOrThrow("nome")));
                p.setCpf(c.getString(c.getColumnIndexOrThrow("cpf")));
                p.setTelefone(c.getString(c.getColumnIndexOrThrow("telefone")));
                p.setEmail(c.getString(c.getColumnIndexOrThrow("email")));
                pessoas.add(p);

            }
        }catch (Exception e){

        }
        finally {
            c.close();
        }


        return pessoas;
    }

    public void atualizar(Pessoa pessoa){
        try{

            Cursor cursor = conexao.getWritableDatabase().
                    rawQuery("update pessoa set" +
                            " nome = '" +pessoa.getNome()+
                            "' ,cpf = '" +pessoa.getCpf()+
                            "' ,email = '" +pessoa.getEmail()+
                            "' ,senha = '" +pessoa.getSenha()+
                            "' ,telefone = '" +pessoa.getTelefone()+
                            "' where id = "+pessoa.getId(),null);
            cursor.moveToFirst();

        }catch (SQLException ex){
            Log.e("ERRO:",ex.getMessage());
        }
    }

    public Cursor excluir(String id){

        Cursor cursor = conexao.getWritableDatabase().
                rawQuery("delete from pessoa where id = "+id,null);
        cursor.moveToFirst();
        return cursor;
    }



    public void inserir(Pessoa pessoa){
        try{
            ContentValues valores = new ContentValues();

            valores.put("nome",pessoa.getNome());
            valores.put("cpf",pessoa.getCpf());
            valores.put("telefone",pessoa.getTelefone());
            valores.put("email",pessoa.getEmail());
            valores.put("senha",pessoa.getSenha());

            conexao.getWritableDatabase().insert("pessoa",
                    null,valores);

        }catch (SQLException ex){
            Log.e("ERRO",ex.getMessage());
        }
    }

}
