package io.github.wyparks2.rxjava.chapter05;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;
import io.github.wyparks2.rxjava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.YELLOW;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.star;
import static io.github.wyparks2.rxjava.common.Shape.triangle;
import static io.github.wyparks2.rxjava.common.Shape.pentagon;

public class FlipExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] objs = {star(RED), triangle(YELLOW), pentagon(GREEN)};
		Observable<String> source = Observable.fromArray(objs)
				.doOnNext(data -> Log.v("Original data = " + data))
				.subscribeOn(Schedulers.newThread())
				.observeOn(Schedulers.newThread())
				.map(Shape::flip);
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}

	public void observeOnRemoved() {
		String[] objs = {star(RED), triangle(YELLOW), pentagon(GREEN)};
		Observable<String> source = Observable.fromArray(objs)
				.doOnNext(data -> Log.v("Origianl data = " + data))
				.subscribeOn(Schedulers.newThread())
				//removed .observeOn(Schedulers.newThread())
				.map(Shape::flip);
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
	}
	
	public static void main(String[] args) { 
		FlipExample demo = new FlipExample();
		demo.marbleDiagram();
		demo.observeOnRemoved();
	}
}
