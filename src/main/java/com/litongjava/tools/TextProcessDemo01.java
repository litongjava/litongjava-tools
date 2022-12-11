package com.litongjava.tools;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author create by Ping E Lee on 2022年3月19日 下午11:06:39
 *
 */
@Slf4j
public class TextProcessDemo01 {
  /**
   * 1.读取文件
   * 2.
   * 
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    String filepath = "D:\\document\\k.开发资料总结\\07.前端\\01.HTML5+CSS3模块\\知识体系.txt";
    File file = new File(filepath);
    String text = FileUtils.readFileToString(file, "UTF-8");
    String[] array = text.split("\n");
    log.info("size:{}", array.length);
    for (String line : array) {
      String[] split = line.split("：");
      if (split.length > 1) {
        System.out.println(split[1]);
      }
    }

  }
}
