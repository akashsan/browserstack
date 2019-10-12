import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {
	public static void main(String[] args) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();

		JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("c:\\projects\\drive_map.json");
		Set<String> keys = jsonObject.keySet();

		Iterator<String> itr = keys.iterator();

		// traversing over HashSet
		System.out.println("Traversing over Set using Iterator");
		while (itr.hasNext()) {
			String key = (String) itr.next();
			String value = (String) jsonObject.get(key);
			map.put(key, value);
		}

		System.out.println(map);

		try {

			FileInputStream fin = new FileInputStream("c:\\projects\\drive_input.txt");

			int i = 0;
			String s = "";

			while ((i = fin.read()) != -1) {

				String c = String.valueOf((char) i);

				if (c.equals("&")) {
					int k = 0;
					String s1 = "";
					k = fin.read();
					while (k != -1 && k != '&') {
						s1 += String.valueOf((char) k);
						k = fin.read();
					}
					if (map.get(s1) != null) {
						s = s + map.get(s1);
					}
				} else {
					s = s + String.valueOf((char) i);
				}

			}

			FileOutputStream fout = new FileOutputStream("c:\\projects\\drive_output.html");
			byte[] b = s.getBytes();

			fout.write(b);
			fout.close();

			System.out.println("Done reading and writing!!");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {
		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}

}
