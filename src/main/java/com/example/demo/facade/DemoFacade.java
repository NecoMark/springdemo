package com.example.demo.facade;

import com.example.demo.entity.DemoInfoEntity;
import com.example.demo.service.DemoService;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * @author ljyang
 */

@RestController
@RequestMapping("/spring")
public class DemoFacade {

    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    DemoService demoService;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/demo-info")
    public List<DemoInfoEntity> getDemoInfo(){
        return demoService.getDemoInfo();
    }

    @PostMapping("/demo-info")
    public void addDemoInfo(@RequestBody DemoInfoEntity demoInfoEntity){
        demoService.addDemoInfo(demoInfoEntity);

    }

    @GetMapping("/mail")
    public String sendMail() throws Exception{
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.port", 465);
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("zhishan49@zhiyuzhishantech.cn");


        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.exmail.qq.com");
        javaMailSender.setUsername("zhishan49@zhiyuzhishantech.cn");
        javaMailSender.setPassword("Zhiyu49");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        messageHelper.addInline("myLogo", new ClassPathResource("static/img/DNS.png"));
        messageHelper.setFrom("zhishan49@zhiyuzhishantech.cn");
        messageHelper.setTo("ljyang@Hillstonenet.com");
        messageHelper.setSentDate(new Date());
        messageHelper.setSubject("tets");
        javaMailSender.setJavaMailProperties(javaMailProperties);
//        messageHelper.setText("test");

        Map<String, Object> model = new HashMap<>();
        model.put("title", "新建工单");
        model.put("priority", "高");
        model.put("severity", "严重");
        model.put("asset", "服务器");
        model.put("owner", "kobe");
        model.put("status", "待处理");
        model.put("person", "James");
        model.put("desc", "年幼时雨天的快乐真的是无厘头的。一到下雨天就和小伙伴们撑着伞，穿着雨鞋，踩在泥泞的路上，淌过一个又一个水洼，溅起一身的泥水；用小伞漂在水洼里旋转，高高扬起脚踢小朋友一身泥水或者干脆连伞也不撑，就在雨里面象疯子一样嬉戏奔跑玩乐。");
        model.put("solution", "曾几何时，把自己当作是农夫，平平淡淡地播种，平平淡淡地收获；总以为付出就会有回报，总以为自己不会受伤害，但结局却成了一种最深的伤痛，在心底烙下了一个永远的伤疤，一触就痛。都说“曾经的伤痛是人生中最珍贵的财富\"，可那些我不想要的回忆是否可以随意删除呢？");
        model.put("operate", "总想为这雨写点什么，却觉得自己的心性早已不如从前那么敏感了，也不会再有一点新意出于笔端。但让我百思不得其解的是，一向多愁善感的我对于这雨的麻木究竟是因为自己当真已经老了许多，还是因为网络的物化，碎片的思维，让心灵深处也蒙了尘，多了现实与物质的，而少了精神的回归。");

        Map<String, Object> model_test = new HashMap<>();
        model_test.put("name", "curry");

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("email_test.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model_test);
        messageHelper.setText(text, true);

        javaMailSender.send(mimeMessage);


        return "mail";
    }
}

/**
 * spring.kafka.bootstrap-servers=10.180.15.68:9092
 * spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.ByteArrayDeserializer
 * spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
 */