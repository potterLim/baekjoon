#include <stdio.h>

int main(void)
{
    int numerator;
    int denominator;
    int digit;
    int result;
    int i;

    scanf("%d %d %d", &numerator, &denominator, &digit);
    for (i = 0; i < digit; i++)		//n번째 자리수를 구하기 위해서 n번을 반복
	{
		numerator %= denominator;
		numerator *= 10;
		result = numerator / denominator;
	}

    printf("%d\n", result);
    return 0;
}
