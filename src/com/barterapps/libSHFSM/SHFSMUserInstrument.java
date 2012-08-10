package com.barterapps.libSHFSM;

public interface SHFSMUserInstrument {
	//	on enter
	public void shfsmBeforeOnEnter(SHFSM shfsm);
	
	public void shfsmAfterOnEnter(SHFSM shfsm);
	
	//	on step
	
	public void shfsmBeforeOnStep(SHFSM shfsm);
	
	public void shfsmAfterOnStep(SHFSM shfsm);
	
	//	on exit
	
	public void shfsmBeforeOnExit(SHFSM shfsm);
	
	public void shfsmAfterOnExit(SHFSM shfsm);
	
	//	error handling
	
	public void shfsmOnInternalError(SHFSM shfsm, Exception ex);
	
}
