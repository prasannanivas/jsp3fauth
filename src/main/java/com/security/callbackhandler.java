package com.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.servlet.http.HttpSession;

public class callbackhandler implements CallbackHandler{

	private HttpSession session;
	
	public callbackhandler(HttpSession session) {
		// TODO Auto-generated constructor stub
		this.session = session;
		
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		// TODO Auto-generated method stub
		NameCallback ncb = (NameCallback) callbacks[0];
		ncb.setName((String)session.getAttribute("jaas"));
		
	}
	
}
