package com.alex.customerstatementprocessor.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {
	/**
	 * Forwarding all requests that do not match an API endpoint to the frontend app
	 */
	@GetMapping(path = "/**/{[path:[^\\\\.]*}")
	public String forwardToAngular() {
		return "forward:/";
	}
}
