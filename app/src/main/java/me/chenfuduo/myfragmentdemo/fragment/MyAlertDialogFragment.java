package me.chenfuduo.myfragmentdemo.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2015/8/1.
 */
public class MyAlertDialogFragment extends DialogFragment {

    public MyAlertDialogFragment() {
    }

    public static MyAlertDialogFragment newInstance(String title){
        MyAlertDialogFragment alertDialogFragment = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        alertDialogFragment.setArguments(args);
        return alertDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString("title");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title);

        builder.setMessage("Are you sure?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

}
