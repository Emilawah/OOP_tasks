package oop.filesystem;

import java.io.PrintStream;

public class MockNode extends Node {
  public MockNode() {
    super();
  }

  public MockNode(Node parent, String name) {
    super(parent, name);
  }

  public static void tests(PrintStream ps) {
    Node root = new MockNode();
    Node toto = new MockNode(root,"toto");
    if (root!=toto.parent())
      throw new Error();
    Node tata = new MockNode(root,"tata");
    if (root!=tata.parent())
      throw new Error();
    Node titi = new MockNode(toto,"titi");
    if (toto!=titi.parent())
      throw new Error();
    
    try {
      root.remove(toto);
      throw new Error();
    } catch (IllegalStateException ex){
    }

    try {
      toto.remove(tata);
      throw new Error();
    } catch (IllegalStateException ex){
    }

    toto.remove(titi);
    if (null!=titi.parent())
      throw new Error();
    
    root.remove(toto);
    if (null!=toto.parent())
      throw new Error();
    
    root.remove(tata);
    if (null!=tata.parent())
      throw new Error();
  }

  public static void main(String[]  args) {
    tests(System.out);
    System.out.println("PASSED.");
  }
}
