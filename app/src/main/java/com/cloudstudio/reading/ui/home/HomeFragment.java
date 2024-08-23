package com.cloudstudio.reading.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cloudstudio.reading.R;
import com.cloudstudio.reading.adapter.BannerImageAdapter;
import com.cloudstudio.reading.adapter.ImageItemAdapter;
import com.cloudstudio.reading.adapter.ItemAdapter;
import com.cloudstudio.reading.databinding.FragmentHomeBinding;
import com.cloudstudio.reading.util.BannerDataInfo;
import com.cloudstudio.reading.util.BookBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private List<BannerDataInfo> mBannerDataList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initBannerView(root);
        initRefreshView(root);
        initMainListView(root);

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initBannerView(View root){
        Banner mBanner = binding.bannerView;//绑定轮播
        mBannerDataList=new ArrayList<>();
        mBannerDataList.add(new BannerDataInfo(R.mipmap.banner_01,"测试轮播链接1:https://test.com"));
        mBannerDataList.add(new BannerDataInfo(R.mipmap.banner_01,"测试轮播链接2:https://test.com"));
        mBannerDataList.add(new BannerDataInfo(R.mipmap.banner_01,"测试轮播链接3:https://test.com"));

        final BannerImageAdapter bannerImageAdapter=new BannerImageAdapter(mBannerDataList);

        mBanner.setAdapter(bannerImageAdapter);//添加进适配器
        mBanner.addBannerLifecycleObserver(this);//添加生命周期
        mBanner.setIndicator(new CircleIndicator(this.getContext()));/*设置圆形计数器*/
        mBanner.setOnBannerListener(new OnBannerListener<BannerDataInfo>() {
            @Override
            public void OnBannerClick(BannerDataInfo data, int position) {
                Toast.makeText(root.getContext(), data.getUrl(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*刷新页面*/
    private void initRefreshView(View root){
        swipeRefreshLayout=(SwipeRefreshLayout)root.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initMainListView(root);
                        Toast.makeText(root.getContext(), "刷新成功",Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1500);
            }
        });
    }

    /*绑定list数据*/
    private void initMainListView(View root){
        mRecyclerView=root.findViewById(R.id.mainList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 2); // 假设我们想要一个3列的宫格
        // 将 GridLayoutManager 设置给 RecyclerView
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        List<BookBean> list=new ArrayList<>();
        BookBean bookBean=new BookBean();
        bookBean.setId("1");
        bookBean.setName("测试");
        /*
         * https://img2.baidu.com/it/u=180472719,1598977165&fm=253&fmt=auto&app=138&f=JPEG?w=516&h=500
         * http://49.234.181.81:8080/upload/HeadIcon/matrix.png
         * https://wimg.588ku.com/gif620/21/01/12/dc1f33b9ecd1da2b1a4a125929ed5762.gif
         */
        bookBean.setPic("https://img2.baidu.com/it/u=180472719,1598977165&fm=253&fmt=auto&app=138&f=JPEG?w=516&h=500");
        bookBean.setWriter("清风");
        bookBean.setType("武侠");
        bookBean.setHot("999");
        bookBean.setDes("主人公杨过自然而然地走上了非正统的人生道路，入了“道流”。其特点是“至情至性，实现自我”，即把个人的利益、情感、个性及人格尊严置于人生首位，作为首要目标，亦作为待人处事，评价是非的首要原则。书中将杨过对郭靖的杀父之仇与疼惜之恩难以取舍的复杂心理刻画得维妙维肖；他与“姑姑”小龙女的情感纠葛和他对江湖世事的渴望又令他挣扎不已……");
        list.add(bookBean);
//        ItemAdapter itemAdapter = new ItemAdapter(getActivity(),list);
//        itemAdapter.SetOnItemClickListener(new ItemAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(root.getContext(),"点击了:"+position,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });

        ImageItemAdapter itemAdapter=new ImageItemAdapter(getActivity(),list);
        itemAdapter.SetOnImageItemClickListener(new ImageItemAdapter.OnImageItemClickListener() {
            @Override
            public void onImageItemClick(View view, int position) {
                Toast.makeText(root.getContext(),"点击了:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(itemAdapter);
    }
}