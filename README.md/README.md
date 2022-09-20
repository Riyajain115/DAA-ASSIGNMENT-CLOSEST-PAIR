# Closest Pair Problem 
### Using Divide and Conquer Algorithm

## About the Problem Statement:
In this problem, a set of n points are given on the 2D plane. We have to find the pair of points (let pair of points be represented as {x,y} ) whose distance is minimum.


## Test Cases
Test case 01-
<br/>
3 4 
<br/>
5 0.5
<br/>
4 1
<br/>
2 6
<br/>
1 1

Output 01: <br/>
The closest distance is: 1.118033988749895

Test case 02-
<br/>
2 3
<br/>
12 30
<br/>
40 50
<br/>
5 1
<br/>
12 10
<br/>
3 4

Output 02: <br/>
The closest distance is: 1.4142135623730951
<br/>

## Code:
```

import java.util.Arrays;
import java.util.Comparator;
class Mindist
{
    public double x;
    public double y;
    
    Mindist(double x2, double y2) {
      this.x = x2;
      this.y = y2;
    }
    //Method to calculate distance between two points using distance formula
    //Taking two point coordinates as parametes
    public static float distance(Mindist p1, Mindist p2)
    {
    return (float) Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
      
    public static float bruteForce(Mindist A[], int n) {
      float min = Float.MAX_VALUE;
      float Min = 0;
      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          Min = distance(A[i], A[j]);
          if (Min < min) {
            min =Min;
          }
        }
      }
      return min;
    }
    
   
    public static float stripClosest(Mindist A[], int n, float d) {
      float min = d; 
      Arrays.sort(A, 0, n, new PointYComparator());
      for (int i = 0; i <n; i++) {
        for (int j = i + 1; j < n && (A[j].y - A[i].y) < min; j++) {
          if (distance(A[i], A[j]) < min) {
            min = distance(A[i], A[j]);
          }
        }
      }
    
      return min;
    }
    
    public static float closestDist(Mindist A[], int low,int high)
    {
        if ((high - low) <= 3) {
        return bruteForce(A, high);
      }
      int mid = low+ (high - low) / 2;
      Mindist midPoint = A[mid];
      float dl = closestDist(A, low, mid);
      float dr = closestDist(A, mid, high);
      float d = Math.min(dl, dr);
      Mindist strip[] = new Mindist[high];
      int j = 0;
      for (int i = 0; i < high; i++) {
        if (Math.abs(A[i].x - midPoint.x) < d) {
          strip[j] = A[i];
          j++;
        }
      }
      return Math.min(d, stripClosest(strip, j, d));
    }
    public static float closest(Mindist A[], int n) {
      Arrays.sort(A, 0, n, new PointXComparator());
      return closestDist(A, 0, n);
    }
    
  }
 
  class PointXComparator implements Comparator<Mindist> {
    @Override
   public int compare(Mindist pointA, Mindist pointB) {
    if(pointB.x<pointA.x)
    return 1;
    else if(pointB.x>pointA.x)
    return -1;
    else 
    return 0;
    
   }

}  
  class PointYComparator implements Comparator<Mindist> {
    @Override
    public int compare(Mindist pointA, Mindist pointB) {
      if(pointB.y<pointA.y)
      return 1;
      else if(pointB.y>pointA.y)
      return -1;
      else 
      return 0;
      //return Integer.compare(pointA.y, pointB.y);
    }
    
  }

```



## Implementation and Steps:
</br>
To solve this problem, we have to divide points into two halves, after that smallest distance between two points is calculated in a recursive way. 
Using distances from the middle line, the points are separated into some strips. We will find the smallest distance from the array. At first two lists are created with data points, one list will hold points which are sorted on x values, another will hold data points, sorted on y values.

<br/>
1) We sort all points according to x coordinates. <br/>
2) Divide all points in two halves. <br/>
3) Recursively find the smallest distances in both subarrays. <br/>
4) Take the minimum of two smallest distances. Let the minimum be d. <br/>
5) Create an array A[] that stores all points which are at most d distance away from the middle line dividing the two sets. <br/>
6) Find the smallest distance in A[]. <br/>
7) Return the minimum of d and the smallest distance calculated in above step 6. <br/>
8) Also, we need to consider the scenario when the closest pair exists such that one point is present in the left half and second point in the right half.
This is handled using stripClosest method. Finally,will then get  the closest pair whose distance is minimum.

The time complexity of this algorithm will be O(n log n).
