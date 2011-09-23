package com.vlad.lisp.atom;

public class LispArgument extends LispAtom {

	private String mName;
	
	public LispArgument(String name, LispAtom atom) {
		super(atom);
		mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public LispAtom getAtom() {
		return (LispAtom) getValue();
	}
	
	@Override
	public Object getValue() {
		Object result = super.getValue();
		
		if (result instanceof LispArgument) {
			result = ((LispArgument) result).getValue();
		}
		
		return result;
	}
}
