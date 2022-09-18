package com.adriano.agendadealunos.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.adriano.agendadealunos.R;
import com.adriano.agendadealunos.models.Aluno;
import com.adriano.agendadealunos.ui.activity.ConstantsActivities;
import com.adriano.agendadealunos.ui.activity.FormularioAlunoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity implements  View.OnClickListener {

    private ListView studentsList;
    private FloatingActionButton buttonAdd;
    private final ListaAlunosView listaAlunosView= new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.app_name);
        setContentView(R.layout.activity_lista_alunos);

        studentsList = findViewById(R.id.activity_lista_alunos_list_view_students);

        configurarFabNovoAluno();

        configurarAdapterEListeners();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu,
                                menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        Aluno alunoEscolhido = listaAlunosView.getAdapterItem(menuInfo.position);

        if(item.getItemId() == R.id.activity_lista_alunos_remover){
            listaAlunosView.confirmarDelecao(alunoEscolhido);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listaAlunosView.atualizarListagem();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_main_fab_novo_aluno){
           abrirFormularioParaInserirAluno();
        }
    }

    private void configurarFabNovoAluno(){
        buttonAdd = findViewById(R.id.activity_main_fab_novo_aluno);

        buttonAdd.setOnClickListener(this);
    }

    private void configurarAdapterEListeners(){
       listaAlunosView.configurarAdapter(studentsList);
       configurarListenerDeClique();

        registerForContextMenu(studentsList);
    }

    private void configurarListenerDeClique(){
        studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lidarComClique(view, position,id);
            }

            private void lidarComClique(View view, int position, long id) {
                abreFormularioParaEditarAluno(position);
            }
        });
    }

    public void abrirFormularioParaInserirAluno(){
        Intent intent = new Intent(this,FormularioAlunoActivity.class);
        startActivity(intent);
    }

    public void abreFormularioParaEditarAluno(int position){
        Aluno alunoEscolhido = (Aluno) studentsList.getItemAtPosition(position);
        Intent irParaEdicao = new Intent(ListaAlunosActivity.this,FormularioAlunoActivity.class);

        irParaEdicao.putExtra(ConstantsActivities.CHAVE_ALUNO,alunoEscolhido);

        startActivity(irParaEdicao);
    }
}
