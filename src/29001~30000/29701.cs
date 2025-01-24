namespace MorseCodeDecoder
{
    public class Program
    {
        private static readonly Dictionary<string, char> MorseToCharMap = new Dictionary<string, char>
        {
            {".-", 'A'}, {"-...", 'B'}, {"-.-.", 'C'}, {"-..", 'D'}, {".", 'E'}, {"..-.", 'F'},
            {"--.", 'G'}, {"....", 'H'}, {"..", 'I'}, {".---", 'J'}, {"-.-", 'K'}, {".-..", 'L'},
            {"--", 'M'}, {"-.", 'N'}, {"---", 'O'}, {".--.", 'P'}, {"--.-", 'Q'}, {".-.", 'R'},
            {"...", 'S'}, {"-", 'T'}, {"..-", 'U'}, {"...-", 'V'}, {".--", 'W'}, {"-..-", 'X'},
            {"-.--", 'Y'}, {"--..", 'Z'},
            {".----", '1'}, {"..---", '2'}, {"...--", '3'}, {"....-", '4'}, {".....", '5'},
            {"-....", '6'}, {"--...", '7'}, {"---..", '8'}, {"----.", '9'}, {"-----", '0'},
            {"--..--", ','}, {".-.-.-", '.'}, {"..--..", '?'}, {"---...", ':'}, {"-....-", '-'},
            {".--.-.", '@'}
        };

        public static void Main()
        {
            int n = int.Parse(Console.ReadLine());
            string input = Console.ReadLine();

            string[] morseCodes = input.Split(' ');
            string decodedMessage = string.Empty;

            foreach (string morseCode in morseCodes)
            {
                if (MorseToCharMap.TryGetValue(morseCode, out char decodedChar))
                {
                    decodedMessage += decodedChar;
                }
            }

            Console.WriteLine(decodedMessage);
        }
    }
}