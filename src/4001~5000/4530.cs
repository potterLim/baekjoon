public class Program
{
    static void Main(string[] args)
    {
        int testCaseCount = int.Parse(Console.ReadLine());

        for (int i = 1; i <= testCaseCount; ++i)
        {
            int n = int.Parse(Console.ReadLine());
            int[] numbers = new int[n];
            Dictionary<int, int> interestingScores = new Dictionary<int, int>();

            for (int j = 0; j < n; ++j)
            {
                numbers[j] = int.Parse(Console.ReadLine());
            }

            for (int j = 0; j < n; ++j)
            {
                int num = numbers[j];
                interestingScores[num] = getInterestingFeatures(num, numbers, n);
            }

            int maxInterestingScore = -1;

            for (int j = 0; j < n; ++j)
            {
                if (interestingScores[numbers[j]] > maxInterestingScore)
                {
                    maxInterestingScore = interestingScores[numbers[j]];
                }
            }

            List<int> interestingNumbers = new List<int>();

            for (int j = 0; j < n; ++j)
            {
                if (interestingScores[numbers[j]] == maxInterestingScore)
                {
                    interestingNumbers.Add(numbers[j]);
                }
            }

            for (int j = 0; j < interestingNumbers.Count - 1; ++j)
            {
                for (int k = j + 1; k < interestingNumbers.Count; ++k)
                {
                    if (interestingNumbers[j] > interestingNumbers[k])
                    {
                        int temp = interestingNumbers[j];
                        interestingNumbers[j] = interestingNumbers[k];
                        interestingNumbers[k] = temp;
                    }
                }
            }

            Console.WriteLine("DATA SET #" + i);

            for (int j = 0; j < interestingNumbers.Count; ++j)
            {
                Console.WriteLine(interestingNumbers[j]);
            }
        }
    }

    private static int getInterestingFeatures(int num, int[] numbers, int n)
    {
        int count = 0;
        int digitSum = getDigitSum(num);
        int digitProduct = getDigitProduct(num);

        if (isPrime(num))
        {
            count++;
        }

        if (isPerfectSquare(num))
        {
            count++;
        }

        if (isPerfectCube(num))
        {
            count++;
        }

        if (isPerfectFourthPower(num))
        {
            count++;
        }

        if (num % digitSum == 0)
        {
            count++;
        }

        if (digitProduct != 0 && num % digitProduct == 0)
        {
            count++;
        }

        for (int i = 0; i < n; ++i)
        {
            int other = numbers[i];

            if (other == num)
            {
                continue;
            }

            if (other % num == 0)
            {
                count++;
            }

            if (num % other == 0)
            {
                count++;
            }

            if (isPerfectSquare(other) && num == other * other)
            {
                count++;
            }

            if (isPerfectCube(other) && num == other * other * other)
            {
                count++;
            }

            if (isPerfectFourthPower(other) && num == other * other * other * other)
            {
                count++;
            }

            if (num % getDigitSum(other) == 0)
            {
                count++;
            }

            if (getDigitProduct(other) != 0 && num % getDigitProduct(other) == 0)
            {
                count++;
            }
        }

        return count;
    }

    private static bool isPrime(int num)
    {
        if (num < 2)
        {
            return false;
        }

        for (int i = 2; i * i <= num; ++i)
        {
            if (num % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    private static bool isPerfectSquare(int num)
    {
        int root = (int)Math.Sqrt(num);
        return root * root == num;
    }

    private static bool isPerfectCube(int num)
    {
        int root = (int)Math.Round(Math.Pow(num, 1.0 / 3.0));
        return root * root * root == num;
    }

    private static bool isPerfectFourthPower(int num)
    {
        int root = (int)Math.Round(Math.Pow(num, 1.0 / 4.0));
        return root * root * root * root == num;
    }

    private static int getDigitSum(int num)
    {
        int sum = 0;

        while (num > 0)
        {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    private static int getDigitProduct(int num)
    {
        int product = 1;
        bool hasNonZeroDigit = false;

        while (num > 0)
        {
            int digit = num % 10;

            if (digit != 0)
            {
                product *= digit;
                hasNonZeroDigit = true;
            }

            num /= 10;
        }

        if (!hasNonZeroDigit)
        {
            return 0;
        }

        return product;
    }
}