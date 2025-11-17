package engine.brain;

abstract class ActionGAL {

	abstract void exec(Bot b);

    abstract boolean acceptedBy(Bot b);
}
