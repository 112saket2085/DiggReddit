package com.example.diggreddit.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
}
