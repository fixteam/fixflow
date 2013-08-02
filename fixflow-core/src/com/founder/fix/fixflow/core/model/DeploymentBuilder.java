/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.model;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 
 * @author kenshin
 *
 */
public interface DeploymentBuilder {
  
  DeploymentBuilder addInputStream(String resourceName, InputStream inputStream);
  DeploymentBuilder addClasspathResource(String resource);
  DeploymentBuilder addString(String resourceName, String text);
  DeploymentBuilder addZipInputStream(ZipInputStream zipInputStream);

  
  DeploymentBuilder name(String name);
  DeploymentBuilder enableDuplicateFiltering();
  
  /**
   * 更新资源
   * @param resourceId 资源编号
   * @param inputStream 流
   * @return
   */
  DeploymentBuilder updateResource(String resourceId,InputStream inputStream);

  Deployment deploy();
}
