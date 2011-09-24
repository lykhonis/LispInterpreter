package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispBoolean;
import com.vlad.lisp.atom.LispList;

public class LispCommonp extends LispAbstractCommand {

	public LispCommonp(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() != 2) {
			throw new LispException("Invalid arguments amount", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispBoolean result = null;
		
		LispAtom atom = evaluateAtom(getParameters().get(1), arguments);
		
		result = new LispBoolean(atom.isBoolean() || atom.isNumber() || atom.isList() || atom.isString() || atom.isSymbol());
		
		return result;
	}
}
