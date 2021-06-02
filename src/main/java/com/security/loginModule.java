package com.security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import javax.security.auth.*;

public class loginModule implements LoginModule{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CallbackHandler cbh = null;
	public loginModule(String string, callbackhandler callbackhandler) {
		this.cbh = callbackhandler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,Map<String, ?> options) {
		// TODO Auto-generated method stub
		this.cbh = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		System.out.println("login vainkooo");
		Callback[] callbackArray= new Callback[1];
		callbackArray[0] = new NameCallback("uname");
		
		
		  
		try {
			cbh.handle(callbackArray);
		} catch (IOException | UnsupportedCallbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String status = ((NameCallback) callbackArray[0]).getName();
		if(status.equals("success")) {
			return true;
		}
		else if(status.equals("failure")) {
			throw new LoginException("Login failed");
		}
		
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		System.out.println("commit vainkooo");
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		System.out.println("abort vainkooo");
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		System.out.println("login vainkooo");
		return true;
	}


}
