public class Program
{
    static void Main(string[] args)
    {

        string[] inputs = Console.ReadLine().Split();
        long A = long.Parse(inputs[0]);
        long B = long.Parse(inputs[1]);

        Console.WriteLine(CountOnes(B) - CountOnes(A - 1));
    }

    static long CountOnes(long x)
    {
        if (x < 0) return 0;

        long count = 0;
        long powerOfTwo = 1;

        while (powerOfTwo <= x)
        {
            long fullCycles = x / (powerOfTwo * 2);
            long remainder = x % (powerOfTwo * 2);

            count += fullCycles * powerOfTwo;
            count += Math.Max(0, remainder - powerOfTwo + 1);

            powerOfTwo *= 2;
        }

        return count;
    }
}