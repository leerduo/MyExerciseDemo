package me.chenfuduo.networkingmodels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] urlItems = new String[]{
            "Sending and Managing Network Requests",
            "Converting JSON to Models",
            "Creating and Executing Async Tasks",
            "Handling ProgressBars",
            "RottenTomatoes Networking Tutorial",
            "Networking with the Volley Library",
            "Displaying Images with the Fresco Library",
            "Building Data driven Apps with Parse",
            "Building Simple Chat Client with Parse",
            "Troubleshooting Common Issues with Parse",
            "Consuming APIs with Retrofit",
            "Sending and Receiving Data with Sockets"
    };

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,urlItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position){
                    case 0:
                        i = new Intent(MainActivity.this,SendAndManageRequestActivity.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
