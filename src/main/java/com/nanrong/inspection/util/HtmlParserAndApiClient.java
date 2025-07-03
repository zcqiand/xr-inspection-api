package com.nanrong.inspection.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.nanrong.inspection.dto.test_item_setting.TestItemSettingCreateRequestDto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HtmlParserAndApiClient {

  private final RestTemplate restTemplate;
  private final String apiUrl;

  public HtmlParserAndApiClient(String apiUrl) {
    this.restTemplate = new RestTemplate();
    this.apiUrl = apiUrl;
  }

  public List<TestItemInfo> extractTestItemNames(String htmlFilePath) throws IOException {
    File input = new File(htmlFilePath);
    Document doc = Jsoup.parse(input, "UTF-8");
    List<TestItemInfo> testItemInfos = new ArrayList<>();

    // 查找所有表格
    Elements tables = doc.select("table");

    for (Element table : tables) {
      // 查找表格中的所有th和td元素
      Elements cells = table.select("th, td");
      for (Element cell : cells) {
        String text = cell.text().trim().replaceAll("\\s+", "");
        // 过滤掉空字符串和只包含数字、括号、斜杠等非检测项目名称的文本
        if (!text.isEmpty() && !text.replaceAll("\\s+", "").matches("^[\\d\\(\\)\\-/]*$")
            && !text.contains("样品信息") && !text.contains("检测信息") && !text.contains("结论")
            && !text.contains("备注") && !text.contains("力学性能") && !text.contains("工艺性能")
            && !text.contains("重量偏差") && !text.contains("技术要求") && !text.contains("检测结果")
            && !text.contains("检测依据") && !text.contains("弯曲压头直径") && !text.contains("弯曲角度")
            && !text.contains("表面裂纹") && !text.contains("检验") && !text.contains("样品编号")
            && !text.contains("钢筋生产厂家及钢筋批号") && !text.contains("公称直径") && !text.contains("断口离焊缝距离")
            && !text.contains("断裂特征") && !text.contains("接头类型") && !text.contains("接头等级")
            && !text.contains("生产厂家") && !text.contains("断裂位置") && !text.contains("样品种类")
            && !text.contains("炉号") && !text.contains("批号") && !text.contains("代表数量")
            && !text.contains("样品状态") && !text.contains("检测类别") && !text.contains("焊接方式")
            && !text.contains("钢筋牌号") && !text.contains("焊工姓名") && !text.contains("焊工证号")
            && !text.contains("样品来源") && !text.contains("收样日期") && !text.contains("检测日期")
            && !text.contains("检测地址") && !text.contains("检测环境") && !text.contains("判定依据")
            && !text.contains("主要设备") && !text.contains("代表数量") && !text.contains("检测内容")
            && !text.contains("结论") && !text.contains("反向")) {
          String itemName = text;
          String itemUnit = "";

          // 提取括号内的单位
          int startIndex = text.indexOf("（");
          int endIndex = text.indexOf("）");
          if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            itemName = text.substring(0, startIndex).trim();
            itemUnit = text.substring(startIndex + 1, endIndex).trim();
          } else {
            startIndex = text.indexOf("(");
            endIndex = text.indexOf(")");
            if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
              itemName = text.substring(0, startIndex).trim();
              itemUnit = text.substring(startIndex + 1, endIndex).trim();
            }
          }
          testItemInfos.add(new TestItemInfo(itemName, itemUnit));
        }
      }
    }
    return testItemInfos;
  }

  public void sendTestItemSetting(TestItemSettingCreateRequestDto requestDto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<TestItemSettingCreateRequestDto> request = new HttpEntity<>(requestDto, headers);

    try {
      restTemplate.postForObject(apiUrl, request, String.class);
      System.out.println("成功发送检测项目设置: " + requestDto.getTestItemName());
    } catch (Exception e) {
      System.err.println("发送检测项目设置失败: " + requestDto.getTestItemName() + ", 错误: " + e.getMessage());
    }
  }

  public boolean existsTestItemSetting(String testItemName) {
    String checkUrl = apiUrl + "/exists?testItemName=" + testItemName;
    try {
      Boolean exists = restTemplate.getForObject(checkUrl, Boolean.class);
      return Boolean.TRUE.equals(exists);
    } catch (Exception e) {
      System.err.println("查询检测项目是否存在失败: " + testItemName + ", 错误: " + e.getMessage());
      return false; // 查询失败，默认认为不存在，避免重复发送
    }
  }

  // 辅助类，用于存储提取的检测项目名称和单位
  private static class TestItemInfo {
    String name;
    String unit;

    public TestItemInfo(String name, String unit) {
      this.name = name;
      this.unit = unit;
    }
  }

  // 简单的拼音转换，用于生成英文代码
  private static String toPinyin(String chinese) {
    // 这是一个简化的示例，实际应用中需要更完善的拼音转换库
    // 这里只做简单的替换，例如“钢筋” -> "gangjin"
    return chinese.replace("钢筋", "Gangjin").replace("焊接", "HanJie").replace("接头", "JieTou")
        .replace("检测", "JianCe").replace("报告", "BaoGao").replace("样品", "YangPin")
        .replace("状态", "ZhuangTai").replace("类别", "LeiBie").replace("方式", "FangShi")
        .replace("牌号", "PaiHao").replace("焊工", "HanGong").replace("姓名", "XingMing")
        .replace("证号", "ZhengHao").replace("来源", "LaiYuan").replace("代表", "DaiBiao")
        .replace("数量", "ShuLiang").replace("信息", "XinXi").replace("收样", "ShouYang")
        .replace("日期", "RiQi").replace("地址", "DiZhi").replace("环境", "HuanJing")
        .replace("依据", "YiJu").replace("判定", "PanDing").replace("主要", "ZhuYao")
        .replace("设备", "SheBei").replace("内容", "NeiRong").replace("编号", "BianHao")
        .replace("生产厂家", "ShengChanChangJia").replace("批号", "PiHao").replace("公称", "GongCheng")
        .replace("直径", "ZhiJing").replace("技术", "JiShu").replace("要求", "YaoQiu")
        .replace("抗拉", "KangLa").replace("强度", "QiangDu").replace("断口", "DuanKou")
        .replace("离焊缝", "LiHanFeng").replace("距离", "JuLi").replace("断裂", "DuanLie")
        .replace("特征", "TeZheng").replace("弯曲", "WanQu").replace("试验", "ShiYan")
        .replace("机械", "JiXie").replace("连接", "LianJie").replace("类型", "LeiXing")
        .replace("等级", "DengJi").replace("极限", "JiXian").replace("位置", "WeiZhi")
        .replace("力学", "LiXue").replace("性能", "XingNeng").replace("工艺", "GongYi")
        .replace("重量", "ZhongLiang").replace("偏差", "PianCha").replace("下屈服", "XiaQuFu")
        .replace("断后", "DuanHou").replace("伸长率", "ShenChangLv").replace("最大力", "ZuiDaLi")
        .replace("总延伸率", "ZongYanShenLv").replace("压头", "YaTou").replace("角度", "JiaoDu")
        .replace("表面", "BiaoMian").replace("裂纹", "LieWen").replace("检验", "JianYan")
        .replace("反向", "FanXiang").replace("结论", "JieLun").replace("备注", "BeiZhu")
        .replaceAll("[^a-zA-Z0-9]", ""); // 移除所有非字母数字字符
  }

  public static void main(String[] args) {
    String apiUrl = "http://localhost:8080/api/test-item-settings"; // 替换为你的API地址
    HtmlParserAndApiClient parser = new HtmlParserAndApiClient(apiUrl);

    String[] htmlFiles =
        {"doc/钢筋焊接接头检测报告.html", "doc/钢筋机械连接接头检测报告.html", "doc/钢筋力学性能、工艺性能、重量偏差检测报告.html"};

    // 用于存储已处理的检测项目名称，避免重复发送
    List<String> processedItemNames = new ArrayList<>();

    for (String filePath : htmlFiles) {
      try {
        List<TestItemInfo> itemInfos = parser.extractTestItemNames(filePath);
        System.out.println("从文件 " + filePath + " 中提取的检测项目信息:");
        for (TestItemInfo info : itemInfos) {
          // 先检查数据库中是否已存在该检测项目
          if (parser.existsTestItemSetting(info.name)) {
            System.out.println("- 数据库中已存在检测项目，跳过: " + info.name);
          } else if (!processedItemNames.contains(info.name)) {
            System.out.println("- 名称: " + info.name + ", 单位: " + info.unit);
            // 创建DTO并发送到API
            TestItemSettingCreateRequestDto dto = TestItemSettingCreateRequestDto.builder()
                .testItemName(info.name).testItemType("报告模板提取") // 可以根据实际情况设置类型
                .testItemCode(toPinyin(info.name)) // 根据名称生成英文代码
                .testItemUnit(info.unit) // 提取的单位
                .build();
            parser.sendTestItemSetting(dto);
            processedItemNames.add(info.name);
          } else {
            System.out.println("- 本次运行中已处理的重复检测项目名称，跳过: " + info.name);
          }
        }
      } catch (IOException e) {
        System.err.println("读取文件失败: " + filePath + ", 错误: " + e.getMessage());
      }
    }
  }
}
