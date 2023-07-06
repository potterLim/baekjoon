#include <stdio.h>
#include <stdlib.h>

typedef struct person {
    int height;
    int weight;
    double bmi;
    int physical_rate;
} t_person;

int main(void)
{
    t_person* person;
    int T;
    int i;

    scanf("%d", &T);

    person = malloc(sizeof(t_person) * T);
    for (i = 0; i <T; ++i) {
        scanf("%d %d", &person[i].height, &person[i].weight);

        if (person[i].height >= 204) {
            person[i].physical_rate = 4;
        } else if (person[i].height >= 161) {
            person[i].bmi = person[i].weight / ((person[i].height / 100.0) * person[i].height / 100.0);
            if (person[i].bmi >= 20 && person[i].bmi < 25) {
                person[i].physical_rate = 1;
            } else if (person[i].bmi >= 18.5 && person[i].bmi < 30) {
                person[i].physical_rate = 2;
            } else if (person[i].bmi >= 16 && person[i].bmi < 35) {
                person[i].physical_rate = 3;
            } else {
                person[i].physical_rate = 4;
            }
        } else if (person[i].height >= 159) {
            person[i].bmi = person[i].weight / ((person[i].height / 100.0) * person[i].height / 100.0);

            if (person[i].bmi >= 16 && person[i].bmi < 35) {
                person[i].physical_rate = 3;
            } else {
                person[i].physical_rate = 4;
            }
        } else if (person[i].height >= 146) {
            person[i].physical_rate = 4;
        } else if (person[i].height >= 140.1) {
            person[i].physical_rate = 5;
        } else {
            person[i].physical_rate = 6;
        }

        printf("%d\n", person[i].physical_rate);
    }
    
    free(person);
    return 0;
}
