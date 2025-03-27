import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Update totalPerim by currDist
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        int numSides = getNumPoints(s);
        double aveLength = perimeter / numSides;
        return aveLength;
    }

    public double getLargestSide(Shape s) {
        double currLargest = 0;
        
        double currentDistance = 0.0;
        
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist > currLargest){
                currLargest = currDist;
            }
            prevPt = currPt;
        }
        return currLargest;
    }

    public double getLargestX(Shape s) {
        double currLargestX = 0;
        
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            int xVal = currPt.getX();
            if(xVal > currLargestX){
                currLargestX = xVal;
            }
        }
        return currLargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPeri = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > largestPeri){
                largestPeri = length;
            }
        }
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPeri = 0.0;
        File fileWithLargestPeri = null; 
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > largestPeri){
                largestPeri = length;
                fileWithLargestPeri = f;
            }
        }
        return fileWithLargestPeri.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int numberOfPoints = getNumPoints(s);
        System.out.println("number of points = " + numberOfPoints);
        
        double aveLen = getAverageLength(s);
        System.out.println("average length = " + aveLen);
        
        double largestSide = getLargestSide(s);
        System.out.println("Largest side = " + largestSide);
        
        double largestX = getLargestX(s);
        System.out.println("Largest X value = " + largestX);
        
        printFileNames();
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
