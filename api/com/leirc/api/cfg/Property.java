package com.leirc.api.cfg;

public final class Property{
	private final String name;
	private final Object value;
	
	public Property(String name, Object value){
		this.name = name;
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Object getValue(){
		return this.value;
	}
	
	public String asString(){
		return (String) this.getValue();
	}
	
	public int asInteger(){
		return (Integer) this.getValue();
	}
	
	public short asShort(){
		return (Short) this.getValue();
	}
	
	public double asDouble(){
		return (Double) this.getValue();
	}
	
	public float asFloat(){
		return (Float) this.getValue();
	}
	
	public char asChar(){
		return (Character) this.getValue();
	}
	
	public boolean asBoolean(){
		return (Boolean) this.getValue();
	}
	
	public byte asByte(){
		return (Byte) this.getValue();
	}
	
	public boolean isExactSame(Property prop){
		return this.getName().equals(prop.getName()) && this.getValue().equals(prop.getValue());
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Property){
			return ((Property) obj).getName().equals(this.getName());
		} else{
			return false;
		}
	}
	
	@Override
	public String toString(){
		return String.format("%s=%s", this.getName(), this.asString());
	}
	
	public static Property toProperty(String line){
		String[] args = line.split("=");
		return new Property(args[0], args[1]);
	}
	
	public static Property asDummy(String name){
		return new Property(name, null);
	}
}