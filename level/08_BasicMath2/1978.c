#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int n;
    int count = 0;
    int check = 0;
    int i;
    int j;
    scanf("%d", &n);
    int* nums = malloc(sizeof(int) * count);

    for (i = 0; i < n; i++) {
        scanf("%d", &nums[i]);
    }

    for (i = 0; i < n; ++i) {
        for (j = 2; j < nums[i]; ++j) {
            if (nums[i] % j ==0) {
                check = 1;
            }
        }

        if (nums[i] != 1 && check == 0) {
            count++;
        }

        check = 0;
    }

    printf("%d", count);
    return 0;
}