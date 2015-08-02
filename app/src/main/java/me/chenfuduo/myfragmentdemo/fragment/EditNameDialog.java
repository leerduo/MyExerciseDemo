package me.chenfuduo.myfragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import me.chenfuduo.myfragmentdemo.R;

/**
 * Created by Administrator on 2015/7/31.
 */
public class EditNameDialog extends DialogFragment implements TextView.OnEditorActionListener{


    private EditText editText;

    public interface EditNameDialogListener{
        void onFinishEditDialog(String inputText);
    }


    public EditNameDialog() {

        // Empty constructor required for DialogFragment


    }

    public static EditNameDialog newInstance(String title){
        EditNameDialog dialogFragment = new EditNameDialog();
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_name, container);

        editText = (EditText) view.findViewById(R.id.txt_your_name);

        String title = getArguments().getString("TITLE");

        getDialog().setTitle(title);


        editText.requestFocus();

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        editText.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId){
            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
            listener.onFinishEditDialog(editText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }
}
