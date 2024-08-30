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
import com.cloudstudio.reading.entities.nertworkBean.LoginResponseBean;
import com.cloudstudio.reading.network.api.LoginApi;
import com.cloudstudio.reading.network.service.LoginService;
import com.cloudstudio.reading.util.ElementUtil;
import com.cloudstudio.reading.util.UserManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //初始化并绑定对话框的布局
        //传递null作为父视图
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);

        // Find the views to use in the dialog
        EditText etUsername = view.findViewById(R.id.etUsername);
        EditText etPassword = view.findViewById(R.id.etPassword);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        Button btnCancel = view.findViewById(R.id.btnLoginCancel);


        // Set up the button's click listener
        btnLogin.setOnClickListener(v -> {
            /*响应*/
            //Toast.makeText(getActivity(), "Logging in...", Toast.LENGTH_SHORT).show();
            if(ElementUtil.checkElementValue(etUsername)||ElementUtil.checkElementValue(etPassword)){
                Toast.makeText(getActivity(), "请检查输入！", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    loginHandle(view,etUsername.getText().toString(),etPassword.getText().toString());
                } catch (IOException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
                //关闭对话框
                dismiss();
            }
        });
        btnCancel.setOnClickListener(v -> {
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

    private void loginHandle(View root,String name,String pwd) throws IOException {
        LoginApi loginApi=new LoginApi();
        LoginService loginService=loginApi.getService();
        //UserManager.getInstance(root.getContext()).getUserToken()
        Call<LoginResponseBean> call_login=loginService.getState("",name,pwd);
        call_login.enqueue(new Callback<LoginResponseBean>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseBean> call, @NonNull Response<LoginResponseBean> response) {
                if(response.body().getHandleCode()==200){
                    Toast.makeText(root.getContext(),"已登录:"+response.body().getHandleData().getAccount(),Toast.LENGTH_SHORT).show();
                    UserManager.getInstance(root.getContext()).login(
                            response.body().getHandleData().getAuthorization(),
                            response.body().getHandleData().getAccount());
//                    Toast.makeText(root.getContext(),"已登录:"+UserManager.getInstance(root.getContext()).getUserAccount(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseBean> call, @NonNull Throwable throwable) {
                Toast.makeText(root.getContext(),"网络异常!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
