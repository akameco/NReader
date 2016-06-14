package io.github.akameco.nreader.models;

import java.util.List;

import narou4j.Narou;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class Novel {
	public Novel() {
	}

	public Observable<List<narou4j.entities.Novel>> fetchNovelList() {
		return Observable.create(new Observable.OnSubscribe<List<narou4j.entities.Novel>>() {
			@Override
			public void call(Subscriber<? super List<narou4j.entities.Novel>> subscriber) {
				try {
					Narou narou = new Narou();
					subscriber.onNext(narou.getNovels());
					subscriber.onCompleted();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
		}).subscribeOn(Schedulers.io());
	}
}
