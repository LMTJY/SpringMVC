package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginAction {
	
	@RequestMapping("log")
	public String log(String uname,String upwd,Model model){
		if(uname.equals("LMT") && upwd.equals("123")){
			model.addAttribute("name",uname);
			return "success";
		}else{
			return "login";
		}
	}
	@RequestMapping("login")
	public String login(){
		return "login";
	}
}
