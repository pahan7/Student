package lv.ctco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StudentSpringApplication.class)
@WebAppConfiguration
public class StudentSpringApplicationTests {

	@Test
	public void deleteByIdTest() {
		delete("/students/0").then().statusCode(200);
		delete("/students/3").then().statusCode(404);
	}

	@Test
	public void updateById() {

        Student student = new Student();
        student.setFirstName("Alina");
        student.setLastName("Vasilevska");
        student.setId(0);

		given().
				contentType("application/json").
                body(student).
				//body("{\"firstName\": \"Ivan\", \"lastName\": \"b\", \"id\": 0}").
				when().put("/students/5").then().statusCode(404);
                when().put("/students/0").then().statusCode(200);
	}


	@Test
	public void testStudents() {
		get("/students").then().statusCode(200);
	}
}
