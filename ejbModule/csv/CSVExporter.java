package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Company;

public class CSVExporter {

	private static final String COMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = System.lineSeparator();

	private static final String FILE_HEADER = "id,name,address,email,phone";

	public static void createCSVFile(String fileName, List<Company> companies) {
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);
			for (Company company : companies) {
				fileWriter.append(String.valueOf(company.getCompanyId()));
				fileWriter.append(COMA_DELIMITER);
				fileWriter.append(company.getCompanyName());
				fileWriter.append(COMA_DELIMITER);
				fileWriter.append(company.getCompanyAddress());
				fileWriter.append(COMA_DELIMITER);
				fileWriter.append(company.getCompanyEmail());
				fileWriter.append(COMA_DELIMITER);
				fileWriter.append(company.getCompanyPhone());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
		} catch (IOException e) {
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
