/**
* Copyright (c) 2009-2012, Regents of the University of Colorado
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
* Neither the name of the University of Colorado at Boulder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
* ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*/
package com.clearnlp.run;

import java.io.BufferedReader;
import java.io.IOException;

import org.kohsuke.args4j.Option;

import com.clearnlp.dependency.DEPFeat;
import com.clearnlp.dependency.DEPLib;
import com.clearnlp.reader.AbstractColumnReader;
import com.clearnlp.util.UTInput;

/**
 * @since 1.0.0
 * @author Jinho D. Choi ({@code jdchoi77@gmail.com})
 */
public class RolesetEvaluate extends AbstractRun
{
	@Option(name="-g", usage="gold-standard file (required)", required=true, metaVar="<filename>")
	private String s_goldFile;
	@Option(name="-s", usage="system-generated file (required)", required=true, metaVar="<filename>")
	private String s_autoFile;
	@Option(name="-gi", usage="column index of extra features in a gold-standard file (required)", required=true, metaVar="<integer>")
	private int    i_goldIndex;
	@Option(name="-si", usage="column index of extra features in a system-generated file (required)", required=true, metaVar="<integer>")
	private int    i_autoIndex;
	
	public RolesetEvaluate() {}
	
	public RolesetEvaluate(String[] args)
	{
		initArgs(args);
		run(s_goldFile, s_autoFile, i_goldIndex-1, i_autoIndex-1);
	}
	
	public void run(String goldFile, String autoFile, int goldIndex, int autoIndex)
	{
		BufferedReader fGold = UTInput.createBufferedFileReader(goldFile);
		BufferedReader fAuto = UTInput.createBufferedFileReader(autoFile);
		DEPFeat gFeats, aFeats;
		int[] counts = {0,0};	// correct, total
		String[] gold, auto;
		String gPred, aPred;
		String line;
		
		try
		{
			while ((line = fGold.readLine()) != null)
			{
				gold = line.split(AbstractColumnReader.DELIM_COLUMN);
				auto = fAuto.readLine().split(AbstractColumnReader.DELIM_COLUMN);
				
				line = line.trim();
				if (line.isEmpty())	 continue;
				
				gFeats = new DEPFeat(gold[goldIndex]);
				aFeats = new DEPFeat(auto[autoIndex]);
				
				gPred = gFeats.get(DEPLib.FEAT_PB);
				aPred = aFeats.get(DEPLib.FEAT_PB);
				
				if (gPred != null)
				{
					counts[0]++;
					
					if (gPred.equals(aPred))
						counts[1]++;
				}
			}
		}
		catch (IOException e) {e.printStackTrace();}
		
		System.out.printf("Accuracy: %5.2f (%d/%d)\n", 100d*counts[1]/counts[0], counts[1], counts[0]);
	}
	
	static public void main(String[] args)
	{
		new RolesetEvaluate(args);
	}
}
