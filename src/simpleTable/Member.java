package simpleTable;

public class Member {
	private String name;
	private String password;
	public Member(){}
	public Member(String name,String password){this.name = name; this.password = password;}
	public String getName(){return name;}
	public String getPassword(){return password;}
	public void setName(String name){this.name = name;}
	public void setPassword(String password){this.password = password;}
}