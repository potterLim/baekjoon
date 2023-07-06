#include <stdio.h>
#include <string.h>

int main(void)
{
    char jaehwan_ah[1001];
    char doctor_ah[1001];

    scanf("%s", jaehwan_ah);
    scanf("%s", doctor_ah);

    (strlen(jaehwan_ah) >= strlen(doctor_ah)) ? printf("go\n") : printf("no\n");

    return 0;
}
