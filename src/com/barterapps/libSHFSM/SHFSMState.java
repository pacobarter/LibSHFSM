package com.barterapps.libSHFSM;

public interface SHFSMState {

	public int getID();
	
	public void onEnter();
	
	public void onStep();
	
	public void onExit();
}
