#include <stdio.h>
#include <stdlib.h>

typedef struct point{
    int x;
    int y;
} point_t;

int calculateMaxDifference(int N, point_t *points);

int main(void) 
{
    int N;
    int i;
    scanf("%d", &N);

    point_t* points = malloc(sizeof(point_t) * N);

    for (i = 0; i < N; ++i) {
        scanf("%d %d", &(points[i].x), &(points[i].y));
    }

    printf("%d\n", calculateMaxDifference(N, points));

    free(points);
    return 0;
}

int calculateMaxDifference(int N, point_t *points) 
{
    int result1;
    int result2;
    int i;
    int maxSum = points[0].x + points[0].y;
    int minSum = points[0].x + points[0].y;

    for (i = 1; i < N; ++i) {
        int sum = points[i].x + points[i].y;
        if(sum > maxSum) {
            maxSum = sum;
        }
        if(sum < minSum) {
            minSum = sum;
        }
    }
    result1 = maxSum - minSum;

    int maxDiff = points[0].x - points[0].y;
    int minDiff = points[0].x - points[0].y;

    for (i = 1; i < N; ++i) {
        int diff = points[i].x - points[i].y;
        if(diff > maxDiff) {
            maxDiff = diff;
        }
        if(diff < minDiff) {
            minDiff = diff;
        }
    }
    result2 = maxDiff - minDiff;

    return (result1 > result2) ? result1 : result2;
}
