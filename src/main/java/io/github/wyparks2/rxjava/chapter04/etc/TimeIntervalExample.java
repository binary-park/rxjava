package io.github.wyparks2.rxjava.chapter04.etc;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

public class TimeIntervalExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED", "GREEN", "ORANGE"};
		
		CommonUtils.exampleStart();
		Observable<Timed<String>> source = Observable.fromArray(data)
			.delay(item -> { 
				CommonUtils.doSomething();
				return Observable.just(item);
			})
			.timeInterval();
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) { 
		TimeIntervalExample demo = new TimeIntervalExample();
		demo.marbleDiagram();	
	}
}
