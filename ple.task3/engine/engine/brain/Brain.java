package engine.brain;

import java.util.LinkedList;

import engine.IBrain;
import engine.IModel;

public class Brain implements IBrain{
	
	protected IModel model;
	protected LinkedList<IBot> m_listBots;
	
	public Brain(IModel m) {
		this.model=m;
		m_listBots = new LinkedList<IBot>();
	}
	
	
	public void tick(int elapsed) {
		for(IBot bot : m_listBots) {
			bot.think(elapsed);
		}
	}
}
