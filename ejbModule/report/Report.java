package report;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import utils.ReportUtils;

@Stateless(name = "reportGenerator")
@LocalBean
public class Report<T> {

	@EJB
	private CSVExporter<T> CSVExporter;

	public Report() {

	}

	public void generateReport(Class<T> type, List<T> data, long namePrefix) {
		String fileName = ReportUtils.getFileName(type, String.valueOf(namePrefix));
		CSVExporter.createCSVFile(type, data, fileName);
	}
}
