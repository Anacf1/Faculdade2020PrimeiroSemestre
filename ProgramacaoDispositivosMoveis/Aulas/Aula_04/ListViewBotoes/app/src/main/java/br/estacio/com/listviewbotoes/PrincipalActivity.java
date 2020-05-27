package br.estacio.com.listviewbotoes;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PrincipalActivity  extends Activity {

    ListView list;
    String[] web = {"Google Plus", "Twitter", "Windows", "Bing", "Itunes", "Wordpress", "Drupal"};
    Integer[] imageId = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        ItensListaActivity adapter = new ItensListaActivity(PrincipalActivity.this, web, imageId);
        list = (ListView) findViewById(R.id.lista);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PrincipalActivity.this, "You Clicked at " + web[+position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
