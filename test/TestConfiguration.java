import org.junit.Test;

import com.leirc.LeIRC;

public class TestConfiguration{
	@Test
	public void testConfigurationSaving(){
		LeIRC.cleanup(0);
	}
	
	@Test
	public void testConfigurationLoading(){
		LeIRC.loadConfiguration();
	}
}