package com.adriano.agendadealunos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adriano.agendadealunos.R;
import com.adriano.agendadealunos.models.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Aluno aluno = alunos.get(position);

        View viewGerada = retornarViewDeAluno(viewGroup);
        definirInformacaoesDeView(aluno, viewGerada);

        return viewGerada;
    }

    private void definirInformacaoesDeView(Aluno aluno,View viewGerada){
        TextView nome = viewGerada.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNome());

        TextView telefone = viewGerada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefone());
    }

    private View retornarViewDeAluno(ViewGroup viewGroup){
        return LayoutInflater.from(context)
                .inflate(R.layout.item_aluno,viewGroup,false);
    }

    public void atualizar(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno alunoEscolhido) {

        alunos.remove(alunoEscolhido);
        notifyDataSetChanged();
    }
}
