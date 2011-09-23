package com.vlad;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import com.vlad.lisp.LispAtomFormatter;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispAtomFactory;
import com.vlad.lisp.command.LispCommand;
import com.vlad.lisp.command.LispCommandFactory;
import com.vlad.lisp.command.LispDefun;
import com.vlad.lisp.parser.LispParser;
import com.vlad.lisp.parser.LispParserAtom;

public class Main {

	public static void main(String[] args) throws Exception {
		boolean printAtomResult = args.length > 1 && args[1].equals("-r");

		// read lisp code from file to String
		String content = readAllTextFromFile(args[0]);

		// lisp global data, such as user defined functions and/or variables
		LispGlobal global = new LispGlobal();

		// lisp parser to parse the lisp code
		LispParser parser = new LispParser(content);

		// go throw parsed items and execute each one
		for (LispParserAtom parserAtom : parser) {

			// printOutParserItem(parserAtom, 0);

			// create lisp atom from parsed item as atom
			LispAtom atom = LispAtomFactory.create(parserAtom);

			// create command from lisp atom (atom must be lisp list)
			// or just print atom out
			if (LispCommandFactory.isCommand(atom, global)) {
				LispCommand command = LispCommandFactory.create(atom, global);

				// defun is not for running execute command
				if (!(command instanceof LispDefun)) {
					LispAtom result = command.execute();

					if (printAtomResult) {
						System.out.println("=> " + LispAtomFormatter.toString(result));
					}
				}
			} else if (printAtomResult) {
				System.out.println("=> " + LispAtomFormatter.toString(atom));
			}
		}
	}

	// private static void printOutLevel(int level) {
	// for (int i = 0; i < level; i++) {
	// System.out.print("  ");
	// }
	// }
	//
	// private static void printOutParserItem(LispParserAtom item, int level) {
	// printOutLevel(level);
	//
	// if (item instanceof LispParserList) {
	// System.out.println("(");
	//
	// for (LispParserAtom child : (LispParserList) item) {
	// printOutParserItem(child, level + 1);
	// }
	//
	// printOutLevel(level);
	// System.out.println(")");
	// } else if (item instanceof LispParserAtom) {
	// System.out.println("\"" + item.getValue() + "\"");
	// } else if (item instanceof LispParserSymbol) {
	// System.out.println("\"" + item.getValue() + "\"");
	// } else if (item instanceof LispParserString) {
	// System.out.println("\"" + item.getValue() + "\"");
	// } else {
	// System.out.println("Unknown element: \"" + item.getValue() + "\"");
	// }
	// }

	private static String readAllTextFromFile(String filename) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(filename));
		try {
			FileChannel fileChannel = fileInputStream.getChannel();
			MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
			return new StringBuilder(Charset.defaultCharset().decode(mappedByteBuffer)).toString();
		} finally {
			fileInputStream.close();
		}
	}
}
