package OutputAutomation;

public class OutputPojoforReseller {
	String resellerCompanyName;
	//From CMP File
	String cmpIngramMicroCost;
	//From CMP File
	String cmpResellerCost;
	
	
	String partnerCenterIngramCost;
	String parnerCenterResellerCost;
	String iMcostVarience;
	String iMcostVariencePercentage;
	String partnerCenterCostVarience;
	String partnerCenterCostVariencePercentage;
	public OutputPojoforReseller() {
		
	}
	public OutputPojoforReseller(String resellerCompanyName,String cmpIngramMicroCost, String cmpResellerCost){
		this.resellerCompanyName=resellerCompanyName;
		this.cmpIngramMicroCost=cmpIngramMicroCost;
		this.cmpResellerCost=cmpResellerCost;
	}
	public String getResellerCompanyName() {
		return resellerCompanyName;
	}
	public void setResellerCompanyName(String resellerCompanyName) {
		this.resellerCompanyName = resellerCompanyName;
	}
	public String getCmpIngramMicroCost() {
		return cmpIngramMicroCost;
	}
	public void setCmpIngramMicroCost(String cmpIngramMicroCost) {
		this.cmpIngramMicroCost = cmpIngramMicroCost;
	}
	public String getCmpResellerCost() {
		return cmpResellerCost;
	}
	public void setCmpResellerCost(String cmpResellerCost) {
		this.cmpResellerCost = cmpResellerCost;
	}
	public String getPartnerCenterIngramCost() {
		return partnerCenterIngramCost;
	}
	public void setPartnerCenterIngramCost(String partnerCenterIngramCost) {
		this.partnerCenterIngramCost = partnerCenterIngramCost;
	}
	public String getParnerCenterResellerCost() {
		return parnerCenterResellerCost;
	}
	public void setParnerCenterResellerCost(String parnerCenterResellerCost) {
		this.parnerCenterResellerCost = parnerCenterResellerCost;
	}
	public String getiMcostVarience() {
		return iMcostVarience;
	}
	public void setiMcostVarience(String iMcostVarience) {
		this.iMcostVarience = iMcostVarience;
	}
	public String getiMcostVariencePercentage() {
		return iMcostVariencePercentage;
	}
	public void setiMcostVariencePercentage(String iMcostVariencePercentage) {
		this.iMcostVariencePercentage = iMcostVariencePercentage;
	}
	public String getPartnerCenterCostVarience() {
		return partnerCenterCostVarience;
	}
	public void setPartnerCenterCostVarience(String partnerCenterCostVarience) {
		this.partnerCenterCostVarience = partnerCenterCostVarience;
	}
	public String getPartnerCenterCostVariencePercentage() {
		return partnerCenterCostVariencePercentage;
	}
	public void setPartnerCenterCostVariencePercentage(String partnerCenterCostVariencePercentage) {
		this.partnerCenterCostVariencePercentage = partnerCenterCostVariencePercentage;
	}
	

}
