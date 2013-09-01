import java.io.File;

import org.junit.Test;

import com.leirc.api.LeIRCApi;
import com.leirc.api.exception.JsonReadException;
import com.leirc.api.exception.JsonWriteException;
import com.leirc.api.plugin.PluginManifest;
import com.leirc.api.rsrc.Resources;

public class TestPlugins {
	@Test
	public void testPluginManifestWriting() throws JsonWriteException{
		PluginManifest manifest = new PluginManifest();
		manifest.setPluginName("test");
		manifest.writeData(LeIRCApi.gson, Resources.PLUGINS);
	}
	
	@Test
	public void testPluginManifestReading() throws JsonReadException{
		PluginManifest manifest = PluginManifest.loadData(LeIRCApi.gson, new File(Resources.PLUGINS, "test.xml"));
		System.out.println(manifest.getName());
	}
}