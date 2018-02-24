//It uses the DFS search and prints the nodes in the order they are expanded and visited.

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class SearchDFS
{
	public Map<String, ArrayList<String>> map;

        public SearchDFS() 
        {
		map = new HashMap<String, ArrayList<String>>();
		initialize();
	}
		 public void initialize() 
        {
        	String csvFile = "./roads.txt";
        	BufferedReader bf = null;
        	String line = "";
        	String by = ",";

        	try 
		{
		  	String[] city_info;
            		bf = new BufferedReader(new FileReader(csvFile));
            		while ((line = bf.readLine()) != null) 
			{

				if(line.length() == 0)
					continue;
                
              	    city_info = line.split(by);
				link(city_info[0].trim(),city_info[1].trim());
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
 
	
    public void link(String src_City, String dest_City) 
    {
	insert(src_City);
    insert(dest_City);
    map.get(src_City).add(dest_City);
    map.get(dest_City).add(src_City);
	}

	public void insert(String city)
        {
		if (map.containsKey(city) != true) 
                {
			ArrayList<String> list = new ArrayList<String>();
			map.put(city, list);
		}
	}
	
	public ArrayList<String> getNeighbours(String city) 
        {
	
		ArrayList<String> neighbours = map.get(city);
		Collections.sort(neighbours);
		return (ArrayList<String>) neighbours.clone();
	}
	
	
	
	public HashMap<String, ArrayList<String>> dfs(String src_City, String dest_City) 
        {
		
		ArrayList<String> path = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
        HashMap<String, ArrayList<String>> h = new HashMap<String, ArrayList<String>>();
		Stack<List<String>> s = new Stack<List<String>>();
		
		path.add(src_City);
		s.push(path);
		
		while(true) 
                {
					System.out.println(path);
			if (s.size() == 0) 
                        {
				h.put("Visited", visited);
				h.put("Path", null);
				return h;
			}

			path = (ArrayList) s.pop();
			String last = path.get(path.size() - 1);
			
			if (last.equals(dest_City)) 
                        {
				h.put("Visited", visited);
				h.put("Path", path);
				return h;
			}

			if (visited.indexOf(last) == -1) visited.add(last);	
			
                        ArrayList<String> neighbours = getNeighbours(last);
			
                        for(int i = neighbours.size() - 1 ; i >= 0 ; i--) 
                        {
				String n = neighbours.get(i);
				if (visited.indexOf(n) == -1)
                                {
					List<String> newpath = (ArrayList) path.clone();
 					newpath.add(n);
					s.add(newpath);	
				}
			}
		}
	}
	
	public void print(HashMap<String, ArrayList<String>> h) 
        {
		ArrayList<String> path = h.get("Path");
		
                if (path == null) return;

 		System.out.println("Cities are expanded in the following order:\n\n" + h.get("Visited"));
		// Above statement does not print destination city since it is only explored and not expanded.

		System.out.println("\nTotal number of cities expanded : " + h.get("Visited").size());
		
		System.out.println("\nThe path to reach "+path.get(0)+ " from "+path.get(path.size()-1)+" is :\n");

		for(int i = 0 ; i < path.size() ; i++) 
                {
			System.out.print(path.get(i));
			if (i != path.size() - 1) 
			 System.out.print(" -> ");
            
		}

               
	}
}
