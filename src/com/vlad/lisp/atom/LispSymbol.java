package com.vlad.lisp.atom;

public class LispSymbol extends LispAtom {

	public LispSymbol(Object value) {
		super(value);
	}
	
	public String getName() {
		return (String) getValue();
	}
}
