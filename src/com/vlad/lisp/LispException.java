package com.vlad.lisp;

import com.vlad.lisp.atom.LispAtom;

public class LispException extends Exception {

	private static final long serialVersionUID = -5156954633097207391L;

	public LispException(String message) {
		super(message);
	}

	public LispException(String message, Object object) {
		this(message + ": " + object);
	}

	public LispException(String message, LispAtom atom) {
		this(message, LispAtomFormatter.toString(atom));
	}
}
