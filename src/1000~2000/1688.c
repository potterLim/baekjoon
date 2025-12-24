#include <stdio.h>

typedef struct point {
    long long x;
    long long y;
} point_t;

static long long get_cross(const point_t* a, const point_t* b, const point_t* c)
{
    long long ab_x;
    long long ab_y;
    long long ac_x;
    long long ac_y;

    ab_x = b->x - a->x;
    ab_y = b->y - a->y;
    ac_x = c->x - a->x;
    ac_y = c->y - a->y;

    return ab_x * ac_y - ab_y * ac_x;
}

static int is_between_inclusive(long long a, long long b, long long value)
{
    long long min_v;
    long long max_v;

    min_v = a;
    max_v = b;

    if (min_v > max_v) {
        long long temp;

        temp = min_v;
        min_v = max_v;
        max_v = temp;
    }

    if (value < min_v) {
        return 0;
    }

    if (value > max_v) {
        return 0;
    }

    return 1;
}

static int is_point_on_segment(const point_t* a, const point_t* b, const point_t* p)
{
    long long cross_value;

    cross_value = get_cross(a, b, p);

    if (cross_value != 0) {
        return 0;
    }

    if (!is_between_inclusive(a->x, b->x, p->x)) {
        return 0;
    }

    if (!is_between_inclusive(a->y, b->y, p->y)) {
        return 0;
    }

    return 1;
}

static int is_point_inside_or_on_polygon(const point_t* polygon, int n, const point_t* p)
{
    int i;
    int j;
    int inside;

    inside = 0;
    j = n - 1;

    for (i = 0; i < n; ++i) {
        const point_t* a;
        const point_t* b;

        a = &polygon[j];
        b = &polygon[i];

        if (is_point_on_segment(a, b, p)) {
            return 1;
        }

        if ((a->y > p->y) != (b->y > p->y)) {
            long double intersection_x;

            intersection_x = (long double)(b->x - a->x) * (long double)(p->y - a->y) / (long double)(b->y - a->y) + (long double)a->x;

            if ((long double)p->x <= intersection_x) {
                inside = !inside;
            }
        }

        j = i;
    }

    return inside;
}

int main(void)
{
    int n;
    point_t polygon[10000];
    point_t targets[3];

    int i;

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        scanf("%lld %lld", &polygon[i].x, &polygon[i].y);
    }

    for (i = 0; i < 3; ++i) {
        scanf("%lld %lld", &targets[i].x, &targets[i].y);
    }

    for (i = 0; i < 3; ++i) {
        if (is_point_inside_or_on_polygon(polygon, n, &targets[i])) {
            printf("1\n");
        } else {
            printf("0\n");
        }
    }

    return 0;
}
