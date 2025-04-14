import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int plugCount = scanner.nextInt();
        int usageCount = scanner.nextInt();

        int[] usageOrder = new int[usageCount];
        for (int i = 0; i < usageCount; ++i) {
            usageOrder[i] = scanner.nextInt();
        }

        List<Integer> plugged = new ArrayList<>();
        int unplugCount = 0;

        for (int i = 0; i < usageCount; ++i) {
            int device = usageOrder[i];

            if (plugged.contains(device)) {
                continue;
            }

            if (plugged.size() < plugCount) {
                plugged.add(device);
                continue;
            }

            int idxToUnplug = -1;
            int latestUsage = -1;

            for (int j = 0; j < plugged.size(); ++j) {
                int pluggedDevice = plugged.get(j);
                int nextUsageIndex = Integer.MAX_VALUE;

                for (int k = i + 1; k < usageCount; ++k) {
                    if (usageOrder[k] == pluggedDevice) {
                        nextUsageIndex = k;
                        break;
                    }
                }

                if (nextUsageIndex > latestUsage) {
                    latestUsage = nextUsageIndex;
                    idxToUnplug = j;
                }
            }

            plugged.set(idxToUnplug, device);
            unplugCount++;
        }

        System.out.println(unplugCount);
    }
}