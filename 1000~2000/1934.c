#include <stdio.h>

int main(void)
{
    int N;
    int num1;
    int num2;
    int i;
    int j;
    int k;

    scanf("%d", &N);
    for (i = 0; i < N; ++i) {
        scanf("%d %d", &num1, &num2);

        k = (num1 < num2) ? num1 : num2;
        while(k) {
            if (k == 0 || (num1 % k == 0 && num2 % k == 0)) {
                break;
            }
            k--;
        }

        printf("%d\n", num1 * num2 / k);
    }

    return 0;
}