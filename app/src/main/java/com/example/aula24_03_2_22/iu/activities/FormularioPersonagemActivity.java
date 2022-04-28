package com.example.aula24_03_2_22.iu.activities;

import static com.example.aula24_03_2_22.iu.activities.ConstatesActivities.CHAVE_PERSONAGEM;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aula24_03_2_22.R;
import com.example.aula24_03_2_22.dao.PersonagemDAO;
import com.example.aula24_03_2_22.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {
    //nao tem apossibilidade de mudar o nome 
    private static final String TITULO_APPBAR_EDITAR_PERSONAGEM = "Editar o Personagem";
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    // editar campos
    private EditText campoNome; 
    private EditText campoNascimento;
    private EditText campoAltura;
    //adicionar novo personagem
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;
//criar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }
//selecionar menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_personagem_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        inicializacaoCampos();
        //configuraBotaoSalvar();
        carregaPersonagem();
    }
//carregar persoangem, dados
    private void carregaPersonagem(){
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PERSONAGEM)){
            setTitle(TITULO_APPBAR_EDITAR_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        }else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }
    //prencer campos
    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }
//final formulario
    private void finalizarFormulario() {
        preencherPersonagem();
        if(personagem.idValido())
        {
            dao.edita(personagem);
            finish();
        }else {
            dao.salvar(personagem);
        }
        finish();
    }
    // inicializaçao de campos para digitar
    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.ediText_nome);
        campoNascimento = findViewById(R.id.editText_nascimento);
        campoAltura = findViewById(R.id.editText_altura);

        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, amtAltura);
        campoAltura.addTextChangedListener(mtwAltura);

        SimpleMaskFormatter smfNascimento =new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoAltura, smtNascimento);
        campoNascimento.addTextChangedListener(mtwNascimento);
    }
// para prencher com as informaçoes
    private void preencherPersonagem() {
        String nome = campoNome.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        String altura = campoAltura.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}
