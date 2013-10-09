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
package com.clearnlp.component.evaluation;

import com.clearnlp.dependency.DEPLib;
import com.clearnlp.dependency.DEPNode;
import com.clearnlp.dependency.DEPTree;

/**
 * @since 2.0.0
 * @author Jinho D. Choi ({@code jdchoi77@gmail.com})
 */
public class RoleEval extends AbstractAccuracyEval
{
	@Override
	public void countAccuracy(DEPTree sTree, Object[] gTags)
	{
		String[] tags = (String[])gTags;
		int i, size = sTree.size();
		DEPNode node;
		String tag;
		
		for (i=1; i<size; i++)
		{
			node = sTree.get(i);
			tag  = tags[i];
			
			if (tag != null)
			{
				n_total++;
				
				if (tag.equals(node.getFeat(DEPLib.FEAT_PB)))
					n_correct++;
			}
		}
	}
}
