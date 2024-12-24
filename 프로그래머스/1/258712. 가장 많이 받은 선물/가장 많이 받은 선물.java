import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // 친구 목록과 선물 기록 초기화
        List<String> friendsList = Arrays.asList(friends);
        int n = friendsList.size();
        int[][] giftRecords = new int[n][n];
        int[] giftScores = new int[n];

        // 선물 기록 처리
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            int sender = friendsList.indexOf(parts[0]);
            int receiver = friendsList.indexOf(parts[1]);

            // 선물 기록 업데이트
            giftRecords[sender][receiver]++;
            giftScores[sender]++;
            giftScores[receiver]--;
        }

        // 다음 달 예상 선물 계산
        int[] nextGiftCounts = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (giftRecords[i][j] > giftRecords[j][i]) {
                    // i가 더 많은 선물을 준 경우
                    nextGiftCounts[i]++;
                } else if (giftRecords[i][j] == giftRecords[j][i]) {
                    // 선물 주고받은 수가 같은 경우
                    if (giftScores[i] > giftScores[j]) {
                        nextGiftCounts[i]++;
                    }
                }
            }
        }

        // 가장 많은 선물을 받을 친구의 예상 수
        answer = Arrays.stream(nextGiftCounts).max().orElse(0);
       
        return answer;
    }
}