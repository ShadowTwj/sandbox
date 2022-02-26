package cn.tianwenjie.common;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;

/**
 * @author tianwj
 * @date 2021/11/30 18:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Polygon {

    private List<Point> polygon;

    public PGpolygon convertPGpolygon() {
        List<PGpoint> pgPoints = polygon.stream().map(it -> new PGpoint(it.getLon(), it.getLat())).collect(Collectors.toList());

        return new PGpolygon(pgPoints.toArray(new PGpoint[0]));
    }
}
