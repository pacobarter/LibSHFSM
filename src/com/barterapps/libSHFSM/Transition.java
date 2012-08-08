package com.barterapps.libSHFSM;

public interface Transition {

	public boolean checkFired();
	
	public void onStart();
	
	public void onStep();
	
	public boolean isCompleted();

}
