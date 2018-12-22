package com.github.hibernate.helper.condition;

public enum SQLSymbol {
	// 利用构造函数传参
	LIKE("like"), EQ("="), GT(">"), GE(">="), LT("<"), LE("<="), NEQ("<>");
	// 定义私有变量
	private String _symbol;

	// 构造函数，枚举类型只能为私有
	private SQLSymbol(String _symbol) {
		this._symbol = _symbol;
	}

	public String get_symbol() {
		return _symbol;
	}

	@Override
	public String toString() {
		return String.valueOf(this._symbol);
	}
}
