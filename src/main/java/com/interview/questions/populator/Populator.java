package com.interview.questions.populator;

public interface Populator<T, K> {
	
	K populate(T bean);
	

}
