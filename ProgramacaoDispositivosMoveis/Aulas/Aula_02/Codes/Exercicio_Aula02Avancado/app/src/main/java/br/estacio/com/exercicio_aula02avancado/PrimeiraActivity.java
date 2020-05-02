package br.estacio.com.exercicio_aula02avancado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PrimeiraActivity extends AppCompatActivity {

    EditText primeiraParcela;
    EditText segundaParcela;
    Button botaoSoma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeira_activity);

        primeiraParcela = findViewById(R.id.primeiraParcela);
        segundaParcela = findViewById(R.id.segundaParcela);
        botaoSoma = findViewById(R.id.botaoSomar);
        botaoSoma.setOnClickListener(new addButtonClickHandler());
    }

    private class addButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Integer valorPrimeiraParcela = Integer.parseInt(primeiraParcela.getText().toString());
            Integer valorSegundaParcela = Integer.parseInt(segundaParcela.getText().toString());

            Intent intent = new Intent(PrimeiraActivity.this, SegundaActivity.class);
            intent.putExtra("SOMA", valorPrimeiraParcela + valorSegundaParcela);
            startActivity(intent);

        }
    }
}
