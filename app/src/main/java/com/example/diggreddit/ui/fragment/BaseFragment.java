package com.example.diggreddit.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.diggreddit.ui.activity.MainActivity;

public abstract class BaseFragment extends Fragment {

    private MainActivity mainActivity;
    abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(getLayoutId(),(ViewGroup) null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity=(MainActivity) getActivity();
    }



    protected MainActivity getParentActivity() {
        return mainActivity;
    }
}
