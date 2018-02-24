// Main class which calls the corresponding search function based on user's search typre, source city and destination city. It would print the path from source to destination city. 

import java.util.*;

public class SearchUSA 
{
	public static void main(String args[]) 
	{
		
			String search_Type = args[0];
			String src_City = args[1];
			String dest_City = args[2];
			
			SearchBFS b = new SearchBFS();
			SearchDFS d = new SearchDFS();
			SearchASTAR a = new SearchASTAR();

			HashMap<String, ArrayList<String>> path = new HashMap<String, ArrayList<String>>();

			if(search_Type.equals("BFS")) 
            {
				path = b.bfs(src_City, dest_City);
				b.print(path);
			}
			else if(search_Type.equals("DFS")) 
            {
				path = d.dfs(src_City, dest_City);
				d.print(path);
			}
			else if(search_Type.equals("ASTAR"))
			{
				a.print(a.asearch(src_City,dest_City));
			}
            else
		    {
				System.out.println("Search type can only be one of the following : BFS, DFS or ASTAR");
		    }

	}
}