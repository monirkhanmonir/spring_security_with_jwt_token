package com.excellenceict.eprescription.util;

public final class UrlConstraint {
	private UrlConstraint() {};
	
	private static final String VERSION = "/v1";
	private static final String API = "/api";
	
	public static class ApiManager {
		public static final String ROOT = VERSION+API+"/doctmate";
		public static final String CREATE = "/create";
		public static final String UPDATE = "/update/{id}";
		
		}
	
	public static class AuthManager {
		public static final String ROOT = API+"/auth";
		public static final String LOGIN = "/login";
	
		
		}
}
