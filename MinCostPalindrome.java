import java.util.*;
import java.io.*;
import java.lang.*;
public class MinCostPalindrome{
    public static long findMinimumCost(long[][] costDis, String palindromeString, long infinity){
        // Now iterating the half string
        // Checking the forward index to it's respective backward index for equality
        // If not equal changing the letters to get the equality in minimum cost
        int stringSize = palindromeString.length();
        long minCost = 0;    
        int connotConvert  = 0;
        for(int i = 0; i < stringSize/2 ; i++){
            int forwardIndxLetter = palindromeString.charAt(i) - 'a';
            int backwardIndxLetter = palindromeString.charAt(stringSize-i-1) - 'a';
            // If Not Equal the cost of change is calculated.
            if(forwardIndxLetter != backwardIndxLetter){
                long minimum = Math.min(costDis[forwardIndxLetter][backwardIndxLetter], costDis[backwardIndxLetter][forwardIndxLetter]);
                if(minimum == infinity){
                    connotConvert = 1;
                }else{
                    minCost += minimum;
                }
            }
        }
        return connotConvert == 0 ? minCost : -1;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Input String
        String s = br.readLine();
        int m = Integer.parseInt(br.readLine());
        //Declaration of Floyd Warshall Matrix 
        long[][] costDis = new long[26][26];
        long infinity = Integer.MAX_VALUE * (long)10000;
        // Initializing costDis to infinity
        // Infinity can be considered as > 10^8 max value for cost
        for(int i = 0 ; i < 26; i++){
            for(int j = 0; j < 26; j++){
                costDis[i][j] = infinity;
            }
            costDis[i][i] = 0;
        }
        for(int i = 0; i < m ; i++){
            // Input x , y and cost(x <-> y {conversion})
            String[] input = br.readLine().split(" ");
            String x = input[0];
            String y = input[1];
            long cost = Long.parseLong(input[2]);
            // For x -> y conversion
            costDis[x.charAt(0)-'a'][y.charAt(0)-'a'] = cost;
            // For y -> x conversion
            costDis[y.charAt(0)-'a'][x.charAt(0)-'a'] = cost;
        }
        // Floyd Warshal Algorithm
        // Generates mimimum distance matrix between every two points
        // By checking through every point
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    if (costDis[j][i] + costDis[i][k] < costDis[j][k]) 
                        costDis[j][k] = costDis[j][i] + costDis[i][k]; 
                }
            }
        }
        // Now calculating the minimum cost for making the giving string palindrome
        long minCost = findMinimumCost(costDis, s, infinity);
        // Minimum Cost to convert string to palindrome.
        System.out.println(minCost);
    }
}