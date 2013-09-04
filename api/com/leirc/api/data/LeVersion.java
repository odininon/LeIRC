package com.leirc.api.data;

@Deprecated
public class LeVersion implements IVersioning{
	private final char type;
	private final int major;
	private final int minor;
	private final int revis;
	
	public LeVersion(VersionType type, String vers){
		this.type = type.getUID().toCharArray()[0];
		String[] params = vers.split(".");
		this.major = Integer.valueOf(params[0]);
		this.minor = Integer.valueOf(params[1]);
		this.revis = Integer.valueOf(params[2]);
	}
	
	@Override
	public int getMajor() {
		return this.major;
	}

	@Override
	public int getMinor() {
		return this.minor;
	}

	@Override
	public int getRevis() {
		return this.revis;
	}

	@Override
	public char getVersionType() {
		return this.type;
	}
	
	public boolean isLargerThan(LeVersion vers){
		return !isSmallerThan(vers) && !versionsAreEqual(vers);
	}
	
	public boolean isSmallerThan(LeVersion vers){
		return (this.getMajor() < vers.getMajor()) &&
			   (this.getMinor() < vers.getMinor()) &&
			   (this.getRevis() < vers.getRevis());
	}
	
	public boolean versionsAreEqual(LeVersion vers){
		return (this.getVersionType() == vers.getVersionType()) &&
			   (this.getMajor() == vers.getMajor()) &&
			   (this.getMinor() == vers.getMinor()) &&
			   (this.getRevis() == vers.getRevis());
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof IVersioning){
			return versionsAreEqual((LeVersion) obj);
		} else{
			return false;
		}
	}
	
	@Override
	public String toString(){
		return String.format("%s%s.%s.%s", this.getVersionType(), this.getMajor(), this.getMinor(), this.getRevis());
	}
}