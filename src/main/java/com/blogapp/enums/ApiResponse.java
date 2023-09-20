package com.blogapp.enums;

import com.blogapp.constants.ApiConstants;

public enum ApiResponse {
	
	DATA(ApiConstants.DATA),MESSAGE(ApiConstants.MESSAGE),
    TOKEN(ApiConstants.TOKEN),SUCCESS(ApiConstants.SUCCESS);

    String data;
    
    ApiResponse(String data2) {
    	
        this.data=data2;
    }
	
}
