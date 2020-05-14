package br.estacio.com.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalExternalStorageActivity extends AppCompatActivity {

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_externalstorage);
    }

    public void irParaSQLiteDatabase(View view){
        Intent intent = new Intent(this.getApplicationContext(), PrincipalSQLiteDatabaseActivity.class);
        startActivity(intent);
    }
}
