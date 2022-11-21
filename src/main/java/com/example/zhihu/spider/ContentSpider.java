//package com.example.zhihu.spider;
//
//import com.example.zhihu.util.HttpUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Map;
//
//@Component
//public class ContentSpider {
//
//    @Autowired
//    private HttpUtil httpUtil;
//
//    private static final String HOST = "www.sogou.com";
//
//    private static final String REFERER = "https://www.sogou.com/link?url=hedJjaC291MBtMZVirtXo-8HqdeMO9Y0";
//
//    private static final String COOKIE = "_zap=a55c657a-d187-476b-8b69-a93383cb4a99; _xsrf=32404823-3e61-4578-ad47-bf4354cf9097; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1666582485; d_c0=APCXT1MXwhWPTl3u5CwKz_HpiahGPPrzY_4=|1666582487; __snaker__id=Uc7uUKK3VmgCK8x2; o_act=login; utm_source=sogou; ref_source=other_https://www.zhihu.com/signin?next=/; gdxidpyhxdE=WnDCzq5it7%2B1eNj%2F6WZ32rQJIgVbLi%5CJkwlqN7nWoUzajTPCNEIha8SinPe%2B5%2FMe%2F0St8Ry%2FQ0MUD3pjS4NcsBg54Hjc5rW54tRKQyOI0kcjQ6x3straPjaNhh2QnNI84lDdzsvS4unt9Ip3A1%2BJ2YzyvbdbD9%5CpBQu5thMler5u1Sak%3A1666583387647; YD00517437729195%3AWM_NI=PF25461%2B4xcou2ORWAKkx5KY387X3Gjq%2BkGLgk5yPCstBT%2BkB2DsAooUwGYbvN7pHDypSGlp33kZ1CoS1fviX3cu%2BG0XbKW4r3vp6yBjIvWvNoyBxASS%2Bxh%2FY1bE6l7jVnM%3D; YD00517437729195%3AWM_NIKE=9ca17ae2e6ffcda170e2e6eeaebb5af7aba090db629bb88aa2d14f839a8bacc847979d8ab2cf63a8eb9e83db2af0fea7c3b92a949afed6b73ef3f5fc99e265b7ee81b3d64b89b5b8d6ca53f1e8f9b9b24daaafe58cc648a5b5fb9ad349b1ac8eb5aa6182ee8c94c465ad8bb9a8dc6ab692f991cb50bbb09da8ef7cf1eda5bbb449ad8dff87f5639cedad96aa4df488aeacd640b2edba91b57382b68797d67e8eb1b7a3fb4b918de599eb259befaea6b568f5b69a8fd037e2a3; YD00517437729195%3AWM_TID=pxqUzuAy1c5EFVVAAAbVJBWRVKmd9APD; expire_in=15552000; q_c1=e3eb8598a4fe48e7a55bfee791c216a7|1666582506000|1666582506000; NOT_UNREGISTER_WAITING=1; captcha_session_v2=2|1:0|10:1666582555|18:captcha_session_v2|88:aXkvTjJFVTJsTGlBMGlhSW1qRXZZbGQ4c2YxdFlUT1FwdFhBZHZUSHhHR0dRcy92dWs1VzBwZ0xiZU16Z2I0Sw==|b198c58cc3c864e530186b5970c90f51ff1697d60188c7852f60e66f7dfa9d1c; captcha_ticket_v2=2|1:0|10:1666582561|17:captcha_ticket_v2|704:eyJ2YWxpZGF0ZSI6IkNOMzFfR0d4a1NOQWExeEFsODlqd3RSQ2tENkZIUHE4cURwdFlrVUk5RHRfb29yaXRvWVhPZjRWTUh2WWNnTDJ1TmxRbEFLdnBVNDRpbHROLk05ems0TDUxTWEwZFlyUy53QS1hT0QwWVRmQzE4S3dBVk4ueklqbC5kTkM5RzZDZ0pfdGd0Mi5zeXpJclNiU2ZQNjFEUy1hTUhMYUY1anpVVHVrdGJKZVVTck1BZjFMUFdhQk8wUEtUdWFOVWJ3YnlSTHM4ZHJDLi1BNTlJLi5xRHcxYlhkRWhrcEJMbHc3bjZVMmU1RllqbkNLdkdRX3VDVXlLOWxGTTJHd1ZBSzlfdXguOHFWODVoZW5zdWU4ZXNxRm04TVE2MmpoTE10N3EyZzJKdzBHZ0c4bVBEc3VnVm1UMVJ0N0dnQkplYXNuaW1qWTJOMHNYS0J2Y0E1NVIwcUxEZVl0eVVCWF9uWlVFVmR6Z3l6Z1N4aU10VW9BNGM2WXRaTjVWQ3FFT1RHZEwuOTZ4SWtWMlZReDBXVjFyQXhPelVPZXpSUkZFVFA3SXF4MHNqWHotSlZBRUdKTVZodE9KcEc0ZllXWXJpeHFfekZvMlN0VDQwTExadU4wVVZpdVY1TC5DUUd1QmhjcS41bjFsODJtdUtheWdqMjRrb01rZXQ2bmt5Q3pMcXJYMyJ9|d7918a436a26b950941913c6c1abfc97a93c54d825824c91e3f46f5eeee21ded; z_c0=2|1:0|10:1666582576|4:z_c0|92:Mi4xQTFWS013QUFBQUFBOEpkUFV4ZkNGU1lBQUFCZ0FsVk5NRlpEWkFCNkwxZDJ1R3g2eXdrb0pyNTFPVW9mSHhyYTF3|60d8dc2d4538b34d9bd69935b495809924571ab4b104a626333928085440f6a3; SESSIONID=zI6zjpHcXhA4FiEI2sy3G5asLIbXPATXX8BaMIXNmEb; JOID=Vl4XCkkvXZL43dwgHSLpQv6JxuYOVxCvibGubHVxN9fHkLNnUB9ZxJbZ2i8d2DLxSYBptbbJR9tbUzSdrFTALNM=; osd=UFsSAU8pWJfz29olGCnvRPuMzeAIUhWkj7eraX53MdLCm7VhVRpSwpDc3yQb3jf0QoZvsLPCQd1eVj-bqlHFJ9U=; tst=r; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1666583400; KLBRSID=cdfcc1d45d024a211bb7144f66bda2cf|1666583403|1666582486";
//
//    private static final String URL = "https://www.zhihu.com/";
//
//    @PostConstruct
//    public void init(){
//        saveData();
//    }
//
//    public void saveData(){
//        //  构件表头信息
//        Map<String,String> header = httpUtil.buildHeader(REFERER,HOST,COOKIE);
//        //  获取内容
//        String content = httpUtil.getContent(URL, header);
//    }
//}
