package lv.ctco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StudentSpringApplication.class)
@WebAppConfiguration
public class StudentSpringApplicationTests {

	@Test
	public void deleteByIdTest() {


	}


	@Test
	public void testStudents() {
		get("/students").then().statusCode(200);
	}
}
