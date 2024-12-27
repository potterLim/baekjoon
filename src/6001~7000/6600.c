#include <stdio.h>
#include <math.h>

#define PI 3.141592653589793

double calculate_circle(double x1, double y1, double x2, double y2, double x3, double y3) 
{
    double d;
    double ux;
    double uy;
    double r;
    double circumference;

    d = 2 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));

    ux = ((x1 * x1 + y1 * y1) * (y2 - y3) + 
          (x2 * x2 + y2 * y2) * (y3 - y1) +
          (x3 * x3 + y3 * y3) * (y1 - y2)) / d;
    uy = ((x1 * x1 + y1 * y1) * (x3 - x2) + 
          (x2 * x2 + y2 * y2) * (x1 - x3) +
          (x3 * x3 + y3 * y3) * (x2 - x1)) / d;

    r = sqrt((ux - x1) * (ux - x1) + (uy - y1) * (uy - y1));
    circumference = 2 * PI * r;

    return circumference;
}

int main(void) 
{
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;

    double circle;

    while (scanf("%lf %lf %lf %lf %lf %lf", &x1, &y1, &x2, &y2, &x3, &y3) == 6) {
        circle = calculate_circle(x1, y1, x2, y2, x3, y3);
        printf("%.2f\n", circle);
    }

    return 0;
}
