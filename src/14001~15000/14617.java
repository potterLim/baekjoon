import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final long FNV_OFFSET_BASIS = 1469598103934665603L;
    private static final long FNV_PRIME = 1099511628211L;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<PrimeExponentPair>> factorAList = new ArrayList<>();
        List<List<PrimeExponentPair>> factorBList = new ArrayList<>();
        int[] cValues = new int[n];
        long totalCount = 0L;

        for (int i = 0; i < n; ++i) {
            int aValue = scanner.nextInt();
            int bValue = scanner.nextInt();
            int cValue = scanner.nextInt();
            cValues[i] = cValue;
            factorAList.add(factorize(aValue));
            factorBList.add(factorize(bValue));
            totalCount += (long)cValue + 1L;
        }

        LongHashSet set = new LongHashSet((int)Math.min(totalCount, 20000000L));

        for (int i = 0; i < n; ++i) {
            List<PrimeExponentPair> aFactor = factorAList.get(i);
            List<PrimeExponentPair> bFactor = factorBList.get(i);
            for (int j = 0; j <= cValues[i]; ++j) {
                long hashValue = computeHash(aFactor, bFactor, j);
                set.add(hashValue);
            }
        }

        System.out.println(set.size());
        scanner.close();
    }

    private static List<PrimeExponentPair> factorize(int value) {
        List<PrimeExponentPair> result = new ArrayList<>();
        int remainder = value;
        for (int i = 2; i * i <= remainder; ++i) {
            if (remainder < i) {
                break;
            }
            if (remainder % i == 0) {
                int exponent = 0;
                while (remainder % i == 0) {
                    remainder /= i;
                    ++exponent;
                }
                result.add(new PrimeExponentPair(i, exponent));
            }
        }
        if (remainder > 1) {
            result.add(new PrimeExponentPair(remainder, 1));
        }
        result.sort((x, y) -> Integer.compare(x.prime, y.prime));
        return result;
    }

    private static long computeHash(List<PrimeExponentPair> aFactor,
                                    List<PrimeExponentPair> bFactor,
                                    int mul) {
        long h = FNV_OFFSET_BASIS;
        int idxA = 0;
        int idxB = 0;

        while (idxA < aFactor.size() || idxB < bFactor.size()) {
            if (idxA < aFactor.size() && idxB < bFactor.size()) {
                int pA = aFactor.get(idxA).prime;
                int pB = bFactor.get(idxB).prime;
                if (pA < pB) {
                    int e = aFactor.get(idxA).exponent;
                    h = fnv64(h, pA);
                    h = fnv64(h, e);
                    ++idxA;
                } else if (pA == pB) {
                    int e = aFactor.get(idxA).exponent + bFactor.get(idxB).exponent * mul;
                    if (e != 0) {
                        h = fnv64(h, pA);
                        h = fnv64(h, e);
                    }
                    ++idxA;
                    ++idxB;
                } else {
                    int e = bFactor.get(idxB).exponent * mul;
                    if (e != 0) {
                        h = fnv64(h, pB);
                        h = fnv64(h, e);
                    }
                    ++idxB;
                }
            } else if (idxA < aFactor.size()) {
                int p = aFactor.get(idxA).prime;
                int e = aFactor.get(idxA).exponent;
                h = fnv64(h, p);
                h = fnv64(h, e);
                ++idxA;
            } else {
                int p = bFactor.get(idxB).prime;
                int e = bFactor.get(idxB).exponent * mul;
                if (e != 0) {
                    h = fnv64(h, p);
                    h = fnv64(h, e);
                }
                ++idxB;
            }
        }

        return h;
    }

    private static long fnv64(long current, int value) {
        long r = current ^ (value & 0xffffffffL);
        r *= FNV_PRIME;
        return r;
    }

    private static final class PrimeExponentPair {
        public int prime;
        public int exponent;

        public PrimeExponentPair(int prime, int exponent) {
            this.prime = prime;
            this.exponent = exponent;
        }
    }

    private static final class LongHashSet {
        private long[] table;
        private boolean[] used;
        private int size;
        private int mask;
        private int threshold;
        private static final double LOAD_FACTOR = 0.75;

        public LongHashSet(int capacity) {
            int cap = 1;
            while (cap < capacity) {
                cap <<= 1;
            }
            table = new long[cap];
            used = new boolean[cap];
            mask = cap - 1;
            threshold = (int)(cap * LOAD_FACTOR);
            size = 0;
        }

        public boolean add(long key) {
            if (size >= threshold) {
                rehash();
            }
            int index = (int)(key & mask);
            while (true) {
                if (!used[index]) {
                    table[index] = key;
                    used[index] = true;
                    ++size;
                    return true;
                } else if (table[index] == key) {
                    return false;
                }
                index = (index + 1) & mask;
            }
        }

        private void rehash() {
            long[] oldTable = table;
            boolean[] oldUsed = used;
            int newCap = table.length << 1;
            table = new long[newCap];
            used = new boolean[newCap];
            mask = newCap - 1;
            threshold = (int)(newCap * LOAD_FACTOR);
            size = 0;
            for (int i = 0; i < oldTable.length; ++i) {
                if (oldUsed[i]) {
                    add(oldTable[i]);
                }
            }
        }

        public int size() {
            return size;
        }
    }
}
