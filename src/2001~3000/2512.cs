using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int countProvinces = int.Parse(Console.ReadLine());
            List<int> budgets = new List<int>(countProvinces);

            string str = Console.ReadLine();
            string[] splitedStr = str.Split();

            foreach (string budget in splitedStr)
            {
                budgets.Add(int.Parse(budget));
            }
            budgets.Sort();
            budgets.Reverse();

            int totalBudget = int.Parse(Console.ReadLine());
            int maxBudget = budgets[0];

            while (true)
            {
                int sumBudget = 0;
                for (int i = 0; i < countProvinces; i++)
                {
                    if (budgets[i] > maxBudget)
                    {
                        sumBudget += maxBudget;
                    }
                    else
                    {
                        sumBudget += budgets[i];
                    }

                    if (sumBudget > totalBudget)
                    {
                        maxBudget--;
                        break;
                    }
                }

                if (sumBudget <= totalBudget)
                {
                    break;
                }
            }

            Console.WriteLine(maxBudget);
        }
    }
}