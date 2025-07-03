#include <stdio.h>

int main(void)
{
    int num_test_cases;
    int num_stations;
    int i;
    int initial_passengers;

    scanf("%d", &num_test_cases);

    while (num_test_cases-- > 0) {
        scanf("%d", &num_stations);

        initial_passengers = 0;

        for (i = 0; i < num_stations; ++i) {
            initial_passengers = 2 * initial_passengers + 1;
        }

        printf("%d\n", initial_passengers);
    }

    return 0;
}
