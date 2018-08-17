package com.bresiu.codechallenge.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bresiu.codechallenge.data.entity.EntitiesCombined;
import com.bresiu.codechallenge.model.PostWithUserAddress;
import com.bresiu.codechallenge.repository.Repository;
import com.bresiu.codechallenge.viewmodels.uimodels.Result;
import com.bresiu.codechallenge.viewmodels.uimodels.ResultBundle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;

public class ListViewModel extends ViewModel {
	private final Repository repository;
	private final CompositeDisposable compositeDisposable;
	private MutableLiveData<Result<List<PostWithUserAddress>>> mutableLiveData;

	@Inject ListViewModel(Repository repository) {
		this.repository = repository;
		compositeDisposable = new CompositeDisposable();
		initLiveData();
		fetchData();
	}

	private void initLiveData() {
		mutableLiveData = new MutableLiveData<>();
		new MediatorLiveData<>().addSource(repository.getLiveData(),
				new Observer<List<PostWithUserAddress>>() {
					@Override
					public void onChanged(@Nullable List<PostWithUserAddress> postWithUserAddresses) {
						mutableLiveData.postValue(
								Result.successResult(new ResultBundle<>(postWithUserAddresses)));
					}
				});
	}

	@Override protected void onCleared() {
		compositeDisposable.dispose();
		super.onCleared();
	}

	public LiveData<Result<List<PostWithUserAddress>>> getLiveData() {
		return mutableLiveData;
	}

	private void fetchData() {
		compositeDisposable.add(repository.fetchData()
				.observeOn(AndroidSchedulers.mainThread())
				.doOnError(new Consumer<Throwable>() {
					@Override public void accept(Throwable throwable) {
						Log.d("BRS", "doOnError Main thread: " + (Looper.myLooper() == Looper.getMainLooper()));
						mutableLiveData.postValue(Result.<List<PostWithUserAddress>>errorResult(throwable));
					}
				})
				.subscribe(new Consumer<EntitiesCombined>() {
					@Override public void accept(EntitiesCombined entitiesCombined) {
						Log.d("BRS", "onNext Main thread: " + (Looper.myLooper() == Looper.getMainLooper()));
						repository.saveData(entitiesCombined);
					}
				}));
	}
}
