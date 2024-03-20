package com.example.encore_spring_pjt.ctrl.user.service;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
import com.example.encore_spring_pjt.ctrl.user.mapper.UserMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public UserResponse loginService(UserRequest params) {
        System.out.println("debug >>>> service loginService");
        return userMapper.loginRow(params);
    }

    // insert
    public void registerService(UserRequest params) {
        System.out.println("debug >>>> service joinService");
        userMapper.insertRow(params);
    }

    public String getPwd(UserRequest params) {
        System.out.println("debug >>>> service getPwd");
        return userMapper.getPwd(params);
    }

    /*
     API 흐름 구조
     - 사용자가 카카오 API 사용을 호출하면 서버는 호출이 됨
     - 로그인 페이지가 보여지고 정보를 입력받는다
     - 문제가 없으면 카카오서버는 redirect_uri 로 인증코드를 보내줌
     - 사용자는 해당 인증코드를 가지고 서버에게 토큰(access token)을 요구함
     - 문제가 없으면 서버는 사용자에게 토큰을 넘겨줌
     - 받은 토큰을 카카오 서버에게 전달하여 사용자 정보를 요청하게 됨
     - 문제가 없으면 토큰에 해당하는 사용자의 정보를 보내줌
     - 해당 정보를 받아서 처리(e.g., 내려받은 email 과 테이블에 저장된 email 비교)
     */
    public String getAccessToken(String code) {
        String accessToken = null, refreshToken;
        String kakaoUrl = "https://kauth.kakao.com/oauth/token";
        try {
            URL url = new URL(kakaoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println("debug service getAccessToken conn , " + conn);

            // header setting
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            String buffer = "grant_type=authorization_code" +
                    "&client_id=d9fdb3fbfe8b2103f40a0eab933b89ed" +
                    "&redirect_uri=http://localhost:8888/user/kakao_login.hanwha" +
                    "&code=" + code;

            writer.write(buffer);
            writer.flush();

            int http_status = conn.getResponseCode();
            System.out.println("debug service http_status , " + http_status);

            // token 을 얻어오기 위해서 BufferedReader 객체를 준비
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder responseResult = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // System.out.println("debug service line , " + line);
                responseResult.append(line);
            }
            System.out.println("debug service responseResult json , " + responseResult);
            // json -> Object (gson library 이용)
            JsonParser parser = new JsonParser();
            JsonElement jObj = parser.parse(responseResult.toString());
            System.out.println("debug service jObj Object , " + jObj);
            accessToken = jObj.getAsJsonObject().get("access_token").getAsString();
            refreshToken = jObj.getAsJsonObject().get("refresh_token").getAsString();
            System.out.println("debug service access token , " + accessToken);
            System.out.println("debug service refresh token , " + refreshToken);

            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    // access token 을 이용해서 사용자 정보를 요청하고 넘겨 받는 역할
    public Map<String, Object> getUserInfo(String token) {
        String kakaoUrl = "https://kapi.kakao.com/v2/user/me";
        Map<String, Object> map = new HashMap<>();
        try {
            URL url = new URL(kakaoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // header setting
            conn.setRequestMethod("GET");
            // 중요!! (헤더에 access token 을 심어서 보내줘야 함)
            conn.setRequestProperty("Authorization", "Bearer " + token);
            int http_status = conn.getResponseCode();
            System.out.println("debug service http_status , " + http_status);

            // 사용자 정보 넘겨받는 작업
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder responseResult = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // System.out.println("debug service line , " + line);
                responseResult.append(line);
            }
            System.out.println("debug service userInfo text , " + responseResult);
            // test -> json
            JsonParser parser = new JsonParser();
            JsonElement jObj = parser.parse(responseResult.toString());
            System.out.println("debug service jObj Object , " + jObj);
            JsonObject properties = jObj.getAsJsonObject().get("properties").getAsJsonObject();
            String name = properties.getAsJsonObject().get("nickname").getAsString();
            map.put("name", name);

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public String logout(Map<String, String> map) {
        String kakaoUrl = "https://kapi.kakao.com/v1/user/logout";
        StringBuilder buffer = new StringBuilder();
        try {
            URL url = new URL(kakaoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // header setting
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            if (map.get("Authorization") != null) {
                conn.setRequestProperty("Authorization", map.get("Authorization"));
            }

            // 서버에 파라미터를 전달하는 스트림
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            System.out.println("debug >>> service ready writer");

            // writer
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            for (String key : map.keySet()) {
                if (cnt >= 1) {
                    sb.append("&");
                }
                cnt += 1;
                sb.append(key).append(map.get(key));
            }
            writer.write(sb.toString());
            writer.flush();

            int http_status = conn.getResponseCode();
            System.out.println("debug service http_status , " + http_status);

            // 서버로부터 응답된 내용을 반환하는 스트림
            BufferedReader reader;
            if (http_status == 200) {
                System.out.println("debug service status 200");
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                System.out.println("debug service status != 200");
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

}