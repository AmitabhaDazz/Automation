//cmpfilepojo

package Recon.ReconAutomation.cmp;

public class CmpFilePojo {
	
	String resellerCompanyName;
	String subscriptionId;
	String resellerCost;
	String partnerCost;
	
	public CmpFilePojo(){
		
	}
	
	
	public CmpFilePojo(String resellerCompanyName,String subscriptionId,String resellerCost,String partnerCost) {
		this.resellerCompanyName = resellerCompanyName;
		this.subscriptionId = subscriptionId;
		this.resellerCost = resellerCost;
		this.partnerCost = partnerCost;
	}

	public String getResellerCompanyName() {
		return resellerCompanyName;
	}

	public void setResellerCompanyName(String resellerCompanyName) {
		this.resellerCompanyName = resellerCompanyName;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getResellerCost() {
		return resellerCost;
	}

	public void setResellerCost(String resellerCost) {
		this.resellerCost = resellerCost;
	}

	public String getPartnerCost() {
		return partnerCost;
	}

	public void setPartnerCost(String partnerCost) {
		this.partnerCost = partnerCost;
	}
	
	
	

}
