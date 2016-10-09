package de.desktop.application.fx.model.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import de.desktop.application.fx.model.ModelObject;

/**
 * 
 * @author Max.Auch
 *
 * @param <M>
 */
public class ModelMapper<M> {

	private final Class<M> type;

	public List<M> toToModel(Object to) {

		Field[] fields = to.getClass().getDeclaredFields();
		List<M> modelList = new ArrayList<M>();
		Object value = null;

		for (Field f : fields) {
			try {
				value = f.get(to);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

			if (value != null && f.getName() != null) {
				M modelinstance = createModel(getType(), f.getName(), (String) value);
				modelList.add(modelinstance);
			}
		}

		return modelList;
	}

	public M modelToTo(List<ModelObject> modelList) {

		M transportObject = null;
		try {
			transportObject = (M) getType().getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		for (ModelObject mo : modelList) {
			try {
				Field f = transportObject.getClass().getDeclaredField(mo.getProperty().get());
				try {
					f.set(transportObject, mo.getWert().get());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

		}

		return transportObject;
	}

	private M createModel(Class<M> clazz, String a, String b) {
		Class<?>[] cArg = new Class[2];
		cArg[0] = String.class;
		cArg[1] = String.class;

		try {
			return clazz.getDeclaredConstructor(cArg).newInstance(a, b);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ModelMapper(Class<M> type) {
		this.type = type;
	}

	public Class<M> getType() {
		return this.type;
	}

}
