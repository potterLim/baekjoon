using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;

namespace StudentAgeCalculator
{
    public class Program
    {
        public static void Main(string[] args)
        {
            int studentCount = int.Parse(Console.ReadLine());
            List<Student> students = new List<Student>();

            for (int i = 0; i < studentCount; i++)
            {
                string[] input = Console.ReadLine().Split(' ');
                string name = input[0];
                int day = int.Parse(input[1]);
                int month = int.Parse(input[2]);
                int year = int.Parse(input[3]);

                students.Add(new Student(name, new DateTime(year, month, day)));
            }

            Student youngest = students.OrderByDescending(s => s.Birthday).First();
            Student oldest = students.OrderBy(s => s.Birthday).First();

            Console.WriteLine(youngest.Name);
            Console.WriteLine(oldest.Name);
        }
    }

    public class Student
    {
        public string Name { get; }
        public DateTime Birthday { get; }

        public Student(string name, DateTime birthday)
        {
            Name = name;
            Birthday = birthday;
        }
    }
}