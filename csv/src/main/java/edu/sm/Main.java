package edu.sm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        String csvFile = "d:/aa.csv";  // 입력 파일 경로

        // StringBuilder를 사용하여 출력 데이터를 저장
        StringBuilder output = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), Charset.forName("MS949")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // 각 값을 StringBuilder에 저장
                for (String value : values) {
                    output.append(value).append(" ");  // 값을 추가하고 공백을 넣음
                }
                output.append("\n");  // 각 줄이 끝나면 줄바꿈 추가
            }

            // StringBuilder에 저장된 내용을 출력 (런타임 출력)
            System.out.println(output.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
