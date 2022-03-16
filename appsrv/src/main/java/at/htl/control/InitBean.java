package at.htl.control;

import at.htl.entity.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class InitBean {

    @Inject
    LevelRepository levelRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    CourseRepository courseRepository;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    FileRepository fileRepository;

    @Inject
    UsageRepository usageRepository;

    @Inject
    AccessTokenRepository accessTokenRepository;

    void onStart(@Observes StartupEvent event) throws IOException {
        initDb();
    }

    @Transactional
    void initDb() throws IOException {
        String resourcesPath = Paths
                .get("")
                .toAbsolutePath() +
                "/src/main/resources/META-INF/resources/";

        File mediafilesFolder = new File(resourcesPath + "mediafiles");

        mediafilesFolder.mkdir();

        List<File> courseFolders = Arrays.stream(Objects.requireNonNull(mediafilesFolder.listFiles(File::isDirectory)))
                .collect(Collectors.toList());

        HashMap<String, Level> levels = new HashMap<>();

        courseFolders.forEach(folder -> {
            Arrays.asList(Objects.requireNonNull(folder.listFiles(File::isDirectory))).forEach(levelFolder -> {
                if (levels.get(levelFolder.getName()) == (null)) {
                    Level level = levelRepository.save(new Level(levelFolder.getName(), ""));
                    levels.put(levelFolder.getName(), level);
                }

                Course course = courseRepository.save(new Course(
                                folder.getName(),
                                "",
                                levels.get(levelFolder.getName())
                        )
                );

                Arrays.asList(Objects.requireNonNull(levelFolder.listFiles(File::isFile))).forEach(file -> {
                    ContentType ct;

                    if (file.getName().split("\\.")[1].contains("mp4")) {
                        ct = ContentType.VIDEO;
                    } else {
                        ct = ContentType.AUDIO;
                    }

                    D_File d_file = fileRepository.save(new D_File(
                            file.getName(),
                            String.format("mediafiles/%s/%s/%s",
                                    folder.getName(),
                                    levelFolder.getName(),
                                    file.getName()),
                            "",
                            ct)
                    );
                    usageRepository.persist(new Usage(course, d_file));
                });
            });
        });

        //User
        User kelly = new User("KellyTran03", "Kelly", "Tran", "12324", "TEACHER");
        userRepository.persist(kelly);
        User rosalie = new User("RosalieMandel14", "Rosalie", "Mandel ", "456", "TEACHER");
        userRepository.persist(rosalie);
        User sandy = new User("SandyTang24", "Sandy", "Tang ", "sandypw", "TEACHER");
        userRepository.persist(sandy);
        User anton = new User("Anton123", "Anton", "Traxler", "akdjb", "STUDENT");
        userRepository.persist(anton);
        User lisa = new User("Lisa124", "Lisa", "Müller ", "<jnov", "STUDENT");
        userRepository.persist(lisa);
        User jonas = new User("JonasT123", "Jonas", "Berg ", "orslgn", "STUDENT");
        userRepository.persist(jonas);
    }

    //set of all paths
    private Set<String> listFiles(String dir) throws IOException {
        try (var stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }
}
