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
    
  