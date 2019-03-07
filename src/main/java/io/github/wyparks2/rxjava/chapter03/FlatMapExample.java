package io.github.wyparks2.rxjava.chapter03;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;

public class FlatMapExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		//함수를 별도로 정의하는 것이 가장 먼저 겪어야 할 관문임 
		Function<String, Observable<String>> getDoubleDiamonds = 
				ball -> Observable.just(ball + "<>", ball + "<>");
		
		String[] balls = {RED, GREEN, BLUE}; 
		Observable<String> source = Observable.fromArray(balls)
				.flatMap(getDoubleDiamonds);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}

	public void flatMapLambda() { 
		//Observable안에 Observable을 넣는다는 것이 직관적이지는 않음 		
		String[] balls = {RED, GREEN, BLUE}; 
		Observable<String> source = Observable.fromArray(balls)
				.flatMap(ball -> Observable.just(ball + "<>", ball + "<>"));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	
	public static void main(String[] args) { 
		FlatMapExample demo = new FlatMapExample();
		demo.marbleDiagram();
		demo.flatMapLambda();
	}
}
