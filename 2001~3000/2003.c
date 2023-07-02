#include <stdio.h>

int main(void)
{
    int totalNumbers;
    int targetSum;
    int i;
    int j;
    int count = 0;
    int numbers[10002];

    scanf("%d %d", &totalNumbers, &targetSum);

    for (i = 0; i < totalNumbers; i++) {
        scanf("%d", &numbers[i]);
    }

    for (i = 0; i < totalNumbers; i++) {
        int currentSum = numbers[i];

        for (j = i + 1; currentSum < targetSum; j++) {
            currentSum += numbers[j];
        }

        if (currentSum == targetSum) {
            count++;
        }
    }

    printf("%d", count);
    return 0;
}