package com.barterapps.libSHFSM;

public interface SHFSMTransition {

	public int getID();
	
	public boolean checkFired();
	
	public void onStart();
	
	public void onStep();
	
	public boolean isCompleted();

}
