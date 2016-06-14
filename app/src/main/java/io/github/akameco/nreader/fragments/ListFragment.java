package io.github.akameco.nreader.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import io.github.akameco.nreader.R;
import io.github.akameco.nreader.databinding.FragmentListBinding;
import io.github.akameco.nreader.models.Novel;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class ListFragment extends Fragment {
	private FragmentListBinding binding;
	private ArrayAdapter<String> adapter;
	private Context mContext;

	public static ListFragment newInstance() {
		Bundle args = new Bundle();
		ListFragment fragment = new ListFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
		adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1);
		fetchInitData();
		return binding.getRoot();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mContext = context;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void fetchInitData() {
		Novel novel = new Novel();
		novel.fetchNovelList()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				new Subscriber<List<narou4j.entities.Novel>>() {
					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {
					}

					@Override
					public void onNext(List<narou4j.entities.Novel> novels) {
						for (narou4j.entities.Novel novel : novels) {
							if (novel.getTitle() != null) {
								adapter.add(novel.getTitle());
							}
						}
						binding.listView.setAdapter(adapter);
					}
				}
			);
	}
}
