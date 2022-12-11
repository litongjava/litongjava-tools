package com.litongjava.tools;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

/**
 * 文件工具
 * @author Administrator
 *
 */
public class FileToolsGetFileExtension {

  public static void main(String[] args) throws IOException {
    FileToolsGetFileExtension app = new FileToolsGetFileExtension();
    Set<String> allExtendsionInPath = app.getAllExtendsionInPath("F:\\document\\k.开发资料总结");
    for (String string : allExtendsionInPath) {
      System.out.println("git lfs track "+"\"*."+string+"\"");
    }
  }
  /**
   * 获取指定目录下所有文件的扩展名
   * @return
   * @throws IOException 
   */
  public Set<String> getAllExtendsionInPath(String path) throws IOException {
    Set<String> retval = new HashSet<>();
    Path dir = Paths.get(path);
    Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String filename = file.toString();
        String extension = FilenameUtils.getExtension(filename);
        retval.add(extension);
        return super.visitFile(file, attrs);
      }
    });
    return retval;
  }
}
