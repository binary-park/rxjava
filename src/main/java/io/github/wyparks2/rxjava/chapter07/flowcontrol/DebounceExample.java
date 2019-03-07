package io.github.wyparks2.rxjava.chapter07.flowcontrol;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.github.wyparks2.rxjava.common.Shape;
import io.reactivex.Observable;

public class DebounceExample implements MarbleDiagram {
	@Override
	public void marbleDiagram() {
		String[] data = {Shape.RED, Shape.YELLOW, Shape.GREEN, Shape.BLUE};
		
		Observable<String> source = Observable.concat(
			Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[0]),
			Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[1]),
			Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[2]),
			Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[3]))
			.debounce(200L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		DebounceExample demo = new DebounceExample();
		demo.marbleDiagram();
	}
}
