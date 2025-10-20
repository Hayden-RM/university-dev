/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

/**
 *
 * @author Hayden 21152003
 */
public class DataAnalysis <E extends Comparable<E>>{
    private Queue <E> queue = new Queue();
    private Stack <E> stack = new Stack();
  
    private E[] data;
 
    public DataAnalysis(E[] data)
    {
        this.data = data;
        stack = new Stack<>();
        queue = new Queue<>();
        for(E d : data){
            queue.enqueue(d);
            stack.push(d);
        }
        
    }    
    
    public boolean isPalindrome() {
       
        int size = stack.getSize();
        if (size <= 1) {
            return true;
        }
        
        for (int i = 0; i < size / 2; i++) {
            E stackElement = stack.pop();
            E queueElement = queue.dequeue();
            
            if (!stackElement.equals(queueElement)) {
                return false;
            }
        }
        
        return true;
    }

  
}
