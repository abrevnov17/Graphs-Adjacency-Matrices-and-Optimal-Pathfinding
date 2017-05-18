//Anatoly Brevnov
//November 1, 2015
//Period 5
//Advanced Computer Science
//GraphTemplate

import java.util.*;
import java.io.*;
import java.util.Scanner;

class GraphTemplate {
    
    public static void main(String[] args) {
        
        int[][] graph = loadGraph();
        
        System.out.println("Number of Vertices: "+graph[0].length);
        System.out.println("Does a path exist between 0 and 9?  "+doesPathExist(graph, 2, 14));
        System.out.println(""+isConnected(graph));
        System.out.println(""+isComplete(graph));
        System.out.println(getAPath(graph,1,2));
        System.out.println(getShortestPath(graph,2,1));
         
         
        
       // System.out.println(getShortestPath(graph,6,0));


        
        //System.out.println(getAPath(graph,1,5));

        
    }
    
    
    
    static int[][] loadGraph() {
        Scanner s = new Scanner(System.in);
        int numVertices = s.nextInt();
        int[][] graph = new int[numVertices][numVertices];
        System.out.println("Graph:");
        for (int i=0; i<numVertices; i++) {
            for (int j=0; j<numVertices; j++) {
                graph[i][j]=s.nextInt();
                System.out.print(graph[i][j]+" ");
            }
            System.out.println("");
        } 
        return graph;
    }
    
    static boolean doesPathExist(int[][] graph, int start, int goal){
        //Breadth first search here
        
        //this just checks quickly if you are already there
        if (start==goal){
            return true;
        }
        
        //creating my waiting room
        Queue<Integer> waitingRoom=new LinkedList<Integer>();
        
        //ArrayList<TreeNode<T>> waitingRoom=new ArrayList<TreeNode<T>>();
        
        //check will make sure I do not add someting to my waitingRoom that has already been removed from it
        ArrayList<Integer> check=new ArrayList<Integer>();
        
        
        waitingRoom.add(start);
        
        while (waitingRoom.size()>0){
            int temp=waitingRoom.poll();

            check.add(temp);
            //waitingRoom.add(temp);
            
            for (int i=0;i<graph.length;i++){
                //prevents from adding the same thing and checks if there is an edge
                if (i!=temp && graph[temp][i]!=0){
                    //if you are at the goal
                    if (graph[temp][i]!=0 && i==goal){
                        return true;
                    }
                    if (!(check.contains(i)) && !(waitingRoom.contains(i))){
                    waitingRoom.add(i);
                    }
                    
                }
            }
            
            
            
        }

        
        return false;
    }
    
    static boolean isConnected(int[][] graph){
        //basically just loop and check if there is not a path from one vertex to another
        
        int root=graph[0][0];
        for (int i=0;i<graph.length;i++){
            
            if (!(doesPathExist(graph,root,i))){
                
                return false;
            }
        }
        
        return true;
    }
    
    static boolean isComplete(int[][] graph){
        //check if there is a single 0 in the adjacency matrix and returns false if there is
        for (int i=0; i<graph.length; i++) {
            for (int j=0; j<graph[0].length; j++) {
                if (graph[i][j]==0){
                    return false;
                    
                }
            }
            
        }
        
        return true;
    }
    
    static ArrayList<Integer> getAPath(int[][] graph, int start, int goal){
        /*
        if (doesPathExist(graph,start,goal)==false){
            return null;
        }

        if (start==goal){
            return null;
        }
        Queue<Integer> waitingRoom=new LinkedList<Integer>();
        ArrayList<Integer> check=new ArrayList<Integer>();
        ArrayList<Integer> finalReturn=new ArrayList<Integer>();
        waitingRoom.add(start);
        
        while (waitingRoom.size()>0){
            int temp=waitingRoom.poll();
            
            check.add(temp);
            finalReturn.add(temp);
            //waitingRoom.add(temp);
            
            for (int i=0;i<graph.length;i++){
                
                if (i!=temp&&graph[temp][i]!=0){
                    
                    if (graph[temp][i]!=0&&i==goal){
                        finalReturn.add(goal);
                        return finalReturn;
                    }
                    boolean done=false;
                    for (int j=0;j<graph.length;j++){
                        if (graph[temp][j]!=0&&j!=temp){
                            done=true;
                        }
                    }
                    if (!(done)){
                        finalReturn.add(temp);
                    }
                    if (!(check.contains(i))&&!(waitingRoom.contains(i))){
                        waitingRoom.add(i);
                    }
                    
                }
            }
            
            
            
        }
        return finalReturn;
        
        */
        
        //breadth first search using ArrayLists of Arraylists as the waiting room (it is storing whole paths this time)
        
        //check some initial cases quickly
        
        if (doesPathExist(graph,start,goal)==false){
            return null;
        }
        
        if (start==goal){
            ArrayList <Integer> x=new ArrayList<Integer>();
            x.add(start);
            return x;
        }
        
        //waiting room initialization
        
        ArrayList<ArrayList<Integer>> waitingRoom=new ArrayList<ArrayList<Integer>>();
        
        //add the root as the beginning of a path to the waiting room
        
        ArrayList <Integer> temp3 = new ArrayList<Integer>();
        temp3.add(start);
        waitingRoom.add(temp3);
        
        while (waitingRoom.size()>0){
            //ArrayList <Integer> check=new ArrayList<Integer>();
            ArrayList <Integer> temp=waitingRoom.get(waitingRoom.size()-1);
            waitingRoom.remove(waitingRoom.size()-1);
            //ArrayList <Integer> temp2=new ArrayList<Integer>();
            
            //check.add(temp.get(temp.size()-1));
            //waitingRoom.add(temp);
            
            for (int i=0;i<graph.length;i++){
                
                if (i!=temp.get(temp.size()-1)&&graph[temp.get(temp.size()-1)][i]!=0){
                    
                    if (graph[temp.get(temp.size()-1)][i]!=0&&i==goal){
                        ArrayList<Integer> temp2=new ArrayList<Integer>(temp);
                        temp2.add(i);
                        return temp2;
                    }
                    
                    //this prevents from loops in the path
                    
                    if (!(temp.contains(i))){
                    
                    ArrayList<Integer> temp2=new ArrayList<Integer>(temp);
                    temp2.add(i);
                    waitingRoom.add(0,temp2);
                    }
                    //temp.remove(i);

                   
                     
                    
                }
            }
            
    
            
            
        }
        return null;

        
    }
    static ArrayList<Integer> getShortestPath(int[][] graph, int start, int goal){
        //since I use breadth first search (which is optimal) this should work
        return getAPath(graph,start,goal);
        
        
        
    }

    
}
