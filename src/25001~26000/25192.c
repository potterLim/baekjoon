#include <stdio.h>
#include <string.h>

#define MAX_N 100000
#define NAME_LEN 21
#define BUCKET_COUNT 262144

static int s_bucket_head[BUCKET_COUNT];
static int s_bucket_version[BUCKET_COUNT];
static int s_next_node_index[MAX_N];
static char s_node_names[MAX_N][NAME_LEN];
static int s_node_count;
static int s_current_version;

static unsigned long hash_string(const char* name)
{
    unsigned long hash_value;
    int character;
    hash_value = 5381UL;
    while ((character = (unsigned char)(*name++)) != 0) {
        hash_value = ((hash_value << 5) + hash_value) + (unsigned long)character;
    }
    return hash_value;
}

static int insert_name_if_new(const char* name)
{
    unsigned long hash_value;
    int bucket_index;
    int node_index;

    hash_value = hash_string(name);
    bucket_index = (int)(hash_value & (BUCKET_COUNT - 1));

    if (s_bucket_version[bucket_index] != s_current_version) {
        s_bucket_version[bucket_index] = s_current_version;
        s_bucket_head[bucket_index] = -1;
    } else {
        node_index = s_bucket_head[bucket_index];
        while (node_index != -1) {
            if (strcmp(s_node_names[node_index], name) == 0) {
                return 0;
            }
            node_index = s_next_node_index[node_index];
        }
    }

    strcpy(s_node_names[s_node_count], name);
    s_next_node_index[s_node_count] = (s_bucket_version[bucket_index] == s_current_version) ? s_bucket_head[bucket_index] : -1;
    s_bucket_head[bucket_index] = s_node_count;
    ++s_node_count;
    return 1;
}

int main(void)
{
    int record_count;
    int i;
    long total_gomgom_count;
    int session_unique_count;

    scanf("%d", &record_count);

    s_current_version = 1;
    total_gomgom_count = 0;
    session_unique_count = 0;
    s_node_count = 0;

    for (i = 0; i < record_count; ++i) {
        char input_token[NAME_LEN];
        scanf("%20s", input_token);

        if (strcmp(input_token, "ENTER") == 0) {
            total_gomgom_count += session_unique_count;
            ++s_current_version;
            session_unique_count = 0;
            s_node_count = 0;
        } else {
            if (insert_name_if_new(input_token)) {
                ++session_unique_count;
            }
        }
    }

    total_gomgom_count += session_unique_count;
    printf("%ld\n", total_gomgom_count);
    return 0;
}
