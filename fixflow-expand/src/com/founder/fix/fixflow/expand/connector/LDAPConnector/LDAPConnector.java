package com.founder.fix.fixflow.expand.connector.LDAPConnector;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;

import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class LDAPConnector implements ConnectorHandler {

	private java.lang.String HOST;

	private int Port = 389;

	private LdapProtocol Protocol;

	private java.lang.String username;

	private java.lang.String password;

	private java.lang.String BaseDN;

	private java.lang.String Filter;

	private java.lang.String[] attributes;

	private LdapScope scope;

	private long sizelimit;

	private int timelimit;

	private java.lang.String referralhanding = "ignore";

	private LdapDereferencingAlias Aliasdeferencing;

	private List<LinkedHashMap<String,String>> result;

	private Hashtable<String, String> getEnvironment() {
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, "ldap://" + HOST + ":" + Port);
		if (Protocol.equals(LdapProtocol.LDAPS)) {
			environment.put(Context.SECURITY_PROTOCOL, "ssl");
		}
		if (!LdapProtocol.TLS.equals(Protocol) && username != null
				&& password != null) {
			environment.put(Context.SECURITY_AUTHENTICATION, "simple");
			environment.put(Context.SECURITY_PRINCIPAL, username);
			environment.put(Context.SECURITY_CREDENTIALS, password);
		} else {
			environment.put(Context.SECURITY_AUTHENTICATION, "none");
		}
		environment.put("java.naming.ldap.derefAliases", Aliasdeferencing==null?"":Aliasdeferencing
				.toString().toLowerCase());
		environment.put(Context.REFERRAL, referralhanding);
		return environment;
	}

	public void execute(ExecutionContext executionContext) throws Exception {
		final Hashtable<String, String> env = getEnvironment();
		final LdapContext ctx = new InitialLdapContext(env, null);

		StartTlsResponse response = null;
		try {
			if (LdapProtocol.TLS.equals(Protocol)) {
				StartTlsRequest request = new StartTlsRequest();
				response = (StartTlsResponse) ctx.extendedOperation(request);
				response.negotiate();
				if (username != null && password != null) {
					ctx.addToEnvironment(Context.SECURITY_AUTHENTICATION,
							"simple");
					ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, username);
					ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
				}
			}
			final SearchControls ctls = new SearchControls();
			ctls.setTimeLimit(timelimit * 1000);
			ctls.setCountLimit(sizelimit);
			ctls.setReturningAttributes(attributes);
			ctls.setSearchScope(scope.value());
			final NamingEnumeration<SearchResult> answer = ctx.search(BaseDN,
					Filter, ctls);
			long count = sizelimit;
			// count is useful in case of the size-limit is defined
			// the search method does not care about size-limit. It returns all
			// entries
			// which match with the filter.
			if (count == 0) {
				count = Long.MAX_VALUE;
			}
			result = new ArrayList<LinkedHashMap<String,String>>();
			while (count > 0 && answer.hasMore()) {
				SearchResult sr = answer.next();
				count--;
				Attributes attribs = sr.getAttributes();
				NamingEnumeration<? extends Attribute> enume = attribs.getAll();
				LinkedHashMap<String,String> elements = new LinkedHashMap<String,String>();
				while (enume.hasMore()) {
					Attribute attribute = enume.next();
					NamingEnumeration<?> all = attribute.getAll();
					while (all.hasMore()) {
						Object key = all.next();
						String value = null;
						if (key instanceof byte[]) {
							value = new String((byte[]) key, "UTF-8");
						} else {
							value = key.toString();
						}
						elements.put(attribute.getID(), value);
					}
				}
				if (!elements.isEmpty()) {
					result.add(elements);
				}
			}
		} finally {
			if (LdapProtocol.TLS.equals(Protocol) && response != null) {
				response.close();
			}
			ctx.close();
		}
	}

	public void setHOST(java.lang.String HOST) {
		this.HOST = HOST;
	}

	public void setPort(int Port) {
		this.Port = Port;
	}

	public void setProtocol(java.lang.String Protocol) {
		this.Protocol = LdapProtocol.LDAPS;
		if (Protocol != null) {
			Protocol = Protocol.toUpperCase();
			if (Protocol.equals(LdapProtocol.TLS.toString())) {
				this.Protocol = LdapProtocol.TLS;
			} else if (Protocol.equals(LdapProtocol.LDAP.toString())) {
				this.Protocol = LdapProtocol.LDAP;
			}
		}
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public void setBaseDN(java.lang.String BaseDN) {
		this.BaseDN = BaseDN;
	}

	public void setFilter(java.lang.String Filter) {
		this.Filter = Filter;
	}

	public void setAttributes(java.lang.String attributes) {
		if (attributes == null || "".equals(attributes.trim())) {
			this.attributes = null;
		} else {
			StringTokenizer tokens = new StringTokenizer(attributes, ",");
			int i = 0;
			this.attributes = new String[tokens.countTokens()];
			while (tokens.hasMoreElements()) {
				this.attributes[i] = (String) tokens.nextElement();
				i++;
			}
		}
	}

	public void setScope(java.lang.String scope) {
		this.scope = LdapScope.ONELEVEL;
		if (scope != null) {
			scope = scope.toUpperCase();
			if (scope.equals(LdapScope.BASE.toString())) {
				this.scope = LdapScope.BASE;
			} else if (scope.equals(LdapScope.SUBTREE.toString())) {
				this.scope = LdapScope.SUBTREE;
			}
		}
	}

	public void setSizelimit(long sizelimit) {
		this.sizelimit = sizelimit;
	}

	public void setTimelimit(int timelimit) {
		this.timelimit = timelimit;
	}

	public void setReferralhanding(java.lang.String referralhanding) {
		this.referralhanding = referralhanding;
	}

	public void setAliasdeferencing(java.lang.String Aliasdeferencing) {
	    this.Aliasdeferencing = LdapDereferencingAlias.ALWAYS;
	    if (Aliasdeferencing.equals("never")) {
	      this.Aliasdeferencing = LdapDereferencingAlias.NEVER;
	    } else if (Aliasdeferencing.equals("finding")) {
	      this.Aliasdeferencing = LdapDereferencingAlias.FINDING;
	    } else if (Aliasdeferencing.equals("searching")) {
	      this.Aliasdeferencing = LdapDereferencingAlias.SEARCHING;
	    }
	}

	public List<LinkedHashMap<String,String>> getLdapAttributeList() {
		return result;
	}

}