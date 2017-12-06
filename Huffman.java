public class Huffman {
    public static int[] huffmanDecode (String input, String output){
        
        In code = new In(input);
        Out file = new Out(output);
        int bits = 0;
        int chars = 0;
        int[] count = new int[2];
        String target = "";

        while(code.hasNextChar()){
            bits++;
            char ch = code.readChar();
            if ((ch == '0') || (ch == '1')){
                target = target + ch;
                char decode = lookupCode("code-table.txt", target);
                if (!(decode == Character.UNASSIGNED)){
                    file.print(decode);
                    target = "";
                    chars++;
                }
                
            }
        }
        code.close();
        file.close();
        count[1] = chars;
        count[0] = bits;
        return count;
    }
//--------------------------------------------------------------------------
	public static char lookupCode (String codeFilename, String target){

		In inFile = new In(codeFilename);
		String s, l; 
		int i; 

		while (inFile.hasNextLine()){
			s = inFile.readString();
			if (s.equalsIgnoreCase(target)){
				i = inFile.readInt();
				l = inFile.readLine();
                char ch = (char)i;
                return(ch);
			}
			else {
				l = inFile.readLine();
			}
		}
        return Character.UNASSIGNED;
	}
//---------------------------------------------------------------------------
	private static void test() {

		if (lookupCode("code-table-small.txt", "Ada") == 'L') {
            System.out.println("Ada was looked up correctly");
        } else {
            System.err.println("Ada was not looked up correctly");
        }

        if (lookupCode("code-table-small.txt", "Alan") == 'T') {
            System.out.println("Alan was looked up correctly");
        } else {
            System.err.println("Alan was not looked up correctly");
        }
        	if (lookupCode("code-table-small.txt", "Grace") == 'H') {
            System.out.println("Grace was looked up correctly");
        } else {
            System.err.println("Grace was not looked up correctly");
        }

        if (lookupCode("code-table-small.txt", "Steve") == ' ') {
            System.out.println("Steve was looked up correctly");
        } else {
            System.err.println("Steve was not looked up correctly");
        }
         if (lookupCode("code-table-small.txt", "Bill") == '\t') {
            System.out.println("Bill was looked up correctly");
        } else {
            System.err.println("Bill was not looked up correctly");
        }
        if (lookupCode("code-table-small.txt", "A") == Character.UNASSIGNED) {
            System.out.println("A was looked up correctly");
        } else {
            System.err.println("A was not looked up correctly");
        }

	}// end of test
 //----------------------------------------------------------------------------
    public static void main(String[] args) {
        //test(); //no longer needed in the finished project

        System.out.println();
        System.out.println("Welcome to the Huffman Decompression program.");

        System.out.print("Input file: ");
        String inputPath = StdIn.readLine();
        System.out.print("Output file: ");
        String outputPath = StdIn.readLine();

        int[] finalCount = huffmanDecode(inputPath, outputPath);
        System.out.println("Number of bits read from input file: " + finalCount[0]);
        System.out.println("Number of characters decoded: " + finalCount[1]);

        double average = (finalCount[0] + 0.0) / finalCount[1];
        System.out.printf("Average bits per compressed character: %.3f \n", average);
        
        System.out.println("\nThank you and have a nice day!\n");
    }
}