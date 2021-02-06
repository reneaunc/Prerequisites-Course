import java.util.*;

public class CoursePlanner {
	// this method prints out: whether it is possible to take all the given courses and
	// one possible schedule for the given courses
	public static void plan(int numberOfCourses, int[][] prerequisites) {
		List<Integer>[] graph = new LinkedList[numberOfCourses];
		boolean[] visited = new boolean[numberOfCourses];
		
		for(int i = 0; i < numberOfCourses; i++){
			graph[i] = new LinkedList<>();
		}
		for(int x = 0; x < prerequisites.length; x++){
			//adds course to existing graph
			graph[prerequisites[x][1]].add(prerequisites[x][0]);
			//ensures every course with prerequisite is marked as visited
			visited[prerequisites[x][0]] = true;
		}
		//create new queue to keep track of unvisited courses 
		Queue<Integer> queue = new LinkedList<>();
		for(int y = 0; y < numberOfCourses;y++){
			if(!visited[y]){
				queue.add(y);
			}
		}
		//creates schedule to be filled with correct classes
		List<Integer> schedule = new ArrayList<Integer>();
		visited = new boolean[numberOfCourses];
		while(!queue.isEmpty()){
			//pops item from queue and adds to schedule if it hasnt been visited yet
			int u = queue.remove();
			if(!visited[u]){
				schedule.add(u);
				for(int v : graph[u]){				
					queue.add(v);
				}
			visited[u] = true;
		}
	}
		if(check(numberOfCourses, prerequisites)){
			System.out.println("It is possible to take all the given courses");
			System.out.print("A possible schedule is ");
			for(int a = 0; a < schedule.size(); a++){
				System.out.print(schedule.get(a) + " ");
			}
			System.out.println();
		}
		else{
			System.out.println("It is impossible to take all the given courses");
		}
}
	// this is a helper method for plan; it returns a boolean to indicate if a given series of courses can be possibly scheduled
	public static boolean check(int numberOfCourses, int[][] prerequisites) {
		//setup the array holding the list for each class's prereqs
		ArrayList<Integer>[] prereqList = (ArrayList<Integer>[]) new ArrayList[numberOfCourses];
		for(int i = 0; i < numberOfCourses; i++){
			prereqList[i] = new ArrayList<Integer>();
		}

		for(int[] edge : prerequisites){
			//add's the prerequisite(edge[1]) to the list of the course it is a prereq for(edge[0])
			prereqList[edge[0]].add(edge[1]);
		}


		//stores whether there is at least one class with no prerequisites
		boolean hasOneWithoutPrereq = false;
		boolean hasNoCircular = true;

		//loop through all courses
		for(int course = 0; course < numberOfCourses; course ++){
			int count = countPrevious(course, prereqList, numberOfCourses);
			if(count == -1){
				//if it found to be circular set it to false and stop
				hasNoCircular = false;
				break;
			}
			else if(count == 0){
				//if there is at least one class with no prereqs set that value to true
				hasOneWithoutPrereq = true;
			}
		}
		

		//if there is at least one class with no prereqs and there are no circular dependancies
		//it is a possible schedule
		return hasOneWithoutPrereq && hasNoCircular;
	}

	//given a particular course number
	//returns -1 if it finds itself or if it detects ther is a loop
	//returns 0 if a course has no prereqs
	//retunrs 1 if 
	private static int countPrevious(int course, ArrayList[] prereqList, int numberOfCourses){
		//the list of all previously visited nodes
		ArrayList<Integer> prevList = new ArrayList<Integer>();

		//using array list as a queue for the next courses to visit
		//using array list to allow to check as to not duplicate visits
		ArrayList<Integer> toVisitQueue = new ArrayList<Integer>();
		//start the search with the specified course
		toVisitQueue.add(course);
		
		int numberOfVisits = 0;
		//while therea re still classes to visit
		while(toVisitQueue.size() != 0){
			
			//if numberOfVisits exceeds the number of courses we know there must be some kind of loop
			if(numberOfVisits > numberOfCourses){
				//return -1 indicating a circular dependancy
				return -1;
			}
			
			//get the next course from the queue
			int current = toVisitQueue.remove(0);

			//Loop through all of the prereqs of current
			for(int i = 0; i < prereqList[current].size(); i++ ){
				
				int prereq = (int) prereqList[current].get(i);
				
				//if one of the prereqs is the starting node
				if(prereq == course){
					//one of the requirementss for course is course
					//and there is a circular dependancy
					return - 1;
				}

				//if the prereq isn't already on the queue, add it
				if( ! toVisitQueue.contains(prereq)){
					toVisitQueue.add(prereq);
				}
			}

			//add the course that was just visited to the list of all previous
			prevList.add(current);

			numberOfVisits++;
		}

		//remove the given origional course from the list
		prevList.remove(0);

		//course has no prereqs if prevList is empty
		//course has some number of prereqs if prevList is not empty
		return prevList.size() == 0 ? 0 : 1;
	}
}
