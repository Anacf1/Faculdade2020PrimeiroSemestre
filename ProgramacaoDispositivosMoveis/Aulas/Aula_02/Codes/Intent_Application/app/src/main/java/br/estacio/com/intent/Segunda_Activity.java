package br.estacio.com.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Segunda_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.segunda_activity);

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(R.string.text_segunda);
    }
}
