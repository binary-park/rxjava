package io.github.wyparks2.rxjava.chapter05.schedulers;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;

import io.github.wyparks2.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ComputationSchedulerExample {
	public void basic() { 
		String[] orgs = {Shape.RED, Shape.GREEN, Shape.BLUE};
		Observable<String> source = Observable.fromArray(orgs)
			.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), 
					(a,b) -> a);
		
		//Subscription #1 
		source.map(item -> "<<" + item + ">>")
			.subscribeOn(Schedulers.computation())
			.subscribe(Log::i);
		
		//Subscription #2
		source.map(item -> "##" + item + "##")
			.subscribeOn(Schedulers.computation())		
			.subscribe(Log::i);		
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ComputationSchedulerExample demo = new ComputationSchedulerExample();
		demo.basic();
	}
}
