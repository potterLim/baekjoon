#include <stdio.h>
#include <stdlib.h>

#define NUM_TIMES 5

typedef struct time_info {
    int hour;
    int minute;
    double angle;
} time_info_t;

double calculate_angle(int hour, int minute) 
{
    double hour_angle = (hour % 12) * 30.0 + minute * 0.5;
    double minute_angle = minute * 6.0;
    double angle = hour_angle - minute_angle;

    if (angle < 0) {
        angle = -angle;
    }
    if (angle > 180.0) {
        angle = 360.0 - angle;
    }

    return angle;
}

int compare_times(const void* time1, const void* time2) 
{
    const time_info_t* time_a = (const time_info_t*)time1;
    const time_info_t* time_b = (const time_info_t*)time2;
    int result = 0;

    if (time_a->angle < time_b->angle) {
        result = -1;
    } else if (time_a->angle > time_b->angle) {
        result = 1;
    } else {
        if (time_a->hour < time_b->hour) {
            result = -1;
        } else if (time_a->hour > time_b->hour) {
            result = 1;
        } else {
            result = time_a->minute - time_b->minute;
        }
    }

    return result;
}

int main(void) 
{
    int num_cases;
    size_t i;
    size_t j;

    scanf("%d", &num_cases);

    for (i = 0; i < num_cases; ++i) {
        time_info_t times[NUM_TIMES];

        for (j = 0; j < NUM_TIMES; ++j) {
            scanf("%d:%d", &times[j].hour, &times[j].minute);
            times[j].angle = calculate_angle(times[j].hour, times[j].minute);
        }

        qsort(times, NUM_TIMES, sizeof(time_info_t), compare_times);

        printf("%02d:%02d\n", times[2].hour, times[2].minute);
    }

    return 0;
}
