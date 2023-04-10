package com.litongjava.tools.video;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.litongjava.utils.video.VideoUtils;

public class GetVideoLength {

  public static void main(String[] args) throws IOException {
    /**
     * 1.导出数据到excel
     * 2.修改Excel中的时间
     * 3.读取Excel,比对时间
     * 4.重命名
     */
    GetVideoLength getVideoLength = new GetVideoLength();
    String filePath = "F:\\video\\左程云-算法课\\03.进阶班";
    List<VideoInfo> list = getVideoLength.getVoideoInfo(filePath);
//    writeToExcel(filePath, list);
    readExcelAndRename(filePath, list);

  }

  /**
   * 读取Excel并对文件进行重命名
   * @param filePath
   * @param list
   */
  private static void readExcelAndRename(String filePath, List<VideoInfo> list) {
    EasyExcel.read(filePath + "\\课程信息.xlsx", VideoInfo.class, new AnalysisEventListener<VideoInfo>() {

      // 每读取一行就调用该方法
      @Override
      public void invoke(VideoInfo data, AnalysisContext context) {
        for (VideoInfo videoInfo : list) {
          Long length1 = videoInfo.getLength();
          Long length2 = data.getLength();
//          System.out
//              .println(videoInfo.getFilename() + " " + length1 + " compare to" + data.getFilename() + " " + length2);
//          if (length1 == 7499) {
//            System.out.println("break");
//          }
          if (length1.equals(length2) || length1 == length2 - 1 || length1 == length2 + 1) {
            String srcFilename = videoInfo.getFilename();
            String dstFilename = data.getFilename() + "." + FilenameUtils.getExtension(srcFilename);

            File src = new File(filePath, srcFilename);
            File dst = new File(filePath, dstFilename);
            try {
              String x = "rename " + srcFilename + " to " + dstFilename;
              System.out.println(x);
              FileUtils.moveFile(src, dst);
            } catch (Exception e) {
              e.printStackTrace();
            }
            break;
          }
        }
      }

      // 全部读取完成就调用该方法
      @Override
      public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完成");
      }
    }).sheet().doRead();
  }

  private static void writeToExcel(String filePath, List<VideoInfo> list) {
    // 导出数据
    ExcelWriterBuilder excelBuilder = EasyExcel.write(filePath + "\\文件信息.xlsx", VideoInfo.class);
    ExcelWriterSheetBuilder sheetBuilder = excelBuilder.sheet();
    sheetBuilder.doWrite(list);
  }

  /**
   * 获取指定目录下面的视频长度
   * @throws IOException 
   */
  public List<VideoInfo> getVoideoInfo(String path) throws IOException {
    File[] listFiles = new File(path).listFiles();
    List<VideoInfo> retval = new ArrayList<>();
    for (File file : listFiles) {
      retval.add(new VideoInfo(file.getName(), file.length(), getVideoLength(file)));
    }
    return retval;
  }

  /**
   * 获取单个视频长度
   * @param file
   * @return
   * @throws IOException 
   */
  private Long getVideoLength(File file) throws IOException {
    return VideoUtils.getDuration(file.getAbsolutePath());
  }
}
