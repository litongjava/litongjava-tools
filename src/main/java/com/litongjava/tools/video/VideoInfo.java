package com.litongjava.tools.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoInfo {
  private String filename;
  /**
   * Unit Byte
   */
  private Long size;
  /**
   * Unit second
   */
  private Long length;
}
