package io.github.wyparks2.rxjava.chapter04.conditional;

import java.util.Arrays;
import java.util.List;
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
import static io.github.wyparks2.rxjava.common.Shape.rectangle;

public class AmbExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data1 = {RED, GREEN, BLUE};
		String[] data2 = {rectangle(YELLOW), rectangle(SKY)};
		
		List<Observable<String>> sources = Arrays.asList(
				Observable.fromArray(data1)
						  .doOnComplete(() -> Log.d("Observable #1 : onComplete()")), 
				Observable.fromArray(data2)
						  .delay(100L, TimeUnit.MILLISECONDS)
						  .doOnComplete(() -> Log.d("Observable #2 : onComplete()")));
		
		Observable.amb(sources)
				  .doOnComplete(() -> Log.d("Result : onComplete()"))
				  .subscribe(Log::i);		
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		AmbExample demo = new AmbExample();
		demo.marbleDiagram();
	}
}
