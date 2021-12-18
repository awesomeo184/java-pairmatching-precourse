package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", new ArrayList<>(Arrays.asList("자동차 경주", "로또", "야구게임"))),
    LEVEL2("레벨2", new ArrayList<>(Arrays.asList("장바구니", "결제", "지하철노선도"))),
    LEVEL3("레벨3", Collections.EMPTY_LIST),
    LEVEL4("레벨4", new ArrayList<>(Arrays.asList("성능개선", "배포"))),
    LEVEL5("레벨5", Collections.EMPTY_LIST),
    ;

    private final String name;
    private final List<String> missionNames;

    Level(String name, List<String> missionNames) {
        this.name = name;
        this.missionNames = missionNames;
    }

    public String getName() {
        return name;
    }

    public List<String> getMissionNames() {
        return missionNames;
    }
}
