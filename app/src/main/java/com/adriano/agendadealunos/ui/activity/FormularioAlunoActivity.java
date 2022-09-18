package com.adriano.agendadealunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adriano.agendadealunos.R;
import com.adriano.agendadealunos.dao.AlunoDAO;
import com.adriano.agendadealunos.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private AlunoDAO alunoDAO;
    private Aluno alunoParaEditar = null;
    private EditText editTextNome;
    private EditText editTextTelefone;
    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        definirInputsEDAO();

        preencherInputsEDefinirObjetoDeEdicaoCasoSejaParaEditar();

        preencherTitulo();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.activity_formulario_aluno_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencherInputsEDefinirObjetoDeEdicaoCasoSejaParaEditar(){
        Intent dados = getIntent();

        if(dados.hasExtra(ConstantsActivities.CHAVE_ALUNO)){
            alunoParaEditar = (Aluno) dados.getSerializableExtra(ConstantsActivities.CHAVE_ALUNO);


            if(alunoParaEditar != null){

                editTextTelefone.setText(alunoParaEditar.getTelefone());
                editTextNome.setText(alunoParaEditar.getNome());
                editTextEmail.setText(alunoParaEditar.getEmail());

            }
        }
    }

    private void finalizarFormulario(){
        Aluno aluno = gerarAluno();

        salvarAluno(aluno);
    }

    private Aluno gerarAluno(){
        String nome = editTextNome.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();

        Aluno aluno = new Aluno(nome, telefone, email);
        if(alunoParaEditar != null){
            aluno.setId(alunoParaEditar.getId());
        }

        return aluno;
    }

    private void salvarAluno(Aluno aluno){
        if(alunoParaEditar == null){
            alunoDAO.inserir(aluno);
        } else {
            alunoDAO.editar(aluno);
        }


        finish();
    }

    private void preencherTitulo(){
        if(alunoParaEditar == null){
            setTitle(R.string.app_form_add_aluno);
        }
        else {
          setTitle(R.string.app_form_edit_aluno);
        }
    }
    private void definirInputsEDAO(){
        editTextEmail=findViewById(R.id.activity_formulario_aluno_email);
        editTextNome=findViewById(R.id.activity_formulario_aluno_nome);
        editTextTelefone=findViewById(R.id.activity_formulario_aluno_telefone);
        alunoDAO = new AlunoDAO();
    }


}