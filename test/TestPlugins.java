import java.io.File;

import org.junit.Test;

import com.leirc.api.LeIRCApi;
import com.leirc.api.exception.XMLReadException;
import com.leirc.api.exception.XMLWriteException;
import com.leirc.api.plugin.PluginManifest;
import com.leirc.api.rsrc.Resources;

public class TestPlugins {
	@Test
	public void testPluginManifestWriting() throws XMLWriteException{
		PluginManifest manifest = new PluginManifest();
		manifest.setPluginName("test");
		manifest.writeData(LeIRCApi.stream, Resources.DESKTOP);
	}
	
	@Test
	public void testPluginManifestReading() throws XMLReadException{
		PluginManifest manifest = PluginManifest.loadData(LeIRCApi.stream, new File(Resources.DESKTOP, "test.xml"));
		System.out.println(manifest.getName());
	}
}