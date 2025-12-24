#include <math.h>
#include <stdio.h>

static double clamp(double value, double low, double high)
{
    if (value < low) {
        return low;
    }

    if (value > high) {
        return high;
    }

    return value;
}

int main(void)
{
    double x1;
    double y1;
    double r1;
    double x2;
    double y2;
    double r2;

    double dx;
    double dy;
    double d;

    double r_small;
    double r_large;

    double area;
    double pi;

    scanf("%lf %lf %lf %lf %lf %lf", &x1, &y1, &r1, &x2, &y2, &r2);

    dx = x2 - x1;
    dy = y2 - y1;
    d = sqrt(dx * dx + dy * dy);

    pi = acos(-1.0);

    r_small = r1;
    r_large = r2;

    if (r_small > r_large) {
        double temp;

        temp = r_small;
        r_small = r_large;
        r_large = temp;
    }

    area = 0.0;

    if (d >= r1 + r2) {
        area = 0.0;
    } else if (d <= r_large - r_small) {
        area = pi * r_small * r_small;
    } else {
        double cos_alpha;
        double cos_beta;
        double alpha;
        double beta;

        cos_alpha = (d * d + r1 * r1 - r2 * r2) / (2.0 * d * r1);
        cos_beta = (d * d + r2 * r2 - r1 * r1) / (2.0 * d * r2);

        cos_alpha = clamp(cos_alpha, -1.0, 1.0);
        cos_beta = clamp(cos_beta, -1.0, 1.0);

        alpha = 2.0 * acos(cos_alpha);
        beta = 2.0 * acos(cos_beta);

        area = 0.5 * r1 * r1 * (alpha - sin(alpha)) + 0.5 * r2 * r2 * (beta - sin(beta));
    }

    printf("%.3f\n", area);

    return 0;
}
