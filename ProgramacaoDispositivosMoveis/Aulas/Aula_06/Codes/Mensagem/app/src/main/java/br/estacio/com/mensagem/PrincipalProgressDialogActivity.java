package br.estacio.com.mensagem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalProgressDialogActivity extends AppCompatActivity {

    ProgressDialog barraProgresso;
    Handler manipuladorBarraProgresso = new Handler();
    int statusBarraProgresso = 0;
    long TAMANHO = 0;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_progressdialog);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        Button botaoEnviarAquivo = (Button) findViewById(R.id.botaoEnviarArquivo);
        botaoEnviarAquivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barraProgresso = new ProgressDialog(v.getContext());
                barraProgresso.setCancelable(true);
                barraProgresso.setMessage("Enviando . . .");
                barraProgresso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                barraProgresso.setProgress(0);
                barraProgresso.setMax(100);
                barraProgresso.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(statusBarraProgresso < 100){
                            statusBarraProgresso = doSomeTasks();
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException in){
                                in.printStackTrace();
                                manipuladorBarraProgresso.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        barraProgresso.setProgress(statusBarraProgresso);
                                    }
                                });
                            }
                        }
                        if(statusBarraProgresso >= 100){
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException in){
                                in.printStackTrace();
                                barraProgresso.dismiss();
                            }
                        }
                    }
                });

            }
        });
    }

    public int doSomeTasks(){
        while(TAMANHO <= 1000000){
            TAMANHO++;
            if(TAMANHO == 100000){
                return 10;
            }else if(TAMANHO == 200000){
                return 20;
            }else if(TAMANHO == 30000){
                return 30;
            }
        }
        return 100;
    }

    public void irParaExercicioDatePickerDialog(View view){
        Intent intent = new Intent(this, PrincipalDatePickerDialogActivity.class);
        startActivity(intent);
    }
}
