package Recon.ReconAutomation.raw;

public class RawFilePojo {

	 String subscriptionid;
	 String posttaxtotal;
	 String resellerCost;
	 
	 public String getResellerCost() {
		return resellerCost;
	}

	public void setResellerCost(String resellerCost) {
		this.resellerCost = resellerCost;
	}

	public RawFilePojo() {
		 
	 }
	 
	 public RawFilePojo(String subscriptionid, String posttaxtotal) {
		 this.subscriptionid = subscriptionid;
		 this.posttaxtotal = posttaxtotal;
	 }
	 
	public String getSubscriptionid() {
		return subscriptionid;
	}
	public void setSubscriptionid(String subscriptionid) {
		this.subscriptionid = subscriptionid;
	}
	public String getPosttaxtotal() {
		return posttaxtotal;
	}
	public void setPosttaxtotal(String posttaxtotal) {
		this.posttaxtotal = posttaxtotal;
	}
	

}
