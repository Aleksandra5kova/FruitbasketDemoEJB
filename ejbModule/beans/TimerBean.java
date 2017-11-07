package beans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import configuration.Config;
import report.CompanyReportGenerator;
import utils.CommonUtils;
import utils.CronTabConstants;
import utils.TimerContraints;

@Singleton
@LocalBean
@Startup
public class TimerBean implements TimerBeanLocal {

	private Config config = Config.getInstance();

	@Resource
	private TimerService timerService;

	@EJB
	private CompanyReportGenerator reportGenerator;

	public TimerBean() {

	}

	@PostConstruct
	public void createTimers() {
		createCSVReportTimer();
	}

	@Timeout
	public void timeOutHandler(Timer timer) {
		if (timer.getInfo().equals(TimerContraints.COMPANY_INFO)) {
			reportGenerator.generateCompanyReport();
			System.out.println(timer.getNextTimeout());
		}
	}

	@Override
	public void createCSVReportTimer() {
		Map<String, String> date = CommonUtils.extractCrontabToMap(config, TimerContraints.COMPANY_EXPRESSION);

		ScheduleExpression scheduleExpression = new ScheduleExpression();
		scheduleExpression.minute(date.get(CronTabConstants.MINUTES)).hour(date.get(CronTabConstants.HOUR))
				.dayOfMonth(date.get(CronTabConstants.DAY_OF_MONTH)).month(date.get(CronTabConstants.MONTH))
				.dayOfWeek(date.get(CronTabConstants.DAY_OF_WEEK));

		timerService.createCalendarTimer(scheduleExpression, new TimerConfig(TimerContraints.COMPANY_INFO, true));
	}

}
