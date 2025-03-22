import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static class Document {
        int priority;
        boolean isTarget;

        public Document(int priority, boolean isTarget) {
            this.priority = priority;
            this.isTarget = isTarget;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        for (int i = 0; i < testCaseCount; ++i) {
            int numberOfDocuments = scanner.nextInt();
            int targetIndex = scanner.nextInt();
            Queue<Document> documentQueue = new LinkedList<>();

            for (int j = 0; j < numberOfDocuments; ++j) {
                int documentPriority = scanner.nextInt();
                boolean isTargetDocument = (j == targetIndex);
                documentQueue.add(new Document(documentPriority, isTargetDocument));
            }

            int printOrder = 0;
            while (!documentQueue.isEmpty()) {
                Document currentDocument = documentQueue.poll();
                boolean hasHigherPriority = false;

                for (Document document : documentQueue) {
                    if (document.priority > currentDocument.priority) {
                        hasHigherPriority = true;
                        break;
                    }
                }

                if (hasHigherPriority) {
                    documentQueue.add(currentDocument);
                } else {
                    printOrder++;
                    if (currentDocument.isTarget) {
                        System.out.println(printOrder);
                        break;
                    }
                }
            }
        }
        scanner.close();
    }
}