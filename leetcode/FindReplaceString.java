package leetcode;

import java.util.*;

public class FindReplaceString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindReplaceString sol = new FindReplaceString();
		//S = "abcd", indexes = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
		System.out.println(sol.findReplaceString("abcd", new int[]{0, 2}, new String[] {"a", "cd"}, new String[]{"eee", "fff"}));
		System.out.println(sol.findReplaceString("abcd", new int[]{0, 2}, new String[] {"ab", "ec"}, new String[]{"eee", "fff"}));
		System.out.println(sol.findReplaceString("vmokgggqzp", new int[]{3,5,1}, new String[] {"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"}));
	}
	
	// 833
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		StringBuilder sb = new StringBuilder();
		Pair[] pair = new Pair[indexes.length];
		for (int i = 0; i < indexes.length; i++) {
			pair[i] = new Pair(indexes[i], sources[i], targets[i]);
		}
		
		Arrays.sort(pair, new Comparator<Pair>() {
			@Override
			public int compare(Pair e1, Pair e2) {
				if (e1.index == e2.index) {
					return 0;
				}
				return e1.index < e2.index ? -1 : 1;
			}
		});
		
		int idx = 0;
		// 遍历S
		for (int i = 0; i < S.length(); i++) {
			// S的index不在indexes[]里时，直接把S[i] copy到sb里
			if (idx >= pair.length || i != pair[idx].index) {
				sb.append(S.charAt(i));
			} else {
				// S的index在indexes[]里时，先看看S里从i开始，到i + sources这段substring和sources[index] match上没有
				// 1. 如果match上了，那就把targets[index]这段copy到sb里去，然后i+sources继续大循环
				// 2. 如果match不上，那就老实把S[i]copy到sb里，继续大循环
				// 不管有没有match上，index都要++
				
				// 1. match
				Pair cur = pair[idx];
				int curIdx = cur.index;
				String curSource = cur.source;
				String curTarget = cur.target;
				if (S.substring(i, i + curSource.length()).equals(curSource)) {
					for (int j = 0; j < curTarget.length(); j++) {
						sb.append(curTarget.charAt(j));
					}
					i += curSource.length() - 1;
				} else {
					// 2. not match
					sb.append(S.charAt(i));
				}
				idx++;
			}
		}
		
		return sb.toString();
	}
	
	class Pair {
		int index;
		String source;
		String target;
		Pair(int index, String source, String target) {
			this.index = index;
			this.source = source;
			this.target = target;
		}
	}

}
