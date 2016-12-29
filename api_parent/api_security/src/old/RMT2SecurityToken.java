package org.authentication;

import com.RMT2Base;;

/**
 * This is a token to indicate an authenticated user.  It stores the detailed information 
 * about the user's session and the mechanism(s) used to perform user authentication.
 *     
 * @author rterrell
 *
 */
public class RMT2SecurityToken extends RMT2Base {

    /**
     * Variable used to track data regarding the user's session 
     */
    private RMT2SessionBean session;

    /**
     * Variable used to track the source of the authentication scheme. 
     */
    private Object token;

    /**
     * @return the session
     */
    public RMT2SessionBean getSession() {
	return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(RMT2SessionBean session) {
	this.session = session;
    }

    /**
     * @return the token
     */
    public Object getToken() {
	return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(Object token) {
	this.token = token;
    }

}
