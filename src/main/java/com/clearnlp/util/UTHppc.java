/**
 * Copyright (c) 2009/09-2012/08, Regents of the University of Colorado
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/**
 * Copyright 2012/09-2013/04, 2013/11-Present, University of Massachusetts Amherst
 * Copyright 2013/05-2013/10, IPSoft Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.clearnlp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.carrotsearch.hppc.IntContainer;
import com.carrotsearch.hppc.IntOpenHashSet;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.google.common.collect.Sets;

public class UTHppc
{
	static public <T>Set<T> getKeySet(ObjectIntOpenHashMap<T> map)
	{
		Set<T> set = Sets.newHashSet();
		
		for (ObjectCursor<T> cur : map.keys())
			set.add(cur.value);
		
		return set;
	}
	
	static public int max(IntContainer c)
	{
		int max = Integer.MIN_VALUE;
		
		for (int i : c.toArray())
			max = Math.max(max, i);
		
		return max;
	}
	
	static public int min(IntContainer c)
	{
		int min = Integer.MAX_VALUE;
		
		for (int i : c.toArray())
			min = Math.min(min, i);
		
		return min;
	}
	
	/**
	 * Returns {@code true} if {@code s2} is the subset of {@code s1}.
	 * @return {@code true} if {@code s2} is the subset of {@code s1}.
	 */
	static public boolean isSubset(IntContainer s1, IntContainer s2)
	{
		for (int i : s2.toArray())
		{
			if (!s1.contains(i))
				return false;
		}
		
		return true;
	}
	
	static public IntOpenHashSet intersection(IntContainer c1, IntContainer c2)
	{
		IntOpenHashSet s1 = new IntOpenHashSet(c1);
		IntOpenHashSet s2 = new IntOpenHashSet(c2);
		
		s1.retainAll(s2);
		return s1;
	}
	
	static public List<String> getSortedKeys(ObjectIntOpenHashMap<String> map)
	{
		List<String> keys = new ArrayList<String>(map.size());
		
		for (ObjectCursor<String> cur : map.keys())
			keys.add(cur.value);
		
		Collections.sort(keys);
		return keys;
	}
}
