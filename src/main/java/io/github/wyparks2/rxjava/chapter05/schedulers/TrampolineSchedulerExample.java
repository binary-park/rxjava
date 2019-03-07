package io.github.wyparks2.rxjava.chapter05.schedulers;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TrampolineSchedulerExample {
	public void run() { 
		String[] orgs = {"RED", "GREEN", "BLUE"};		
		Observable<String> source = Observable.fromArray(orgs);
		
		//Subscription #1 
		source.subscribeOn(Schedulers.trampoline())
				.map(data -> "<<" + data + ">>")
				.subscribe(Log::i);
		
		//Subscription #2 
		source.subscribeOn(Schedulers.trampoline())
				.map(data -> "##" + data + "##")
				.subscribe(Log::i);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		TrampolineSchedulerExample demo = new TrampolineSchedulerExample();
		demo.run();
	}
}
