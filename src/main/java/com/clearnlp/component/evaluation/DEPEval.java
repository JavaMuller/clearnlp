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

import com.clearnlp.dependency.DEPNode;
import com.clearnlp.dependency.DEPTree;
import com.clearnlp.util.pair.StringIntPair;

/**
 * @since 2.0.0
 * @author Jinho D. Choi ({@code jdchoi77@gmail.com})
 */
public class DEPEval extends AbstractEval
{
	private int n_total;
	private int n_las;
	private int n_uas;
	private int n_ls;
	
	public DEPEval()
	{
		n_total = 0;
		n_las   = 0;
		n_uas   = 0;
		n_ls    = 0;
	}
	
	@Override
	public void countAccuracy(DEPTree sTree, Object[] gHeads)
	{
		StringIntPair[] heads = (StringIntPair[])gHeads;
		int i, size = sTree.size();
		StringIntPair p;
		DEPNode node;
		
		n_total += size - 1;
		
		for (i=1; i<size; i++)
		{
			node = sTree.get(i);
			p = heads[i];
			
			if (node.isDependentOf(sTree.get(p.i)))
			{
				n_uas++;
				if (node.isLabel(p.s)) n_las++;
			}
			
			if (node.isLabel(p.s)) n_ls++;
		}
	}
	
	@Override
	public double[] getAccuracies()
	{
		double[] acc = new double[3];
		
		acc[0] = 100d * n_las / n_total;
		acc[1] = 100d * n_uas / n_total;
		acc[2] = 100d * n_ls  / n_total;
		
		return acc;
	}
	
	@Override
	public String toString()
	{
		double[] d = getAccuracies();
		return String.format("LAS: %5.2f (%d), UAS: %5.2f (%d), LS: %5.2f (%d), TOTAL: %d", d[0], n_las, d[1], n_uas, d[2], n_ls, n_total);
	}
}
