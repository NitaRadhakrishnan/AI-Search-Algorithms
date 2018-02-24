/* It stores the city name, latitude, longitude and the possible paths from the city.*/

import java.util.*;
import java.lang.*;

class City 
{
	String name;
	double la, ln;
	HashMap<City, Double> road;
	HashMap<City, Road> optimal_Path;
	
	public City(String n, double latitude, double longitude) 
	{
		name = new String(n);
		la = latitude;
		ln = longitude;
		road = new HashMap<City, Double>();
		optimal_Path = new HashMap<City, Road>();
	}
	
	// Getter and Setter functions to get city name, latitude, longitude and the possible paths from the city.
	
	public double getLatitude() 
	{
		return la;
	}
	
	public double getLongitude() 
	{
		return ln;
	}
	
	public String getCity() 
	{
		return name;
	}
	
	public boolean equals(City city) 
	{
		return city.name.equals(this.name) && (city.la == this.la) && (city.ln == this.ln);
	}
	
	public ArrayList<City> getCityList() 
	{
		return new ArrayList<City>(road.keySet());
	}
	
	public void setRoad(City c, int d) 
	{
		Double distance;
		distance = new Double(d);
		road.put(c, distance);
	}
	
	public double getDistance(City c) 
	{
		double distance;
		distance = (double) road.get(c);
		return distance;
	}
	
	public Road getOptimalPath(City dest_City) 
	{
		return optimal_Path.get(dest_City);
	}
	
	public void setOptimalPath(City dest_City, Road path) 
	{
		optimal_Path.put(dest_City, path);
	}
	
	public double eval_heuristic(City city) 
	/* Computing the heuristic functions using the formula sqrt((69.5 * (Lat1 - Lat2)) ^ 2 + (69.5 * cos((Lat1 + Lat2)/360 * pi) * (Long1 - Long2)) ^ 2) and then using a precision of to about 1 m. */
	
	{
		double heuristic_F,result;
		heuristic_F = Math.sqrt(Math.pow((69.5*(this.la-city.la)),2)+(69.5 * Math.cos((this.la + city.la)/360 * Math.PI))*Math.pow((this.ln-city.ln),2));
		result=100*heuristic_F;
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
