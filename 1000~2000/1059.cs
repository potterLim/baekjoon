namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int length = int.Parse(Console.ReadLine());
            string[] numbers = Console.ReadLine().Split(' ');
            int target = int.Parse(Console.ReadLine());

            int[] array = new int[length];
            for (int i = 0; i < array.Length; i++)
            {
                array[i] = int.Parse(numbers[i]);
            }
            Array.Sort(array);

            int count = 0;
            int smallestNum = array[0];

            for (int i = 1; i < smallestNum; i++)
            {
                for (int j = i + 1; j < smallestNum; j++)
                {
                    if ((target >= i && target <= j) && j < smallestNum)
                    {
                        count++;
                    }
                }
            }

            for (int i = 0; i < array.Length - 1; i++)
            {
                int currentNum = array[i];
                int nextNum = array[i + 1];

                for (int j = currentNum + 1; j < nextNum; j++)
                {
                    for (int k = j + 1; k < nextNum; k++)
                    {
                        if ((target >= j && target <= k) && k < nextNum)
                        {
                            count++;
                        }
                    }
                }
            }

            Console.WriteLine(count);
        }
    }
}