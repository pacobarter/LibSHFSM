package com.barterapps.libSHFSM.test.FSM;

import com.barterapps.libSHFSM.SHFSMState;

public class StateC implements SHFSMState {

	@Override
	public int getID() {
		return GlobalData.IDSTATE_C;
	}

	@Override
	public void onEnter() {
		TestFSM.log("state C: onEnter");
		GlobalData.counter2 = 0;
	}

	@Override
	public void onStep() {
		TestFSM.log("state C: onStep");
	}

	@Override
	public void onExit() {
		TestFSM.log("state C: onExit");
	}

}
