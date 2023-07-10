using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int numberOfCraneWeights = int.Parse(Console.ReadLine());

            List<int> craneWeights = new List<int>();
            string[] craneWeightStrings = Console.ReadLine().Split(' ');
            for (int i = 0; i < numberOfCraneWeights; i++)
            {
                craneWeights.Add(int.Parse(craneWeightStrings[i]));
            }
            craneWeights.Sort();
            craneWeights.Reverse();

            int numberOfBoxWeights = int.Parse(Console.ReadLine());
            List<int> boxWeights = new List<int>();
            string[] boxWeightStrings = Console.ReadLine().Split(' ');
            for (int i = 0; i < numberOfBoxWeights; i++)
            {
                boxWeights.Add(int.Parse(boxWeightStrings[i]));
            }
            boxWeights.Sort();
            boxWeights.Reverse();

            if (craneWeights[0] < boxWeights[0])
            {
                Console.WriteLine("-1");
            }
            else
            {
                int time = 0;
                while (boxWeights.Count > 0)
                {
                    int craneIndex = 0;
                    for (int i = 0; i < craneWeights.Count;)
                    {
                        if (craneIndex == boxWeights.Count)
                        {
                            break;
                        }
                        else if (craneWeights[i] >= boxWeights[craneIndex])
                        {
                            boxWeights.RemoveAt(craneIndex);
                            i++;
                        }
                        else
                        {
                            craneIndex++;
                        }
                    }
                    time++;
                }
                Console.WriteLine(time);
            }
        }
    }
}
