package io.github.wyparks2.rxjava.chapter04.transform;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Observable;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;

public class ScanExample implements MarbleDiagram {
	public void marbleDiagram() { 
		String[] balls = {RED, GREEN, BLUE}; //1,3,5
		Observable<String> source = Observable.fromArray(balls)
				.scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ScanExample demo = new ScanExample();
		demo.marbleDiagram();
	}
}
