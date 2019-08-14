package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class TestDataHelper {
	

	public TestDataHelper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author sds-v.muthu
	 * @param subCategoryName
	 * @return returns shop category path separated by "/"
	 * @throws IOException
	 */
	public String getShopCategoryPath(String subCategoryName) throws IOException {
		                     
        JSONObject jsonObject = getJsonObject("json/shopCategory.json");
        String categoryPath = jsonObject.get(subCategoryName).toString();
		return categoryPath;
	}
	
	
	
	public JSONObject getJsonObject(String path) throws IOException {
		URL resource = ClassLoader.getSystemResource(path);
	    InputStream stream = resource.openStream();
	    String data_all = IOUtils.toString(stream, "UTF-8");                       
        JSONObject jsonObject = new JSONObject(data_all);
        return jsonObject;
	
	}
	

}
