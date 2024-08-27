package com.cloudstudio.reading.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.cloudstudio.reading.R;
import com.cloudstudio.reading.adapter.fregment.DashboardFragmentPagerAdapter;
import com.cloudstudio.reading.databinding.FragmentDashboardBinding;
import com.cloudstudio.reading.util.ElementUtil;

import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ViewPager2 viewPager;
    private DashboardFragmentPagerAdapter adapter;
    private TextView _manText,_woManText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        initElement(root);
        initDashboardFragment(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initElement(View root){
        viewPager = binding.viewPager2;
        _manText=binding.classificationTextMan;
        _woManText=binding.classificationTexWotMan;

        _manText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(root.getContext(), "点击"+_manText.getText(),Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(0, true);
            }
        });
        _woManText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });
    }

    private void initDashboardFragment(View root){
        List<String> items=Arrays.asList(root.getContext().getString(R.string.btn_man),root.getContext().getString(R.string.btn_woman));
        DashboardFragmentPagerAdapter adapter = new DashboardFragmentPagerAdapter(items, R.layout.fragment_classification);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // 页面滑动时的回调
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // 页面选中时的回调
                switch (position){
                    case 0:
                        ElementUtil.textStatusClick(root.getContext(),_manText);
                        ElementUtil.textStatusNormal(root.getContext(),_woManText);
                        break;
                    case 1:
                        ElementUtil.textStatusNormal(root.getContext(),_manText);
                        ElementUtil.textStatusClick(root.getContext(),_woManText);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                // 页面滚动状态改变时的回调
            }
        });
    }
}