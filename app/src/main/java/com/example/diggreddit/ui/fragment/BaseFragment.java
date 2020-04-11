package com.example.diggreddit.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.diggreddit.R;
import com.example.diggreddit.ui.activity.MainActivity;

import java.util.Objects;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    abstract int getLayoutId();
    abstract String  getTitle();
    private ProgressDialog progressDialog;
    private NavController navController;
    MainActivity getParentActivity() {
        return (MainActivity) Objects.requireNonNull(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getParentActivity()).inflate(getLayoutId(),(ViewGroup) null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getParentActivity().setActionBarTitle(getTitle());
        navController = Navigation.findNavController(getParentActivity(), R.id.nav_host_fragment);
        initProgressDialog();
    }

    void navigateTo(int resId) {
        navController.navigate(resId);
    }

    void navigateTo(int resId,Bundle bundle) {
        navController.navigate(resId,bundle);
    }

    void showShortToast(String msg) {
        Toast.makeText(getParentActivity(),msg,Toast.LENGTH_SHORT).show();
    }

     void showLongToast(String msg) {
        Toast.makeText(getParentActivity(),msg,Toast.LENGTH_LONG).show();
    }

    private void initProgressDialog() {
      progressDialog=new ProgressDialog(getParentActivity());
      progressDialog.setCancelable(false);
    }

    void showProgressDialog(String msg) {
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    void dismissProgressDialog() {
        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
    }


}
