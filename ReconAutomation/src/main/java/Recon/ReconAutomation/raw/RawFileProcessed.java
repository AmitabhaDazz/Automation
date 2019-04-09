package Recon.ReconAutomation.raw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;


public class RawFileProcessed {

	private List<RawFilePojo> rawList;
   Map<String, List<RawFilePojo>> subscriptionidMap = new HashMap<String, List<RawFilePojo>>();
   public RawFileProcessed(List<RawFilePojo> cmpList) {

	this.rawList = rawList;
    ListIterator<RawFilePojo> iterator = (ListIterator<RawFilePojo>) rawList.listIterator();

	String subscriptionID = "";

	String resellerCost = "";

	String posttaxtotalCost = "";

	List<RawFilePojo> localListperSubscriptionID;

	while (iterator.hasNext()) {

	RawFilePojo objj = (RawFilePojo)iterator.next();

	subscriptionID = objj.getSubscriptionid();

	resellerCost = objj.getResellerCost();

	if(subscriptionidMap.containsKey(subscriptionID)) {

	localListperSubscriptionID = subscriptionidMap.get(subscriptionID);

	localListperSubscriptionID.add(objj);

	subscriptionidMap.put(subscriptionID, localListperSubscriptionID);

	}else {

	localListperSubscriptionID = new ArrayList<RawFilePojo>();
    localListperSubscriptionID.add(objj);
    subscriptionidMap.put(subscriptionID, localListperSubscriptionID);
	}
       } 
	      }

public Map<String, List<RawFilePojo>> getSubscriptionIDMap() {
return subscriptionidMap;
}
    public void setSubscriptionIDMap(Map<String, List<RawFilePojo>> subscriptionIDMap) {
     this.subscriptionidMap = subscriptionIDMap;
}
public Map<String, List<RawFilePojo>> getAllSubscriptionIDTotal(){
return subscriptionidMap;
}
	public String getSubscriptionIDTotalResellerCost(String subscriptionID){
    List<RawFilePojo> subTotal = subscriptionidMap.get(subscriptionID);
    ListIterator<RawFilePojo> iterator = (ListIterator<RawFilePojo>) subTotal.listIterator();
    BigDecimal bigDecimalResellerCost = new BigDecimal(0.0);
    bigDecimalResellerCost.setScale(2, BigDecimal.ROUND_UP);
    while (iterator.hasNext()) {

	RawFilePojo obj = (RawFilePojo)iterator.next();

	bigDecimalResellerCost = bigDecimalResellerCost.add(new BigDecimal(obj.getResellerCost().toString()));

	System.out.println(bigDecimalResellerCost);

}

return bigDecimalResellerCost.toString();

	    }

	    }