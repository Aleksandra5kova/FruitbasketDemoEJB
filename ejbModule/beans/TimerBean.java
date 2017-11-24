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
import utils.TimerUtils;

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
		createCompanyFindAllTimer();
	}

	@PreDestroy
	public void deleteTimers() {
		deleteCompanyFindAllTimer();
	}

	@SuppressWarnings("unchecked")
	@Timeout
	public void timeOutHandler(javax.ejb.Timer timer) {
		if (timer.getInfo().equals(TimerContraints.COMPANY_FIND_ALL)) {
			List<Company> data = companyService.findAll(Company.class);
			reportGenerator.generateReport(Company.class, data, TimerContraints.COMPANY_FIND_ALL,
					timer.getNextTimeout().getTime());
		}
	}

	@Override
	public void createCompanyFindAllTimer() {
		String timerName = TimerContraints.COMPANY_FIND_ALL;
		String timerInfo = TimerUtils.getTimerInfo(timerName);
		String timerExpression = TimerUtils.getTimerExpression(timerName);

		Timer timer = new Timer();
		timer.setTimerUniqueName(timerName);
		timer.setTimerInfo(timerInfo);
		timer.setTimerExpression(timerExpression);

		timerService.save(timer);
		Map<String, String> date = CommonUtils.extractCrontabToMap(timerExpression);
		ScheduleExpression scheduleExpression = new ScheduleExpression();
		scheduleExpression.minute(date.get(CrontabConstants.MINUTES)).hour(date.get(CrontabConstants.HOUR))
				.dayOfMonth(date.get(CrontabConstants.DAY_OF_MONTH)).month(date.get(CrontabConstants.MONTH))
				.dayOfWeek(date.get(CrontabConstants.DAY_OF_WEEK)).second(date.get(CrontabConstants.SECOND));
		ejbTimerService.createCalendarTimer(scheduleExpression, new TimerConfig(timerName, true));
	}

	@Override
	public void deleteCompanyFindAllTimer() {
		String parameterName = config.getProperty(QueryContraints.TIMER_UNIQUENAME);
		String timerName = TimerContraints.COMPANY_FIND_ALL;
		Timer timer = findTimerByUniqueName(parameterName, timerName);
		timerService.delete(Timer.class, timer.getTimerId());
	}

	private Timer findTimerByUniqueName(String name, String value) {
		return timerService.findByField(Timer.class, name, value);
	}

}