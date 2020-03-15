package com.example.diggreddit.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.diggreddit.ui.activity.MainActivity;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private MainActivity mainActivity;
    abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(getLayoutId(),(ViewGroup) null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity=(MainActivity) getActivity();
    }

    MainActivity getParentActivity() {
        return mainActivity;
    }

    void showShortToast(String msg) {
        Toast.makeText(getParentActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    void showLongToast(String msg) {
        Toast.makeText(getParentActivity(),msg,Toast.LENGTH_LONG).show();
    }


}
