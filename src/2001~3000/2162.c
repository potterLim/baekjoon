#include <stdio.h>

typedef struct point {
    long long x;
    long long y;
} point_t;

typedef struct line {
    point_t start;
    point_t end;
} line_t;

static long get_orientation(const point_t* a, const point_t* b, const point_t* c)
{
    long long cross_value;
    long long ab_x;
    long long ab_y;
    long long ac_x;
    long long ac_y;

    ab_x = b->x - a->x;
    ab_y = b->y - a->y;
    ac_x = c->x - a->x;
    ac_y = c->y - a->y;

    cross_value = ab_x * ac_y - ab_y * ac_x;

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

static void get_sorted_endpoints(const line_t* line, point_t* out_min_point, point_t* out_max_point)
{
    point_t temp;

    *out_min_point = line->start;
    *out_max_point = line->end;

    if (compare_points_lex(out_max_point, out_min_point) < 0) {
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
    long o1;
    long o2;
    long o3;
    long o4;

    o1 = get_orientation(&first_line->start, &first_line->end, &second_line->start);
    o2 = get_orientation(&first_line->start, &first_line->end, &second_line->end);
    o3 = get_orientation(&second_line->start, &second_line->end, &first_line->start);
    o4 = get_orientation(&second_line->start, &second_line->end, &first_line->end);

    if (o1 == 0 && o2 == 0) {
        return is_collinear_overlap(first_line, second_line);
    }

    if (o1 * o2 <= 0 && o3 * o4 <= 0) {
        return 1;
    }

    return 0;
}

static int find_root(int* parent, int node)
{
    int root;
    int current;
    int next;

    root = node;
    while (parent[root] != root) {
        root = parent[root];
    }

    current = node;
    while (parent[current] != current) {
        next = parent[current];
        parent[current] = root;
        current = next;
    }

    return root;
}

static void union_sets(int* parent, int* set_size, int a, int b)
{
    int root_a;
    int root_b;

    root_a = find_root(parent, a);
    root_b = find_root(parent, b);

    if (root_a == root_b) {
        return;
    }

    if (set_size[root_a] < set_size[root_b]) {
        int temp;

        temp = root_a;
        root_a = root_b;
        root_b = temp;
    }

    parent[root_b] = root_a;
    set_size[root_a] += set_size[root_b];
}

int main(void)
{
    int n;
    line_t lines[3000];
    int parent[3000];
    int set_size[3000];

    int i;
    int j;

    int group_count;
    int max_group_size;

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        scanf("%lld %lld %lld %lld", &lines[i].start.x, &lines[i].start.y, &lines[i].end.x, &lines[i].end.y);
        parent[i] = i;
        set_size[i] = 1;
    }

    for (i = 0; i < n; ++i) {
        for (j = i + 1; j < n; ++j) {
            if (is_lines_intersect(&lines[i], &lines[j])) {
                union_sets(parent, set_size, i, j);
            }
        }
    }

    group_count = 0;
    max_group_size = 0;

    for (i = 0; i < n; ++i) {
        if (find_root(parent, i) == i) {
            group_count += 1;
            if (set_size[i] > max_group_size) {
                max_group_size = set_size[i];
            }
        }
    }

    printf("%d\n", group_count);
    printf("%d\n", max_group_size);

    return 0;
}
