package br.estacio.com.menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuExpandidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menuexpandido);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.item01:
                Toast.makeText(getApplicationContext(), "Usado para dispositivos Android", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item02:
                Toast.makeText(getApplicationContext(), "Usado para dispositivos IOS", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item03:
                Toast.makeText(getApplicationContext(), "Usado para dispositivos Windows Mobile", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void forMenuContext(View view){
        Intent menuContext = new Intent(this, MenuContextActivity.class);
        startActivity(menuContext);
    }
}
