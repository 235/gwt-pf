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
package net.pleso.framework.client.bl.auth;


/**
 * Represents class that requires authorization check.
 * 
 * Every class for which authorizftion rights can be applied must implement this
 * interface.
 * 
 * This interface is used by {@link IAuthorizer} and
 * {@link AuthorizationProvider} as parameter in their methods which checks
 * authorization rights.
 */

public interface IAuth {

}
