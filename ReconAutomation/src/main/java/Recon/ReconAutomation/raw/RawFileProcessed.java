package Recon.ReconAutomation.raw;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import Recon.ReconAutomation.cmp.CmpFilePojo;

public class RawFileProcessed {

	private List<RawFilePojo> RawList;
	Map<String, List<RawFilePojo>> subscriptionIDMap = new HashMap<String, List<RawFilePojo>>();
	
	public RawFileProcessed(List<RawFilePojo> RawList) {
		this.RawList = RawList;
	}
	

	public void createMapBySubscriptionID(){
		ListIterator<RawFilePojo> iterator = RawList.listIterator();
		String subscriptionID = "";
		List<RawFilePojo> localListperSubscritionID;
		while (iterator.hasNext()) {
			RawFilePojo obj = (RawFilePojo)iterator.next();
			subscriptionID = obj.getSubscriptionid();

			if(subscriptionIDMap.containsKey(subscriptionID)) {
				localListperSubscritionID = subscriptionIDMap.get(subscriptionID);
				localListperSubscritionID.add(obj);
				subscriptionIDMap.put(subscriptionID, localListperSubscritionID);

			}else {
				localListperSubscritionID = new ArrayList<RawFilePojo>();
				localListperSubscritionID.add(obj);
				subscriptionIDMap.put(subscriptionID, localListperSubscritionID);
			}
		}
	}


	public Map<String, List<RawFilePojo>> getSubscriptionIDMap() {
		return subscriptionIDMap;
	}

	public void setSubscriptionIDMap(Map<String, List<RawFilePojo>> subscriptionIDMap) {
		this.subscriptionIDMap = subscriptionIDMap;
	}




	
}