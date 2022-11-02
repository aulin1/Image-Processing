package controller;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This class represents a readable that always fail; use for testing the controller.
 */
public class FailReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
