/*
package com.mvc.mudi.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mudi.interceptor.AccessInterceptor;
import com.mvc.mudi.interceptor.AccessInterceptor.Access;
@RequestMapping("access")
@RestController
public class AccessRest {
	
	@GetMapping
	List<Access> getAccess(){
		return AccessInterceptor.accessList; 
	}
}
*/