package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;

public interface LispCommand {

	public LispGlobal getGlobal();
	public LispList getParameters();
	
	public LispAtom execute(LispArguments arguments) throws LispException;
	public LispAtom execute() throws LispException;
}
