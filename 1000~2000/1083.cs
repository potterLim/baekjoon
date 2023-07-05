namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] numbers;
            int arraySize;
            int remainingSum;
            int currentIndex;

            arraySize = int.Parse(Console.ReadLine());
            numbers = new int[arraySize];

            string[] numberInputs = Console.ReadLine().Split();
            for (int i = 0; i < arraySize; i++)
            {
                numbers[i] = int.Parse(numberInputs[i]);
            }

            remainingSum = int.Parse(Console.ReadLine());
            currentIndex = 0;

            while (remainingSum > 0 && currentIndex < arraySize)
            {
                int limit = remainingSum + currentIndex;
                int maxIndex, maxValue;

                if (arraySize - 1 < remainingSum + currentIndex)
                {
                    limit = arraySize - 1;
                }

                maxIndex = currentIndex;
                maxValue = 0;

                for (int j = currentIndex; j <= limit; j++)
                {
                    if (numbers[j] > maxValue)
                    {
                        maxIndex = j;
                        maxValue = numbers[j];
                    }
                }

                for (int j = maxIndex; j > currentIndex; j--)
                {
                    int temp = numbers[j];
                    numbers[j] = numbers[j - 1];
                    numbers[j - 1] = temp;
                }

                remainingSum = remainingSum - maxIndex + currentIndex;
                currentIndex++;
            }

            for (int i = 0; i < arraySize; i++)
            {
                Console.Write($"{numbers[i]} ");
            }

            Console.WriteLine();
        }
    }
}