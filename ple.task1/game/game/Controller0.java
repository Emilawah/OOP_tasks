package game;

import engine.IModel;
import engine.IView;
import engine.controller.Controller;
import oop.graphics.Canvas;

public class Controller0 extends Controller{

	public Controller0(Canvas canvas, IModel model, IView view) {
		super(canvas, model, view);
	}

	@Override
	protected void pressed(Canvas canvas, int keyCode, char keyChar) {
		if ((keyCode == oop.graphics.VirtualKeyCodes.VK_LEFT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_LEFT) && m_shift) {
			m_model.player().rotate(-90);		
		}
		if ((keyCode == oop.graphics.VirtualKeyCodes.VK_RIGHT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_RIGHT) && m_shift) {
			m_model.player().rotate(90);		
		}
		
		if ((keyCode == oop.graphics.VirtualKeyCodes.VK_LEFT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_LEFT) && !m_shift && !m_control){
			m_model.player().left();
		} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_RIGHT
				|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_RIGHT)&& !m_shift && !m_control) {
			m_model.player().right();
		} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_UP
				|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_UP) && !m_control) {
			m_model.player().up();
		} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_DOWN
				|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_DOWN)&& !m_control) {
			m_model.player().down();
		}
		
	}

	@Override
	protected void released(Canvas canvas, int keyCode, char keyChar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void typed(Canvas canvas, char keyChar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pressed(Canvas canvas, int bno, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void released(Canvas canvas, int bno, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void moved(Canvas canvas, int px, int py) {
		m_view.focus(px, py);
	}
	
	
}
