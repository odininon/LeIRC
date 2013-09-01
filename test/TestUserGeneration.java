import org.junit.BeforeClass;
import org.junit.Test;

import com.leirc.api.exception.JsonReadException;
import com.leirc.api.exception.JsonWriteException;
import com.leirc.api.rsrc.Resources;
import com.leirc.api.user.User;
import com.leirc.api.user.UserHelper;

public class TestUserGeneration{
	@BeforeClass
	public static void checkDirs(){
		Resources.checkDirs();
	}
	
	@Test
	public void testUserGeneration() throws JsonWriteException{
		User user = new User("Asyncronous");
		UserHelper.writeUserData(user);
	}
	
	@Test
	public void testUserReading() throws JsonReadException{
		User user = UserHelper.loadUserData("Asyncronous");
		System.out.println(user);
	}
}