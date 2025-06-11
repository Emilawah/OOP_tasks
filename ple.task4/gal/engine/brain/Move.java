package engine.brain;

class Move extends ActionGAL{
	// The optional parameters of a Move 
    Direction d; // its direction
    int i;       // a number of step

    // plusieurs constructeurs

    Move(){
    	// par defaut
        d = Direction.F ;
        i = 1 ;
    }

    Move(Direction d, int i){ 
    	
    }

    // REQUIRED 

    void exec(Bot b){
    }

	@Override
	boolean acceptedBy(Bot b) {
		// TODO Auto-generated method stub
		return false;
	} 
}
