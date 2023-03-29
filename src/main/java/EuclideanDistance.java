import java.util.ArrayList;
import java.util.List;

public class EuclideanDistance {
    public static void main(String[] args) {
        // matrix of Euclidean distances between documents and query
        double[][] distances = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
        // query
        double[] query = {0.0, 0.0, 0.0};
        // number of best documents to retrieve
        int n = 2;

        // compute distances between query and documents
        List<Double> documentDistances = new ArrayList<>();
        for (int i = 0; i < distances.length; i++) {
            double[] document = distances[i];
            double distance = 0.0;
            for (int j = 0; j < document.length; j++) {
                distance += Math.pow(document[j] - query[j], 2);
            }
            distance = Math.sqrt(distance);
            documentDistances.add(distance);
        }

        // retrieve n best documents
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

        // print best documents
        System.out.println("Best documentsare:");
        for (int i = 0; i < bestDocuments.size(); i++) {
            int documentIndex = bestDocuments.get(i);
            System.out.println("Document " + documentIndex + " with distance " + documentDistances.get(documentIndex));
        }
    }
 }

