#include stdio.h

int main(void)
{
    int sum = 0;
    int m;
    int n;
    int min = 10001;
    int count = 0;
    int check = 0;
    int i;
    int j;

    scanf(%d, &m);
    scanf(%d, &n);

    for (i = m; i = n; ++i) {
        for (j = 2; j  i; ++j) {
            if (i % j ==0) {
                check = 1;
            }
        }

        if (i != 1 && check == 0) {
            if (min  i) {
                min = i;
            }
            
            count++;
            sum+=i;
        }

        check = 0;
    }

    if (count == 0) {
        printf(-1n);
    } else {
        printf(%dn, sum);
        printf(%dn, min);
    }
    return 0;
}
