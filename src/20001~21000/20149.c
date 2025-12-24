#include <assert.h>
#include <stdio.h>

typedef struct point {
    long long x;
    long long y;
} point_t;

typedef struct line {
    point_t start;
    point_t end;
} line_t;

static long get_orientation(const line_t* line, const point_t* point)
{
    long long dx1;
    long long dy1;
    long long dx2;
    long long dy2;
    long long cross_value;

    dx1 = line->end.x - line->start.x;
    dy1 = line->end.y - line->start.y;
    dx2 = point->x - line->start.x;
    dy2 = point->y - line->start.y;

    cross_value = dx1 * dy2 - dy1 * dx2;

    if (cross_value > 0) {
        return 1;
    }

    if (cross_value < 0) {
        return -1;
    }

    return 0;
}

static int compare_points_lex(const point_t* left, const point_t* right)
{
    if (left->x < right->x) {
        return -1;
    }

    if (left->x > right->x) {
        return 1;
    }

    if (left->y < right->y) {
        return -1;
    }

    if (left->y > right->y) {
        return 1;
    }

    return 0;
}

static void get_sorted_endpoints(const line_t* line, point_t* const out_min_point, point_t* const out_max_point)
{
    assert(line != NULL);
    assert(out_min_point != NULL);
    assert(out_max_point != NULL);

    *out_min_point = line->start;
    *out_max_point = line->end;

    if (compare_points_lex(out_max_point, out_min_point) < 0) {
        point_t temp;

        temp = *out_min_point;
        *out_min_point = *out_max_point;
        *out_max_point = temp;
    }
}

static int is_collinear_overlap(const line_t* first_line, const line_t* second_line)
{
    point_t first_min;
    point_t first_max;
    point_t second_min;
    point_t second_max;

    get_sorted_endpoints(first_line, &first_min, &first_max);
    get_sorted_endpoints(second_line, &second_min, &second_max);

    if (compare_points_lex(&first_max, &second_min) < 0) {
        return 0;
    }

    if (compare_points_lex(&second_max, &first_min) < 0) {
        return 0;
    }

    return 1;
}

static int is_lines_intersect(const line_t* first_line, const line_t* second_line)
{
    long first_start_orientation;
    long first_end_orientation;
    long second_start_orientation;
    long second_end_orientation;

    first_start_orientation = get_orientation(first_line, &second_line->start);
    first_end_orientation = get_orientation(first_line, &second_line->end);
    second_start_orientation = get_orientation(second_line, &first_line->start);
    second_end_orientation = get_orientation(second_line, &first_line->end);

    if (first_start_orientation == 0 && first_end_orientation == 0) {
        return is_collinear_overlap(first_line, second_line);
    }

    if (first_start_orientation * first_end_orientation <= 0 &&
        second_start_orientation * second_end_orientation <= 0) {
        return 1;
    }

    return 0;
}

static int is_same_point(const point_t* left, const point_t* right)
{
    return (left->x == right->x && left->y == right->y) ? 1 : 0;
}

static int get_single_intersection_point_collinear(const line_t* first_line, const line_t* second_line, point_t* const out_point)
{
    point_t first_min;
    point_t first_max;
    point_t second_min;
    point_t second_max;
    point_t overlap_start;
    point_t overlap_end;

    assert(out_point != NULL);

    get_sorted_endpoints(first_line, &first_min, &first_max);
    get_sorted_endpoints(second_line, &second_min, &second_max);

    overlap_start = first_min;
    if (compare_points_lex(&overlap_start, &second_min) < 0) {
        overlap_start = second_min;
    }

    overlap_end = first_max;
    if (compare_points_lex(&second_max, &overlap_end) < 0) {
        overlap_end = second_max;
    }

    if (is_same_point(&overlap_start, &overlap_end)) {
        *out_point = overlap_start;
        return 1;
    }

    return 0;
}

static void compute_intersection_point_non_collinear(const line_t* first_line, const line_t* second_line, long double* const out_x, long double* const out_y)
{
    long double x1;
    long double y1;
    long double x2;
    long double y2;
    long double x3;
    long double y3;
    long double x4;
    long double y4;
    long double denominator;
    long double det12;
    long double det34;

    assert(out_x != NULL);
    assert(out_y != NULL);

    x1 = (long double)first_line->start.x;
    y1 = (long double)first_line->start.y;
    x2 = (long double)first_line->end.x;
    y2 = (long double)first_line->end.y;
    x3 = (long double)second_line->start.x;
    y3 = (long double)second_line->start.y;
    x4 = (long double)second_line->end.x;
    y4 = (long double)second_line->end.y;

    denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
    det12 = x1 * y2 - y1 * x2;
    det34 = x3 * y4 - y3 * x4;

    *out_x = (det12 * (x3 - x4) - (x1 - x2) * det34) / denominator;
    *out_y = (det12 * (y3 - y4) - (y1 - y2) * det34) / denominator;
}

int main(void)
{
    line_t line1;
    line_t line2;
    int intersects;
    long orientation_start;
    long orientation_end;
    point_t intersection_point;
    long double intersection_x;
    long double intersection_y;

    scanf("%lld %lld %lld %lld", &line1.start.x, &line1.start.y, &line1.end.x, &line1.end.y);
    scanf("%lld %lld %lld %lld", &line2.start.x, &line2.start.y, &line2.end.x, &line2.end.y);

    intersects = is_lines_intersect(&line1, &line2);

    if (intersects == 0) {
        printf("0\n");
        return 0;
    }

    printf("1\n");

    orientation_start = get_orientation(&line1, &line2.start);
    orientation_end = get_orientation(&line1, &line2.end);

    if (orientation_start == 0 && orientation_end == 0) {
        if (get_single_intersection_point_collinear(&line1, &line2, &intersection_point)) {
            printf("%.15Lf %.15Lf\n", (long double)intersection_point.x, (long double)intersection_point.y);
        }
    } else {
        compute_intersection_point_non_collinear(&line1, &line2, &intersection_x, &intersection_y);
        printf("%.15Lf %.15Lf\n", intersection_x, intersection_y);
    }

    return 0;
}
