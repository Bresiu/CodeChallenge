package com.bresiu.codechallenge.presentation.viewmodel;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.bresiu.codechallenge.data.entity.EntitiesCombined;
import com.bresiu.codechallenge.model.PostWithUserAddress;
import com.bresiu.codechallenge.presentation.uimodels.Result;
import com.bresiu.codechallenge.presentation.uimodels.ResultBundle;
import com.bresiu.codechallenge.repository.Repository;
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
		Log.d("BRS", "initLiveData");
		mutableLiveData = new MutableLiveData<>();
		repository.getLiveData().observeForever(new Observer<List<PostWithUserAddress>>() {
			@Override public void onChanged(List<PostWithUserAddress> postWithUserAddresses) {
				Log.d("BRS", "onChanged!!!!");
				postWithUserAddresses.forEach(new java.util.function.Consumer<PostWithUserAddress>() {
					@Override public void accept(PostWithUserAddress postWithUserAddress) {
						Log.d("BRS", "post: " + postWithUserAddress);
					}
				});
			}
		});
		mutableLiveData.postValue(Result.<List<PostWithUserAddress>>loadingResult());
		new MediatorLiveData<>().addSource(repository.getLiveData(),
				new Observer<List<PostWithUserAddress>>() {
					@Override
					public void onChanged(@Nullable List<PostWithUserAddress> postWithUserAddresses) {
						Log.d("BRS", "onChanged");
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
		compositeDisposable.add(repository.fetchData().subscribe(new Consumer<EntitiesCombined>() {
			@Override public void accept(EntitiesCombined entitiesCombined) {
				Log.d("BRS", "repository.saveData");
				repository.saveData(entitiesCombined);
			}
		}, new Consumer<Throwable>() {
			@Override public void accept(Throwable throwable) {
				mutableLiveData.postValue(Result.<List<PostWithUserAddress>>errorResult(throwable));
			}
		}));
	}
}
