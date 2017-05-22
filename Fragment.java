
public class Fragment {
	int number;
	String ident;
	int offset;
	int moreFragments;
	int size;
	
	public int getSize() {
		return size;
	}

	public Fragment(int number, String ident, int offset, int moreFragments, int size){
		this.number = number;
		this.ident = ident;
		this.offset = offset;
		this.moreFragments = moreFragments;
		this.size = size;
	}
	
	@Override
	public String toString(){
		String string = "Fragment number: " + number + "\nIdent: " + ident + "\nOffset: " + offset + "\nMore fragments (MF): " + moreFragments + "\nSize: " + size + "\n";
		return string;
	}
}
