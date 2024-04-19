package id.ac.ui.cs.pustakaone.identity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IdentityApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		assertNotNull(applicationContext);
	}

}
