package com.jack.lottery.controller;


import com.jack.lottery.entity.BigLottoBall;
import com.jack.lottery.entity.DltOld;
import com.jack.lottery.service.DltOldService;
import com.jack.lottery.util.GetLottery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jack魏
 * @since 2019-09-24
 */
@RestController
@RequestMapping("/dlt-old")
public class DltOldController {

    private DltOldService dltOldService;

    public DltOldController(DltOldService dltOldService) {
        this.dltOldService = dltOldService;
    }

    @PostMapping
    public void getDLT(){
        long star=System.currentTimeMillis();
        String url="http://datachart.500.com/ssq/history/newinc/history.php?limit=100000&sort=0";
        try {
            //测试获取url信息
            //System.out.println(GetLottery.getString(url));
            //测试获取全部的双色球开奖信息
            //List<DoubleColorBall> list= GetLottery.getAllDCB();
            //测试获取全部的大乐透开奖信息
            List<BigLottoBall> list= GetLottery.getAllBLB();
            //测试获取这个双色球16120期号自后的开奖数据
            //List<DoubleColorBall> list= GetLottery.getBeforeDCB(16120);
            //测试获取这个双色球17120期号自后的开奖数据
            //List<DoubleColorBall> list= GetLottery.getBeforeDCB(17120);
            //测试获取这个大乐透16120期号自后的开奖数据
            //List<BigLottoBall> list= GetLottery.getBeforeBLB(16120);
            //测试获取这个大乐透17110期号自后的开奖数据
            //List<BigLottoBall> list= GetLottery.getBeforeBLB(17110);
            System.out.println(list.size());
            for(int i=0;i<list.size();i++){
                BigLottoBall bigLottoBall = list.get(i);
                DltOld dltOld = new DltOld();
                dltOld.setPeriods(bigLottoBall.getPeriod_Id());
                dltOld.setNo1(bigLottoBall.getRedBall_One());
                dltOld.setNo2(bigLottoBall.getRedBall_Tow());
                dltOld.setNo3(bigLottoBall.getRedBall_Three());
                dltOld.setNo4(bigLottoBall.getRedBall_Four());
                dltOld.setNo5(bigLottoBall.getRedBall_Fives());
                dltOld.setNo6(bigLottoBall.getBlueBall_One());
                dltOld.setNo7(bigLottoBall.getBlueBall_Tow());
                dltOldService.save(dltOld);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-star);
    }
}
