package com.cloudstudio.reading.ui.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cloudstudio.reading.R;
import com.cloudstudio.reading.databinding.FragmentNotificationsBinding;
import com.cloudstudio.reading.entities.nertworkBean.LoginResponseBean;
import com.cloudstudio.reading.entities.nertworkBean.QueryInfoResponseBean;
import com.cloudstudio.reading.entities.nertworkBean.UserInfoBean;
import com.cloudstudio.reading.network.api.QueryUserInfoApi;
import com.cloudstudio.reading.network.service.QueryUserInfoService;
import com.cloudstudio.reading.ui.dialog.EditDialogFragment;
import com.cloudstudio.reading.ui.dialog.LoginDialogFragment;
import com.cloudstudio.reading.util.DipPx;
import com.cloudstudio.reading.util.GlideRoundCornersTransUtils;
import com.cloudstudio.reading.util.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    /**控件组**/
    private CircleImageView headView;
    private ImageView name_edit_btn,levelImage;
    private TextView nameTextView,coinNum;

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
        queryUserInfo(root);

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
        coinNum= binding.coinNum;
        levelImage=binding.levelImage;
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
                if(UserManager.getInstance(root.getContext()).isLogin()) {
                    // 用户已登录，执行相应操作
                    EditDialogFragment editDialogFragment=new EditDialogFragment();
                    editDialogFragment.show(getParentFragmentManager(),"editDialog");
                } else {
                    // 用户未登录
                    LoginDialogFragment loginDialog = new LoginDialogFragment();
                    loginDialog.show(getParentFragmentManager(), "loginDialog");
                }
            }
        });
    }

    /**
     * 网络请求获取数据
     * @param root
     */
    private void queryUserInfo(View root){
        if(UserManager.getInstance(root.getContext()).isLogin()){
            QueryUserInfoApi queryUserInfoApi=new QueryUserInfoApi();
            QueryUserInfoService queryUserInfoService=queryUserInfoApi.getService();
            Call<QueryInfoResponseBean> queryInfoCall=queryUserInfoService.getState(UserManager.getInstance(root.getContext()).getUserAccount());
            queryInfoCall.enqueue(new Callback<QueryInfoResponseBean>() {
                @Override
                public void onResponse(@NonNull Call<QueryInfoResponseBean> call, @NonNull Response<QueryInfoResponseBean> response) {
                    if(response.body().getHandleCode()==200){
                        bindingElementView(root,response.body().getHandleData());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<QueryInfoResponseBean> call, @NonNull Throwable throwable) {
                    Toast.makeText(root.getContext(),"网络异常!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 绑定数据
     * @param userInfoBean
     */
    private void bindingElementView(View root,UserInfoBean userInfoBean){
        final String serviceUrl="https://42f9f82b.r7.cpolar.cn/image";
        Glide.with(root.getContext())
                .load(serviceUrl+userInfoBean.getmHead())
                .centerCrop()
                .into(headView);
        nameTextView.setText(userInfoBean.getmName());
        if(userInfoBean.getmCoin()>999999){
            coinNum.setText(root.getContext().getString(R.string.num_test));
        }else{
            coinNum.setText(String.valueOf(userInfoBean.getmCoin()));
        }
        if(userInfoBean.getmLevel()>0){
            levelImage.setBackgroundResource(R.mipmap.vip_on);
        }else{
            levelImage.setBackgroundResource(R.mipmap.vip_off);
        }
    }

    private void showLoginViewHandle(View root){
        if(UserManager.getInstance(root.getContext()).isLogin()) {
            // 用户已登录，执行相应操作
        } else {
            // 用户未登录
            LoginDialogFragment loginDialog = new LoginDialogFragment();
            loginDialog.show(getParentFragmentManager(), "loginDialog");
        }
    }
}