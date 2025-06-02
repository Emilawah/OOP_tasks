package engine.controller;

import engine.IController;


import engine.IModel;
import engine.IView;
import engine.controller.Controller.KeyListener;
import engine.controller.Controller.MouseListener;

import oop.graphics.Canvas;
import oop.graphics.VirtualKeyCodes;

public abstract class Controller implements IController {
	protected Canvas m_canvas;
	protected IView m_view;
	protected IModel m_model;
	protected boolean m_shift;
	protected boolean m_control;
	protected boolean m_alt;

	protected Controller(Canvas canvas, IModel model, IView view) {
		m_canvas = canvas;
		m_model = model;
		m_view = view;
		canvas.set(new MouseListener());
		canvas.set(new KeyListener());
	}

	protected abstract void pressed(Canvas canvas, int keyCode, char keyChar);

	protected abstract void released(Canvas canvas, int keyCode, char keyChar);

	protected abstract void typed(Canvas canvas, char keyChar);

	protected abstract void pressed(Canvas canvas, int bno, int x, int y);

	protected abstract void released(Canvas canvas, int bno, int x, int y);

	protected abstract void moved(Canvas canvas, int px, int py);

	class KeyListener implements Canvas.KeyListener {

		@Override
		public void pressed(Canvas canvas, int keyCode, char keyChar) {
			switch (keyCode) {
			case VirtualKeyCodes.VK_SHIFT:
				m_shift = true;
				break;
			case VirtualKeyCodes.VK_ALT:
				m_alt = true;
				break;
			case VirtualKeyCodes.VK_CONTROL:
				m_control = true;
				break;
			case VirtualKeyCodes.VK_LEFT:
				if (m_control) {
					m_view.translate(-5, 0);
				}
				break;
			case VirtualKeyCodes.VK_RIGHT:
				if (m_control) {
					m_view.translate(5, 0);
				}
				break;
			case VirtualKeyCodes.VK_UP:
				if (m_control) {
					m_view.translate(0, -5);
				}
				break;
			case VirtualKeyCodes.VK_DOWN:
				if (m_control) {
					m_view.translate(0, 5);
				}
				break;

			}
			Controller.this.pressed(canvas, keyCode, keyChar);
		}

		@Override
		public void released(Canvas canvas, int keyCode, char keyChar) {
			switch (keyCode) {
			case VirtualKeyCodes.VK_SHIFT:
				m_shift = false;
				break;
			case VirtualKeyCodes.VK_ALT:
				m_alt = false;
				break;
			case VirtualKeyCodes.VK_CONTROL:
				m_control = false;
				break;
			default:
				Controller.this.released(canvas, keyCode, keyChar);
				break;
			}
		}

		@Override
		public void typed(Canvas canvas, char keyChar) {
			switch (keyChar) {
			case '+':
				m_view.zoomIn();
				break;
			case '-':
				m_view.zoomOut();
				break;
			case '=':
				m_view.resetZoom();
				break;

			default:
				Controller.this.typed(canvas, keyChar);
				break;
			}
		}
	}

	class MouseListener implements Canvas.MouseListener {

		@Override
		public void moved(Canvas canvas, int px, int py) {

			// inform the model about the current focus,
			// that is, where the mouse is pointing at in the world.

			Controller.this.moved(canvas, px, py);

		}

		@Override
		public void pressed(Canvas canvas, int bno, int x, int y) {
			Controller.this.pressed(canvas, bno, x, y);
		}

		@Override
		public void released(Canvas canvas, int bno, int x, int y) {
			Controller.this.released(canvas, bno, x, y);
		}
	}

}
