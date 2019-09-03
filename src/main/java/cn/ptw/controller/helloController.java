/**
 * 
 */
package cn.ptw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mr Zhang
 *
 */
@Controller
public class helloController {
 
	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		return "hello_world";
	}
}
