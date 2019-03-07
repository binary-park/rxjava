package io.github.wyparks2.rxjava.chapter03;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;

public class ReduceExample implements MarbleDiagram {
	public void marbleDiagram() { 
		String[] balls = {RED, GREEN, BLUE}; //1,3,5
		Maybe<String> source = Observable.fromArray(balls)
				.reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void biFunction() {
		BiFunction<String, String, String> mergeBalls = 
				(ball1, ball2) -> ball2 + "(" + ball1 + ")";
		
		String[] balls = {RED, GREEN, BLUE};
		Maybe<String> source = Observable.fromArray(balls)
				.reduce(mergeBalls);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args){ 
		ReduceExample demo = new ReduceExample();
		demo.marbleDiagram();
		demo.biFunction();
	}
}
