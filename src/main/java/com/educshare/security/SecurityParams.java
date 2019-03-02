package com.educshare.security;

public interface SecurityParams {
	public static final String JWT_HEADER_NAME = "Authorization";
	public static final String SECRET = "med@.net";
	public static final long EXPIRATION = 5000000 * 24 * 5600 *1000; // 864_000_000 10days
	public static final String HEADER_PREFIX = "Bearer ";

}
