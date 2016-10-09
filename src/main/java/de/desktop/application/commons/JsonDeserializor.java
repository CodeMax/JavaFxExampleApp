package de.desktop.application.commons;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Max.Auch
 *
 * @param <T>
 */
public class JsonDeserializor<T> {

	private static final Logger LOG = LoggerFactory.getLogger(JsonDeserializor.class);
	private final Class<T> type;

	public JsonDeserializor(Class<T> type) {
		this.type = type;
	}

	public Class<T> getType() {
		return this.type;
	}

	public T deserializeJsonToPojo(String url) {
		String content = null;
		T to = null;
		try {
			String file = getFileOfURL(url);
			content = file;
			Gson gson = new Gson();
			Type collectionType = new TypeToken<LinkedHashMap<String, Object>>() {
			}.getType();
			LinkedHashMap<String, Object> jsonContent = gson.fromJson(content, collectionType);
			to = (T) createTo(getType(), jsonContent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return to;
	}
	
	private T createTo(Class<T> clazz, LinkedHashMap<String, Object> jsonContent) {
		Class<?>[] cArg = new Class[1];
		cArg[0] = LinkedHashMap.class;
		
		try {
			return clazz.getDeclaredConstructor(cArg).newInstance(jsonContent);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;

	}

	public T deserializeJsonToObject(final Class<T> objectClass, String url) {
		String content = null;
		try {
			String file = getFileOfURL(url);
			content = file;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, objectClass); // type
		} catch (JsonGenerationException e) {
			LOG.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}

		return null;
	}

	private String getFileOfURL(String url) throws FileNotFoundException {

		String fileString = null;
		InputStream in = getClass().getClassLoader().getResourceAsStream(url);
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			fileString = sb.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return fileString;
	}
}