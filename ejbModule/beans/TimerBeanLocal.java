package beans;

import javax.ejb.Local;

@Local
public interface TimerBeanLocal {
	
	public void createCompanyFindAllTimer();

	public void deleteCompanyFindAllTimer();
}
