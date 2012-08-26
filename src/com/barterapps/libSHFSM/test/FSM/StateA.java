package com.barterapps.libSHFSM.test.FSM;

import com.barterapps.libSHFSM.SHFSMState;

public class StateA implements SHFSMState {

	@Override
	public int getID() {
		return GlobalData.IDSTATE_A;
	}

	@Override
	public void onEnter() {
		TestFSM.log("state A: onEnter");
	}

	@Override
	public void onStep() {
		TestFSM.log("state A: onStep");
		
		GlobalData.counter1++;
		GlobalData.counter2++;
		
		TestFSM.log(String.format("counter1 = %2d / counter2 = %2d",GlobalData.counter1, GlobalData.counter2));
	}

	@Override
	public void onExit() {
		TestFSM.log("state A: onExit");
	}

}
