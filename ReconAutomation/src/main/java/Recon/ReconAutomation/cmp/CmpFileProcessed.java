//cmpfileprocessed

package Recon.ReconAutomation.cmp;

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

public class CmpFileProcessed {
	
	private List<CmpFilePojo> cmpList;
	Map<String, List<CmpFilePojo>> subscriptionIDMap = new HashMap<String, List<CmpFilePojo>>();
	Map<String, List<CmpFilePojo>> resellerCompanyNameMap = new HashMap<String, List<CmpFilePojo>>();
	
	public CmpFileProcessed(List<CmpFilePojo> cmpList) {
		this.cmpList = cmpList;
	}
	
	public void createMapBySubscriptionID(){
		ListIterator<CmpFilePojo> iterator = cmpList.listIterator();
		String subscriptionID = "";
		List<CmpFilePojo> localListperSubscritionID;
		while (iterator.hasNext()) {
			CmpFilePojo obj = (CmpFilePojo)iterator.next();
			subscriptionID = obj.getSubscriptionId();
			
			if(subscriptionIDMap.containsKey(subscriptionID)) {
				localListperSubscritionID = subscriptionIDMap.get(subscriptionID);
				localListperSubscritionID.add(obj);
				subscriptionIDMap.put(subscriptionID, localListperSubscritionID);
				
			}else {
				localListperSubscritionID = new ArrayList<CmpFilePojo>();
				localListperSubscritionID.add(obj);
				subscriptionIDMap.put(subscriptionID, localListperSubscritionID);
			}
		}
	}
	
	public void createMapByResellerCompanyName(){
		ListIterator<CmpFilePojo> iterator = cmpList.listIterator();
		String resellerCompanyName = "";
		List<CmpFilePojo> localListperResellerCompanyName;
		while (iterator.hasNext()) {
			CmpFilePojo obj = (CmpFilePojo)iterator.next();
			resellerCompanyName = obj.getResellerCompanyName();
			
			if(resellerCompanyNameMap.containsKey(resellerCompanyName)) {
				localListperResellerCompanyName = resellerCompanyNameMap.get(resellerCompanyName);
				localListperResellerCompanyName.add(obj);
				resellerCompanyNameMap.put(resellerCompanyName, localListperResellerCompanyName);
				
			}else {
				localListperResellerCompanyName = new ArrayList<CmpFilePojo>();
				localListperResellerCompanyName.add(obj);
				resellerCompanyNameMap.put(resellerCompanyName, localListperResellerCompanyName);
			}
		}
	}

	public Map<String, List<CmpFilePojo>> getResellerCompanyNameMap() {
		return resellerCompanyNameMap;
	}

	public void setResellerCompanyNameMap(Map<String, List<CmpFilePojo>> resellerCompanyNameMap) {
		this.resellerCompanyNameMap = resellerCompanyNameMap;
	}

	public Map<String, List<CmpFilePojo>> getSubscriptionIDMap() {
		return subscriptionIDMap;
	}

	public void setSubscriptionIDMap(Map<String, List<CmpFilePojo>> subscriptionIDMap) {
		this.subscriptionIDMap = subscriptionIDMap;
	}

	
	public String getResellerCompanyNameBySubscriptionID(String subscriptionID){
		List<CmpFilePojo> subTotal = subscriptionIDMap.get(subscriptionID);
		ListIterator<CmpFilePojo> iterator = subTotal.listIterator();
		String resellecCompanyName= null;
		while (iterator.hasNext()) {
			CmpFilePojo obj = (CmpFilePojo)iterator.next();
			resellecCompanyName = obj.getResellerCompanyName().toString();
			return resellecCompanyName;
		}
		return null;
	}
	
	public String getResellerCostBySubscriptionID(String subscriptionID){
		List<CmpFilePojo> subTotal = subscriptionIDMap.get(subscriptionID);
		ListIterator<CmpFilePojo> iterator = subTotal.listIterator();
		BigDecimal bigDecimalResellerCost = new BigDecimal(0.0);
		bigDecimalResellerCost.setScale(3, BigDecimal.ROUND_CEILING);
		while (iterator.hasNext()) {
			CmpFilePojo obj = (CmpFilePojo)iterator.next();
			String resellecrCost = obj.getResellerCost().toString();
			BigDecimal resellerCosrBD = new BigDecimal(resellecrCost);
			resellerCosrBD.setScale(3, BigDecimal.ROUND_CEILING);
			bigDecimalResellerCost = bigDecimalResellerCost.add(new BigDecimal(format(obj.getResellerCost().toString(), resellerCosrBD.scale() > 0 ? resellerCosrBD.precision() : resellerCosrBD.scale())));
		}
		return bigDecimalResellerCost.toString();
	}
	
	public String getPartnerCostBySubscriptionID(String subscriptionID){
		List<CmpFilePojo> subTotal = subscriptionIDMap.get(subscriptionID);
		ListIterator<CmpFilePojo> iterator = subTotal.listIterator();
		BigDecimal bigDecimalPartnerCost = new BigDecimal(0.0);
		bigDecimalPartnerCost.setScale(3, BigDecimal.ROUND_CEILING);
		while (iterator.hasNext()) {
			CmpFilePojo obj = (CmpFilePojo)iterator.next();
			String partnerCost = obj.getPartnerCost().toString();
			BigDecimal partnerCostBD = new BigDecimal(partnerCost);
			partnerCostBD.setScale(3, BigDecimal.ROUND_CEILING);
			bigDecimalPartnerCost = bigDecimalPartnerCost.add(new BigDecimal(format(obj.getPartnerCost().toString(), partnerCostBD.scale() > 0 ? partnerCostBD.precision() : partnerCostBD.scale())));
		}
		return bigDecimalPartnerCost.toString();
	}
	
	/*
	 * Partner Cost Total from CMP File
	 */
	public String getCmpIngramMicroCostByResellerCompName(String resellerCompanyName){
		List<CmpFilePojo> subTotal = resellerCompanyNameMap.get(resellerCompanyName);
		ListIterator<CmpFilePojo> iterator = subTotal.listIterator();
		BigDecimal bigDecimalResellerCost = new BigDecimal(0.0);
		bigDecimalResellerCost.setScale(3, BigDecimal.ROUND_CEILING);
		while (iterator.hasNext()) {
			CmpFilePojo obj = (CmpFilePojo)iterator.next();
			String resellecrCost = obj.getPartnerCost().toString();
			BigDecimal resellerCosrBD = new BigDecimal(resellecrCost);
			resellerCosrBD.setScale(3, BigDecimal.ROUND_CEILING);
			bigDecimalResellerCost = bigDecimalResellerCost.add(new BigDecimal(format(obj.getPartnerCost().toString(), resellerCosrBD.scale() > 0 ? resellerCosrBD.precision() : resellerCosrBD.scale())));
		}
		return bigDecimalResellerCost.toString();
	}
	
	/*
	 * Reseller cost from CMP file
	 */
	public String getCmpResellerCostByResellerCompName(String resellerCompanyName){
		List<CmpFilePojo> subTotal = resellerCompanyNameMap.get(resellerCompanyName);
		ListIterator<CmpFilePojo> iterator = subTotal.listIterator();
		BigDecimal bigDecimalResellerCost = new BigDecimal(0.0);
		bigDecimalResellerCost.setScale(3, BigDecimal.ROUND_CEILING);
		while (iterator.hasNext()) {
			CmpFilePojo obj = (CmpFilePojo)iterator.next();
			String resellecrCost = obj.getResellerCost().toString();
			BigDecimal resellerCosrBD = new BigDecimal(resellecrCost);
			resellerCosrBD.setScale(3, BigDecimal.ROUND_CEILING);
			bigDecimalResellerCost = bigDecimalResellerCost.add(new BigDecimal(format(obj.getResellerCost().toString(), resellerCosrBD.scale() > 0 ? resellerCosrBD.precision() : resellerCosrBD.scale())));
		}
		return bigDecimalResellerCost.toString();
	}
	
	
	public Map<String, List<CmpFilePojo>> getAllResellerCompanyNameTotal(){
		return null;
	}
	
	public List<CmpFilePojo> getResellerCompanyNameTotal(String resellerCompanyName){
		return null;
	}
	
	private  String format(String number, int scale) {
	    BigDecimal value = new BigDecimal(number);
	    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
	    BigDecimal positive = new BigDecimal(1);// scale is zero
	    positive.setScale(0);// unnecessary
	    BigDecimal negative = new BigDecimal(-1);// scale is zero
	    negative.setScale(0);// unnecessary
	    if (value.compareTo(positive) == 1 || value.compareTo(negative) == -1) {
	        symbols.setExponentSeparator("e+");
	    } else {
	        symbols.setExponentSeparator("e");
	    }
	    DecimalFormat formatter = new DecimalFormat("0.0E0", symbols);
	    formatter.setRoundingMode(RoundingMode.HALF_UP);
	    formatter.setMinimumFractionDigits(scale);
	    return formatter.format(value);
	}
	
}
