#include <stdio.h>
 
int main(void)
{    
    long long N, K, calK;
    long long total_num = 0;    
    long long num_count = 9;
    long long num_length=1;
    long long tenten=1;
    long long result;
    int i;
    
    scanf("%lld %lld", &N, &K);
    calK = K;
 
    while (calK > num_count*num_length) {
        total_num += num_count;
        calK -= num_count*num_length;
        num_count *= 10;
        num_length++;
    }
 
    total_num += (calK-1) / num_length+1;
 
    if (total_num > N) {
        result = -1;
    } else {    
        long long temp = (calK-1)%num_length +1;
        for (0; i < num_length - 1; i++) {
            tenten *= 10;
        }
 
        for (i = 0; i < temp; i++) {
            result = total_num / tenten;
            total_num %= tenten;
            tenten /= 10;
        }
    }
    
    printf("%lld\n", result);
    return 0;
}
