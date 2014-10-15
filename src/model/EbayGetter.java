package model;


import java.util.ArrayList;
import java.util.List;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.PaginationInput;
import com.ebay.services.finding.SearchItem;

public class EbayGetter implements ItemGetter{
	private String name;
	
	public EbayGetter(String name){
		this.name = name;
	}
	
	
	@Override
	public List<Item> getItems() {
		// TODO Auto-generated method stub
		// initialize service end-point configuration
		ClientConfig config = new ClientConfig(); 
		config.setApplicationId("Integrit-b441-45bf-8349-da1f0c8a5243");
				
		// create service client
		FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
				
		// create request object
		FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();
				
		// set request parameters
		request.setKeywords(this.name);
		
		PaginationInput pi = new PaginationInput();
		pi.setEntriesPerPage(2);
		request.setPaginationInput(pi);
				
		// call service
		FindItemsByKeywordsResponse result =
				serviceClient.findItemsByKeywords(request);
		
				
		List<SearchItem> items = result.getSearchResult().getItem();
		List<Item> _items = new ArrayList<Item>();
		
		System.out.println("Ebay Called.");
		
		for(SearchItem item : items) {
			Item _item = new Item(item.getTitle(), item.getViewItemURL(), item.getCondition().getConditionDisplayName(),
					item.getListingInfo().getBuyItNowPrice());
			_items.add(_item);
		}
		return _items;
	}

}
