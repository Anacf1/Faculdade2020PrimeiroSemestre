package br.estacio.com.mensagem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PrincipalAlertDialogActitivy extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_alertdialog);

        Button botao01 = findViewById(R.id.botao01);
        Button botao02 = findViewById(R.id.botao02);
        Button botao03 = findViewById(R.id.botao03);
    }

    public void mostarMensagemBotao01(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalAlertDialogActitivy.this);
        builder.setTitle("Botão 01");
        builder.setMessage("Você pertou o botão 01");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botão 01 clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    public void mostarMensagemBotao02(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalAlertDialogActitivy.this);
        builder.setTitle("Botão 02");
        builder.setMessage("Você pertou o botão 02");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botão Ok clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botao No clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    public void mostarMensagemBotao03(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalAlertDialogActitivy.this);
        builder.setTitle("Botão 03");
        builder.setMessage("Você pertou o botão 03");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botão Ok clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botao No clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botao Cancel clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    public void irParaExercicioProgressDialog(View view){
        Intent intent = new Intent(this, PrincipalProgressDialogActivity.class);
        startActivity(intent);
    }

}
