public class Program
{
    static void Main(string[] args)
    {
        Dictionary<string, int> fruitCounts = new Dictionary<string, int>()
        {
            {"STRAWBERRY", 0},
            {"BANANA", 0},
            {"LIME", 0},
            {"PLUM", 0}
        };

        int countOpenedCard = int.Parse(Console.ReadLine());

        for (int i = 0; i < countOpenedCard; ++i)
        {
            string[] cardInfo = Console.ReadLine().Split(' ');
            string fruitType = cardInfo[0];
            int fruitQuantity = int.Parse(cardInfo[1]);

            fruitCounts[fruitType] += fruitQuantity;
        }

        bool isBellPressed = fruitCounts["STRAWBERRY"] == 5 || fruitCounts["BANANA"] == 5 || fruitCounts["LIME"] == 5 || fruitCounts["PLUM"] == 5;

        if (isBellPressed)
        {
            Console.WriteLine("YES");
        }
        else
        {
            Console.WriteLine("NO");
        }
    }
}