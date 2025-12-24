#include <math.h>
#include <stdio.h>

static double get_min_double(double a, double b)
{
    if (a < b) {
        return a;
    }

    return b;
}

static double get_max_double(double a, double b)
{
    if (a > b) {
        return a;
    }

    return b;
}

int main(void)
{
    int x;
    int y;
    int d;
    int t;

    double dist;
    double answer;

    int k;
    double time_walk;
    double time_k;
    double time_k_plus_1;

    scanf("%d %d %d %d", &x, &y, &d, &t);

    dist = sqrt((double)x * (double)x + (double)y * (double)y);

    answer = dist;

    if (dist < (double)d) {
        answer = get_min_double(answer, (double)t + ((double)d - dist));
        answer = get_min_double(answer, 2.0 * (double)t);
    } else {
        k = (int)(dist / (double)d);

        time_k = (double)k * (double)t + get_max_double(0.0, dist - (double)k * (double)d);
        answer = get_min_double(answer, time_k);

        time_k_plus_1 = (double)(k + 1) * (double)t + get_max_double(0.0, dist - (double)(k + 1) * (double)d);
        answer = get_min_double(answer, time_k_plus_1);

        answer = get_min_double(answer, (double)(k + 2) * (double)t);
    }

    printf("%.10f\n", answer);

    return 0;
}
