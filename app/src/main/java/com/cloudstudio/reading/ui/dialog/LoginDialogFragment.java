package com.cloudstudio.reading.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.cloudstudio.reading.R;

/**
 * @ClassName LoginDialogFragment
 * @Author Create By matrix
 * @Date 2024/8/29 15:17
 */
public class LoginDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);

        // Find the views to use in the dialog
        EditText etUsername = view.findViewById(R.id.etUsername);
        EditText etPassword = view.findViewById(R.id.etPassword);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        // Set up the button's click listener
        btnLogin.setOnClickListener(v -> {
            /*响应*/
            Toast.makeText(getActivity(), "Logging in...", Toast.LENGTH_SHORT).show();
            // Optionally dismiss the dialog
            dismiss();
        });
        // Set the dialog title and content
        builder.setView(view)
                .setTitle("登录");
        // Add actions if needed
        // .setPositiveButton("OK", null);
        // .setNegativeButton("Cancel", null);

        return builder.create();
    }
}
