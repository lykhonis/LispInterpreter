package com.vlad.lisp.parser;

public class LispParserString extends LispParserAtom {

	public LispParserString(String data, int startPosition, int endPosition, LispParserAtom parent) {
		super(data, startPosition, endPosition, parent);
	}

	@Override
	public String getValue() {
		String value = super.getValue();

		return value.substring(1, value.length() - 1).replace("\\\"", "\"");
	}
}
