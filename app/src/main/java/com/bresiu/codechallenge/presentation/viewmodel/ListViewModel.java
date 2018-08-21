package com.bresiu.codechallenge.presentation.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import com.bresiu.codechallenge.model.PostWithUserAddress;
import com.bresiu.codechallenge.presentation.uimodels.Result;
import com.bresiu.codechallenge.presentation.uimodels.ResultBundle;
import com.bresiu.codechallenge.repository.Repository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.util.List;
import javax.inject.Inject;

public class ListViewModel extends ViewModel {
	private final Repository repository;
	private final CompositeDisposable compositeDisposable;
	private BehaviorSubject<Long> subject;
	private MediatorLiveData<Result<List<PostWithUserAddress>>> mediatorLiveData;

	@Inject ListViewModel(Repository repository) {
		this.repository = repository;
		compositeDisposable = new CompositeDisposable();
		initLiveData();
		initListeners();
		fetchData();
	}

	private void initListeners() {
		subject = BehaviorSubject.create();
		compositeDisposable.add(subject.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io())
				.subscribe(repository::deletePostById));
	}

	private void initLiveData() {
		Log.d("BRS", "initLiveData");
		mediatorLiveData = new MediatorLiveData<>();
		mediatorLiveData.addSource(repository.getLiveData(), postWithUserAddresses -> {
			Log.d("BRS", "onChanged");
			if (postWithUserAddresses.isEmpty()) {
				Log.d("BRS", "skip empty update");
			} else {
				mediatorLiveData.setValue(Result.successResult(new ResultBundle<>(postWithUserAddresses)));
			}
		});
		mediatorLiveData.setValue(Result.loadingResult());
	}

	@Override protected void onCleared() {
		compositeDisposable.dispose();
		super.onCleared();
	}

	public LiveData<Result<List<PostWithUserAddress>>> getLiveData() {
		return mediatorLiveData;
	}

	private void fetchData() {
		compositeDisposable.add(repository.fetchData()
				.subscribe(repository::saveData,
						throwable -> mediatorLiveData.setValue(Result.errorResult(throwable))));
	}

	public void deletePostById(long postId) {
		subject.onNext(postId);
	}
}
