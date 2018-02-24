//It uses the A-star search and prints the nodes in the order they are expanded and visited.

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class SearchASTAR
{
	public Map<String, City> a_map;
	
	public SearchASTAR() 
	{
		a_map = new HashMap<String, City>();
		initialize();
	}
	public void initialize() 
	{
		String csvFile = "./roads.txt";
		String csvFile2 = "./cities.txt";
		BufferedReader br = null;
        	String line = "";
        	String by = ",";

 	        try 
		  {
			String[] city_info;
            	 	br = new BufferedReader(new FileReader(csvFile2));
            		while ((line = br.readLine()) != null) 
			{
					if(line.length() == 0)
						continue;
                
              		city_info = line.split(by);
					insertCity(city_info[0].trim(),Double.parseDouble(city_info[1].trim()),Double.parseDouble(city_info[2].trim()));

            		}

       		  } 
		  catch (FileNotFoundException e) 
		  {
            		e.printStackTrace();
        	  } 
		  catch (IOException e) 
		  {
            		e.printStackTrace();
        	  } 

		
       			BufferedReader bf2 = null;
        		String line2 = "";
        		String by2 = ","; int c;

        	  try 
		    {
		  	String[] city_info2;
            		bf2 = new BufferedReader(new FileReader(csvFile));
            		while ((line2 = bf2.readLine()) != null) 
			{

				if(line2.length() == 0)
					continue;
                
            	 city_info2 = line2.split(by2);
			  	 c = Integer.valueOf(city_info2[2].trim());
			  	 insertLink(city_info2[0].trim(),city_info2[1].trim(),c);

           		}

        	    } 
		    catch (FileNotFoundException e) 
		    {
            		e.printStackTrace();
        	    } 
		    catch (IOException e) 
		    {
            		e.printStackTrace();
        	    } 
	   }
	
	   public void insertCity(String c, double la, double ln) 
	   {
		City city = new City(c, la, ln);
		a_map.put(c, city);
	   }
	
	   public void insertLink(String src_City, String dest_City, int dist) 
	   {
		City source = a_map.get(src_City);
		City destination = a_map.get(dest_City);
		a_map.put(src_City, source);
		a_map.put(dest_City, destination);
		source.setRoad(destination, dist);
		destination.setRoad(source, dist);
	    }
	
	
	
	public double pathCost(Road path)
	{
		if (path != null) return path.getY() + path.getX();
		else return 0;
	}
	
	public Map<String, Object> asearch(String source, String destination) 
	{
		City destinationCity = a_map.get(destination);
		City sourceCity = a_map.get(source);
		ArrayList<String> visitedNodes = new ArrayList<String>();
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		Comparator<Road> comparator = new Comparator<Road>() 
		{
			@Override
			public int compare(Road x, Road y)
			{
				double xValue = 0, yValue = 0;
				xValue = pathCost(x);
				yValue = pathCost(y);
				
				return ((xValue > yValue) ? 1 : ((xValue == yValue) ? 0 : -1));
			}
		};
		
		PriorityQueue<Road> queue = new PriorityQueue<Road>(a_map.size(), comparator);
		
		Road path = new Road();
		path.setCity(sourceCity);
		path.setX(0);
		path.setY(sourceCity.eval_heuristic(destinationCity));
		
		sourceCity.setOptimalPath(sourceCity, path);
		
		queue.add(path);
		
		while(!queue.isEmpty())
		 {
			path = queue.poll();
			City lastCity = path.getDestination();
			
			if (lastCity.equals(destinationCity)) 
			{
				ret.put("nodesExpanded", visitedNodes.clone());
				ret.put("path", path);
				break;
			}
			
			if (visitedNodes.indexOf(lastCity.getCity()) == -1) {
				visitedNodes.add(lastCity.getCity());
			}
			else {
				Road existingPath = path.getSource().getOptimalPath(lastCity);
				if (pathCost(path) < pathCost(existingPath)) {
					queue.remove(existingPath);
					path.getSource().setOptimalPath(path.getDestination(), path);
				}
				else 
				{
					continue;
				}
			}
			
			
			ArrayList<City> neighbors = lastCity.getCityList();
			for (int i = 0 ; i < neighbors.size() ; i++ ) 
			{
				Road newPath = new Road(path);
				if (newPath.getCity(neighbors.get(i)) == -1) 
				{
					newPath.setCity(neighbors.get(i));
					newPath.setX(path.getX() + lastCity.getDistance(neighbors.get(i)));
					newPath.setY(neighbors.get(i).eval_heuristic(destinationCity));
					queue.add(newPath);
				}
			}
		}

		return ret;
	}
	
	public void print(Map<String, Object> data)
	{
		ArrayList<String> visited= (ArrayList<String>) data.get("nodesExpanded");
		LinkedList<City> path = ((Road) data.get("path")).getRoute();

		System.out.println("Cities are expanded in the following order: ");
		System.out.println();
		
		String p = " ";
		for(String c : visited)
		{

			  System.out.print(p+c);
			  p = ",";
		}

		System.out.println("\n\nNumber of cities expanded: " + visited.size());
		
		double distance = 0;
		System.out.println("\nPath to reach "+path.get(path.size() - 1).getCity()+ " from " +path.get(0).getCity()+" is :\n"); 
		for (int i = 0 ; i < path.size()-1 ; i++) 
		{
			distance += path.get(i).getDistance(path.get(i + 1));
			System.out.print(path.get(i).getCity() + p);
		}
		
		System.out.println(path.get(path.size() - 1).getCity());
	
		System.out.println("\n Number of cities in the path: " + path.size() + "\n");
	
		System.out.println("Total distance between "+ path.get(0).getCity()+" and "+ path.get(path.size() - 1).getCity() + " is " + distance +" km");
	}
}