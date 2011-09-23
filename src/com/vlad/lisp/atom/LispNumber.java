package com.vlad.lisp.atom;

import com.vlad.lisp.LispException;

public class LispNumber extends LispAtom {

	public enum Type {
		INTEGER, DOUBLE
	}

	private Type mType;
	private LispInteger mInteger;
	private LispDouble mDouble;

	public LispNumber(Object value) throws LispException {
		super(value);

		if (LispInteger.isValid(value)) {
			mType = Type.INTEGER;
			mInteger = new LispInteger(value);
		} else if (LispDouble.isValid(value)) {
			mType = Type.DOUBLE;
			mDouble = new LispDouble(value);
		}
	}

	public static boolean isValid(Object value) {
		return LispInteger.isValid(value) || LispDouble.isValid(value);
	}

	public Type getType() {
		return mType;
	}

	public Integer getInteger() {
		return mInteger.getInteger();
	}

	public Double getDouble() {
		return mDouble.getDouble();
	}

	@Override
	public Object getValue() {
		Object result = null;

		if (mType == Type.INTEGER) {
			result = getInteger();
		} else if (mType == Type.DOUBLE) {
			result = getDouble();
		}

		return result;
	}

	public LispNumber add(LispNumber number) throws LispException {
		LispNumber result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getDouble() + number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispNumber(getDouble() + number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getInteger() + number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispNumber(getInteger() + number.getInteger());
		}

		return result;
	}

	public LispNumber subtract(LispNumber number) throws LispException {
		LispNumber result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getDouble() - number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispNumber(getDouble() - number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getInteger() - number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispNumber(getInteger() - number.getInteger());
		}

		return result;
	}

	public LispNumber negative() throws LispException {
		LispNumber result = null;

		if (mType == Type.DOUBLE) {
			result = new LispNumber(-getDouble());
		} else if (mType == Type.INTEGER) {
			result = new LispNumber(-getInteger());
		}

		return result;
	}

	public LispNumber multiply(LispNumber number) throws LispException {
		LispNumber result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getDouble() * number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispNumber(getDouble() * number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getInteger() * number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispNumber(getInteger() * number.getInteger());
		}

		return result;
	}

	public LispNumber divide(LispNumber number) throws LispException {
		LispNumber result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getDouble() / number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispNumber(getDouble() / number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getInteger() / number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispNumber(getInteger() / number.getInteger());
		}

		return result;
	}

	public LispNumber modulo(LispNumber number) throws LispException {
		LispNumber result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getDouble() % number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispNumber(getDouble() % number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispNumber(getInteger() % number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispNumber(getInteger() % number.getInteger());
		}

		return result;
	}
	
	public LispBoolean isLessThan(LispNumber number) throws LispException {
		LispBoolean result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getDouble() < number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getDouble() < number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getInteger() < number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getInteger() < number.getInteger());
		}

		return result;
	}
	
	public LispBoolean isLessOrEqualTo(LispNumber number) throws LispException {
		LispBoolean result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getDouble() <= number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getDouble() <= number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getInteger() <= number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getInteger() <= number.getInteger());
		}

		return result;
	}
	
	public LispBoolean isGreaterThan(LispNumber number) throws LispException {
		LispBoolean result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getDouble() > number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getDouble() > number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getInteger() > number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getInteger() > number.getInteger());
		}

		return result;
	}
	
	public LispBoolean isGreaterOrEqualTo(LispNumber number) throws LispException {
		LispBoolean result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getDouble() >= number.getDouble());
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getDouble() >= number.getInteger());
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getInteger() >= number.getDouble());
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getInteger() >= number.getInteger());
		}

		return result;
	}
	
	public LispBoolean isEqualTo(LispNumber number) throws LispException {
		LispBoolean result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(getDouble().equals(number.getDouble()));
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getDouble().equals(number.getInteger().doubleValue()));
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(number.getDouble().equals(getInteger().doubleValue()));
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispBoolean(getInteger().equals(number.getInteger()));
		}

		return result;
	}
	
	public LispBoolean isNotEqualTo(LispNumber number) throws LispException {
		LispBoolean result = null;

		if (mType == Type.DOUBLE && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(!getDouble().equals(number.getDouble()));
		} else if (mType == Type.DOUBLE && number.getType() == Type.INTEGER) {
			result = new LispBoolean(!getDouble().equals(number.getInteger().doubleValue()));
		} else if (mType == Type.INTEGER && number.getType() == Type.DOUBLE) {
			result = new LispBoolean(!number.getDouble().equals(getInteger().doubleValue()));
		} else if (mType == Type.INTEGER && number.getType() == Type.INTEGER) {
			result = new LispBoolean(!getInteger().equals(number.getInteger()));
		}

		return result;
	}
}
