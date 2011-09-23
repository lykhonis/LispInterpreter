package com.vlad.lisp.atom;

import java.util.ArrayList;
import java.util.List;

import com.vlad.lisp.LispException;
import com.vlad.lisp.parser.LispParserAtom;
import com.vlad.lisp.parser.LispParserException;
import com.vlad.lisp.parser.LispParserList;
import com.vlad.lisp.parser.LispParserString;
import com.vlad.lisp.parser.LispParserSymbol;

public class LispAtomFactory {

	public static LispAtom create(LispParserAtom parserAtom) throws LispParserException, LispException {
		LispAtom atom = null;

		if (parserAtom instanceof LispParserSymbol) {
			atom = new LispSymbol(parserAtom.getValue());
		} else if (parserAtom instanceof LispParserString) {
			atom = new LispString(parserAtom.getValue());
		} else if (parserAtom instanceof LispParserList) {
			atom = createList((LispParserList) parserAtom);
		} else if (LispNumber.isValid(parserAtom.getValue())) {
			atom = new LispNumber(parserAtom.getValue());
		} else {
			atom = new LispAtom(parserAtom.getValue());
		}

		return atom;
	}

	private static LispList createList(LispParserList parserList) throws LispParserException, LispException {
		List<LispAtom> atoms = new ArrayList<LispAtom>();

		for (LispParserAtom parserAtom : parserList) {
			atoms.add(LispAtomFactory.create(parserAtom));
		}

		return new LispList(atoms);
	}
}
