package boj.after2502.p2447;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// 빌딩 블록: N/3 * N/3

		var br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
//		int row = N / 3;
//		System.out.println(N);

		map = new char[N+2][N + 2];

		for(int r=0;r<N;r++){
			for(int c=0;c<N;c++){
				map[r][c] = ' ';
			}
		}

		drawBlock(N, 0, 0);

		BufferedOutputStream bos = new BufferedOutputStream(System.out);


		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				bos.write(map[row][col]);
			}
			bos.write('\n');
		}

		bos.flush();

	}

	static void drawBlock(int N, int nr, int nc) {
		if (N == 3) {
			for (int r = nr; r < nr + 3; r++) {
				for (int c = nc; c < nc + 3; c++) {

					if (r == nr + 1 && c == nc + 1) {
						map[r][c] = ' ';
					} else {
						map[r][c] = '*';
					}
				}
			}

			return;
		}

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if(r==1 && c==1){
					continue;
				}
				drawBlock(N / 3, nr + r * N / 3, nc + c * N / 3);
			}
		}

	}

	static int N;
	static char[][] map;

}
