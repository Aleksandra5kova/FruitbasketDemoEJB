package beans;

import javax.ejb.Local;

@Local
public interface TimerBeanLocal {
	
	public void createCSVReportTimer();

	public void deleteCSVReportTimer();
}
