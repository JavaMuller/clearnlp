/**
* Copyright 2013 IPSoft Inc.
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
*   
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.clearnlp.constant.english;

import com.clearnlp.constant.DefaultConstant;

/**
 * @since 1.4.0
 * @author Jinho D. Choi ({@code jdchoi77@gmail.com})
 */
public class ENTime extends DefaultConstant
{
	static public boolean isTemporalSuffix(String str)
	{
		return str.equals("am") || str.equals("pm") || str.equals("a.m") || str.equals("p.m") || str.equals("a.m.") || str.equals("p.m.");  
	}
}
