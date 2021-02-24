package com.dahua.clxx;

import com.dahua.clxx.mapper.ApplyMapper;
import com.dahua.clxx.mapper.UserMapper;
import com.dahua.clxx.pojo.PersonFaceImgDto;
import com.dahua.clxx.service.CardPersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@RunWith(SpringRunner.class)
@Slf4j
class ClxxApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private CardPersonService cardPersonService;

    @Test
    void contextLoads() {

        System.out.println(userMapper.getStudent("1"));
//        PersonFaceImgDto img = new PersonFaceImgDto();
//        img.setPersonCode("1");
//        img.setBase64file("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");
//        cardPersonService.updFaceImg(img);

//        User user = userMapper.selectById("1");
//        System.out.println(user);
//
//        Wrapper wrapper = Wrappers.<User>lambdaQuery().eq(User::getNo,"1").or().eq(User::getUserName,"李四");
//        List<User> ll = userMapper.selectList(wrapper);
//        System.out.println(ll);
////
//        Apply apply = applyMapper.selectById("1");
//        System.out.println(apply);
//
//        System.out.println(applyMapper.queryApply(null));

//        Page<User> page = new Page<>(1, 1);
//        page.setAsc("id");
//        Apply apply = new Apply();
//        apply.setId(3L);
//        apply.setTeacherId(2L);
//        applyMapper.updateById(apply);
//        IPage<ApplyVo> pageData = applyMapper.queryApplyPage(page,apply);
//        assertThat(page).isSameAs(pageData);
//        System.out.println(pageData.getRecords());
//        System.out.println(pageData.getTotal());
//        page.addOrder(OrderItem.asc("age"));

//        Page<Apply> page = new Page<>(2, 1);
//        Page<Apply> userIPage = (Page<Apply>) applyMapper.selectPage(page, Wrappers.<Apply>lambdaQuery().eq(Apply::getTeacherId, "2"));
//        assertThat(page).isSameAs(userIPage);
//        log.error("总条数 -------------> {}", userIPage.getTotal());
//        log.error("当前页数 -------------> {}", userIPage.getCurrent());
//        log.error("当前每页显示数 -------------> {}", userIPage.getSize());
//        List<Apply> records = userIPage.getRecords();
//        System.out.println(records);


//        Page<Apply> page = new Page<>(2, 2);
//        IPage<ApplyVo> userIPage = applyMapper.queryApplyPage(page, apply);
//        assertThat(page).isSameAs(userIPage);
//        log.error("总条数 -------------> {}", userIPage.getTotal());
//        log.error("当前页数 -------------> {}", userIPage.getCurrent());
//        log.error("当前每页显示数 -------------> {}", userIPage.getSize());
//        List<ApplyVo> records = userIPage.getRecords();
//        System.out.println(records.size());
//        System.out.println(records);
    }

}

