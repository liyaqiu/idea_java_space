package jfreetest;

import lombok.Data;

@Data
public class MarkLineDto {
    private Double[] markLineValues = new Double[0];
    private String[] markLineNames = new String[0];
    private LabelPosition[] labelPositions = new LabelPosition[0];

    public enum LabelPosition {
        Top,RIGHT
    }
}
