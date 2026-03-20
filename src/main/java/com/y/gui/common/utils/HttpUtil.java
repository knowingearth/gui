package com.y.gui.common.utils;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * HTTP请求
 */
public class HttpUtil {

    /**
     * HTTP请求
     */
    public static <R, P> R exchange(String url, HttpMethod method, Class<R> responseBodyType, P requestBody) {
        return new RestTemplate().exchange(url, method, new HttpEntity<>(requestBody), responseBodyType).getBody();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Random random = new Random();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 1. 创建RestTemplate实例
                RestTemplate restTemplate = new RestTemplate();

                // 2. 设置请求URL
                String url = "https://xbp.jd.com/api/ticket/remind/42095170";

                // 3. 准备表单数据（必须使用MultiValueMap）
                MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

                // 4. 设置请求头
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.setOrigin("https://xbp.jd.com");
                headers.add("cookie", "jd.erp.lang=zh_CN; jdd69fo72b8lfeoe=XLC3JPQ7GIVVBRGXIVXTXKJVOJPCGZ7IVRSTXUO2T3C4HBWWF25Q74JVVJIJAYCJOS4GDOCFMDTOTFBEM6FI4BT37Q; focus-login-switch=saas; __jdu=1769570983451733331359; qid_uid=a64cade6-a2a7-4103-970f-0665df5ea5f6; qid_fs=1769582054429; uas_jtoken=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb21wYW55SWQiOiJqZGpyIiwidGlja2V0IjoiMjdiYTNmODIwNjExYmM0M2Y1MzBkMzQ1OGMxY2ZjNjAiLCJhY2NvdW50VHlwZSI6MCwiaXNzIjoiVUFTIiwiZXhwIjozNDk4MTA5MzE4LCJ1c2VybmFtZSI6ImV4dC55dWFuZ3VvbGVpNyJ9.1RZXGIzyAEPUcDdUUzV9yho_lOt_l0DVLlkrBMgA0ag; _erp=ext.yuanguolei7; erp=okdX7eGOVuSGJjquQllDPg==; pinId=Ub395dcJXUu4cxUFmHUxbg; pin=ambu_test; unick=ygambu_test0; shshshfpa=9561502f-5727-e880-c94c-3d11bc5a6294-1770110942; shshshfpx=9561502f-5727-e880-c94c-3d11bc5a6294-1770110942; TrackID=1tLceKG5wqQ3xwbh9a0Mee8rDXDqX_RRAiFlUw4_e7HSClS_5kziQWwNsA-0Q5fjLnSaCfAMyMjAyNY_nZDF-vr59LhSsXyHtd4MdpNiUK08; light_key=AASBKE7rOxgWQziEhC_QY6yaHntOJJky0_3N_i5YdsasBPOB-LiJNARZCmat2sCaRHcLuN6Y; logbook_u=ext.yuanguolei7; qid_ls=1771998905657; qid_ts=1772087241304; qid_vis=3; qid_evord=179; ssa.jd-sep-web-shop=59173b6c2e72c23e686dbdbf8889d06bd263a5f51ee8d3c59a93471611fadcb1c7fde6ca1b28ed91bd76f2cab415b4474cd31107425b30b00cd82abcd97363d4d593998bc0b729e2b8b1e9eeaacb315e3fe0a28fe2613aba87e8740dbe8af6ec05884e5b831e6d316a6562a7e85b79b777197e41c42c224dece16dbad56ce5ca; __jdv=155324846|direct|-|none|-|1772172495900; me_fp=0716ea70637c77313ef948af80caaa3c; me_js_token=jdd03ULHIJYKX2BVZQRPEEGML6M7UXPMFEFLQ2AXS2MZZJFAFEWXT6DOASY3EST7LZRGBRJU5OEYYMBFWU2WDHTDYHLI5K4AAAAM4IZDVNTQAAAAACZEODEWSPE6OD4X; focus-lang=zh_CN; focus-team-id=00046419; me_saas_userInfo=U2FsdGVkX19qESI4F6d+OBkV0+zfYy95DphGmyjylocpRX5Wvck2fdaPd8nPNQcI; focus-client=WEB; 3AB9D23F7A4B3CSS=jdd03ULHIJYKX2BVZQRPEEGML6M7UXPMFEFLQ2AXS2MZZJFAFEWXT6DOASY3EST7LZRGBRJU5OEYYMBFWU2WDHTDYHLI5K4AAAAM42IEAYMQAAAAACYV26TOPNW2XIYX; areaId=1; cid=9; ipLoc-djd=1-2809-51231-0; shshshfpb=BApXWhrQA0flAl_4_pWAGNHuFVGYHnNNOBjJQDm1q9xJ1MmKEnI-28XS83Sn7YNNyJbAKtfPQhumm9PQ; 3AB9D23F7A4B3C9B=ULHIJYKX2BVZQRPEEGML6M7UXPMFEFLQ2AXS2MZZJFAFEWXT6DOASY3EST7LZRGBRJU5OEYYMBFWU2WDHTDYHLI5K4; me_token=ee.5dtzdaO65mqTbeBCcZxEylrcfdTrzuheDsng4HzDAW0hyTjf4R87BZ4zA3FDI9RnhaFskWImifujj4M7rA465mOf43c2.ROLMiv-0USQCMqyAxUq3jw.LJ6ghyuIrN28s5rGAahvpQ; token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6InVaTWpheUNKYkJHS0NCb3JieHB6IiwiZXhwIjoxNzczODExMTc2LCJpYXQiOjE3NzMyMDYzNzZ9.wWf45dV8ZJ7e4N9wcBNkQP17LrjZfIkKpo1LFaI9BWo; focus-token-type=15; xingyun-lang=zh-CN; ssa.origin-api=2b16bf126a49e58d7aaf7d6b8a0030b3a2aff89304f37ea47d636390d831a8b4322f34a988341e93868a61c8ca57fb7070b5f560663a16908ae388ca9d169ca25d660be19ee0eac9589d50adad5e208d09c2f5c1fca1be0356a13ac7701f30e70384fb7a82a526e971d3bb6bdccc555cb503e459ed64badf781cedebc59cc6f2; sso.jd.com=BJ.4E0BD94B75F04F0101A45A9ED03BFDAB.0320260312144354; ssa.ticket=BJ.A53C2901B8FF4B6173B6BDD038BF07FE.0520260312144354S; ssa.origin-gateway=66fd8fd459a60a025a1896a88dc481636366140f1600cb1dbacbc26bcba7ab856ba8f3f6ad057cacc33f718ed09849faf5f638c1d96f7ed95332c2a4e12462e9c1a4e3d7652cadd021a78b712b2f1215b964fafd03735147581818a4620e85e8c0a2e0c1157e5f8bfc4f35b9dcb1e8cf017f53d46c834cdb13fec760563f6196; ssa.global.ticket=83c6b8ed99d15ef70605bfcd5d62f3529b947ca77436d6cb96caf768f7bf8186; __jdc=121254977; __jda=121254977.1769570983451733331359.1769570983.1773297835.1773303455.89; __jdb=121254977.1.1769570983451733331359|89.1773303455; JSESSIONID=41EEF5FEC38D7CF79BE639369849A6CA; RT=\"z=1&dm=jd.com&si=b5mverxa8lp&ss=mmn73aqh&sl=1&tt=rd&ld=1jc\"");
                headers.add("Remote Address", "127.0.0.1");

                // 5. 构建HTTP实体
                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

                Long count = 0L;
                while (true) {
                    // 6. 发送POST请求
                    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

                    // 7. 处理响应
                    System.out.println("-----------------------------------------");
                    System.out.println("Status Code: " + response.getStatusCode());
                    System.out.println("Response Body: " + response.getBody());
                    System.out.println(sdf.format(new Date()));
                    int i = random.nextInt(50);
                    System.out.println("睡眠时间：" + i + "秒\t请求次数：" + ++count);
                    TimeUnit.SECONDS.sleep(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
