package com.barterapps.libSHFSM.test.FSM;

import com.barterapps.libSHFSM.SHFSMState;

public class StateB implements SHFSMState {

	@Override
	public int getID() {
		return GlobalData.IDSTATE_B;
	}

	@Override
	public void onEnter() {
		TestFSM.log("state B: onEnter");
		GlobalData.counter1 = 0;
	}

	@Override
	public void onStep() {
		TestFSM.log("state B: onStep");
	}

	@Override
	public void onExit() {
		TestFSM.log("state B: onExit");
	}

}
