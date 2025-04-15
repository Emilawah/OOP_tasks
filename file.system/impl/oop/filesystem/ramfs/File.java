package oop.filesystem.ramfs;

import edu.filesystem.IDirectory;
import edu.filesystem.IFile;
import oop.filesystem.Node;

public class File extends Node implements IFile{
	
	private String name;
	private Node parent;
	
	public File(Node parent, String name) {
		this.parent = parent;
		this.name = name;
	}
	
	
	@Override
	public IDirectory directory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int available() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte read() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void write(byte val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seek(int offset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean move(IDirectory dst, String name) {
		// TODO Auto-generated method stub
		return false;
	}
}
