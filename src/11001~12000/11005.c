#include <stdio.h>

#define MAX_LEN 32

int convert_int_to_base(int n, int b, char result[]) 
{
    int index;
    int remainder;
    int i;
    int j;
    char temp;

    index = 0;

    while (n > 0) {
        remainder = n % b;
        if (remainder < 10) {
            result[index] = '0' + remainder;
        } else {
            result[index] = 'A' + (remainder - 10);
        }
        n = n / b;
        index++;
    }

    result[index] = '\0';

    i = 0;
    j = index - 1;

    while (i < j) {
        temp = result[i];
        result[i] = result[j];
        result[j] = temp;
        i++;
        j--;
    }

    return index;
}

int main(void) 
{
    int n;
    int b;
    char result[MAX_LEN + 1];
    int length;
    
    scanf("%d %d", &n, &b);
    
    length = convert_int_to_base(n, b, result);
    
    printf("%s\n", result);

    return 0;
}
