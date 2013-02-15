/**
 * Copyright (C) 2009-2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.founder.fix.fixflow.expand.connector.LDAPConnector;

import java.io.Serializable;

/**
 * 
 * @author Matthieu Chaffotte
 *
 */
public class LdapAttribute  implements Serializable {

  private static final long serialVersionUID = 5939100299757700046L;
  private String name;
  private String value;

  public LdapAttribute(String attrName, String attrValue) {
    name = attrName;
    value = attrValue;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    boolean equals = false;
    if (obj instanceof LdapAttribute) {
      LdapAttribute temp = (LdapAttribute) obj;
      if (this.getName().equals(temp.getName())
          && this.getValue().equals(temp.getValue())) {
        equals = true;
      }
    }
    return equals;
  }

  @Override
  public int hashCode() {
    return name.hashCode() + value.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append("= ");
    builder.append(value);
    return builder.toString();
  }

}
