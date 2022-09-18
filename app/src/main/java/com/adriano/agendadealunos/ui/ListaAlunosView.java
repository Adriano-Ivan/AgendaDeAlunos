package com.adriano.agendadealunos.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ListView;

import com.adriano.agendadealunos.dao.AlunoDAO;
import com.adriano.agendadealunos.models.Aluno;
import com.adriano.agendadealunos.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;
    private final Context context;

    public ListaAlunosView(Context context){
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.alunoDAO = new AlunoDAO();
    }

    public void confirmarDelecao( final Aluno alunoEscolhido){
        new AlertDialog.Builder(context)
                .setTitle("Remover aluno")
                .setMessage("Deseja realmente remover o aluno ?")
                .setPositiveButton("Sim", (dialog, i)-> {
                    deletarAlunoEAtualizar(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizarListagem(){
        adapter.atualizar(alunoDAO.todos());
    }

    public void deletarAlunoEAtualizar(Aluno alunoEscolhido){
        alunoDAO.remover(alunoEscolhido);

        adapter.remove(alunoEscolhido);

    }

    public void configurarAdapter(ListView studentsList){
        studentsList.setAdapter(adapter);
    }

    public Aluno getAdapterItem(int i ){
        return adapter.getItem(i);
    }
}
