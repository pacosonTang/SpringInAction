package com.spring.spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*public class SpittleNotFoundException extends RuntimeException{

}*/

@ResponseStatus(value=HttpStatus.NOT_FOUND,
				reason="spittle not found")
public class SpittleNotFoundException extends RuntimeException{

}
