package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;

public class LispProgn extends LispAbstractCommand {

	public LispProgn(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() < 1) {
			throw new LispException("Too few arguments", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispAtom result = null;

		for (int i = 1; i < getParameters().size(); i++) {
			LispAtom atom = getParameters().get(i);
			LispCommand command = LispCommandFactory.create(atom, getGlobal());
			result = command.execute(arguments);
		}

		return result;
	}
}
