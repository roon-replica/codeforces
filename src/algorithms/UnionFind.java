package algorithms;

public class UnionFind {

	private final int[] group;
	private final int[] depth;

	public UnionFind(int size) {
		group = new int[size + 1];
		depth = new int[size + 1];
		for (int i = 0; i < group.length; i++) {
			group[i] = i;
		}
	}

	public int find(int v) {
		if (group[v] != v) {
			group[v] = find(group[v]);
		}

		return group[v];
	}

	public boolean union(int v1, int v2) {
		int group1 = find(v1);
		int group2 = find(v2);
		if (group1 == group2) {
			return false; // already same group
		}

		if (depth[group1] < depth[group2]) {
			group[group1] = group2;
		} else if (depth[group1] > depth[group2]) {
			group[group2] = group1;
		} else {
			group[group1] = group2;
			depth[group2]++;
		}

		return true; // newly merged into same group
	}
}
