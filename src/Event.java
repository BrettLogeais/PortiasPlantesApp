import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Elizabeth Stevenson, Brett Logeais
 * Purpose: to create the different types of events
 */
public class Event extends DatabaseObject {
	protected static final String TITLE = "title";
	protected static final String LOCATION = "location";
	protected static final String TIMES = "time";
	protected static final String PRICE = "price";
	protected static final String SEATING = "seating";
	protected static final String REVIEWS = "reviews";
	
	protected String title;
	protected String location;
	protected ArrayList<String> showTimes;
	protected double price;
	protected Seating seating;
	protected ArrayList<Review> reviews;
	
	/**
	 * Purpose: creates an event from a jsonobject
	 * @param objectJSON
	 */
	public Event(JSONObject objectJSON) {
		this.title = (String)objectJSON.get(TITLE);
		this.location = (String)objectJSON.get(LOCATION);
		this.price = (double)objectJSON.get(PRICE);
		this.seating = new Seating((JSONObject)objectJSON.get(SEATING));
		
		JSONArray arrayJSON = (JSONArray)objectJSON.get(TIMES);
		ArrayList<String> array = new ArrayList<String>();
		for (int i = 0; i < arrayJSON.size(); i++) array.add((String)arrayJSON.get(i));
		this.showTimes = array;
		arrayJSON = (JSONArray)objectJSON.get(REVIEWS);
		ArrayList<Review> array2 = new ArrayList<Review>();
		for (int i = 0; i < arrayJSON.size(); i++) array2.add(new Review((JSONObject)arrayJSON.get(i)));
		this.reviews = array2;
	}
	
	/**
	 * Purpose: to create a new event
	 * @param title: name of event
	 * @param location: place of event
	 * @param time: time of event
	 */
	public Event(String title, String location, ArrayList<String> times, double price) {
		this.title = title;
		this.location = location;
		this.showTimes = times;
		this.price = price;
		int[] hand = {};
		this.seating = new Seating(50, 10, hand);
		this.reviews = new ArrayList<Review>();
	}
	
	public Event(String title, String location, double price) {
		this.title = title;
		this.location = location;
		this.showTimes = new ArrayList<String>();
		this.price = price;
		int[] hand = {};
		this.seating = new Seating(50, 10, hand);
		this.reviews = new ArrayList<Review>();
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Purpose: returns the title, location, time, and price of event
	 * @return title: name of event
	 * @return location: place of event
	 * @return time: time of event
	 * @return price: price of event
	 * @return Seating: Seating's to string statement
	 */
	@Override
	public String toString() {
		return "Title: " + this.title +
				"\n     Location: " + this.location + 
				"\n     Price: $" + this.price;
	}
	
	/**
	 * Purpose: adds a new review
	 */
	public void addReview(String user, int rating, String comment) {
		this.reviews.add(new Review(user, rating, comment));
	}
	
	/**
	 * Purpose: prints the reviews
	 */
	public void printReviews() {
		System.out.println("Reviews for " + this.title + "\n");
		for (int i = 0; i < reviews.size(); i++) {
			System.out.println(i + ") " + reviews.get(i) + "\n\n");
		}
	}
	
	/**
	 * Purpose: prints show times
	 */
	public int printShowTimes() {
		String string = "Show Times:\n";
		for (int i = 0; i < showTimes.size(); i++) {
			string += "\t" + i + ") " + showTimes.get(i) + "\n";
		}
		System.out.println(string);
		return showTimes.size();
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject eventDetails = new JSONObject();
		eventDetails.put(TITLE, this.title);
		eventDetails.put(LOCATION, this.location);
		eventDetails.put(PRICE, this.price);
		eventDetails.put(SEATING, this.seating.toJSON());
		JSONArray array = new JSONArray();
		for (int i = 0; i < this.showTimes.size(); i++) array.add(this.showTimes.get(i));
		eventDetails.put(TIMES, array);
		array = new JSONArray();
		for (int i = 0; i < this.reviews.size(); i++) array.add(this.reviews.get(i).toJSON());
		eventDetails.put(REVIEWS, array);
		return eventDetails;
	}
}

