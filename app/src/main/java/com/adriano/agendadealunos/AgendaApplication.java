package com.adriano.agendadealunos;

import android.app.Application;

import com.adriano.agendadealunos.dao.AlunoDAO;
import com.adriano.agendadealunos.models.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criarAlunosDeTeste();
    }

    private void criarAlunosDeTeste(){
        AlunoDAO dao = new AlunoDAO();

        dao.inserir(new Aluno("Eita","838","kdjfkjd@GMAIL.COM"));
        dao.inserir(new Aluno("Eita2","73","k3DFDGMAIL.COM"));

    }
}
