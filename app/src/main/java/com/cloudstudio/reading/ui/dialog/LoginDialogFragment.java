package com.cloudstudio.reading.ui.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cloudstudio.reading.R;
import com.cloudstudio.reading.entities.nertworkBean.LoginBean;
import com.cloudstudio.reading.entities.nertworkBean.UserInfoBean;
import com.cloudstudio.reading.network.api.LoginApi;
import com.cloudstudio.reading.network.service.LoginService;
import com.cloudstudio.reading.ui.notifications.NotificationsViewModel;
import com.cloudstudio.reading.util.DipPx;
import com.cloudstudio.reading.util.ElementUtil;
import com.cloudstudio.reading.util.GlideRoundCornersTransUtils;
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
        Call<LoginBean> call_login=loginService.getState("",name,pwd);
        call_login.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, @NonNull Response<LoginBean> response) {
                if(response.body().getHandleCode()==200){
                    Toast.makeText(root.getContext(),"已登录:"+response.body().getHandleData().getmAccount(),Toast.LENGTH_SHORT).show();
                    UserManager.getInstance(root.getContext()).login(response.body().getHandleData().getmAccount(),
                            response.body().getHandleData().getmHead(),response.body().getHandleData().getmName(),
                            response.body().getHandleData().getmSex(),response.body().getHandleData().getmPhone(),
                            response.body().getHandleData().getmEmail(),response.body().getHandleData().getmLevel(),
                            response.body().getHandleData().getmStatus(),response.body().getHandleData().getmAddressIp(),
                            response.body().getAuthorization());
//                    Toast.makeText(root.getContext(),"已登录:"+UserManager.getInstance(root.getContext()).getUserAccount(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable throwable) {

            }
        });
    }
}
