#include <stdio.h>
#include <string.h>

#define MAX_RECORDS 1000
#define MAX_NAME_LEN 21
#define MAX_PEOPLE (2 * MAX_RECORDS + 5)

static char s_people_names[MAX_PEOPLE][MAX_NAME_LEN];
static int s_is_dancing[MAX_PEOPLE];
static int s_total_people_count;

static int find_or_add_person_index(const char* person_name)
{
    int i;
    for (i = 0; i < s_total_people_count; ++i) {
        if (strcmp(s_people_names[i], person_name) == 0) {
            return i;
        }
    }
    strcpy(s_people_names[s_total_people_count], person_name);
    s_is_dancing[s_total_people_count] = 0;
    ++s_total_people_count;
    return s_total_people_count - 1;
}

int main(void)
{
    int meeting_count;
    int i;
    int chongchong_index;
    int total_dancers;

    scanf("%d", &meeting_count);

    s_total_people_count = 0;
    chongchong_index = find_or_add_person_index("ChongChong");
    s_is_dancing[chongchong_index] = 1;

    for (i = 0; i < meeting_count; ++i) {
        char person_a[MAX_NAME_LEN];
        char person_b[MAX_NAME_LEN];
        int index_a;
        int index_b;

        scanf("%20s %20s", person_a, person_b);
        index_a = find_or_add_person_index(person_a);
        index_b = find_or_add_person_index(person_b);

        if (s_is_dancing[index_a] || s_is_dancing[index_b]) {
            s_is_dancing[index_a] = 1;
            s_is_dancing[index_b] = 1;
        }
    }

    total_dancers = 0;
    for (i = 0; i < s_total_people_count; ++i) {
        if (s_is_dancing[i]) {
            ++total_dancers;
        }
    }

    printf("%d\n", total_dancers);
    return 0;
}
