package com.ljproject.handler;

import java.util.HashMap;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {
	public final static HashMap<String, Integer> failedAttemp = new HashMap<>();
	
	@EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        String username = (String) event.getAuthentication().getPrincipal();
        if(failedAttemp.containsKey(username)) {
        	if(failedAttemp.get(username) == 3) {
        		///
        	} else {
        		failedAttemp.put(username, failedAttemp.get(username) + 1);
        	}
        } else {
        	failedAttemp.put(username, 1);
        }
    }
}
