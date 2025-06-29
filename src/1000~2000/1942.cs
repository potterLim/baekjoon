using System;

public class Program
{
    static void Main(string[] args)
    {
        for (int i = 0; i < 3; ++i)
        {
            string input = Console.ReadLine();
            string startTime = input.Split(" ")[0];
            string endTime = input.Split(" ")[1];

            string[] startTimeParts = startTime.Split(":");
            string[] endTimeParts = endTime.Split(":");

            int startHour = int.Parse(startTimeParts[0]);
            int startMinute = int.Parse(startTimeParts[1]);
            int startSecond = int.Parse(startTimeParts[2]);

            int endHour = int.Parse(endTimeParts[0]);
            int endMinute = int.Parse(endTimeParts[1]);
            int endSecond = int.Parse(endTimeParts[2]);

            int startTotalSeconds = startHour * 3600 + startMinute * 60 + startSecond;
            int endTotalSeconds = endHour * 3600 + endMinute * 60 + endSecond;

            if (startTotalSeconds > endTotalSeconds)
            {
                endTotalSeconds += 24 * 3600;
            }

            int count = 0;
            int current = startTotalSeconds;

            while (current <= endTotalSeconds)
            {
                int secondsInDay = current % (24 * 3600);
                int hour = secondsInDay / 3600;
                int minute = (secondsInDay % 3600) / 60;
                int second = secondsInDay % 60;

                string clockStr = $"{hour:D2}{minute:D2}{second:D2}";
                int clockInt = int.Parse(clockStr);

                if (clockInt % 3 == 0)
                {
                    count++;
                }

                current++;
            }

            Console.WriteLine(count);
        }
    }
}
