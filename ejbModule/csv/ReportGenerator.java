package csv;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import beans.CrudService;
import model.Company;

@Stateless(name = "reportGenerator")
@LocalBean
public class ReportGenerator {

	private static final String ABSOLUTE_PATH = "C:\\Users\\aleksandra.petkova\\Downloads\\eclipse-jee-mars-R-win32-x86_64\\eclipse\\workspace\\FruitbasketDemoEJB\\ejbModule\\META-INF\\companies.csv";

	@EJB
	private CrudService<Company> companyService;

	public ReportGenerator() {

	}

	@Schedule(second = "*/15", minute = "*", hour = "*", persistent = false)
	public void generateReport() {
		List<Company> companies = companyService.findWithNamedQuery("Company.findAll");
		CSVExporter.createCSVFile(ABSOLUTE_PATH, companies);
	}
}
