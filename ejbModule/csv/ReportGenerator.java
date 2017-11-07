package csv;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.CrudService;
import configuration.Config;
import model.Company;

@Stateless(name = "reportGenerator")
@LocalBean
public class ReportGenerator {

	private static final String ABSOLUTE_PATH = "absolutePath";
	private static final String FILE_NAME = "fileName";
	private static final String FILE_HEADER = "company.fileHeader";

	private Config config = Config.getInstance();

	@EJB
	private CrudService<Company> companyService;

	public ReportGenerator() {

	}

	public void generateReport() {
		List<Company> companies = companyService.findWithNamedQuery("Company.findAll");
		String filePath = config.getProperty(ABSOLUTE_PATH) + config.getProperty(FILE_NAME);
		String fileHeader = config.getProperty(FILE_HEADER);
		CSVExporter.createCSVFile(fileHeader, filePath, companies);
	}
}
