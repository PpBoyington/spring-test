package hello;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	@Autowired

	JdbcTemplate jdbcTemplate;

    @RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "hello") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

	@RequestMapping("/getCustomer")
	public ArrayList<Customer> getCustomer(@RequestParam(value = "name") String name) {
		ArrayList<Customer> arrayCustomer = new ArrayList<Customer>();
		String query = "SELECT id, first_name, last_name FROM customers";
		if (query != null) {
			query = query.concat(" Where first_name = ?");
	    }

		jdbcTemplate.query(query, new Object[] { name },
				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))

		).forEach(customer -> arrayCustomer.add(customer));

		return arrayCustomer;
	}

}
