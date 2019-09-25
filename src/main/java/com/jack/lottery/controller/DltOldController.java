package com.jack.lottery.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.lottery.entity.BigLottoBall;
import com.jack.lottery.entity.DltOld;
import com.jack.lottery.service.DltOldService;
import com.jack.lottery.util.GetLottery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "工具")
public class DltOldController {

    private DltOldService dltOldService;

    public DltOldController(DltOldService dltOldService) {
        this.dltOldService = dltOldService;
    }

    @PostMapping
    @ApiOperation("获取大乐透中奖记录")
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

            for(int i=0;i<list.size();i++){
                BigLottoBall bigLottoBall = list.get(i);
                if (0 <= dltOldService.count(new QueryWrapper<DltOld>().eq("periods", bigLottoBall.getPeriod_Id()))){
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
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-star);
    }

    @PostMapping("seer")
    @ApiOperation("预测")
    public void seer(){
        int RED = 35;
        int BLUE = 12;

        long sum = 0;

        long start = System.currentTimeMillis();

        for (int r1 = 1; r1 <= RED; r1++){
            for (int r2 = 1; r2 <= RED; r2++){
                if (r1 == r2){
                    continue;
                }
                for (int r3 = 1; r3 <= RED; r3++){
                    if (r1 == r2 || r1 == r3 || r2 == r3){
                        continue;
                    }
                    for (int r4 = 1; r4 <= RED; r4++){
                        if (r1 == r2 || r1 == r3 || r2 == r3 || r2==r4 || r3==r4){
                            continue;
                        }
                        for (int r5 = 1; r5 <= RED; r5++){
                            if (r1 == r2 || r1 == r3 || r2 == r3 || r2==r4 || r2==r5 || r3==r4 || r3==r5){
                                continue;
                            }
                            for (int b1 = 1; b1 <= BLUE; b1++){
                                for (int b2 = 1; b2 <= BLUE; b2++){
                                    if (b1 == b2){
                                        continue;
                                    }
                                    sum++;
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(sum);
        System.out.println("耗时： "+ (System.currentTimeMillis() - start));
    }
}
