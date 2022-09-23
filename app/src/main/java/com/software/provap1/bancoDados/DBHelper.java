package com.software.provap1.bancoDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "ListaTelefonica", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySQL =
                "create table listaAbastecimento(" +
                        "id integer primary key autoincrement," +
                        "quilometragemAtual float," +
                        "quantidadeAbastecida float," +
                        "data varchar(10)," +
                        "valor float)";

        sqLiteDatabase.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
