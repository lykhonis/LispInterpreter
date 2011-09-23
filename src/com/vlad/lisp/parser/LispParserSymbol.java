package com.vlad.lisp.parser;

public class LispParserSymbol extends LispParserAtom {

	public LispParserSymbol(String data, int startPosition, int endPosition, LispParserAtom parent) {
		super(data, startPosition, endPosition, parent);
	}

	@Override
	public String getValue() {
		String value = super.getValue();

		return value.substring(1);
	}
}
