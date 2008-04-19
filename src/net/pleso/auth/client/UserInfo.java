/*
 * Copyright 2007 Pleso.net
 * 
 * Licensed under the GNU Lesser General Public License, Version 2.1 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.gnu.org/licenses/lgpl.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.pleso.auth.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Utility-class container of user and connection information.
 * 
 * @author Scater
 *
 */
public class UserInfo implements IsSerializable {
	
	public UserInfo(){
	}
	
	public UserInfo(String host, String port, String database, String login, String password){
		this.host = host;
		this.port = port;
		this.database = database;
		this.login = login;
		this.password = password;
	}
	
	public UserInfo(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	private String host = new String();
	private String port = new String();
	private String database = new String();
	private String login = new String();
	private String password = new String();
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
