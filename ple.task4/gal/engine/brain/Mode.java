package engine.brain;

import java.util.ArrayList;
import java.util.List;

public class Mode {
	State state;
	List<Transition> transitions;
	
	public Mode(State state) {
		this.state = state;
		this.transitions = new ArrayList<Transition>();
	}
	
	void add(ConditionGAL condition, ActionGAL action, State cible) {
		transitions.add(new Transition(condition,action,cible));
	}
}
