package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "Timer.findAll", query = "SELECT t FROM Timer t"),
		@NamedQuery(name = "Timer.findByUniqueName", query = "SELECT t FROM Timer t WHERE t.timerUniqueName = :timerUniqueName") })
@NamedNativeQuery(name = "Timer.lock", query = "LOCK TABLES timer WRITE")
public class Timer implements Serializable {

	private static final long serialVersionUID = 1L;

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