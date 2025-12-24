#include <stdio.h>

typedef struct point {
    int x;
    int y;
} point_t;

typedef struct line {
    point_t start;
    point_t end;
} line_t;

long get_orientation(const line_t* line, const point_t* point)
{
    long cross_value;

    cross_value = (long)(line->end.x - line->start.x) * (point->y - line->start.y) - (long)(line->end.y - line->start.y) * (point->x - line->start.x);

    if (cross_value > 0) {
        return 1;
    }

    if (cross_value < 0) {
        return -1;
    }

    return 0;
}

int is_collinear_overlap(const line_t* first_line, const line_t* second_line)
{
    int first_min_x;
    int first_max_x;
    int second_min_x;
    int second_max_x;

    int first_min_y;
    int first_max_y;
    int second_min_y;
    int second_max_y;

    first_min_x = first_line->start.x;
    first_max_x = first_line->end.x;
    second_min_x = second_line->start.x;
    second_max_x = second_line->end.x;

    first_min_y = first_line->start.y;
    first_max_y = first_line->end.y;
    second_min_y = second_line->start.y;
    second_max_y = second_line->end.y;

    if (first_min_x > first_max_x) {
        int temp;

        temp = first_min_x;
        first_min_x = first_max_x;
        first_max_x = temp;
    }

    if (second_min_x > second_max_x) {
        int temp;

        temp = second_min_x;
        second_min_x = second_max_x;
        second_max_x = temp;
    }

    if (first_min_y > first_max_y) {
        int temp;

        temp = first_min_y;
        first_min_y = first_max_y;
        first_max_y = temp;
    }

    if (second_min_y > second_max_y) {
        int temp;

        temp = second_min_y;
        second_min_y = second_max_y;
        second_max_y = temp;
    }

    if (first_max_x < second_min_x || second_max_x < first_min_x) {
        return 0;
    }

    if (first_max_y < second_min_y || second_max_y < first_min_y) {
        return 0;
    }

    return 1;
}

int is_lines_intersect(const line_t* first_line, const line_t* second_line)
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

    if (first_start_orientation * first_end_orientation <= 0 && second_start_orientation * second_end_orientation <= 0) {
        return 1;
    }

    return 0;
}

int main(void)
{
    line_t first_line;
    line_t second_line;

    scanf("%d %d %d %d", &first_line.start.x, &first_line.start.y, &first_line.end.x, &first_line.end.y);
    scanf("%d %d %d %d", &second_line.start.x, &second_line.start.y, &second_line.end.x, &second_line.end.y);

    if (is_lines_intersect(&first_line, &second_line)) {
        printf("1\n");
    } else {
        printf("0\n");
    }

    return 0;
}
