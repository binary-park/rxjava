package io.github.wyparks2.rxjava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;
import io.github.wyparks2.rxjava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import static io.github.wyparks2.rxjava.common.Shape.YELLOW;
import static io.github.wyparks2.rxjava.common.Shape.ORANGE;
import static io.github.wyparks2.rxjava.common.Shape.PUPPLE;
import static io.github.wyparks2.rxjava.common.Shape.SKY;
import static io.github.wyparks2.rxjava.common.Shape.DIAMOND;
import static io.github.wyparks2.rxjava.common.Shape.PENTAGON;
import static io.github.wyparks2.rxjava.common.Shape.STAR;

public class CombineLatestExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data1 = {PUPPLE, ORANGE, SKY, YELLOW}; //6, 7, 4, 2
		String[] data2 = {DIAMOND, STAR, PENTAGON};
		
		Observable<String> source = Observable.combineLatest(
				Observable.fromArray(data1)  
						  .zipWith( //zipWith()로 깔끔하게 코드 정리
						      Observable.interval(100L, TimeUnit.MILLISECONDS), 
							  (shape, notUsed) -> Shape.getColor(shape)),	  
				Observable.fromArray(data2)
				          .zipWith(
				        	  Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),	  
				        	  (shape, notUsed) -> Shape.getSuffix(shape)),
				(v1, v2) -> v1 + v2);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
		
	public void tmp01() {	
		Observable<String> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(CommonUtils::numberToAlphabet);
		Observable<Long> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS);
		BiFunction<String, Long, String> combiner = 
				(val1, val2) -> val1 + val2;
		
		Observable<String> source = Observable.combineLatest(source1, source2, combiner);
		source.subscribe(System.out::println);
		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) {
		CombineLatestExample demo = new CombineLatestExample();
		demo.marbleDiagram();
	}
}
