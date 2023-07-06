namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string numStr = Console.ReadLine();
            int[] nums = new int[5];
            
            for (int i = 0; i < nums.Length; i++) 
            {
                nums[i] = int.Parse(numStr.Split(' ')[i]);
            }

            Array.Sort(nums);
            int num = nums[0];
            int count;
            while (true)
            {
                count = 0;
                for (int i = 0; i < nums.Length; i++) 
                {
                    if (num % nums[i] == 0)
                    {
                        count++;
                    }
                }

                if (count >= 3)
                {
                    break;
                }
                num++;
            }

            Console.WriteLine(num);
        }
    }
}