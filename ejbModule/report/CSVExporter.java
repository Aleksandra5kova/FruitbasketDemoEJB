package report;

import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;

@Stateless
@LocalBean
public class CSVExporter<T> {

	private static final String COMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = System.lineSeparator();

	public CSVExporter() {

	}

	public void createCSVFile(Class<T> type, List<T> data, String fileName) {
		FileWriter fileWriter = null;
		boolean headerRow = false;
		try {
			fileWriter = new FileWriter(fileName);

			for (T t : data) {
				if (headerRow == false) {
					Field[] fields = t.getClass().getDeclaredFields();
					for (Field field : fields) {
						if (Modifier.isPrivate(field.getModifiers())) {
							Column column = field.getAnnotation(Column.class);
							fileWriter.append(column.name());
							fileWriter.append(COMA_DELIMITER);
						}
					}
					headerRow = true;
					fileWriter.append(NEW_LINE_SEPARATOR);
				}

				Field[] fields = t.getClass().getDeclaredFields();
				for (Field field : fields) {
					if (Modifier.isPrivate(field.getModifiers())) {
						Object value = new PropertyDescriptor(field.getName(), type).getReadMethod().invoke(t);
						fileWriter.append(value.toString());
						fileWriter.append(COMA_DELIMITER);
					}
				}
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
