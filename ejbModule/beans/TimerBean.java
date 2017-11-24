package beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import configuration.Config;
import constraints.CrontabConstants;
import constraints.QueryContraints;
import constraints.TimerContraints;
import model.Company;
import model.Timer;
import report.Report;
import utils.CommonUtils;

@Singleton
@Startup
public class TimerBean implements TimerBeanLocal {

	private Config config = Config.getInstance();

	@Resource
	private TimerService ejbTimerService;

	@EJB
	private CrudServiceLocal<Timer> timerService;

	@EJB
	private CrudServiceLocal<Company> companyService;
	
	@EJB
	private CrudDaoLocal<Timer> crudDao;

	@EJB
	private Report reportGenerator;

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

	@SuppressWarnings("unchecked")
	@Timeout
	public void timeOutHandler(javax.ejb.Timer timer) {
		String timerUniqueName = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_UNIQUENAME);
		if (timer.getInfo().equals(timerUniqueName)) {
			List<Company> data = companyService.findAll(Company.class);
			reportGenerator.generateReport(Company.class, data, timer.getNextTimeout().getTime());
		}
	}

	@Override
	public void createCSVReportTimer() {
		String timerUniqueName = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_UNIQUENAME);
		String timerInfo = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_INFO);
		String timerExpression = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_EXPRESSION);

		Timer timer = new Timer();
		timer.setTimerUniqueName(timerUniqueName);
		timer.setTimerInfo(timerInfo);
		timer.setTimerExpression(timerExpression);

		timerService.save(timer);
		Map<String, String> date = CommonUtils.extractCrontabToMap(config, timerExpression);
		ScheduleExpression scheduleExpression = new ScheduleExpression();
		scheduleExpression.minute(date.get(CrontabConstants.MINUTES)).hour(date.get(CrontabConstants.HOUR))
				.dayOfMonth(date.get(CrontabConstants.DAY_OF_MONTH)).month(date.get(CrontabConstants.MONTH))
				.dayOfWeek(date.get(CrontabConstants.DAY_OF_WEEK)).second(date.get(CrontabConstants.SECOND));
		ejbTimerService.createCalendarTimer(scheduleExpression, new TimerConfig(timerUniqueName, true));

	}

	@Override
	public void deleteCSVReportTimer() {
		Timer timer = findCSVReportTimerByUniqueName();
		timerService.delete(Timer.class, timer.getTimerId());
	}

	private Timer findCSVReportTimerByUniqueName() {
		String timerUniqueName = config.getProperty(TimerContraints.TIMER_COMPANY_CSVREPORT_UNIQUENAME);
		String uniqueNameKey = config.getProperty(QueryContraints.NAMEDQUERY_PARAMETER_TIMER_UNIQUENAME);
		return timerService.findByField(Timer.class, uniqueNameKey, timerUniqueName);
	}

}