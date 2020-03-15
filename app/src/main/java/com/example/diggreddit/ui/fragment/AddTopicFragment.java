package com.example.diggreddit.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.diggreddit.R;
import com.example.diggreddit.model.AddTopicResponseModel;
import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.viewmodel.AddTopicViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;
import butterknife.BindView;
import butterknife.OnClick;

public class AddTopicFragment extends BaseFragment {

    @BindView(R.id.text_input_layout_title) TextInputLayout textInputLayoutTitle;
    @BindView(R.id.text_input_edittext_title) TextInputEditText textInputEditTextTitle;
    @BindView(R.id.text_input_layout_description) TextInputLayout textInputLayoutDescription;
    @BindView(R.id.text_input_edittext_description) TextInputEditText textInputEditTextDescription;
    @BindView(R.id.button_post) Button buttonPost;
    private String title,topicDescription;
    private AddTopicViewModel addTopicViewModel;

    @Override
    public String getTitle() {
        return getString(R.string.str_add_topic);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_add_topic;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AddTopicViewModel.AddTopicFactory addTopicFactory=new AddTopicViewModel.AddTopicFactory(getParentActivity().getApplication());
        addTopicViewModel= new ViewModelProvider(getParentActivity().getViewModelStore(),addTopicFactory).get(AddTopicViewModel.class);
    }

    @OnClick(R.id.button_post)
    public void onPostButtonCLick(View view) {
        if(isFieldValidated()) {
            addTopicListObserver(new TopicModel(title,topicDescription,0));
        }
    }

    private void addTopicListObserver(TopicModel topicModel) {
        showProgressDialog(getString(R.string.adding_topic));
        addTopicViewModel.getResponse(topicModel).observe(getViewLifecycleOwner(), new Observer<AddTopicResponseModel>() {
            @Override
            public void onChanged(AddTopicResponseModel addTopicResponseModel) {
                if(addTopicResponseModel!=null) {
                    dismissProgressDialog();
                    handleResponse(addTopicResponseModel);
                    navigateTo(R.id.action_add_topic_to_topic_list);
                }

            }
        });
    }

    private void handleResponse(AddTopicResponseModel addTopicResponseModel) {
        if (addTopicResponseModel != null) {
            if (addTopicResponseModel.isSuccess()) {
                showShortToast(addTopicResponseModel.getMsg());
            } else {
                showShortToast(getString(R.string.str_error_data));
            }
        } else {
            showShortToast(getString(R.string.str_error_data));
        }
    }


    private boolean isFieldValidated() {
         title= Objects.requireNonNull(textInputEditTextTitle.getText()).toString();
         topicDescription= Objects.requireNonNull(textInputEditTextDescription.getText()).toString();

         if(TextUtils.isEmpty(title)) {
             showShortToast(getString(R.string.str_empty_title));
            return false;
         }
         else if(TextUtils.isEmpty(topicDescription)) {
             showShortToast(getString(R.string.str_empty_description));
             return false;
         }
         return true;
    }
}
