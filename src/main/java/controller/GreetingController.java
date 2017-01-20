package controller;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Greeting;
import model.User;

@RequestMapping("/api")
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();



    @RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "hello") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

	@RequestMapping("/getCustomer")
	public ArrayList<User> getCustomer(@RequestParam(value = "email") String email) {
		ArrayList<User> arrayCustomer = new ArrayList<User>();
		

		return arrayCustomer;
	}


}
