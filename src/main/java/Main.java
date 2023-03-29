import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import java.util.ArrayList;
import java.util.List;



public class Main {


    // Print the results
    public static void main(String[] args) {
        int size = 5;
        int n = 3;
        // Create a random matrix
        RealMatrix matrix = randomRealMatrix(size);

        // Create an SVD object for the matrix
        SingularValueDecomposition svd = new SingularValueDecomposition(matrix);

        // Get the singular values, U matrix, and V matrix

        RealMatrix sigma = svd.getS();
        RealMatrix u = svd.getU();
        RealMatrix v = svd.getV();
        System.out.println("Excercise 1");
        System.out.println("Original matrix:");
        printMatrix(matrix.getData());
        System.out.println("Singular values:");
        printMatrix(sigma.getData());
        System.out.println("U matrix:");
        printMatrix(u.getData());
        System.out.println("V matrix:");
        printMatrix(v.getData());
        int n1 = randomInt(0, size);
        int n2 = randomInt(0, size);
        while (n1 == n2) {
            n2 = randomInt(0, size);
        }
        double[] d1 = getDocument(u.getData(), n1);
        System.out.println("Document " + n1);
        printVector(d1);
        double[] d2 = getDocument(u.getData(), n2);
        System.out.println("Document " + n2);
        printVector(d2);
        double[] d3 = euclidianDistance(d1, d2);
        System.out.println("Dissimilarity distance between d1 and d2:");
        printVector(d3);
        System.out.println("--------------------------------------------");
        System.out.println("Excercise 2");
        double[] query = randomIntVector(size);
        printVector(query);
        List<Double> documentDistances = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            System.out.println("Document " + i);
            double[] document = getDocument(u.getData(), i);
            double[] distance = euclidianDistance(query, document);
            double distanceValue = 0;
            for (int j = 0; j < distance.length; j++) {
                distanceValue += Math.pow(distance[j]-query[j], 2);
            }
            distanceValue = Math.sqrt(distanceValue);
            documentDistances.add(distanceValue);
            System.out.println("Distance between document : " + i + " and query = " + distanceValue);
        }
        List<Integer> bestDocuments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int bestDocumentIndex = 0;
            double bestDocumentDistance = Double.MAX_VALUE;
            for (int j = 0; j < documentDistances.size(); j++) {
                double documentDistance = documentDistances.get(j);
                if (documentDistance < bestDocumentDistance && !bestDocuments.contains(j)) {
                    bestDocumentIndex = j;
                    bestDocumentDistance = documentDistance;
                }
            }
            bestDocuments.add(bestDocumentIndex);
        }
        System.out.println(bestDocuments);
        for (int i = 0; i < bestDocuments.size(); i++) {
            int documentIndex = bestDocuments.get(i);
            System.out.println("Document " + documentIndex + " with distance " + documentDistances.get(documentIndex));
        }

    }



    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] >= 0)
                    System.out.printf("| %2.3f", matrix[i][j]);
                else
                    System.out.printf("|%2.3f", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //metod to print a vector
    public static void printVector(double[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.printf("| %2.3f", vector[i]);
        }
        System.out.println();
    }

    //method that generates random real matrix
    public static RealMatrix randomRealMatrix(int size) {
        double[][] matrixData = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixData[i][j] = randomInt(1, 38);
            }
        }
        return MatrixUtils.createRealMatrix(matrixData);
    }

    //method that generates random int
    public static int randomInt(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }

    //from a given matrix get the n column in a vector
    public static double[] getDocument(double[][] a, int n) {
        double[] column = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            column[i] = a[i][n];
        }
        return column;
    }

    public static double distance(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public static double[] euclidianDistance(double[] a, double[] b) {
        double[] distance = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            distance[i] = distance(a[i], b[i]);
        }
        return distance;
    }

    //method that generates random int vector
    public static double[] randomIntVector(int size) {
        double[] vector = new double[size];
        for (int i = 0; i < size; i++) {
            vector[i] = randomInt(1, 38);
        }
        return vector;
    }

}
