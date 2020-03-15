package com.example.diggreddit.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.diggreddit.model.AddTopicResponseModel;
import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.repository.TopicListRepository;
import java.util.List;

public class AddTopicViewModel extends AndroidViewModel {

    MutableLiveData<List<TopicModel>> mutableLiveData;

    public AddTopicViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<AddTopicResponseModel> getResponse(TopicModel topicModel) {
        return TopicListRepository.getInstance().getResponse(topicModel);
    }

    public static class AddTopicFactory implements ViewModelProvider.Factory {
        private Application application;

        public AddTopicFactory(Application application) {
            this.application=application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new AddTopicViewModel(application);
        }
    }
}
