package qaframework.custom;

import java.io.InputStream;
import java.util.Properties;

public class PathsProperties {
	
	public Properties Read_FilePath() throws Exception {
		Properties autoitProps = new Properties();
		InputStream is = this.getClass().getResourceAsStream(System.getProperty("path.file", "/PropertyFiles/path.properties"));
		autoitProps.load(is);
		return autoitProps;
}

}
