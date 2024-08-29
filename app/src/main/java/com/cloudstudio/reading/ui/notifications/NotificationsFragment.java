package com.cloudstudio.reading.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cloudstudio.reading.databinding.FragmentNotificationsBinding;
import com.cloudstudio.reading.ui.dialog.LoginDialogFragment;
import com.cloudstudio.reading.util.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    /**控件组**/
    private CircleImageView headView;
    private ImageView name_edit_btn;
    private EditText nameTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textNotifications;
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        initView(root);
        elementHandle(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * 加载所有控件组件
     * @param root
     */
    private void initView(View root){
        headView=binding.headView;
        name_edit_btn=binding.nameEditBtn;
        nameTextView=binding.nameTextView;
    }

    /**
     * 控件组件逻辑
     * @param root
     */
    private void elementHandle(View root){
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginViewHandle(root);
            }
        });

        name_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginViewHandle(root);
            }
        });
    }

    private void showLoginViewHandle(View root){
        if(UserManager.getInstance(root.getContext()).isUserLogin()) {
            // 用户已登录，执行相应操作
        } else {
            // 用户未登录
            LoginDialogFragment loginDialog = new LoginDialogFragment();
            loginDialog.show(getFragmentManager(), "loginDialog");
        }
    }
}