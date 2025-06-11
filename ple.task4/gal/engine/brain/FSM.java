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
		for (Mode m : modes) {
			if (m.state.name.equals(name)) {
				return m;
			}
		}
		return mode;

	}

	boolean transit() {
		for (Transition t : mode.transitions) {
			if (t.condition.eval(bot)) {
				t.action.exec(bot);

				Mode newMode = canocical(t.target.name);
				if (newMode != null) {
					mode = newMode;
				} else {
					System.out.print("Mode introuvable dans la liste");
				}
				return true;
			}
		}
		return false;
	}
}
