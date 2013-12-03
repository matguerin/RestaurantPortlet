package org.esupportail.restaurant.web.springmvc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.esupportail.restaurant.domain.beans.User;
import org.esupportail.restaurant.services.auth.Authenticator;
import org.esupportail.restaurant.web.dao.DatabaseConnector;
import org.esupportail.restaurant.web.flux.RestaurantFlux;
import org.esupportail.restaurant.web.json.Restaurant;
import org.esupportail.restaurant.web.json.RestaurantFeedRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

@Controller
@RequestMapping("EDIT")
public class EditController extends AbstractExceptionController {

	@Autowired
	private Authenticator authenticator;
	@Autowired
	private DatabaseConnector dc;
	@Autowired
	private RestaurantFlux flux;
	private RestaurantFeedRoot restaurants;
	
	  @RequestMapping
	    public ModelAndView renderEditView(RenderRequest request, RenderResponse response) throws Exception {
	        
	    	ModelMap model = new ModelMap();
	    	
	    	User user = authenticator.getUser();
	    	model.put("user", user);
	    	
	    	try {
	    		Set<String> areaList = new HashSet<String>();
	    		for(Restaurant r : flux.getFlux().getRestaurants()) {
	    			areaList.add(r.getArea());
	    		}
	    		
	    		model.put("areas", areaList);
	    		
	    		String userArea = new String();
	    		try {
	    			ResultSet results = dc.executeQuery("SELECT AREANAME FROM USERAREA WHERE USERNAME='"+user.getLogin()+"';");
	    			results.next();
	    			userArea = results.getString("AREANAME");
	    		} catch (SQLException e) {
	    			// here we are if the user doesn't already have a specific area setting.
	    			ResultSet results = dc.executeQuery("SELECT AREANAME FROM PATHFLUX");
	    			results.next();
	    			try {
	    				userArea = results.getString("AREANAME");
	    			} catch (SQLException e2) {
	    				// No default area for all user, admin must configure the portlet. 
	    				// No need to throw an exception
	    			}
	    			
	    		}
	    		model.put("defaultArea", userArea);
	    		
	        	Set<String> favResults = new HashSet<String>();
	        	try {
	        		ResultSet results = dc.executeQuery("SELECT RESTAURANTID FROM FAVORITERESTAURANT WHERE USERNAME='"+ user.getLogin() +"'");
	        		while(results.next()) {
	        			favResults.add(results.getString("restaurantId"));
	        		}
	        	} catch(Exception e) {
	        		// No data available, doesn't matter 
	        	}
	        	model.put("favList", favResults);
	        	
	        	List<Restaurant> listRestaurant = flux.getFlux().getRestaurants();
	        	List<Restaurant> listFavRestaurant = new ArrayList<Restaurant>();
	        
	        	for(Restaurant r : listRestaurant) {
	        		for(String favId : favResults) {
	        			if(r.getId() == Integer.parseInt(favId, 10)) listFavRestaurant.add(r);
	        		}
	        	}

	        	model.put("listFavRestaurant", listFavRestaurant);
	       	
	    	} catch(NullPointerException e) {
	    		model.put("nothingToDisplay", "This portlet needs to be configured by an authorized user");
	    	}
	    	
	    	String zoneSubmit = request.getParameter("zoneSubmit");
	    	if(zoneSubmit != null)
	    		model.put("zoneSubmit", zoneSubmit);
	    	
	    	return new ModelAndView("edit", model);
	    }    
	    
	    @RequestMapping(params = {"action=setUserArea"})
	    public void setUserArea(ActionRequest request, ActionResponse response, @RequestParam(value = "zone", required = true) String area) throws Exception {

	    	User user = authenticator.getUser();
	    	
	    	try {
	    		ResultSet results = dc.executeQuery("SELECT AREANAME FROM USERAREA WHERE USERNAME='" + user.getLogin() + "';");
	    		results.next();
	    		results.updateString("AREANAME", area);
	    		results.updateRow();
	    	} catch (SQLException e) {
	    		dc.executeUpdate("INSERT INTO USERAREA (USERNAME, AREANAME) VALUES ('"+user.getLogin()+"', '"+area+"');");
	    	}
	    	
	    	response.setRenderParameter("zoneSubmit", "true");
	    }	    
}