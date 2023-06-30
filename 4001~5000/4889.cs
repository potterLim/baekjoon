using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        public static void Main(string[] args)
        {
            int no = 1;
            while (true)
            {
                string str = Console.ReadLine();
                if (str.Contains("-"))
                {
                    break;
                }

                int n = str.Length;
                int count = 0;
                Stack<char> st = new Stack<char>();

                for (int i = 0; i < n; i++)
                {
                    if (str[i] == '{')
                    {
                        st.Push(str[i]);
                    }
                    else
                    {
                        if (st.Count == 0)
                        {
                            count++;
                            st.Push('{');
                        }
                        else
                        {
                            st.Pop();
                        }
                    }
                }
                Console.WriteLine($"{no++}. {(count + st.Count / 2)}");
            }
        }
    }
}
