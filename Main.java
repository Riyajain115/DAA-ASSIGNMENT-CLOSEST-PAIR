import java.util.*;
public class Main
{
    public static void main(String[] args) 
    {
      Scanner sc=new Scanner(System.in);
      System.out.println("Enter number of points in the plane: ");
      int num=sc.nextInt();
      double x,y;
        Mindist A[] = new Mindist[num];//An array A to store x and y coordinates
        for(int i=0;i<num;i++)
        {
          x=sc.nextDouble();
          y=sc.nextDouble();
          A[i]=new Mindist(x, y);
        }

       
        System.out.println("The closest distance is:" +  Mindist.closest(A, A.length));
      //Passing an array and its length
      }
}
