package br.estacio.com.mensagem;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class PrincipalNotification extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_notification);

        //Button botaoIrParaExercicioAlertDialog = findViewById(R.id.forExercicioAlertDialog);

        Button botao = findViewById(R.id.botaoPrincipal);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNotification();
            }

            protected void startNotification(){
                NotificationCompat.Builder notificacao = new NotificationCompat.Builder(PrincipalNotification.this);
                notificacao.setContentTitle("Notificação Title");
                notificacao.setContentText("Notificao Text");
                notificacao.setTicker("Atenção");
                notificacao.setSmallIcon(R.drawable.ic_launcher_background);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(PrincipalNotification.this);
                stackBuilder.addParentStack(NotificacaoActivity.class);

                Intent intent = new Intent(PrincipalNotification.this, NotificacaoActivity.class);

                stackBuilder.addNextIntent(intent);

                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                notificacao.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificacao.build());
            }

        });
    }

    public void irParaExercicioAlertDialog(View view){
        Intent intent = new Intent(getApplicationContext(), PrincipalAlertDialogActitivy.class);
        startActivity(intent);
    }
}
