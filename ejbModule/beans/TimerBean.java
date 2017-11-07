package beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;

import configuration.Config;
import csv.ReportGenerator;

@Singleton
@LocalBean
@Startup
public class TimerBean implements TimerBeanLocal {

	private static final String MILISECONDS = "company.generateReport.miliseconds";

	private Config config = Config.getInstance();

	@Resource
	private SessionContext context;

	@EJB
	private ReportGenerator reportGenerator;

	public TimerBean() {

	}

	@PostConstruct
	public void createTimers() {
		createCSVReportTimer();
	}

	@Timeout
	public void timeOutHandler(Timer timer) {
		if (timer.getInfo().equals("csvReport")) {
			reportGenerator.generateReport();
		}
	}

	@Override
	public void createCSVReportTimer() {
		Integer miliseconds = Integer.parseInt(config.getProperty(MILISECONDS));
		context.getTimerService().createIntervalTimer(new Date(), miliseconds, new TimerConfig("csvReport", true));
	}

}
