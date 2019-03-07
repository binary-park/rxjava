package io.github.wyparks2.rxjava.chapter05.schedulers;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;

import io.github.wyparks2.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewThreadSchedulerExample {
	public void basic() { 
		String[] orgs = {Shape.RED, Shape.GREEN, Shape.BLUE};
		Observable.fromArray(orgs)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "<<" + data + ">>")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);		
		CommonUtils.sleep(500);
		
		Observable.fromArray(orgs)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "##" + data + "##")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);		
		CommonUtils.sleep(500);
	}
	
	public static void main(String[] args) { 
		NewThreadSchedulerExample demo = new NewThreadSchedulerExample();
		demo.basic();
	}
}
