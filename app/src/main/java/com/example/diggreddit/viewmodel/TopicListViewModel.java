package com.example.diggreddit.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.repository.TopicListRepository;

import java.util.List;

public class TopicListViewModel extends AndroidViewModel {

    MutableLiveData<List<TopicModel>> mutableLiveData;

    public TopicListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<TopicModel>> getTopicListData() {
        return TopicListRepository.getInstance().getTopicListResponseData();
    }

    public void changeVote(TopicModel topicModel) {
        TopicListRepository.getInstance().changeVote(topicModel);
    }

    public static class TopicListFactory implements ViewModelProvider.Factory {
        private Application application;

        public TopicListFactory(Application application) {
            this.application=application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new TopicListViewModel(application);
        }
    }
}
