package com.litongjava.tools.demo;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.litongjava.tools.utils.PdfUtils;

public class PdfToImageDemo {

  public static void main(String[] args) {
    new PdfToImageDemo().pdfDocumentToImage2();
  }

  /**
   * 多页PDF文档转为图片
   * 此处单元测试的注解是采用:org.junit.Test
   */

  public void pdfDocumentToImage2() {
    String source = "F:\\document\\subject-docs\\03_Math\\高中数学\\人教A版教材\\人教版高中数学A版必修4.pdf";
    String desFileName = "人教版高中数学A版必修4";
    String desFilePath = "F:\\document\\subject-docs\\03_Math\\高中数学\\人教版高中数学A版必修4";
    String imageType = "png";
    Pair<Boolean, Object> pair = PdfUtils.pdfToImage(source, desFilePath, desFileName, imageType);
    System.out.println("PDF文档转化为图片结果：" + pair.getLeft());
    if (!pair.getLeft()) {
      System.out.println("" + pair.getRight());
    } else {
      @SuppressWarnings("unchecked")
      List<String> fileList = (List<String>) pair.getRight();
      System.out.println("转化的文件的内容：");
      fileList.forEach(System.out::println);
    }
  }

}
