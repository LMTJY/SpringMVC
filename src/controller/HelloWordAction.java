package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class HelloWordAction {
	
	@RequestMapping("hello.html")
	public String hello(){
		return "hello";
	}
}
