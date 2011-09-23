package com.vlad.lisp.parser;

public class LispParserException extends Exception {

	private static final long serialVersionUID = -1601180484577230344L;

	public LispParserException(String message, int errorOffset) {
		super(String.format("%s at %d", message, errorOffset));
	}
	
	public LispParserException(String message, LispParserAtom parserAtom) {
		super(String.format("%s \"%s\" at %d", message, parserAtom.getValue(), parserAtom.getStartPosition()));
	}
}
