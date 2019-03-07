package io.github.wyparks2.rxjava.chapter04.etc;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Observable;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.YELLOW;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.SKY;
import static io.github.wyparks2.rxjava.common.Shape.ORANGE;

public class DelayExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		CommonUtils.exampleStart();
		
		String[] data = {RED, ORANGE, YELLOW, GREEN, SKY};
		Observable<String> source = Observable.fromArray(data)
				.delay(100L, TimeUnit.MILLISECONDS);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		DelayExample demo = new DelayExample();
		demo.marbleDiagram();
	}
}
