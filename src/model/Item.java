package model;

import com.ebay.services.finding.Amount;


public class Item {

    private final String title;
    private final String url;
    private final String condition;
    private final Amount buyItNowPrice;
    

    public Item(String title, String url, String condition, Amount buyItNowPrice) {
        
        this.title = title;
        this.url = url;
        this.condition = condition;
        this.buyItNowPrice = buyItNowPrice;
    }

    public Amount getPrice() {
    	return this.buyItNowPrice;
    }
    
    public String getContent() {
        return title;
    }

	public String getURL() {
		// TODO Auto-generated method stub
		return url;
	}
    
	public String getCondition() {
		return condition;
	}
	
}