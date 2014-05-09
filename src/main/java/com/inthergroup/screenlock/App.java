package com.inthergroup.screenlock;

/**
 *
 */
public class App
{


  public static void main(String[] args)
  {
      ScreenWrapper screenWrapper = new ScreenWrapper();
      screenWrapper.wrap(new LockFrame());
  }
}
