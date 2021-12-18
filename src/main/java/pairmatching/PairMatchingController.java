package pairmatching;

import pairmatching.domain.Course;
import pairmatching.domain.pair.Pair;
import pairmatching.domain.pair.PairService;
import pairmatching.domain.pair.PairTag;
import pairmatching.view.RematchMenu;
import pairmatching.view.View;
import pairmatching.view.Menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class PairMatchingController {

    private static final String BACKEND_CREW_NAMES = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_NAMES = "src/main/resources/frontend-crew.md";
    private final PairService pairService;

    public PairMatchingController() {
        pairService = initPairService();
    }

    private PairService initPairService() {
        return new PairService(loadCrews(Course.FRONTEND), loadCrews(Course.BACKEND));
    }

    public void run() {
        Menu menu;
        do {
            menu = View.getMenu();
            execute(menu);
        } while (menu != Menu.QUIT);
    }

    private void execute(Menu menu) {
        if (menu == Menu.PAIR_MATCHING) {
            View.printBoard();
            PairTag pairTag = View.getPairTag();

            if (pairService.isRegistered(pairTag)) {
                RematchMenu rematchMenu = View.getClearOrContinue();
                if (rematchMenu == RematchMenu.NO_OP) {
                    return;
                }
            }

            List<Pair> pairs = pairService.makePairs(pairTag);
            View.printParis(pairs);


        }
        if (menu == Menu.PAIR_SEARCH) {
            PairTag pairTag = View.getPairTag();
            List<Pair> pairs = pairService.getPairs(pairTag);
            View.printParis(pairs);


        }
        if (menu == Menu.PAIR_CLEAR) {
            pairService.clear();
            View.printClearResult();
        }
    }


    private List<String> loadCrews(Course course) {
        if (course == Course.BACKEND) {
            return loadBackendCrewName()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 파일이 존재하지 않습니다."));
        }

        if (course == Course.FRONTEND) {
            return loadFrontendCrewName()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 파일이 존재하지 않습니다."));
        }

        throw new IllegalArgumentException("[ERROR] 크루 생성에 실패했습니다.");
    }


    private Optional<List<String>> loadBackendCrewName() {
        try {
            Path path = Paths.get(BACKEND_CREW_NAMES);
            List<String> crewNames = Files.readAllLines(path);
            return Optional.of(crewNames);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<List<String>> loadFrontendCrewName() {
        try {
            Path path = Paths.get(FRONTEND_CREW_NAMES);
            List<String> crewNames = Files.readAllLines(path);
            return Optional.of(crewNames);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
