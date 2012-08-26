package com.barterapps.libSHFSM.test.FSM;

import com.barterapps.libSHFSM.SHFSMTransition;

public class Transition1 implements SHFSMTransition {

	private int innerCounter;
	
	@Override
	public int getID() {
		return GlobalData.IDTRANSITION_1;
	}

	@Override
	public boolean checkFired() {
		return GlobalData.counter1 >= 5;
	}

	@Override
	public void onStart() {
		innerCounter = 4;
		TestFSM.log("trans 1: onStart");
	}

	@Override
	public void onStep() {
		innerCounter--;
		TestFSM.log(String.format("trans 1: onStep (%d)", innerCounter));
	}

	@Override
	public boolean isCompleted() {
		return innerCounter <= 0;
	}

}
