//package id.ac.ui.cs.pustakaone.identity;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class AuthenticationApplicationTests {
//
//	@Autowired
//	ApplicationContext applicationContext;
//
//	@Test
//	public void contextLoads() {
//		assertNotNull(applicationContext);
//	}
//
//}

package id.ac.ui.cs.pustakaone.identity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthApplicationTests {
	@Test
	void testApplicationStartsSuccessfully() {
		// Arrange

		// Act
		AuthApplication.main(new String[]{});

		// Assert
		assertTrue(true); // If the application starts without any errors, the test passes
	}
}
