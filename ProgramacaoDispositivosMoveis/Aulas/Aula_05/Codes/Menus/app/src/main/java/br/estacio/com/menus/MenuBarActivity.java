package br.estacio.com.menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuBarActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menubar);

        text = findViewById(R.id.textViewMenuBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.add:
                text.setText("Adicionar item");
                return true;
            case R.id.reset:
                text.setText("Voltar");
            case R.id.about:
                text.setText("");
                Toast.makeText(getApplicationContext(), "Saiba Mais", Toast.LENGTH_LONG).show();
                return true;
            case R.id.exit:
                text.setText("");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
