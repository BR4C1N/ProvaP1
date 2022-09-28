package com.software.provap1.bancoDados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.software.provap1.entidades.Abastecimento;

import java.util.List;

public class AbastecimentoDB {

    private DBHelper db;
    private SQLiteDatabase conexao;

    public AbastecimentoDB(DBHelper db) {
        this.db = db;
    }

    public void inserir(Abastecimento abastecimento) {
        conexao = db.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("quilometragemAtual", abastecimento.getQuilometragemAtual());
        valores.put("quantidadeAbastecida", abastecimento.getQuantidadeAbastecida());
        valores.put("data", abastecimento.getData());
        valores.put("valor", abastecimento.getValor());

        conexao.insertOrThrow("listaAbastecimento", null, valores);

        conexao.close();
    }

    public void remover(Integer id) {
        conexao = db.getWritableDatabase();

        conexao.delete("listaAbastecimento", "id=?", new String[]{id + ""});

        conexao.close();
    }

    public void listar(List listaAbastecimento) {
        listaAbastecimento.clear();
        conexao = db.getReadableDatabase();

        String colunas[] = {"id", "quilometragemAtual", "quantidadeAbastecida", "data", "valor"};
        Cursor query = conexao.query("listaAbastecimento", colunas, null, null, null, null, "quilometragemAtual");

        while (query.moveToNext()) {
            Abastecimento abastecimento = new Abastecimento();

            abastecimento.setId(query.getInt(0));
            abastecimento.setQuilometragemAtual(query.getFloat(1));
            abastecimento.setQuantidadeAbastecida(query.getFloat(2));
            abastecimento.setData(query.getString(3));
            abastecimento.setValor(query.getFloat(4));

            listaAbastecimento.add(abastecimento);
        }

        conexao.close();
    }
}
