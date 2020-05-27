package br.estacio.com.exercicio_aula02avancado;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SegundaActivity extends AppCompatActivity {

    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_activity);
        resultado = findViewById(R.id.resultado);

        Intent intent = getIntent();
        Integer soma = (Integer) intent.getSerializableExtra("SOMA");
        resultado.setText(soma.toString());
    }
}
