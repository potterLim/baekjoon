import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final long LIMIT = 1000000001L;
    private static final long INF = Long.MAX_VALUE / 4L;

    private static class Recipe {
        private final int mResultIndex;
        private final int[] mIngredientIndices;
        private final int[] mCounts;

        public Recipe(int resultIndex, int[] ingredientIndices, int[] counts) {
            this.mResultIndex = resultIndex;
            this.mIngredientIndices = ingredientIndices;
            this.mCounts = counts;
        }
    }

    private static int getIndex(String name, HashMap<String, Integer> nameToIndex, ArrayList<Long> marketCosts) {
        Integer index = nameToIndex.get(name);

        if (index != null) {
            return index;
        }

        int newIndex = marketCosts.size();
        nameToIndex.put(name, newIndex);
        marketCosts.add(INF);
        return newIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        HashMap<String, Integer> nameToIndex = new HashMap<String, Integer>();
        ArrayList<Long> marketCosts = new ArrayList<Long>();
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        for (int i = 0; i < n; ++i) {
            String name = scanner.next();
            long price = scanner.nextLong();

            int index = getIndex(name, nameToIndex, marketCosts);
            long currentPrice = marketCosts.get(index);

            if (price < currentPrice) {
                marketCosts.set(index, price);
            }
        }

        for (int i = 0; i < m; ++i) {
            String line = scanner.next();
            String[] parts = line.split("=");

            String resultName = parts[0];
            String[] terms = parts[1].split("\\+");

            int resultIndex = getIndex(resultName, nameToIndex, marketCosts);

            int[] ingredientIndices = new int[terms.length];
            int[] counts = new int[terms.length];

            for (int j = 0; j < terms.length; ++j) {
                String term = terms[j];
                int count = term.charAt(0) - '0';
                String ingredientName = term.substring(1);

                int ingredientIndex = getIndex(ingredientName, nameToIndex, marketCosts);

                ingredientIndices[j] = ingredientIndex;
                counts[j] = count;
            }

            recipes.add(new Recipe(resultIndex, ingredientIndices, counts));
        }

        Integer loveIndexObject = nameToIndex.get("LOVE");

        if (loveIndexObject == null) {
            System.out.println(-1);
            scanner.close();
            return;
        }

        int itemCount = marketCosts.size();
        long[] costs = new long[itemCount];

        for (int i = 0; i < itemCount; ++i) {
            costs[i] = marketCosts.get(i);
        }

        for (int i = 0; i < itemCount; ++i) {
            long[] nextCosts = Arrays.copyOf(costs, itemCount);
            boolean isChanged = false;

            for (int j = 0; j < recipes.size(); ++j) {
                Recipe recipe = recipes.get(j);

                long totalCost = 0L;
                boolean isPossible = true;

                for (int k = 0; k < recipe.mIngredientIndices.length; ++k) {
                    long ingredientCost = costs[recipe.mIngredientIndices[k]];

                    if (ingredientCost == INF) {
                        isPossible = false;
                        break;
                    }

                    totalCost += (long) recipe.mCounts[k] * ingredientCost;

                    if (totalCost > LIMIT) {
                        totalCost = LIMIT;
                    }
                }

                if (isPossible && totalCost < nextCosts[recipe.mResultIndex]) {
                    nextCosts[recipe.mResultIndex] = totalCost;
                    isChanged = true;
                }
            }

            costs = nextCosts;

            if (!isChanged) {
                break;
            }
        }

        int loveIndex = loveIndexObject;
        long answer = costs[loveIndex];

        if (answer == INF) {
            System.out.println(-1);
        } else if (answer > 1000000000L) {
            System.out.println(1000000001);
        } else {
            System.out.println(answer);
        }

        scanner.close();
    }
}