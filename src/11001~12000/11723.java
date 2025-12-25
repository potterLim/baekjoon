import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static final int ALL_MASK = (1 << 20) - 1;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int m = scanner.nextInt();

        int mask = 0;
        StringBuilder output = new StringBuilder(1 << 20);

        for (int i = 0; i < m; ++i) {
            int command = scanner.nextCommand();

            if (command == Command.ADD) {
                int x = scanner.nextInt();
                mask |= (1 << (x - 1));
            } else if (command == Command.REMOVE) {
                int x = scanner.nextInt();
                mask &= ~(1 << (x - 1));
            } else if (command == Command.CHECK) {
                int x = scanner.nextInt();
                int bit = (mask >>> (x - 1)) & 1;
                output.append(bit).append('\n');
            } else if (command == Command.TOGGLE) {
                int x = scanner.nextInt();
                mask ^= (1 << (x - 1));
            } else if (command == Command.ALL) {
                mask = ALL_MASK;
            } else if (command == Command.EMPTY) {
                mask = 0;
            }
        }

        System.out.print(output.toString());
    }

    private static final class Command {
        private static final int ADD = 1;
        private static final int REMOVE = 2;
        private static final int CHECK = 3;
        private static final int TOGGLE = 4;
        private static final int ALL = 5;
        private static final int EMPTY = 6;
    }

    private static final class FastScanner {
        private final BufferedInputStream in;

        public FastScanner() {
            this.in = new BufferedInputStream(System.in, 1 << 16);
        }

        public int nextInt() throws IOException {
            int c = this.readNonSpace();
            int value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = this.in.read();
            }

            return value;
        }

        public int nextCommand() throws IOException {
            int c = this.readNonSpace();

            if (c == 'a') {
                int c2 = this.in.read();
                if (c2 == 'd') {
                    this.skipRestOfWord();
                    return Command.ADD;
                }

                this.skipRestOfWord();
                return Command.ALL;
            } else if (c == 'r') {
                this.skipRestOfWord();
                return Command.REMOVE;
            } else if (c == 'c') {
                this.skipRestOfWord();
                return Command.CHECK;
            } else if (c == 't') {
                this.skipRestOfWord();
                return Command.TOGGLE;
            } else {
                this.skipRestOfWord();
                return Command.EMPTY;
            }
        }

        private int readNonSpace() throws IOException {
            int c = this.in.read();
            while (c <= ' ') {
                c = this.in.read();
            }

            return c;
        }

        private void skipRestOfWord() throws IOException {
            int c = this.in.read();
            while (c > ' ') {
                c = this.in.read();
            }
        }
    }
}
