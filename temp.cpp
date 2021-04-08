#include <iostream>
#include <string.h>
#include <math.h>

using namespace std;

int count, a, i, j, k = 0, l, flag = 0, m = 0, freq[25], sum = 0, total, temp;
int q, sum1 = 0, sum2 = 0, min, diff[25] = {0};
double start[10];
double r1[10], r2[10];
double low = 0, high = 1;
double info_cont[25], p[25];
char ch[26], temp1, range[25][25];
string str;
int main()
{
    void cha();
    void sortf();
    void prob_range();
    void low_high();
    void decoding();

    cout << "\nEnter the string: ";
    getline(cin, str);
    cha();
    sortf();
    prob_range();
    low_high();
    decoding();
}
void cha()
{
    ch[k] = str[0];
    for (i = 0; str[i] != '\0'; i++)
    {
        for (j = 0; j <= k; j++)
        {
            if (ch[j] == str[i])
            {
                flag = 1;
                break;
            }
            else
            {
                flag = 0;
            }
        }
        if (flag == 0)
        {
            k++;
            ch[k] = str[i];
        }
    }
    cout << "\nUnique String: ";
    for (i = 0; i <= k; i++)
    {
        cout << ch[i];
    }
}
void sortf()
{
    for (i = 0; ch[i] != '\0'; i++)
    {
        a = ch[i];
        count = 0;
        for (j = 0; str[j] != '\0'; j++)
        {
            if (a == str[j])
            {
                count++;
            }
        }
        freq[m] = count;
        sum += count;
        m++;
    }
    cout << "\nTotal characters: " << sum;
    cout << "\nChar \t Frequency";
    for (i = 0; i <= k; i++)
    {
        cout << "\n"
             << ch[i] << "\t" << freq[i];
    }
    cout << "\n";
    for (i = 0; i <= k; i++)
    {
        for (j = i + 1; j <= k; j++)
        {
            if (ch[i] > ch[j])
            {
                temp = ch[i];
                ch[i] = ch[j];
                ch[j] = temp;
                temp1 = freq[i];
                freq[i] = freq[j];
                freq[j] = temp1;
            }
        }
    }
    // cout << "\nAfter sorting the frequency ";
    // cout << "\nChar \t Frequency";
    // for (i = 0; i <= k; i++)
    // {
    //     cout << "\n"
    //          << ch[i] << "\t" << freq[i];
    // }
    // cout << "\n";
}
void prob_range()
{
    //cout << "\nTotal characters: " << sum;
    for (i = 0; i <= k; i++)
    {
        p[i] = (double)freq[i] / sum;
    }
    r1[0] = 0;
    r2[0] = p[0];
    for (i = 1; i <= k; i++)
    {
        r1[i] = r2[i - 1];
        r2[i] = r1[i] + p[i];
    }

    cout << "\nChar    Freq     Prob.          Range";
    for (i = 0; i <= k; i++)
        cout << "\n"
             << ch[i] << "\t" << freq[i] << "\t" << p[i] << "\t" << r1[i] << "<=r<" << r2[i];
    cout<<"\n\n";
}
void low_high()
{
    double l_range(char);
    double h_range(char);
    double range, high_range[100], low_range[100], jj;
    for (i = 0; str[i] != '\0'; i++)
    {
        range = high - low;
        for (j = 0; ch[j] != '\0'; j++)
        {
            if (ch[j] == str[i])
                break;
        }
        low_range[i] = low + range * r1[j];
        high_range[i] = low + range * r2[j];
        high = high_range[i];
        low = low_range[i];
    }

    cout << "\nCHAR    LOW                            HIGH";
    for (i = 0; str[i] != '\0'; i++)
    {
        printf("\n%c\t%.12lf\t\t%.12lf", str[i], low_range[i], high_range[i]);
    }
    printf("\n \nThe Encoded Number: %.12lf", low);
}
double l_range(char c)
{
    double l;
    for (i = 0; ch[i] != '\0'; i++)
    {
        if (ch[i] == c)
        {
            l = r1[i];
            break;
        }
    }
    return l;
}
double h_range(char c)
{
    double h;
    for (i = 0; i <= k; i++)
    {
        if (ch[i] == c)
        {
            h = r1[i];
            break;
        }
    }
    return h;
}
void decoding()
{
    double encode_no;
    double no;
    encode_no = low;
    //printf("\n The Encode No...%.12lf", encode_no);
    printf("\n\nChar     Encoded No             Low     High");

    for (i = 0; str[i] != '\0'; i++)
    {
        for (j = 0; ch[j] != '\0'; j++)
        {
            if (str[i] == ch[j])
            {
                printf("\n%c\t%.12lf\t\t%.2lf\t%.2lf", ch[j], encode_no, r1[j], r2[j]);
                encode_no = (encode_no - r1[j]) / (r2[j] - r1[j]);
            }
        }
    }
}
