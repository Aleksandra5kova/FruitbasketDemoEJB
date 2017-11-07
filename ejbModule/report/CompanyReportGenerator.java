package report;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.CrudServiceLocal;
import configuration.Config;
import csv.CompanyCSVExporter;
import model.Company;
import report.ReportConstants;

@Stateless(name = "reportGenerator")
@LocalBean
public class CompanyReportGenerator {

	private Config config = Config.getInstance();

	@EJB
	private CrudServiceLocal<Company> companyService;

	public CompanyReportGenerator() {

	}

	public void generateCompanyReport() {
		List<Company> companies = companyService.findWithNamedQuery("Company.findAll");
		String companyPath = config.getProperty(ReportConstants.COMPANY_PATH);
		String companyHeader = config.getProperty(ReportConstants.COMPANY_HEADER);

		CompanyCSVExporter.createCSVFile(companyHeader, companyPath, companies);
	}
}
