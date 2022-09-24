package com.software.provap1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.provap1.bancoDados.AbastecimentoDB;
import com.software.provap1.bancoDados.DBHelper;
import com.software.provap1.entidades.Abastecimento;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText campoQuilometroAtual;
    EditText campoQuantidadeLitros;
    EditText campoData;
    EditText campoValor;
    TextView campoConsumo;

    Button botaoSalvar;

    List<Abastecimento> listaAbastecimento;
    ListView listaDados;
    ArrayAdapter adapter;

    AbastecimentoDB abastecimentoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(MainActivity.this);
        abastecimentoDB = new AbastecimentoDB(db);

        campoQuilometroAtual = findViewById(R.id.campoQuilometragem);
        campoQuantidadeLitros = findViewById(R.id.campoQuantidadeLitros);
        campoData = findViewById(R.id.campoData);
        campoValor = findViewById(R.id.campoValor);
        campoConsumo = findViewById(R.id.campoConsumo);
        listaDados = findViewById(R.id.listaAbastecimento);

        botaoSalvar = findViewById(R.id.botaoSalvar);

        listaAbastecimento = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaAbastecimento);
        listaDados.setAdapter(adapter);
        abastecimentoDB.listar(listaAbastecimento);

        campoConsumo.setText(String.format("%.2f", calcularConsumo()));
        acoesComponentes();
    }

    private float calcularConsumo() {
        Float consumo;

        consumo = 0f;

        return (consumo);
    }

    private void acoesComponentes() {
        listaDados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(view.getContext())
                        .setMessage("Deseja realmente remover?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                abastecimentoDB.remover(listaAbastecimento.get(i).getId());

                                abastecimentoDB.listar(listaAbastecimento);
                                adapter.notifyDataSetChanged();

                                Toast.makeText(MainActivity.this, "Removido com Sucesso!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create().show();
                return (false);
            }
        });

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Abastecimento abastecimento = new Abastecimento();

                abastecimento.setQuilometragemAtual(Float.parseFloat(campoQuilometroAtual.getText().toString()));
                abastecimento.setQuantidadeAbastecida(Float.parseFloat(campoQuantidadeLitros.getText().toString()));
                abastecimento.setData(campoData.getText().toString());
                abastecimento.setValor(Float.parseFloat(campoValor.getText().toString()));

                abastecimentoDB.inserir(abastecimento);
                abastecimentoDB.listar(listaAbastecimento);
                adapter.notifyDataSetChanged();
            }
        });
    }
}