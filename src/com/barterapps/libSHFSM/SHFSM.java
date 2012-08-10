package com.barterapps.libSHFSM;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

//	TODO add error checking
//	TODO make methods synchronized

public class SHFSM implements SHFSMState {
	private int id;
	
	private Hashtable<Integer, SHFSMState> states = null;
	private Hashtable<Integer, SHFSMTransition> transitions = null;
	
	//	holds the Transitions of each State
	private Hashtable<Integer, ArrayList<Integer>> stateTransitions = null;
	
	//	holds the "next State to go" for each Transition
	private Hashtable<Integer, Integer> transitionNextState = null;

	private SHFSMUserInstrument userInstrument = null;
	
	private int idActualState;
	private int idFirstState;

	private boolean restartOnEnter;

	private boolean execTransition;

	//	aux vars
	private SHFSMState state = null;
	private SHFSMTransition trans = null;
	private ArrayList<Integer> arrT = null;
	private Iterator<Integer> it = null;

	//	user notifications
	private synchronized void notifyUserBeforeOnEnter(){
		if(userInstrument != null){
			userInstrument.shfsmBeforeOnEnter(this);
		}		
	}

	private synchronized void notifyUserAfterOnEnter(){
		if(userInstrument != null){
			userInstrument.shfsmAfterOnEnter(this);
		}		
	}

	private synchronized void notifyUserBeforeOnStep(){
		if(userInstrument != null){
			userInstrument.shfsmBeforeOnStep(this);
		}
	}

	private synchronized void notifyUserAfterOnStep(){
		if(userInstrument != null){
			userInstrument.shfsmAfterOnStep(this);
		}
	}

	private synchronized void notifyUserBeforeOnExit(){
		if(userInstrument != null){
			userInstrument.shfsmBeforeOnExit(this);
		}
	}

	private synchronized void notifyUserAfterOnExit(){
		if(userInstrument != null){
			userInstrument.shfsmAfterOnExit(this);
		}
	}

	private synchronized void notifyUserOnError(Exception ex){
		if(userInstrument != null){
			userInstrument.shfsmOnInternalError(this, ex);
		}
	}
	
	//	builder
	
	public SHFSM(int id){
		this(id, true);
	}
	
	public SHFSM(int id, boolean restartOnEnter){
		this.id = id;
		this.restartOnEnter = restartOnEnter;

		states = new Hashtable<Integer, SHFSMState>();
		transitions = new Hashtable<Integer, SHFSMTransition>();
		
		stateTransitions = new Hashtable<Integer, ArrayList<Integer>>();
		transitionNextState = new Hashtable<Integer, Integer>();
		
		idActualState = -1;
		idFirstState = -1;
		
		execTransition = false;
	}

	public synchronized void setUserInstrument(SHFSMUserInstrument userInstrument){
		this.userInstrument = userInstrument;
	}
	
	public synchronized int getIdActualState() {
    	return idActualState;
    }

	public synchronized void setIdActualState(int idActualState) {
		//	TODO: check for errors
    	this.idActualState = idActualState;
    }

	public int getIdFirstState() {
    	return idFirstState;
    }

	public void setIdFirstState(int idFirstState) {
		//	TODO: check for errors
    	this.idFirstState = idFirstState;
    }

	public void addState(SHFSMState state) throws Exception{
		if(state == null){
			throw new Exception("[SHFSMManager:addState] Param 'state' cannot be null");
		}
		
		states.put(state.getID(), state);
	}
	
	public void addTransition(SHFSMTransition trans) throws Exception{
		if(trans == null){
			throw new Exception("[SHFSMManager:addTransition] Param 'trans' cannot be null");
		}
		
		transitions.put(trans.getID(), trans);
	}
	
	public void addTransitionToState(int idState, int idTransition) throws Exception{
		if(!states.containsKey(idState)){
			throw new Exception(String.format("[SHFSMManager:addTransitionToState] Unknown State with id = '%d'", idState));
		}
		
		if(!transitions.containsKey(idTransition)){
			throw new Exception(String.format("[SHFSMManager:addTransitionToState] Unknown Transition with id = '%d'", idTransition));
		}
		
		arrT = stateTransitions.get(idState);
		
		if(arrT == null){
			arrT = new ArrayList<Integer>();
			
			stateTransitions.put(idState, arrT);
		}
		
		arrT.add(idTransition);
	}
	
	public void setTransitionNextState(int idTransition, int idNextState){
		transitionNextState.put(idTransition, idNextState);
	}
	
	public synchronized SHFSMState getState(int idState){
		return states.get(idState);
	}
	
	public synchronized SHFSMTransition getTransition(int idTransition){
		return transitions.get(idTransition);
	}
	
	public synchronized ArrayList<Integer> getTransitionsOfState(int idState){
		return stateTransitions.get(idState);
	}
	
	public synchronized int getTransitionNextState(int idTransition){
		return transitionNextState.get(idTransition);
	}
	
	@Override
    public int getID() {
	    return id;
    }

	@Override
    public void onEnter() {
		synchronized(this){
			try{
				notifyUserBeforeOnEnter();
				
				if(restartOnEnter){
					setIdActualState(getIdFirstState());
					
					SHFSMState state = getState(getIdActualState());
					state.onEnter();
				}
				
				notifyUserAfterOnEnter();
				
			}catch(Exception ex){
				notifyUserOnError(ex);
			}
		}
    }

	@Override
    public void onStep() {
		synchronized(this){
			try{
				notifyUserBeforeOnStep();
				
				if(execTransition){
					if(trans == null){
						execTransition = false;
					
					}else{
						trans.onStep();
						
						if(trans.isCompleted()){
							//	'exit' actual state
							state = getState(getIdActualState());
							state.onExit();

							//	set next state
							setIdActualState(transitionNextState.get(trans.getID()));
							
							//	'enter' (new) actual state
							state = getState(getIdActualState());
							state.onEnter();
							
							//	clear vars
							trans = null;
							execTransition = false;
						}
					}
					
				}else{
					//	'step' actual state
					state = getState(getIdActualState());
					state.onStep();

					//	check for actual state transitions
					arrT = stateTransitions.get(state.getID());
					if(arrT != null){
						it = arrT.iterator();
						while(it.hasNext()){
							trans = transitions.get(it.next());
							
							if(trans.checkFired()){
								trans.onStart();
								
								execTransition = true;
							}
						}
					}
				}
				
				notifyUserAfterOnStep();
				
			}catch(Exception ex){
				notifyUserOnError(ex);
			}
		}
    }

	@Override
    public void onExit() {
		synchronized(this){
			try{
				notifyUserBeforeOnExit();
				
				//	?
				
				notifyUserAfterOnExit();
				
			}catch(Exception ex){
				notifyUserOnError(ex);
			}
		}
    }
	
	
}
