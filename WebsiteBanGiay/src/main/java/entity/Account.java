/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Account {
    private int maAccount;
    private String username;
    private String password;
    private String email;
    private int isAdmin;
    
    public Account(int maAccount, String username, String password, int isAdmin,String email) {
		this.maAccount = maAccount;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.email = email;
	}

	public Account() {
		
	}

	@Override
	public String toString() {
		return "Account [maAccount=" + this.maAccount + ", username=" + this.username + ", password=" + this.password + ", isAdmin=" + this.isAdmin
				+ ", email=" + this.email + "]";
	}
    //
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getMaAccount() {
		return maAccount;
	}

	public void setMaAccount(int maAccount) {
		this.maAccount = maAccount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email= email;
	}
   

    
}
