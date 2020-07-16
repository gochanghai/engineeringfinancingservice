package com.shenhua119.leadservice.common;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.shenhua119.leadservice.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * aliyun短信服务
 */
public class Aliyunsms {

    @Autowired
    private SMSCodeService smsCodeService;

    // 产品名称:云通信短信API产品,开发者无需替换

    private static final String product = "Dysmsapi";

    // 产品域名,开发者无需替换

    private static final String domain = "dysmsapi.aliyuncs.com";

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)

    private static String accessKeyId = "";

    private static String accessKeySecret = "";

    private static String signName = "";

    private static String identifyingTempleteCode = "yourAccessKeySecret";

    private static String registTempleteCode = "yourAccessKeySecret";

    public static boolean sendSms(String phoneNumber, String code){
        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("TemplateCode", "SMS_161592639");
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
