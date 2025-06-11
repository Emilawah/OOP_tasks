package engine.brain;

import java.util.List;


class FSM {
	List<Mode> modes;
	Mode mode;
	Bot bot;

	
	public FSM(Bot b) {
		this.bot = b;
	}
	
	Mode canocical(String name) {
		for(Mode m : modes) {
			if(m.state.equals(name)) {
				return m;
			}
		}
		return null;

	}

	boolean transit() {
		for(Transition t : mode.transitions) {
			if(t.condition.eval(bot)) {
				t.action.exec(bot);
				return true;
			}
		}
		return false;
	}
}
