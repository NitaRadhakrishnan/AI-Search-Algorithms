//It stores the data for the city paths in the USA.

import java.util.*;

public class Road 
{
	double x,y;
	LinkedList<City> city_Route;
	
	// It has basic Getter and Setter functions to store values for the paths between differrent USA cities.
	public Road() 
	{
		this.x = 0;
		this.y = 0;
		city_Route = new LinkedList<City>();
		
	}
	
	public Road(LinkedList<City> c, double x, double y) 
	{
		this.x = x;
		this.y = y;
		city_Route = new LinkedList<City>(c);
	}
	
	public Road(Road p) 
	{
		this.x = p.x;
		this.y = p.y;
		this.city_Route = new LinkedList<City>(p.city_Route);
	}
	
	public int getCity(City c)
	{
		return city_Route.indexOf(c);
	}
	
	public LinkedList<City> getRoute() 
	{
		return city_Route;
	}
	
	public double getX() 
	{
		return x;
	}
	
	public double getY() 
	{
		return y;
	}
	
	public City getSource() 
	{
		return city_Route.get(0);
	}
	
	public City getDestination() 
	{
		return city_Route.get(city_Route.size() - 1);
	}
	
	public void setCity(City c) 
	{
		this.city_Route.add(c);
	}	
	
	public void setX(double x) 
	{
		this.x = x;
	}
	
	public void setY(double y) 
	{
		this.y = y;
	}
	
}
