package com.barterapps.libSHFSM.test.FSM;

import com.barterapps.libSHFSM.SHFSMTransition;

public class TransitionAlways implements SHFSMTransition {

	
	@Override
	public int getID() {
		return GlobalData.IDTRANSITION_ALWAYS;
	}

	@Override
	public boolean checkFired() {
		return true;
	}

	@Override
	public void onStart() {
		TestFSM.log("trans []: onStart");
	}

	@Override
	public void onStep() {
		TestFSM.log("trans []: onStep");
	}

	@Override
	public boolean isCompleted() {
		return true;
	}

}
