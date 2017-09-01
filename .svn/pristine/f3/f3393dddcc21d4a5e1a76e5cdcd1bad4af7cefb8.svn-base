package com.amwell.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.amwell.demo.model.Apple;
import com.amwell.util.JSONHelper;

public class LambdaDemo {
	
	/**
	 * 方法一：
	 * @param inHeavy
	 * @return
	 */
	public static List<Apple> filterHeavyApples(List<Apple> inHeavy){
		List<Apple> result = new ArrayList<Apple>();
		for(Apple ap:inHeavy){
			if(ap.getWeight()>10){
				result.add(ap);
			}
		}
		return result;
	}
	
	public static List<Apple> filterHeavyApples(List<Apple> inHeavy,Predicate<Apple> p){
		List<Apple> result = new ArrayList<Apple>();
		for(Apple ap:inHeavy){
			if(p.test(ap)){
				result.add(ap);
			}
		}
		return result;
	}
	
	
	public static boolean isHeavy(Apple app){
		Optional<Apple> o = Optional.ofNullable(app);
		if(o.isPresent()){
			return app.getWeight()>10;
		}
		return false;
	}
	

	public static void main(String[] args) {
		
		Apple ap1 = new Apple();
		ap1.setWeight(8);
		Apple ap2 = new Apple();
		ap2.setWeight(12);
		Apple ap3 = new Apple();
		ap3.setWeight(15);
		List<Apple> aplist = new ArrayList<Apple>();
		aplist.add(ap1);
		aplist.add(ap2);
		aplist.add(ap3);
		
		List<Apple> result = filterHeavyApples(aplist,(Apple a)->a.getWeight()>10);
		System.err.println(JSONHelper.toString(result));
		
		

	}

}
