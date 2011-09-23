package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;
import com.vlad.lisp.atom.LispNumber;
import com.vlad.lisp.atom.LispNumber.Type;
import com.vlad.lisp.atom.LispString;
import com.vlad.lisp.atom.LispSymbol;

public class LispPrint extends LispAbstractCommand {

	public LispPrint(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() < 2) {
			throw new LispException("Too few arguments", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispAtom result = null;

		for (int i = 1; i < getParameters().size(); i++) {
			LispAtom atom = evaluateAtom(getParameters().get(i), arguments);
			
			if (atom.isString()) {
				System.out.print(((LispString) atom).getString());
			} else if (atom.isSymbol()) {
				System.out.print(((LispSymbol) atom).getName());
			} else if (atom.isNumber()) {
				if (((LispNumber) atom).getType() == Type.DOUBLE) {
					System.out.print(((LispNumber) atom).getDouble());
				} else if (((LispNumber) atom).getType() == Type.INTEGER) {
					System.out.print(((LispNumber) atom).getInteger());
				}
			}
			
			result = atom;
		}
		
		System.out.println();

		return result;
	}
}
