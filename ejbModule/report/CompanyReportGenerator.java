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

	public void generateCompanyReport(String prefix) {
		
		List<Company> companies = companyService.findWithNamedQuery("Company.findAll");
		String companyAbsolutePath = config.getProperty(ReportConstants.COMPANY_PATH);
		String companyDocumentName = prefix + config.getProperty(ReportConstants.COMPANY_DOCUMENT_NAME);
		String companyFullName = companyAbsolutePath + companyDocumentName;
		String companyHeader = config.getProperty(ReportConstants.COMPANY_HEADER);

		CompanyCSVExporter.createCSVFile(companyHeader, companyFullName, companies);
	}
}
