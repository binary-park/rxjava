package io.github.wyparks2.rxjava.chapter02;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;

public class ConnectableObservableExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] dt = {RED, GREEN, BLUE}; 
		Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(i -> dt[i])
				.take(dt.length);
		ConnectableObservable<String> source = balls.publish();
		source.subscribe(data -> System.out.println("Subscriber #1 => " + data)); 
		source.subscribe(data -> System.out.println("Subscriber #2 => " + data)); 
		source.connect();
		
		CommonUtils.sleep(250);
		source.subscribe(data -> System.out.println("Subscriber #3 => " + data)); 
		CommonUtils.sleep(100);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ConnectableObservableExample demo = new ConnectableObservableExample();
		demo.marbleDiagram();
	}
}
