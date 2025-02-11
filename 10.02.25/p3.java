// You are given an integer array nums and two integers l and r. Your task is to 
// analyze the volatility of a sequence of values. The volatility of a sequence is 
// defined as the difference between the maximum and minimum values in that sequence.

// You need to determine the sequence with the highest volatility among all 
// sequences of length between l and r (inclusive).

// Return the highest volatility. If no such sequence exists, return -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers, n, l, r
// Line-2: n space separated integers, nums[].

// Output Format:
// -------------
// An integer, the highest volatility.


// Sample Input-1:
// ---------------
// 5 2 3
// 8 3 1 6 2

// Sample Output-1:
// ----------------
// 7

// Explanation:
// ------------
// The possible sequences of length between l = 2 and r = 3 are:

// [8, 3] with a volatility of 8−3=5
// [3, 1] with a volatility of 3−1=2
// [1, 6] with a volatility of 6−1=5
// [8, 3, 1] with a volatility of 8−1=7
// The sequence [8, 3, 1] has the highest volatility of 7.

// Sample Input-2:
// ---------------
// 4 2 4
// 5 5 5 5

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// All possible sequences have no volatility as the maximum and minimum values 
// are the same, resulting in a difference of 0.
 
import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int l=sc.nextInt();
        int r=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int i=0;
        int j=0;
        int maxvol=-1;
        TreeMap<Integer,Integer> map=new TreeMap<>();
        while(j<n){
            map.put(arr[j],map.getOrDefault(arr[j],0)+1);
            while(j-i+1>=l){
                int vol=map.lastKey()-map.firstKey();
                maxvol=Math.max(maxvol,vol);
                if(j-i+1==r){
                    if(map.get(arr[i])==1){
                        map.remove(arr[i]);
                    }
                    else{
                        map.put(arr[i],map.get(arr[i])-1);
                    }
                    i++;
                }
                else{
                    break;
                }
            }
            j++;
        }
        System.out.println(maxvol);
        sc.close();
    }
}