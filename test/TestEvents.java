import org.junit.BeforeClass;
import org.junit.Test;

import com.leirc.api.event.EventHandler;
import com.leirc.api.rsrc.Resources;
import com.leirc.event.TestEvent;

public class TestEvents{
	@BeforeClass
	public static void checkDirs(){
		Resources.checkDirs();
	}
	
	@Test
	public void testEventPosting(){
		EventHandler.postEvent(new TestEvent(), true);
	}
}