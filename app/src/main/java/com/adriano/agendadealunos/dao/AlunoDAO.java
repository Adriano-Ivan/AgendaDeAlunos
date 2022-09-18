package com.adriano.agendadealunos.dao;

import com.adriano.agendadealunos.models.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static long acumulatedId = 1;

    public void inserir(Aluno aluno) {

        aluno.setId(acumulatedId);
        alunos.add(aluno);
        acumulatedId++;
    }

    public void editar(Aluno aluno){
        boolean exists = false;
        int positionOfStudent =0;

        for(int i = 0; i < alunos.size();i++){
            if(alunos.get(i).getId() == aluno.getId()){
                exists = true;
                positionOfStudent = i;
            }
        }


        if(exists){
            alunos.set(positionOfStudent, aluno);
        }
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    private Aluno retornarPorId(long id){
        Aluno aluno  = null;

        for(int i = 0; i < alunos.size();i++){
            if(alunos.get(i).getId() == id){
                aluno = alunos.get(i);
                break;
            }
        }

        return aluno;
    }

    public void remover(Aluno alunoEscolhido) {
        Aluno alunoADeletar = retornarPorId(alunoEscolhido.getId());

        if(alunoADeletar != null){
            alunos.remove(alunoADeletar);
        }
    }
}
