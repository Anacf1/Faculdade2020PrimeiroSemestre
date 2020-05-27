package br.estacio.com.mensagem;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PrincipalTimePickerDialogActivity extends AppCompatActivity {

    TextView textoHora;
    TimePicker timePicker;
    int hora;
    int minuto;
    final static int TIME_DIALOG_ID = 999;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_timepicker);

        setarHoraAtual();
    }

    public void setarHoraAtual(){
        Calendar calendar = Calendar.getInstance();
        hora = calendar.get(Calendar.HOUR_OF_DAY);
        minuto = calendar.get(Calendar.MINUTE);

        textoHora = findViewById(R.id.textoHora);
        textoHora.setText(new StringBuilder()
                .append(String.valueOf(hora))
                .append(" : ")
                .append(String.valueOf(minuto)));

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setCurrentHour(hora);
        timePicker.setCurrentMinute(minuto);
    }

    public void adicionarListenerBotaoAlterarHora(View view){
        Button button = (Button) findViewById(R.id.alterarHora);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora = hourOfDay;
                minuto = minute;

                textoHora.setText(new StringBuilder()
                        .append(String.valueOf(hora))
                        .append(" : ")
                        .append(String.valueOf(minuto))
                        );

                timePicker.setCurrentHour(hora);
                timePicker.setCurrentMinute(minuto);
            }
        };

        switch (id){
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timeSetListener, hora, minuto, false);
        }
        return null;
    }

}
