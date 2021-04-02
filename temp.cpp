#include <cstring>
#include<iostream>
#include <cstdlib>

using namespace std;

struct rotation {
    int index;
    char* suffix;
};

int cmpfunc(const void* x, const void* y)
{
    struct rotation* rx = (struct rotation*)x;
    struct rotation* ry = (struct rotation*)y;
    return strcmp(rx->suffix, ry->suffix);
}

int* computeSuffixArray(char* input_text, int len_text)
{
    struct rotation suff[len_text];
    for (int i = 0; i < len_text; i++) {
        suff[i].index = i;
        suff[i].suffix = (input_text + i);
    }

    qsort(suff, len_text, sizeof(struct rotation),cmpfunc);

    int* suffix_arr
    = (int*)malloc(len_text * sizeof(int));
    for (int i = 0; i < len_text; i++)
    suffix_arr[i] = suff[i].index;
    return suffix_arr;
}

char* findLastChar(char* input_text,int* suffix_arr, int n)
{
    char* bwt_arr = (char*)malloc(n * sizeof(char));
    int i;
    for (i = 0; i < n; i++) {
        int j = suffix_arr[i] - 1;
        if (j < 0)
        j = j + n;
        bwt_arr[i] = input_text[j];
    }
    bwt_arr[i] = '\0';
    return bwt_arr;
}
int search(char input_char, char* list)
{
    int i;
    for (i = 0; i < strlen(list); i++) {
        if (list[i] == input_char) {
            return i;
            break;
        }
    }
    return 0;
}

void moveToFront(int curr_index, char* list)
{
    char* record = (char*)malloc(sizeof(char) * 26);
    strcpy(record, list);
    strncpy(list + 1, record, curr_index);
    list[0] = record[curr_index];
}
void mtfEncode(char* input_text, int len_text, char* list)
{
    int i;
    int* output_arr = (int*)malloc(len_text * sizeof(int));
    for (i = 0; i < len_text; i++) { 
        output_arr[i] = search(input_text[i], list);
        printf("%d ", output_arr[i]);
        moveToFront(output_arr[i], list);
    }
}
int main()
{
    char input_text[] = "panama";
    int len_text = strlen(input_text);
    int* suffix_arr= computeSuffixArray(input_text, len_text);
    char* bwt_arr= findLastChar(input_text, suffix_arr, len_text);
    cout<<"Input text : "<<input_text<<endl;
    cout<<"\nBurrows - Wheeler Transform : "<<bwt_arr<<endl;
    char* list = (char*)malloc(sizeof(char) * 26);
    strcpy(list, "abcdefghijklmnopqrstuvwxyz");
    cout<<"\nMove to Front coding: ";
    mtfEncode(bwt_arr, len_text, list);
    cout<<endl;
    return 0;
}