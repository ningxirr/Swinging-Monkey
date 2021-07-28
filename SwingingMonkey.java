//Nasreeya 6213128
//Pojanut 6213205
//Palakorn 6213206
//Pakkapond 6213207

import java.util.*;

public class SwingingMonkey {
    public static void main(String[] args) {
        new SwingingMonkey();
    }
    public SwingingMonkey(){
        boolean cont = true; 
        while(cont){
            try { 
                int num_tree;
                ArrayList<Tree> List_Tree = new ArrayList<>();
                Scanner num_tree_keyboard_scanner = new Scanner(System.in);
                System.out.println("Enter number of trees (>=3) = ");                
                num_tree = num_tree_keyboard_scanner.nextInt();
                
                if (num_tree < 3) {
                    System.out.println("!!! Number of trees must be more than or equal 3 !!!");
                }
                else{
                    for (int i = 0; i < num_tree; i++) {
                        try{
                            int height_tree;
                            Scanner size_tree_keyboard_scanner = new Scanner(System.in);                            
                            System.out.println("Enter tree size ("+(i+1)+") = ");                           
                            height_tree = size_tree_keyboard_scanner.nextInt();
                            
                            if(height_tree <= 0){
                                System.out.println("!!! Tree size must be more than 0 !!! ");
                                i--;
                            }
                            else{
                                List_Tree.add(new Tree(height_tree,i+1));
                            }   
                        } catch (RuntimeException e) {
                            System.out.println("!!! Tree size must be a number !!!");
                            i--;
                        }                    
                    }
                    
                    System.out.printf("Number of pairs = %d\n\n",countpairs(List_Tree));
                    
                    String cont_str = "x";
                    while(!cont_str.equalsIgnoreCase("n")&&!cont_str.equalsIgnoreCase("y")){                       
                        Scanner cont_keyboard_scanner = new Scanner(System.in);   
                        System.out.println("Try again ? (Y/N) : ");
                        cont_str = cont_keyboard_scanner.nextLine();
                        if(cont_str.equalsIgnoreCase("n"))cont = false;
                        else if(!cont_str.equalsIgnoreCase("y"))System.out.println("!!! Wrong input !!!");
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("!!! Number of trees must be a number !!!");
            }
        }
    }
    public int countpairs(ArrayList<Tree> T){
        int num_pairs = 0;
        System.out.println("\nSolution");
        ArrayDeque<Tree> Stack_Tree = new ArrayDeque<>();
        for(int i = 0,j = 1; i < T.size(); i++){
            if(Stack_Tree.isEmpty())Stack_Tree.add(T.get(i));
            else{
                while(!Stack_Tree.isEmpty() && T.get(i).getheight() > Stack_Tree.peek().getheight()){
                    System.out.printf("%2d. Tree[%d], %3d ft. ---> Tree[%d], %3d ft.\n",
                            j++,Stack_Tree.peek().getindex(),Stack_Tree.peek().getheight(),T.get(i).getindex(),T.get(i).getheight());
                    Stack_Tree.pop();
                    num_pairs++;
                }
                if(Stack_Tree.isEmpty())Stack_Tree.push(T.get(i));
                else if(!Stack_Tree.isEmpty() && T.get(i).getheight() < Stack_Tree.peek().getheight()){
                    System.out.printf("%2d. Tree[%d], %3d ft. ---> Tree[%d], %3d ft.\n",
                            j++,Stack_Tree.peek().getindex(),Stack_Tree.peek().getheight(),T.get(i).getindex(),T.get(i).getheight());
                    Stack_Tree.push(T.get(i));
                    num_pairs++;
                }
                else if(T.get(i).getheight() == Stack_Tree.peek().getheight()){
                    System.out.printf("%2d. Tree[%d], %3d ft. ---> Tree[%d], %3d ft.\n",
                            j++,Stack_Tree.peek().getindex(),Stack_Tree.peek().getheight(),T.get(i).getindex(),T.get(i).getheight());
                    Stack_Tree.pop();
                    Stack_Tree.push(T.get(i));                    
                    num_pairs++;
                }
            }
        }
        return num_pairs;
    }
}
class Tree {
    private int height ,index;
    public Tree (int h,int i){height = h;index = i;}
    public int getheight()  { return height;}
    public int getindex()   { return index; }
}