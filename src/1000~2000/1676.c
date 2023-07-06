#include <stdio.h>

int main(void) {
    int i;
    int N;
    int count_have_five = 0;

    scanf("%d", &N);

    for (i = 1; i<= N; i++) {
        if(i % 125 == 0) {
            count_have_five += 3;
        } else if (i % 25 == 0) {
            count_have_five +=2;
        } else if (i % 5 == 0) {
            count_have_five +=1;
        }
    }

    printf("%d", count_have_five);
    return 0;
}
