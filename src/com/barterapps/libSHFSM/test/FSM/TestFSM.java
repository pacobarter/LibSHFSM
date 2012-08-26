package com.barterapps.libSHFSM.test.FSM;

import com.barterapps.libSHFSM.SHFSM;

public class TestFSM {

	public static void log(String msg){
		System.out.println(String.format("[%08d] %s", System.currentTimeMillis(), msg));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//	FSM creation	
			//
			SHFSM fsm = new SHFSM(-1);

			fsm.addState(new StateA());
			fsm.addState(new StateB());
			fsm.addState(new StateC());

			fsm.setIdFirstState(GlobalData.IDSTATE_A);
			
			fsm.addTransition(new Transition1(), GlobalData.IDSTATE_B);
			fsm.addTransition(new Transition2(), GlobalData.IDSTATE_C);
			fsm.addTransition(new TransitionAlways(), GlobalData.IDSTATE_A);
			
			fsm.addTransitionToState(GlobalData.IDSTATE_A, GlobalData.IDTRANSITION_1);
			fsm.addTransitionToState(GlobalData.IDSTATE_A, GlobalData.IDTRANSITION_2);

			fsm.addTransitionToState(GlobalData.IDSTATE_B, GlobalData.IDTRANSITION_ALWAYS);

			fsm.addTransitionToState(GlobalData.IDSTATE_C, GlobalData.IDTRANSITION_ALWAYS);

			//	FSM test execution
			//
			log("+++ Begin Test +++");
			log("");
			
			fsm.onEnter();
			
			for(int i=0; i<20; i++){
				fsm.onStep();
			}
			
			fsm.onExit();

			log("");
			log("--- End Test ---");

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
