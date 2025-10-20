package week_12Work;

import java.util.Arrays; 
import java.util.Comparator; 
import java.util.HashMap; 
import java.util.Map; 

class Main {     
	static char[] underScore(String input, int p)
	{         //Array to store final answer     
		char[] ans = new char[input.length() * (p + 1)]; 
		int ind=0;     
		int i=0;   
		while (i<ans.length){   
			//Populate character 
			ans[i++] = input.charAt(ind++);  
			//Populate underscore           
			for(int j=0;j<p;j++){          
				ans[i++] = '_';          
				}        
			}        
		return ans;   
		}     
	static String frequencies(int[] input) {   
		//Map to store frequency   
		Map<Integer, Integer> f = new HashMap<Integer, Integer>();  
		//store the frequency  
		for (int i : input) {    
			if (!f.containsKey(i)) {    
				f.put(i, 0);         
				}           
			f.put(i, f.get(i) + 1);    
			}   
		//Arrays to store keys, values and indices 
		Integer keys[] = new Integer[f.size()]; 
		Integer values[] = new Integer[f.size()];   
		Integer index[] = new Integer[f.size()];  
		int ind = 0;      
		for (int i : f.keySet()) {        
			keys[ind] = i;       
			values[ind] = f.get(i);
			index[ind] = ind;            
			ind++;         }       
		//Sort the index array, based on values and keys  
		Arrays.sort(index, new Comparator<Integer>()
		{             @Override     
			public int compare(Integer o1, Integer o2) { 
			if (values[o2] == values[o1]) {       
				return keys[o1] - keys[o2];      
				}             
			return values[o2] - values[o1];    
			}         });     
		//Print the frequencies    
		String ans = "";      
		for (int i = 0; i < keys.length; i++) {  
			ans = ans + keys[index[i]] + " has frequency " + values[index[i]] + "\n";     
			}       
		return ans;  
		}     
	
	public static void main(String[] args) {   
		int input[] = {2, 9, 2, 1, 2, 1, 7, 7, 9, 9, 9, 0};  
		
		System.out.println("Frequencies for "+Arrays.toString(input));
		
		System.out.println(frequencies(input));   
		String s = "abc";
        int p = 2;    
        System.out.println("underScore("+s+","+p+") = "+Arrays.toString(underScore(s,p))); 
        } 
	
		
	}

