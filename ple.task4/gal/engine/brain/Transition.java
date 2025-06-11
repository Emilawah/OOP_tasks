package engine.brain;

class Transition {
	ConditionGAL condition;
    ActionGAL action;
    State target;
    
    public Transition(ConditionGAL cond, ActionGAL act, State state) {
    	this.condition = cond;
    	this.action = act;
    	this.target = state;
    }
}
