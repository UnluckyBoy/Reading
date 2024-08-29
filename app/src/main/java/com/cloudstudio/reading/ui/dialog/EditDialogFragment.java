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
 * @ClassName EditDialogFragment
 * @Author Create By matrix
 * @Date 2024/8/29 16:18
 */
public class EditDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit, null);

        EditText etUsername = view.findViewById(R.id.editTitle);
        Button confirm = view.findViewById(R.id.btnConfirm);
        Button editCancel = view.findViewById(R.id.btnEditCancel);

        confirm.setOnClickListener(v -> {
            /*响应*/
            Toast.makeText(getActivity(), "编辑成功", Toast.LENGTH_SHORT).show();
            // Optionally dismiss the dialog
            dismiss();
        });
        editCancel.setOnClickListener(v -> {
            dismiss();
        });
        // Set the dialog title and content
        builder.setView(view)
                .setTitle(getString(R.string.app_name)+"-修改昵称");
        // Add actions if needed
        // .setPositiveButton("OK", null);
        // .setNegativeButton("Cancel", null);

        return builder.create();
    }
}
