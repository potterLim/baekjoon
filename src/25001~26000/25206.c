#include <stdio.h>
#include <string.h>

typedef struct subject {
    char name[51];
    float credit;
    char grade[3];
} subject_t;

float grade_to_point(const char* grade) 
{
    if (strcmp(grade, "A+") == 0) {
        return 4.5f;
    }
    if (strcmp(grade, "A0") == 0) {
        return 4.0f;
    }
    if (strcmp(grade, "B+") == 0) {
        return 3.5f;
    }
    if (strcmp(grade, "B0") == 0) {
        return 3.0f;
    }
    if (strcmp(grade, "C+") == 0) {
        return 2.5f;
    }
    if (strcmp(grade, "C0") == 0) {
        return 2.0f;
    }
    if (strcmp(grade, "D+") == 0) {
        return 1.5f;
    }
    if (strcmp(grade, "D0") == 0) {
        return 1.0f;
    }
    if (strcmp(grade, "F") == 0) {
        return 0.0f;
    }
    return 0.0f;
}

int main(void) 
{
    subject_t subjects[20];
    size_t i;
    float total_credits = 0.0f;
    float weighted_sum = 0.0f;
    float grade_point;

    for (i = 0; i < 20; ++i) {
        scanf("%50s %f %2s", subjects[i].name, &subjects[i].credit, subjects[i].grade);

        if (strcmp(subjects[i].grade, "P") == 0) {
            continue;
        }

        grade_point = grade_to_point(subjects[i].grade);

        weighted_sum += subjects[i].credit * grade_point;
        total_credits += subjects[i].credit;
    }

    printf("%.6f\n", weighted_sum / total_credits);

    return 0;
}
