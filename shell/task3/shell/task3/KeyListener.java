package shell.task3;

import oop.graphics.Canvas;


import oop.shell.IShell;

public class KeyListener implements Canvas.KeyListener {

  Canvas m_canvas;
  IShell m_shell;
  KeyListener(Canvas canvas, IShell shell) {
    this.m_canvas = canvas;
    this.m_shell = shell;
    canvas.set(this);
  }

  @Override
  public void pressed(Canvas canvas, int keyCode, char keyChar) {
    //System.out.printf("TextLine: pressed=[%d]\n", keyCode);
    m_shell.pressed(keyCode, keyChar);
  }

  @Override
  public void released(Canvas canvas, int keyCode, char keyChar) {
    //System.out.printf("TextLine: released=[%d]\n", keyCode);
	  m_shell.released(keyCode, keyChar);
  }

  @Override
  public void typed(Canvas canvas, char keyChar) {
    m_shell.typed(keyChar);
  }


}

