package com.blogapp.constants;

public class ApiURI {
	
	public static final String BASE_URL = "/api/v1";
	
//	User Api's
	public static final String USER = "/user";
	public static final String CREATE_USER = "/";
	public static final String UPDATE_USER = "/{userId}";
	public static final String GET_USER_BY_ID = "/{userId}";
	public static final String GET_ALL_USER = "/";
	public static final String DELETE_USER = "/{userId}";
	
	
//	Category Api's
	public static final String CATEGORY = "/category";
	public static final String CREATE_CATEGORY = "/";
	public static final String UPDATE_CATEGORY = "/{categoryId}";
	public static final String GET_CATEGORY_BY_ID = "/{categoryId}";
	public static final String GET_ALL_CATEGORY = "/";
	public static final String DELETE_CATEGORY = "/{categoryId}";

	
//	Post Api's
	public static final String POST = "/post";
	public static final String CREATE_POST = "/category/{categoryId}/user/{userId}";
	public static final String UPDATE_POST = "/{postId}";
	public static final String GET_ALL_POSTS = "/";
	public static final String GET_POST_BY_ID = "/{postId}";
	public static final String DELETE_POST = "/{postId}";
	public static final String CATEGORY_POSTS = "/category/{categoryId}";
	public static final String USER_POSTS = "/user/{userId}";
	public static final String PAGINATION = "/pagination";
	public static final String SEARCH_POST = "/search/{keyword}";
	
	
//	Comment Api's 
	public static final String COMMENT = "/comment";
	public static final String CREATE_COMMENT = "/user/{userId}/post/{postId}";
	public static final String DELETE_COMMENT = "/{commentId}";
	
	
}
