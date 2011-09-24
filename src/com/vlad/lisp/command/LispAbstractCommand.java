package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispArgument;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispBoolean;
import com.vlad.lisp.atom.LispList;

public abstract class LispAbstractCommand implements LispCommand {

	private LispGlobal mGlobal;
	private LispList mParameters;

	public LispAbstractCommand(LispGlobal global) throws LispException {
		this(new LispList(), global);
	}

	protected LispAbstractCommand(LispList parameters, LispGlobal global, Object... args) throws LispException {
		mGlobal = global;
		mParameters = parameters;

		init(args);
		validateParameters();
	}

	protected abstract void validateParameters() throws LispException;

	protected void init(Object... args) throws LispException {

	}

	@Override
	public LispList getParameters() {
		return mParameters;
	}

	@Override
	public LispGlobal getGlobal() {
		return mGlobal;
	}

	@Override
	public LispAtom execute() throws LispException {
		return execute(new LispArguments());
	}

	protected LispAtom evaluateAtom(LispAtom atom, LispArguments arguments) throws LispException {
		LispAtom result = atom;

		if (!result.isNil()) {
			if (result.isAtom()) {
				if (result.getValue().equals("t")) {
					result = new LispBoolean(true);
				} else {
					LispArgument argument = arguments.getByName((String) result.getValue());
					if (argument == null) {
						throw new LispException("Unknown atom", atom);
					}

					result = argument.getAtom();
				}
			} else if (LispCommandFactory.isPossibleCommand(result)) {
				LispCommand command = LispCommandFactory.create(result, getGlobal());

				result = command.execute(arguments);
			}
		}

		return result;
	}
}
