#include <stdio.h>

int is_leapyear(int year);

int main(void)
{
    int year;
    int month;
    int day;
    int dday_year;
    int dday_month;
    int dday_day;

    scanf("%d %d %d", &year, &month, &day);
    scanf("%d %d %d", &dday_year, &dday_month, &dday_day);

    if (dday_year - year > 1000 || (dday_year - year == 1000 && (dday_month > month || (dday_month == month && dday_day >= day)))) {
        printf("gg\n");
    } else {
        int count = 0;
        while (year < dday_year || month < dday_month || day < dday_day) {
            day++;
            int days_in_month = 31;
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                days_in_month = 30;
            } else if (month == 2) {
                if (is_leapyear(year)) {
                    days_in_month = 29;
                } else {
                    days_in_month = 28;
                }
            }
            if (day > days_in_month) {
                day = 1;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
            count++;
        }
        printf("D-%d\n", count);
    }
    return 0;
}

int is_leapyear(int year)
{
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        return 1;
    } else {
        return 0;
    }
}
