package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispBoolean;
import com.vlad.lisp.atom.LispList;
import com.vlad.lisp.atom.LispNumber;

public class LispNumberGreaterOrEqual extends LispAbstractCommand {

	public LispNumberGreaterOrEqual(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() != 3) {
			throw new LispException("Invalid arguments amount", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispBoolean result = null;
		
		LispAtom atomLeft = evaluateAtom(getParameters().get(1), arguments);
		LispAtom atomRight = evaluateAtom(getParameters().get(2), arguments);
		
		if (!atomLeft.isNumber()) {
			throw new LispException("Invalid value, expecting for number", atomLeft);
		}
		
		if (!atomRight.isNumber()) {
			throw new LispException("Invalid value, expecting for number", atomRight);
		}
		
		result = ((LispNumber) atomLeft).isGreaterOrEqualTo((LispNumber) atomRight);
		
		return result;
	}
}
