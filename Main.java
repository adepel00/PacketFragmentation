import java.util.Scanner;
/*
 * Console Input:
 * Line 1: MTU
 * Line 2: Packet size
 * 
 * Console Output:	
 * 			If we need to fragment the packet: 	"Fragment number: (fragment number)
 * 												Ident: 0x0002 (as an example of ident)
 * 												Offset: (the offset)
 * 												More fragments (MF): (1 or 0)
 * 												Size: (the fragment size)"
 * 
 * 			If we needn't to fragment the packet: "We needn't fragment this packet"
 */


public class Main {
	
	static int HEADER_SIZE = 20;

	public static void main(String[] args) {
		
		//Reading
		Scanner scan = new Scanner(System.in);
		int mtu = scan.nextInt();
		int packetSize = scan.nextInt();
		scan.close();
		//End reading
		
		if(mtu < packetSize + HEADER_SIZE){ //We must fagment the packet
			
			int fragmentsSize = mtu - HEADER_SIZE; //Calculate the fragment size
			if(fragmentsSize % 8 != 0){ //Fragment size must be divisible by 8
				fragmentsSize = fragmentsSize - (fragmentsSize % 8);
			}
			
			//Calculate the number of fragments
			int numberOfFragments = packetSize / fragmentsSize;
			
			if(packetSize % fragmentsSize != 0){
				numberOfFragments++;
			}
			
			String ident = "0x0002"; //An example of ident, all fragments from a same packet have the same ident
			int offset = 0;
			int moreFragments = 1;
			int size = fragmentsSize;
			
			//Calculate fragments fields
			for(int i = 0; i < numberOfFragments; i++){
				
				if(i == numberOfFragments - 1){ //The last fragment
					moreFragments = 0;
					if(packetSize % fragmentsSize != 0){
						size = packetSize - (fragmentsSize * (i));
					}
				}
				
				//Create a new fragment
				Fragment fragment = new Fragment(i + 1, ident, offset, moreFragments, size);
				
				//Print the fragment information
				System.out.println(fragment.toString());
				
				//Calculate next offset
				offset = ((i + 1) * fragmentsSize) / 8;
			}	
			
		}else{ //We needn't fagment the packet
			System.out.println("We needn't fragment this packet");
		}
	}

}
