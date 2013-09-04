package com.leirc.api.data;

@Deprecated
public interface IVersioning{
	public enum VersionType{
		ALPHA("a"), BETA("b"), DEV("d"), RELEASE("r"), FULL("f");
		
		private final String uid;
		
		private VersionType(String uid){
			this.uid = uid;
		}
		
		public String getUID(){
			return this.uid;
		}
		
		public VersionType valueOf(char type){
			switch(type)
			{
			case 'a'|'A':
				return ALPHA;
			case 'b'|'B':
				return BETA;
			case 'd'|'D':
				return DEV;
			case 'r'|'R':
				return RELEASE;
			case 'f'|'F':
				return FULL;
			default:
				return null;
			}
		}
	}
	
	public int getMajor();
	public int getMinor();
	public int getRevis();
	public char getVersionType();
}