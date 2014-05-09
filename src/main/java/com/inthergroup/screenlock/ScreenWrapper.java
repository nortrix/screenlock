package com.inthergroup.screenlock;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @since May 7, 2014
 * @author dmorari
 */
public class ScreenWrapper
{

  private static GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

  public void wrap(LockFrame frame)
  {
    devices[0].setFullScreenWindow(frame);

    final List<JFrame> frames = DummyFrame.hideMonitors();
    frames.add(frame);

    frame.setUnlock(new Unlock() {

      public void unlock()
      {
        for (JFrame f : frames) {
          f.dispose();
        }
      }
    });

  }

  private static class DummyFrame extends JFrame
  {

    public static List<JFrame> hideMonitors(){
      List<JFrame> frames = new ArrayList<JFrame>();
      for (int i = 1; i < devices.length; i++) {
        DummyFrame frame = new DummyFrame();
        devices[i].setFullScreenWindow(frame);
        frames.add(frame);

      }

      return frames;
    }
    
    public DummyFrame()
    {
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setAlwaysOnTop(true);
      this.setResizable(false);
      this.setUndecorated(true);

      JPanel panel = new JPanel(new FlowLayout());
      panel.setBackground(Color.BLACK);

      this.add(panel);
    }
  }
}
