package model;

import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "Timer.FINDALL", query = "SELECT t FROM Timer t"),
		@NamedQuery(name = "Timer.FINDBYTIMERUNIQUENAME", query = "SELECT t FROM Timer t WHERE t.timerUniqueName = :timerUniqueName") })
public class Timer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timer_id")
	private Integer timerId;

	@Column(name = "timer_unique_name", unique = true)
	private String timerUniqueName;

	@Column(name = "timer_info")
	private String timerInfo;

	@Column(name = "timer_expression")
	private String timerExpression;

	public Timer() {
	}

	public Integer getTimerId() {
		return this.timerId;
	}

	public void setTimerId(Integer timerId) {
		this.timerId = timerId;
	}

	public String getTimerExpression() {
		return this.timerExpression;
	}

	public void setTimerExpression(String timerExpression) {
		this.timerExpression = timerExpression;
	}

	public String getTimerInfo() {
		return this.timerInfo;
	}

	public void setTimerInfo(String timerInfo) {
		this.timerInfo = timerInfo;
	}

	public String getTimerUniqueName() {
		return this.timerUniqueName;
	}

	public void setTimerUniqueName(String timerUniqueName) {
		this.timerUniqueName = timerUniqueName;
	}

}