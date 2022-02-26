package cn.tianwenjie.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tianwj
 * @date 2021/11/30 18:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private Double lon;

    private Double lat;
}
