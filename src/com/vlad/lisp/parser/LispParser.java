package com.vlad.lisp.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class LispParser implements Iterable<LispParserAtom> {

	private List<LispParserAtom> mItems;

	public LispParser(String data) throws LispParserException {
		mItems = new ArrayList<LispParserAtom>();

		parse(data);
	}

	@Override
	public Iterator<LispParserAtom> iterator() {
		return mItems.iterator();
	}

	private enum State {
		NONE, STRING, SYMBOL, LIST, COMMENT
	}

	private void parse(String data) throws LispParserException {
		State state = State.NONE;
		State lastState = State.NONE;
		LispParserAtom item = null;
		Stack<LispParserAtom> stackItems = new Stack<LispParserAtom>();
		int atomStartPosition = 0;

		for (int i = 0; i < data.length(); i++) {
			char currentCharacter = data.charAt(i);

			if (currentCharacter == '\'') {
				if (state == State.LIST) {
					state = State.SYMBOL;

					if (atomStartPosition < i) {
						new LispParserAtom(data, atomStartPosition, i - 1, item);
					}

					stackItems.push(item);
					item = new LispParserSymbol(data, i, i, item);
				}
			} else if (currentCharacter == ';') {
				if ((state == State.NONE) || (state == State.LIST)) {
					lastState = state;
					state = State.COMMENT;
				}
			} else if (currentCharacter == '"') {
				if (state == State.LIST) {
					state = State.STRING;

					if (atomStartPosition < i) {
						new LispParserAtom(data, atomStartPosition, i - 1, item);
					}

					stackItems.push(item);
					item = new LispParserString(data, i, i, item);
				} else if (state == State.STRING) {
					char previousCharacter = data.charAt(i - 1);

					if (previousCharacter == '\\') {
						continue;
					}

					state = State.LIST;

					atomStartPosition = i + 1;

					item.setEndPosition(i);
					item = stackItems.pop();
				}
			} else if (currentCharacter == '(') {
				if (state == State.SYMBOL) {
					if (item.getStartPosition() == i - 1) {
						throw new LispParserException("Invalid symbol expecting for characters", item.getStartPosition());
					}
					
					state = State.LIST;
					
					item.setEndPosition(i - 1);
					item = stackItems.pop();

					atomStartPosition = i + 1;	
				}
				if (state == State.NONE) {
					state = State.LIST;
					atomStartPosition = i + 1;

					stackItems.push(null);
					item = new LispParserList(data, i, i);
					mItems.add(item);
				} else if (state == State.LIST) {
					if (atomStartPosition < i) {
						new LispParserAtom(data, atomStartPosition, i - 1, item);
					}
					atomStartPosition = i + 1;

					stackItems.push(item);
					item = new LispParserList(data, i, i, item);
				}
			} else if (currentCharacter == ')') {
				if (state == State.SYMBOL) {
					state = State.LIST;
					
					item.setEndPosition(i - 1);
					item = stackItems.pop();

					atomStartPosition = i + 1;	
				}
				if (state == State.LIST) {
					if (stackItems.isEmpty()) {
						throw new LispParserException("Mismatch \")\"", i + 1);
					}

					if (atomStartPosition < i) {
						new LispParserAtom(data, atomStartPosition, i - 1, item);
					}
					atomStartPosition = i + 1;

					item.setEndPosition(i);
					item = stackItems.pop();

					if (stackItems.isEmpty()) {
						state = State.NONE;
					}
				}
			} else if (Character.isWhitespace(currentCharacter)) {
				if (state == State.LIST) {
					if (atomStartPosition < i) {
						new LispParserAtom(data, atomStartPosition, i - 1, item);
					}
					atomStartPosition = i + 1;
				} else if (state == State.COMMENT) {
					if (currentCharacter == '\r' || currentCharacter == '\n') {
						state = lastState;
						atomStartPosition = i + 1;
					}
				} else if (state == State.SYMBOL) {
					if (item.getStartPosition() == i - 1) {
						throw new LispParserException("Invalid symbol expecting for characters", item.getStartPosition());
					}
					
					state = State.LIST;

					atomStartPosition = i + 1;

					item.setEndPosition(i - 1);
					item = stackItems.pop();
				}
			}
		}

		if (!stackItems.isEmpty()) {
			throw new LispParserException("Mismatch \")\"", data.length());
		}
	}
}
