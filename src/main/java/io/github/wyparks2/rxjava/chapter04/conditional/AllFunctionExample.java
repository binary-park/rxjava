package io.github.wyparks2.rxjava.chapter04.conditional;

import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;
import io.github.wyparks2.rxjava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.Single;

import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.SKY;
import static io.github.wyparks2.rxjava.common.Shape.YELLOW;
import static io.github.wyparks2.rxjava.common.Shape.rectangle;

public class AllFunctionExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data = {RED, YELLOW, GREEN, SKY};
		
		Single<Boolean> source = Observable.fromArray(data)
			.map(Shape::getShape)
			.all(Shape.BALL::equals);
			//.all(val -> Shape.BALL.equals(Shape.getShape(val)));
		source.subscribe(Log::i);
	}
	
	public void wrongCase() { 
		String[] data = {RED, rectangle(YELLOW), GREEN, SKY};
		
		Single<Boolean> source = Observable.fromArray(data)
			.map(Shape::getShape)
			.doOnNext(Log::d)
			.doOnComplete(() -> Log.d("onComplete"))
			.all(Shape.BALL::equals)
			.doOnSuccess(v -> Log.d("onSuccess : val = " + v));
		
			//.all(val -> Shape.BALL.equals(Shape.getShape(val)));
		source.subscribe(Log::i);
	}
	
	public static void main(String[] args) { 
		AllFunctionExample demo = new AllFunctionExample();
		demo.marbleDiagram();
		demo.wrongCase();
	}
}
