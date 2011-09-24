package com.vlad.lisp.command;

import java.util.Arrays;
import java.util.List;

import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;

public class LispCommandFactory {

	public static LispCommand create(LispAtom atom, LispGlobal global) throws LispException {
		LispCommand command = null;
		LispList list = null;

		if (!atom.isList()) {
			throw new LispException("Invalid value, expecting for list", atom);
		}

		list = (LispList) atom;

		if (list.isNil()) {
			throw new LispException("Invalid command, expecting for identifier", list);
		}

		String commandName = (String) list.get(0).getValue();

		if ("let".equals(commandName)) {
			command = new LispLet(list, global);
		} else if ("print".equals(commandName)) {
			command = new LispPrint(list, global);
		} else if ("+".equals(commandName)) {
			command = new LispNumberAdd(list, global);
		} else if ("-".equals(commandName)) {
			command = new LispNumberSubtract(list, global);
		} else if ("*".equals(commandName)) {
			command = new LispNumberMultiply(list, global);
		} else if ("/".equals(commandName)) {
			command = new LispNumberDivide(list, global);
		} else if ("%".equals(commandName)) {
			command = new LispNumberModulo(list, global);
		} else if ("<".equals(commandName)) {
			command = new LispNumberLess(list, global);
		} else if ("<=".equals(commandName)) {
			command = new LispNumberLessOrEqual(list, global);
		} else if ("=".equals(commandName)) {
			command = new LispNumberEqual(list, global);
		} else if ("/=".equals(commandName)) {
			command = new LispNumberNotEqual(list, global);
		} else if (">".equals(commandName)) {
			command = new LispNumberGreater(list, global);
		} else if (">=".equals(commandName)) {
			command = new LispNumberGreaterOrEqual(list, global);
		} else if ("defun".equals(commandName)) {
			command = new LispDefun(list, global);
		} else if ("if".equals(commandName)) {
			command = new LispIf(list, global);
		} else if ("progn".equals(commandName)) {
			command = new LispProgn(list, global);
		} else if ("cond".equals(commandName)) {
			command = new LispCond(list, global);
		} else if ("not".equals(commandName)) {
			command = new LispNot(list, global);
		} else if ("null".equals(commandName)) {
			command = new LispNull(list, global);
		} else if ("and".equals(commandName)) {
			command = new LispAnd(list, global);
		} else if ("or".equals(commandName)) {
			command = new LispOr(list, global);
		} else if ("commonp".equals(commandName)) {
			command = new LispCommonp(list, global);
		} else if ("floatp".equals(commandName)) {
			command = new LispFloatp(list, global);
		} else if ("functionp".equals(commandName)) {
			command = new LispFunctionp(list, global);
		} else if ("integerp".equals(commandName)) {
			command = new LispIntegerp(list, global);
		} else if ("numberp".equals(commandName)) {
			command = new LispNumberp(list, global);
		} else if ("stringp".equals(commandName)) {
			command = new LispStringp(list, global);
		} else if ("symbolp".equals(commandName)) {
			command = new LispSymbolp(list, global);
		} else {
			LispDefun defun = global.getDefinedFunctionByName(commandName);
			if (defun != null) {
				command = new LispDefinedFunction(list, defun);
			} else {
				throw new LispException("Unknown command identifier", list);
			}
		}

		return command;
	}

	private static final List<String> mValidCommandNames = Arrays.asList("let", "print", "+", "-", "*", "/", "%", "<",
			"<=", "=", ">", ">=", "/=", "defun", "if", "progn", "cond", "not", "null", "and", "or", "commonp",
			"floatp", "functionp", "integerp", "numberp", "stringp", "symbolp");

	public static boolean isPossibleCommand(LispAtom atom) {
		if (atom.isList() && !atom.isNil()) {
			LispAtom commandAtom = ((LispList) atom).get(0);

			if (commandAtom.isAtom()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidCommandName(String name) {
		return mValidCommandNames.contains(name);
	}

	public static boolean isCommand(LispAtom atom, LispGlobal global) {
		if (isPossibleCommand(atom)) {
			String commandName = (String) ((LispList) atom).get(0).getValue();

			return isValidCommandName(commandName) || global.isDefinedFunction(commandName);
		}
		return false;
	}
}
