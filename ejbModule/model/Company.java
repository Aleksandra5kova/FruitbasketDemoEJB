package model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Company.FINDALL", query = "SELECT c FROM Company c ORDER BY c.companyId")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Integer companyId;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "company_email")
	private String companyEmail;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_phone")
	private String companyPhone;

	public Company() {

	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyEmail() {
		return this.companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyAddress=" + companyAddress + ", companyEmail="
				+ companyEmail + ", companyName=" + companyName + ", companyPhone=" + companyPhone + "]";
	}

}