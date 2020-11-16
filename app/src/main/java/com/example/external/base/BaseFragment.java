package com.example.external.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.external.utils.StatusBarUtil;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @ClassName: BaseFragment
 * @Description:
 * @CreateDate: 2020/11/15 12:13
 * @Creator: lf
 */
public abstract class BaseFragment extends SupportFragment {
    protected Context mContext;
    protected Activity mActivity;
    private boolean isViewCreated = false;
    private boolean isUIVisible = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setStatusBar();
        initView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void setStatusBar() {
        StatusBarUtil.setStatusBar(mActivity);
    }

    private void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            loadData();

            isViewCreated = false;
            isUIVisible = false;

        }
    }


    protected abstract int getLayout();


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void loadData();
}

