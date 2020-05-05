package br.estacio.com.mensagem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PrincipalDatePickerDialogActivity extends AppCompatActivity {

    TextView textoData;
    DatePicker datePicker;
    int dia;
    int mes;
    int ano;
    static final int DATE_DIALOG_ID = 999;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_datepicker);

        setarDataAtual();
        adicionarListenerBotaoalterarData();
    }

    public void setarDataAtual(){
        Calendar calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);

        textoData = (TextView) findViewById(R.id.textoData);
        textoData.setText(new StringBuilder()
                .append(dia)
                .append("-")
                .append(mes+1)
                .append("-").append(ano));

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(ano, mes,dia, null);

    }

    public void adicionarListenerBotaoalterarData(){
        Button botaoAlterarData = (Button) findViewById(R.id.alterarData);
        botaoAlterarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dayOfMonth = dia;
                month = mes;
                year = ano;

                textoData.setText(new StringBuilder()
                        .append(dia)
                        .append("-")
                        .append(mes+1)
                        .append("-")
                        .append(ano)
                        .append(" "));

                datePicker.init(ano, mes, dia, null);

            }
        };

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, ano, mes, dia);
        }
        return null;

    }



}
