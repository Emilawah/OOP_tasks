package engine.brain;

class Conjonction extends ConditionGAL{
	
    private ConditionGAL cond1, cond2;

	
	// CONSTRUCTOR
    Conjonction(ConditionGAL c1, ConditionGAL c2){
    	this.cond1 = c1;
    	this.cond2 = c2;
    }

    // FIELDS
    
    // REQUIRED
    boolean eval(Bot b){
        return cond1.eval(b) && cond2.eval(b);
    }
}
