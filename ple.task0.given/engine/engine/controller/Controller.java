package engine.controller;

import engine.model.Model;

import engine.view.View;
import oop.graphics.Canvas;
import oop.streams.VirtualKeyCodes;

public class Controller {
	private Canvas m_canvas;
	private View m_view;
	private Model m_model;
	private boolean m_shift;

	public Controller(Canvas canvas, Model model, View view) {
		m_canvas = canvas;
		m_model = model;
		m_view = view;
		canvas.set(new MouseListener());
		canvas.set(new KeyListener());
	}

	class KeyListener implements Canvas.KeyListener {

		@Override
		public void pressed(Canvas canvas, int keyCode, char keyChar) {

			// Look at the class VirtualKeyCodes in the package oop.graphics
			// to know what the key codes.

			// Goals:
			// - arrow keys move the player's entity around, one step
			// left, right, up, and down.
			// - shift and left arrow key rotates counter-clock-wise the player's entity by
			// 90 degrees
			// - shift and right arrow key rotates clock-wise the player's entity by 90
			// degrees
			 
			if ((keyCode == oop.graphics.VirtualKeyCodes.VK_LEFT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_LEFT) && m_shift) {
				m_model.player().rotate(-90);		
			}
			if ((keyCode == oop.graphics.VirtualKeyCodes.VK_RIGHT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_RIGHT) && m_shift) {
				m_model.player().rotate(90);		
			}
			
			if ((keyCode == oop.graphics.VirtualKeyCodes.VK_LEFT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_LEFT) && !m_shift){
				m_model.player().left();
			} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_RIGHT
					|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_RIGHT)&& !m_shift) {
				m_model.player().right();
			} else if (keyCode == oop.graphics.VirtualKeyCodes.VK_UP
					|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_UP) {
				m_model.player().up();
			} else if (keyCode == oop.graphics.VirtualKeyCodes.VK_DOWN
					|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_DOWN) {
				m_model.player().down();
			}

			if(keyCode == oop.graphics.VirtualKeyCodes.VK_SHIFT) {
				m_shift = true;
			}
		}

		@Override
		public void released(Canvas canvas, int keyCode, char keyChar) {
			if(keyCode == oop.graphics.VirtualKeyCodes.VK_SHIFT) {
				m_shift = false;
			}
		}

		@Override
		public void typed(Canvas canvas, char keyChar) {
			// nothing to do here.
		}
	}

	class MouseListener implements Canvas.MouseListener {

		@Override
		public void moved(Canvas canvas, int px, int py) {

			// inform the model about the current focus,
			// that is, where the mouse is pointing at in the world.
			m_view.focus(px, py);
		}

		@Override
		public void pressed(Canvas canvas, int bno, int x, int y) {
			// nothing to do here.
		}

		@Override
		public void released(Canvas canvas, int bno, int x, int y) {
			// nothing to do here.
		}
	}

}
