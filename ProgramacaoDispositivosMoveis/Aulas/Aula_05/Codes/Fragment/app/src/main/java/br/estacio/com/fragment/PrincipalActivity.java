package br.estacio.com.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class PrincipalActivity extends AppCompatActivity {

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.layout_principal);

        Button button = findViewById(R.id.buttonPrincipal);
        button.setOnClickListener(this::onClick);

    }

    private void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentActivity fragment = new FragmentActivity(); /*Minha classe de Fragment*/
        fragmentTransaction.add(R.id.linearLayoutFragment, fragment);
        fragmentTransaction.commit();
    }
}
