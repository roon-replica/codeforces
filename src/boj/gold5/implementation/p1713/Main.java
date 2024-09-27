package boj.gold5.implementation.p1713;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		int n = scanner.nextInt(); // 사진틀 개수
		int tc = scanner.nextInt(); // 추천 횟수

		// treeset? treemap?
		// 시뮬
		// Picture(cnt, time)
		// 어떤 학생이 특정 학생을 추천하면, 추천받은 학생의 사진이 반드시 사진틀에 게시되어야 한다.

		var pictures = new TreeSet<Picture>();

		for (int time = 0; time < tc; time++) {
			int num = scanner.nextInt();
			if (pictures.descendingSet().stream().map(pic -> pic.num).collect(Collectors.toSet()).contains(num)) {
				var pic = get(pictures, num);
				pictures.remove(pic);
				pictures.add(new Picture(num, pic.cnt+1, pic.time));
			} else {
				if (pictures.size() == n) {
					var minPicture = pictures.first();
					pictures.remove(minPicture);
					pictures.add(new Picture(num, 1, time));
				}else{
					pictures.add(new Picture(num, 1, time));
				}
			}
//			System.out.println(num + " " + pictures);
		}

		var ans = pictures.descendingSet().stream()
				.map(pic -> pic.num)
				.sorted(Integer::compareTo)
				.map(String::valueOf)
				.collect(Collectors.joining(" "));

		System.out.println(ans);

//		 왜 틀림??
//		 3
//		 8
//		 1 1 1 2 2 3 3 4
//		아 아직 comparator 구현 안해서

//		treemap이 key를 비교하는건데..
//		(cnt,time)이 key가 되야하나?
//		treeset으로 가야하나. 근데 그럼 item 찾기가 좀..

//		진짜 맞았다 생각했는데 또 틀렸네..

//		진짜 헷갈렸다..
//		구현된 결과는 매우 간단한데도..
//		comparator 헷갈린다
//		set<CustomClass>에서 contains 어케 동작하는지도 명확하지 않으니까 어렵게 구현하고..
//		set, map 각 메서드 어케 동작하는지같은 사소한것도 어떻게 다 기억하고 있음..
//		딜레마?

	}

	static Picture get(TreeSet<Picture> pictures, int num) {
		return pictures.descendingSet().stream().filter(pic -> pic.num == num).findFirst().orElseThrow(() -> new IllegalStateException("never"));
	}

	static class Picture implements Comparable<Picture> {

		public int num;
		public int cnt;
		public int time;

		public Picture(int num, int cnt, int time) {
			this.num = num;
			this.cnt = cnt;
			this.time = time;
		}

		@Override
		public int compareTo(Picture o) {
			if (o.num == this.num) {
				return 0;
			}

			if (this.cnt == o.cnt) {
//				System.out.println("here: "+ this+' '+o);
				return Integer.compare(this.time, o.time);
			} else {
//				System.out.println("here: "+ this+" "+o);
				return Integer.compare(this.cnt, o.cnt);
			}
		}

		@Override
		public String toString() {
			return String.format("%d %d %d", num, cnt, time);
		}
	}
}
