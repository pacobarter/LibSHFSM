package com.barterapps.libSHFSM;

public interface State {

	public int getID();
	
	public void onEnter();
	
	public void onStep();
	
	public void onExit();
	
}
