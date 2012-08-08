package com.barterapps.libSHFSM;

import java.util.ArrayList;
import java.util.Hashtable;

//	TODO add error checking
//	TODO make methods synchronized

public class SHFSMManager implements State {
	private int id;
	
	private Hashtable<Integer, State> states = null;
	private Hashtable<Integer, Transition> transitions = null;
	
	//	holds the Transitions of each State
	private Hashtable<Integer, ArrayList<Integer>> stateTransitions = null;
	
	//	holds the "next State to go" for each Transition
	private Hashtable<Integer, Integer> transitionNextState = null;
	
	private int idActualState;
	private int idFirstState;
	
	public SHFSMManager(int id){
		this.id = id;

		states = new Hashtable<Integer, State>();
		transitions = new Hashtable<Integer, Transition>();
		
		stateTransitions = new Hashtable<Integer, ArrayList<Integer>>();
		transitionNextState = new Hashtable<Integer, Integer>();
		
		idActualState = -1;
		idFirstState = -1;
	}

	public int getIdActualState() {
    	return idActualState;
    }

	public void setIdActualState(int idActualState) {
    	this.idActualState = idActualState;
    }

	public int getIdFirstState() {
    	return idFirstState;
    }

	public void setIdFirstState(int idFirstState) {
    	this.idFirstState = idFirstState;
    }


	public void addState(int id, State state){
		states.put(id, state);
	}
	
	public void addTransition(int id, Transition trans){
		transitions.put(id, trans);
	}
	
	public void addTransitionToState(int idState, int idTransition){
		ArrayList<Integer> arrT = null;
		
		arrT = stateTransitions.get(idState);
		if(arrT == null){
			arrT = new ArrayList<Integer>();
		}
		
		arrT.add(idTransition);
	}
	
	public void setTransitionNextState(int idTransition, int idNextState){
		transitionNextState.put(idTransition, idNextState);
	}
	
	@Override
    public int getID() {
	    return id;
    }

	@Override
    public void onEnter() {
	    
    }

	@Override
    public void onStep() {
	    
    }

	@Override
    public void onExit() {
	    
    }
	
	
}
