package br.estacio.com.mensagem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalToastDialogActivity extends AppCompatActivity {

    Button botao;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_toast);

        botao = findViewById(R.id.botaoToast);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View layout = layoutInflater.inflate(R.layout.toast_mensagem, (ViewGroup) findViewById(R.id.custom_toast_layout_id));

                ImageView imagem = (ImageView) findViewById(R.id.imagem);
                imagem.setImageResource(R.drawable.ic_launcher_background);

                TextView texto = (TextView) findViewById(R.id.texto);
                texto.setText("Exercicio Toast(layout)");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

            }
        });

    }

    public void irParaExercicioNotificationDialog(View view){
        Intent intent = new Intent(this, PrincipalNotificationDialogActivity.class);
        startActivity(intent);
    }
}
