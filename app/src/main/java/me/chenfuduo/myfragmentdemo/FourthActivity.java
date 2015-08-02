package me.chenfuduo.myfragmentdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import me.chenfuduo.myfragmentdemo.fragment.EditNameDialog;

public class FourthActivity extends FragmentActivity implements EditNameDialog.EditNameDialogListener {

    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        showEditDialog();
    }

    private void showEditDialog() {
        FragmentManager fm =
                getSupportFragmentManager();

        EditNameDialog editNameDialog = EditNameDialog.newInstance("Some Title");

        editNameDialog.show(fm, "fragment_edit_name");


      /*  FragmentManager fragmentManager = getSupportFragmentManager();

        MyAlertDialogFragment alertDialogFragment = MyAlertDialogFragment.newInstance("Some Title");

        alertDialogFragment.show(fragmentManager,"fragment_alert");*/


    }


    /**  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_fourth, menu);
    return true;
    }

     @Override public boolean onOptionsItemSelected(MenuItem item) {
     // Handle action bar item clicks here. The action bar will
     // automatically handle clicks on the Home/Up button, so long
     // as you specify a parent activity in AndroidManifest.xml.
     int id = item.getItemId();

     //noinspection SimplifiableIfStatement
     if (id == R.id.action_settings) {
     return true;
     }

     return super.onOptionsItemSelected(item);
     }*/
}
