package com.barterapps.libSHFSM.test.FSM;

import com.barterapps.libSHFSM.SHFSMTransition;

public class Transition2 implements SHFSMTransition {

	private int innerCounter;
	
	@Override
	public int getID() {
		return GlobalData.IDTRANSITION_2;
	}

	@Override
	public boolean checkFired() {
		return GlobalData.counter2 >= 8;
	}

	@Override
	public void onStart() {
		innerCounter = 2;
		TestFSM.log("trans 2: onStart");
	}

	@Override
	public void onStep() {
		innerCounter--;
		TestFSM.log(String.format("trans 2: onStep (%d)", innerCounter));
	}

	@Override
	public boolean isCompleted() {
		return innerCounter <= 0;
	}

}
