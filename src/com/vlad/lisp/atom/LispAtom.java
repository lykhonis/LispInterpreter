package com.vlad.lisp.atom;

import com.vlad.lisp.atom.LispNumber.Type;

public class LispAtom {

	private Object mValue;
	
	public LispAtom(Object value) {
		mValue = value;
	}

	public Object getValue() {
		return mValue;
	}
	
	public boolean isAtom() {
		return getClass().equals(LispAtom.class);
	}
	
	public boolean isNumber() {
		return getClass().equals(LispNumber.class);
	}
	
	public boolean isInteger() {
		return isNumber() && (((LispNumber) this).getType() == Type.INTEGER);
	}
	
	public boolean isDouble() {
		return isNumber() && (((LispNumber) this).getType() == Type.DOUBLE);
	}
	
	public boolean isString() {
		return getClass().equals(LispString.class);
	}
	
	public boolean isList() {
		return getClass().equals(LispList.class);
	}
	
	public boolean isNil() {
		return (getValue() == null) || (isAtom() && getValue().equals("nil")) || (isList() && ((LispList) this).isEmpty());
	}

	public boolean isSymbol() {
		return getClass().equals(LispSymbol.class);
	}

	public boolean isArgument() {
		return getClass().equals(LispArgument.class);
	}
	
	public boolean isBoolean() {
		return getClass().equals(LispBoolean.class) || isNil() || (isAtom() && getValue().equals("t"));
	}
}
