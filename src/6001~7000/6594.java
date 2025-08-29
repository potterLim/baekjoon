import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

final class Main {
    private static final class Linear {
        public final double coefX;
        public final double constant;

        public Linear(double coefX, double constant) {
            this.coefX = coefX;
            this.constant = constant;
        }
    }

    private static final class Parser {
        private final String src;
        private int pos;

        public Parser(String s) {
            this.src = s;
            this.pos = 0;
        }

        public Linear parseExpression() {
            Linear value = parseTerm();
            while (true) {
                skipSpaces();
                if (match('+')) {
                    Linear rhs = parseTerm();
                    value = new Linear(value.coefX + rhs.coefX, value.constant + rhs.constant);
                } else if (match('-')) {
                    Linear rhs = parseTerm();
                    value = new Linear(value.coefX - rhs.coefX, value.constant - rhs.constant);
                } else {
                    break;
                }
            }
            return value;
        }

        private Linear parseTerm() {
            Linear value = parseFactor();
            while (true) {
                skipSpaces();
                if (match('*')) {
                    Linear rhs = parseFactor();
                    if (value.coefX != 0.0 && rhs.coefX != 0.0) {
                        throw new IllegalArgumentException("Non-linear term encountered");
                    }
                    double newCoefX = value.coefX * rhs.constant + rhs.coefX * value.constant;
                    double newConstant = value.constant * rhs.constant;
                    value = new Linear(newCoefX, newConstant);
                } else {
                    break;
                }
            }
            return value;
        }

        private Linear parseFactor() {
            skipSpaces();
            if (match('(')) {
                Linear inside = parseExpression();
                expect(')');
                return inside;
            }
            if (peek() == 'x') {
                pos++;
                return new Linear(1.0, 0.0);
            }
            long number = parseNumber();
            return new Linear(0.0, (double) number);
        }

        private long parseNumber() {
            skipSpaces();
            if (pos >= src.length() || !Character.isDigit(src.charAt(pos))) {
                throw new IllegalArgumentException("Expected number");
            }
            long value = 0L;
            while (pos < src.length() && Character.isDigit(src.charAt(pos))) {
                value = value * 10 + (src.charAt(pos) - '0');
                pos++;
            }
            return value;
        }

        private void expect(char ch) {
            skipSpaces();
            if (!match(ch)) {
                throw new IllegalArgumentException("Expected '" + ch + "'");
            }
        }

        private boolean match(char ch) {
            if (pos < src.length() && src.charAt(pos) == ch) {
                pos++;
                return true;
            }
            return false;
        }

        private char peek() {
            skipSpaces();
            if (pos < src.length()) {
                return src.charAt(pos);
            }
            return '\0';
        }

        private void skipSpaces() {
            while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) {
                pos++;
            }
        }
    }

    private static Linear parseSide(String s) {
        Parser p = new Parser(s);
        Linear lin = p.parseExpression();
        return lin;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int caseIndex = 1;
        StringBuilder out = new StringBuilder();
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            int eqPos = line.indexOf('=');
            String left = line.substring(0, eqPos);
            String right = line.substring(eqPos + 1);

            Linear L = parseSide(left);
            Linear R = parseSide(right);

            double a = L.coefX - R.coefX;
            double c = R.constant - L.constant;

            out.append("Equation #").append(caseIndex++).append('\n');

            if (Math.abs(a) < 1e-12) {
                if (Math.abs(c) < 1e-12) {
                    out.append("Infinitely many solutions.\n\n");
                } else {
                    out.append("No solution.\n\n");
                }
            } else {
                double x = c / a;
                out.append(String.format("x = %.6f%n%n", x));
            }
        }
        System.out.print(out.toString());
    }
}
