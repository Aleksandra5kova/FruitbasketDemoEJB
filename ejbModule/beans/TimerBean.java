package beans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import configuration.Config;
import report.CompanyReportGenerator;
import report.ReportConstants;
import utils.CommonUtils;
import utils.CronTabConstants;
import utils.QueryContraints;
import utils.TimerContraints;

@Singleton
@Startup
public class TimerBean implements TimerBeanLocal {

	private Config config = Config.getInstance();

	@Resource
	private TimerService timerService;

	@EJB
	private CrudServiceLocal<model.Timer> timerCrudService;

	@EJB
	private CompanyReportGenerator reportGenerator;

	public TimerBean() {

	}

	@PostConstruct
	public void createTimers() {
		createCSVReportTimer();
	}

	@PreDestroy
	public void deleteTimers() {
		deleteCSVReportTimer();
	}

	@Timeout
	public void timeOutHandler(Timer timer) {
		String timerUniqueName = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_UNIQUENAME);
		if (timer.getInfo().equals(timerUniqueName)) {
			String nextTimeout = String.valueOf(timer.getNextTimeout().getTime());
			String prefix = nextTimeout + ReportConstants.SEPARATOR + timer.hashCode() + ReportConstants.SEPARATOR;
			reportGenerator.generateCompanyReport(prefix);
		}
	}

	@Override
	public void createCSVReportTimer() {
		String timerUniqueName = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_UNIQUENAME);
		String timerInfo = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_INFO);
		String timerExpression = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_EXPRESSION);

		model.Timer timer = findCSVReportTimerByUniqueName();

		if (timer == null) {
			Map<String, String> date = CommonUtils.extractCrontabToMap(config, timerExpression);

			ScheduleExpression scheduleExpression = new ScheduleExpression();
			scheduleExpression.minute(date.get(CronTabConstants.MINUTES)).hour(date.get(CronTabConstants.HOUR))
					.dayOfMonth(date.get(CronTabConstants.DAY_OF_MONTH)).month(date.get(CronTabConstants.MONTH))
					.dayOfWeek(date.get(CronTabConstants.DAY_OF_WEEK)).second(date.get(CronTabConstants.SECOND));

			timerService.createCalendarTimer(scheduleExpression, new TimerConfig(timerUniqueName, true));

			model.Timer timer1 = new model.Timer();
			timer1.setTimerUniqueName(timerUniqueName);
			timer1.setTimerInfo(timerInfo);
			timer1.setTimerExpression(timerExpression);
			timerCrudService.create(timer1);
		}
	}

	@Override
	public void deleteCSVReportTimer() {
		model.Timer timer = findCSVReportTimerByUniqueName();
		timerCrudService.delete(model.Timer.class, timer.getTimerId());
	}

	private model.Timer findCSVReportTimerByUniqueName() {
		String timerUniqueName = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_UNIQUENAME);

		String namedQuery = config.getProperty(QueryContraints.NAMEDQUERY_TIMER_FINDBYUNIQUENAME);
		String uniqueNameKey = config.getProperty(QueryContraints.NAMEDQUERY_PARAMETER_TIMER_UNIQUENAME);

		Map<String, String> parameters = new HashMap<>();
		parameters.put(uniqueNameKey, timerUniqueName);
		return timerCrudService.findWithNamedQuery(namedQuery, parameters);
	}

}