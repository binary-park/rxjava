package io.github.wyparks2.rxjava.chapter04.conditional;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Observable;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.YELLOW;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.SKY;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;
import static io.github.wyparks2.rxjava.common.Shape.PUPPLE;

public class TakeUntilExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), 
						(val, notUsed) -> val)
				.takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		TakeUntilExample demo = new TakeUntilExample();
		demo.marbleDiagram();
	}
}
