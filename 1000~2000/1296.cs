namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string name = Console.ReadLine();
            int[] arr = new int[4];
            int max = -1;
            string str = "LOVE";
            string str2 = "";
            int N = int.Parse(Console.ReadLine());

            for (int i = 0; i < N; i++)
            {
                string team = Console.ReadLine();

                for (int j = 0; j < 4; j++)
                {
                    int cnt = name.Length - name.Replace(str[j].ToString(), "").Length;
                    cnt += team.Length - team.Replace(str[j].ToString(), "").Length;
                    arr[j] = cnt;
                }

                int tot = 1;
                for (int j = 0; j < 4; j++)
                {
                    for (int k = (j + 1); k < 4; k++)
                    {
                        tot *= (arr[j] + arr[k]);
                    }
                }

                if (max == (tot % 100) && str2 != "")
                {
                    string[] arr2 = new string[2];
                    arr2[0] = str2;
                    arr2[1] = team;
                    Array.Sort(arr2);
                    str2 = arr2[0];
                }

                if (max < (tot % 100))
                {
                    max = tot % 100;
                    str2 = team;
                }
            }

            Console.WriteLine(str2);
        }
    }
}