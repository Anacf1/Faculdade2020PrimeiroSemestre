package br.estacio.com.menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuContextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menucontext);
        Button button = findViewById(R.id.buttonMenuContext);

        /*Manter pressionado o botão*/
        button.setOnCreateContextMenuListener(this);
        /*registerForContextMenu(button); -> Também funciona*/
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.menu, contextMenu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
        return true;
    }

    public void forActionBar(View view){
        Intent intent = new Intent(this, MenuBarActivity.class);
        startActivity(intent);

    }
}
