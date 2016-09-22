package br.com.finance.financeiro_pessoal.service;

public class HandlerRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -7385459694927362346L;
	
	public static void handlerRuntimeException(String exception, Class<?> clazz){
		throw new RuntimeException(exception + " - " + clazz.getSimpleName());
	}

}
