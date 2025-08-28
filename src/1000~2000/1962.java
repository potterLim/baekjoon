import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

final class Main {
    private static final String[] DIG = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
    private static final String[] BIG = {"만", "억", "조", "경", "해", "자", "양", "구", "간", "정", "재", "극"};
    private static final long[] GROUP_MASK = new long[10000];
    private static final long[] BIG_MASK = new long[12];
    private static final BigInteger BASE = BigInteger.valueOf(10000L);

    public static void main(String[] args) throws IOException {
        precomputeMasks();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < t; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            BigInteger n = new BigInteger(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            long banned = 0L;
            for (int i = 0; i < m; ++i) {
                String token = st.nextToken();
                if (!token.isEmpty()) {
                    banned |= bitOfJamo(token.charAt(0));
                }
            }

            boolean[] bigAllowed = new boolean[12];
            for (int i = 0; i < 12; ++i) {
                bigAllowed[i] = (BIG_MASK[i] & banned) == 0L;
            }

            ArrayList<Integer> allowedList = new ArrayList<>(10000);
            for (int v = 1; v <= 9999; ++v) {
                if ((GROUP_MASK[v] & banned) == 0L) {
                    allowedList.add(v);
                }
            }
            int A = allowedList.size();
            BigInteger bigA = BigInteger.valueOf(A);

            BigInteger[][] loose = new BigInteger[14][2];
            loose[0][0] = BigInteger.ZERO;
            loose[0][1] = BigInteger.ONE;
            for (int i = 1; i <= 13; ++i) {
                int j = i - 1;
                boolean allow = j == 0 || bigAllowed[j - 1];
                if (!allow) {
                    loose[i][0] = loose[i - 1][0];
                    loose[i][1] = loose[i - 1][1];
                } else {
                    loose[i][0] = loose[i - 1][0].add(loose[i - 1][1].multiply(bigA));
                    loose[i][1] = loose[i - 1][1].multiply(bigA.add(BigInteger.ONE));
                }
            }

            BigInteger total;
            boolean allowTop = bigAllowed[11];
            if (!allowTop) {
                total = loose[12][0];
            } else {
                total = loose[12][0].add(loose[12][1].multiply(bigA));
            }

            if (total.compareTo(n) < 0) {
                out.append("-1\n");
                continue;
            }

            int[] groups = new int[13];
            boolean started = false;
            for (int pos = 12; pos >= 0; --pos) {
                BigInteger zeroCnt = loose[pos][started ? 1 : 0];
                if (n.compareTo(zeroCnt) <= 0) {
                    groups[pos] = 0;
                    continue;
                }
                n = n.subtract(zeroCnt);
                boolean allowUnit = pos == 0 || bigAllowed[pos - 1];
                if (!allowUnit || A == 0) {
                    out.append("-1\n");
                    started = true;
                    break;
                }
                BigInteger block = loose[pos][1];
                BigInteger idxBI = n.subtract(BigInteger.ONE).divide(block).add(BigInteger.ONE);
                if (idxBI.compareTo(BigInteger.valueOf(A)) > 0) {
                    out.append("-1\n");
                    started = true;
                    break;
                }
                int idx = idxBI.intValue();
                groups[pos] = allowedList.get(idx - 1);
                n = n.subtract(block.multiply(BigInteger.valueOf(idx - 1)));
                started = true;
            }
            if (out.length() > 0 && out.charAt(out.length() - 1) == '\n') {
                if (out.substring(out.length() - 2).equals("-\n")) {
                    continue;
                }
            }
            if (out.length() > 0) {
                int len = out.length();
                if (len >= 2 && out.substring(len - 2).equals("-\n")) {
                    continue;
                }
            }

            BigInteger ans = BigInteger.ZERO;
            for (int i = 12; i >= 0; --i) {
                ans = ans.multiply(BASE).add(BigInteger.valueOf(groups[i]));
            }
            out.append(ans.toString()).append('\n');
        }
        System.out.print(out.toString());
    }

    private static void precomputeMasks() {
        for (int v = 1; v <= 9999; ++v) {
            GROUP_MASK[v] = maskOfString(groupString(v));
        }
        for (int i = 0; i < 12; ++i) {
            BIG_MASK[i] = maskOfString(BIG[i]);
        }
    }

    private static String groupString(int v) {
        int a = v / 1000;
        int b = (v / 100) % 10;
        int c = (v / 10) % 10;
        int d = v % 10;
        StringBuilder sb = new StringBuilder();
        if (a > 0) {
            sb.append(DIG[a]).append("천");
        }
        if (b > 0) {
            sb.append(DIG[b]).append("백");
        }
        if (c > 0) {
            sb.append(DIG[c]).append("십");
        }
        if (d > 0) {
            sb.append(DIG[d]);
        }
        return sb.toString();
    }

    private static long maskOfString(String s) {
        long m = 0L;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 0xAC00 && ch <= 0xD7A3) {
                int code = ch - 0xAC00;
                int cho = code / (21 * 28);
                int jung = (code / 28) % 21;
                int jong = code % 28;
                char cCho = mapCho(cho);
                if (cCho != 0) {
                    m |= bitOfJamo(cCho);
                }
                char cJung = mapJung(jung);
                if (cJung != 0) {
                    m |= bitOfJamo(cJung);
                }
                char[] finals = mapJong(jong);
                for (char f : finals) {
                    if (f != 0) {
                        m |= bitOfJamo(f);
                    }
                }
            }
        }
        return m;
    }

    private static char mapCho(int idx) {
        switch (idx) {
            case 0:
                return 'ㄱ';
            case 2:
                return 'ㄴ';
            case 5:
                return 'ㄹ';
            case 6:
                return 'ㅁ';
            case 7:
                return 'ㅂ';
            case 9:
                return 'ㅅ';
            case 11:
                return 'ㅇ';
            case 12:
                return 'ㅈ';
            case 14:
                return 'ㅊ';
            case 17:
                return 'ㅍ';
            case 18:
                return 'ㅎ';
            default:
                return 0;
        }
    }

    private static char mapJung(int idx) {
        switch (idx) {
            case 0:
                return 'ㅏ';
            case 1:
                return 'ㅐ';
            case 2:
                return 'ㅑ';
            case 4:
                return 'ㅓ';
            case 6:
                return 'ㅕ';
            case 8:
                return 'ㅗ';
            case 13:
                return 'ㅜ';
            case 17:
                return 'ㅠ';
            case 18:
                return 'ㅡ';
            case 20:
                return 'ㅣ';
            default:
                return 0;
        }
    }

    private static char[] mapJong(int idx) {
        switch (idx) {
            case 0:
                return new char[]{};
            case 1:
                return new char[]{'ㄱ'};
            case 4:
                return new char[]{'ㄴ'};
            case 7:
                return new char[]{'ㄷ'};
            case 8:
                return new char[]{'ㄹ'};
            case 16:
                return new char[]{'ㅁ'};
            case 17:
                return new char[]{'ㅂ'};
            case 19:
                return new char[]{'ㅅ'};
            case 21:
                return new char[]{'ㅇ'};
            case 22:
                return new char[]{'ㅈ'};
            case 23:
                return new char[]{'ㅊ'};
            case 26:
                return new char[]{'ㅍ'};
            case 27:
                return new char[]{'ㅎ'};
            case 2:
                return new char[]{};
            case 3:
                return new char[]{'ㄱ', 'ㅅ'};
            case 5:
                return new char[]{'ㄴ', 'ㅈ'};
            case 6:
                return new char[]{'ㄴ', 'ㅎ'};
            case 9:
                return new char[]{'ㄹ', 'ㄱ'};
            case 10:
                return new char[]{'ㄹ', 'ㅁ'};
            case 11:
                return new char[]{'ㄹ', 'ㅂ'};
            case 12:
                return new char[]{'ㄹ', 'ㅅ'};
            case 13:
                return new char[]{'ㄹ', 'ㅌ'};
            case 14:
                return new char[]{'ㄹ', 'ㅍ'};
            case 15:
                return new char[]{'ㄹ', 'ㅎ'};
            case 18:
                return new char[]{'ㅂ', 'ㅅ'};
            case 20:
                return new char[]{};
            case 24:
                return new char[]{};
            case 25:
                return new char[]{};
            default:
                return new char[]{};
        }
    }

    private static long bitOfJamo(char j) {
        switch (j) {
            case 'ㄱ':
                return 1L << 0;
            case 'ㄴ':
                return 1L << 1;
            case 'ㄹ':
                return 1L << 2;
            case 'ㅁ':
                return 1L << 3;
            case 'ㅂ':
                return 1L << 4;
            case 'ㅅ':
                return 1L << 5;
            case 'ㅇ':
                return 1L << 6;
            case 'ㅈ':
                return 1L << 7;
            case 'ㅊ':
                return 1L << 8;
            case 'ㅍ':
                return 1L << 9;
            case 'ㅎ':
                return 1L << 10;
            case 'ㅏ':
                return 1L << 11;
            case 'ㅐ':
                return 1L << 12;
            case 'ㅑ':
                return 1L << 13;
            case 'ㅓ':
                return 1L << 14;
            case 'ㅕ':
                return 1L << 15;
            case 'ㅗ':
                return 1L << 16;
            case 'ㅜ':
                return 1L << 17;
            case 'ㅠ':
                return 1L << 18;
            case 'ㅡ':
                return 1L << 19;
            case 'ㅣ':
                return 1L << 20;
            default:
                return 0L;
        }
    }
}
