#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int compare_digits(const void* left, const void* right)
{
    return (*(char*)left - *(char*)right);
}

void get_sorted_digits(int number, char* sorted_digits)
{
    sprintf(sorted_digits, "%d", number);
    qsort(sorted_digits, strlen(sorted_digits), sizeof(char), compare_digits);
}

int is_vampire_number(int number)
{
    char number_digits[12];
    char product_digits[12];
    size_t i;
    
    get_sorted_digits(number, number_digits);
    
    for (i = 1; (i * i) <= number; ++i) {
        if (number % i == 0) {
            int factor_a = i;
            int factor_b = number / factor_a;
            
            if (factor_a % 10 == 0 && factor_b % 10 == 0) {
                continue;
            }
            
            sprintf(product_digits, "%d%d", factor_a, factor_b);
            qsort(product_digits, strlen(product_digits), sizeof(char), compare_digits);
            
            if (strcmp(number_digits, product_digits) == 0) {
                return 1;
            }
        }
    }
    
    return 0;
}

int main(void)
{
    int input_number;
    
    while (1) {
        scanf("%d", &input_number);
        
        if (input_number == 0) {
            break;
        }
        
        while (!is_vampire_number(input_number)) {
            ++input_number;
        }
        
        printf("%d\n", input_number);
    }
    
    return 0;
}
